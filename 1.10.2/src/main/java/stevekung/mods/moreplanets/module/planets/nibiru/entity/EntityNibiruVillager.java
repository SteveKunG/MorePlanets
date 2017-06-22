package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedWitch;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.items.capsule.CapsuleType;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.ai.*;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public class EntityNibiruVillager extends EntityAgeable implements IMerchant, INpc, IEntityBreathable, ISpaceMob
{
    private static DataParameter<Integer> PROFESSION = EntityDataManager.createKey(EntityNibiruVillager.class, DataSerializers.VARINT);
    private int randomTickDivider;
    private boolean isMating;
    private boolean isPlaying;
    private Village villageObj;
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    private boolean needsInitilization;
    private boolean isWillingToMate;
    private int wealth;
    private String lastBuyingPlayer;
    private boolean isLookingForHome;
    private boolean areAdditionalTasksSet;
    private InventoryBasic villagerInventory;
    private ITradeList[] farmerTradeList = new ITradeList[] {
            new EmeraldForItems(new ItemStack(NibiruItems.INFECTED_WHEAT), new PriceInfo(18, 22)),
            new EmeraldForItems(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 6), new PriceInfo(15, 19)),
            new ListItemForEmeralds(new ItemStack(Items.BREAD), new PriceInfo(-4, -2)),
            new EmeraldForItems(new ItemStack(NibiruBlocks.INFECTED_MELON_BLOCK), new PriceInfo(7, 12)),
            new ListItemForEmeralds(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 0), new PriceInfo(-5, -7)),
            new ListItemForEmeralds(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 4), new PriceInfo(-7, -9))
    };
    private ITradeList[] librarianTradeList = new ITradeList[] {
            new EmeraldForItems(new ItemStack(Items.PAPER), new PriceInfo(24, 36)),
            new ListEnchantedBookForEmeralds(),
            new EmeraldForItems(new ItemStack(Items.BOOK), new PriceInfo(8, 10)),
            new ListItemForEmeralds(new ItemStack(NibiruBlocks.NIBIRU_BOOKSHELF, 1, 0), new PriceInfo(3, 4)),
            new ListItemForEmeralds(new ItemStack(NibiruBlocks.NIBIRU_BOOKSHELF, 1, 1), new PriceInfo(3, 4)),
            new ListEnchantedBookForEmeralds(),
            new ListEnchantedBookForEmeralds()
    };
    private ITradeList[] medicTradeList = new ITradeList[] {
            new EmeraldForItems(new ItemStack(Items.IRON_INGOT), new PriceInfo(24, 32)),
            new EmeraldForItems(new ItemStack(GCItems.basicItem, 1, 4), new PriceInfo(8, 10)),
            new ListItemForEmeralds(CapsuleType.getInfectedProtectionCapsule(), new PriceInfo(4, 6), true),
            new ListItemForEmeralds(CapsuleType.getDarkEnergyProtectionCapsule(), new PriceInfo(4, 6), true)
    };

    public EntityNibiruVillager(World world)
    {
        this(world, 0);
    }

    public EntityNibiruVillager(World world, int professionId)
    {
        super(world);
        this.villagerInventory = new InventoryBasic("Items", false, 8);
        this.setProfession(professionId);
        this.setSize(0.6F, 1.8F);
        ((PathNavigateGroundMP)this.getNavigator()).setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEvolvedZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityInfectedZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAINibiruVillagerTradePlayer(this));
        this.tasks.addTask(1, new EntityAINibiruVillagerLookAtTradePlayer(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(6, new EntityAINibiruVillagerMate(this));
        this.tasks.addTask(7, new EntityAINibiruVillagerFollowGolem(this));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAINibiruVillagerInteract(this));
        this.tasks.addTask(9, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.setCanPickUpLoot(true);
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    protected PathNavigate getNewNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    private void setAdditionalAItasks()
    {
        if (!this.areAdditionalTasksSet)
        {
            this.areAdditionalTasksSet = true;

            if (this.isChild())
            {
                this.tasks.addTask(8, new EntityAINibiruVillagerPlay(this, 0.32D));
            }
            else if (this.getProfession() == 0 || this.getProfession() == 3)
            {
                this.tasks.addTask(6, new EntityAINibiruVillagerHarvestFarmland(this, 0.6D));
            }
        }
    }

    @Override
    protected void onGrowingAdult()
    {
        if (this.getProfession() == 0 || this.getProfession() == 3)
        {
            this.tasks.addTask(8, new EntityAINibiruVillagerHarvestFarmland(this, 0.6D));
        }
        super.onGrowingAdult();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }

    @Override
    protected void updateAITasks()
    {
        if (--this.randomTickDivider <= 0)
        {
            BlockPos blockpos = new BlockPos(this);
            this.worldObj.getVillageCollection().addToVillagerPositionList(blockpos);
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.getVillageCollection().getNearestVillage(blockpos, 32);

            if (this.villageObj == null)
            {
                this.detachHome();
            }
            else
            {
                BlockPos blockpos1 = this.villageObj.getCenter();
                this.setHomePosAndDistance(blockpos1, (int)(this.villageObj.getVillageRadius() * 1.0F));

                if (this.isLookingForHome)
                {
                    this.isLookingForHome = false;
                    this.villageObj.setDefaultPlayerReputation(5);
                }
            }
        }

        if (!this.isTrading() && this.timeUntilReset > 0)
        {
            --this.timeUntilReset;

            if (this.timeUntilReset <= 0)
            {
                if (this.needsInitilization)
                {
                    for (MerchantRecipe merchantrecipe : this.buyingList)
                    {
                        if (merchantrecipe.isRecipeDisabled())
                        {
                            merchantrecipe.increaseMaxTradeUses(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                        }
                    }

                    this.populateBuyingList();
                    this.needsInitilization = false;

                    if (this.villageObj != null && this.lastBuyingPlayer != null)
                    {
                        this.worldObj.setEntityState(this, (byte)14);
                        this.villageObj.modifyPlayerReputation(this.lastBuyingPlayer, 1);
                    }
                }
                this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
            }
        }
        super.updateAITasks();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack itemStack)
    {
        boolean flag = itemStack != null && itemStack.getItem() == Items.SPAWN_EGG;

        if (!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking())
        {
            if (!this.worldObj.isRemote && (this.buyingList == null || this.buyingList.size() > 0))
            {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }
            player.addStat(StatList.TALKED_TO_VILLAGER);
            return true;
        }
        else
        {
            return super.processInteract(player, hand, itemStack);
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(PROFESSION, Integer.valueOf(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("Profession", this.getProfession());
        tagCompound.setInteger("Riches", this.wealth);
        tagCompound.setBoolean("Willing", this.isWillingToMate);

        if (this.buyingList != null)
        {
            tagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

            if (itemstack != null)
            {
                nbttaglist.appendTag(itemstack.writeToNBT(new NBTTagCompound()));
            }
        }
        tagCompound.setTag("Inventory", nbttaglist);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setProfession(tagCompund.getInteger("Profession"));
        this.wealth = tagCompund.getInteger("Riches");
        this.isWillingToMate = tagCompund.getBoolean("Willing");

        if (tagCompund.hasKey("Offers", 10))
        {
            NBTTagCompound nbttagcompound = tagCompund.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound);
        }

        NBTTagList nbttaglist = tagCompund.getTagList("Inventory", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            ItemStack itemStack = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));

            if (itemStack != null)
            {
                this.villagerInventory.addItem(itemStack);
            }
        }
        this.setCanPickUpLoot(true);
        this.setAdditionalAItasks();
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return this.isTrading() ? SoundEvents.ENTITY_VILLAGER_TRADING : SoundEvents.ENTITY_VILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    @Override
    public void setRevengeTarget(EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (this.villageObj != null && livingBase != null)
        {
            this.villageObj.addOrRenewAgressor(livingBase);

            if (livingBase instanceof EntityPlayer)
            {
                int i = -1;

                if (this.isChild())
                {
                    i = -3;
                }

                this.villageObj.modifyPlayerReputation(livingBase.getName(), i);

                if (this.isEntityAlive())
                {
                    this.worldObj.setEntityState(this, (byte)13);
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        if (this.villageObj != null)
        {
            Entity entity = cause.getEntity();

            if (entity != null)
            {
                if (entity instanceof EntityPlayer)
                {
                    this.villageObj.modifyPlayerReputation(entity.getName(), -2);
                }
                else if (entity instanceof IMob)
                {
                    this.villageObj.endMatingSeason();
                }
            }
            else
            {
                EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16.0D);

                if (entityplayer != null)
                {
                    this.villageObj.endMatingSeason();
                }
            }
        }
        super.onDeath(cause);
    }

    @Override
    public void setCustomer(EntityPlayer player)
    {
        this.buyingPlayer = player;
    }

    @Override
    public EntityPlayer getCustomer()
    {
        return this.buyingPlayer;
    }

    @Override
    public void useRecipe(MerchantRecipe recipe)
    {
        recipe.incrementToolUses();
        this.livingSoundTime = -this.getTalkInterval();
        this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        int i = 3 + this.rand.nextInt(4);

        if (recipe.getToolUses() == 1 || this.rand.nextInt(5) == 0)
        {
            this.timeUntilReset = 40;
            this.needsInitilization = true;
            this.isWillingToMate = true;

            if (this.buyingPlayer != null)
            {
                this.lastBuyingPlayer = this.buyingPlayer.getName();
            }
            else
            {
                this.lastBuyingPlayer = null;
            }
            i += 5;
        }

        if (recipe.getItemToBuy().getItem() == Items.EMERALD)
        {
            this.wealth += recipe.getItemToBuy().stackSize;
        }
        if (recipe.getRewardsExp())
        {
            this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY + 0.5D, this.posZ, i));
        }
    }

    @Override
    public void verifySellingItem(ItemStack itemStack)
    {
        if (!this.worldObj.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
        {
            this.livingSoundTime = -this.getTalkInterval();

            if (itemStack != null)
            {
                this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
            }
            else
            {
                this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
            }
        }
    }

    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player)
    {
        if (this.buyingList == null)
        {
            this.populateBuyingList();
        }
        return this.buyingList;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setRecipes(MerchantRecipeList recipeList) {}

    @Override
    public ITextComponent getDisplayName()
    {
        String s = this.getCustomNameTag();

        if (s != null && s.length() > 0)
        {
            TextComponentString chatcomponenttext = new TextComponentString(s);
            chatcomponenttext.getStyle().setHoverEvent(this.getHoverEvent());
            chatcomponenttext.getStyle().setInsertion(this.getUniqueID().toString());
            return chatcomponenttext;
        }
        else
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }

            String name = null;

            switch (this.getProfession())
            {
            case 0:
            case 3:
                name = "farmer";
                break;
            case 1:
            case 4:
                name = "librarian";
                break;
            case 2:
            case 5:
                name = "medic";
                break;
            }

            if (name != null)
            {
                TextComponentTranslation chatcomponenttranslation = new TextComponentTranslation("entity.nibiru_villager." + name);
                chatcomponenttranslation.getStyle().setHoverEvent(this.getHoverEvent());
                chatcomponenttranslation.getStyle().setInsertion(this.getUniqueID().toString());
                return chatcomponenttranslation;
            }
            else
            {
                return super.getDisplayName();
            }
        }
    }

    @Override
    public float getEyeHeight()
    {
        float f = 1.62F;

        if (this.isChild())
        {
            f = (float)(f - 0.81D);
        }
        return f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 12)
        {
            this.spawnParticles(EnumParticleTypes.HEART);
        }
        else if (id == 13)
        {
            this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
        }
        else if (id == 14)
        {
            this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        if (this.worldObj.getBiome(this.getPosition()) == MPBiomes.GREEN_VEIN)
        {
            this.setProfession(3 + this.rand.nextInt(3));
        }
        else
        {
            this.setProfession(this.rand.nextInt(3));
        }
        this.setAdditionalAItasks();
        return super.onInitialSpawn(difficulty, data);
    }

    public void setLookingForHome()
    {
        this.isLookingForHome = true;
    }

    @Override
    public EntityNibiruVillager createChild(EntityAgeable ageable)
    {
        EntityNibiruVillager entityvillager = new EntityNibiruVillager(this.worldObj);
        entityvillager.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null);
        return entityvillager;
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return false;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        if (!this.worldObj.isRemote && !this.isDead)
        {
            EntityEvolvedWitch entitywitch = new EntityEvolvedWitch(this.worldObj);
            entitywitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entitywitch.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(entitywitch)), (IEntityLivingData)null);
            entitywitch.setNoAI(this.isAIDisabled());

            if (this.hasCustomName())
            {
                entitywitch.setCustomNameTag(this.getCustomNameTag());
                entitywitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
            }
            this.worldObj.spawnEntityInWorld(entitywitch);
            this.setDead();
        }
    }

    @Override
    protected void updateEquipmentIfNeeded(EntityItem itemEntity)
    {
        ItemStack itemStack = itemEntity.getEntityItem();

        if (this.canVillagerPickupItem(itemStack))
        {
            ItemStack itemstack1 = this.villagerInventory.addItem(itemStack);

            if (itemstack1 == null)
            {
                itemEntity.setDead();
            }
            else
            {
                itemStack.stackSize = itemstack1.stackSize;
            }
        }
    }

    @Override
    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStack)
    {
        if (super.replaceItemInInventory(inventorySlot, itemStack))
        {
            return true;
        }
        else
        {
            int i = inventorySlot - 300;

            if (i >= 0 && i < this.villagerInventory.getSizeInventory())
            {
                this.villagerInventory.setInventorySlotContents(i, itemStack);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public void setProfession(int professionId)
    {
        this.dataManager.set(PROFESSION, Integer.valueOf(professionId));
    }

    public int getProfession()
    {
        return Math.max(this.dataManager.get(PROFESSION).intValue() % 6, 0);
    }

    public boolean isMating()
    {
        return this.isMating;
    }

    public void setMating(boolean mating)
    {
        this.isMating = mating;
    }

    public void setPlaying(boolean playing)
    {
        this.isPlaying = playing;
    }

    public boolean isPlaying()
    {
        return this.isPlaying;
    }

    public boolean isTrading()
    {
        return this.buyingPlayer != null;
    }

    public boolean getIsWillingToMate(boolean updateFirst)
    {
        if (!this.isWillingToMate && updateFirst && this.func_175553_cp())
        {
            boolean flag = false;

            for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
            {
                ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

                if (itemStack != null)
                {
                    if (itemStack.getItem() == Items.BREAD && itemStack.stackSize >= 3)
                    {
                        flag = true;
                        this.villagerInventory.decrStackSize(i, 3);
                    }
                    else if (itemStack.getItem() == NibiruItems.NIBIRU_FRUITS && itemStack.getItemDamage() == 6 && itemStack.stackSize >= 12)
                    {
                        flag = true;
                        this.villagerInventory.decrStackSize(i, 12);
                    }
                }

                if (flag)
                {
                    this.worldObj.setEntityState(this, (byte)18);
                    this.isWillingToMate = true;
                    break;
                }
            }
        }
        return this.isWillingToMate;
    }

    public void setIsWillingToMate(boolean willingToTrade)
    {
        this.isWillingToMate = willingToTrade;
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(EnumParticleTypes particleType)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(particleType, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 1.0D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
        }
    }

    private void populateBuyingList()
    {
        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
        }

        if (this.getProfession() == 0 || this.getProfession() == 3)
        {
            for (ITradeList tradeList : this.farmerTradeList)
            {
                tradeList.modifyMerchantRecipeList(this.buyingList, this.rand);
            }
        }
        if (this.getProfession() == 1 || this.getProfession() == 4)
        {
            for (ITradeList tradeList : this.librarianTradeList)
            {
                tradeList.modifyMerchantRecipeList(this.buyingList, this.rand);
            }
        }
        if (this.getProfession() == 2 || this.getProfession() == 5)
        {
            for (ITradeList tradeList : this.medicTradeList)
            {
                tradeList.modifyMerchantRecipeList(this.buyingList, this.rand);
            }
        }
    }

    public InventoryBasic getVillagerInventory()
    {
        return this.villagerInventory;
    }

    private boolean canVillagerPickupItem(ItemStack itemStack)
    {
        return itemStack.getItem() == NibiruItems.NIBIRU_FRUITS && itemStack.getItemDamage() == 6 || itemStack.getItem() == NibiruItems.INFECTED_WHEAT || itemStack.getItem() == NibiruItems.INFECTED_WHEAT_SEEDS;
    }

    private boolean func_175553_cp()
    {
        return this.hasEnoughItems(1);
    }

    public boolean canAbondonItems()
    {
        return this.hasEnoughItems(2);
    }

    public boolean func_175557_cr()
    {
        boolean flag = this.getProfession() == 0 || this.getProfession() == 3;
        return flag ? !this.hasEnoughItems(5) : !this.hasEnoughItems(1);
    }

    private boolean hasEnoughItems(int multiplier)
    {
        boolean flag = this.getProfession() == 0 || this.getProfession() == 3;

        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

            if (itemStack != null)
            {
                if (itemStack.getItem() == Items.BREAD && itemStack.stackSize >= 3 * multiplier || itemStack.getItem() == NibiruItems.NIBIRU_FRUITS && itemStack.getItemDamage() == 6 && itemStack.stackSize >= 12 * multiplier)
                {
                    return true;
                }
                if (flag && itemStack.getItem() == NibiruItems.INFECTED_WHEAT && itemStack.stackSize >= 9 * multiplier)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFarmItemInInventory()
    {
        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

            if (itemStack != null && (itemStack.getItem() == NibiruItems.INFECTED_WHEAT || itemStack.getItem() == NibiruItems.NIBIRU_FRUITS && itemStack.getItemDamage() == 6))
            {
                return true;
            }
        }
        return false;
    }

    private static class EmeraldForItems implements ITradeList
    {
        private ItemStack stackSell;
        private PriceInfo price;

        public EmeraldForItems(ItemStack stackSell, PriceInfo price)
        {
            this.stackSell = stackSell;
            this.price = price;
        }

        @Override
        public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random rand)
        {
            int i = 1;

            if (this.price != null)
            {
                i = this.price.getPrice(rand);
            }
            recipeList.add(new MerchantRecipe(new ItemStack(this.stackSell.getItem(), i, this.stackSell.getItemDamage()), Items.EMERALD));
        }
    }

    private interface ITradeList
    {
        void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random rand);
    }

    private static class ListEnchantedBookForEmeralds implements ITradeList
    {
        @Override
        public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random rand)
        {
            Enchantment enchantment = Enchantment.REGISTRY.getRandomObject(rand);
            int i = MathHelper.getRandomIntegerInRange(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemstack = Items.ENCHANTED_BOOK.getEnchantedItemStack(new EnchantmentData(enchantment, i));
            int j = 2 + rand.nextInt(5 + i * 10) + 3 * i;

            if (j > 64)
            {
                j = 64;
            }
            recipeList.add(new MerchantRecipe(new ItemStack(Items.BOOK), new ItemStack(Items.EMERALD, j), itemstack));
        }
    }

    private static class ListItemForEmeralds implements ITradeList
    {
        private ItemStack buyStack;
        private PriceInfo price;
        private boolean isCapsule;

        public ListItemForEmeralds(ItemStack buyStack, PriceInfo price)
        {
            this.buyStack = buyStack;
            this.price = price;
        }

        public ListItemForEmeralds(ItemStack buyStack, PriceInfo price, boolean isCapsule)
        {
            this.buyStack = buyStack;
            this.price = price;
            this.isCapsule = isCapsule;
        }

        @Override
        public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random rand)
        {
            int i = 1;

            if (this.price != null)
            {
                i = this.price.getPrice(rand);
            }

            ItemStack buyItem;
            ItemStack sellItem;

            if (this.isCapsule)
            {
                ItemStack capsule = this.buyStack;

                if (i < 0)
                {
                    buyItem = new ItemStack(Items.EMERALD, 1, 0);
                    capsule.stackSize = 1 + rand.nextInt(4);
                    sellItem = capsule;
                }
                else
                {
                    buyItem = new ItemStack(Items.EMERALD, i, 0);
                    capsule.stackSize = 1 + rand.nextInt(4);
                    sellItem = capsule;
                }
            }
            else
            {
                if (i < 0)
                {
                    buyItem = new ItemStack(Items.EMERALD, 1, 0);
                    sellItem = new ItemStack(this.buyStack.getItem(), -i, this.buyStack.getMetadata());
                }
                else
                {
                    buyItem = new ItemStack(Items.EMERALD, i, 0);
                    sellItem = new ItemStack(this.buyStack.getItem(), 1, this.buyStack.getMetadata());
                }
            }
            recipeList.add(new MerchantRecipe(buyItem, sellItem));
        }
    }

    private static class PriceInfo extends Tuple<Integer, Integer>
    {
        public PriceInfo(int min, int max)
        {
            super(Integer.valueOf(min), Integer.valueOf(max));
        }

        public int getPrice(Random rand)
        {
            return this.getFirst().intValue() >= this.getSecond().intValue() ? this.getFirst().intValue() : this.getFirst().intValue() + rand.nextInt(this.getSecond().intValue() - this.getFirst().intValue() + 1);
        }
    }
}
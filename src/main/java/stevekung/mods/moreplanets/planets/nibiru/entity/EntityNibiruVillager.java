package stevekung.mods.moreplanets.planets.nibiru.entity;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedWitch;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.planets.nibiru.entity.ai.*;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.BiomeGreenVeinFields;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;
import stevekung.mods.moreplanets.utils.entity.ai.PathNavigateGroundMP;

public class EntityNibiruVillager extends EntityAgeable implements INpc, IMerchant, IEntityBreathable, ISpaceMob
{
    private static final DataParameter<Integer> PROFESSION = EntityDataManager.createKey(EntityNibiruVillager.class, DataSerializers.VARINT);
    private int randomTickDivider;
    private boolean isMating;
    private boolean isPlaying;
    private Village village;
    @Nullable
    private EntityPlayer buyingPlayer;
    @Nullable
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    private boolean needsInitilization;
    private boolean isWillingToMate;
    private int wealth;
    private UUID lastBuyingPlayer;
    private int careerId;
    private int careerLevel;
    private boolean isLookingForHome;
    private boolean areAdditionalTasksSet;
    private final InventoryBasic villagerInventory;

    private static final EntityVillager.ITradeList[][][][] DEFAULT_TRADE_LIST_MAP =
        {
                // profession infected farmer
                {
                    // farmer
                    {
                        {
                            new EntityVillager.EmeraldForItems(MPItems.INFECTED_WHEAT, new EntityVillager.PriceInfo(18, 22)),
                            new EntityVillager.ListItemForEmeralds(Items.BREAD, new EntityVillager.PriceInfo(-4, -2))
                        },
                        {
                            new EntityVillager.EmeraldForItems(Item.getItemFromBlock(MPBlocks.INFECTED_MELON), new EntityVillager.PriceInfo(7, 12)),
                            new EntityVillager.ListItemForEmeralds(MPItems.INFECTED_APPLE, new EntityVillager.PriceInfo(-7, -5))
                        },
                        {
                            new EntityVillager.ListItemForEmeralds(MPItems.INFECTED_GOLDEN_APPLE, new EntityVillager.PriceInfo(-10, -6)),
                            new EntityVillager.ListItemForEmeralds(MPItems.ENCHANTED_INFECTED_GOLDEN_APPLE, new EntityVillager.PriceInfo(16, 24))
                        }
                    }
                },
                // profession infected librarian
                {
                    // librarian
                    {
                        {
                            new EntityVillager.EmeraldForItems(Items.PAPER, new EntityVillager.PriceInfo(24, 36)), new EntityVillager.ListEnchantedBookForEmeralds()
                        },
                        {
                            new EntityVillager.EmeraldForItems(Items.BOOK, new EntityVillager.PriceInfo(8, 10)),
                            new EntityVillager.ListItemForEmeralds(Items.COMPASS, new EntityVillager.PriceInfo(10, 12)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(MPBlocks.INFECTED_OAK_BOOKSHELF), new EntityVillager.PriceInfo(3, 4)),
                        },
                        {
                            new EntityVillager.EmeraldForItems(Items.WRITTEN_BOOK, new EntityVillager.PriceInfo(2, 2)),
                            new EntityVillager.ListItemForEmeralds(Items.CLOCK, new EntityVillager.PriceInfo(10, 12)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.GLASS), new EntityVillager.PriceInfo(-5, -3))
                        },
                        {
                            new EntityVillager.ListEnchantedBookForEmeralds()
                        },
                        {
                            new EntityVillager.ListEnchantedBookForEmeralds()
                        },
                        {
                            new EntityVillager.ListEnchantedBookForEmeralds()
                        }
                    }
                },
                // profession infected medic
                {
                    {
                        {
                            new EntityVillager.EmeraldForItems(Items.IRON_INGOT, new EntityVillager.PriceInfo(12, 24)),
                            new EmeraldForItemStack(new ItemStack(GCItems.basicItem, 1, 4), new EntityVillager.PriceInfo(8, 10)),
                            new EntityVillager.EmeraldForItems(MPItems.EMPTY_CAPSULE, new EntityVillager.PriceInfo(4, 8))
                        },
                        {
                            new EntityVillager.ListItemForEmeralds(MPItems.CHEESE_SPORE_BERRY, new EntityVillager.PriceInfo(-5, -2)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(MPBlocks.CHEESE_SPORE_FLOWER), new EntityVillager.PriceInfo(-4, -2)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(MPBlocks.PURE_HERB), new EntityVillager.PriceInfo(-3, -1)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(MPBlocks.TERRAPUFF_HERB), new EntityVillager.PriceInfo(3, 6))
                        },
                        {
                            new EntityVillager.ListItemForEmeralds(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE, new EntityVillager.PriceInfo(-5, -3)),
                        }
                    }
                },
                // profession green vein farmer
                {
                    // farmer
                    {
                        {
                            new EntityVillager.EmeraldForItems(MPItems.TERRABERRY, new EntityVillager.PriceInfo(15, 19)),
                        },
                        {
                            new EntityVillager.ListItemForEmeralds(MPItems.ALIEN_BERRY, new EntityVillager.PriceInfo(-10, -6)),
                            new EntityVillager.ListItemForEmeralds(MPItems.GOLDEN_ALIEN_BERRY, new EntityVillager.PriceInfo(5, 10))
                        }
                    }
                },
                // profession green vein librarian
                {
                    // librarian
                    {
                        {
                            new EntityVillager.EmeraldForItems(Items.PAPER, new EntityVillager.PriceInfo(24, 36)), new EntityVillager.ListEnchantedBookForEmeralds()
                        },
                        {
                            new EntityVillager.EmeraldForItems(Items.BOOK, new EntityVillager.PriceInfo(8, 10)),
                            new EntityVillager.ListItemForEmeralds(Items.COMPASS, new EntityVillager.PriceInfo(10, 12)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(MPBlocks.ALIEN_BERRY_OAK_BOOKSHELF), new EntityVillager.PriceInfo(3, 4))
                        },
                        {
                            new EntityVillager.EmeraldForItems(Items.WRITTEN_BOOK, new EntityVillager.PriceInfo(2, 2)),
                            new EntityVillager.ListItemForEmeralds(Items.CLOCK, new EntityVillager.PriceInfo(10, 12)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.GLASS), new EntityVillager.PriceInfo(-5, -3))
                        },
                        {
                            new EntityVillager.ListEnchantedBookForEmeralds()
                        },
                        {
                            new EntityVillager.ListEnchantedBookForEmeralds()
                        },
                        {
                            new EntityVillager.ListEnchantedBookForEmeralds()
                        }
                    }
                },
                // profession infected medic
                {
                    {
                        {
                            new EntityVillager.EmeraldForItems(Items.IRON_INGOT, new EntityVillager.PriceInfo(12, 24)),
                            new EmeraldForItemStack(new ItemStack(GCItems.basicItem, 1, 4), new EntityVillager.PriceInfo(8, 10)),
                            new EntityVillager.EmeraldForItems(MPItems.EMPTY_CAPSULE, new EntityVillager.PriceInfo(4, 8))
                        },
                        {
                            new EntityVillager.ListItemForEmeralds(MPItems.INFERUMITE_CRYSTAL, new EntityVillager.PriceInfo(-5, -2)),
                            new EntityVillager.ListItemForEmeralds(MPItems.INFECTED_CRYSTALLIZED_SHARD, new EntityVillager.PriceInfo(-4, -2)),
                            new EntityVillager.ListItemForEmeralds(new ItemStack(MarsItems.marsItemBasic, 1, 0), new EntityVillager.PriceInfo(-3, -1)),
                            new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(MPBlocks.TERRAPUFF_HERB), new EntityVillager.PriceInfo(3, 6))
                        },
                        {
                            new EntityVillager.ListItemForEmeralds(MPItems.DARK_ENERGY_PROTECTION_CAPSULE, new EntityVillager.PriceInfo(-3, -1))
                        }
                    }
                },
                {
                    new EntityVillager.ITradeList[0][]
                }
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
        this.setSize(0.6F, 1.95F);
        ((PathNavigateGroundMP)this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityEvolvedZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityInfectedZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAINibiruVillagerTradePlayer(this));
        this.tasks.addTask(1, new EntityAINibiruVillagerLookAtTradePlayer(this));
        this.tasks.addTask(2, new EntityAINibiruVillagerMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(6, new EntityAINibiruVillagerMate(this));
        this.tasks.addTask(7, new EntityAINibiruVillagerFollowGolem(this));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAINibiruVillagerInteract(this));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    protected PathNavigate createNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
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
            BlockPos pos = new BlockPos(this);
            this.world.getVillageCollection().addToVillagerPositionList(pos);
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.village = this.world.getVillageCollection().getNearestVillage(pos, 32);

            if (this.village == null)
            {
                this.detachHome();
            }
            else
            {
                BlockPos pos1 = this.village.getCenter();
                this.setHomePosAndDistance(pos1, this.village.getVillageRadius());

                if (this.isLookingForHome)
                {
                    this.isLookingForHome = false;
                    this.village.setDefaultPlayerReputation(5);
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
                    for (MerchantRecipe recipe : this.buyingList)
                    {
                        if (recipe.isRecipeDisabled())
                        {
                            recipe.increaseMaxTradeUses(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                        }
                    }

                    this.populateBuyingList();
                    this.needsInitilization = false;

                    if (this.village != null && this.lastBuyingPlayer != null)
                    {
                        this.world.setEntityState(this, (byte)14);
                        this.village.modifyPlayerReputation(this.lastBuyingPlayer, 1);
                    }
                }
                this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
            }
        }
        super.updateAITasks();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        boolean flag = itemStack.getItem() == Items.NAME_TAG;

        if (flag)
        {
            itemStack.interactWithEntity(player, this, hand);
            return true;
        }
        else if (!this.holdingSpawnEggOfClass(itemStack, this.getClass()) && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking())
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }
            if (hand == EnumHand.MAIN_HAND)
            {
                player.addStat(StatList.TALKED_TO_VILLAGER);
            }

            if (!this.world.isRemote && !this.buyingList.isEmpty())
            {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }
            else if (this.buyingList.isEmpty())
            {
                return super.processInteract(player, hand);
            }
            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(PROFESSION, 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("Profession", this.getProfession());
        nbt.setInteger("Riches", this.wealth);
        nbt.setInteger("Career", this.careerId);
        nbt.setInteger("CareerLevel", this.careerLevel);
        nbt.setBoolean("Willing", this.isWillingToMate);

        if (this.buyingList != null)
        {
            nbt.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

            if (!itemStack.isEmpty())
            {
                list.appendTag(itemStack.writeToNBT(new NBTTagCompound()));
            }
        }
        nbt.setTag("Inventory", list);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setProfession(nbt.getInteger("Profession"));
        this.wealth = nbt.getInteger("Riches");
        this.careerId = nbt.getInteger("Career");
        this.careerLevel = nbt.getInteger("CareerLevel");
        this.isWillingToMate = nbt.getBoolean("Willing");

        if (nbt.hasKey("Offers", 10))
        {
            NBTTagCompound compound = nbt.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(compound);
        }

        NBTTagList list = nbt.getTagList("Inventory", 10);

        for (int i = 0; i < list.tagCount(); ++i)
        {
            ItemStack itemStack = new ItemStack(list.getCompoundTagAt(i));

            if (!itemStack.isEmpty())
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
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_VILLAGER;
    }

    @Override
    public void setRevengeTarget(@Nullable EntityLivingBase entity)
    {
        super.setRevengeTarget(entity);

        if (this.village != null && entity != null)
        {
            this.village.addOrRenewAgressor(entity);

            if (entity instanceof EntityPlayer)
            {
                int i = -1;

                if (this.isChild())
                {
                    i = -3;
                }

                this.village.modifyPlayerReputation(entity.getUniqueID(), i);

                if (this.isEntityAlive())
                {
                    this.world.setEntityState(this, (byte)13);
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        if (this.village != null)
        {
            Entity entity = cause.getTrueSource();

            if (entity != null)
            {
                if (entity instanceof EntityPlayer)
                {
                    this.village.modifyPlayerReputation(entity.getUniqueID(), -2);
                }
                else if (entity instanceof IMob)
                {
                    this.village.endMatingSeason();
                }
            }
            else
            {
                EntityPlayer player = this.world.getClosestPlayerToEntity(this, 16.0D);

                if (player != null)
                {
                    this.village.endMatingSeason();
                }
            }
        }
        super.onDeath(cause);
    }

    @Override
    public void setCustomer(@Nullable EntityPlayer player)
    {
        this.buyingPlayer = player;
    }

    @Override
    @Nullable
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
                this.lastBuyingPlayer = this.buyingPlayer.getUniqueID();
            }
            else
            {
                this.lastBuyingPlayer = null;
            }
            i += 5;
        }

        if (recipe.getItemToBuy().getItem() == Items.EMERALD)
        {
            this.wealth += recipe.getItemToBuy().getCount();
        }
        if (recipe.getRewardsExp())
        {
            this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY + 0.5D, this.posZ, i));
        }
    }

    @Override
    public void verifySellingItem(ItemStack itemStack)
    {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
        {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(itemStack.isEmpty() ? SoundEvents.ENTITY_VILLAGER_NO : SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    @Nullable
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
    public void setRecipes(@Nullable MerchantRecipeList recipeList) {}

    @Override
    public World getWorld()
    {
        return this.world;
    }

    @Override
    public BlockPos getPos()
    {
        return new BlockPos(this);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        Team team = this.getTeam();
        String customName = this.getCustomNameTag();

        if (customName != null && !customName.isEmpty())
        {
            TextComponentString component = new TextComponentString(ScorePlayerTeam.formatPlayerName(team, customName));
            component.getStyle().setHoverEvent(this.getHoverEvent());
            component.getStyle().setInsertion(this.getCachedUniqueIdString());
            return component;
        }
        else
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }

            String name = null;
            String type = this.getProfession() > 2 ? "green_vein" : "infected";

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
                ITextComponent component = new TextComponentTranslation("entity.nibiru_villager." + type + "_" + name);
                component.getStyle().setHoverEvent(this.getHoverEvent());
                component.getStyle().setInsertion(this.getCachedUniqueIdString());

                if (team != null)
                {
                    component.getStyle().setColor(team.getColor());
                }
                return component;
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
        return this.isChild() ? 0.81F : 1.62F;
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
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data)
    {
        data = super.onInitialSpawn(difficulty, data);
        this.setProfession(this.world.getBiome(this.getPosition()) instanceof BiomeGreenVeinFields ? 3 + this.rand.nextInt(3) : this.rand.nextInt(3));
        this.setAdditionalAItasks();
        this.populateBuyingList();
        return data;
    }

    @Override
    public EntityNibiruVillager createChild(EntityAgeable ageable)
    {
        EntityNibiruVillager entity = new EntityNibiruVillager(this.world);
        entity.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entity)), null);
        entity.setProfession(this.world.getBiome(this.getPosition()) instanceof BiomeGreenVeinFields ? 3 + this.rand.nextInt(3) : this.rand.nextInt(3));
        return entity;
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        if (!this.world.isRemote && !this.isDead)
        {
            EntityEvolvedWitch entitywitch = new EntityEvolvedWitch(this.world);
            entitywitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entitywitch.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entitywitch)), null);
            entitywitch.setNoAI(this.isAIDisabled());

            if (this.hasCustomName())
            {
                entitywitch.setCustomNameTag(this.getCustomNameTag());
                entitywitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
            }
            this.world.spawnEntity(entitywitch);
            this.setDead();
        }
    }

    public InventoryBasic getVillagerInventory()
    {
        return this.villagerInventory;
    }

    @Override
    protected void updateEquipmentIfNeeded(EntityItem itemEntity)
    {
        ItemStack itemStack = itemEntity.getItem();
        Item item = itemStack.getItem();

        if (this.canVillagerPickupItem(item))
        {
            ItemStack itemStack1 = this.villagerInventory.addItem(itemStack);

            if (itemStack1.isEmpty())
            {
                itemEntity.setDead();
            }
            else
            {
                itemStack.setCount(itemStack1.getCount());
            }
        }
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

    public boolean isFarmItemInInventory()
    {
        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

            if (!itemStack.isEmpty() && (itemStack.getItem() == MPItems.INFECTED_WHEAT_SEEDS || itemStack.getItem() == MPItems.TERRABERRY))
            {
                return true;
            }
        }
        return false;
    }

    public void setProfession(int professionId)
    {
        this.dataManager.set(PROFESSION, professionId);
    }

    public int getProfession()
    {
        return Math.max(this.dataManager.get(PROFESSION).intValue(), 0);
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
        if (!this.isWillingToMate && updateFirst && this.hasEnoughFoodToBreed())
        {
            boolean flag = false;

            for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
            {
                ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

                if (!itemStack.isEmpty())
                {
                    if (itemStack.getItem() == Items.BREAD && itemStack.getCount() >= 3)
                    {
                        flag = true;
                        this.villagerInventory.decrStackSize(i, 3);
                    }
                    else if (itemStack.getItem() == MPItems.TERRABERRY && itemStack.getCount() >= 12)
                    {
                        flag = true;
                        this.villagerInventory.decrStackSize(i, 12);
                    }
                }
                if (flag)
                {
                    this.world.setEntityState(this, (byte)18);
                    this.isWillingToMate = true;
                    break;
                }
            }
        }
        return this.isWillingToMate;
    }

    public void setIsWillingToMate(boolean isWillingToMate)
    {
        this.isWillingToMate = isWillingToMate;
    }

    public boolean canAbondonItems()
    {
        return this.hasEnoughItems(2);
    }

    public boolean wantsMoreFood()
    {
        boolean flag = this.getProfession() == 0 || this.getProfession() == 3;

        if (flag)
        {
            return !this.hasEnoughItems(5);
        }
        else
        {
            return !this.hasEnoughItems(1);
        }
    }

    private boolean hasEnoughFoodToBreed()
    {
        return this.hasEnoughItems(1);
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

    private boolean hasEnoughItems(int multiplier)
    {
        boolean flag = this.getProfession() == 0 || this.getProfession() == 3;

        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = this.villagerInventory.getStackInSlot(i);

            if (!itemStack.isEmpty())
            {
                if (itemStack.getItem() == Items.BREAD && itemStack.getCount() >= 3 * multiplier || itemStack.getItem() == MPItems.TERRABERRY && itemStack.getCount() >= 12 * multiplier)
                {
                    return true;
                }
                if (flag && itemStack.getItem() == MPItems.INFECTED_WHEAT && itemStack.getCount() >= 9 * multiplier)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void populateBuyingList()
    {
        EntityVillager.ITradeList[][][] tradelist = EntityNibiruVillager.DEFAULT_TRADE_LIST_MAP[this.getProfession()];

        if (this.careerId != 0 && this.careerLevel != 0)
        {
            ++this.careerLevel;
        }
        else
        {
            this.careerId = this.rand.nextInt(tradelist.length) + 1;
            this.careerLevel = 1;
        }

        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
        }

        int careerId = this.careerId - 1;
        int careerLevel = this.careerLevel - 1;

        if (careerId >= 0 && careerId < tradelist.length)
        {
            EntityVillager.ITradeList[][] tradelist1 = tradelist[careerId];

            if (careerLevel >= 0 && careerLevel < tradelist1.length)
            {
                EntityVillager.ITradeList[] tradelist2 = tradelist1[careerLevel];

                for (EntityVillager.ITradeList trades : tradelist2)
                {
                    trades.addMerchantRecipe(this, this.buyingList, this.rand);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(EnumParticleTypes particleType)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(particleType, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 1.0D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
        }
    }

    private boolean canVillagerPickupItem(Item item)
    {
        return item == Items.BREAD || item == MPItems.TERRABERRY || item == MPItems.INFECTED_WHEAT || item == MPItems.INFECTED_WHEAT_SEEDS;
    }

    public static class EmeraldForItemStack implements EntityVillager.ITradeList
    {
        public ItemStack buyingItem;
        public EntityVillager.PriceInfo price;

        public EmeraldForItemStack(ItemStack itemStack, EntityVillager.PriceInfo price)
        {
            this.buyingItem = itemStack;
            this.price = price;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random rand)
        {
            int i = 1;

            if (this.price != null)
            {
                i = this.price.getPrice(rand);
            }
            this.buyingItem.setCount(i);
            recipeList.add(new MerchantRecipe(this.buyingItem, Items.EMERALD));
        }
    }
}
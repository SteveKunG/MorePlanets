package stevekung.mods.moreplanets.planets.diona.entity;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;
import stevekung.mods.moreplanets.utils.entity.EntitySlimeBaseMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class EntityInfectedCrystallizedSlimeBoss extends EntitySlimeBaseMP implements IMorePlanetsBoss
{
    private TileEntityDungeonSpawner<?> spawner;
    public int deathTicks = 0;
    private int entitiesWithin;
    private int entitiesWithinLast;
    private static final DataParameter<Boolean> BARRIER = EntityDataManager.createKey(EntityInfectedCrystallizedSlimeBoss.class, DataSerializers.BOOLEAN);
    public EntityInfectedCrystallizedTentacle tentacle;
    private final BossInfoServer bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
    private boolean tentacleSpawning = true;

    public EntityInfectedCrystallizedSlimeBoss(World world)
    {
        super(world);
        MorePlanetsMod.PROXY.addBoss(this);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(BARRIER, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);

        if (this.dataManager.get(BARRIER).booleanValue())
        {
            nbt.setBoolean("Barrier", true);
        }
        nbt.setBoolean("TentacleSpawning", this.tentacleSpawning);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.dataManager.set(BARRIER, nbt.getBoolean("Barrier"));
        this.tentacleSpawning = nbt.getBoolean("TentacleSpawning");
    }

    @Override
    public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {}

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(this.getDetectRange());
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data)
    {
        if (this.tentacleSpawning)
        {
            EntityInfectedCrystallizedTentacle tentacle1 = new EntityInfectedCrystallizedTentacle(this.world);
            tentacle1.setLocationAndAngles(this.posX + 5.0F, this.posY + 2.5F, this.posZ + 5.0F, 0.0F, 0.0F);
            this.world.spawnEntity(tentacle1);

            EntityInfectedCrystallizedTentacle tentacle2 = new EntityInfectedCrystallizedTentacle(this.world);
            tentacle2.setLocationAndAngles(this.posX - 5.0F, this.posY + 2.5F, this.posZ - 5.0F, 0.0F, 0.0F);
            this.world.spawnEntity(tentacle2);

            EntityInfectedCrystallizedTentacle tentacle3 = new EntityInfectedCrystallizedTentacle(this.world);
            tentacle3.setLocationAndAngles(this.posX + 5.0F, this.posY + 2.5F, this.posZ - 5.0F, 0.0F, 0.0F);
            this.world.spawnEntity(tentacle3);

            EntityInfectedCrystallizedTentacle tentacle4 = new EntityInfectedCrystallizedTentacle(this.world);
            tentacle4.setLocationAndAngles(this.posX - 5.0F, this.posY + 2.5F, this.posZ + 5.0F, 0.0F, 0.0F);
            this.world.spawnEntity(tentacle4);
            this.tentacleSpawning = false;
        }
        this.setSlimeSize(5, true);
        return data;
    }

    @Override
    public float getSizeBased()
    {
        return 1.01000005F;
    }

    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 1.5F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 1.5F;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + f, this.posY + 2.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
        }

        int i;
        int j;

        if (!this.world.isRemote)
        {
            if (this.deathTicks >= 180 && this.deathTicks % 5 == 0)
            {
                GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_EXPLODE, GCCoreUtil.getDimensionID(this.world), new Object[] { }), new TargetPoint(GCCoreUtil.getDimensionID(this.world), this.posX, this.posY, this.posZ, 40.0D));
            }
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                i = 120;

                while (i > 0)
                {
                    j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }

            if (this.deathTicks == 40)
            {
                GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_BOSS_DEATH, GCCoreUtil.getDimensionID(this.world), new Object[] { this.getSoundPitch() - 0.1F }), new TargetPoint(GCCoreUtil.getDimensionID(this.world), this.posX, this.posY, this.posZ, 40.0D));
            }
        }

        if (this.deathTicks == 200 && !this.world.isRemote)
        {
            i = 120;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
            }

            TileEntityTreasureChestMP chest = null;

            if (this.spawner != null && this.spawner.getChestPos() != null)
            {
                TileEntity chestTest = this.world.getTileEntity(this.spawner.getChestPos());

                if (chestTest != null && chestTest instanceof TileEntityTreasureChestMP)
                {
                    chest = (TileEntityTreasureChestMP) chestTest;
                }
            }

            if (chest == null)
            {
                chest = TileEntityTreasureChestMP.findClosest(this, MPItems.DIONA_DUNGEON_KEY);
            }
            else
            {
                double dist = this.getDistanceSq(chest.getPos().getX() + 0.5, chest.getPos().getY() + 0.5, chest.getPos().getZ() + 0.5);

                if (dist < 1000 * 1000)
                {
                    if (!chest.locked)
                    {
                        chest.locked = true;
                    }
                    int slot = this.rand.nextInt(chest.getSizeInventory());
                    chest.setLootTable(MPLootTables.COMMON_SPACE_DUNGEON, this.rand.nextLong());
                    chest.setInventorySlotContents(slot, MPLootTables.getTieredKey(this.rand, 4));
                }
            }

            this.entityDropItem(new ItemStack(MPItems.DIONA_DUNGEON_KEY, 1, 0), 0.5F);
            this.setDead();

            if (this.spawner != null)
            {
                this.spawner.isBossDefeated = true;
                this.spawner.boss = null;
                this.spawner.spawned = false;
            }
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        MorePlanetsMod.PROXY.addBoss(this);
        List<EntityInfectedCrystallizedTentacle> list = this.world.getEntitiesWithinAABB(EntityInfectedCrystallizedTentacle.class, this.getEntityBoundingBox().grow(32.0D));
        this.updateTentacle();

        if (list.size() > 0)
        {
            this.dataManager.set(BARRIER, true);
        }
        else
        {
            this.dataManager.set(BARRIER, false);
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MPLootTables.INFECTED_CRYSTALLIZED_SLIME_BOSS;
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.spawner != null)
        {
            List<EntityPlayer> playersWithin = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.spawner.getRangeBounds());
            this.entitiesWithin = playersWithin.size();

            if (this.entitiesWithin == 0 && this.entitiesWithinLast != 0)
            {
                List<EntityPlayer> playerWithin = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.spawner.getRangeBoundsPlus11());

                for (EntityPlayer player : playerWithin)
                {
                    player.sendMessage(JsonUtils.create(LangUtils.translate("gui.skeleton_boss.message")).setStyle(JsonUtils.red()));
                }
                this.setDead();
                return;
            }
            this.entitiesWithinLast = this.entitiesWithin;
        }
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        super.onLivingUpdate();
    }

    @Override
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (this.spawner != null)
        {
            this.spawner.isBossDefeated = false;
            this.spawner.boss = null;
            this.spawner.spawned = false;
        }
        if (!this.world.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 8 + this.rand.nextInt(8);

            for (int k = 0; k < j; ++k)
            {
                float f = (k % 2 - 0.5F) * i / 4.0F;
                float f1 = (k / 2 - 0.5F) * i / 4.0F;
                EntityInfectedCrystallizedSlimeMinion entityslime = new EntityInfectedCrystallizedSlimeMinion(this.world);

                if (this.hasCustomName())
                {
                    entityslime.setCustomNameTag(this.getCustomNameTag());
                }
                if (this.isNoDespawnRequired())
                {
                    entityslime.enablePersistence();
                }
                entityslime.setSlimeSize(i / 2, true);
                entityslime.setLocationAndAngles(this.posX + f, this.posY + 0.5D, this.posZ + f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.world.spawnEntity(entityslime);
            }
        }
        MorePlanetsMod.PROXY.removeBoss(this);
        this.isDead = true;
    }

    @Override
    public void onKillCommand()
    {
        this.setHealth(0.0F);
    }

    @Override
    protected void dealDamage(EntityLivingBase entity)
    {
        if (this.canEntityBeSeen(entity) && this.getDistanceSq(entity) < this.getDetectRange() && entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
        {
            this.applyEnchantments(this, entity);
            entity.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZED, 200, 1));
        }
    }

    @Override
    protected int getAttackStrength()
    {
        return 12;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (this.isEntityInvulnerable(damageSource))
        {
            return false;
        }
        else if (this.getBarrier() && !damageSource.isCreativePlayer())
        {
            return false;
        }
        else if (super.attackEntityFrom(damageSource, damage))
        {
            Entity entity = damageSource.getTrueSource();

            if (this.getPassengers().contains(entity) && this.getRidingEntity() != entity)
            {
                if (entity != this)
                {
                    this.setRevengeTarget((EntityLivingBase) entity);
                }
                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public void onBossSpawned(TileEntityDungeonSpawner spawner)
    {
        this.spawner = spawner;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {}

    @Override
    protected double getDetectRange()
    {
        return 512.0D;
    }

    @Override
    protected int getJumpDelay()
    {
        return this.rand.nextInt(2) + 3;
    }

    @Override
    protected EntitySlimeBaseMP createInstance()
    {
        return new EntityInfectedCrystallizedSlimeBoss(this.world);
    }

    @Override
    protected void createParticles()
    {
        int i = this.getSlimeSize();

        for (int j = 0; j < i * 50; ++j)
        {
            float f = this.rand.nextFloat() * (float)Math.PI * 2.5F;
            float f1 = this.rand.nextFloat() * 0.65F + 0.65F;
            float f2 = MathHelper.sin(f) * i * 0.65F * f1;
            float f3 = MathHelper.cos(f) * i * 0.65F * f1;
            double d0 = this.posX + f2;
            double d1 = this.posZ + f3;
            MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_BREAKING, d0, this.getEntityBoundingBox().minY, d1, new Object[] { MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        }
    }

    @Override
    protected void overrideHealth()
    {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0F * ConfigManagerCore.dungeonBossHealthMod);
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        this.bossInfo.removePlayer(player);
    }

    @Override
    public boolean isNonBoss()
    {
        return false;
    }

    @Override
    public UUID getBossUUID()
    {
        return this.bossInfo.getUniqueId();
    }

    @Override
    public String getBossName()
    {
        return this.getName();
    }

    @Override
    public String getBossType()
    {
        return "Zelius Boss";
    }

    @Override
    public int getBossTextColor()
    {
        return ColorUtils.rgbToDecimal(157, 147, 183);
    }

    public boolean getBarrier()
    {
        return this.dataManager.get(BARRIER);
    }

    private void updateTentacle()
    {
        if (this.tentacle != null)
        {
            if (this.tentacle.isDead)
            {
                this.tentacle = null;
            }
        }

        List<EntityInfectedCrystallizedTentacle> list = this.world.getEntitiesWithinAABB(EntityInfectedCrystallizedTentacle.class, this.getEntityBoundingBox().grow(32.0D));
        EntityInfectedCrystallizedTentacle tentacle = null;
        double distance1 = Double.MAX_VALUE;
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            EntityInfectedCrystallizedTentacle tentacle1 = (EntityInfectedCrystallizedTentacle)iterator.next();
            double distance = tentacle1.getDistanceSq(this);

            if (distance < distance1)
            {
                distance1 = distance;
                tentacle = tentacle1;
            }
            this.tentacle = tentacle;
        }
    }
}
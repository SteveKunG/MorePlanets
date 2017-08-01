package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import java.util.List;
import java.util.UUID;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityVeinBall;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.weather.EntityNibiruLightningBolt;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.IMorePlanetsBoss;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class EntityVeinFloater extends EntityMob implements IMorePlanetsBoss, IEntityBreathable, ISpaceMob, IEntityMultiPart
{
    private TileEntityDungeonSpawner spawner;
    public int deathTicks = 0;
    public int entitiesWithin;
    public int entitiesWithinLast;
    private static final DataParameter<Boolean> VINE_PULL = EntityDataManager.createKey(EntityVeinFloater.class, DataSerializers.BOOLEAN);
    public EntityDragonPart[] partArray;
    public EntityDragonPart partHead;
    private BossInfoServer bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
    private boolean playMusic;

    public EntityVeinFloater(World world)
    {
        super(world);
        this.partArray = new EntityDragonPart[] { this.partHead = new EntityDragonPart(this, "head", 6.0F, 4.0F) };
        this.isImmuneToFire = true;
        this.setSize(6.0F, 16.0F);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VINE_PULL, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);

        if (this.dataManager.get(VINE_PULL).booleanValue())
        {
            nbt.setBoolean("VinePull", true);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.dataManager.set(VINE_PULL, nbt.getBoolean("VinePull"));
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        MorePlanetsCore.PROXY.addBoss(this);
        this.motionY *= 0.5D;

        if (this.getHealth() <= 0.0F)
        {
            int i = (300 + this.rand.nextInt(600)) * 20;
            WorldInfo worldinfo = this.world.getWorldInfo();
            worldinfo.setCleanWeatherTime(0);
            worldinfo.setRainTime(i);
            worldinfo.setThunderTime(i);
            worldinfo.setRaining(true);
            worldinfo.setThundering(false);
            this.dataManager.set(VINE_PULL, false);
            return;
        }

        int tick = this.ticksExisted;
        tick %= 600;

        if (this.getHealth() <= this.getMaxHealth() / 2)
        {
            if (!this.isDead)
            {
                if (tick > 300)
                {
                    this.dataManager.set(VINE_PULL, true);
                }
                else
                {
                    this.dataManager.set(VINE_PULL, false);
                }
            }
        }
        else
        {
            this.dataManager.set(VINE_PULL, false);

            if (!this.world.isRemote && this.ticksExisted % 200 == 0)
            {
                List<EntityVeinFloaterMinion> minionList = this.world.getEntitiesWithinAABB(EntityVeinFloaterMinion.class, new AxisAlignedBB(this.posX - 32, this.posY - 32, this.posZ - 32, this.posX + 32, this.posY + 32, this.posZ + 32));

                if (minionList.size() <= 32)
                {
                    EntityVeinFloaterMinion tentacle1 = new EntityVeinFloaterMinion(this.world);
                    tentacle1.setLocationAndAngles(this.posX + 2.0F, this.posY + 12.0F, this.posZ + 2.0F, 0.0F, 0.0F);
                    tentacle1.setAbsorptionAmount(20.0F);
                    this.world.spawnEntity(tentacle1);

                    EntityVeinFloaterMinion tentacle2 = new EntityVeinFloaterMinion(this.world);
                    tentacle2.setLocationAndAngles(this.posX - 2.0F, this.posY + 12.0F, this.posZ - 2.0F, 0.0F, 0.0F);
                    tentacle2.setAbsorptionAmount(20.0F);
                    this.world.spawnEntity(tentacle2);

                    EntityVeinFloaterMinion tentacle3 = new EntityVeinFloaterMinion(this.world);
                    tentacle3.setLocationAndAngles(this.posX + 2.0F, this.posY + 12.0F, this.posZ - 2.0F, 0.0F, 0.0F);
                    tentacle3.setAbsorptionAmount(20.0F);
                    this.world.spawnEntity(tentacle3);

                    EntityVeinFloaterMinion tentacle4 = new EntityVeinFloaterMinion(this.world);
                    tentacle4.setLocationAndAngles(this.posX - 2.0F, this.posY + 12.0F, this.posZ + 2.0F, 0.0F, 0.0F);
                    tentacle4.setAbsorptionAmount(20.0F);
                    this.world.spawnEntity(tentacle4);
                }
            }
        }

        if (this.getVinePull())
        {
            int range = 16;
            List<EntityPlayer> entitiesAroundBH = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.posX - range, this.posY - range, this.posZ - range, this.posX + range, this.posY + range, this.posZ + range));

            for (EntityPlayer entity : entitiesAroundBH)
            {
                if (!entity.capabilities.isCreativeMode)
                {
                    double motionX = this.posX - entity.posX;
                    double motionY = this.posY - entity.posY + 12.0D;
                    double motionZ = this.posZ - entity.posZ;
                    entity.motionX = motionX * 0.025F;
                    entity.motionY = motionY * 0.025F;
                    entity.motionZ = motionZ * 0.025F;
                    List<EntityPlayer> entityNearBH = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.posX - 1.0D, this.posY - 1.0D, this.posZ - 1.0D, this.posX + 5.0D, this.posY + 12.5D, this.posZ + 5.0D));

                    for (EntityPlayer near : entityNearBH)
                    {
                        near.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
                    }
                }
            }
        }
        if (this.getHealth() <= this.getMaxHealth() / 3)
        {
            int i = (300 + this.rand.nextInt(600)) * 20;
            WorldInfo worldinfo = this.world.getWorldInfo();
            worldinfo.setCleanWeatherTime(0);
            worldinfo.setRainTime(i);
            worldinfo.setThunderTime(i);
            worldinfo.setRaining(true);
            worldinfo.setThundering(true);

            if (this.rand.nextFloat() > 0.975F && !this.isDead)
            {
                EntityPlayer player = this.world.getClosestPlayer(this.posX, this.posY, this.posZ, 32, false);

                if (player != null && !player.capabilities.isCreativeMode && !player.isDead)
                {
                    EntityNibiruLightningBolt bolt = new EntityNibiruLightningBolt(this.world);
                    bolt.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
                    this.world.spawnEntity(bolt);
                }
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (!(damageSource.getSourceOfDamage() instanceof EntityVeinBall) && damageSource != DamageSource.LIGHTNING_BOLT)
        {
            if (this.isEntityInvulnerable(damageSource))
            {
                return false;
            }
            else if (super.attackEntityFrom(damageSource, damage))
            {
                Entity entity = damageSource.getEntity();

                if (this.getPassengers().contains(entity) && this.getRidingEntity() != entity)
                {
                    if (entity != this)
                    {
                        this.setRevengeTarget((EntityLivingBase)entity);
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
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    protected void collideWithEntity(Entity entity) {}

    @Override
    public void onKillCommand()
    {
        this.setHealth(0.0F);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_SPORE ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean isInWater()
    {
        return false;
    }

    @Override
    public boolean handleWaterMovement()
    {
        return false;
    }

    @Override
    public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {}

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0F * ConfigManagerCore.dungeonBossHealthMod);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
    }

    @Override
    protected void updateAITasks()
    {
        if (this.ticksExisted % 40 == 0)
        {
            this.heal(10.0F);
        }
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void onDeathUpdate()
    {
        this.world.playEvent(1010, this.getPosition(), 0);
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 5.5F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 28.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 5.5F;
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
                i = 200;

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
            i = 200;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
            }

            this.entityDropItem(new ItemStack(NibiruItems.NIBIRU_DUNGEON_KEY, 1, this.rand.nextBoolean() ? 1 : 2), 0.5F);
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
                    JsonUtil json = new JsonUtil();
                    player.sendMessage(json.text(GCCoreUtil.translate("gui.skeleton_boss.message")).setStyle(json.red()));
                }
                this.setDead();
                return;
            }
            this.entitiesWithinLast = this.entitiesWithin;
        }
        if (!this.playMusic)
        {
            this.world.playEvent(1010, this.getPosition(), Item.getIdFromItem(MPItems.VEIN_FLOATER_DISC));
            this.playMusic = true;
        }
        this.partHead.onUpdate();
        this.partHead.setLocationAndAngles(this.posX, this.posY + 13.0D, this.posZ, 0.0F, 0.0F);
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        super.onLivingUpdate();
    }

    @Override
    public void setDead()
    {
        if (this.spawner != null)
        {
            this.spawner.isBossDefeated = false;
            this.spawner.boss = null;
            this.spawner.spawned = false;
        }
        MorePlanetsCore.PROXY.removeBoss(this);
        super.setDead();
    }

    @Override
    public void onBossSpawned(TileEntityDungeonSpawner spawner)
    {
        this.spawner = spawner;
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
    public World getWorld()
    {
        return this.world;
    }

    @Override
    public boolean attackEntityFromPart(EntityDragonPart part, DamageSource source, float damage)
    {
        return super.attackEntityFrom(source, damage);
    }

    @Override
    public Entity[] getParts()
    {
        return this.partArray;
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
        return "Nibiru Boss";
    }

    @Override
    public int getBossTextColor()
    {
        return ColorHelper.rgbToDecimal(189, 95, 17);
    }

    public boolean getVinePull()
    {
        return this.dataManager.get(VINE_PULL);
    }
}
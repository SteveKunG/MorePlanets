package stevekung.mods.moreplanets.planets.nibiru.entity;

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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class EntityMiniVeinFloater extends EntityMob implements IMorePlanetsBoss, IEntityBreathable, ISpaceMob
{
    private TileEntityDungeonSpawner<?> spawner;
    public int deathTicks = 0;
    private int entitiesWithin;
    private int entitiesWithinLast;
    private static final DataParameter<Boolean> VINE_PULL = EntityDataManager.createKey(EntityMiniVeinFloater.class, DataSerializers.BOOLEAN);
    private final BossInfoServer bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);

    public EntityMiniVeinFloater(World world)
    {
        super(world);
        MorePlanetsMod.PROXY.addBoss(this);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.setSize(3.0F, 8.2F);
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
        MorePlanetsMod.PROXY.addBoss(this);
        this.motionY *= 0.5D;

        if (this.getHealth() <= 0.0F)
        {
            this.dataManager.set(VINE_PULL, false);
            return;
        }

        int tick = this.ticksExisted;
        tick %= 300;

        if (!this.isDead)
        {
            if (tick > 150)
            {
                this.dataManager.set(VINE_PULL, true);
            }
            else
            {
                this.dataManager.set(VINE_PULL, false);
            }
        }

        if (this.getVinePull())
        {
            int range = 32;
            List<EntityPlayer> entitiesAroundBH = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.posX - range, this.posY - range, this.posZ - range, this.posX + range, this.posY + range, this.posZ + range));

            for (EntityPlayer entity : entitiesAroundBH)
            {
                if (!entity.capabilities.isCreativeMode)
                {
                    double motionX = this.posX - entity.posX;
                    double motionY = this.posY - entity.posY + 4.0D;
                    double motionZ = this.posZ - entity.posZ;
                    entity.motionX = motionX * 0.025F;
                    entity.motionY = motionY * 0.025F;
                    entity.motionZ = motionZ * 0.025F;
                    List<EntityPlayer> entityNearBH = this.world.getEntitiesWithinAABB(entity.getClass(), new AxisAlignedBB(this.posX - 1.0D, this.posY - 1.0D, this.posZ - 1.0D, this.posX + 5.0D, this.posY + 6.5D, this.posZ + 5.0D));

                    for (EntityPlayer near : entityNearBH)
                    {
                        near.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
                    }
                }
            }
        }
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
    public void addPotionEffect(PotionEffect effect) {}

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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(750.0F * ConfigManagerCore.dungeonBossHealthMod);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
    }

    @Override
    protected void updateAITasks()
    {
        if (this.ticksExisted % 60 == 0)
        {
            this.heal(5.0F);
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
                chest = TileEntityTreasureChestMP.findClosest(this, MPItems.NIBIRU_DUNGEON_KEY);
            }

            if (chest != null)
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

            this.entityDropItem(new ItemStack(MPItems.NIBIRU_DUNGEON_KEY_BOW), 0.5F);
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
        if (this.spawner != null)
        {
            this.spawner.isBossDefeated = false;
            this.spawner.boss = null;
            this.spawner.spawned = false;
        }
        MorePlanetsMod.PROXY.removeBoss(this);
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
        return "Nibiru Mini Boss";
    }

    @Override
    public int getBossTextColor()
    {
        return ColorUtils.rgbToDecimal(189, 95, 17);
    }

    public boolean getVinePull()
    {
        return this.dataManager.get(VINE_PULL);
    }
}
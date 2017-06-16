/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.entities;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.IBoss;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.core.entities.EntityFlyingBossMP;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityPolongniusTreasureChest;

public class EntityCheeseCubeEyeBoss extends EntityFlyingBossMP implements IMob, IEntityBreathable, IBossDisplayData, IRangedAttackMob, IBoss
{
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private TileEntityDungeonSpawner spawner;
    private Entity targetedEntity;
    protected long ticks = 0;
    public int deathTicks = 0;
    public int attackCounter;
    public int prevAttackCounter;
    private int aggroCooldown;
    public int entitiesWithin;
    public int entitiesWithinLast;
    private Vector3 roomCoords;
    private Vector3 roomSize;

    public EntityCheeseCubeEyeBoss(World world)
    {
        super(world);
        this.setSize(1.8F, 2.0F);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1007);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(350.0F * ConfigManagerCore.dungeonBossHealthMod);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(15.0F);
    }

    @Override
    public void knockBack(Entity par1Entity, float par2, double par3, double par5)
    {
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 1.5F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 1.5F;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + f, this.posY + 2.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D);
        }

        int i;
        int j;

        if (!this.worldObj.isRemote)
        {
            if (this.deathTicks >= 180 && this.deathTicks % 5 == 0)
            {
                GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_EXPLODE, new Object[] { }), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 40.0D));
            }

            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                i = 30;

                while (i > 0)
                {
                    j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            if (this.deathTicks == 1)
            {
                GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_PLAY_SOUND_BOSS_DEATH, new Object[] {}), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 40.0D));
            }
        }

        this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        this.renderYawOffset = this.rotationYaw += 20.0F;

        if (this.deathTicks == 200 && !this.worldObj.isRemote)
        {
            i = 20;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
            }

            if (!this.worldObj.isRemote)
            {
                for (TileEntity tile : (List<TileEntity>) this.worldObj.loadedTileEntityList)
                {
                    if (tile instanceof TileEntityPolongniusTreasureChest)
                    {
                        double d3 = tile.xCoord + 0.5D - this.posX;
                        double d4 = tile.yCoord + 0.5D - this.posY;
                        double d5 = tile.zCoord + 0.5D - this.posZ;
                        double dSq = d3 * d3 + d4 * d4 + d5 * d5;
                        TileEntityPolongniusTreasureChest chest = (TileEntityPolongniusTreasureChest) tile;

                        if (dSq < Math.pow(100.0D, 2))
                        {
                            if (!chest.locked)
                            {
                                chest.locked = true;
                            }

                            for (int k = 0; k < chest.getSizeInventory(); k++)
                            {
                                chest.setInventorySlotContents(k, null);
                            }

                            ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                            WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
                            WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
                            WeightedRandomChestContent.generateChestContents(this.rand, info.getItems(this.rand), chest, info.getCount(this.rand));
                            chest.setInventorySlotContents(this.rand.nextInt(chest.getSizeInventory()), this.getGuaranteedLoot(this.rand));
                            break;
                        }
                    }
                }
            }

            this.entityDropItem(new ItemStack(PolongniusItems.polongnius_dungeon_key, 1, 0), 0.5F);
            super.setDead();

            if (this.spawner != null)
            {
                this.spawner.isBossDefeated = true;
                this.spawner.boss = null;
                this.spawner.spawned = false;
            }
        }
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
        super.setDead();
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.ticks >= Long.MAX_VALUE)
        {
            this.ticks = 1;
        }

        this.ticks++;

        EntityPlayer player = this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, 20.0);

        if (player != null && !player.equals(this.targetedEntity))
        {
            if (this.getDistanceSqToEntity(player) < 400.0D)
            {
                this.getNavigator().getPathToEntityLiving(player);
                this.targetedEntity = player;
            }
        }
        else
        {
            this.targetedEntity = null;
        }

        if (this.roomCoords != null && this.roomSize != null)
        {
            @SuppressWarnings("unchecked")
            final
            List<Entity> entitiesWithin = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.roomCoords.intX() - 1, this.roomCoords.intY() - 1, this.roomCoords.intZ() - 1, this.roomCoords.intX() + this.roomSize.intX(), this.roomCoords.intY() + this.roomSize.intY(), this.roomCoords.intZ() + this.roomSize.intZ()));

            this.entitiesWithin = entitiesWithin.size();

            if (this.entitiesWithin == 0 && this.entitiesWithinLast != 0)
            {
                @SuppressWarnings("unchecked")
                final
                List<EntityPlayer> entitiesWithin2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.roomCoords.intX() - 11, this.roomCoords.intY() - 11, this.roomCoords.intZ() - 11, this.roomCoords.intX() + this.roomSize.intX() + 10, this.roomCoords.intY() + this.roomSize.intY() + 10, this.roomCoords.intZ() + this.roomSize.intZ() + 10));

                for (EntityPlayer p : entitiesWithin2)
                {
                    p.addChatMessage(new ChatComponentText(GCCoreUtil.translate("gui.skeletonBoss.message")));
                }

                this.setDead();

                if (this.spawner != null)
                {
                    this.spawner.playerCheated = true;
                }
                return;
            }
            this.entitiesWithinLast = this.entitiesWithin;
        }
        super.onLivingUpdate();
    }

    @Override
    public EntityItem entityDropItem(ItemStack par1ItemStack, float par2)
    {
        EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY + par2, this.posZ, par1ItemStack);
        entityitem.motionY = -2.0D;
        entityitem.delayBeforeCanPickup = 10;

        if (this.captureDrops)
        {
            this.capturedDrops.add(entityitem);
        }
        else
        {
            this.worldObj.spawnEntityInWorld(entityitem);
        }
        return entityitem;
    }

    public ItemStack getGuaranteedLoot(Random rand)
    {
        List<ItemStack> stackList = GalacticraftRegistry.getDungeonLoot(4);
        return stackList.get(rand.nextInt(stackList.size()));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);

        if (this.roomCoords != null)
        {
            nbt.setDouble("roomCoordsX", this.roomCoords.x);
            nbt.setDouble("roomCoordsY", this.roomCoords.y);
            nbt.setDouble("roomCoordsZ", this.roomCoords.z);
            nbt.setDouble("roomSizeX", this.roomSize.x);
            nbt.setDouble("roomSizeY", this.roomSize.y);
            nbt.setDouble("roomSizeZ", this.roomSize.z);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.roomCoords = new Vector3();
        this.roomCoords.x = nbt.getDouble("roomCoordsX");
        this.roomCoords.y = nbt.getDouble("roomCoordsY");
        this.roomCoords.z = nbt.getDouble("roomCoordsZ");
        this.roomSize = new Vector3();
        this.roomSize.x = nbt.getDouble("roomSizeX");
        this.roomSize.y = nbt.getDouble("roomSizeY");
        this.roomSize.z = nbt.getDouble("roomSizeZ");
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (damageSource.getDamageType().equals("arrow"))
        {
            if (this.isEntityInvulnerable())
            {
                return false;
            }
            else if (super.attackEntityFrom(damageSource, damage))
            {
                Entity entity = damageSource.getEntity();

                if (this.riddenByEntity != entity && this.ridingEntity != entity)
                {
                    if (entity != this)
                    {
                        this.entityToAttack = entity;
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
    protected void updateEntityActionState()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }

        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double d1 = this.waypointX - this.posX;
        double d2 = this.waypointY - this.posY;
        double d3 = this.waypointZ - this.posZ;
        double d4 = d1 * d1 + d2 * d2 + d3 * d3;

        if (d4 < 1.0D || d4 > 3600.0D)
        {
            this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
        }
        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d4 = MathHelper.sqrt_double(d4);

            if (this.isCourseTraversable(d4))
            {
                this.motionX += d1 / d4 * 0.1D;
                this.motionY += d2 / d4 * 0.1D;
                this.motionZ += d3 / d4 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.isDead)
        {
            this.targetedEntity = null;
        }
        if (this.targetedEntity == null || this.aggroCooldown-- <= 0)
        {
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

            if (this.targetedEntity != null)
            {
                this.aggroCooldown = 20;
            }
        }

        double d5 = 64.0D;

        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d5 * d5)
        {
            double d6 = this.targetedEntity.posX - this.posX;
            double d7 = this.targetedEntity.boundingBox.minY + this.targetedEntity.height / 2.0F - (this.posY + this.height / 2.0F);
            double d8 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -(float)Math.atan2(d6, d8) * 180.0F / 3.141593F;

            if (this.canEntityBeSeen(this.targetedEntity))
            {
                this.attackCounter += 1;

                if (this.attackCounter == 15)
                {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1014, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
                    EntityCheeseSpore cheese = new EntityCheeseSpore(this.worldObj, this, d6, d7, d8);
                    Vec3 localVec3 = this.getLook(1.0F);
                    cheese.posX = this.posX + localVec3.xCoord;
                    cheese.posY = this.posY + this.height / 2.0F;
                    cheese.posZ = this.posZ + localVec3.zCoord;
                    this.worldObj.spawnEntityInWorld(cheese);
                    this.attackCounter = -40;
                }
            }
            else if (this.attackCounter > 0)
            {
                this.attackCounter -= 1;
            }
        }
        else
        {
            this.renderYawOffset = this.rotationYaw = -(float)Math.atan2(this.motionX, this.motionZ) * 180.0F / 3.141593F;

            if (this.attackCounter > 0)
            {
                this.attackCounter -= 1;
            }
        }
    }

    private boolean isCourseTraversable(double par1)
    {
        double d1 = (this.waypointX - this.posX) / par1;
        double d2 = (this.waypointY - this.posY) / par1;
        double d3 = (this.waypointZ - this.posZ) / par1;
        AxisAlignedBB axis = this.boundingBox.copy();

        for (int i = 1; i < par1; i++)
        {
            axis.offset(d1, d2, d3);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axis).isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    protected String getLivingSound()
    {
        return null;
    }

    @Override
    protected String getHurtSound()
    {
        return "fronos:mob.cheese_boss.hit";
    }

    @Override
    protected String getDeathSound()
    {
        return null;
    }

    @Override
    protected float getSoundVolume()
    {
        return 10.0F;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase, float f)
    {
        this.func_82216_a(0, entitylivingbase);
    }

    private void func_82216_a(int par1, EntityLivingBase par2EntityLivingBase)
    {
        this.func_82209_a(par1, par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() * 0.5D, par2EntityLivingBase.posZ);
    }

    private void func_82209_a(int par1, double par2, double par4, double par6)
    {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1014, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
        double d3 = this.func_82214_u(par1);
        double d4 = this.func_82208_v(par1);
        double d5 = this.func_82213_w(par1);
        double d6 = par2 - d3;
        double d7 = par4 - d4;
        double d8 = par6 - d5;
        EntityCheeseSpore entitywitherskull = new EntityCheeseSpore(this.worldObj, this, d6 * 0.5D, d7 * 0.5D, d8 * 0.5D);

        entitywitherskull.posY = d4;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        this.worldObj.spawnEntityInWorld(entitywitherskull);
    }

    private double func_82214_u(int par1)
    {
        if (par1 <= 0)
        {
            return this.posX;
        }
        else
        {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
            float f1 = MathHelper.cos(f);
            return this.posX + f1 * 0.5D;
        }
    }

    private double func_82208_v(int par1)
    {
        return par1 <= 0 ? this.posY + 0.0D : this.posY + 0.0D;
    }

    private double func_82213_w(int par1)
    {
        if (par1 <= 0)
        {
            return this.posZ;
        }
        else
        {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 0.5D;
        }
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public void setRoom(Vector3 roomCoords, Vector3 roomSize)
    {
        this.roomCoords = roomCoords;
        this.roomSize = roomSize;
    }

    @Override
    public void onBossSpawned(TileEntityDungeonSpawner spawner)
    {
        this.spawner = spawner;
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities;

import java.util.IdentityHashMap;
import java.util.UUID;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityEvolvedEnderman extends EntityMob implements IEntityBreathable
{
    private static UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137D, 0).setSaved(false);
    @Deprecated
    private static boolean[] carriableBlocks = new boolean[256];
    private int teleportDelay;
    private int stareTimer;
    private Entity lastEntityToAttack;
    private boolean isAggressive;
    private static IdentityHashMap<Block, Boolean> carriable;

    public EntityEvolvedEnderman(World world)
    {
        super(world);
        this.setSize(0.6F, 2.9F);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.300000011920929D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1001);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
        this.dataWatcher.addObject(17, new Byte((byte)0));
        this.dataWatcher.addObject(18, new Byte((byte)0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setShort("carried", (short)Block.getIdFromBlock(this.getCarried()));
        nbt.setShort("carriedData", (short)this.getCarryingData());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.setCarried(Block.getBlockById(nbt.getShort("carried")));
        this.setCarryingData(nbt.getShort("carriedData"));
    }

    @Override
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

        if (entityplayer != null)
        {
            if (this.shouldAttackPlayer(entityplayer))
            {
                this.isAggressive = true;

                if (this.stareTimer == 0)
                {
                    this.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.endermen.stare", 1.0F, 1.0F);
                }
                if (this.stareTimer++ == 5)
                {
                    this.stareTimer = 0;
                    this.setScreaming(true);
                    return entityplayer;
                }
            }
            else
            {
                this.stareTimer = 0;
            }
        }
        return null;
    }

    private boolean shouldAttackPlayer(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.armorInventory[3];

        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
        {
            return false;
        }

        Vec3 vec3 = player.getLook(1.0F).normalize();
        Vec3 vec31 = Vec3.createVectorHelper(this.posX - player.posX, this.boundingBox.minY + this.height / 2.0F - (player.posY + player.getEyeHeight()), this.posZ - player.posZ);
        double d0 = vec31.lengthVector();
        vec31 = vec31.normalize();
        double d1 = vec3.dotProduct(vec31);
        return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        }
        if (this.lastEntityToAttack != this.entityToAttack)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            iattributeinstance.removeModifier(attackingSpeedBoostModifier);

            if (this.entityToAttack != null)
            {
                iattributeinstance.applyModifier(attackingSpeedBoostModifier);
            }
        }

        this.lastEntityToAttack = this.entityToAttack;

        if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
        {
            if (this.getCarried().getMaterial() == Material.air)
            {
                if (this.rand.nextInt(20) == 0)
                {
                    int k = MathHelper.floor_double(this.posX - 2.0D + this.rand.nextDouble() * 4.0D);
                    int i = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 3.0D);
                    int j = MathHelper.floor_double(this.posZ - 2.0D + this.rand.nextDouble() * 4.0D);
                    Block block = this.worldObj.getBlock(k, i, j);

                    if (getCarriable(block))
                    {
                        this.setCarried(block);
                        this.setCarryingData(this.worldObj.getBlockMetadata(k, i, j));
                        this.worldObj.setBlock(k, i, j, Blocks.air);
                    }
                }
            }
            else if (this.rand.nextInt(2000) == 0)
            {
                int k = MathHelper.floor_double(this.posX - 1.0D + this.rand.nextDouble() * 2.0D);
                int i = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 2.0D);
                int j = MathHelper.floor_double(this.posZ - 1.0D + this.rand.nextDouble() * 2.0D);
                Block block = this.worldObj.getBlock(k, i, j);
                Block block1 = this.worldObj.getBlock(k, i - 1, j);

                if (block.getMaterial() == Material.air && block1.getMaterial() != Material.air && block1.renderAsNormalBlock())
                {
                    this.worldObj.setBlock(k, i, j, this.getCarried(), this.getCarryingData(), 3);
                    this.setCarried(Blocks.air);
                }
            }
        }

        for (int k = 0; k < 2; k++)
        {
            MorePlanetsCore.proxy.spawnMotionParticle("bluePortal", this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
            {
                this.entityToAttack = null;
                this.setScreaming(false);
                this.isAggressive = false;
                this.teleportRandomly();
            }
        }
        if (this.isWet() || this.isBurning())
        {
            this.entityToAttack = null;
            this.setScreaming(false);
            this.isAggressive = false;
            this.teleportRandomly();
        }
        if (this.isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0)
        {
            this.setScreaming(false);
        }

        this.isJumping = false;

        if (this.entityToAttack != null)
        {
            this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
        }
        if (!this.worldObj.isRemote && this.isEntityAlive())
        {
            if (this.entityToAttack != null)
            {
                if (this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)this.entityToAttack))
                {
                    if (this.entityToAttack.getDistanceSqToEntity(this) < 16.0D)
                    {
                        this.teleportRandomly();
                    }
                    this.teleportDelay = 0;
                }
                else if (this.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.teleportDelay++ >= 30 && this.teleportToEntity(this.entityToAttack))
                {
                    this.teleportDelay = 0;
                }
            }
            else
            {
                this.setScreaming(false);
                this.teleportDelay = 0;
            }
        }
        super.onLivingUpdate();
    }

    protected boolean teleportRandomly()
    {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.posY + (this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity entity)
    {
        Vec3 vec3 = Vec3.createVectorHelper(this.posX - entity.posX, this.boundingBox.minY + this.height / 2.0F - entity.posY + entity.getEyeHeight(), this.posZ - entity.posZ);
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
        double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_)
    {
        EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0.0F);

        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = event.targetX;
        this.posY = event.targetY;
        this.posZ = event.targetZ;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    this.posY -= 1.0D;
                    j--;
                }
            }
            if (flag1)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    flag = true;
                }
            }
        }
        if (!flag)
        {
            this.setPosition(d3, d4, d5);
            return false;
        }

        short short1 = 128;

        for (int l = 0; l < short1; l++)
        {
            double d6 = l / (short1 - 1.0D);
            float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
            double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
            double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
            MorePlanetsCore.proxy.spawnMotionParticle("bluePortal", d7, d8, d9, f, f1, f2);
        }
        this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
        this.playSound("mob.endermen.portal", 1.0F, 1.0F);
        return true;
    }

    @Override
    protected String getLivingSound()
    {
        return this.isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.endermen.hit";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.endermen.death";
    }

    @Override
    protected Item getDropItem()
    {
        return Items.ender_pearl;
    }

    @Override
    protected void dropFewItems(boolean bool, int par2)
    {
        Item item = this.getDropItem();

        if (item != null)
        {
            int j = this.rand.nextInt(2 + par2);

            for (int k = 0; k < j; k++)
            {
                this.dropItem(item, 1);
            }
        }
    }

    public void setCarried(Block block)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)(Block.getIdFromBlock(block) & 0xFF)));
    }

    public Block getCarried()
    {
        return Block.getBlockById(this.dataWatcher.getWatchableObjectByte(16));
    }

    public void setCarryingData(int meta)
    {
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)(meta & 0xFF)));
    }

    public int getCarryingData()
    {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }

        this.setScreaming(true);

        if (source instanceof EntityDamageSource && source.getEntity() instanceof EntityPlayer)
        {
            this.isAggressive = true;
        }
        if (source instanceof EntityDamageSourceIndirect)
        {
            this.isAggressive = false;

            for (int i = 0; i < 64; i++)
            {
                if (this.teleportRandomly())
                {
                    return true;
                }
            }
            return super.attackEntityFrom(source, par2);
        }
        return super.attackEntityFrom(source, par2);
    }

    public boolean isScreaming()
    {
        return this.dataWatcher.getWatchableObjectByte(18) > 0;
    }

    public void setScreaming(boolean bool)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)(bool ? 1 : 0)));
    }

    static
    {
        carriableBlocks[Block.getIdFromBlock(Blocks.grass)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.dirt)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.sand)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.gravel)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.yellow_flower)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.red_flower)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.brown_mushroom)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.red_mushroom)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.tnt)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.cactus)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.clay)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.pumpkin)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.melon_block)] = true;
        carriableBlocks[Block.getIdFromBlock(Blocks.mycelium)] = true;

        for (int x = 0; x < carriableBlocks.length; x++)
        {
            if (carriableBlocks[x])
            {
                setCarriable(Block.getBlockById(x), true);
            }
        }
    }

    public static void setCarriable(Block block, boolean canCarry)
    {
        if (carriable == null)
        {
            carriable = new IdentityHashMap(4096);
        }
        carriable.put(block, Boolean.valueOf(canCarry));
    }

    public static boolean getCarriable(Block block)
    {
        Boolean ret = carriable.get(block);
        return ret != null ? ret.booleanValue() : false;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    protected void dropRareDrop(int par1)
    {
        switch (this.rand.nextInt(10))
        {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            break;
        case 6:
            //Oxygen tank half empty or less
            this.entityDropItem(new ItemStack(GCItems.oxTankMedium, 1, 901 + this.rand.nextInt(900)), 0.0F);
            break;
        case 7:
        case 8:
            this.dropItem(Items.ender_eye, 1);
            break;
        case 9:
            this.dropItem(GCItems.oxygenConcentrator, 1);
            break;
        case 10:
            this.dropItem(GCItems.oxMask, 1);
            break;
        }
    }
}
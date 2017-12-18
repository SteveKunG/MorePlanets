package stevekung.mods.moreplanets.module.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public class EntityInfectedCow extends EntityAnimal implements ISpaceMob, IEntityBreathable
{
    public EntityInfectedCow(World world)
    {
        super(world);
        this.setSize(0.9F, 1.3F);
        ((PathNavigateGroundMP)this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, NibiruItems.INFECTED_WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected PathNavigate getNewNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.worldObj.getBlockState(blockpos.down()).getBlock() == NibiruBlocks.INFECTED_GRASS && this.worldObj.getLight(blockpos) > 8 && this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotionID() == MPPotions.INFECTED_SPORE.id ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    public EnumMobType getMobType()
    {
        return EnumMobType.NIBIRU;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.cow.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.cow.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.cow.hurt";
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block)
    {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    protected Item getDropItem()
    {
        return Items.leather;
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int i = this.rand.nextInt(3) + this.rand.nextInt(1 + fortune);

        for (int j = 0; j < i; ++j)
        {
            this.dropItem(Items.leather, 1);
        }

        i = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + fortune);

        for (int k = 0; k < i; ++k)
        {
            if (this.isBurning())
            {
                this.dropItem(Items.cooked_beef, 1);
            }
            else
            {
                this.dropItem(Items.beef, 1);
            }
        }
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.bucket && !player.capabilities.isCreativeMode && !this.isChild())
        {
            if (itemstack.stackSize-- == 1)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.milk_bucket));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
            }
            return true;
        }
        else
        {
            return super.interact(player);
        }
    }

    @Override
    public EntityInfectedCow createChild(EntityAgeable ageable)
    {
        return new EntityInfectedCow(this.worldObj);
    }

    @Override
    public float getEyeHeight()
    {
        return this.height;
    }
}
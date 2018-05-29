package stevekung.mods.moreplanets.planets.chalos.entity;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.entity.ai.PathNavigateGroundMP;

public class EntityCheeseCow extends EntityCow implements IEntityBreathable
{
    public EntityCheeseCow(World world)
    {
        super(world);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, MPItems.CHEESE_MILK_CURD, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected PathNavigate createNavigator(World world)
    {
        return new PathNavigateGroundMP(this, world);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.world.getBlockState(this.getPosition().down()).getBlock() == MPBlocks.CHEESE_GRASS_BLOCK && this.world.getLight(this.getPosition()) > 8 && this.getBlockPathWeight(this.getPosition()) >= 0.0F;
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack)
    {
        return itemStack.getItem() == MPItems.CHEESE_MILK_CURD;
    }

    @Override
    public EntityCheeseCow createChild(EntityAgeable entity)
    {
        return new EntityCheeseCow(this.world);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            ItemStack cheeseMilk = FluidUtil.getFilledBucket(new FluidStack(MPBlocks.CHEESE_MILK_FLUID, 1000));
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            itemStack.shrink(1);

            if (itemStack.isEmpty())
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, cheeseMilk);
            }
            else if (!player.inventory.addItemStackToInventory(cheeseMilk))
            {
                player.dropItem(cheeseMilk, false);
            }
            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MPLootTables.CHEESE_COW;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}
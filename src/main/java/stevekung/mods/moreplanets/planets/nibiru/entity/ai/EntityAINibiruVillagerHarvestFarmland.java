package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;
import stevekung.mods.moreplanets.utils.blocks.BlockCropsMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class EntityAINibiruVillagerHarvestFarmland extends EntityAIMoveToBlock
{
    private final EntityNibiruVillager entity;
    private boolean hasFarmItem;
    private boolean wantsToReapStuff;
    private int currentTask;

    public EntityAINibiruVillagerHarvestFarmland(EntityNibiruVillager entity, double speed)
    {
        super(entity, speed, 16);
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.runDelay <= 0)
        {
            if (!ForgeEventFactory.getMobGriefingEvent(this.entity.world, this.entity))
            {
                return false;
            }
            this.currentTask = -1;
            this.hasFarmItem = this.entity.isFarmItemInInventory();
            this.wantsToReapStuff = this.entity.wantsMoreFood();
        }
        return super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.currentTask >= 0 && super.shouldContinueExecuting();
    }

    @Override
    public void updateTask()
    {
        super.updateTask();
        this.entity.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.entity.getVerticalFaceSpeed());

        if (this.getIsAboveDestination())
        {
            World world = this.entity.world;
            BlockPos pos = this.destinationBlock.up();
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (this.currentTask == 0 && block instanceof BlockCropsMP && state.getValue(BlockStateProperty.AGE_7) == 7)
            {
                world.destroyBlock(pos, true);
            }
            else if (this.currentTask == 1 && state.getMaterial() == Material.AIR)
            {
                InventoryBasic inv = this.entity.getVillagerInventory();

                for (int i = 0; i < inv.getSizeInventory(); ++i)
                {
                    ItemStack itemStack = inv.getStackInSlot(i);
                    boolean flag = false;

                    if (!itemStack.isEmpty())
                    {
                        if (itemStack.getItem() == MPItems.INFECTED_WHEAT_SEEDS)
                        {
                            world.setBlockState(pos, MPBlocks.INFECTED_WHEAT.getDefaultState(), 3);
                            flag = true;
                        }
                        else if (itemStack.getItem() == MPItems.TERRABERRY)
                        {
                            world.setBlockState(pos, MPBlocks.TERRABERRY.getDefaultState(), 3);
                            flag = true;
                        }
                    }

                    if (flag)
                    {
                        itemStack.shrink(1);

                        if (itemStack.isEmpty())
                        {
                            inv.setInventorySlotContents(i, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
            this.currentTask = -1;
            this.runDelay = 10;
        }
    }

    @Override
    protected boolean shouldMoveTo(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block == MPBlocks.INFECTED_FARMLAND)
        {
            pos = pos.up();
            IBlockState state = world.getBlockState(pos);
            block = state.getBlock();

            if (block instanceof BlockCropsMP && state.getValue(BlockStateProperty.AGE_7).intValue() == 7 && this.wantsToReapStuff && (this.currentTask == 0 || this.currentTask < 0))
            {
                this.currentTask = 0;
                return true;
            }
            if (state.getMaterial() == Material.AIR && this.hasFarmItem && (this.currentTask == 1 || this.currentTask < 0))
            {
                this.currentTask = 1;
                return true;
            }
        }
        return false;
    }
}
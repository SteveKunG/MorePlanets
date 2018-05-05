package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.blocks.BlockCropsMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class EntityAINibiruVillagerHarvestFarmland extends EntityAIMoveToBlock
{
    private EntityNibiruVillager theVillager;
    private boolean hasFarmItem;
    private boolean field_179503_e;
    private int field_179501_f;

    public EntityAINibiruVillagerHarvestFarmland(EntityNibiruVillager theVillagerIn, double speedIn)
    {
        super(theVillagerIn, speedIn, 16);
        this.theVillager = theVillagerIn;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.runDelay <= 0)
        {
            if (!this.theVillager.world.getGameRules().getBoolean("mobGriefing"))
            {
                return false;
            }
            this.field_179501_f = -1;
            this.hasFarmItem = this.theVillager.isFarmItemInInventory();
            this.field_179503_e = this.theVillager.func_175557_cr();
        }
        return super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.field_179501_f >= 0 && super.shouldContinueExecuting();
    }

    @Override
    public void updateTask()
    {
        super.updateTask();
        this.theVillager.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.theVillager.getVerticalFaceSpeed());

        if (this.getIsAboveDestination())
        {
            World world = this.theVillager.world;
            BlockPos blockpos = this.destinationBlock.up();
            IBlockState iblockstate = world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();

            if (this.field_179501_f == 0 && block instanceof BlockCropsMP && iblockstate.getValue(BlockStateProperty.AGE_7).intValue() == 7)
            {
                world.destroyBlock(blockpos, true);
            }
            else if (this.field_179501_f == 1 && block == Blocks.AIR)
            {
                InventoryBasic inventorybasic = this.theVillager.getVillagerInventory();

                for (int i = 0; i < inventorybasic.getSizeInventory(); ++i)
                {
                    ItemStack itemStack = inventorybasic.getStackInSlot(i);
                    boolean flag = false;

                    if (!itemStack.isEmpty())
                    {
                        if (itemStack.getItem() == NibiruItems.INFECTED_WHEAT_SEEDS)
                        {
                            world.setBlockState(blockpos, NibiruBlocks.INFECTED_WHEAT.getDefaultState(), 3);
                            flag = true;
                        }
                        else if (itemStack.getItem() == NibiruItems.TERRABERRY)
                        {
                            world.setBlockState(blockpos, NibiruBlocks.TERRABERRY.getDefaultState(), 3);
                            flag = true;
                        }
                    }

                    if (flag)
                    {
                        itemStack.shrink(1);

                        if (itemStack.getCount() <= 0)
                        {
                            inventorybasic.setInventorySlotContents(i, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
            this.field_179501_f = -1;
            this.runDelay = 10;
        }
    }

    @Override
    protected boolean shouldMoveTo(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block == NibiruBlocks.INFECTED_FARMLAND)
        {
            pos = pos.up();
            IBlockState iblockstate = world.getBlockState(pos);
            block = iblockstate.getBlock();

            if (block instanceof BlockCropsMP && iblockstate.getValue(BlockStateProperty.AGE_7).intValue() == 7 && this.field_179503_e && (this.field_179501_f == 0 || this.field_179501_f < 0))
            {
                this.field_179501_f = 0;
                return true;
            }
            if (block == Blocks.AIR && this.hasFarmItem && (this.field_179501_f == 1 || this.field_179501_f < 0))
            {
                this.field_179501_f = 1;
                return true;
            }
        }
        return false;
    }
}
package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockBushMP;

public class BlockInfectedSeaweed extends BlockBushMP
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.8D, 0.8D);

    public BlockInfectedSeaweed(String name)
    {
        super(Material.WATER);
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockInfectedSeaweed.AABB;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, heldStack) > 0)
        {
            List<ItemStack> items = new ArrayList<>();
            ItemStack itemstack = this.getSilkTouchDrop(state);

            if (!itemstack.isEmpty())
            {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, pos, world.getBlockState(pos), 0, 1.0f, true, player);

            for (ItemStack is : items)
            {
                Block.spawnAsEntity(world, pos, is);
            }
        }
        else
        {
            if (world.provider.doesWaterVaporize())
            {
                world.setBlockToAir(pos);
                return;
            }

            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, heldStack);
            this.harvesters.set(player);
            this.dropBlockAsItem(world, pos, state, i);
            this.harvesters.set(null);
            Material material = world.getBlockState(pos.down()).getMaterial();

            if (material.blocksMovement() || material.isLiquid() || player.isCreative())
            {
                world.setBlockState(pos, MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());
            }
        }
    }

    @Override
    protected boolean validBlock(Block block)
    {
        return block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        Block blockDown = world.getBlockState(pos.down()).getBlock();
        IBlockState blockUp = world.getBlockState(pos.up());
        return super.canPlaceBlockAt(world, pos) && this.validBlock(blockDown) && blockUp == MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block blockDown = world.getBlockState(pos.down()).getBlock();
        IBlockState blockUp = world.getBlockState(pos.up());

        if (state.getBlock() == this)
        {
            return this.validBlock(blockDown) && blockUp == MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();
        }
        return this.validBlock(blockDown) && blockUp == MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return false;
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XYZ;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockLiquid.LEVEL);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockLiquid.LEVEL, 15);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}
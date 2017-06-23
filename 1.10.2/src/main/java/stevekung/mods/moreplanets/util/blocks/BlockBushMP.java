package stevekung.mods.moreplanets.util.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBushMP extends BlockBaseMP
{
    public BlockBushMP()
    {
        super(Material.PLANTS);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
    }

    public BlockBushMP(Material material)
    {
        super(material);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean canReplace(World world, BlockPos pos, EnumFacing side, ItemStack itemStack)
    {
        if (world == null || pos == null || side == null)
        {
            return false;
        }
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && this.canBlockStay(world, pos, this.getStateFromMeta(itemStack.getMetadata()));
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block)
    {
        this.checkAndDropBlock(world, pos, state);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(world, pos, state);
    }

    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.FLOWER;
    }
}
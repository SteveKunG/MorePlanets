package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.DamageSourceMP;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.helper.BlockEventHelper;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockInfectedCactus extends BlockBushMP
{
    public BlockInfectedCactus(String name)
    {
        super(Material.cactus);
        this.setUnlocalizedName(name);
        this.setHardness(0.4F);
        this.setStepSound(soundTypeCloth);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        BlockPos blockpos = pos.up();

        if (world.isAirBlock(blockpos))
        {
            int i;

            for (i = 1; world.getBlockState(pos.down(i)).getBlock() == this; ++i) {}

            if (i < 3)
            {
                int j = state.getValue(BlockStateHelper.AGE).intValue();

                if (j == 15)
                {
                    world.setBlockState(blockpos, this.getDefaultState());
                    IBlockState iblockstate = state.withProperty(BlockStateHelper.AGE, Integer.valueOf(0));
                    world.setBlockState(pos, iblockstate, 4);
                    this.onNeighborBlockChange(world, blockpos, iblockstate, this);
                }
                else
                {
                    world.setBlockState(pos, state.withProperty(BlockStateHelper.AGE, Integer.valueOf(j + 1)), 4);
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        float f = 0.0625F;
        return new AxisAlignedBB(pos.getX() + f, pos.getY(), pos.getZ() + f, pos.getX() + 1 - f, pos.getY() + 1 - f, pos.getZ() + 1 - f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
    {
        float f = 0.0625F;
        return new AxisAlignedBB(pos.getX() + f, pos.getY(), pos.getZ() + f, pos.getX() + 1 - f, pos.getY() + 1, pos.getZ() + 1 - f);
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            world.destroyBlock(pos, true);
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
        {
            if (world.getBlockState(pos.offset(enumfacing)).getBlock().getMaterial().isSolid())
            {
                return false;
            }
        }
        if (BlockEventHelper.isLiquidBlock(world, pos))
        {
            return false;
        }
        return world.getBlockState(pos.down()).getBlock() == NibiruBlocks.INFECTED_SAND || world.getBlockState(pos.down()).getBlock() == this;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        entity.attackEntityFrom(DamageSourceMP.INFECTED_GAS, (int) (4.0D * 0.1D + 1.0D));
        entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.AGE, Integer.valueOf(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.AGE).intValue();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {BlockStateHelper.AGE});
    }

    @Override
    public String getName()
    {
        return "infected_cactus";
    }
}
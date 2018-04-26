package stevekung.mods.moreplanets.util.blocks;

import java.util.Iterator;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public abstract class BlockLogMP extends BlockBaseMP
{
    public BlockLogMP()
    {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }

    public BlockLogMP(Material material)
    {
        super(material);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(BlockStateProperty.AXIS, BlockStateProperty.EnumAxis.fromFacingAxis(facing.getAxis()));
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        byte b0 = 4;
        int i = b0 + 1;

        if (world.isAreaLoaded(pos.add(-i, -i, -i), pos.add(i, i, i)))
        {
            Iterator iterator = BlockPos.getAllInBox(pos.add(-b0, -b0, -b0), pos.add(b0, b0, b0)).iterator();

            while (iterator.hasNext())
            {
                BlockPos blockpos1 = (BlockPos)iterator.next();
                IBlockState iblockstate1 = world.getBlockState(blockpos1);

                if (iblockstate1.getBlock().isLeaves(iblockstate1, world, blockpos1))
                {
                    iblockstate1.getBlock().beginLeavesDecay(iblockstate1, world, blockpos1);
                }
            }
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos)
    {
        return true;
    }
}
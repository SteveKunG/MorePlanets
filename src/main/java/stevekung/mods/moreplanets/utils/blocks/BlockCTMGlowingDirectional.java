package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockCTMGlowingDirectional extends BlockDirectionalMP
{
    public BlockCTMGlowingDirectional(String name, Material material)
    {
        super(material);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.FACING_ALL, EnumFacing.UP));
        this.setUnlocalizedName(name);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(BlockStateProperty.FACING_ALL, facing);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }
}
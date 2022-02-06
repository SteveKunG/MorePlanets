package stevekung.mods.moreplanets.planets.chalos.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidFiniteMP;
import stevekung.mods.moreplanets.utils.blocks.material.MaterialsBase;

public class BlockGaseousCheeseMilk extends BlockFluidFiniteMP
{
    public BlockGaseousCheeseMilk(String name)
    {
        super(MPBlocks.GASEOUS_CHEESE_MILK, MaterialsBase.GAS);
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockFluidBase.LEVEL, 7));
        this.setLightOpacity(0);
        this.setTranslationKey(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        world.setBlockState(pos, this.getDefaultState().withProperty(BlockFluidBase.LEVEL, 7), 3);
    }
}
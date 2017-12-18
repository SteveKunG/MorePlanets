package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.moreplanets.util.blocks.BlockFluidFiniteMP;
import stevekung.mods.moreplanets.util.blocks.material.MaterialsBase;

public class BlockGasCheeseOfMilk extends BlockFluidFiniteMP
{
    public BlockGasCheeseOfMilk(String name)
    {
        super(ChalosBlocks.CHEESE_OF_MILK_GAS, MaterialsBase.GAS);
        this.setRenderLayer(EnumWorldBlockLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        world.setBlockState(pos, this.getDefaultState().withProperty(BlockFluidBase.LEVEL, 7), 3);
    }

    @Override
    public String getName()
    {
        return "cheese_of_milk_gas";
    }
}
package stevekung.mods.moreplanets.utils.client.renderer;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.blocks.BlockStemMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

@SideOnly(Side.CLIENT)
public class StateMapperType extends StateMapperBase
{
    private final String type;

    public StateMapperType(String type)
    {
        this.type = type;
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state)
    {
        if (this.type.equals("stem"))
        {
            Map<IProperty<?>, Comparable<?>> map = new LinkedHashMap<>(state.getProperties());

            if (state.getValue(BlockStemMP.FACING) != EnumFacing.UP)
            {
                map.remove(BlockStateProperty.AGE_7);
            }
            return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()), this.getPropertyString(map));
        }
        return null;
    }
}
package stevekung.mods.moreplanets.utils.client.renderer;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;

@SideOnly(Side.CLIENT)
public class StateMapperCTM extends StateMapperBase
{
    private IProperty<?> toRemove;

    public StateMapperCTM() {}

    public StateMapperCTM(IProperty<?> toRemove)
    {
        this.toRemove = toRemove;
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state)
    {
        Map<IProperty<?>, Comparable<?>> map = new LinkedHashMap<>(state.getProperties());
        String ctm = CompatibilityManagerMP.isCTMLoaded ? "_glow" : "";

        if (this.toRemove != null)
        {
            map.remove(this.toRemove);
            return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()) + ctm, this.getPropertyString(map));
        }
        else
        {
            return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()) + ctm, this.getPropertyString(state.getProperties()));
        }
    }
}
package stevekung.mods.moreplanets.client.renderer;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.blocks.BlockCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;

@SideOnly(Side.CLIENT)
public class StateMapperCTM extends StateMapperBase
{
    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state)
    {
        Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
        boolean ignoreState = state.getBlock() == DionaBlocks.CRASHED_ALIEN_PROBE;

        if (ignoreState)
        {
            map.remove(BlockCrashedAlienProbe.HAS_ALIEN);
        }
        String ctm = CompatibilityManagerMP.isCTMLoaded() ? "_glow" : "";
        return new ModelResourceLocation(Block.REGISTRY.getNameForObject(state.getBlock()) + ctm, this.getPropertyString(ignoreState ? map : state.getProperties()));
    }
}
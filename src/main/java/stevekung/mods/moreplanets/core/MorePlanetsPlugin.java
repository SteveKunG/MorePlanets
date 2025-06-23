package stevekung.mods.moreplanets.core;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;

@IFMLLoadingPlugin.Name("MorePlanets Core")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class MorePlanetsPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public List<String> getMixinConfigs()
    {
        return ImmutableList.of("mixins.more_planets.json");
    }
}
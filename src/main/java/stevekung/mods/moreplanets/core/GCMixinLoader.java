package stevekung.mods.moreplanets.core;

import java.util.List;

import com.google.common.collect.Lists;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GCMixinLoader implements ILateMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return Lists.newArrayList("mixins.more_planets_gc.json", "mixins.more_planets_gs.json");
    }
}
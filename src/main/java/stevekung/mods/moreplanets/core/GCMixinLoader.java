package stevekung.mods.moreplanets.core;

import java.util.Collections;
import java.util.List;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GCMixinLoader implements ILateMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return Collections.singletonList("mixins.more_planets_gc.json");
    }
}
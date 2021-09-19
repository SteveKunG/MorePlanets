package stevekung.mods.moreplanets.core;

import org.spongepowered.asm.mixin.Mixins;

import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class GCMixinLoader
{
    static
    {
        Mixins.addConfiguration("mixins.more_planets_gc.json");
    }
}
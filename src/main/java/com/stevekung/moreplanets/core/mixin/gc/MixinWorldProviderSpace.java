package com.stevekung.moreplanets.core.mixin.gc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.moreplanets.utils.SurvivalPlanetUtils;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;

@Mixin(WorldProviderSpace.class)
public class MixinWorldProviderSpace
{
    @Inject(method = "netherPortalsOperational", cancellable = true, remap = false, at = @At("HEAD"))
    private void moreplanets$enablePortal(CallbackInfoReturnable<Boolean> info)
    {
        if (SurvivalPlanetUtils.hasSurvivalPlanetData() || SurvivalPlanetUtils.hasSurvivalPlanetDataForServer())
        {
            info.setReturnValue(true);
        }
    }
}
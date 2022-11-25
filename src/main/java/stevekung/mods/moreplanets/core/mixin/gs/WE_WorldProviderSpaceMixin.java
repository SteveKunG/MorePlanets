package stevekung.mods.moreplanets.core.mixin.gs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

@Pseudo
@Mixin(targets = "asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace")
public class WE_WorldProviderSpaceMixin
{
    @Inject(method = "netherPortalsOperational", cancellable = true, remap = false, at = @At("HEAD"))
    private void moreplanets$enablePortal(CallbackInfoReturnable<Boolean> info)
    {
        if (WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            info.setReturnValue(true);
        }
    }
}
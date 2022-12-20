package stevekung.mods.moreplanets.core.mixin.gs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import stevekung.mods.moreplanets.utils.SurvivalPlanetUtils;

@Pseudo
@Mixin(targets = "asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace")
public class MixinWE_WorldProviderSpace
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
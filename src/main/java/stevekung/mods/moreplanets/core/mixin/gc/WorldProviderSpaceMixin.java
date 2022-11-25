package stevekung.mods.moreplanets.core.mixin.gc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

@Mixin(WorldProviderSpace.class)
public class WorldProviderSpaceMixin
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
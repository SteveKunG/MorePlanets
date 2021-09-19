package stevekung.mods.moreplanets.core.mixin.client;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.init.MPPlanets;

@Mixin(value = GuiCelestialSelection.class, remap = false)
public class GuiCelestialSelectionMixin
{
    @Shadow(remap = false)
    List<CelestialBody> bodiesToRender;

    @Inject(method = "initGui", at = @At("TAIL"))
    private void initGui(CallbackInfo info)
    {
        if (WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            this.bodiesToRender.removeIf(body -> body == MPPlanets.SPACE_NETHER);
        }
    }

    @Inject(method = "getChildren", remap = false, at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void getChildren(Object object, CallbackInfoReturnable<List<CelestialBody>> info, List<CelestialBody> bodyList)
    {
        if (WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            bodyList.removeIf(body -> body == MPPlanets.SPACE_NETHER);
        }
    }
}
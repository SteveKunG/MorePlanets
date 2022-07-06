package stevekung.mods.moreplanets.core.mixin.client.gc;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import stevekung.mods.moreplanets.init.MPPlanets;

@Pseudo
@Mixin(targets = "asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection")
public class NewGuiCelestialSelectionMixin extends GuiCelestialSelection
{
    NewGuiCelestialSelectionMixin()
    {
        super(false, null, false);
    }

    @Inject(method = "refreshBodies", remap = false, at = @At("TAIL"))
    private void moreplanets$removeSpaceNether(CallbackInfo info)
    {
        this.bodiesToRender.removeIf(body -> body == MPPlanets.SPACE_NETHER);
    }

    @Inject(method = "getChildren", remap = false, at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void moreplanets$removeSpaceNetherChildren(Object object, int start, int size, CallbackInfoReturnable<List<CelestialBody>> info, List<CelestialBody> bodyList)
    {
        bodyList.removeIf(body -> body == MPPlanets.SPACE_NETHER);
    }
}
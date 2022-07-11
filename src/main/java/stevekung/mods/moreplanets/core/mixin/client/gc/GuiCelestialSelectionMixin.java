package stevekung.mods.moreplanets.core.mixin.client.gc;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import stevekung.mods.moreplanets.utils.CelestialRegistryUtils;

@Mixin(GuiCelestialSelection.class)
public class GuiCelestialSelectionMixin
{
    @Redirect(method = "initGui", at = @At(value = "INVOKE", target = "java/util/Map.values()Ljava/util/Collection;", remap = false, ordinal = 1))
    private Collection<Planet> moreplanets$removeSpaceNetherInit(Map<String, Planet> collection)
    {
        return CelestialRegistryUtils.removeSpaceNether(collection.values());
    }

    @Redirect(method = "getChildren", remap = false, at = @At(value = "INVOKE", target = "micdoodle8/mods/galacticraft/api/galaxies/GalaxyRegistry.getPlanetsForSolarSystem(Lmicdoodle8/mods/galacticraft/api/galaxies/SolarSystem;)Ljava/util/List;", remap = false))
    private List<Planet> moreplanets$removeSpaceNetherChildren(SolarSystem solarSystem)
    {
        return CelestialRegistryUtils.removeSpaceNether(GalaxyRegistry.getPlanetsForSolarSystem(solarSystem));
    }
}
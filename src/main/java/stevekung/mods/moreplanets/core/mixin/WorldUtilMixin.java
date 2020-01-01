package stevekung.mods.moreplanets.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.planets.chalos.dimension.WorldProviderChalos;

@Mixin(value = WorldUtil.class, remap = false)
public class WorldUtilMixin
{
    @Inject(method = "getWorldColor(Lnet/minecraft/world/World;)Lmicdoodle8/mods/galacticraft/api/vector/Vector3;", cancellable = true, at = @At("HEAD"), remap = false)
    private static void getPlanetWorldColor(World world, CallbackInfoReturnable<Vector3> info)
    {
        if (world.provider instanceof WorldProviderChalos)
        {
            info.setReturnValue(new Vector3(0.9F, 0.85F, 0.65F));
        }
        else if (world.provider instanceof WorldProviderKoentus)
        {
            info.setReturnValue(new Vector3(0.7F, 0.85F, 1.0F));
        }
    }
}
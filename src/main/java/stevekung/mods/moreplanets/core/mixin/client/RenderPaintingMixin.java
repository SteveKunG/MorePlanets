package stevekung.mods.moreplanets.core.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.utils.PaintingMP;

@Mixin(RenderPainting.class)
public class RenderPaintingMixin
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/painting/paintings_more_planets.png");

    @Inject(method = "getEntityTexture", cancellable = true, at = @At("HEAD"))
    private void moreplanets$getPaintingTexture(EntityPainting entity, CallbackInfoReturnable<ResourceLocation> info)
    {
        if (entity.art == PaintingMP.RONG)
        {
            info.setReturnValue(TEXTURE);
        }
    }
}
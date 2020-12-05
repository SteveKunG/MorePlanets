package stevekung.mods.moreplanets.core.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

@Mixin(RenderWolf.class)
public class RenderWolfMixin
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/dog/rong.png");

    @Inject(method = "getEntityTexture(Lnet/minecraft/entity/passive/EntityWolf;)Lnet/minecraft/util/ResourceLocation;", cancellable = true, at = @At("HEAD"))
    private void getEntityTexture(EntityWolf entity, CallbackInfoReturnable<ResourceLocation> info)
    {
        if (entity.isTamed() && entity.hasCustomName() && entity.getCustomNameTag().equals("Rong"))
        {
            info.setReturnValue(TEXTURE);
        }
    }
}
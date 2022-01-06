package stevekung.mods.moreplanets.core.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import stevekung.mods.moreplanets.utils.WorldColorUtils;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin
{
    @Shadow
    @Final
    Minecraft mc;

    @ModifyVariable(method = "updateLightmap", at = @At(value = "CONSTANT", args = "intValue=255"), name = "f8")
    private float moreplanets$addWorldRed(float f8)
    {
        return f8 * (float)WorldColorUtils.getWorldColor(this.mc.world).x;
    }

    @ModifyVariable(method = "updateLightmap", at = @At(value = "CONSTANT", args = "intValue=255"), name = "f9")
    private float moreplanets$addWorldGreen(float f9)
    {
        return f9 * (float)WorldColorUtils.getWorldColor(this.mc.world).y;
    }

    @ModifyVariable(method = "updateLightmap", at = @At(value = "CONSTANT", args = "intValue=255"), name = "f10")
    private float moreplanets$addWorldBlue(float f10)
    {
        return f10 * (float)WorldColorUtils.getWorldColor(this.mc.world).z;
    }
}
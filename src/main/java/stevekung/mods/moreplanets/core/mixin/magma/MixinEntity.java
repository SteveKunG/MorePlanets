package stevekung.mods.moreplanets.core.mixin.magma;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.entity.Entity;
import stevekung.mods.moreplanets.utils.SurvivalPlanetUtils;

@Mixin(Entity.class)
public class MixinEntity
{
    @ModifyConstant(method = "postTick", remap = false, constant = @Constant(intValue = 0), slice = @Slice(from = @At(value = "INVOKE", target = "net/minecraft/world/DimensionType.func_186068_a()I", remap = false), to = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.func_184204_a(I)Lnet/minecraft/entity/Entity;", remap = false)), require = 0)
    private int moreplanets$changeDimForMagmaServer(int defaultDim)
    {
        return SurvivalPlanetUtils.getSurvivalPlanetDimension(defaultDim);
    }
}
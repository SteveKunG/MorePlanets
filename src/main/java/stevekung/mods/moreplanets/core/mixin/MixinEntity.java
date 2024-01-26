package stevekung.mods.moreplanets.core.mixin;

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
    @ModifyConstant(method = "onEntityUpdate", constant = @Constant(), slice = @Slice(from = @At(value = "INVOKE", target = "net/minecraft/world/DimensionType.getId()I"), to = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.changeDimension(I)Lnet/minecraft/entity/Entity;")), require = 0)
    private int moreplanets$changeDim(int defaultDim)
    {
        return SurvivalPlanetUtils.getSurvivalPlanetDimension(defaultDim);
    }
}
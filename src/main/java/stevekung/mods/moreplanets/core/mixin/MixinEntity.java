package stevekung.mods.moreplanets.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.entity.Entity;
import stevekung.mods.moreplanets.core.config.ConfigManagerServerMP;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.utils.SurvivalPlanetUtils;

@Mixin(Entity.class)
public class MixinEntity
{
    @ModifyConstant(method = "onEntityUpdate", constant = @Constant(intValue = 0), slice = @Slice(from = @At(value = "INVOKE", target = "net/minecraft/world/DimensionType.getId()I"), to = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.changeDimension(I)Lnet/minecraft/entity/Entity;")))
    private int moreplanets$changeDim(int defaultDim)
    {
        if (SurvivalPlanetUtils.hasSurvivalPlanetData())
        {
            return SurvivalPlanetUtils.getSurvivalPlanetProvider(WorldTickEventHandler.survivalPlanetData.survivalPlanetName).getDimension();
        }
        else if (SurvivalPlanetUtils.hasSurvivalPlanetDataForServer())
        {
            String dimensionName = ConfigManagerServerMP.survivalPlanetDimensionName.replace("\"", "");
            return SurvivalPlanetUtils.getSurvivalPlanetProvider(dimensionName).getDimension();
        }
        return defaultDim;
    }
}
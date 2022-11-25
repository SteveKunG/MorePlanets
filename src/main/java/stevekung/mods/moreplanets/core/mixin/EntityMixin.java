package stevekung.mods.moreplanets.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.Entity;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

@Mixin(Entity.class)
public class EntityMixin
{
    @ModifyConstant(method = "onEntityUpdate", constant = @Constant(intValue = 0), slice = @Slice(from = @At(value = "INVOKE", target = "net/minecraft/world/DimensionType.getId()I"), to = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.changeDimension(I)Lnet/minecraft/entity/Entity;")))
    private int moreplanets$changeDim(int defaultDim)
    {
        if (WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
        {
            return WorldUtil.getProviderForNameServer(WorldTickEventHandler.survivalPlanetData.survivalPlanetName).getDimension();
        }
        return defaultDim;
    }
}
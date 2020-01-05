package stevekung.mods.moreplanets.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.core.event.EntityEventHandler;
import stevekung.mods.moreplanets.entity.IInfectedPurlonite;
import stevekung.mods.moreplanets.init.MPPotions;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin implements IInfectedPurlonite
{
    private final EntityLivingBase that = (EntityLivingBase) (Object) this;

    @Inject(method = "updatePotionMetadata()V", at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.setInvisible(Z)V", shift = At.Shift.AFTER, ordinal = 0))
    private void updatePotionMetadataPre(CallbackInfo info)
    {
        this.setInfectedPurlonite(false);
    }

    @Inject(method = "updatePotionMetadata()V", at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.setInvisible(Z)V", shift = At.Shift.BEFORE, ordinal = 1))
    private void updatePotionMetadataPost(CallbackInfo info)
    {
        this.setInfectedPurlonite(this.that.isPotionActive(MPPotions.INFECTED_PURLONITE));
    }

    @Override
    public boolean isInfectedPurlonite()
    {
        return this.that.getDataManager().get(EntityEventHandler.INFECTED_PURLONITE);
    }

    @Override
    public void setInfectedPurlonite(boolean infected)
    {
        this.that.getDataManager().set(EntityEventHandler.INFECTED_PURLONITE, infected);
    }
}
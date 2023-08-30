package stevekung.mods.moreplanets.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import stevekung.mods.moreplanets.entity.IInfectedPurlonite;
import stevekung.mods.moreplanets.init.MPPotions;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity implements IInfectedPurlonite
{
    private static final DataParameter<Boolean> INFECTED_PURLONITE = new DataParameter<>(252, DataSerializers.BOOLEAN);

    MixinEntityLivingBase()
    {
        super(null);
    }

    @Inject(method = "entityInit", at = @At("TAIL"))
    private void moreplanets$addInfectedPurloniteData(CallbackInfo info)
    {
        this.dataManager.register(INFECTED_PURLONITE, false);
    }

    @Inject(method = "updatePotionMetadata", at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.setInvisible(Z)V", shift = At.Shift.AFTER, ordinal = 0))
    private void moreplanets$updateInfectedPurlonitePre(CallbackInfo info)
    {
        this.setInfectedPurlonite(false);
    }

    @Inject(method = "updatePotionMetadata", at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.setInvisible(Z)V", shift = At.Shift.BEFORE, ordinal = 1))
    private void moreplanets$updateInfectedPurlonitePost(CallbackInfo info)
    {
        this.setInfectedPurlonite(((EntityLivingBase)(Object)this).isPotionActive(MPPotions.INFECTED_PURLONITE));
    }

    @Override
    public boolean isInfectedPurlonite()
    {
        if (INFECTED_PURLONITE == null)
        {
            return false;
        }
        return this.dataManager.get(INFECTED_PURLONITE);
    }

    @Override
    public void setInfectedPurlonite(boolean infected)
    {
        this.dataManager.set(INFECTED_PURLONITE, infected);
    }
}

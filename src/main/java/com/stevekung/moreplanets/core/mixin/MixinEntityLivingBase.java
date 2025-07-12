package com.stevekung.moreplanets.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.stevekung.moreplanets.core.config.ConfigManagerMP;
import com.stevekung.moreplanets.entity.IInfectedPurlonite;
import com.stevekung.moreplanets.init.MPPotions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity implements IInfectedPurlonite
{
    @Unique
    private static final DataParameter<Boolean> INFECTED_PURLONITE = new DataParameter<>(ConfigManagerMP.moreplanets_other.infectedPurloniteDataId, DataSerializers.BOOLEAN);

    MixinEntityLivingBase()
    {
        super(null);
    }

    @Inject(method = "entityInit", at = @At("TAIL"))
    private void moreplanets$addInfectedPurloniteData(CallbackInfo info)
    {
        this.dataManager.register(INFECTED_PURLONITE, false);
    }

    @Inject(method = "updatePotionMetadata", at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.setInvisible(Z)V", ordinal = 0))
    private void moreplanets$updateInfectedPurlonitePre(CallbackInfo info)
    {
        this.moreplanets$setInfectedPurlonite(false);
    }

    @Inject(method = "updatePotionMetadata", at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.setInvisible(Z)V", ordinal = 1))
    private void moreplanets$updateInfectedPurlonitePost(CallbackInfo info)
    {
        this.moreplanets$setInfectedPurlonite(((EntityLivingBase) (Object) this).isPotionActive(MPPotions.INFECTED_PURLONITE));
    }

    @Override
    public boolean moreplanets$isInfectedPurlonite()
    {
        // Rare case of infected purlonite data cannot be created
        if (INFECTED_PURLONITE == null)
        {
            return false;
        }
        return this.dataManager.get(INFECTED_PURLONITE);
    }

    @Override
    public void moreplanets$setInfectedPurlonite(boolean infected)
    {
        this.dataManager.set(INFECTED_PURLONITE, infected);
    }
}
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
public abstract class EntityLivingBaseMixin extends Entity implements IInfectedPurlonite
{
    private static final DataParameter<Boolean> INFECTED_PURLONITE = new DataParameter<>(122, DataSerializers.BOOLEAN);
    private final EntityLivingBase that = (EntityLivingBase) (Object) this;

    private EntityLivingBaseMixin()
    {
        super(null);
    }

    @Inject(method = "entityInit()V", at = @At("RETURN"))
    private void entityInit(CallbackInfo info)
    {
        this.dataManager.register(INFECTED_PURLONITE, false);
    }

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
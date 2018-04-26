package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelShlimeFur;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity.RenderShlime;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityShlime;
import stevekung.mods.stevekunglib.utils.CachedEnum;

@SideOnly(Side.CLIENT)
public class LayerShlimeWool implements LayerRenderer<EntityShlime>
{
    private RenderShlime render;
    private ModelShlimeFur sheepModel = new ModelShlimeFur();

    public LayerShlimeWool(RenderShlime render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityShlime entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entity.getSheared() && !entity.isInvisible())
        {
            this.render.bindTexture(new ResourceLocation("moreplanets:textures/entity/shlime_fur.png"));

            if (entity.hasCustomName() && "jeb_".equals(entity.getCustomNameTag()))
            {
                int i = entity.ticksExisted / 25 + entity.getEntityId();
                int j = CachedEnum.dyeColorValues.length;
                int k = i % j;
                int l = (i + 1) % j;
                float f = (entity.ticksExisted % 25 + partialTicks) / 25.0F;
                float[] afloat1 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(k));
                float[] afloat2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(l));
                GlStateManager.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
            }
            else
            {
                float[] afloat = EntitySheep.getDyeRgb(entity.getFleeceColor());
                GlStateManager.color(afloat[0], afloat[1], afloat[2]);
            }
            this.sheepModel.setModelAttributes(this.render.getMainModel());
            this.sheepModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
            this.sheepModel.renderWool();
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            this.sheepModel.renderBase();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
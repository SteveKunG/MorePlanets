package stevekung.mods.moreplanets.planets.chalos.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCheeseCubeEyeBoss extends ModelBase
{
    private final ModelRenderer body1;
    private final ModelRenderer body2;
    private final ModelRenderer body3;
    private final ModelRenderer body4;
    private final ModelRenderer eye;

    public ModelCheeseCubeEyeBoss()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.addBox(-8F, -8F, -8F, 16, 16, 16, 0F);
        this.body1.setRotationPoint(0F, 16F, 0F);
        this.body2 = new ModelRenderer(this, 0, 0);
        this.body2.addBox(-8F, -8F, -8F, 16, 16, 16, 0F);
        this.body2.setRotationPoint(0F, 16F, 0F);
        this.body3 = new ModelRenderer(this, 0, 0);
        this.body3.addBox(-8F, -8F, -8F, 16, 16, 16, 0F);
        this.body3.setRotationPoint(0F, 16F, 0F);
        this.body4 = new ModelRenderer(this, 0, 0);
        this.body4.addBox(-8F, -8F, -8F, 16, 16, 16, 0F);
        this.body4.setRotationPoint(0F, 16F, 0F);
        this.eye = new ModelRenderer(this, 100, 0);
        this.eye.addBox(0F, 0F, 0F, 10, 6, 1, 0F);
        this.eye.setRotationPoint(-5F, 13F, -9F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.updateRotation(entity, ageInTicks);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.body1.render(scale);
        this.body2.render(scale);
        this.body3.render(scale);
        this.body4.render(scale);
        this.eye.render(scale);
        GlStateManager.disableBlend();
    }

    private void updateRotation(Entity entity, float ageInTicks)
    {
        float partialTicks = entity.ticksExisted + ageInTicks;
        float z = (float)Math.PI * 0.05F;
        float z1 = (float)Math.PI * -0.05F;
        this.body1.offsetZ = -0.025F;
        this.body3.offsetZ = 0.025F;
        this.body1.rotateAngleZ = partialTicks * z + (float) (Math.PI / 8) * 1.0F;
        this.body2.rotateAngleZ = -partialTicks * z + (float) (Math.PI / 8) * 1.0F;
        this.body3.rotateAngleZ = partialTicks * z1 + (float) (Math.PI / 8) * 0.5F;
        this.body4.rotateAngleZ = -partialTicks * z1 + (float) (Math.PI / 8) * 0.5F;
    }
}
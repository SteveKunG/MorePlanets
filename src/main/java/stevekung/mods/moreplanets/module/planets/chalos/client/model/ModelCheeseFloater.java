package stevekung.mods.moreplanets.module.planets.chalos.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCheeseFloater extends ModelBase
{
    private ModelRenderer body1;
    private ModelRenderer body2;
    private ModelRenderer body3;
    private ModelRenderer body4;
    private ModelRenderer eye1;
    private ModelRenderer eye2;
    private ModelRenderer eye3;
    private ModelRenderer eye4;

    public ModelCheeseFloater()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body1 = new ModelRenderer(this, 0, 48);
        this.body1.addBox(-6F, -2F, -6F, 12, 4, 12);
        this.body1.setRotationPoint(0F, 0F, 0F);
        this.body2 = new ModelRenderer(this, 0, 32);
        this.body2.addBox(-6F, -2F, -6F, 12, 4, 12);
        this.body2.setRotationPoint(0F, 16F, 0F);
        this.body3 = new ModelRenderer(this, 0, 0);
        this.body3.addBox(-4F, -1F, -4F, 8, 2, 8);
        this.body3.setRotationPoint(0F, 3F, 0F);
        this.body4 = new ModelRenderer(this, 0, 0);
        this.body4.addBox(-4F, -1F, -4F, 8, 2, 8);
        this.body4.setRotationPoint(0F, 13F, 0F);
        this.eye1 = new ModelRenderer(this, 0, 16);
        this.eye1.addBox(-2.5F, -1.5F, -7F, 5, 3, 1);
        this.eye1.setRotationPoint(0F, 7.5F, 0F);
        this.eye2 = new ModelRenderer(this, 0, 16);
        this.eye2.addBox(-2.5F, -1.5F, 6F, 5, 3, 1);
        this.eye2.setRotationPoint(0F, 7.5F, 0F);
        this.eye3 = new ModelRenderer(this, 0, 24);
        this.eye3.addBox(-7F, -1.5F, -2.5F, 1, 3, 5);
        this.eye3.setRotationPoint(0F, 7.5F, 0F);
        this.eye4 = new ModelRenderer(this, 0, 24);
        this.eye4.addBox(6F, -1.5F, -2.5F, 1, 3, 5);
        this.eye4.setRotationPoint(0F, 7.5F, 0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.updateRotation(entity);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        this.body1.render(scale);
        this.body2.render(scale);
        this.body3.render(scale);
        this.body4.render(scale);
        this.eye1.render(scale);
        this.eye2.render(scale);
        this.eye3.render(scale);
        this.eye4.render(scale);
        GlStateManager.disableBlend();
    }

    private void updateRotation(Entity entity)
    {
        float y = 0.01F * (entity.getEntityId() % 5) + 0.15F;
        float y1 = 0.01F * (entity.getEntityId() % 5) + 0.25F;
        float y2 = 0.01F * (entity.getEntityId() % 3) + 0.025F;
        this.body1.rotateAngleY = entity.ticksExisted * y + (float) (Math.PI / 8) * 2;
        this.body2.rotateAngleY = -entity.ticksExisted * y + (float) (Math.PI / 8) * 2;
        this.body3.rotateAngleY = -entity.ticksExisted * y1 + (float) (Math.PI / 8) * 2;
        this.body4.rotateAngleY = entity.ticksExisted * y1 + (float) (Math.PI / 8) * 2;
        this.eye1.rotateAngleY = entity.ticksExisted * y2 + (float) (Math.PI / 8) * 2;
        this.eye2.rotateAngleY = entity.ticksExisted * y2 + (float) (Math.PI / 8) * 2;
        this.eye3.rotateAngleY = entity.ticksExisted * y2 + (float) (Math.PI / 8) * 2;
        this.eye4.rotateAngleY = entity.ticksExisted * y2 + (float) (Math.PI / 8) * 2;
    }
}
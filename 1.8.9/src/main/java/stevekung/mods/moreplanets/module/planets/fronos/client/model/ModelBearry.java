package stevekung.mods.moreplanets.module.planets.fronos.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.module.planets.fronos.entity.EntityBearry;

public class ModelBearry extends ModelBase
{
    private ModelRenderer body;
    private ModelRenderer leg1;
    private ModelRenderer leg2;
    private ModelRenderer hand1;
    private ModelRenderer hand2;
    private ModelRenderer breach;

    public ModelBearry()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0F, 0F, -4F, 10, 10, 10, 0F);
        this.body.setRotationPoint(-5F, 10F, -1F);
        this.body.mirror = true;
        this.leg1 = new ModelRenderer(this, 60, 0);
        this.leg1.addBox(-1F, 0F, -1F, 3, 4, 3, 0F);
        this.leg1.setRotationPoint(-3F, 20F, -1F);
        this.leg1.mirror = true;
        this.leg2 = new ModelRenderer(this, 60, 0);
        this.leg2.addBox(-1F, 0F, -1F, 3, 4, 3, 0F);
        this.leg2.setRotationPoint(2F, 20F, -1F);
        this.leg2.mirror = true;
        this.hand1 = new ModelRenderer(this, 60, 10);
        this.hand1.addBox(0F, 0F, -0.5F, 1, 4, 1, 0F);
        this.hand1.setRotationPoint(4.5F, 15F, 0F);
        this.hand1.rotateAngleZ = -0.7853982F;
        this.hand1.mirror = true;
        this.hand2 = new ModelRenderer(this, 60, 10);
        this.hand2.addBox(-1F, 0F, -0.5F, 1, 4, 1, 0F);
        this.hand2.setRotationPoint(-4.5F, 15F, 0F);
        this.hand2.rotateAngleZ = 0.7853982F;
        this.hand2.mirror = true;
        this.breach = new ModelRenderer(this, 0, 30);
        this.breach.addBox(0F, 0F, 0F, 2, 6, 2, 0F);
        this.breach.setRotationPoint(-1F, 4F, -1F);
        this.breach.mirror = true;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks)
    {
        EntityBearry bearry = (EntityBearry)entity;

        if (bearry.isSitting())
        {
            this.body.setRotationPoint(-5F, 14.1F, -1F);
            this.hand1.setRotationPoint(4.5F, 19F, 0F);
            this.hand2.setRotationPoint(-4.5F, 19F, 0F);
            this.breach.setRotationPoint(-1F, 8F, -1F);
        }
        else
        {
            this.body.setRotationPoint(-5F, 10F, -1F);
            this.hand1.setRotationPoint(4.5F, 15F, 0F);
            this.hand2.setRotationPoint(-4.5F, 15F, 0F);
            this.breach.setRotationPoint(-1F, 4F, -1F);
        }
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.hand1.render(scale);
            this.hand2.render(scale);
            this.breach.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.breach.render(scale);
            this.hand1.render(scale);
            this.hand2.render(scale);
        }
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.85F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.85F * limbSwingAmount;
    }
}
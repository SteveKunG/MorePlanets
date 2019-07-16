package stevekung.mods.moreplanets.planets.fronos.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.planets.fronos.entity.EntityGiantBlueberry;

public class ModelGiantBlueberry extends ModelBase
{
    private final ModelRenderer body;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer hand1;
    private final ModelRenderer hand2;
    private final ModelRenderer top1;
    private final ModelRenderer top2;
    private final ModelRenderer top3;
    private final ModelRenderer top4;
    private final ModelRenderer top5;

    public ModelGiantBlueberry()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0F, 0F, -4F, 10, 10, 10, 0F);
        this.body.setRotationPoint(-5F, 10F, -1F);

        this.leg1 = new ModelRenderer(this, 60, 0);
        this.leg1.addBox(-1F, 0F, -1F, 3, 4, 3, 0F);
        this.leg1.setRotationPoint(-3F, 20F, -1F);

        this.leg2 = new ModelRenderer(this, 60, 0);
        this.leg2.addBox(-1F, 0F, -1F, 3, 4, 3, 0F);
        this.leg2.setRotationPoint(2F, 20F, -1F);

        this.hand1 = new ModelRenderer(this, 60, 10);
        this.hand1.addBox(0F, 0F, -0.5F, 1, 4, 1, 0F);
        this.hand1.setRotationPoint(4.5F, 15F, 0F);
        this.hand1.rotateAngleZ = -0.7853982F;

        this.hand2 = new ModelRenderer(this, 60, 10);
        this.hand2.addBox(-1F, 0F, -0.5F, 1, 4, 1, 0F);
        this.hand2.setRotationPoint(-4.5F, 15F, 0F);
        this.hand2.rotateAngleZ = 0.7853982F;

        this.top1 = new ModelRenderer(this, 11, 0);
        this.top1.addBox(-1F, -2F, 0F, 2, 2, 1, 0F);
        this.top1.setRotationPoint(0F, 10F, -1.5F);
        this.top1.rotateAngleX = -0.2443461F;

        this.top2 = new ModelRenderer(this, 11, 0);
        this.top2.addBox(-1F, -2F, 0F, 2, 2, 1, 0F);
        this.top2.setRotationPoint(-2F, 10F, 0.5F);
        this.top2.rotateAngleX = -0.1396263F;
        this.top2.rotateAngleY = 1.570796F;

        this.top3 = new ModelRenderer(this, 11, 0);
        this.top3.addBox(-1F, -2F, 0F, 2, 2, 1, 0F);
        this.top3.setRotationPoint(0F, 10F, 1.5F);
        this.top3.rotateAngleX = -0.1396263F;
        this.top3.rotateAngleY = -0.3490658F;

        this.top4 = new ModelRenderer(this, 11, 0);
        this.top4.addBox(-1F, -2F, 0F, 2, 2, 1, 0F);
        this.top4.setRotationPoint(2F, 10F, 1F);
        this.top4.rotateAngleX = -0.1396263F;
        this.top4.rotateAngleY = -1.815142F;

        this.top5 = new ModelRenderer(this, 11, 0);
        this.top5.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.top5.setRotationPoint(-1F, 9.5F, -1F);
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
            this.top1.render(scale);
            this.top2.render(scale);
            this.top3.render(scale);
            this.top4.render(scale);
            this.top5.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.hand1.render(scale);
            this.hand2.render(scale);
            this.top1.render(scale);
            this.top2.render(scale);
            this.top3.render(scale);
            this.top4.render(scale);
            this.top5.render(scale);
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks)
    {
        EntityGiantBlueberry berry = (EntityGiantBlueberry)entity;

        if (berry.isSitting())
        {
            this.body.setRotationPoint(-5F, 14.1F, -1F);
            this.hand1.setRotationPoint(4.5F, 19F, 0F);
            this.hand2.setRotationPoint(-4.5F, 19F, 0F);
            this.top1.setRotationPoint(0F, 14F, -1.5F);
            this.top2.setRotationPoint(-2F, 14F, 0.5F);
            this.top3.setRotationPoint(0F, 14F, 1.5F);
            this.top4.setRotationPoint(2F, 14F, 1F);
            this.top5.setRotationPoint(-1F, 13.5F, -1F);
        }
        else
        {
            this.body.setRotationPoint(-5F, 10F, -1F);
            this.hand1.setRotationPoint(4.5F, 15F, 0F);
            this.hand2.setRotationPoint(-4.5F, 15F, 0F);
            this.top1.setRotationPoint(0F, 10F, -1.5F);
            this.top2.setRotationPoint(-2F, 10F, 0.5F);
            this.top3.setRotationPoint(0F, 10F, 1.5F);
            this.top4.setRotationPoint(2F, 10F, 1F);
            this.top5.setRotationPoint(-1F, 9.5F, -1F);
        }
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.85F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.85F * limbSwingAmount;
    }
}
package stevekung.mods.moreplanets.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityShlime;

public class ModelShlimeFur extends ModelBase
{
    private ModelRenderer body;
    private ModelRenderer bodyside1;
    private ModelRenderer bodyside2;
    private ModelRenderer wool;
    private ModelRenderer vein1;
    private ModelRenderer vein2;
    private ModelRenderer vein3;
    private ModelRenderer vein4;
    private ModelRenderer vein5;
    private ModelRenderer vein6;
    private ModelRenderer vein7;
    private ModelRenderer vein8;
    private ModelRenderer tail1;
    private ModelRenderer head;
    private ModelRenderer tail2;
    private ModelRenderer tail3;
    private ModelRenderer tail4;
    private float tailRotationAngleX;

    public ModelShlimeFur()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body = new ModelRenderer(this, 32, 0);
        this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.body.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bodyside1 = new ModelRenderer(this, 21, 0);
        this.bodyside1.setRotationPoint(-5.0F, 17.0F, 1.0F);
        this.bodyside1.addBox(0.0F, 0.0F, -2.0F, 1, 3, 4, 0.0F);
        this.vein2 = new ModelRenderer(this, 27, 11);
        this.vein2.setRotationPoint(-4.5F, 15.0F, -0.5F);
        this.vein2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.vein4 = new ModelRenderer(this, 27, 11);
        this.vein4.setRotationPoint(3.5F, 15.0F, -0.5F);
        this.vein4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.tail4 = new ModelRenderer(this, 15, 5);
        this.tail4.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.tail4.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.tail4, 0.0F, 0.0F, -0.5585053606381855F);
        this.vein3 = new ModelRenderer(this, 25, 8);
        this.vein3.setRotationPoint(2.0F, 14.5F, -0.5F);
        this.vein3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.head = new ModelRenderer(this, 11, 11);
        this.head.setRotationPoint(0.0F, 15.5F, -4.5F);
        this.head.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.vein5 = new ModelRenderer(this, 27, 11);
        this.vein5.setRotationPoint(0.5F, 15.5F, 3.5F);
        this.vein5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.wool = new ModelRenderer(this, 48, 17);
        this.wool.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.wool.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F);
        this.vein1 = new ModelRenderer(this, 25, 8);
        this.vein1.setRotationPoint(-4.0F, 14.5F, -0.5F);
        this.vein1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.tail1 = new ModelRenderer(this, 11, 0);
        this.tail1.setRotationPoint(0.0F, 21.0F, 3.5F);
        this.tail1.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(this.tail1, -1.0471975803375244F, 0.0F, 0.0F);
        this.vein8 = new ModelRenderer(this, 20, 11);
        this.vein8.setRotationPoint(-1.5333333015441895F, 14.5F, 2.0F);
        this.vein8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.vein6 = new ModelRenderer(this, 27, 11);
        this.vein6.setRotationPoint(-1.5F, 15.5F, 3.5F);
        this.vein6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.vein7 = new ModelRenderer(this, 20, 11);
        this.vein7.setRotationPoint(0.46666666865348816F, 14.5F, 2.0F);
        this.vein7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.bodyside2 = new ModelRenderer(this, 21, 0);
        this.bodyside2.setRotationPoint(4.0F, 17.0F, 1.0F);
        this.bodyside2.addBox(0.0F, 0.0F, -2.0F, 1, 3, 4, 0.0F);
        this.tail3 = new ModelRenderer(this, 15, 5);
        this.tail3.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.tail3.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.tail3, 0.0F, 0.0F, 0.5585053606381855F);
        this.tail2 = new ModelRenderer(this, 11, 0);
        this.tail2.setRotationPoint(0.0F, -1.8F, 0.0F);
        this.tail2.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(this.tail2, 0.13962634015954636F, 0.0F, 0.0F);
        this.tail2.addChild(this.tail4);
        this.tail2.addChild(this.tail3);
        this.tail1.addChild(this.tail2);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks)
    {
        this.tailRotationAngleX = ((EntityShlime)entity).getTailRotationAngleX(partialTicks);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.tail1.rotateAngleX = this.tailRotationAngleX;
    }

    public void renderWool()
    {
        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * 0.0625F, 0.0F);
            this.wool.render(0.0625F);
            GlStateManager.popMatrix();
        }
        else
        {
            this.wool.render(0.0625F);
        }
    }

    public void renderBase()
    {
        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * 0.0625F, 0.0F);
            this.body.render(0.0625F);
            this.bodyside1.render(0.0625F);
            this.bodyside2.render(0.0625F);
            this.vein1.render(0.0625F);
            this.vein2.render(0.0625F);
            this.vein3.render(0.0625F);
            this.vein4.render(0.0625F);
            this.vein5.render(0.0625F);
            this.vein6.render(0.0625F);
            this.vein7.render(0.0625F);
            this.vein8.render(0.0625F);
            this.tail1.render(0.0625F);
            this.head.render(0.0625F);
            GlStateManager.popMatrix();
        }
        else
        {
            this.body.render(0.0625F);
            this.bodyside1.render(0.0625F);
            this.bodyside2.render(0.0625F);
            this.vein1.render(0.0625F);
            this.vein2.render(0.0625F);
            this.vein3.render(0.0625F);
            this.vein4.render(0.0625F);
            this.vein5.render(0.0625F);
            this.vein6.render(0.0625F);
            this.vein7.render(0.0625F);
            this.vein8.render(0.0625F);
            this.tail1.render(0.0625F);
            this.head.render(0.0625F);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
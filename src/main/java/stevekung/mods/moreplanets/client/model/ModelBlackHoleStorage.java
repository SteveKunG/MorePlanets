package stevekung.mods.moreplanets.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBlackHoleStorage extends ModelBase
{
    ModelRenderer base;
    ModelRenderer rod1;
    ModelRenderer rod2;
    ModelRenderer rod3;
    ModelRenderer rod4;
    ModelRenderer top1;
    ModelRenderer top2;
    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer side3;
    ModelRenderer side4;
    ModelRenderer rodt1;
    ModelRenderer rodt2;
    ModelRenderer rodt3;
    ModelRenderer rodt4;

    public ModelBlackHoleStorage()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.base = new ModelRenderer(this, 0, 38);
        this.base.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.base.addBox(-7.0F, 0.0F, -7.0F, 14, 12, 14, 0.0F);
        this.side3 = new ModelRenderer(this, 0, 24);
        this.side3.mirror = true;
        this.side3.setRotationPoint(0.0F, 11.5F, 7.5F);
        this.side3.addBox(-1.5F, 0.0F, 0.0F, 3, 13, 1, 0.0F);
        this.setRotateAngle(this.side3, 0.0F, 3.141592653589793F, 0.0F);
        this.top2 = new ModelRenderer(this, 26, 3);
        this.top2.setRotationPoint(0.0F, 10.25F, 0.0F);
        this.top2.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
        this.rod1 = new ModelRenderer(this, 26, 19);
        this.rod1.mirror = true;
        this.rod1.setRotationPoint(6.0F, 10.5F, 6.0F);
        this.rod1.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(this.rod1, 0.0F, -0.7853981633974483F, 0.0F);
        this.rodt3 = new ModelRenderer(this, 9, 32);
        this.rodt3.setRotationPoint(6.0F, 11.0F, 6.0F);
        this.rodt3.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(this.rodt3, 0.5585053606381855F, 0.7853981633974483F, 0.0F);
        this.side4 = new ModelRenderer(this, 0, 24);
        this.side4.setRotationPoint(7.5F, 11.5F, 0.0F);
        this.side4.addBox(-1.5F, 0.0F, 0.0F, 3, 13, 1, 0.0F);
        this.setRotateAngle(this.side4, 0.0F, -1.5707963267948966F, 0.0F);
        this.top1 = new ModelRenderer(this, 26, 9);
        this.top1.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.top1.addBox(-4.0F, 0.0F, -4.0F, 8, 1, 8, 0.0F);
        this.rodt1 = new ModelRenderer(this, 9, 32);
        this.rodt1.setRotationPoint(-6.0F, 11.0F, -6.0F);
        this.rodt1.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(this.rodt1, -0.5585053606381855F, 0.7853981633974483F, 0.0F);
        this.rod4 = new ModelRenderer(this, 26, 19);
        this.rod4.setRotationPoint(6.0F, 10.5F, -6.0F);
        this.rod4.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(this.rod4, 0.0F, -0.7853981633974483F, 0.0F);
        this.rod2 = new ModelRenderer(this, 26, 19);
        this.rod2.setRotationPoint(-6.0F, 10.5F, 6.0F);
        this.rod2.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(this.rod2, 0.0F, 0.7853981633974483F, 0.0F);
        this.rodt4 = new ModelRenderer(this, 9, 32);
        this.rodt4.setRotationPoint(6.0F, 11.0F, -6.0F);
        this.rodt4.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(this.rodt4, -0.5585053606381855F, -0.7853981633974483F, 0.0F);
        this.rod3 = new ModelRenderer(this, 26, 19);
        this.rod3.mirror = true;
        this.rod3.setRotationPoint(-6.0F, 10.5F, -6.0F);
        this.rod3.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(this.rod3, 0.0F, 0.7853981633974483F, 0.0F);
        this.side2 = new ModelRenderer(this, 0, 24);
        this.side2.setRotationPoint(-7.5F, 11.5F, 0.0F);
        this.side2.addBox(-1.5F, 0.0F, 0.0F, 3, 13, 1, 0.0F);
        this.setRotateAngle(this.side2, 0.0F, 1.5707963267948966F, 0.0F);
        this.rodt2 = new ModelRenderer(this, 9, 32);
        this.rodt2.setRotationPoint(-6.0F, 11.0F, 6.0F);
        this.rodt2.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(this.rodt2, 0.5585053606381855F, -0.7853981633974483F, 0.0F);
        this.side1 = new ModelRenderer(this, 0, 24);
        this.side1.mirror = true;
        this.side1.setRotationPoint(0.0F, 11.5F, -7.5F);
        this.side1.addBox(-1.5F, 0.0F, 0.0F, 3, 13, 1, 0.0F);
    }

    public void renderBase()
    {
        this.base.render(0.0625F);
        this.rod1.render(0.0625F);
        this.rod2.render(0.0625F);
        this.rod3.render(0.0625F);
        this.rod4.render(0.0625F);
        this.side1.render(0.0625F);
        this.side2.render(0.0625F);
        this.side3.render(0.0625F);
        this.side4.render(0.0625F);
    }

    public void renderRod()
    {
        this.rodt1.render(0.0625F);
        this.rodt2.render(0.0625F);
        this.rodt3.render(0.0625F);
        this.rodt4.render(0.0625F);
    }

    public void renderTop()
    {
        this.top1.render(0.0625F);
        this.top2.render(0.0625F);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

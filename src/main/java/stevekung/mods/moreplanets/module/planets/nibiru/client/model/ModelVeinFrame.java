package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelVeinFrame extends ModelBase
{
    ModelRenderer base;
    ModelRenderer eye;
    ModelRenderer rod1;
    ModelRenderer rod2;
    ModelRenderer rod3;
    ModelRenderer rod4;
    ModelRenderer rod5;
    ModelRenderer rod6;
    ModelRenderer rod7;
    ModelRenderer rod8;

    public ModelVeinFrame()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.base = new ModelRenderer(this, 0, 36);
        this.base.addBox(-8F, 0F, -8F, 16, 12, 16);
        this.base.setRotationPoint(0F, 12F, 0F);
        this.eye = new ModelRenderer(this, 0, 27);
        this.eye.addBox(-3F, 0F, -3F, 6, 2, 6);
        this.eye.setRotationPoint(0F, 10F, 0F);
        this.rod1 = new ModelRenderer(this, 0, 12);
        this.rod1.addBox(-1F, -3F, -1F, 2, 3, 2);
        this.rod1.setRotationPoint(4F, 12.5F, -4F);
        this.setRotation(this.rod1, -0.2792527F, -0.7853982F, 0F);
        this.rod2 = new ModelRenderer(this, 0, 12);
        this.rod2.addBox(-1F, -3F, -1F, 2, 3, 2);
        this.rod2.setRotationPoint(4F, 12.5F, 4F);
        this.setRotation(this.rod2, 0.2792527F, 0.7853982F, 0F);
        this.rod3 = new ModelRenderer(this, 0, 12);
        this.rod3.addBox(-1F, -3F, -1F, 2, 3, 2);
        this.rod3.setRotationPoint(-4F, 12.5F, 4F);
        this.setRotation(this.rod3, 0.2792527F, -0.7853982F, 0F);
        this.rod4 = new ModelRenderer(this, 0, 12);
        this.rod4.addBox(-1F, -3F, -1F, 2, 3, 2);
        this.rod4.setRotationPoint(-4F, 12.5F, -4F);
        this.setRotation(this.rod4, -0.2792527F, 0.7853982F, 0F);
        this.rod5 = new ModelRenderer(this, 10, 14);
        this.rod5.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rod5.setRotationPoint(3.5F, 10F, -3.5F);
        this.setRotation(this.rod5, -0.6283185F, -0.7853982F, 0F);
        this.rod6 = new ModelRenderer(this, 10, 14);
        this.rod6.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rod6.setRotationPoint(3.5F, 10F, 3.5F);
        this.setRotation(this.rod6, 0.6283185F, 0.7853982F, 0F);
        this.rod7 = new ModelRenderer(this, 10, 14);
        this.rod7.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rod7.setRotationPoint(-3.5F, 10F, 3.5F);
        this.setRotation(this.rod7, 0.6283185F, -0.7853982F, 0F);
        this.rod8 = new ModelRenderer(this, 10, 14);
        this.rod8.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rod8.setRotationPoint(-3.5F, 10F, -3.5F);
        this.setRotation(this.rod8, -0.6283185F, 0.7853982F, 0F);
    }

    public void renderEye()
    {
        this.eye.render(0.0625F);
    }

    public void renderAll()
    {
        this.base.render(0.0625F);
        this.rod1.render(0.0625F);
        this.rod2.render(0.0625F);
        this.rod3.render(0.0625F);
        this.rod4.render(0.0625F);
        this.rod5.render(0.0625F);
        this.rod6.render(0.0625F);
        this.rod7.render(0.0625F);
        this.rod8.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
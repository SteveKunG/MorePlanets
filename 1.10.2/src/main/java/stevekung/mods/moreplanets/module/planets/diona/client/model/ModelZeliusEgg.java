package stevekung.mods.moreplanets.module.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelZeliusEgg extends ModelBase
{
    ModelRenderer base1;
    ModelRenderer base2;
    ModelRenderer base3;
    ModelRenderer base4;
    ModelRenderer base5;
    ModelRenderer base6;
    ModelRenderer rod1;
    ModelRenderer rod2;
    ModelRenderer rod3;
    ModelRenderer rod4;
    ModelRenderer rod5;
    ModelRenderer rod6;
    ModelRenderer rod7;
    ModelRenderer rod8;

    public ModelZeliusEgg()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.base1 = new ModelRenderer(this, 0, 50);
        this.base1.addBox(-6F, 0F, -6F, 12, 2, 12);
        this.base1.setRotationPoint(0F, 22F, 0F);
        this.base2 = new ModelRenderer(this, 0, 33);
        this.base2.addBox(-7F, 1F, -7F, 14, 3, 14);
        this.base2.setRotationPoint(0F, 18F, 0F);
        this.base3 = new ModelRenderer(this, 0, 15);
        this.base3.addBox(-8F, 0F, -8F, 16, 2, 16);
        this.base3.setRotationPoint(0F, 17F, 0F);
        this.base4 = new ModelRenderer(this, 68, 46);
        this.base4.addBox(-7.5F, 0F, -7.5F, 15, 3, 15);
        this.base4.setRotationPoint(0F, 14F, 0F);
        this.base5 = new ModelRenderer(this, 96, 36);
        this.base5.addBox(-4F, 0F, -4F, 8, 2, 8);
        this.base5.setRotationPoint(0F, 12F, 0F);
        this.base6 = new ModelRenderer(this, 112, 30);
        this.base6.addBox(-2F, 0F, -2F, 4, 2, 4);
        this.base6.setRotationPoint(0F, 10F, 0F);
        this.rod1 = new ModelRenderer(this, 0, 8);
        this.rod1.addBox(-1F, -4F, -1F, 2, 4, 2);
        this.rod1.setRotationPoint(6F, 14.5F, -6F);
        this.setRotation(this.rod1, -0.2617994F, -0.7853982F, 0F);
        this.rod2 = new ModelRenderer(this, 0, 8);
        this.rod2.addBox(-1F, -4F, -1F, 2, 4, 2);
        this.rod2.setRotationPoint(-6F, 14.5F, -6F);
        this.setRotation(this.rod2, -0.2617994F, 0.7853982F, 0F);
        this.rod3 = new ModelRenderer(this, 0, 8);
        this.rod3.addBox(-1F, -4F, -1F, 2, 4, 2);
        this.rod3.setRotationPoint(-6F, 14.5F, 6F);
        this.setRotation(this.rod3, 0.2617994F, -0.7853982F, 0F);
        this.rod4 = new ModelRenderer(this, 0, 8);
        this.rod4.addBox(-1F, -4F, -1F, 2, 4, 2);
        this.rod4.setRotationPoint(6F, 14.5F, 6F);
        this.setRotation(this.rod4, 0.2617994F, 0.7853982F, 0F);
        this.rod5 = new ModelRenderer(this, 0, 0);
        this.rod5.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.rod5.setRotationPoint(-5.5F, 11F, -5.5F);
        this.setRotation(this.rod5, -0.6108652F, 0.7853982F, 0F);
        this.rod6 = new ModelRenderer(this, 0, 0);
        this.rod6.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.rod6.setRotationPoint(-5.5F, 11F, 5.5F);
        this.setRotation(this.rod6, 0.6108652F, -0.7853982F, 0F);
        this.rod7 = new ModelRenderer(this, 0, 0);
        this.rod7.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.rod7.setRotationPoint(5.5F, 11F, -5.5F);
        this.setRotation(this.rod7, -0.6108652F, -0.7853982F, 0F);
        this.rod8 = new ModelRenderer(this, 0, 0);
        this.rod8.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.rod8.setRotationPoint(5.5F, 11F, 5.5F);
        this.setRotation(this.rod8, 0.6108652F, 0.7853982F, 0F);
    }

    public void renderAll()
    {
        this.base1.render(0.0625F);
        this.base2.render(0.0625F);
        this.base3.render(0.0625F);
        this.base4.render(0.0625F);
        this.base5.render(0.0625F);
        this.base6.render(0.0625F);
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
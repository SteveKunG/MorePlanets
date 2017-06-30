package stevekung.mods.moreplanets.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelAlienDefenderBeacon extends ModelBase
{
    ModelRenderer base;
    ModelRenderer light;
    ModelRenderer beam1;
    ModelRenderer beam2;
    ModelRenderer beam3;
    ModelRenderer beam4;
    ModelRenderer decor1;
    ModelRenderer decor2;
    ModelRenderer decor3;
    ModelRenderer decor4;

    public ModelAlienDefenderBeacon()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.decor3 = new ModelRenderer(this, 0, 37);
        this.decor3.setRotationPoint(-6.5F, 21.5F, -6.5F);
        this.decor3.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.decor2 = new ModelRenderer(this, 0, 37);
        this.decor2.setRotationPoint(-6.5F, 21.5F, 6.5F);
        this.decor2.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.beam1 = new ModelRenderer(this, 56, 26);
        this.beam1.setRotationPoint(0.0F, 1.0F, -2.0F);
        this.beam1.addBox(-2.0F, 0.0F, 0.0F, 4, 20, 0, 0.0F);
        this.beam2 = new ModelRenderer(this, 56, 26);
        this.beam2.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.beam2.addBox(-2.0F, 0.0F, 0.0F, 4, 20, 0, 0.0F);
        this.decor1 = new ModelRenderer(this, 0, 37);
        this.decor1.setRotationPoint(6.5F, 21.5F, 6.5F);
        this.decor1.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.beam3 = new ModelRenderer(this, 56, 26);
        this.beam3.setRotationPoint(-2.0F, 1.0F, 0.0F);
        this.beam3.addBox(-2.0F, 0.0F, 0.0F, 4, 20, 0, 0.0F);
        this.setRotateAngle(this.beam3, 0.0F, 1.5707963267948966F, 0.0F);
        this.light = new ModelRenderer(this, 0, 40);
        this.light.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.light.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.beam4 = new ModelRenderer(this, 56, 26);
        this.beam4.setRotationPoint(2.0F, 1.0F, 0.0F);
        this.beam4.addBox(-2.0F, 0.0F, 0.0F, 4, 20, 0, 0.0F);
        this.setRotateAngle(this.beam4, 0.0F, 1.5707963267948966F, 0.0F);
        this.decor4 = new ModelRenderer(this, 0, 37);
        this.decor4.setRotationPoint(6.5F, 21.5F, -6.5F);
        this.decor4.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.base = new ModelRenderer(this, 0, 46);
        this.base.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.base.addBox(-8.0F, 0.0F, -8.0F, 16, 2, 16, 0.0F);
    }

    public void renderBase()
    {
        this.decor3.render(0.0625F);
        this.decor2.render(0.0625F);
        this.decor1.render(0.0625F);
        this.light.render(0.0625F);
        this.decor4.render(0.0625F);
        this.base.render(0.0625F);
    }

    public void renderBeam()
    {
        this.beam1.render(0.0625F);
        this.beam2.render(0.0625F);
        this.beam3.render(0.0625F);
        this.beam4.render(0.0625F);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
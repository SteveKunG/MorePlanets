package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelJuicerEgg extends ModelBase
{
    ModelRenderer core;
    ModelRenderer stoneplate;
    ModelRenderer base1;
    ModelRenderer base2;
    ModelRenderer coreside1;
    ModelRenderer coreside2;
    ModelRenderer coreside3;
    ModelRenderer coreside4;
    ModelRenderer top1;
    ModelRenderer top2;
    ModelRenderer top3;
    ModelRenderer top4;
    ModelRenderer topbase;

    public ModelJuicerEgg()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.core = new ModelRenderer(this, 0, 39);
        this.core.addBox(-6F, 0F, -6F, 12, 13, 12);
        this.core.setRotationPoint(0F, 8F, 0F);
        this.stoneplate = new ModelRenderer(this, 0, 24);
        this.stoneplate.addBox(-6F, 0F, -6F, 12, 1, 12);
        this.stoneplate.setRotationPoint(0F, 23F, 0F);
        this.base1 = new ModelRenderer(this, 0, 16);
        this.base1.addBox(-3F, 0F, -3F, 6, 1, 6);
        this.base1.setRotationPoint(0F, 22F, 0F);
        this.base2 = new ModelRenderer(this, 0, 4);
        this.base2.addBox(-5F, 0F, -5F, 10, 1, 10);
        this.base2.setRotationPoint(0F, 21F, 0F);
        this.coreside1 = new ModelRenderer(this, 50, 45);
        this.coreside1.addBox(0F, -5F, -4F, 1, 11, 8);
        this.coreside1.setRotationPoint(-6.5F, 14F, 0F);
        this.coreside2 = new ModelRenderer(this, 50, 45);
        this.coreside2.addBox(-1F, -5F, -4F, 1, 11, 8);
        this.coreside2.setRotationPoint(6.5F, 14F, 0F);
        this.coreside3 = new ModelRenderer(this, 50, 32);
        this.coreside3.addBox(-4F, -5F, 0F, 8, 11, 1);
        this.coreside3.setRotationPoint(0F, 14F, -6.5F);
        this.coreside4 = new ModelRenderer(this, 50, 32);
        this.coreside4.addBox(-4F, -5F, -1F, 8, 11, 1);
        this.coreside4.setRotationPoint(0F, 14F, 6.5F);
        this.top1 = new ModelRenderer(this, 26, 17);
        this.top1.addBox(-2.5F, -5F, -0.5F, 5, 5, 1);
        this.top1.setRotationPoint(0F, 8.5F, -5F);
        this.setRotation(this.top1, -0.5235988F, 0F, 0F);
        this.top2 = new ModelRenderer(this, 26, 17);
        this.top2.addBox(-2.5F, -5F, -0.5F, 5, 5, 1);
        this.top2.setRotationPoint(5F, 8.5F, 0F);
        this.setRotation(this.top2, -0.5235988F, -1.570796F, 0F);
        this.top3 = new ModelRenderer(this, 26, 17);
        this.top3.addBox(-2.5F, -5F, -0.5F, 5, 5, 1);
        this.top3.setRotationPoint(0F, 8.5F, 5F);
        this.setRotation(this.top3, 0.5235988F, 0F, 0F);
        this.top4 = new ModelRenderer(this, 26, 17);
        this.top4.addBox(-2.5F, -5F, -0.5F, 5, 5, 1);
        this.top4.setRotationPoint(-5F, 8.5F, 0F);
        this.setRotation(this.top4, 0.5235988F, -1.570796F, 0F);
        this.topbase = new ModelRenderer(this, 43, 4);
        this.topbase.addBox(-5F, 0F, -5F, 10, 1, 10);
        this.topbase.setRotationPoint(0F, 7.5F, 0F);
    }

    public void renderAll()
    {
        this.core.render(0.0625F);
        this.stoneplate.render(0.0625F);
        this.base1.render(0.0625F);
        this.base2.render(0.0625F);
        this.coreside1.render(0.0625F);
        this.coreside2.render(0.0625F);
        this.coreside3.render(0.0625F);
        this.coreside4.render(0.0625F);
        this.top1.render(0.0625F);
        this.top2.render(0.0625F);
        this.top3.render(0.0625F);
        this.top4.render(0.0625F);
        this.topbase.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
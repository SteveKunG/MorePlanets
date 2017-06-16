package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelNuclearWasteTank extends ModelBase
{
    ModelRenderer waste;
    ModelRenderer base1;
    ModelRenderer base2;
    ModelRenderer top1;
    ModelRenderer top2;
    ModelRenderer glassrod1;
    ModelRenderer glassrod2;
    ModelRenderer glassrod3;
    ModelRenderer glassrod4;
    ModelRenderer glass1;
    ModelRenderer glass2;
    ModelRenderer glass3;
    ModelRenderer glass4;
    ModelRenderer glassr1;
    ModelRenderer glassr2;
    ModelRenderer glassr3;
    ModelRenderer glassr4;

    public ModelNuclearWasteTank()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.glassrod4 = new ModelRenderer(this, 0, 36);
        this.glassrod4.setRotationPoint(7.0F, -20.0F, -8.0F);
        this.glassrod4.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.top1 = new ModelRenderer(this, 36, 108);
        this.top1.setRotationPoint(0.0F, -20.0F, 0.0F);
        this.top1.addBox(-8.0F, -4.0F, -8.0F, 16, 4, 16, 0.0F);
        this.glass3 = new ModelRenderer(this, 15, 22);
        this.glass3.setRotationPoint(-6.0F, -20.0F, -6.0F);
        this.glass3.addBox(0.0F, 0.0F, 0.0F, 1, 40, 12, 0.0F);
        this.setRotateAngle(this.glass3, 0.0F, 1.5707963267948966F, 0.0F);
        this.base2 = new ModelRenderer(this, 36, 92);
        this.base2.setRotationPoint(0.0F, 18.5F, 0.0F);
        this.base2.addBox(-6.0F, 0.0F, -6.0F, 12, 2, 12, 0.0F);
        this.glass2 = new ModelRenderer(this, 15, 22);
        this.glass2.setRotationPoint(-7.0F, -20.0F, -6.0F);
        this.glass2.addBox(0.0F, 0.0F, 0.0F, 1, 40, 12, 0.0F);
        this.glassr3 = new ModelRenderer(this, 25, 33);
        this.glassr3.setRotationPoint(6.0F, -20.0F, -7.0F);
        this.glassr3.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.glassrod1 = new ModelRenderer(this, 0, 36);
        this.glassrod1.setRotationPoint(-8.0F, -20.0F, -8.0F);
        this.glassrod1.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.glassrod3 = new ModelRenderer(this, 0, 36);
        this.glassrod3.setRotationPoint(7.0F, -20.0F, 7.0F);
        this.glassrod3.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.top2 = new ModelRenderer(this, 36, 77);
        this.top2.setRotationPoint(0.0F, -18.5F, 0.0F);
        this.top2.addBox(-6.0F, -2.0F, -6.0F, 12, 2, 12, 0.0F);
        this.glassr2 = new ModelRenderer(this, 25, 33);
        this.glassr2.setRotationPoint(-7.0F, -20.0F, -7.0F);
        this.glassr2.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.waste = new ModelRenderer(this, 0, 80);
        this.waste.setRotationPoint(0.0F, -20.0F, 0.0F);
        this.waste.addBox(-4.0F, 0.0F, -4.0F, 8, 40, 8, 0.0F);
        this.glass4 = new ModelRenderer(this, 15, 22);
        this.glass4.setRotationPoint(-6.0F, -20.0F, 7.0F);
        this.glass4.addBox(0.0F, 0.0F, 0.0F, 1, 40, 12, 0.0F);
        this.setRotateAngle(this.glass4, 0.0F, 1.5707963267948966F, 0.0F);
        this.base1 = new ModelRenderer(this, 36, 108);
        this.base1.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.base1.addBox(-8.0F, 0.0F, -8.0F, 16, 4, 16, 0.0F);
        this.glassr1 = new ModelRenderer(this, 25, 33);
        this.glassr1.setRotationPoint(-7.0F, -20.0F, 6.0F);
        this.glassr1.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.glassr4 = new ModelRenderer(this, 25, 33);
        this.glassr4.setRotationPoint(6.0F, -20.0F, 6.0F);
        this.glassr4.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.glassrod2 = new ModelRenderer(this, 0, 36);
        this.glassrod2.setRotationPoint(-8.0F, -20.0F, 7.0F);
        this.glassrod2.addBox(0.0F, 0.0F, 0.0F, 1, 40, 1, 0.0F);
        this.glass1 = new ModelRenderer(this, 15, 22);
        this.glass1.setRotationPoint(6.0F, -20.0F, -6.0F);
        this.glass1.addBox(0.0F, 0.0F, 0.0F, 1, 40, 12, 0.0F);
    }

    public void renderWaste()
    {
        this.waste.render(0.0625F);
    }

    public void renderBase()
    {
        this.base1.render(0.0625F);
        this.base2.render(0.0625F);
        this.top1.render(0.0625F);
        this.top2.render(0.0625F);
        this.glassrod1.render(0.0625F);
        this.glassrod2.render(0.0625F);
        this.glassrod3.render(0.0625F);
        this.glassrod4.render(0.0625F);
    }

    public void renderGlass()
    {
        this.glass1.render(0.0625F);
        this.glass2.render(0.0625F);
        this.glass3.render(0.0625F);
        this.glass4.render(0.0625F);
        this.glassr1.render(0.0625F);
        this.glassr2.render(0.0625F);
        this.glassr3.render(0.0625F);
        this.glassr4.render(0.0625F);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
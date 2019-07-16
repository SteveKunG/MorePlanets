package stevekung.mods.moreplanets.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSpaceWarpPad extends ModelBase
{
    private final ModelRenderer base1;
    private final ModelRenderer base2;
    private final ModelRenderer base3;
    private final ModelRenderer rod11;
    private final ModelRenderer rod12;
    private final ModelRenderer rod13;
    private final ModelRenderer rod14;
    private final ModelRenderer rod21;
    private final ModelRenderer rod22;
    private final ModelRenderer rod23;
    private final ModelRenderer rod24;
    private final ModelRenderer rod31;
    private final ModelRenderer rod32;
    private final ModelRenderer rod33;
    private final ModelRenderer rod34;
    private final ModelRenderer rod41;
    private final ModelRenderer rod42;
    private final ModelRenderer rod43;
    private final ModelRenderer rod44;
    private final ModelRenderer rodh1;
    private final ModelRenderer rodh2;
    private final ModelRenderer rodh3;
    private final ModelRenderer rodh4;
    private final ModelRenderer rodh5;
    private final ModelRenderer rodh6;
    private final ModelRenderer rodh7;
    private final ModelRenderer rodh8;
    private final ModelRenderer rodh9;
    private final ModelRenderer rodh10;
    private final ModelRenderer rodh11;
    private final ModelRenderer rodh12;

    public ModelSpaceWarpPad()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;

        this.base1 = new ModelRenderer(this, 0, 21);
        this.base1.addBox(0F, 0F, 0F, 16, 7, 16);
        this.base1.setRotationPoint(-8F, 17.65F, -8F);
        this.setRotation(this.base1, 0F, 0F, 0F);
        this.base2 = new ModelRenderer(this, 0, 44);
        this.base2.addBox(0F, 0F, 0F, 32, 2, 32);
        this.base2.setRotationPoint(-16F, 20F, -16F);
        this.setRotation(this.base2, 0F, 0F, 0F);
        this.base3 = new ModelRenderer(this, 0, 78);
        this.base3.addBox(0F, 0F, 0F, 48, 2, 48);
        this.base3.setRotationPoint(-24F, 22F, -24F);
        this.setRotation(this.base3, 0F, 0F, 0F);
        this.rod11 = new ModelRenderer(this, 240, 0);
        this.rod11.addBox(-2F, 0F, -2F, 4, 8, 4);
        this.rod11.setRotationPoint(-23F, 15.5F, -23F);
        this.setRotation(this.rod11, 0F, 0.7853982F, 0F);
        this.rod12 = new ModelRenderer(this, 244, 12);
        this.rod12.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.rod12.setRotationPoint(-22.5F, 12.5F, -22.5F);
        this.setRotation(this.rod12, -0.1745329F, 0.7853982F, 0F);
        this.rod13 = new ModelRenderer(this, 248, 19);
        this.rod13.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rod13.setRotationPoint(-21.5F, 10.5F, -21.5F);
        this.setRotation(this.rod13, -0.5585054F, 0.7853982F, 0F);
        this.rod14 = new ModelRenderer(this, 252, 24);
        this.rod14.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod14.setRotationPoint(-20.5F, 9.2F, -20.5F);
        this.setRotation(this.rod14, -0.7853982F, 0.7853982F, 0F);
        this.rod21 = new ModelRenderer(this, 240, 0);
        this.rod21.addBox(-2F, 0F, -2F, 4, 8, 4);
        this.rod21.setRotationPoint(23F, 15.5F, 23F);
        this.setRotation(this.rod21, 0F, 0.7853982F, 0F);
        this.rod22 = new ModelRenderer(this, 244, 12);
        this.rod22.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.rod22.setRotationPoint(22.5F, 12.5F, 22.5F);
        this.setRotation(this.rod22, 0.1745329F, 0.7853982F, 0F);
        this.rod23 = new ModelRenderer(this, 248, 19);
        this.rod23.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rod23.setRotationPoint(21.5F, 10.5F, 21.5F);
        this.setRotation(this.rod23, 0.5585054F, 0.7853982F, 0F);
        this.rod24 = new ModelRenderer(this, 252, 24);
        this.rod24.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod24.setRotationPoint(20.5F, 9.2F, 20.5F);
        this.setRotation(this.rod24, 0.7853982F, 0.7853982F, 0F);
        this.rod31 = new ModelRenderer(this, 240, 0);
        this.rod31.addBox(-2F, 0F, -2F, 4, 8, 4);
        this.rod31.setRotationPoint(23F, 15.5F, -23F);
        this.setRotation(this.rod31, 0F, 0.7853982F, 0F);
        this.rod32 = new ModelRenderer(this, 244, 12);
        this.rod32.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.rod32.setRotationPoint(22.5F, 12.5F, -22.5F);
        this.setRotation(this.rod32, -0.1745329F, -0.7853982F, 0F);
        this.rod33 = new ModelRenderer(this, 248, 19);
        this.rod33.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rod33.setRotationPoint(21.5F, 10.5F, -21.5F);
        this.setRotation(this.rod33, -0.5585054F, -0.7853982F, 0F);
        this.rod34 = new ModelRenderer(this, 252, 24);
        this.rod34.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod34.setRotationPoint(20.5F, 9.2F, -20.5F);
        this.setRotation(this.rod34, -0.7853982F, -0.7853982F, 0F);
        this.rod41 = new ModelRenderer(this, 240, 0);
        this.rod41.addBox(-2F, 0F, -2F, 4, 8, 4);
        this.rod41.setRotationPoint(-23F, 15.5F, 23F);
        this.setRotation(this.rod41, 0F, 0.7853982F, 0F);
        this.rod42 = new ModelRenderer(this, 244, 12);
        this.rod42.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.rod42.setRotationPoint(-22.5F, 12.5F, 22.5F);
        this.setRotation(this.rod42, 0.1745329F, -0.7853982F, 0F);
        this.rod43 = new ModelRenderer(this, 248, 19);
        this.rod43.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rod43.setRotationPoint(-21.5F, 10.5F, 21.5F);
        this.setRotation(this.rod43, 0.5585054F, -0.7853982F, 0F);
        this.rod44 = new ModelRenderer(this, 252, 24);
        this.rod44.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod44.setRotationPoint(-20.5F, 9.2F, 20.5F);
        this.setRotation(this.rod44, 0.7853982F, -0.7853982F, 0F);
        this.rodh1 = new ModelRenderer(this, 248, 126);
        this.rodh1.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
        this.rodh1.setRotationPoint(24.5F, 21.5F, 24.5F);
        this.setRotation(this.rodh1, 0F, 0.7853982F, 0F);
        this.rodh2 = new ModelRenderer(this, 248, 126);
        this.rodh2.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
        this.rodh2.setRotationPoint(24.5F, 19.5F, 24.5F);
        this.setRotation(this.rodh2, 0F, 0.7853982F, 0F);
        this.rodh3 = new ModelRenderer(this, 248, 126);
        this.rodh3.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
        this.rodh3.setRotationPoint(24.5F, 17.5F, 24.5F);
        this.setRotation(this.rodh3, 0F, 0.7853982F, 0F);
        this.rodh4 = new ModelRenderer(this, 248, 126);
        this.rodh4.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
        this.rodh4.setRotationPoint(-24.5F, 21.5F, -24.5F);
        this.setRotation(this.rodh4, 0F, 0.7853982F, 0F);
        this.rodh5 = new ModelRenderer(this, 248, 126);
        this.rodh5.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
        this.rodh5.setRotationPoint(-24.5F, 19.5F, -24.5F);
        this.setRotation(this.rodh5, 0F, 0.7853982F, 0F);
        this.rodh6 = new ModelRenderer(this, 248, 126);
        this.rodh6.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
        this.rodh6.setRotationPoint(-24.5F, 17.5F, -24.5F);
        this.setRotation(this.rodh6, 0F, 0.7853982F, 0F);
        this.rodh7 = new ModelRenderer(this, 248, 121);
        this.rodh7.addBox(-0.5F, 0F, -1.5F, 1, 1, 3);
        this.rodh7.setRotationPoint(-24.5F, 21.5F, 24.5F);
        this.setRotation(this.rodh7, 0F, 0.7853982F, 0F);
        this.rodh8 = new ModelRenderer(this, 248, 121);
        this.rodh8.addBox(-0.5F, 0F, -1.5F, 1, 1, 3);
        this.rodh8.setRotationPoint(-24.5F, 19.5F, 24.5F);
        this.setRotation(this.rodh8, 0F, 0.7853982F, 0F);
        this.rodh9 = new ModelRenderer(this, 248, 121);
        this.rodh9.addBox(-0.5F, 0F, -1.5F, 1, 1, 3);
        this.rodh9.setRotationPoint(-24.5F, 17.5F, 24.5F);
        this.setRotation(this.rodh9, 0F, 0.7853982F, 0F);
        this.rodh10 = new ModelRenderer(this, 248, 121);
        this.rodh10.addBox(-0.5F, 0F, -1.5F, 1, 1, 3);
        this.rodh10.setRotationPoint(24.5F, 21.5F, -24.5F);
        this.setRotation(this.rodh10, 0F, 0.7853982F, 0F);
        this.rodh11 = new ModelRenderer(this, 248, 121);
        this.rodh11.addBox(-0.5F, 0F, -1.5F, 1, 1, 3);
        this.rodh11.setRotationPoint(24.5F, 19.5F, -24.5F);
        this.setRotation(this.rodh11, 0F, 0.7853982F, 0F);
        this.rodh12 = new ModelRenderer(this, 248, 121);
        this.rodh12.addBox(-0.5F, 0F, -1.5F, 1, 1, 3);
        this.rodh12.setRotationPoint(24.5F, 17.5F, -24.5F);
        this.setRotation(this.rodh12, 0F, 0.7853982F, 0F);
    }

    public void render()
    {
        this.base1.render(0.0625F);
        this.base2.render(0.0625F);
        this.base3.render(0.0625F);
        this.rod11.render(0.0625F);
        this.rod12.render(0.0625F);
        this.rod13.render(0.0625F);
        this.rod14.render(0.0625F);
        this.rod21.render(0.0625F);
        this.rod22.render(0.0625F);
        this.rod23.render(0.0625F);
        this.rod24.render(0.0625F);
        this.rod31.render(0.0625F);
        this.rod32.render(0.0625F);
        this.rod33.render(0.0625F);
        this.rod34.render(0.0625F);
        this.rod41.render(0.0625F);
        this.rod42.render(0.0625F);
        this.rod43.render(0.0625F);
        this.rod44.render(0.0625F);
        this.rodh1.render(0.0625F);
        this.rodh2.render(0.0625F);
        this.rodh3.render(0.0625F);
        this.rodh4.render(0.0625F);
        this.rodh5.render(0.0625F);
        this.rodh6.render(0.0625F);
        this.rodh7.render(0.0625F);
        this.rodh8.render(0.0625F);
        this.rodh9.render(0.0625F);
        this.rodh10.render(0.0625F);
        this.rodh11.render(0.0625F);
        this.rodh12.render(0.0625F);
    }

    public void renderRod()
    {
        this.rodh1.render(0.0625F);
        this.rodh2.render(0.0625F);
        this.rodh3.render(0.0625F);
        this.rodh4.render(0.0625F);
        this.rodh5.render(0.0625F);
        this.rodh6.render(0.0625F);
        this.rodh7.render(0.0625F);
        this.rodh8.render(0.0625F);
        this.rodh9.render(0.0625F);
        this.rodh10.render(0.0625F);
        this.rodh11.render(0.0625F);
        this.rodh12.render(0.0625F);
    }

    public void renderLight()
    {
        this.rod14.render(0.0625F);
        this.rod24.render(0.0625F);
        this.rod34.render(0.0625F);
        this.rod44.render(0.0625F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
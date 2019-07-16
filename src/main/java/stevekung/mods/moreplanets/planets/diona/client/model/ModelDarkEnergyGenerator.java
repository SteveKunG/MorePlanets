package stevekung.mods.moreplanets.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ModelDarkEnergyGenerator extends ModelBase
{
    private final ModelRenderer base1;
    private final ModelRenderer base2;
    private final ModelRenderer base3;
    private final ModelRenderer ball;
    private final ModelRenderer rod1;
    private final ModelRenderer rod2;
    private final ModelRenderer rod3;
    private final ModelRenderer rod4;
    private final ModelRenderer rods1;
    private final ModelRenderer rods2;
    private final ModelRenderer rods3;
    private final ModelRenderer rods4;
    private final ModelRenderer baseside1;
    private final ModelRenderer baseside2;
    private final ModelRenderer baseside3;
    private final ModelRenderer baseside4;
    private final ModelRenderer baseside5;
    private final ModelRenderer baseside6;
    private final ModelRenderer baseside7;
    private final ModelRenderer baseside8;
    private final ModelRenderer baseside9;
    private final ModelRenderer baseside10;

    public ModelDarkEnergyGenerator()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.base1 = new ModelRenderer(this, 0, 47);
        this.base1.addBox(-6F, 0F, -6F, 12, 5, 12);
        this.base1.setRotationPoint(0F, 19F, 0F);
        this.base2 = new ModelRenderer(this, 0, 34);
        this.base2.addBox(-5F, 0F, -5F, 10, 3, 10);
        this.base2.setRotationPoint(0F, 16F, 0F);
        this.base3 = new ModelRenderer(this, 0, 25);
        this.base3.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
        this.base3.setRotationPoint(0F, 12F, 0F);
        this.ball = new ModelRenderer(this, 0, 16);
        this.ball.addBox(-2F, -2F, -2F, 4, 4, 4);
        this.ball.setRotationPoint(0F, 4F, 0F);
        this.rod1 = new ModelRenderer(this, 32, 0);
        this.rod1.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.rod1.setRotationPoint(3.5F, 16F, 4.5F);
        this.setRotation(this.rod1, 0.2094395F, 0.5585054F, 0F);
        this.rod2 = new ModelRenderer(this, 32, 0);
        this.rod2.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.rod2.setRotationPoint(5.5F, 16F, -3F);
        this.setRotation(this.rod2, -0.2094395F, -1.082104F, 0F);
        this.rod3 = new ModelRenderer(this, 32, 0);
        this.rod3.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.rod3.setRotationPoint(-2.5F, 16F, -5F);
        this.setRotation(this.rod3, -0.2094395F, 0.418879F, 0F);
        this.rod4 = new ModelRenderer(this, 32, 0);
        this.rod4.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.rod4.setRotationPoint(-5F, 16F, 4F);
        this.setRotation(this.rod4, -0.2094395F, 2.094395F, 0F);
        this.rods1 = new ModelRenderer(this, 27, 0);
        this.rods1.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rods1.setRotationPoint(3F, 8.5F, 3.5F);
        this.setRotation(this.rods1, 0.6981317F, 0.5585054F, 0F);
        this.rods2 = new ModelRenderer(this, 27, 0);
        this.rods2.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rods2.setRotationPoint(4F, 8.5F, -2F);
        this.setRotation(this.rods2, -0.6981317F, -1.082104F, 0F);
        this.rods3 = new ModelRenderer(this, 27, 0);
        this.rods3.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rods3.setRotationPoint(-2F, 8.5F, -3.5F);
        this.setRotation(this.rods3, -0.5585054F, 0.418879F, 0F);
        this.rods4 = new ModelRenderer(this, 27, 0);
        this.rods4.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        this.rods4.setRotationPoint(-3.5F, 8.5F, 3F);
        this.setRotation(this.rods4, -0.5585054F, 2.094395F, 0F);
        this.baseside1 = new ModelRenderer(this, 32, 12);
        this.baseside1.addBox(0F, 0F, 0F, 1, 3, 2);
        this.baseside1.setRotationPoint(2F, 13F, -1F);
        this.baseside2 = new ModelRenderer(this, 32, 18);
        this.baseside2.addBox(0F, 0F, 0F, 2, 3, 1);
        this.baseside2.setRotationPoint(-1F, 13F, -3F);
        this.baseside3 = new ModelRenderer(this, 32, 12);
        this.baseside3.addBox(0F, 0F, 0F, 1, 3, 2);
        this.baseside3.setRotationPoint(-2F, 13F, 1F);
        this.setRotation(this.baseside3, 0F, -3.141593F, 0F);
        this.baseside4 = new ModelRenderer(this, 32, 18);
        this.baseside4.addBox(0F, 0F, 0F, 2, 3, 1);
        this.baseside4.setRotationPoint(1F, 13F, 3F);
        this.setRotation(this.baseside4, 0F, 3.141593F, 0F);
        this.baseside5 = new ModelRenderer(this, 40, 12);
        this.baseside5.addBox(-1.5F, 0F, -0.5F, 3, 2, 1);
        this.baseside5.setRotationPoint(0F, 17F, 5F);
        this.baseside6 = new ModelRenderer(this, 40, 16);
        this.baseside6.addBox(-0.5F, 0F, -1.5F, 1, 2, 3);
        this.baseside6.setRotationPoint(5F, 17F, 0F);
        this.baseside7 = new ModelRenderer(this, 40, 12);
        this.baseside7.addBox(-1.5F, 0F, -0.5F, 3, 2, 1);
        this.baseside7.setRotationPoint(0F, 17F, -5F);
        this.baseside8 = new ModelRenderer(this, 40, 16);
        this.baseside8.addBox(-0.5F, 0F, -1.5F, 1, 2, 3);
        this.baseside8.setRotationPoint(-5F, 17F, 0F);
        this.baseside9 = new ModelRenderer(this, 0, 8);
        this.baseside9.addBox(-2F, -3F, -1F, 4, 3, 2);
        this.baseside9.setRotationPoint(0F, 24F, -7F);
        this.setRotation(this.baseside9, -0.4363323F, 0F, 0F);
        this.baseside10 = new ModelRenderer(this, 0, 8);
        this.baseside10.addBox(-2F, -3F, -1F, 4, 3, 2);
        this.baseside10.setRotationPoint(0F, 24F, 7F);
        this.setRotation(this.baseside10, 0.4363323F, 0F, 0F);
    }

    public void renderBase()
    {
        this.base1.render(0.0625F);
        this.base2.render(0.0625F);
        this.base3.render(0.0625F);
        this.rod1.render(0.0625F);
        this.rod2.render(0.0625F);
        this.rod3.render(0.0625F);
        this.rod4.render(0.0625F);
        this.rods1.render(0.0625F);
        this.rods2.render(0.0625F);
        this.rods3.render(0.0625F);
        this.rods4.render(0.0625F);
        this.baseside5.render(0.0625F);
        this.baseside6.render(0.0625F);
        this.baseside7.render(0.0625F);
        this.baseside8.render(0.0625F);
        this.baseside9.render(0.0625F);
        this.baseside10.render(0.0625F);
    }

    public void renderRod()
    {
        this.rods1.render(0.0625F);
        this.rods2.render(0.0625F);
        this.rods3.render(0.0625F);
        this.rods4.render(0.0625F);
    }

    public void renderBaseSide()
    {
        this.base3.render(0.0625F);
        this.baseside1.render(0.0625F);
        this.baseside2.render(0.0625F);
        this.baseside3.render(0.0625F);
        this.baseside4.render(0.0625F);
    }

    public void renderBall(float tick)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -0.35F + tick, 0.0F);
        this.ball.render(0.0625F);
        GlStateManager.popMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
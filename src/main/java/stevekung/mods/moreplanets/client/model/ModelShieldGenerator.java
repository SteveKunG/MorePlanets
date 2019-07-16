package stevekung.mods.moreplanets.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelShieldGenerator extends ModelBase
{
    private final ModelRenderer base;
    private final ModelRenderer baseside;
    private final ModelRenderer side1;
    private final ModelRenderer side2;
    private final ModelRenderer side3;
    private final ModelRenderer side4;
    private final ModelRenderer basetop;
    private final ModelRenderer rod;
    private final ModelRenderer rodbase;
    private final ModelRenderer rodbase1;
    private final ModelRenderer rodbase2;
    private final ModelRenderer rodbase3;
    private final ModelRenderer rodbase4;
    private final ModelRenderer rodbase5;
    private final ModelRenderer rodbase6;
    private final ModelRenderer rodbase7;
    private final ModelRenderer rodbase8;
    private final ModelRenderer rodbase51;
    private final ModelRenderer solar1;
    private final ModelRenderer rodbase61;
    private final ModelRenderer solar2;
    private final ModelRenderer rodbase71;
    private final ModelRenderer solar3;
    private final ModelRenderer rodbase81;
    private final ModelRenderer solar4;
    private final ModelRenderer input;

    public ModelShieldGenerator()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.rod = new ModelRenderer(this, 30, 28);
        this.rod.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.rod.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        this.rodbase5 = new ModelRenderer(this, 5, 19);
        this.rodbase5.setRotationPoint(0.5F, -0.5F, -0.5F);
        this.rodbase5.addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(this.rodbase5, 0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.rodbase3 = new ModelRenderer(this, 0, 25);
        this.rodbase3.setRotationPoint(1.0F, -7.0F, -1.0F);
        this.rodbase3.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.rodbase3, -0.08726646259971647F, -0.7853981633974483F, 0.0F);
        this.rodbase61 = new ModelRenderer(this, 10, 24);
        this.rodbase61.setRotationPoint(0.0F, -7.8F, -0.1F);
        this.rodbase61.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.rodbase61, -0.3490658503988659F, 0.0F, 0.0F);
        this.side4 = new ModelRenderer(this, 0, 29);
        this.side4.setRotationPoint(6.0F, 14.0F, 6.0F);
        this.side4.addBox(-1.5F, -4.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(this.side4, 0.0F, -0.7853981633974483F, 0.0F);
        this.rodbase1 = new ModelRenderer(this, 0, 25);
        this.rodbase1.setRotationPoint(-1.0F, -7.0F, -1.0F);
        this.rodbase1.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.rodbase1, -0.08726646259971647F, 0.7853981633974483F, 0.0F);
        this.rodbase7 = new ModelRenderer(this, 5, 19);
        this.rodbase7.setRotationPoint(0.5F, -0.5F, 0.5F);
        this.rodbase7.addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(this.rodbase7, -0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.solar1 = new ModelRenderer(this, 0, 0);
        this.solar1.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.solar1.addBox(-0.5F, -8.0F, -2.0F, 1, 8, 4, 0.0F);
        this.setRotateAngle(this.solar1, 0.0F, 1.5707963267948966F, 0.0F);
        this.baseside = new ModelRenderer(this, 56, 44);
        this.baseside.setRotationPoint(0.0F, 16.5F, 0.0F);
        this.baseside.addBox(-8.0F, 0.0F, -8.0F, 16, 3, 16, 0.0F);
        this.rodbase4 = new ModelRenderer(this, 0, 25);
        this.rodbase4.setRotationPoint(1.0F, -7.0F, 1.0F);
        this.rodbase4.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.rodbase4, 0.08726646259971647F, 0.7853981633974483F, 0.0F);
        this.rodbase2 = new ModelRenderer(this, 0, 25);
        this.rodbase2.setRotationPoint(-1.0F, -7.0F, 1.0F);
        this.rodbase2.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.rodbase2, 0.08726646259971647F, -0.7853981633974483F, 0.0F);
        this.side2 = new ModelRenderer(this, 0, 29);
        this.side2.setRotationPoint(6.0F, 14.0F, -6.0F);
        this.side2.addBox(-1.5F, -4.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(this.side2, 0.0F, 0.7853981633974483F, 0.0F);
        this.rodbase51 = new ModelRenderer(this, 10, 24);
        this.rodbase51.setRotationPoint(0.0F, -7.8F, -0.1F);
        this.rodbase51.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.rodbase51, -0.3490658503988659F, 0.0F, 0.0F);
        this.base = new ModelRenderer(this, 0, 37);
        this.base.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.base.addBox(-7.0F, 0.0F, -7.0F, 14, 12, 14, 0.0F);
        this.side1 = new ModelRenderer(this, 0, 29);
        this.side1.setRotationPoint(-6.0F, 14.0F, -6.0F);
        this.side1.addBox(-1.5F, -4.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(this.side1, 0.0F, 2.356194490192345F, 0.0F);
        this.solar3 = new ModelRenderer(this, 0, 0);
        this.solar3.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.solar3.addBox(-0.5F, -8.0F, -2.0F, 1, 8, 4, 0.0F);
        this.setRotateAngle(this.solar3, 0.0F, -1.5707963267948966F, 0.0F);
        this.side3 = new ModelRenderer(this, 0, 29);
        this.side3.setRotationPoint(-6.0F, 14.0F, 6.0F);
        this.side3.addBox(-1.5F, -4.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(this.side3, 0.0F, -2.356194490192345F, 0.0F);
        this.rodbase6 = new ModelRenderer(this, 5, 19);
        this.rodbase6.setRotationPoint(-0.5F, -0.5F, -0.5F);
        this.rodbase6.addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(this.rodbase6, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.rodbase71 = new ModelRenderer(this, 10, 24);
        this.rodbase71.setRotationPoint(0.0F, -7.8F, 0.1F);
        this.rodbase71.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.rodbase71, 0.3490658503988659F, 0.0F, 0.0F);
        this.solar4 = new ModelRenderer(this, 0, 0);
        this.solar4.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.solar4.addBox(-0.5F, -8.0F, -2.0F, 1, 8, 4, 0.0F);
        this.setRotateAngle(this.solar4, 0.0F, -1.5707963267948966F, 0.0F);
        this.basetop = new ModelRenderer(this, 13, 30);
        this.basetop.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.basetop.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.solar2 = new ModelRenderer(this, 0, 0);
        this.solar2.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.solar2.addBox(-0.5F, -8.0F, -2.0F, 1, 8, 4, 0.0F);
        this.setRotateAngle(this.solar2, 0.0F, 1.5707963267948966F, 0.0F);
        this.rodbase = new ModelRenderer(this, 39, 30);
        this.rodbase.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.rodbase.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5, 0.0F);
        this.rodbase81 = new ModelRenderer(this, 10, 24);
        this.rodbase81.setRotationPoint(0.0F, -7.8F, 0.1F);
        this.rodbase81.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.rodbase81, 0.3490658503988659F, 0.0F, 0.0F);
        this.rodbase8 = new ModelRenderer(this, 5, 19);
        this.rodbase8.setRotationPoint(-0.5F, -0.5F, 0.5F);
        this.rodbase8.addBox(-0.5F, -8.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(this.rodbase8, -0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.input = new ModelRenderer(this, 15, 20);
        this.input.setRotationPoint(0.0F, 13.0F, -9.0F);
        this.input.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 2, 0.0F);
        this.rod.addChild(this.rodbase5);
        this.rod.addChild(this.rodbase3);
        this.rodbase6.addChild(this.rodbase61);
        this.rod.addChild(this.rodbase1);
        this.rod.addChild(this.rodbase7);
        this.rodbase51.addChild(this.solar1);
        this.rod.addChild(this.rodbase4);
        this.rod.addChild(this.rodbase2);
        this.rodbase5.addChild(this.rodbase51);
        this.rodbase71.addChild(this.solar3);
        this.rod.addChild(this.rodbase6);
        this.rodbase7.addChild(this.rodbase71);
        this.rodbase81.addChild(this.solar4);
        this.rodbase61.addChild(this.solar2);
        this.rod.addChild(this.rodbase);
        this.rodbase8.addChild(this.rodbase81);
        this.rod.addChild(this.rodbase8);
    }

    public void renderBase()
    {
        this.side2.render(0.0625F);
        this.side3.render(0.0625F);
        this.side1.render(0.0625F);
        this.basetop.render(0.0625F);
        this.side4.render(0.0625F);
        this.baseside.render(0.0625F);
        this.base.render(0.0625F);
        this.input.render(0.0625F);
    }

    public void renderRod()
    {
        this.rod.render(0.0625F);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
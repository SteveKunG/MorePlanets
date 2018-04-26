package stevekung.mods.moreplanets.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelDarkEnergyReceiver extends ModelBase
{
    private ModelRenderer base;
    private ModelRenderer core;
    private ModelRenderer core1;
    private ModelRenderer core2;
    private ModelRenderer rod1;
    private ModelRenderer rod2;
    private ModelRenderer top;
    private ModelRenderer trans1;
    private ModelRenderer trans2;
    private ModelRenderer trans3;
    private ModelRenderer control1;
    private ModelRenderer control2;
    private ModelRenderer solar1;
    private ModelRenderer solar2;

    public ModelDarkEnergyReceiver()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.base = new ModelRenderer(this, 0, 50);
        this.base.addBox(-3F, 0F, -3F, 6, 2, 6);
        this.base.setRotationPoint(0F, 22F, 0F);
        this.core = new ModelRenderer(this, 0, 34);
        this.core.addBox(-4F, 0F, -4F, 8, 8, 8);
        this.core.setRotationPoint(0F, 14F, 0F);
        this.core1 = new ModelRenderer(this, 0, 27);
        this.core1.addBox(0F, 0F, 0F, 6, 6, 1);
        this.core1.setRotationPoint(-3F, 15F, -4.5F);
        this.core2 = new ModelRenderer(this, 0, 27);
        this.core2.addBox(0F, 0F, 0F, 6, 6, 1);
        this.core2.setRotationPoint(-3F, 15F, 3.5F);
        this.rod1 = new ModelRenderer(this, 0, 18);
        this.rod1.addBox(0F, -1F, -1F, 12, 2, 2);
        this.rod1.setRotationPoint(4F, 18F, 0F);
        this.rod2 = new ModelRenderer(this, 0, 18);
        this.rod2.addBox(-12F, -1F, -1F, 12, 2, 2);
        this.rod2.setRotationPoint(-4F, 18F, 0F);
        this.top = new ModelRenderer(this, 0, 8);
        this.top.addBox(-3F, 0F, -3F, 6, 1, 6);
        this.top.setRotationPoint(0F, 13F, 0F);
        this.trans1 = new ModelRenderer(this, 48, 0);
        this.trans1.addBox(-0.5F, 0F, -0.5F, 1, 6, 1);
        this.trans1.setRotationPoint(0F, 7F, 2F);
        this.trans2 = new ModelRenderer(this, 53, 0);
        this.trans2.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
        this.trans2.setRotationPoint(-2F, 9F, 0.5F);
        this.trans3 = new ModelRenderer(this, 53, 0);
        this.trans3.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
        this.trans3.setRotationPoint(2F, 9F, 0.5F);
        this.control1 = new ModelRenderer(this, 40, 0);
        this.control1.addBox(0F, 0F, 0F, 1, 1, 2);
        this.control1.setRotationPoint(-1F, 12.5F, -2.5F);
        this.control2 = new ModelRenderer(this, 40, 4);
        this.control2.addBox(0F, 0F, 0F, 1, 1, 2);
        this.control2.setRotationPoint(0F, 12.5F, -2.5F);
        this.solar1 = new ModelRenderer(this, 0, 0);
        this.solar1.addBox(0F, 0F, -3F, 10, 1, 6);
        this.solar1.setRotationPoint(5F, 15F, 0F);
        this.solar2 = new ModelRenderer(this, 0, 0);
        this.solar2.addBox(-10F, 0F, -3F, 10, 1, 6);
        this.solar2.setRotationPoint(-5F, 15F, 0F);
    }

    public void renderBase()
    {
        this.base.render(0.0625F);
        this.core.render(0.0625F);
        this.core1.render(0.0625F);
        this.core2.render(0.0625F);
        this.rod1.render(0.0625F);
        this.rod2.render(0.0625F);
        this.top.render(0.0625F);
        this.control1.render(0.0625F);
        this.control2.render(0.0625F);
    }

    public void renderSolar()
    {
        this.solar1.render(0.0625F);
        this.solar2.render(0.0625F);
    }

    public void renderRod()
    {
        this.trans1.render(0.0625F);
        this.trans2.render(0.0625F);
        this.trans3.render(0.0625F);
    }
}
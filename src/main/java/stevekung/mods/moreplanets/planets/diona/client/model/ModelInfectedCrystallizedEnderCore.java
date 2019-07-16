package stevekung.mods.moreplanets.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ModelInfectedCrystallizedEnderCore extends ModelBase
{
    private final ModelRenderer rodBase1;
    private final ModelRenderer rodBase2;
    private final ModelRenderer rodBase3;
    private final ModelRenderer rodBase4;
    private final ModelRenderer rod1;
    private final ModelRenderer rod2;
    private final ModelRenderer rod3;
    private final ModelRenderer rod4;
    private final ModelRenderer enderCore;

    public ModelInfectedCrystallizedEnderCore()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.rodBase1 = new ModelRenderer(this, 0, 27);
        this.rodBase1.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rodBase1.setRotationPoint(-4F, 9.5F, 4F);
        this.setRotation(this.rodBase1, -0.3141593F, 2.303835F, 0F);
        this.rodBase2 = new ModelRenderer(this, 0, 27);
        this.rodBase2.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rodBase2.setRotationPoint(4F, 9.5F, 4F);
        this.setRotation(this.rodBase2, -0.3141593F, -2.303835F, 0F);
        this.rodBase3 = new ModelRenderer(this, 0, 27);
        this.rodBase3.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rodBase3.setRotationPoint(4F, 9.5F, -4F);
        this.setRotation(this.rodBase3, 0.3141593F, 2.303835F, 0F);
        this.rodBase4 = new ModelRenderer(this, 0, 27);
        this.rodBase4.addBox(-1F, 0F, -1F, 2, 3, 2);
        this.rodBase4.setRotationPoint(-4F, 9.5F, -4F);
        this.setRotation(this.rodBase4, 0.3141593F, -2.303835F, 0F);
        this.rod1 = new ModelRenderer(this, 0, 0);
        this.rod1.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod1.setRotationPoint(-3.2F, 8F, 3.3F);
        this.setRotation(this.rod1, -0.5585054F, 2.303835F, 0F);
        this.rod2 = new ModelRenderer(this, 0, 0);
        this.rod2.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod2.setRotationPoint(3.2F, 8F, 3.3F);
        this.setRotation(this.rod2, -0.5585054F, -2.303835F, 0F);
        this.rod3 = new ModelRenderer(this, 0, 0);
        this.rod3.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod3.setRotationPoint(3.2F, 8F, -3.3F);
        this.setRotation(this.rod3, 0.5585054F, 2.303835F, 0F);
        this.rod4 = new ModelRenderer(this, 0, 0);
        this.rod4.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.rod4.setRotationPoint(-3.2F, 8F, -3.3F);
        this.setRotation(this.rod4, 0.5585054F, -2.303835F, 0F);
        this.enderCore = new ModelRenderer(this, 0, 10);
        this.enderCore.addBox(-2F, 0F, -2F, 4, 4, 4);
        this.enderCore.setRotationPoint(0F, 2F, 0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll(float tick)
    {
        this.rodBase1.render(0.0625F);
        this.rodBase2.render(0.0625F);
        this.rodBase3.render(0.0625F);
        this.rodBase4.render(0.0625F);
        this.rod1.render(0.0625F);
        this.rod2.render(0.0625F);
        this.rod3.render(0.0625F);
        this.rod4.render(0.0625F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -0.25F + tick, 0.0F);
        this.enderCore.render(0.0625F);
        GlStateManager.popMatrix();
    }
}
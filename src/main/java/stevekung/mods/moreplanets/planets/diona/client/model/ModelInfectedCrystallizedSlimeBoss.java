package stevekung.mods.moreplanets.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelInfectedCrystallizedSlimeBoss extends ModelBase
{
    private ModelRenderer body1;
    private ModelRenderer body2;
    private ModelRenderer eye1;
    private ModelRenderer eye2;
    private ModelRenderer eye3;
    private ModelRenderer eye4;
    private ModelRenderer eye5;

    public ModelInfectedCrystallizedSlimeBoss()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.body1 = new ModelRenderer(this, 96, 16);
        this.body1.addBox(-4F, -4F, -4F, 8, 8, 8);
        this.body1.setRotationPoint(0F, 16F, 0F);
        this.body2 = new ModelRenderer(this, 64, 32);
        this.body2.addBox(-8F, -8F, -8F, 16, 16, 16);
        this.body2.setRotationPoint(0F, 16F, 0F);
        this.eye1 = new ModelRenderer(this, 0, 32);
        this.eye1.addBox(-3F, -2F, 0F, 6, 4, 1);
        this.eye1.setRotationPoint(0F, 16F, -9F);
        this.eye2 = new ModelRenderer(this, 0, 32);
        this.eye2.addBox(-3F, -2F, -1F, 6, 4, 1);
        this.eye2.setRotationPoint(0F, 16F, 9F);
        this.eye3 = new ModelRenderer(this, 0, 18);
        this.eye3.addBox(0F, -2F, -3F, 1, 4, 6);
        this.eye3.setRotationPoint(-9F, 16F, 0F);
        this.eye4 = new ModelRenderer(this, 0, 18);
        this.eye4.addBox(-1F, -2F, -3F, 1, 4, 6);
        this.eye4.setRotationPoint(9F, 16F, 0F);
        this.eye5 = new ModelRenderer(this, 0, 8);
        this.eye5.addBox(-3F, 0F, -2F, 6, 1, 4);
        this.eye5.setRotationPoint(0F, 7F, 0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GlStateManager.pushMatrix();
        GlStateManager.enableCull();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.body1.render(scale);
        this.body2.render(scale);
        this.eye1.render(scale);
        this.eye2.render(scale);
        this.eye3.render(scale);
        this.eye4.render(scale);
        this.eye5.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.cullFace(1029);
        GlStateManager.disableCull();
        GlStateManager.popMatrix();
    }
}
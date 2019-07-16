package stevekung.mods.moreplanets.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInfectedCrystallizedBomb extends ModelBase
{
    private final ModelRenderer base1;
    private final ModelRenderer base2;
    private final ModelRenderer base3;
    private final ModelRenderer base4;
    private final ModelRenderer base5;
    private final ModelRenderer base6;
    private final ModelRenderer base7;
    private final ModelRenderer base8;

    public ModelInfectedCrystallizedBomb()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.base1 = new ModelRenderer(this, 0, 23);
        this.base1.addBox(-2F, 0F, -2F, 4, 5, 4);
        this.base1.setRotationPoint(0F, 19F, 0F);
        this.base2 = new ModelRenderer(this, 0, 18);
        this.base2.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
        this.base2.setRotationPoint(0F, 18F, 0F);
        this.base3 = new ModelRenderer(this, 0, 14);
        this.base3.addBox(-1F, 0F, -1F, 2, 1, 2);
        this.base3.setRotationPoint(0F, 17.5F, 0F);
        this.base4 = new ModelRenderer(this, 0, 10);
        this.base4.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.base4.setRotationPoint(0F, 16F, 0F);
        this.base5 = new ModelRenderer(this, 0, 0);
        this.base5.addBox(-1.5F, -2F, -0.5F, 3, 4, 1);
        this.base5.setRotationPoint(0F, 21.5F, -2F);
        this.setRotation(this.base5, -0.2792527F, 0F, 0F);
        this.base6 = new ModelRenderer(this, 0, 0);
        this.base6.addBox(-1.5F, -2F, -0.5F, 3, 4, 1);
        this.base6.setRotationPoint(0F, 21.5F, 2F);
        this.setRotation(this.base6, 0.2792527F, 0F, 0F);
        this.base7 = new ModelRenderer(this, 0, 0);
        this.base7.addBox(-1.5F, -2F, -0.5F, 3, 4, 1);
        this.base7.setRotationPoint(-2F, 21.5F, 0F);
        this.setRotation(this.base7, -0.2792527F, 1.570796F, 0F);
        this.base8 = new ModelRenderer(this, 0, 0);
        this.base8.addBox(-1.5F, -2F, -0.5F, 3, 4, 1);
        this.base8.setRotationPoint(2F, 21.5F, 0F);
        this.setRotation(this.base8, 0.2792527F, 1.570796F, 0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.base1.render(scale);
        this.base2.render(scale);
        this.base3.render(scale);
        this.base4.render(scale);
        this.base5.render(scale);
        this.base6.render(scale);
        this.base7.render(scale);
        this.base8.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
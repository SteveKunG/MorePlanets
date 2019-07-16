package stevekung.mods.moreplanets.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInfectedCrystallizedTentacle extends ModelBase
{
    private final ModelRenderer body1;
    private final ModelRenderer eye1;
    private final ModelRenderer eye2;
    private final ModelRenderer eye3;
    private final ModelRenderer eye4;
    private final ModelRenderer body2;
    private final ModelRenderer body3;
    private final ModelRenderer eye5;
    private final ModelRenderer eye6;
    private final ModelRenderer eye7;
    private final ModelRenderer eye8;
    private final ModelRenderer body4;

    public ModelInfectedCrystallizedTentacle()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.eye1 = new ModelRenderer(this, 0, 12);
        this.eye1.setRotationPoint(-4.0F, -8.0F, 0.0F);
        this.eye1.addBox(-1.0F, -1.5F, -2.0F, 1, 3, 4, 0.0F);
        this.eye7 = new ModelRenderer(this, 0, 35);
        this.eye7.setRotationPoint(-3.0F, -4.0F, 0.0F);
        this.eye7.addBox(-1.0F, -1.0F, -1.5F, 1, 2, 3, 0.0F);
        this.body3 = new ModelRenderer(this, 112, 38);
        this.body3.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.body3.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 4, 0.0F);
        this.body4 = new ModelRenderer(this, 120, 48);
        this.body4.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.body4.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.eye8 = new ModelRenderer(this, 0, 35);
        this.eye8.setRotationPoint(3.0F, -4.0F, 0.0F);
        this.eye8.addBox(0.0F, -1.0F, -1.5F, 1, 2, 3, 0.0F);
        this.body1 = new ModelRenderer(this, 96, 0);
        this.body1.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.body1.addBox(-4.0F, -16.0F, -4.0F, 8, 16, 8, 0.0F);
        this.eye6 = new ModelRenderer(this, 0, 30);
        this.eye6.setRotationPoint(0.0F, -4.0F, -3.0F);
        this.eye6.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 1, 0.0F);
        this.eye3 = new ModelRenderer(this, 0, 20);
        this.eye3.setRotationPoint(0.0F, -8.0F, -4.0F);
        this.eye3.addBox(-2.0F, -1.5F, -1.0F, 4, 3, 1, 0.0F);
        this.eye4 = new ModelRenderer(this, 0, 20);
        this.eye4.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.eye4.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 1, 0.0F);
        this.body2 = new ModelRenderer(this, 104, 24);
        this.body2.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.body2.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6, 0.0F);
        this.eye5 = new ModelRenderer(this, 0, 30);
        this.eye5.setRotationPoint(0.0F, -4.0F, 3.0F);
        this.eye5.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 1, 0.0F);
        this.eye2 = new ModelRenderer(this, 0, 12);
        this.eye2.setRotationPoint(4.0F, -8.0F, 0.0F);
        this.eye2.addBox(0.0F, -1.5F, -2.0F, 1, 3, 4, 0.0F);
        this.body1.addChild(this.eye1);
        this.body2.addChild(this.eye7);
        this.body2.addChild(this.body3);
        this.body3.addChild(this.body4);
        this.body2.addChild(this.eye8);
        this.body2.addChild(this.eye6);
        this.body1.addChild(this.eye3);
        this.body1.addChild(this.eye4);
        this.body1.addChild(this.body2);
        this.body2.addChild(this.eye5);
        this.body1.addChild(this.eye2);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.body1.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.body3.offsetY = 0.05F;
        this.body4.offsetY = 0.05F;
        this.body1.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (-1.5F + Math.abs(2));
        this.body2.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * Math.abs(1);
        this.body3.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (2 + Math.abs(2));
        this.body4.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (4 + Math.abs(4));
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
package stevekung.mods.moreplanets.module.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInfectedCrystallizedWorm extends ModelBase
{
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer tentacle1;
    ModelRenderer tentacle2;

    public ModelInfectedCrystallizedWorm()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body1 = new ModelRenderer(this, 40, 0);
        this.body1.addBox(-3F, -3F, -6F, 6, 6, 6);
        this.body1.setRotationPoint(0F, 21F, -2F);
        this.body2 = new ModelRenderer(this, 46, 12);
        this.body2.addBox(-2F, -2F, 0F, 4, 4, 5);
        this.body2.setRotationPoint(0F, 22F, -2F);
        this.body3 = new ModelRenderer(this, 48, 21);
        this.body3.addBox(-1.5F, -1F, 0F, 3, 3, 5);
        this.body3.setRotationPoint(0F, 22F, 3F);
        this.body4 = new ModelRenderer(this, 0, 27);
        this.body4.addBox(-1F, -1F, 0F, 2, 2, 3);
        this.body4.setRotationPoint(0F, 23F, 8F);
        this.tentacle1 = new ModelRenderer(this, 0, 0);
        this.tentacle1.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.tentacle1.setRotationPoint(1.5F, 18.5F, -6F);
        this.setRotation(this.tentacle1, -0.418879F, 0F, 0F);
        this.tentacle2 = new ModelRenderer(this, 0, 0);
        this.tentacle2.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.tentacle2.setRotationPoint(-1.5F, 18.5F, -6F);
        this.setRotation(this.tentacle2, -0.418879F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.body1.render(scale);
        this.body2.render(scale);
        this.body3.render(scale);
        this.body4.render(scale);
        this.tentacle1.render(scale);
        this.tentacle2.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.body2.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        this.body2.rotationPointX = MathHelper.sin(ageInTicks * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * Math.abs(2 - 2);
        this.body3.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(3 - 2));
        this.body3.rotationPointX = MathHelper.sin(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * Math.abs(3 - 2);
        this.body4.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + 5 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(5 - 2));
        this.body4.rotationPointX = MathHelper.sin(ageInTicks * 0.9F + 5 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * Math.abs(5 - 2);
    }
}
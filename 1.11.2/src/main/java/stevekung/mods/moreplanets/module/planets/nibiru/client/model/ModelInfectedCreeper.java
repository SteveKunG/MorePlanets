package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInfectedCreeper extends ModelCreeper
{
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;

    public ModelInfectedCreeper()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.tail2 = new ModelRenderer(this, 52, 8);
        this.tail2.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.tail2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.tail1 = new ModelRenderer(this, 48, 0);
        this.tail1.setRotationPoint(0.0F, -4.0F, 3.6F);
        this.tail1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
        this.tail3 = new ModelRenderer(this, 56, 14);
        this.tail3.setRotationPoint(-0.5F, 0.0F, 3.0F);
        this.tail3.addBox(-0.5F, -0.5F, 0.0F, 2, 2, 2, 0.0F);
        this.tail1.addChild(this.tail2);
        this.head.addChild(this.tail1);
        this.tail2.addChild(this.tail3);
    }

    public ModelInfectedCreeper(float scale)
    {
        super(scale);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.tail1.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        this.tail1.rotationPointX = MathHelper.sin(ageInTicks * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * Math.abs(2 - 2);
        this.tail2.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(3 - 2));
        this.tail2.rotationPointX = MathHelper.sin(ageInTicks * 0.9F + 3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * Math.abs(3 - 2);
        this.tail3.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + 5 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(5 - 2));
        this.tail3.rotationPointX = MathHelper.sin(ageInTicks * 0.9F + 5 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * Math.abs(5 - 2);
    }
}
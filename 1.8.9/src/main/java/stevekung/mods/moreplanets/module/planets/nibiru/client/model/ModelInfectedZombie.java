package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelInfectedZombie extends ModelZombie
{
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;

    public ModelInfectedZombie()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.tail2 = new ModelRenderer(this, 52, 8);
        this.tail2.setRotationPoint(0.0F, 0.0F, 3.3F);
        this.tail2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
        this.tail3 = new ModelRenderer(this, 56, 14);
        this.tail3.setRotationPoint(0.0F, 0.0F, 2.7F);
        this.tail3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.tail1 = new ModelRenderer(this, 48, 0);
        this.tail1.setRotationPoint(0.0F, -3.6F, 2.8F);
        this.tail1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
        this.tail1.addChild(this.tail2);
        this.tail2.addChild(this.tail3);
        this.bipedHead.addChild(this.tail1);
    }

    public ModelInfectedZombie(float scale, boolean bool)
    {
        super(scale, bool);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.bipedHeadwear.showModel = false;
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
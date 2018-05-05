package stevekung.mods.moreplanets.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityTerrastoneGolem;

@SideOnly(Side.CLIENT)
public class ModelTerrastoneGolem extends ModelBase
{
    private ModelRenderer ironGolemHead;
    private ModelRenderer ironGolemBody;
    private ModelRenderer ironGolemRightArm;
    private ModelRenderer ironGolemLeftArm;
    private ModelRenderer ironGolemLeftLeg;
    private ModelRenderer ironGolemRightLeg;

    public ModelTerrastoneGolem()
    {
        float rotationPoint = -7.0F;
        this.ironGolemHead = new ModelRenderer(this).setTextureSize(128, 128);
        this.ironGolemHead.setRotationPoint(0.0F, 0.0F + rotationPoint, -2.0F);
        this.ironGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, 0.0F);
        this.ironGolemHead.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, 0.0F);
        this.ironGolemBody = new ModelRenderer(this).setTextureSize(128, 128);
        this.ironGolemBody.setRotationPoint(0.0F, 0.0F + rotationPoint, 0.0F);
        this.ironGolemBody.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, 0.0F);
        this.ironGolemBody.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, 0.0F + 0.5F);
        this.ironGolemRightArm = new ModelRenderer(this).setTextureSize(128, 128);
        this.ironGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.ironGolemRightArm.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, 0.0F);
        this.ironGolemLeftArm = new ModelRenderer(this).setTextureSize(128, 128);
        this.ironGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.ironGolemLeftArm.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, 0.0F);
        this.ironGolemLeftLeg = new ModelRenderer(this, 0, 22).setTextureSize(128, 128);
        this.ironGolemLeftLeg.setRotationPoint(-4.0F, 18.0F + rotationPoint, 0.0F);
        this.ironGolemLeftLeg.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
        this.ironGolemRightLeg = new ModelRenderer(this, 0, 22).setTextureSize(128, 128);
        this.ironGolemRightLeg.mirror = true;
        this.ironGolemRightLeg.setTextureOffset(60, 0).setRotationPoint(5.0F, 18.0F + rotationPoint, 0.0F);
        this.ironGolemRightLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.ironGolemHead.render(scale);
        this.ironGolemBody.render(scale);
        this.ironGolemLeftLeg.render(scale);
        this.ironGolemRightLeg.render(scale);
        this.ironGolemRightArm.render(scale);
        this.ironGolemLeftArm.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
    {
        this.ironGolemHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.ironGolemHead.rotateAngleX = headPitch * 0.017453292F;
        this.ironGolemLeftLeg.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.ironGolemRightLeg.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.ironGolemLeftLeg.rotateAngleY = 0.0F;
        this.ironGolemRightLeg.rotateAngleY = 0.0F;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks)
    {
        EntityTerrastoneGolem golem = (EntityTerrastoneGolem)entity;
        int i = golem.getAttackTimer();

        if (i > 0)
        {
            this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * this.triangleWave(i - partialTicks, 10.0F);
            this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * this.triangleWave(i - partialTicks, 10.0F);
        }
        else
        {
            this.ironGolemRightArm.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
            this.ironGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        }
    }

    private float triangleWave(float limbSwing, float waveAmount)
    {
        return (Math.abs(limbSwing % waveAmount - waveAmount * 0.5F) - waveAmount * 0.25F) / (waveAmount * 0.25F);
    }
}
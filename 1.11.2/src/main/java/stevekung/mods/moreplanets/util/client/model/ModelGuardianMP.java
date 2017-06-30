package stevekung.mods.moreplanets.util.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ModelGuardianMP extends ModelBase
{
    ModelRenderer guardianBody;
    ModelRenderer guardianEye;
    ModelRenderer[] guardianSpines;
    ModelRenderer[] guardianTail;

    public ModelGuardianMP()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.guardianSpines = new ModelRenderer[12];
        this.guardianBody = new ModelRenderer(this);
        this.guardianBody.setTextureOffset(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16);
        this.guardianBody.setTextureOffset(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2, 12, 12);
        this.guardianBody.setTextureOffset(0, 28).addBox(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
        this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12);
        this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);

        for (int i = 0; i < this.guardianSpines.length; ++i)
        {
            this.guardianSpines[i] = new ModelRenderer(this, 0, 0);
            this.guardianSpines[i].addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
            this.guardianBody.addChild(this.guardianSpines[i]);
        }

        this.guardianEye = new ModelRenderer(this, 8, 0);
        this.guardianEye.addBox(-1.0F, 15.0F, 0.0F, 2, 2, 1);
        this.guardianBody.addChild(this.guardianEye);
        this.guardianTail = new ModelRenderer[3];
        this.guardianTail[0] = new ModelRenderer(this, 40, 0);
        this.guardianTail[0].addBox(-2.0F, 14.0F, 7.0F, 4, 4, 8);
        this.guardianTail[1] = new ModelRenderer(this, 0, 54);
        this.guardianTail[1].addBox(0.0F, 14.0F, 0.0F, 3, 3, 7);
        this.guardianTail[2] = new ModelRenderer(this);
        this.guardianTail[2].setTextureOffset(41, 32).addBox(0.0F, 14.0F, 0.0F, 2, 2, 6);
        this.guardianTail[2].setTextureOffset(25, 19).addBox(1.0F, 10.5F, 3.0F, 0, 9, 9);
        this.guardianBody.addChild(this.guardianTail[0]);
        this.guardianTail[0].addChild(this.guardianTail[1]);
        this.guardianTail[1].addChild(this.guardianTail[2]);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.guardianBody.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        if (entity instanceof EntityGuardian)
        {
            EntityGuardian entityguardian = (EntityGuardian)entity;
            float f6 = ageInTicks - entityguardian.ticksExisted;
            this.guardianBody.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
            this.guardianBody.rotateAngleX = headPitch / (180F / (float)Math.PI);
            float[] afloat = new float[] {1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
            float[] afloat1 = new float[] {0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
            float[] afloat2 = new float[] {0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
            float[] afloat3 = new float[] {0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
            float[] afloat4 = new float[] { -8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
            float[] afloat5 = new float[] {8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};
            float f7 = (1.0F - entityguardian.getSpikesAnimation(f6)) * 0.55F;

            for (int i = 0; i < 12; ++i)
            {
                this.guardianSpines[i].rotateAngleX = (float)Math.PI * afloat[i];
                this.guardianSpines[i].rotateAngleY = (float)Math.PI * afloat1[i];
                this.guardianSpines[i].rotateAngleZ = (float)Math.PI * afloat2[i];
                this.guardianSpines[i].rotationPointX = afloat3[i] * (1.0F + MathHelper.cos(ageInTicks * 1.5F + i) * 0.01F - f7);
                this.guardianSpines[i].rotationPointY = 16.0F + afloat4[i] * (1.0F + MathHelper.cos(ageInTicks * 1.5F + i) * 0.01F - f7);
                this.guardianSpines[i].rotationPointZ = afloat5[i] * (1.0F + MathHelper.cos(ageInTicks * 1.5F + i) * 0.01F - f7);
            }

            this.guardianEye.rotationPointZ = -8.25F;
            Object object = Minecraft.getMinecraft().getRenderViewEntity();

            if (entityguardian.hasTargetedEntity())
            {
                object = entityguardian.getTargetedEntity();
            }
            if (object != null)
            {
                Vec3d vec3 = ((Entity)object).getPositionEyes(0.0F);
                Vec3d vec31 = entity.getPositionEyes(0.0F);
                double d0 = vec3.yCoord - vec31.yCoord;

                if (d0 > 0.0D)
                {
                    this.guardianEye.rotationPointY = 0.0F;
                }
                else
                {
                    this.guardianEye.rotationPointY = 1.0F;
                }
                Vec3d vec32 = entity.getLook(0.0F);
                vec32 = new Vec3d(vec32.xCoord, 0.0D, vec32.zCoord);
                Vec3d vec33 = new Vec3d(vec31.xCoord - vec3.xCoord, 0.0D, vec31.zCoord - vec3.zCoord).normalize().rotateYaw((float)Math.PI / 2F);
                double d1 = vec32.dotProduct(vec33);
                this.guardianEye.rotationPointX = MathHelper.sqrt((float)Math.abs(d1)) * 2.0F * (float)Math.signum(d1);
            }
            this.guardianEye.showModel = true;
            float f8 = entityguardian.getTailAnimation(f6);
            this.guardianTail[0].rotateAngleY = MathHelper.sin(f8) * (float)Math.PI * 0.05F;
            this.guardianTail[1].rotateAngleY = MathHelper.sin(f8) * (float)Math.PI * 0.1F;
            this.guardianTail[1].rotationPointX = -1.5F;
            this.guardianTail[1].rotationPointY = 0.5F;
            this.guardianTail[1].rotationPointZ = 14.0F;
            this.guardianTail[2].rotateAngleY = MathHelper.sin(f8) * (float)Math.PI * 0.15F;
            this.guardianTail[2].rotationPointX = 0.5F;
            this.guardianTail[2].rotationPointY = 0.5F;
            this.guardianTail[2].rotationPointZ = 6.0F;
        }
    }

    public int getModelVersion()
    {
        return 54;
    }
}
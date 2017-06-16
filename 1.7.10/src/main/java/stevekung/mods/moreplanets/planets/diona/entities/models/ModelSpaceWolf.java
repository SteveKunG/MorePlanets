/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;

public class ModelSpaceWolf extends ModelBase
{
    public ModelRenderer wolfHeadMain;
    public ModelRenderer wolfBody;
    public ModelRenderer wolfLeg1;
    public ModelRenderer wolfLeg2;
    public ModelRenderer wolfLeg3;
    public ModelRenderer wolfLeg4;
    public ModelRenderer wolfTail;
    public ModelRenderer wolfMane;

    public ModelRenderer oxygenMask;
    public ModelRenderer tank1;
    public ModelRenderer tank2;
    public ModelRenderer tube1;
    public ModelRenderer tube2;
    public ModelRenderer tube3;
    public ModelRenderer tube4;
    public ModelRenderer tube5;
    public ModelRenderer tube6;
    public ModelRenderer tube7;
    public ModelRenderer tube8;
    public ModelRenderer tube9;
    public ModelRenderer tube10;
    public ModelRenderer tube11;
    public ModelRenderer tube12;
    public ModelRenderer tube13;
    public ModelRenderer tube14;
    public ModelRenderer tube15;
    public ModelRenderer tube16;
    public ModelRenderer tube17;
    public ModelRenderer tube18;

    public ModelSpaceWolf()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        float f = 0.0F;
        float f1 = 13.5F;
        this.wolfHeadMain = new ModelRenderer(this, 0, 0);
        this.wolfHeadMain.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, f);
        this.wolfHeadMain.setRotationPoint(-1.0F, f1, -7.0F);

        this.wolfBody = new ModelRenderer(this, 18, 14);
        this.wolfBody.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, f);
        this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);

        this.wolfMane = new ModelRenderer(this, 21, 0);
        this.wolfMane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, f);
        this.wolfMane.setRotationPoint(-1.0F, 14.0F, 2.0F);

        this.wolfLeg1 = new ModelRenderer(this, 0, 18);
        this.wolfLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);

        this.wolfLeg2 = new ModelRenderer(this, 0, 18);
        this.wolfLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);

        this.wolfLeg3 = new ModelRenderer(this, 0, 18);
        this.wolfLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);

        this.wolfLeg4 = new ModelRenderer(this, 0, 18);
        this.wolfLeg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);

        this.wolfTail = new ModelRenderer(this, 9, 18);
        this.wolfTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);

        this.wolfHeadMain.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.wolfHeadMain.setTextureOffset(16, 14).addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.wolfHeadMain.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);

        this.oxygenMask = new ModelRenderer(this, 0, 44);
        this.oxygenMask.addBox(-5F, -5F, -9F, 10, 10, 10);
        this.oxygenMask.setRotationPoint(-1F, 13F, -5F);

        this.tank1 = new ModelRenderer(this, 40, 46);
        this.tank1.addBox(-1.5F, -2F, -2.5F, 3, 3, 7);
        this.tank1.setRotationPoint(0.5F, 10F, 3F);

        this.tank2 = new ModelRenderer(this, 40, 46);
        this.tank2.addBox(-1.5F, -2F, -3.5F, 3, 3, 7);
        this.tank2.setRotationPoint(-2.5F, 10F, 4F);

        this.tube1 = new ModelRenderer(this, 40, 44);
        this.tube1.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube1.setRotationPoint(0.5F, 7F, 1F);

        this.tube2 = new ModelRenderer(this, 40, 44);
        this.tube2.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube2.setRotationPoint(0.5F, 6F, 0F);

        this.tube3 = new ModelRenderer(this, 40, 44);
        this.tube3.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube3.setRotationPoint(0.5F, 5F, -1F);

        this.tube4 = new ModelRenderer(this, 40, 44);
        this.tube4.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube4.setRotationPoint(0.5F, 5F, -2F);

        this.tube5 = new ModelRenderer(this, 40, 44);
        this.tube5.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube5.setRotationPoint(0.5F, 5F, -3F);

        this.tube6 = new ModelRenderer(this, 40, 44);
        this.tube6.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube6.setRotationPoint(0.5F, 6F, -4F);

        this.tube7 = new ModelRenderer(this, 40, 44);
        this.tube7.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube7.setRotationPoint(-2.5F, 7F, 1F);

        this.tube8 = new ModelRenderer(this, 40, 44);
        this.tube8.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube8.setRotationPoint(-2.5F, 6F, 0F);

        this.tube9 = new ModelRenderer(this, 40, 44);
        this.tube9.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube9.setRotationPoint(-2.5F, 5F, -1F);

        this.tube10 = new ModelRenderer(this, 40, 44);
        this.tube10.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube10.setRotationPoint(-2.5F, 5F, -2F);

        this.tube11 = new ModelRenderer(this, 40, 44);
        this.tube11.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube11.setRotationPoint(-2.5F, 5F, -3F);

        this.tube12 = new ModelRenderer(this, 40, 44);
        this.tube12.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube12.setRotationPoint(-2.5F, 6F, -4F);

        this.tube13 = new ModelRenderer(this, 40, 44);
        this.tube13.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube13.setRotationPoint(0.5F, 7F, -5F);

        this.tube14 = new ModelRenderer(this, 40, 44);
        this.tube14.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube14.setRotationPoint(-2.5F, 7F, -5F);

        this.tube15 = new ModelRenderer(this, 40, 44);
        this.tube15.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube15.setRotationPoint(-2.5F, 7F, -5F);

        this.tube16 = new ModelRenderer(this, 40, 44);
        this.tube16.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube16.setRotationPoint(-2.5F, 7F, -5F);

        this.tube17 = new ModelRenderer(this, 40, 44);
        this.tube17.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube17.setRotationPoint(-2.5F, 7F, -5F);

        this.tube18 = new ModelRenderer(this, 40, 44);
        this.tube18.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        this.tube18.setRotationPoint(-2.5F, 7F, -5F);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * par7, 2.0F * par7);
            this.wolfHeadMain.renderWithRotation(par7);
            this.oxygenMask.renderWithRotation(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.wolfBody.render(par7);
            this.wolfLeg1.render(par7);
            this.wolfLeg2.render(par7);
            this.wolfLeg3.render(par7);
            this.wolfLeg4.render(par7);
            this.wolfTail.renderWithRotation(par7);
            this.wolfMane.render(par7);
            this.tank1.render(par7);
            this.tank2.render(par7);
            this.tube1.render(par7);
            this.tube2.render(par7);
            this.tube3.render(par7);
            this.tube4.render(par7);
            this.tube5.render(par7);
            this.tube6.render(par7);
            this.tube7.render(par7);
            this.tube8.render(par7);
            this.tube9.render(par7);
            this.tube10.render(par7);
            this.tube11.render(par7);
            this.tube12.render(par7);
            this.tube13.render(par7);
            this.tube14.render(par7);
            this.tube15.render(par7);
            this.tube16.render(par7);
            this.tube17.render(par7);
            this.tube18.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.wolfHeadMain.renderWithRotation(par7);
            this.wolfBody.render(par7);
            this.wolfLeg1.render(par7);
            this.wolfLeg2.render(par7);
            this.wolfLeg3.render(par7);
            this.wolfLeg4.render(par7);
            this.wolfTail.renderWithRotation(par7);
            this.wolfMane.render(par7);
            this.oxygenMask.renderWithRotation(par7);
            this.tank1.render(par7);
            this.tank2.render(par7);
            this.tube1.render(par7);
            this.tube2.render(par7);
            this.tube3.render(par7);
            this.tube4.render(par7);
            this.tube5.render(par7);
            this.tube6.render(par7);
            this.tube7.render(par7);
            this.tube8.render(par7);
            this.tube9.render(par7);
            this.tube10.render(par7);
            this.tube11.render(par7);
            this.tube12.render(par7);
            this.tube13.render(par7);
            this.tube14.render(par7);
            this.tube15.render(par7);
            this.tube16.render(par7);
            this.tube17.render(par7);
            this.tube18.render(par7);
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        EntitySpaceWolf entitywolf = (EntitySpaceWolf)par1EntityLivingBase;

        if (entitywolf.isAngry())
        {
            this.wolfTail.rotateAngleY = 0.0F;
        }
        else
        {
            this.wolfTail.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        }

        if (entitywolf.isSitting())
        {
            this.wolfMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.wolfMane.rotateAngleX = (float)Math.PI * 2F / 5F;
            this.wolfMane.rotateAngleY = 0.0F;
            this.wolfBody.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.wolfBody.rotateAngleX = (float)Math.PI / 4F;
            this.wolfTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.wolfLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
            this.wolfLeg1.rotateAngleX = (float)Math.PI * 3F / 2F;
            this.wolfLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
            this.wolfLeg2.rotateAngleX = (float)Math.PI * 3F / 2F;
            this.wolfLeg3.rotateAngleX = 5.811947F;
            this.wolfLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.wolfLeg4.rotateAngleX = 5.811947F;
            this.wolfLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);

            this.tank1.rotateAngleX = -0.7879449F;
            this.tank1.setRotationPoint(0.5F, 16.1F, 3.75F);
            this.tank2.rotateAngleX = -0.7879449F;
            this.tank2.setRotationPoint(-2.5F, 16.8F, 4.45F);

            this.tube1.rotateAngleX = -0.7879449F;
            this.tube1.setRotationPoint(0.5F, 12.7F, 3.1F);
            this.tube2.rotateAngleX = -0.5235987F;
            this.tube2.setRotationPoint(0.5F, 11.6F, 2.7F);
            this.tube3.rotateAngleX = -0.3490658F;
            this.tube3.setRotationPoint(0.5F, 10.5F, 2.2F);
            this.tube4.rotateAngleX = -0.1745329F;
            this.tube4.setRotationPoint(0.5F, 9.4F, 1.4F);
            this.tube5.setRotationPoint(0.5F, 9.0F, 0.4F);
            this.tube6.setRotationPoint(0.5F, 8.4F, -0.5F);

            this.tube7.rotateAngleX = -0.7879449F;
            this.tube7.setRotationPoint(-2.5F, 12.7F, 3.1F);
            this.tube8.rotateAngleX = -0.5235987F;
            this.tube8.setRotationPoint(-2.5F, 11.6F, 2.7F);
            this.tube9.rotateAngleX = -0.3490658F;
            this.tube9.setRotationPoint(-2.5F, 10.5F, 2.2F);
            this.tube10.rotateAngleX = -0.1745329F;
            this.tube10.setRotationPoint(-2.5F, 9.4F, 1.4F);
            this.tube11.setRotationPoint(-2.5F, 9.0F, 0.4F);
            this.tube12.setRotationPoint(-2.5F, 8.4F, -0.5F);
            this.tube13.setRotationPoint(0.5F, 8.0F, -1.5F);
            this.tube14.setRotationPoint(-2.5F, 8.0F, -1.5F);
            this.tube15.setRotationPoint(0.5F, 8.0F, -2.5F);
            this.tube16.setRotationPoint(-2.5F, 8.0F, -2.5F);
            this.tube17.setRotationPoint(0.5F, 9.0F, -3.5F);
            this.tube18.setRotationPoint(-2.5F, 9.0F, -3.5F);
        }
        else
        {
            this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.wolfBody.rotateAngleX = (float)Math.PI / 2F;
            this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
            this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.wolfLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
            this.wolfLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
            this.wolfLeg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
            this.wolfLeg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;

            this.tank1.rotateAngleX = 0F;
            this.tank1.setRotationPoint(0.5F, 10F, 3F);
            this.tank2.rotateAngleX = 0F;
            this.tank2.setRotationPoint(-2.5F, 10F, 4F);

            this.tube1.rotateAngleX = 0F;
            this.tube1.setRotationPoint(0.5F, 7F, 1F);
            this.tube2.rotateAngleX = 0F;
            this.tube2.setRotationPoint(0.5F, 6F, 0F);
            this.tube3.rotateAngleX = 0F;
            this.tube3.setRotationPoint(0.5F, 5F, -1F);
            this.tube4.rotateAngleX = 0F;
            this.tube4.setRotationPoint(0.5F, 5F, -2F);
            this.tube5.setRotationPoint(0.5F, 5F, -3F);
            this.tube6.setRotationPoint(0.5F, 6F, -4F);
            this.tube7.rotateAngleX = 0F;
            this.tube7.setRotationPoint(-2.5F, 7F, 1F);
            this.tube8.rotateAngleX = 0F;
            this.tube8.setRotationPoint(-2.5F, 6F, 0F);
            this.tube9.rotateAngleX = 0F;
            this.tube9.setRotationPoint(-2.5F, 5F, -1F);
            this.tube10.rotateAngleX = 0F;
            this.tube10.setRotationPoint(-2.5F, 5F, -2F);
            this.tube11.setRotationPoint(-2.5F, 5F, -3F);
            this.tube12.setRotationPoint(-2.5F, 6F, -4F);
            this.tube13.setRotationPoint(0.5F, 7F, -5F);
            this.tube14.setRotationPoint(-2.5F, 7F, -5F);
            this.tube15.setRotationPoint(-2.5F, 7F, -5F);
            this.tube16.setRotationPoint(-2.5F, 7F, -5F);
            this.tube17.setRotationPoint(-2.5F, 7F, -5F);
            this.tube18.setRotationPoint(-2.5F, 7F, -5F);
        }

        this.oxygenMask.rotateAngleZ = entitywolf.getInterestedAngle(par4) + entitywolf.getShakeAngle(par4, 0.0F);
        this.wolfHeadMain.rotateAngleZ = entitywolf.getInterestedAngle(par4) + entitywolf.getShakeAngle(par4, 0.0F);
        this.wolfMane.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.08F);
        this.wolfBody.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.16F);
        this.wolfTail.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.2F);

        this.tank1.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tank2.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube1.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube2.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube3.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube4.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube5.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube6.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube7.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube8.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube9.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube10.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube11.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube12.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube13.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube14.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube15.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube16.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube17.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
        this.tube18.rotateAngleZ = entitywolf.getShakeAngle(par4, -0.14F);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.wolfHeadMain.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.wolfHeadMain.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.oxygenMask.rotateAngleX = this.wolfHeadMain.rotateAngleX;
        this.oxygenMask.rotateAngleY = this.wolfHeadMain.rotateAngleY;
        this.wolfTail.rotateAngleX = par3;
    }
}
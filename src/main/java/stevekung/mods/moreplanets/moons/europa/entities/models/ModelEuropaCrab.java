/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.entities.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEuropaCrab extends ModelBase
{
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer eye1;
    ModelRenderer eye2;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg5;
    ModelRenderer leg6;
    ModelRenderer leg7;
    ModelRenderer leg8;
    ModelRenderer crableg1;
    ModelRenderer crableg2;

    public ModelEuropaCrab()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body1 = new ModelRenderer(this, 28, 0);
        this.body1.addBox(-5F, 0F, -4F, 10, 2, 8);
        this.body1.setRotationPoint(0F, 22F, 0F);
        this.body2 = new ModelRenderer(this, 28, 10);
        this.body2.addBox(-4F, 0F, -3F, 8, 1, 6);
        this.body2.setRotationPoint(0F, 21.5F, 0F);
        this.body3 = new ModelRenderer(this, 28, 17);
        this.body3.addBox(-3F, 0F, -2F, 6, 1, 4);
        this.body3.setRotationPoint(0F, 21F, 0F);
        this.eye1 = new ModelRenderer(this, 0, 29);
        this.eye1.addBox(0F, 0F, 0F, 1, 2, 1);
        this.eye1.setRotationPoint(2F, 20.5F, -4F);
        this.eye2 = new ModelRenderer(this, 0, 29);
        this.eye2.addBox(0F, 0F, 0F, 1, 2, 1);
        this.eye2.setRotationPoint(-3F, 20.5F, -4F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(0F, 0F, -0.5F, 4, 1, 1);
        this.leg1.setRotationPoint(5F, 23F, 2F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(0F, 0F, -0.5F, 4, 1, 1);
        this.leg2.setRotationPoint(5F, 23F, 0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(0F, 0F, -0.5F, 4, 1, 1);
        this.leg3.setRotationPoint(5F, 23F, -2F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-4F, 0F, -0.5F, 4, 1, 1);
        this.leg4.setRotationPoint(-5F, 23F, 2F);
        this.leg5 = new ModelRenderer(this, 0, 16);
        this.leg5.addBox(-4F, 0F, -0.5F, 4, 1, 1);
        this.leg5.setRotationPoint(-5F, 23F, 0F);
        this.leg6 = new ModelRenderer(this, 0, 16);
        this.leg6.addBox(-4F, 0F, -0.5F, 4, 1, 1);
        this.leg6.setRotationPoint(-5F, 23F, -2F);
        this.leg7 = new ModelRenderer(this, 0, 16);
        this.leg7.addBox(-3F, 0F, -0.5F, 3, 1, 1);
        this.leg7.setRotationPoint(-5F, 23F, 2.8F);
        this.setRotation(this.leg7, 0F, 0.1745329F, 0F);
        this.leg8 = new ModelRenderer(this, 0, 16);
        this.leg8.addBox(0F, 0F, -0.5F, 3, 1, 1);
        this.leg8.setRotationPoint(5F, 23F, 2.8F);
        this.setRotation(this.leg8, 0F, -0.1745329F, 0F);
        this.crableg1 = new ModelRenderer(this, 0, 8);
        this.crableg1.addBox(-3F, 0F, -1F, 3, 1, 2);
        this.crableg1.setRotationPoint(4F, 22.5F, -4.5F);
        this.setRotation(this.crableg1, 0F, -0.2443461F, 0F);
        this.crableg2 = new ModelRenderer(this, 0, 8);
        this.crableg2.addBox(0F, 0F, -1F, 3, 1, 2);
        this.crableg2.setRotationPoint(-4F, 22.5F, -4.5F);
        this.setRotation(this.crableg2, 0F, 0.2443461F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(90.0F, 0.0F, 270.0F, 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            this.body1.render(f5);
            this.body2.render(f5);
            this.body3.render(f5);
            this.eye1.render(f5);
            this.eye2.render(f5);
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.leg4.render(f5);
            this.leg5.render(f5);
            this.leg6.render(f5);
            this.leg7.render(f5);
            this.leg8.render(f5);
            this.crableg1.render(f5);
            this.crableg2.render(f5);
            GL11.glPopMatrix();
        }
        else
        {
            GL11.glPushMatrix();
            GL11.glRotatef(90.0F, 0.0F, 270.0F, 0.0F);
            this.body1.render(f5);
            this.body2.render(f5);
            this.body3.render(f5);
            this.eye1.render(f5);
            this.eye2.render(f5);
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.leg4.render(f5);
            this.leg5.render(f5);
            this.leg6.render(f5);
            this.leg7.render(f5);
            this.leg8.render(f5);
            this.crableg1.render(f5);
            this.crableg2.render(f5);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.leg1.rotateAngleY = MathHelper.cos(p_78087_1_ * 3.6662F + (float)Math.PI) * -1.0F * p_78087_2_;
        this.leg2.rotateAngleY = MathHelper.cos(p_78087_1_ * 0.6662F) * 0.5F * p_78087_2_;
        this.leg3.rotateAngleY = MathHelper.cos(p_78087_1_ * 3.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
        this.leg4.rotateAngleY = MathHelper.cos(p_78087_1_ * 3.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
        this.leg5.rotateAngleY = MathHelper.cos(p_78087_1_ * 0.6662F) * 0.5F * p_78087_2_;
        this.leg6.rotateAngleY = MathHelper.cos(p_78087_1_ * 3.6662F + (float)Math.PI) * -1.0F * p_78087_2_;
        this.leg7.rotateAngleY = 0.1745329F + MathHelper.cos(p_78087_1_ * 3.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
        this.leg8.rotateAngleY = -0.1745329F + MathHelper.cos(p_78087_1_ * 3.6662F + (float)Math.PI) * 1.0F * p_78087_2_;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.models.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMelon extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer top3;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public ModelMelon()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;

        this.body = new ModelRenderer(this, 0, 48);
        this.body.addBox(0F, 0F, 0F, 24, 24, 24, 0F);
        this.body.setRotationPoint(-12F, -6F, -12F);

        this.top1 = new ModelRenderer(this, 120, 0);
        this.top1.addBox(0F, 0F, 0F, 34, 8, 6, 0F);
        this.top1.setRotationPoint(-17F, -14F, -3F);

        this.top2 = new ModelRenderer(this, 80, 20);
        this.top2.addBox(0F, 0F, 0F, 5, 8, 6, 0F);
        this.top2.setRotationPoint(-17F, -22F, -3F);

        this.top3 = new ModelRenderer(this, 80, 0);
        this.top3.addBox(0F, 0F, 0F, 5, 8, 6, 0F);
        this.top3.setRotationPoint(12F, -22F, -3F);

        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.addBox(-2F, 0F, -2F, 4, 6, 4, 0F);
        this.leg1.setRotationPoint(-5F, 18F, -4F);

        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.addBox(-2F, 0F, -2F, 4, 6, 4, 0F);
        this.leg2.setRotationPoint(5F, 18F, 5F);

        this.leg3 = new ModelRenderer(this, 0, 0);
        this.leg3.addBox(-2F, 0F, -2F, 4, 6, 4, 0F);
        this.leg3.setRotationPoint(-5F, 18F, 5F);

        this.leg4 = new ModelRenderer(this, 0, 0);
        this.leg4.addBox(-2F, 0F, -2F, 4, 6, 4, 0F);
        this.leg4.setRotationPoint(5F, 18F, -5F);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        if (this.isChild)
        {
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.body.render(par7);
            this.top1.render(par7);
            this.top2.render(par7);
            this.top3.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.leg3.render(par7);
            this.leg4.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.body.render(par7);
            this.top1.render(par7);
            this.top2.render(par7);
            this.top3.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.leg3.render(par7);
            this.leg4.render(par7);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.0F * par2;
        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.0F * par2;
    }
}
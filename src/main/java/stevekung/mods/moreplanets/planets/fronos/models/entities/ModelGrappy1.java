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

public class ModelGrappy1 extends ModelBase
{
    public ModelRenderer wool;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer top;

    public ModelGrappy1()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.wool = new ModelRenderer(this, 40, 20);
        this.wool.addBox(0F, 0F, 0F, 4, 4, 4, 0F);
        this.wool.setRotationPoint(-2F, 13F, -2F);
        this.body = new ModelRenderer(this, 68, 0);
        this.body.addBox(0F, 0F, 0F, 10, 4, 14, 0F);
        this.body.setRotationPoint(-5F, 18F, -7F);
        this.leg1 = new ModelRenderer(this, 0, 20);
        this.leg1.addBox(-1F, 0F, -1F, 2, 3, 2, 0F);
        this.leg1.setRotationPoint(4.5F, 21F, -4.5F);
        this.leg2 = new ModelRenderer(this, 0, 20);
        this.leg2.addBox(-1F, 0F, -1F, 2, 3, 2, 0F);
        this.leg2.setRotationPoint(-4.5F, 21F, -4.5F);
        this.leg3 = new ModelRenderer(this, 0, 20);
        this.leg3.addBox(-1F, 0F, -1F, 2, 3, 2, 0F);
        this.leg3.setRotationPoint(-4.5F, 21F, 4.5F);
        this.leg4 = new ModelRenderer(this, 0, 20);
        this.leg4.addBox(-1F, 0F, -1F, 2, 3, 2, 0F);
        this.leg4.setRotationPoint(4.5F, 21F, 4.5F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.addBox(0F, 0F, 0F, 8, 3, 6, 0F);
        this.top.setRotationPoint(-4F, 16.5F, -3F);
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
            this.wool.render(par7);
            this.body.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.leg3.render(par7);
            this.leg4.render(par7);
            this.top.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.wool.render(par7);
            this.body.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.leg3.render(par7);
            this.leg4.render(par7);
            this.top.render(par7);
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
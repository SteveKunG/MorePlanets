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

public class ModelStarfish extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer star1;
    public ModelRenderer star2;
    public ModelRenderer star3;
    public ModelRenderer star4;
    public ModelRenderer star5;
    public ModelRenderer starbit1;
    public ModelRenderer starbit2;
    public ModelRenderer starbit3;
    public ModelRenderer starbit4;
    public ModelRenderer starbit5;

    public ModelStarfish()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body = new ModelRenderer(this, 24, 0);
        this.body.addBox(0F, 0F, 0F, 6, 1, 6, 0F);
        this.body.setRotationPoint(-3F, 23F, -3F);

        this.star1 = new ModelRenderer(this, 0, 0);
        this.star1.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.star1.setRotationPoint(-3.5F, 23F, 1.5F);

        this.star2 = new ModelRenderer(this, 0, 0);
        this.star2.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.star2.setRotationPoint(1.5F, 23F, 1.5F);

        this.star3 = new ModelRenderer(this, 0, 0);
        this.star3.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.star3.setRotationPoint(2.5F, 23F, -2.5F);

        this.star4 = new ModelRenderer(this, 0, 0);
        this.star4.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.star4.setRotationPoint(-4.5F, 23F, -2.5F);

        this.star5 = new ModelRenderer(this, 0, 0);
        this.star5.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.star5.setRotationPoint(-1F, 23F, -4.5F);

        this.starbit1 = new ModelRenderer(this, 0, 0);
        this.starbit1.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.starbit1.setRotationPoint(-4F, 23F, 3F);

        this.starbit2 = new ModelRenderer(this, 0, 0);
        this.starbit2.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.starbit2.setRotationPoint(3F, 23F, 3F);

        this.starbit3 = new ModelRenderer(this, 0, 0);
        this.starbit3.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.starbit3.setRotationPoint(4.5F, 23F, -2F);

        this.starbit4 = new ModelRenderer(this, 0, 0);
        this.starbit4.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.starbit4.setRotationPoint(-5.5F, 23F, -2F);

        this.starbit5 = new ModelRenderer(this, 0, 0);
        this.starbit5.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.starbit5.setRotationPoint(-0.5F, 23F, -5.5F);
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
            this.star1.render(par7);
            this.star2.render(par7);
            this.star3.render(par7);
            this.star4.render(par7);
            this.star5.render(par7);
            this.starbit1.render(par7);
            this.starbit2.render(par7);
            this.starbit3.render(par7);
            this.starbit4.render(par7);
            this.starbit5.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.body.render(par7);
            this.star1.render(par7);
            this.star2.render(par7);
            this.star3.render(par7);
            this.star4.render(par7);
            this.star5.render(par7);
            this.starbit1.render(par7);
            this.starbit2.render(par7);
            this.starbit3.render(par7);
            this.starbit4.render(par7);
            this.starbit5.render(par7);
        }
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
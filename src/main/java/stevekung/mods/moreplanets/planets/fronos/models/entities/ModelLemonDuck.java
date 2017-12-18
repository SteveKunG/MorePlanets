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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityLemonDuck;

public class ModelLemonDuck extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer mouth1;
    public ModelRenderer mouth2;
    public ModelRenderer mouth3;
    public ModelRenderer mouth4;

    public ModelLemonDuck()
    {
        this.body = new ModelRenderer(this, 36, 0);
        this.body.addBox(0F, 0F, 0F, 6, 6, 6, 0F);
        this.body.setRotationPoint(-3F, 14F, -3F);
        this.leg1 = new ModelRenderer(this, 0, 24);
        this.leg1.addBox(-1.5F, 0F, -1.5F, 3, 4, 3, 0F);
        this.leg1.setRotationPoint(-1.5F, 20F, 0F);
        this.leg2 = new ModelRenderer(this, 13, 24);
        this.leg2.addBox(-1.5F, 0F, -1.5F, 3, 4, 3, 0F);
        this.leg2.setRotationPoint(1.5F, 20F, 0F);
        this.mouth1 = new ModelRenderer(this, 4, 0);
        this.mouth1.addBox(0F, 0F, 0F, 3, 1, 2, 0F);
        this.mouth1.setRotationPoint(-1.5F, 16F, -5F);
        this.mouth2 = new ModelRenderer(this, 4, 0);
        this.mouth2.addBox(0F, 0F, 0F, 3, 1, 2, 0F);
        this.mouth2.setRotationPoint(-1.5F, 18F, -5F);
        this.mouth3 = new ModelRenderer(this, 4, 0);
        this.mouth3.addBox(0F, 0F, 0F, 1, 1, 2, 0F);
        this.mouth3.setRotationPoint(-1.5F, 17F, -5F);
        this.mouth4 = new ModelRenderer(this, 4, 0);
        this.mouth4.addBox(0F, 0F, 0F, 1, 1, 2, 0F);
        this.mouth4.setRotationPoint(0.5F, 17F, -5F);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        EntityLemonDuck duck = (EntityLemonDuck)par1EntityLivingBase;

        if (duck.isSitting())
        {
            this.body.setRotationPoint(-3F, 18F, -3F);
            this.mouth1.setRotationPoint(-1.5F, 20F, -5F);
            this.mouth2.setRotationPoint(-1.5F, 22F, -5F);
            this.mouth3.setRotationPoint(-1.5F, 21F, -5F);
            this.mouth4.setRotationPoint(0.5F, 21F, -5F);
        }
        else
        {
            this.body.setRotationPoint(-3F, 14F, -3F);
            this.mouth1.setRotationPoint(-1.5F, 16F, -5F);
            this.mouth2.setRotationPoint(-1.5F, 18F, -5F);
            this.mouth3.setRotationPoint(-1.5F, 17F, -5F);
            this.mouth4.setRotationPoint(0.5F, 17F, -5F);
        }
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
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.mouth1.render(par7);
            this.mouth2.render(par7);
            this.mouth3.render(par7);
            this.mouth4.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.body.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.mouth1.render(par7);
            this.mouth2.render(par7);
            this.mouth3.render(par7);
            this.mouth4.render(par7);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.0F * par2;
    }
}
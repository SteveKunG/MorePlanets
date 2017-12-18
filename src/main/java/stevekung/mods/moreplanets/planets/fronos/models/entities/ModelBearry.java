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
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBearry;

public class ModelBearry extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer hand1;
    public ModelRenderer hand2;
    public ModelRenderer breach;

    public ModelBearry()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0F, 0F, -4F, 10, 10, 10, 0F);
        this.body.setRotationPoint(-5F, 10F, -1F);
        this.body.mirror = true;
        this.leg1 = new ModelRenderer(this, 60, 0);
        this.leg1.addBox(-1F, 0F, -1F, 3, 4, 3, 0F);
        this.leg1.setRotationPoint(-3F, 20F, -1F);
        this.leg1.mirror = true;
        this.leg2 = new ModelRenderer(this, 60, 0);
        this.leg2.addBox(-1F, 0F, -1F, 3, 4, 3, 0F);
        this.leg2.setRotationPoint(2F, 20F, -1F);
        this.leg2.mirror = true;
        this.hand1 = new ModelRenderer(this, 60, 10);
        this.hand1.addBox(0F, 0F, -0.5F, 1, 4, 1, 0F);
        this.hand1.setRotationPoint(4.5F, 15F, 0F);
        this.hand1.rotateAngleZ = -0.7853982F;
        this.hand1.mirror = true;
        this.hand2 = new ModelRenderer(this, 60, 10);
        this.hand2.addBox(-1F, 0F, -0.5F, 1, 4, 1, 0F);
        this.hand2.setRotationPoint(-4.5F, 15F, 0F);
        this.hand2.rotateAngleZ = 0.7853982F;
        this.hand2.mirror = true;
        this.breach = new ModelRenderer(this, 0, 30);
        this.breach.addBox(0F, 0F, 0F, 2, 6, 2, 0F);
        this.breach.setRotationPoint(-1F, 4F, -1F);
        this.breach.mirror = true;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        EntityBearry bearry = (EntityBearry)par1EntityLivingBase;

        if (bearry.isSitting())
        {
            this.body.setRotationPoint(-5F, 14.1F, -1F);
            this.hand1.setRotationPoint(4.5F, 19F, 0F);
            this.hand2.setRotationPoint(-4.5F, 19F, 0F);
            this.breach.setRotationPoint(-1F, 8F, -1F);
        }
        else
        {
            this.body.setRotationPoint(-5F, 10F, -1F);
            this.hand1.setRotationPoint(4.5F, 15F, 0F);
            this.hand2.setRotationPoint(-4.5F, 15F, 0F);
            this.breach.setRotationPoint(-1F, 4F, -1F);
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
            this.hand1.render(par7);
            this.hand2.render(par7);
            this.breach.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.body.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.breach.render(par7);
            this.hand1.render(par7);
            this.hand2.render(par7);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
    }
}
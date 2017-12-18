/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaCreeperBoss;

public class ModelDionaCreeperBoss extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer neckRight;
    public ModelRenderer neckLeft;
    public ModelRenderer headLeft;
    public ModelRenderer headRight;

    public ModelDionaCreeperBoss()
    {
        this(0.0F);
    }

    public ModelDionaCreeperBoss(float par1)
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        final byte b0 = 4;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.head.setRotationPoint(0.0F, b0, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.body.setRotationPoint(0.0F, b0, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg1.setRotationPoint(-2.0F, 12 + b0, 4.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg2.setRotationPoint(2.0F, 12 + b0, 4.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg3.setRotationPoint(-2.0F, 12 + b0, -4.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg4.setRotationPoint(2.0F, 12 + b0, -4.0F);
        this.top1 = new ModelRenderer(this, 16, 0);
        this.top1.addBox(-1F, -5F, -1F, 2, 5, 2, 0F);
        this.top1.setRotationPoint(0F, -2F, 0F);
        this.top2 = new ModelRenderer(this, 40, 8);
        this.top2.addBox(-2.6F, -4F, -1.5F, 5, 5, 3, 0F);
        this.top2.setRotationPoint(0F, -7.5F, 0F);

        this.neckRight = new ModelRenderer(this, 16, 20);
        this.neckRight.mirror = true;
        this.neckRight.addBox(-2.5F, -9F, -1.5F, 5, 9, 3, par1);
        this.neckRight.setRotationPoint(-3F, 10F, 0F);
        this.neckRight.setTextureSize(128, 64);
        this.neckRight.mirror = true;
        this.setRotation(this.neckRight, 0F, 0F, -1.169371F);
        this.neckRight.mirror = false;
        this.neckLeft = new ModelRenderer(this, 16, 20);
        this.neckLeft.addBox(-2.5F, -9F, -1.5F, 5, 9, 3, par1);
        this.neckLeft.setRotationPoint(3F, 10F, 0F);
        this.neckLeft.setTextureSize(128, 64);
        this.neckLeft.mirror = true;
        this.setRotation(this.neckLeft, 0F, 0F, 1.169371F);

        this.headLeft = new ModelRenderer(this, 0, 0);
        this.headLeft.addBox(1F, -9F, -4F, 8, 8, 8, par1);
        this.headLeft.setRotationPoint(3F, 6F, 0.1F);
        this.headLeft.setTextureSize(128, 64);
        this.headLeft.mirror = true;
        this.setRotation(this.headLeft, 0F, 0F, 0.7853982F);
        this.headRight = new ModelRenderer(this, 0, 0);
        this.headRight.addBox(-9F, -9F, -4F, 8, 8, 8, par1);
        this.headRight.setRotationPoint(-3F, 6F, -0.1F);
        this.headRight.setTextureSize(128, 64);
        this.headRight.mirror = true;
        this.setRotation(this.headRight, 0F, 0F, -0.7853982F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        final EntityDionaCreeperBoss creeper = (EntityDionaCreeperBoss) par1Entity;

        if (creeper.healRemaining == 0)
        {
            this.neckLeft.render(par7);
            this.neckRight.render(par7);
        }
        else if (creeper.healRemaining == 1)
        {
            this.head.render(par7);
            this.top1.render(par7);
            this.top2.render(par7);
            this.headLeft.render(par7);
            this.neckLeft.render(par7);
            this.headRight.render(par7);
            this.neckRight.render(par7);
        }
        this.body.render(par7);
        this.leg1.render(par7);
        this.leg2.render(par7);
        this.leg3.render(par7);
        this.leg4.render(par7);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.top1.rotateAngleY = this.head.rotateAngleY;
        this.top1.rotateAngleX = this.head.rotateAngleX;
        this.top2.rotateAngleY = this.head.rotateAngleY;
        this.top2.rotateAngleX = this.head.rotateAngleX;
    }
}
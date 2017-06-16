/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEvolvedEnderman extends ModelBiped
{
    public boolean isCarrying;
    public boolean isAttacking;

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

    public ModelEvolvedEnderman()
    {
        super(0.0F, -14.0F, 64, 64);
        final float f = -14.0F;
        final float f1 = 0.0F;
        this.bipedHeadwear = new ModelRenderer(this, 0, 16);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f1 - 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f, 0.0F);
        this.bipedBody = new ModelRenderer(this, 32, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, f1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + f, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 56, 0);
        this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, f1);
        this.bipedRightArm.setRotationPoint(-3.0F, 2.0F + f, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, f1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + f, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 56, 0);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, f1);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + f, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 56, 0);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, f1);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + f, 0.0F);

        this.oxygenMask = new ModelRenderer(this, 0, 44);
        this.oxygenMask.addBox(-5F, -9F, -5F, 10, 10, 10);
        this.oxygenMask.setRotationPoint(0F, -14F, 0F);

        this.tank1 = new ModelRenderer(this, 52, 54);
        this.tank1.addBox(0F, 0F, 0F, 3, 7, 3);
        this.tank1.setRotationPoint(0F, -11F, 2F);

        this.tank2 = new ModelRenderer(this, 52, 54);
        this.tank2.addBox(0F, 0F, 0F, 3, 7, 3);
        this.tank2.setRotationPoint(-3F, -11F, 2F);

        this.tube1 = new ModelRenderer(this, 44, 62);
        this.tube1.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube1.setRotationPoint(-2F, -10.5F, 5F);

        this.tube2 = new ModelRenderer(this, 44, 62);
        this.tube2.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube2.setRotationPoint(-2F, -11F, 6F);

        this.tube3 = new ModelRenderer(this, 44, 62);
        this.tube3.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube3.setRotationPoint(-2F, -12F, 7F);

        this.tube4 = new ModelRenderer(this, 44, 62);
        this.tube4.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube4.setRotationPoint(-2F, -13F, 7F);

        this.tube5 = new ModelRenderer(this, 44, 62);
        this.tube5.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube5.setRotationPoint(-2F, -14F, 7F);

        this.tube6 = new ModelRenderer(this, 44, 62);
        this.tube6.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube6.setRotationPoint(-2F, -15F, 6F);

        this.tube7 = new ModelRenderer(this, 44, 62);
        this.tube7.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube7.setRotationPoint(-2F, -16F, 5F);

        this.tube8 = new ModelRenderer(this, 44, 62);
        this.tube8.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube8.setRotationPoint(1F, -10.5F, 5F);

        this.tube9 = new ModelRenderer(this, 44, 62);
        this.tube9.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube9.setRotationPoint(1F, -11F, 6F);

        this.tube10 = new ModelRenderer(this, 44, 62);
        this.tube10.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube10.setRotationPoint(1F, -12F, 7F);

        this.tube11 = new ModelRenderer(this, 44, 62);
        this.tube11.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube11.setRotationPoint(1F, -13F, 7F);

        this.tube12 = new ModelRenderer(this, 44, 62);
        this.tube12.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube12.setRotationPoint(1F, -14F, 7F);

        this.tube13 = new ModelRenderer(this, 44, 62);
        this.tube13.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube13.setRotationPoint(1F, -15F, 6F);

        this.tube14 = new ModelRenderer(this, 44, 62);
        this.tube14.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube14.setRotationPoint(1F, -16F, 5F);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        this.oxygenMask.render(par7);
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
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        this.bipedHead.showModel = true;
        final float f6 = -14.0F;
        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedBody.rotationPointY = f6;
        this.bipedBody.rotationPointZ = -0.0F;
        this.bipedRightLeg.rotateAngleX -= 0.0F;
        this.bipedLeftLeg.rotateAngleX -= 0.0F;
        this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX * 0.5D);
        this.bipedLeftArm.rotateAngleX = (float)(this.bipedLeftArm.rotateAngleX * 0.5D);
        this.bipedRightLeg.rotateAngleX = (float)(this.bipedRightLeg.rotateAngleX * 0.5D);
        this.bipedLeftLeg.rotateAngleX = (float)(this.bipedLeftLeg.rotateAngleX * 0.5D);
        final float f7 = 0.4F;

        if (this.bipedRightArm.rotateAngleX > f7)
        {
            this.bipedRightArm.rotateAngleX = f7;
        }

        if (this.bipedLeftArm.rotateAngleX > f7)
        {
            this.bipedLeftArm.rotateAngleX = f7;
        }

        if (this.bipedRightArm.rotateAngleX < -f7)
        {
            this.bipedRightArm.rotateAngleX = -f7;
        }

        if (this.bipedLeftArm.rotateAngleX < -f7)
        {
            this.bipedLeftArm.rotateAngleX = -f7;
        }

        if (this.bipedRightLeg.rotateAngleX > f7)
        {
            this.bipedRightLeg.rotateAngleX = f7;
        }

        if (this.bipedLeftLeg.rotateAngleX > f7)
        {
            this.bipedLeftLeg.rotateAngleX = f7;
        }

        if (this.bipedRightLeg.rotateAngleX < -f7)
        {
            this.bipedRightLeg.rotateAngleX = -f7;
        }

        if (this.bipedLeftLeg.rotateAngleX < -f7)
        {
            this.bipedLeftLeg.rotateAngleX = -f7;
        }

        if (this.isCarrying)
        {
            this.bipedRightArm.rotateAngleX = -0.5F;
            this.bipedLeftArm.rotateAngleX = -0.5F;
            this.bipedRightArm.rotateAngleZ = 0.05F;
            this.bipedLeftArm.rotateAngleZ = -0.05F;
        }

        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.0F;
        this.bipedLeftLeg.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotationPointY = 9.0F + f6;
        this.bipedLeftLeg.rotationPointY = 9.0F + f6;
        this.bipedHead.rotationPointZ = -0.0F;
        this.bipedHead.rotationPointY = f6 + 1.0F;
        this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;

        this.oxygenMask.rotationPointX = this.bipedHead.rotationPointX;
        this.oxygenMask.rotationPointY = this.bipedHead.rotationPointY;
        this.oxygenMask.rotationPointZ = this.bipedHead.rotationPointZ;
        this.oxygenMask.rotateAngleX = this.bipedHead.rotateAngleX;
        this.oxygenMask.rotateAngleY = this.bipedHead.rotateAngleY;
        this.oxygenMask.rotateAngleZ = this.bipedHead.rotateAngleZ;

        if (this.isAttacking)
        {
            final float f8 = 1.0F;
            this.bipedHead.rotationPointY -= f8 * 5.0F;
        }
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;

public class ModelSiriusMagmaCube extends ModelBase
{
    public ModelRenderer[] field_78109_a = new ModelRenderer[8];
    public ModelRenderer field_78108_b;

    public ModelSiriusMagmaCube()
    {
        for (int i = 0; i < this.field_78109_a.length; ++i)
        {
            byte b0 = 0;
            int j = i;

            if (i == 2)
            {
                b0 = 24;
                j = 10;
            }
            else if (i == 3)
            {
                b0 = 24;
                j = 19;
            }
            this.field_78109_a[i] = new ModelRenderer(this, b0, j);
            this.field_78109_a[i].addBox(-4.0F, 16 + i, -4.0F, 8, 1, 8);
        }
        this.field_78108_b = new ModelRenderer(this, 0, 16);
        this.field_78108_b.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        final EntitySiriusMagmaCube siriusMagma = (EntitySiriusMagmaCube)par1EntityLivingBase;
        float f3 = siriusMagma.prevSquishFactor + (siriusMagma.squishFactor - siriusMagma.prevSquishFactor) * par4;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }
        for (int i = 0; i < this.field_78109_a.length; ++i)
        {
            this.field_78109_a[i].rotationPointY = -(4 - i) * f3 * 1.7F;
        }
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.field_78108_b.render(par7);

        for (ModelRenderer element : this.field_78109_a)
        {
            element.render(par7);
        }
    }
}
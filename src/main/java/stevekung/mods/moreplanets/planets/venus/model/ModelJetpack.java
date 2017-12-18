/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJetpack extends ModelBiped
{
    ModelRenderer tank1;
    ModelRenderer tank2;
    ModelRenderer tank3;

    public ModelJetpack(float scale)
    {
        this.textureWidth = 256;
        this.textureHeight = 128;

        this.tank1 = new ModelRenderer(this, 48, 0);
        this.tank1.addBox(0F, 0F, 0F, 4, 11, 4);
        this.tank1.setRotationPoint(-5F, 0F, 2F);
        this.setRotation(this.tank1, 0F, 0F, 0F);
        this.tank2 = new ModelRenderer(this, 32, 0);
        this.tank2.addBox(0F, 0F, 0F, 4, 11, 4);
        this.tank2.setRotationPoint(1F, 0F, 2F);
        this.setRotation(this.tank2, 0F, 0F, 0F);
        this.tank3 = new ModelRenderer(this, 56, 21);
        this.tank3.addBox(0F, 0F, 0F, 2, 9, 2);
        this.tank3.setRotationPoint(-1F, 1F, 2F);
        this.setRotation(this.tank3, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.tank1.render(f5);
        this.tank2.render(f5);
        this.tank3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        if (this.isSneak)
        {
            this.tank1.rotateAngleX = 0.5F;
            this.tank2.rotateAngleX = 0.5F;
            this.tank3.rotateAngleX = 0.5F;
        }
        else
        {
            this.tank1.rotateAngleX = 0.0F;
            this.tank2.rotateAngleX = 0.0F;
            this.tank3.rotateAngleX = 0.0F;
        }
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
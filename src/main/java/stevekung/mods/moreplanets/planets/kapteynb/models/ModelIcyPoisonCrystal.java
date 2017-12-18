/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelIcyPoisonCrystal extends ModelBase
{
    public ModelRenderer crystal;

    public ModelIcyPoisonCrystal()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.crystal = new ModelRenderer(this, 0, 0);
        this.crystal.addBox(-16.0F, -16.0F, 0.0F, 16, 16, 16);
        this.crystal.setRotationPoint(0.0F, 32.0F, 0.0F);
        this.setRotation(this.crystal, 0.7071F, 0.0F, 0.7071F);
    }

    public void render()
    {
        this.crystal.render(0.0625F);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
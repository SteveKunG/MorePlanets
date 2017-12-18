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

public class ModelLaser extends ModelBase
{
    public ModelRenderer laser;

    public ModelLaser()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.laser = new ModelRenderer(this, 0, 0);
        this.laser.addBox(0F, 0F, 0F, 1, 1, 2, 0F);
        this.laser.setRotationPoint(0F, 0F, 0F);
        this.laser.mirror = true;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.laser.render(f5);
    }
}
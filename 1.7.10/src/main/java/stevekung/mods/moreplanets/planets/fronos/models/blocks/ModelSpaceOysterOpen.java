/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSpaceOysterOpen extends ModelBase
{
    public ModelRenderer bottom;
    public ModelRenderer top;
    public ModelRenderer core;

    public ModelSpaceOysterOpen()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.bottom = new ModelRenderer(this, 0, 0);
        this.bottom.addBox(-4F, 0F, -4F, 8, 2, 8, 0F);
        this.bottom.setRotationPoint(0F, 22F, 0F);

        this.top = new ModelRenderer(this, 0, 22);
        this.top.addBox(-4F, -1F, -8F, 8, 2, 8, 0F);
        this.top.setRotationPoint(0F, 21.2F, 4F);
        this.top.rotateAngleX = -0.5934119F;

        this.core = new ModelRenderer(this, 53, 0);
        this.core.addBox(-1F, -1F, -1.5F, 2, 2, 2, 0F);
        this.core.setRotationPoint(0F, 21F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bottom.render(f5);
        this.top.render(f5);
        this.core.render(f5);
    }

    public void renderAll()
    {
        this.bottom.render(0.0625F);
        this.top.render(0.0625F);
        this.core.render(0.0625F);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
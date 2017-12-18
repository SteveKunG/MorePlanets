/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEmptyCup extends ModelBase
{
    public ModelRenderer bottom;
    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer side3;
    public ModelRenderer side4;
    public ModelRenderer hub1;
    public ModelRenderer hub2;
    public ModelRenderer hub3;
    public ModelRenderer hub4;
    public ModelRenderer hub5;

    public ModelEmptyCup()
    {
        this.bottom = new ModelRenderer(this, 0, 0);
        this.bottom.addBox(0F, 0F, 0F, 4, 1, 4, 0F);
        this.bottom.setRotationPoint(-2F, 23F, -2F);

        this.side1 = new ModelRenderer(this, 0, 0);
        this.side1.addBox(0F, 0F, 0F, 1, 6, 4, 0F);
        this.side1.setRotationPoint(2F, 17F, -2F);

        this.side2 = new ModelRenderer(this, 0, 0);
        this.side2.addBox(0F, 0F, 0F, 1, 6, 4, 0F);
        this.side2.setRotationPoint(-3F, 17F, -2F);

        this.side3 = new ModelRenderer(this, 0, 0);
        this.side3.addBox(0F, 0F, 0F, 4, 6, 1, 0F);
        this.side3.setRotationPoint(-2F, 17F, 2F);

        this.side4 = new ModelRenderer(this, 0, 0);
        this.side4.addBox(0F, 0F, 0F, 4, 6, 1, 0F);
        this.side4.setRotationPoint(-2F, 17F, -3F);

        this.hub1 = new ModelRenderer(this, 0, 0);
        this.hub1.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.hub1.setRotationPoint(3F, 18F, -0.5F);

        this.hub2 = new ModelRenderer(this, 0, 0);
        this.hub2.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.hub2.setRotationPoint(4F, 19F, -0.5F);

        this.hub3 = new ModelRenderer(this, 0, 0);
        this.hub3.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.hub3.setRotationPoint(4F, 20F, -0.5F);

        this.hub4 = new ModelRenderer(this, 0, 0);
        this.hub4.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.hub4.setRotationPoint(4F, 21F, -0.5F);

        this.hub5 = new ModelRenderer(this, 0, 0);
        this.hub5.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.hub5.setRotationPoint(3F, 22F, -0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.bottom.render(f5);
        this.side1.render(f5);
        this.side2.render(f5);
        this.side3.render(f5);
        this.side4.render(f5);
        this.hub1.render(f5);
        this.hub2.render(f5);
        this.hub3.render(f5);
        this.hub4.render(f5);
        this.hub5.render(f5);
    }
}
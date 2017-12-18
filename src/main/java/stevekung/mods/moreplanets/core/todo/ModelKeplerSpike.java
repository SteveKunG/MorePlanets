/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.todo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKeplerSpike extends ModelBase
{
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer rim1;
    public ModelRenderer rim2;
    public ModelRenderer rim3;
    public ModelRenderer rim4;
    public ModelRenderer spike1;
    public ModelRenderer spike2;
    public ModelRenderer spike3;
    public ModelRenderer spike4;

    public ModelKeplerSpike()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.addBox(0F, 0F, 0F, 6, 2, 6, 0F);
        this.body1.setRotationPoint(-3F, 22F, -3F);

        this.body2 = new ModelRenderer(this, 0, 0);
        this.body2.addBox(0F, 0F, 0F, 6, 2, 6, 0F);
        this.body2.setRotationPoint(-3F, 19F, -3F);

        this.body3 = new ModelRenderer(this, 0, 0);
        this.body3.addBox(0F, 0F, 0F, 8, 1, 8, 0F);
        this.body3.setRotationPoint(-4F, 21F, -4F);

        this.rim1 = new ModelRenderer(this, 0, 0);
        this.rim1.addBox(0F, 0F, 0F, 1, 1, 10, 0F);
        this.rim1.setRotationPoint(-6F, 21F, -5F);

        this.rim2 = new ModelRenderer(this, 0, 0);
        this.rim2.addBox(0F, 0F, 0F, 1, 1, 10, 0F);
        this.rim2.setRotationPoint(5F, 21F, -5F);

        this.rim3 = new ModelRenderer(this, 0, 0);
        this.rim3.addBox(0F, 0F, 0F, 10, 1, 1, 0F);
        this.rim3.setRotationPoint(-5F, 21F, 5F);

        this.rim4 = new ModelRenderer(this, 0, 0);
        this.rim4.addBox(0F, 0F, 0F, 10, 1, 1, 0F);
        this.rim4.setRotationPoint(-5F, 21F, -6F);

        this.spike1 = new ModelRenderer(this, 0, 0);
        this.spike1.addBox(0F, 0F, 0F, 1, 2, 1, 0F);
        this.spike1.setRotationPoint(-2F, 18.2F, -1.3F);
        this.spike1.rotateAngleX = -0.1047198F;

        this.spike2 = new ModelRenderer(this, 0, 0);
        this.spike2.addBox(0F, 0F, 0F, 1, 2, 1, 0F);
        this.spike2.setRotationPoint(0F, 18.6F, 0F);
        this.spike2.rotateAngleX = 0.3141593F;

        this.spike3 = new ModelRenderer(this, 0, 0);
        this.spike3.addBox(0F, 0F, 0F, 1, 2, 1, 0F);
        this.spike3.setRotationPoint(-2F, 18.1F, 0.7F);
        this.spike3.rotateAngleX = -0.4363323F;

        this.spike4 = new ModelRenderer(this, 0, 0);
        this.spike4.addBox(0F, 0F, 0F, 1, 2, 1, 0F);
        this.spike4.setRotationPoint(0.2F, 18.6F, -2.4F);
        this.spike4.rotateAngleX = 0.296706F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.body1.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.rim1.render(f5);
        this.rim2.render(f5);
        this.rim3.render(f5);
        this.rim4.render(f5);
        this.spike1.render(f5);
        this.spike2.render(f5);
        this.spike3.render(f5);
        this.spike4.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
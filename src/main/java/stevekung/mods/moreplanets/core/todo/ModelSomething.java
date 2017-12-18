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

public class ModelSomething extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer leg7;
    public ModelRenderer leg8;
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer foot3;
    public ModelRenderer foot4;
    public ModelRenderer foot5;
    public ModelRenderer foot6;
    public ModelRenderer foot7;
    public ModelRenderer foot8;
    public ModelRenderer head;
    public ModelRenderer eye1;
    public ModelRenderer eye2;

    public ModelSomething()
    {
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0F, 0F, 0F, 18, 16, 46, 0F);
        this.body.setRotationPoint(-9F, -6F, -18F);

        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg1.setRotationPoint(-6.5F, 9F, -11F);

        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg2.setRotationPoint(-6.5F, 9F, 0F);

        this.leg3 = new ModelRenderer(this, 0, 0);
        this.leg3.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg3.setRotationPoint(-6.5F, 9F, 11F);

        this.leg4 = new ModelRenderer(this, 0, 0);
        this.leg4.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg4.setRotationPoint(-6.5F, 9F, 21F);

        this.leg5 = new ModelRenderer(this, 0, 0);
        this.leg5.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg5.setRotationPoint(6.5F, 9F, -11F);

        this.leg6 = new ModelRenderer(this, 0, 0);
        this.leg6.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg6.setRotationPoint(6.5F, 9F, 0F);

        this.leg7 = new ModelRenderer(this, 0, 0);
        this.leg7.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg7.setRotationPoint(6.5F, 9F, 11F);

        this.leg8 = new ModelRenderer(this, 0, 0);
        this.leg8.addBox(-3F, 0F, -3F, 6, 11, 6, 0F);
        this.leg8.setRotationPoint(6.5F, 9F, 21F);

        this.foot1 = new ModelRenderer(this, 0, 0);
        this.foot1.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot1.setRotationPoint(-6.5F, 20F, -11F);

        this.foot2 = new ModelRenderer(this, 0, 0);
        this.foot2.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot2.setRotationPoint(-6.5F, 20F, 0F);

        this.foot3 = new ModelRenderer(this, 0, 0);
        this.foot3.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot3.setRotationPoint(-6.5F, 20F, 11F);

        this.foot4 = new ModelRenderer(this, 0, 0);
        this.foot4.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot4.setRotationPoint(-6.5F, 20F, 21F);

        this.foot5 = new ModelRenderer(this, 0, 0);
        this.foot5.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot5.setRotationPoint(6.5F, 20F, -11F);

        this.foot6 = new ModelRenderer(this, 0, 0);
        this.foot6.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot6.setRotationPoint(6.5F, 20F, 0F);

        this.foot7 = new ModelRenderer(this, 0, 0);
        this.foot7.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot7.setRotationPoint(6.5F, 20F, 11F);

        this.foot8 = new ModelRenderer(this, 0, 0);
        this.foot8.addBox(-2.5F, 0F, -2.5F, 5, 4, 5, 0F);
        this.foot8.setRotationPoint(6.5F, 20F, 21F);

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(0F, 0F, 0F, 16, 16, 16, 0F);
        this.head.setRotationPoint(-8F, -20F, -18.5F);

        this.eye1 = new ModelRenderer(this, 0, 0);
        this.eye1.addBox(0F, 0F, 0F, 4, 5, 5, 0F);
        this.eye1.setRotationPoint(-9F, -20F, -19F);

        this.eye2 = new ModelRenderer(this, 0, 0);
        this.eye2.addBox(0F, 0F, 0F, 4, 5, 5, 0F);
        this.eye2.setRotationPoint(5F, -20F, -19F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.leg5.render(f5);
        this.leg6.render(f5);
        this.leg7.render(f5);
        this.leg8.render(f5);
        this.foot1.render(f5);
        this.foot2.render(f5);
        this.foot3.render(f5);
        this.foot4.render(f5);
        this.foot5.render(f5);
        this.foot6.render(f5);
        this.foot7.render(f5);
        this.foot8.render(f5);
        this.head.render(f5);
        this.eye1.render(f5);
        this.eye2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPlutoAlien extends ModelBase
{
    ModelRenderer top1;
    ModelRenderer top2;
    ModelRenderer top3;
    ModelRenderer top4;
    ModelRenderer top5;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;

    public ModelPlutoAlien()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.top1 = new ModelRenderer(this, 0, 47);
        this.top1.addBox(0F, 0F, 0F, 14, 3, 14);
        this.top1.setRotationPoint(-7F, -1F, -7F);

        this.top2 = new ModelRenderer(this, 0, 52);
        this.top2.addBox(0F, 0F, 0F, 10, 2, 10);
        this.top2.setRotationPoint(-5F, -2.5F, -5F);

        this.top3 = new ModelRenderer(this, 0, 56);
        this.top3.addBox(0F, 0F, 0F, 6, 2, 6);
        this.top3.setRotationPoint(-3F, -4F, -3F);

        this.top4 = new ModelRenderer(this, 0, 47);
        this.top4.addBox(0F, 0F, 0F, 16, 1, 16);
        this.top4.setRotationPoint(-8F, 2F, -8F);

        this.top5 = new ModelRenderer(this, 0, 60);
        this.top5.addBox(0F, 0F, 0F, 3, 1, 3);
        this.top5.setRotationPoint(-1.5F, -4.75F, -1.5F);

        this.body = new ModelRenderer(this, 63, 0);
        this.body.addBox(0F, 0F, 0F, 8, 20, 8);
        this.body.setRotationPoint(-4F, 3F, -4F);

        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.addBox(-2F, 0F, -2F, 4, 4, 4);
        this.leg1.setRotationPoint(5F, 20F, 3F);

        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.addBox(-2F, 0F, -2F, 4, 4, 4);
        this.leg2.setRotationPoint(5F, 20F, -4F);

        this.leg3 = new ModelRenderer(this, 0, 0);
        this.leg3.addBox(-2F, 0F, -2F, 4, 4, 4);
        this.leg3.setRotationPoint(-5F, 20F, 3F);

        this.leg4 = new ModelRenderer(this, 0, 0);
        this.leg4.addBox(-2F, 0F, -2F, 4, 4, 4);
        this.leg4.setRotationPoint(-5F, 20F, -4F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.top1.render(f5);
        this.top2.render(f5);
        this.top3.render(f5);
        this.top4.render(f5);
        this.top5.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity)
    {
        this.leg1.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 0.9F * p_78087_2_;
        this.leg2.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 0.9F * p_78087_2_;
        this.leg3.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 0.9F * p_78087_2_;
        this.leg4.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 0.9F * p_78087_2_;
    }
}
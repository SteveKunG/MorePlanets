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

public class ModelPoisonSpike extends ModelBase
{
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer Body5;
    public ModelRenderer Body6;

    public ModelPoisonSpike()
    {
        this.Body1 = new ModelRenderer(this, 0, 0);
        this.Body1.addBox(0F, 0F, 0F, 6, 8, 6, 0F);
        this.Body1.setRotationPoint(-3F, 16F, -3F);

        this.Body2 = new ModelRenderer(this, 0, 0);
        this.Body2.addBox(0F, 0F, 0F, 5, 6, 5, 0F);
        this.Body2.setRotationPoint(-2.5F, 10F, -2.5F);

        this.Body3 = new ModelRenderer(this, 0, 0);
        this.Body3.addBox(0F, 0F, 0F, 4, 5, 4, 0F);
        this.Body3.setRotationPoint(-2F, 5F, -2F);

        this.Body4 = new ModelRenderer(this, 0, 0);
        this.Body4.addBox(0F, 0F, 0F, 3, 4, 3, 0F);
        this.Body4.setRotationPoint(-1.5F, 1F, -1.5F);

        this.Body5 = new ModelRenderer(this, 0, 0);
        this.Body5.addBox(0F, 0F, 0F, 2, 1, 2, 0F);
        this.Body5.setRotationPoint(-1F, 0F, -1F);

        this.Body6 = new ModelRenderer(this, 0, 0);
        this.Body6.addBox(0F, 0F, 0F, 1, 1, 1, 0F);
        this.Body6.setRotationPoint(-0.5F, -1F, -0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Body1.render(f5);
        this.Body2.render(f5);
        this.Body3.render(f5);
        this.Body4.render(f5);
        this.Body5.render(f5);
        this.Body6.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
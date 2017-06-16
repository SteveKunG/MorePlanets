/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.entities.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCheeseCubeEye extends ModelBase
{
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer eye;

    public ModelCheeseCubeEye()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.addBox(-8F, -8F, -8F, 16, 16, 16, 0F);
        this.body1.setRotationPoint(0F, 16F, 0F);
        this.body2 = new ModelRenderer(this, 0, 0);
        this.body2.addBox(-8F, -8F, -8F, 16, 16, 16, 0F);
        this.body2.setRotationPoint(0F, 16F, 0F);
        this.eye = new ModelRenderer(this, 100, 0);
        this.eye.addBox(0F, 0F, 0F, 10, 6, 1, 0F);
        this.eye.setRotationPoint(-5F, 13F, -9F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.updateBodyRotation(entity, f2);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.body1.render(f5);
        this.body2.render(f5);
        this.eye.render(f5);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    private void updateBodyRotation(Entity e, float f)
    {
        float var8 = 0.03F * (e.getEntityId() % 5) + 0.05F;
        this.body1.rotateAngleZ = e.ticksExisted * var8 + (float) (Math.PI / 8) * 2;
        this.body2.rotateAngleZ = (float) (e.ticksExisted * var8 + (float) (Math.PI / 8) * 0.5);
    }
}
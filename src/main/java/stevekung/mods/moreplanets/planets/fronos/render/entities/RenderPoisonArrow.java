/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.projectiles.EntityPoisonArrow;

public class RenderPoisonArrow extends Render
{
    private static ResourceLocation arrowTextures = new ResourceLocation("fronos:textures/model/projectiles/poison_arrow.png");

    public void doRender(EntityPoisonArrow entity, double par2, double par3, double par4, float par5, float par6)
    {
        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par3, (float)par4);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par6 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par6, 0.0F, 0.0F, 1.0F);

        Tessellator localTessellator = Tessellator.instance;
        int i = 0;

        float f1 = 0.0F;
        float f2 = 0.5F;
        float f3 = (0 + i * 10) / 32.0F;
        float f4 = (5 + i * 10) / 32.0F;
        float f5 = 0.0F;
        float f6 = 0.15625F;
        float f7 = (5 + i * 10) / 32.0F;
        float f8 = (10 + i * 10) / 32.0F;
        float f9 = 0.05625F;
        GL11.glEnable(32826);
        float f10 = entity.arrowShake - par6;

        if (f10 > 0.0F)
        {
            float f11 = -MathHelper.sin(f10 * 3.0F) * f10;
            GL11.glRotatef(f11, 0.0F, 0.0F, 1.0F);
        }

        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(f9, f9, f9);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f9, 0.0F, 0.0F);
        localTessellator.startDrawingQuads();
        localTessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f5, f7);
        localTessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f6, f7);
        localTessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f6, f8);
        localTessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f5, f8);
        localTessellator.draw();

        GL11.glNormal3f(-f9, 0.0F, 0.0F);
        localTessellator.startDrawingQuads();
        localTessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, f5, f7);
        localTessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, f6, f7);
        localTessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, f6, f8);
        localTessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, f5, f8);
        localTessellator.draw();

        for (int j = 0; j < 4; j++)
        {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f9);
            localTessellator.startDrawingQuads();
            localTessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, f1, f3);
            localTessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, f2, f3);
            localTessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, f2, f4);
            localTessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, f1, f4);
            localTessellator.draw();
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityPoisonArrow entity)
    {
        return arrowTextures;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityPoisonArrow)entity);
    }

    @Override
    public void doRender(Entity entity, double par2, double par3, double par4, float par5, float par6)
    {
        this.doRender((EntityPoisonArrow)entity, par2, par3, par4, par5, par6);
    }
}
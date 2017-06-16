/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.render.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityUraniumBomb;

public class RenderUraniumBomb extends Render
{
    private final RenderBlocks blockRenderer = new RenderBlocks();

    public RenderUraniumBomb()
    {
        this.shadowSize = 0.5F;
    }

    public void doRender(EntityUraniumBomb tnt, double x, double y, double z, float par5, float par6)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        float var10;

        if (tnt.fuse - par6 + 1.0F < 10.0F)
        {
            var10 = 1.0F - (tnt.fuse - par6 + 1.0F) / 10.0F;

            if (var10 < 0.0F)
            {
                var10 = 0.0F;
            }

            if (var10 > 1.0F)
            {
                var10 = 1.0F;
            }
            var10 *= var10;
            var10 *= var10;
            final float var11 = 1.0F + var10 * 0.3F;
            GL11.glScalef(var11, var11, var11);
        }

        var10 = (1.0F - (tnt.fuse - par6 + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(tnt);
        this.blockRenderer.renderBlockAsItem(KapteynBBlocks.uranium_bomb, 0, tnt.getBrightness(par6));

        if (tnt.fuse / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
            this.blockRenderer.renderBlockAsItem(KapteynBBlocks.uranium_bomb, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityUraniumBomb entity)
    {
        return TextureMap.locationBlocksTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityUraniumBomb)entity);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float par5, float par6)
    {
        this.doRender((EntityUraniumBomb)entity, x, y, z, par5, par6);
    }
}
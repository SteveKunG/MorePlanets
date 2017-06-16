package stevekung.mods.moreplanets.asteroids.darkasteroids.client.render.entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.asteroids.darkasteroids.blocks.DarkAsteroidsBlocks;
import stevekung.mods.moreplanets.asteroids.darkasteroids.entities.EntityDarkAsteroid;

public class RenderDarkAsteroid extends Render
{
    private RenderBlocks blockRenderer = new RenderBlocks();

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float partialTickTime)
    {
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        EntityDarkAsteroid asteroid = (EntityDarkAsteroid) entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(asteroid.rotationPitch, 1, 0, 0);
        GL11.glRotatef(asteroid.rotationYaw, 0, 1, 0);
        this.bindEntityTexture(asteroid);
        this.blockRenderer.renderBlockAsItem(DarkAsteroidsBlocks.dark_asteroid_block, 0, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TextureMap.locationBlocksTexture;
    }
}
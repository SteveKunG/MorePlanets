/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.render.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderIceCrystalMeteorChunk extends Render
{
    private ResourceLocation meteorChunkTexture = new ResourceLocation("kapteynb:textures/model/ice_crystal_meteor_chunk.png");
    private IModelCustom meteorChunkModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftcore:models/meteorChunk.obj"));

    public RenderIceCrystalMeteorChunk()
    {
        this.shadowSize = 0.1F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.meteorChunkTexture;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float var24 = entity.rotationPitch;
        float var24b = entity.rotationYaw;
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glScalef(0.3F, 0.3F, 0.3F);
        GL11.glRotatef(var24b, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(var24, 0.0F, 0.0F, 1.0F);
        this.bindTexture(this.meteorChunkTexture);
        this.meteorChunkModel.renderAll();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
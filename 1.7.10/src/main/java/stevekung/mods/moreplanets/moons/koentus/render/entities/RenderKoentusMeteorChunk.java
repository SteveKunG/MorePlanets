/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.render.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteorChunk;

public class RenderKoentusMeteorChunk extends Render
{
    private static final ResourceLocation meteorChunkTexture = new ResourceLocation("koentus:textures/model/koentus_meteor_chunk.png");
    private final IModelCustom meteorChunkModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftcore:models/meteorChunk.obj"));

    public RenderKoentusMeteorChunk()
    {
        this.shadowSize = 0.1F;
    }

    protected ResourceLocation func_110779_a(EntityKoentusMeteorChunk par1EntityArrow)
    {
        return RenderKoentusMeteorChunk.meteorChunkTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110779_a((EntityKoentusMeteorChunk) par1Entity);
    }

    public void renderMeteorChunk(EntityKoentusMeteorChunk entity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        final float var24 = entity.rotationPitch;
        final float var24b = entity.rotationYaw;
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glScalef(0.3F, 0.3F, 0.3F);
        GL11.glRotatef(var24b, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(var24, 0.0F, 0.0F, 1.0F);
        this.bindTexture(RenderKoentusMeteorChunk.meteorChunkTexture);
        this.meteorChunkModel.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderMeteorChunk((EntityKoentusMeteorChunk) par1Entity, par2, par4, par6, par8, par9);
    }
}
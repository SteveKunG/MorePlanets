/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.render.items;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ItemRendererPolongniusMeteorChunk implements IItemRenderer
{
    private static final ResourceLocation meteorChunkTexture = new ResourceLocation("polongnius:textures/model/polongnius_meteor_chunk.png");
    private final IModelCustom meteorChunkModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftcore:models/meteorChunk.obj"));

    private void renderMeteorChunk(ItemRenderType type, RenderBlocks render, ItemStack item, float translateX, float translateY, float translateZ)
    {
        GL11.glPushMatrix();
        GL11.glScalef(0.7F, 0.7F, 0.7F);

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glTranslatef(1.4F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 1, 0, 0);
        }
        if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glTranslatef(1.4F, 1.0F, 0.5F);
        }
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(ItemRendererPolongniusMeteorChunk.meteorChunkTexture);
        this.meteorChunkModel.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
        case ENTITY:
            return true;
        case EQUIPPED:
            return true;
        case EQUIPPED_FIRST_PERSON:
            return true;
        case INVENTORY:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
        case EQUIPPED:
            this.renderMeteorChunk(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        case EQUIPPED_FIRST_PERSON:
            this.renderMeteorChunk(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        case INVENTORY:
            this.renderMeteorChunk(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        case ENTITY:
            this.renderMeteorChunk(type, (RenderBlocks) data[0], item, -0.5f, -0.5f, -0.5f);
            break;
        default:
        }
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.render.blocks;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class BlockRendererCrystalSegment implements ISimpleBlockRenderingHandler
{
    protected int renderId;

    public BlockRendererCrystalSegment(int id)
    {
        this.renderId = id;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta)
        {
        case 0:
            renderer.setRenderBounds(0.374F, 0.0F, 0.374F, 0.626F, 1.0F, 0.626F);
            renderer.renderStandardBlock(block, x, y, z);
            break;
        case 4:
            renderer.setRenderBounds(0.0F, 0.374F, 0.374F, 1.0F, 0.626F, 0.626F);
            renderer.renderStandardBlock(block, x, y, z);
            break;
        case 8:
            renderer.setRenderBounds(0.374F, 0.374F, 0.0F, 0.626F, 0.626F, 1.0F);
            renderer.renderStandardBlock(block, x, y, z);
            break;
        default:
            renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            renderer.renderStandardBlock(block, x, y, z);
            break;
        }
        return true;
    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer)
    {
        switch (meta)
        {
        case 0:
            renderer.setRenderBounds(0.374F, 0.0F, 0.374F, 0.626F, 1.0F, 0.626F);
            this.renderStandardInvBlock(renderer, block, meta);
            break;
        case 4:
            renderer.setRenderBounds(0.0F, 0.374F, 0.374F, 1.0F, 0.626F, 0.626F);
            this.renderStandardInvBlock(renderer, block, meta);
            break;
        case 8:
            renderer.setRenderBounds(0.374F, 0.374F, 0.0F, 0.626F, 0.626F, 1.0F);
            this.renderStandardInvBlock(renderer, block, meta);
            break;
        default:
            renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            this.renderStandardInvBlock(renderer, block, meta);
            break;
        }
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return this.renderId;
    }

    private void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
    {
        GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);

        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        tessellator.draw();

        GL11.glColor4f(1F, 1F, 1F, 1.0F);

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
}
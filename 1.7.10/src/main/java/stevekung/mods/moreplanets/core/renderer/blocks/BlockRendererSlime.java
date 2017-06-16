/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer.blocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class BlockRendererSlime implements ISimpleBlockRenderingHandler
{
    int renderID;

    public BlockRendererSlime(int id)
    {
        this.renderID = id;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        this.renderSlimeCore(var7, var5, var1, var2, var3, var4);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return this.renderID;
    }

    public static void renderInvNormalBlock(RenderBlocks var0, Block var1, int var2)
    {
        var0.setRenderBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        BlockRendererSlime.renderStandardBlock(var0, var1, var2);

        var0.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        BlockRendererSlime.renderStandardBlock(var0, var1, var2);

        var0.clearOverrideBlockTexture();
    }

    private static void renderStandardBlock(RenderBlocks var0, Block var1, int var2)
    {
        Tessellator var3 = Tessellator.instance;

        GL11.glPushMatrix();
        RenderHelper.enableStandardItemLighting();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        var3.startDrawingQuads();
        var3.setNormal(0.0F, -1.0F, 0.0F);
        var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(0, var2));
        var3.draw();
        var3.startDrawingQuads();
        var3.setNormal(0.0F, 1.0F, 0.0F);
        var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(1, var2));
        var3.draw();
        var3.startDrawingQuads();
        var3.setNormal(0.0F, 0.0F, -1.0F);
        var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(2, var2));
        var3.draw();
        var3.startDrawingQuads();
        var3.setNormal(0.0F, 0.0F, 1.0F);
        var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(3, var2));
        var3.draw();
        var3.startDrawingQuads();
        var3.setNormal(-1.0F, 0.0F, 0.0F);
        var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(4, var2));
        var3.draw();
        var3.startDrawingQuads();
        var3.setNormal(1.0F, 0.0F, 0.0F);
        var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(5, var2));
        var3.draw();
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        BlockRendererSlime.renderInvNormalBlock(renderer, block, metadata);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

    public void renderSlimeCore(RenderBlocks renderBlocks, Block par1Block, IBlockAccess var1, int par2, int par3, int par4)
    {
        renderBlocks.setRenderBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

        renderBlocks.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

        renderBlocks.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

        renderBlocks.clearOverrideBlockTexture();
        par1Block.setBlockBoundsForItemRender();
    }
}
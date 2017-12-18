/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.blocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class BlockRendererOyster implements ISimpleBlockRenderingHandler
{
    int renderID;
    TileEntity oyster;

    public BlockRendererOyster(int id, TileEntity oyster)
    {
        this.renderID = id;
        this.oyster = oyster;
    }

    public void renderOyster(RenderBlocks renderBlocks, IBlockAccess iblockaccess, Block par1Block, int par2, int par3, int par4)
    {
        renderBlocks.overrideBlockTexture = par1Block.getIcon(iblockaccess, par2, par3, par4, 0);
        renderBlocks.clearOverrideBlockTexture();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.6F, -0.15F, -0.55F);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.oyster, 0.0D, 0.0D, 0.0D, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        this.renderOyster(renderer, world, block, x, y, z);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int model)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return this.renderID;
    }
}
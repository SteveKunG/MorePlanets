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
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class BlockRendererAncientChest implements ISimpleBlockRenderingHandler
{
    int renderID;
    TileEntity chest;
    Block block;

    public BlockRendererAncientChest(int id, Block block, TileEntity chest)
    {
        this.renderID = id;
        this.chest = chest;
        this.block = block;
    }

    @Override
    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        this.renderChest(var1, var2, var3);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelID)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return this.renderID;
    }

    public void renderChest(Block block, int par2, float par3)
    {
        if (block == this.block)
        {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(this.chest, 0.0D, 0.0D, 0.0D, 0.0F);
        }
    }
}
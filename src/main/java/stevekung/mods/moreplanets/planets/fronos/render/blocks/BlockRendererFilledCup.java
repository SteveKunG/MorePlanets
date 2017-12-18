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
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class BlockRendererFilledCup implements ISimpleBlockRenderingHandler
{
    int renderID;
    TileEntity cup;

    public BlockRendererFilledCup(int id, TileEntity cup)
    {
        this.renderID = id;
        this.cup = cup;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.6F, -0.15F, -0.55F);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.cup, 0.0D, 0.0D, 0.0D, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int l = block.colorMultiplier(world, x, y, z);
        float f = (l >> 16 & 255) / 255.0F;
        float f1 = (l >> 8 & 255) / 255.0F;
        float f2 = (l & 255) / 255.0F;
        tessellator.setColorOpaque_F(f, f1, f2);
        renderer.overrideBlockTexture = block.getIcon(world, x, y, z, 0);
        renderer.clearOverrideBlockTexture();
        //IIcon iicon = BlockFluidOvantine.ovantineStillIcon;
        //renderer.renderFaceYPos(block, x, y - 0.1F, z, iicon);
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
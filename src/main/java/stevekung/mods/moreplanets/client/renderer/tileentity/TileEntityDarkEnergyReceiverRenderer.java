package stevekung.mods.moreplanets.client.renderer.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.client.model.ModelDarkEnergyReceiver;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;

public class TileEntityDarkEnergyReceiverRenderer extends TileEntitySpecialRenderer<TileEntityDarkEnergyReceiver>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/dark_energy_receiver.png");
    public ModelDarkEnergyReceiver model = new ModelDarkEnergyReceiver();

    @Override
    public void render(TileEntityDarkEnergyReceiver tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        if (tile == null)
        {
            return;
        }

        int facing = 0;
        float solarRotate = tile.solarRotate;

        if (tile != null && tile.hasWorld() && tile.getWorld().getBlockState(tile.getPos()).getBlock() == MPBlocks.DARK_ENERGY_RECEIVER)
        {
            facing = tile.getFacing();
        }

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.5F, 4.5F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(TileEntityDarkEnergyReceiverRenderer.texture);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.rotate(facing, 0.0F, 1.0F, 0.0F);

        if (destroyStage < 0)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

        this.model.renderBase();

        if (tile.activated && !tile.successful)
        {
            solarRotate += partialTicks;
        }

        GlStateManager.pushMatrix();
        GlStateManager.rotate(solarRotate, 0.0F, 1.0F, 0.0F);
        this.model.renderSolar();
        GlStateManager.popMatrix();

        float rodUp = tile.rodUp;

        if (tile.rodUp < 58 && tile.activated && !tile.failed)
        {
            rodUp = tile.rodUp + partialTicks;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.65F, 0.0F);
        GlStateManager.translate(0.0F, -rodUp / 90, 0.0F);
        this.model.renderRod();
        GlStateManager.popMatrix();

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
        if (tile.rendered)
        {
            this.renderBeam(tile, x, y, z, partialTicks);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityDarkEnergyReceiver tile)
    {
        return true;
    }

    private void renderBeam(TileEntityDarkEnergyReceiver tile, double x, double y, double z, float partialTicks)
    {
        for (int yRender = tile.getPos().getY(); yRender < 256; yRender++)
        {
            IBlockState state = tile.getWorld().getBlockState(new BlockPos(tile.getPos().getX(), yRender, tile.getPos().getZ()));

            if (state.isOpaqueCube() && state.getBlock() != DionaBlocks.DARK_ENERGY_CORE || !tile.isActivated())
            {
                return;
            }
        }

        GlStateManager.pushMatrix();

        float f = 1.0F;
        GlStateManager.alphaFunc(516, 0.1F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        GlStateManager.disableFog();
        int i = 0;

        for (int j = 0; j < 1; ++j)
        {
            int height = tile.getWorld().getHeight();
            int k = i + height;
            this.bindTexture(new ResourceLocation("textures/entity/beacon_beam.png"));
            GlStateManager.glTexParameterf(3553, 10242, 10497.0F);
            GlStateManager.glTexParameterf(3553, 10243, 10497.0F);
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
            double d0 = (double)tile.getWorld().getTotalWorldTime() + (double)partialTicks;
            double d1 = MathHelper.frac(-d0 * 0.2D - MathHelper.floor(-d0 * 0.1D));
            float f1 = tile.failedTick > 0 ? 0.1F : tile.successful ? 0.1F : 0.3F;
            float f2 = tile.failedTick > 0 ? 0.08F : tile.successful ? 0.1F : 0.2F;
            float f3 = tile.failedTick > 0 ? 0.12F : tile.successful ? 0.1F : 0.5F;
            double d2 = d0 * 0.025D * -1.5D;
            double d4 = 0.5D + Math.cos(d2 + 2.356194490192345D) * 0.2D;
            double d5 = 0.5D + Math.sin(d2 + 2.356194490192345D) * 0.2D;
            double d6 = 0.5D + Math.cos(d2 + Math.PI / 4D) * 0.2D;
            double d7 = 0.5D + Math.sin(d2 + Math.PI / 4D) * 0.2D;
            double d8 = 0.5D + Math.cos(d2 + 3.9269908169872414D) * 0.2D;
            double d9 = 0.5D + Math.sin(d2 + 3.9269908169872414D) * 0.2D;
            double d10 = 0.5D + Math.cos(d2 + 5.497787143782138D) * 0.2D;
            double d11 = 0.5D + Math.sin(d2 + 5.497787143782138D) * 0.2D;
            double d12 = 0.0D;
            double d13 = 1.0D;
            double d14 = -1.0D + d1;
            double d15 = height * f * 2.5D + d14;
            GlStateManager.translate(0.0F, 0.15F, 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.depthMask(true);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(x + d4, y + k, z + d5).tex(1.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d4, y + i, z + d5).tex(1.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d6, y + i, z + d7).tex(0.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d6, y + k, z + d7).tex(0.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d10, y + k, z + d11).tex(1.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d10, y + i, z + d11).tex(1.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d8, y + i, z + d9).tex(0.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d8, y + k, z + d9).tex(0.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d6, y + k, z + d7).tex(1.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d6, y + i, z + d7).tex(1.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d10, y + i, z + d11).tex(0.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d10, y + k, z + d11).tex(0.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d8, y + k, z + d9).tex(1.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d8, y + i, z + d9).tex(1.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d4, y + i, z + d5).tex(0.0D, d14).color(f1, f2, f3, 0.5F).endVertex();
            worldrenderer.pos(x + d4, y + k, z + d5).tex(0.0D, d15).color(f1, f2, f3, 0.5F).endVertex();
            tessellator.draw();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.depthMask(false);
            d2 = 0.2D;
            d4 = 0.8D;
            d5 = 0.2D;
            d6 = 0.2D;
            d7 = 0.8D;
            d8 = 0.8D;
            d9 = 0.8D;
            d10 = 0.0D;
            d11 = 1.0D;
            d12 = -1.0D + d1;
            d13 = height * f + d12;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos(x + 0.2D, y + k, z + 0.2D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.2D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.2D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.2D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.8D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.8D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.8D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + k, z + 0.8D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.2D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.2D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + i, z + 0.8D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.8D, y + k, z + 0.8D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + k, z + 0.8D).tex(1.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.8D).tex(1.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + i, z + 0.2D).tex(0.0D, d12).color(f1, f2, f3, 0.125F).endVertex();
            worldrenderer.pos(x + 0.2D, y + k, z + 0.2D).tex(0.0D, d13).color(f1, f2, f3, 0.125F).endVertex();
            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            i = k;
        }
        GlStateManager.enableFog();
        GlStateManager.popMatrix();
    }
}
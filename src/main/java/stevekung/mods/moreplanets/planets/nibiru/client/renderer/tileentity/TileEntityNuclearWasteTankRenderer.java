package stevekung.mods.moreplanets.planets.nibiru.client.renderer.tileentity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.client.model.ModelNuclearWasteTank;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteTank;

@SideOnly(Side.CLIENT)
public class TileEntityNuclearWasteTankRenderer extends TileEntitySpecialRenderer<TileEntityNuclearWasteTank>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/nuclear_waste_tank.png");
    private static final ResourceLocation GLOW = new ResourceLocation("moreplanets:textures/model/nuclear_waste_tank_glow.png");
    private final ModelNuclearWasteTank model = new ModelNuclearWasteTank();
    public static final TileEntityNuclearWasteTankRenderer INSTANCE = new TileEntityNuclearWasteTankRenderer();

    @Override
    public void render(TileEntityNuclearWasteTank tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        Random rand = new Random(tile.getPos().getX() + tile.getPos().getY() * tile.getPos().getZ());
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        if (tile.getWorld() != null && tile.getWorld().getBlockState(tile.getPos()) == NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.NO_ROD))
        {
            int count = 3;//tile.getWorld().getBlockState(tile.getPos()).getValue(BlockNuclearWasteTank.FLUID_COUNT);

            for (int i = 0; i < count; i++)
            {
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 0.5F - i, 0.0F);
                GlStateManager.disableLighting();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
                this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                FluidStack tankFluid = FluidRegistry.getFluidStack("nuclear_waste_fluid", 0);
                TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(tankFluid.getFluid().getStill().toString());
                double uMin = sprite.getMinU();
                double uMax = sprite.getMaxU();
                double vMin = sprite.getMinV();
                double vMax = sprite.getMaxV();
                Tessellator tess = Tessellator.getInstance();
                BufferBuilder worldRenderer = tess.getBuffer();
                float level = 1.0F;
                float levelInv = 0.0F;

                // North
                worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldRenderer.pos(-0.4, levelInv, -0.399).tex(uMin, vMin).endVertex();
                worldRenderer.pos(-0.4, 1.0, -0.399).tex(uMin, vMin + (vMax - vMin) * level).endVertex();
                worldRenderer.pos(0.4, 1.0, -0.399).tex(uMax, vMin + (vMax - vMin) * level).endVertex();
                worldRenderer.pos(0.4, levelInv, -0.399).tex(uMax, vMin).endVertex();
                tess.draw();

                // South
                worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldRenderer.pos(0.4, levelInv, 0.399).tex(uMax, vMin).endVertex();
                worldRenderer.pos(0.4, 1.0, 0.399).tex(uMax, vMin + (vMax - vMin) * level).endVertex();
                worldRenderer.pos(-0.4, 1.0, 0.399).tex(uMin, vMin + (vMax - vMin) * level).endVertex();
                worldRenderer.pos(-0.4, levelInv, 0.399).tex(uMin, vMin).endVertex();
                tess.draw();

                // West
                worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldRenderer.pos(-0.399, 1.0, -0.4).tex(uMin, vMin + (vMax - vMin) * level).endVertex();
                worldRenderer.pos(-0.399, levelInv, -0.4).tex(uMin, vMin).endVertex();
                worldRenderer.pos(-0.399, levelInv, 0.4).tex(uMax, vMin).endVertex();
                worldRenderer.pos(-0.399, 1.0, 0.4).tex(uMax, vMin + (vMax - vMin) * level).endVertex();
                tess.draw();

                // East
                worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldRenderer.pos(0.399, 1.0, 0.4).tex(uMax, vMin + (vMax - vMin) * level).endVertex();
                worldRenderer.pos(0.399, levelInv, 0.4).tex(uMax, vMin).endVertex();
                worldRenderer.pos(0.399, levelInv, -0.4).tex(uMin, vMin).endVertex();
                worldRenderer.pos(0.399, 1.0, -0.4).tex(uMin, vMin + (vMax - vMin) * level).endVertex();
                tess.draw();

                // Top
                worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldRenderer.pos(0.4, 0.01 + levelInv, 0.4).tex(uMax, vMax).endVertex();
                worldRenderer.pos(-0.4, 0.01 + levelInv, 0.4).tex(uMax, vMin).endVertex();
                worldRenderer.pos(-0.4, 0.01 + levelInv, -0.4).tex(uMin, vMin).endVertex();
                worldRenderer.pos(0.4, 0.01 + levelInv, -0.4).tex(uMin, vMax).endVertex();
                tess.draw();
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }

        if (tile.renderTicks > 0)
        {
            GlStateManager.rotate(rand.nextInt(180), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(rand.nextInt(8), 1.0F, 0.0F, 1.0F);
        }
        if (tile.getWorld() != null && tile.getWorld().getBlockState(tile.getPos()) != NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.DEPLETE))
        {
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        }
        else
        {
            GlStateManager.color(0.4F, 0.4F, 0.4F, 1.0F);
        }

        GlStateManager.disableLighting();
        this.bindTexture(TileEntityNuclearWasteTankRenderer.GLOW);

        if (tile.getWorld() != null && tile.getWorld().getBlockState(tile.getPos()) != NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.NO_ROD))
        {
            this.model.renderWaste();
        }

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(TileEntityNuclearWasteTankRenderer.TEXTURE);
        }

        this.model.renderBase();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        this.model.renderGlass();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityNuclearWasteTank tile)
    {
        return true;
    }

    public void render()
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityNuclearWasteTankRenderer.GLOW);
        this.model.renderWaste();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(TileEntityNuclearWasteTankRenderer.TEXTURE);
        this.model.renderBase();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.model.renderGlass();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
    }
}
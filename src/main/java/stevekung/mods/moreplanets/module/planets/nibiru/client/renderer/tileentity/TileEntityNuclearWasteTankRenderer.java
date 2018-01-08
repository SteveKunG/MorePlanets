package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity;

import java.util.Random;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteTank;

public class TileEntityNuclearWasteTankRenderer extends TileEntitySpecialRenderer<TileEntityNuclearWasteTank>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/nuclear_waste_tank.png");
    private static ResourceLocation textureGlow = new ResourceLocation("moreplanets:textures/model/nuclear_waste_tank_glow.png");
    private ModelNuclearWasteTank model = new ModelNuclearWasteTank();
    public static TileEntityNuclearWasteTankRenderer INSTANCE;

    public TileEntityNuclearWasteTankRenderer()
    {
        TileEntityNuclearWasteTankRenderer.INSTANCE = this;
    }

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
        this.bindTexture(TileEntityNuclearWasteTankRenderer.textureGlow);

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
            this.bindTexture(TileEntityNuclearWasteTankRenderer.texture);
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

    public void renderItem()
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
        this.bindTexture(TileEntityNuclearWasteTankRenderer.textureGlow);
        this.model.renderWaste();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        this.bindTexture(TileEntityNuclearWasteTankRenderer.texture);
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
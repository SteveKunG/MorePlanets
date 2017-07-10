package stevekung.mods.moreplanets.client.renderer.tileentity;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.obj.OBJModel.OBJBakedModel;
import stevekung.mods.moreplanets.client.model.ModelShieldGenerator;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class TileEntityShieldGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityShieldGenerator>
{
    private OBJBakedModel sphere;
    private ModelShieldGenerator model = new ModelShieldGenerator();
    private static final ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/shield_generator.png");

    private void updateModels()
    {
        if (this.sphere == null)
        {
            try
            {
                OBJModel model = (OBJModel) ModelLoaderRegistry.getModel(new ResourceLocation("moreplanets:obj/sphere.obj"));
                model = (OBJModel) model.process(ImmutableMap.of("flip-v", "true"));
                Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                this.sphere = (OBJModel.OBJBakedModel) model.bake(new OBJModel.OBJState(ImmutableList.of("Sphere"), false), DefaultVertexFormats.ITEM, spriteFunction);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntityShieldGenerator tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float test = 1.75F;
        int color = ColorUtil.to32BitColor(1, (int)(144 / test * 255), (int)(249 / test * 255), (int)(210 / test * 255));
        float renderPartialTicks = tile.renderTicks + partialTicks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 16) + 1F) / 2F + 0.15F;

        this.updateModels();
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        if (tile.hasWorldObj())
        {
            switch (tile.getWorld().getBlockState(tile.getPos()).getValue(BlockStateHelper.FACING_HORIZON))
            {
            case NORTH:
            default:
                GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
                break;
            case SOUTH:
                GlStateManager.rotate(-180.0F, 0.0F, 1.0F, 0.0F);
                break;
            case EAST:
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                break;
            case WEST:
                GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                break;
            }
        }

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png"));
        this.model.renderBase();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow1.png"));
        this.model.renderRod();
        GlStateManager.popMatrix();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png"));
        this.model.renderBase();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/shield_generator_glow2.png"));
        this.model.renderRod();
        GlStateManager.popMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        this.bindTexture(TileEntityShieldGeneratorRenderer.texture);
        this.model.renderBase();
        GlStateManager.rotate(tile.hasEnoughEnergyToRun ? partialTicks + tile.solarRotate : tile.solarRotate, 0.0F, 1.0F, 0.0F);
        this.model.renderRod();

        if (tile.getBubbleVisible())
        {
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.enableBlend();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.depthMask(false);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
            GlStateManager.scale(tile.getBubbleSize(), tile.getBubbleSize(), tile.getBubbleSize());
            ClientUtil.drawBakedModelColored(this.sphere, color);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.depthMask(true);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthFunc(515);
            GlStateManager.enableCull();
        }

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
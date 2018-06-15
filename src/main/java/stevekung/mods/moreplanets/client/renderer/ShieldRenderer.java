package stevekung.mods.moreplanets.client.renderer;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class ShieldRenderer
{
    private static final ResourceLocation OBJ = new ResourceLocation("moreplanets:shield.obj");
    private static IBakedModel SPHERE;

    private static void updateModels()
    {
        try
        {
            ShieldRenderer.SPHERE = OBJLoaderMP.getModelFromOBJ(ShieldRenderer.OBJ, ImmutableList.of("Shield"));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void renderShields(TileEntityShieldGenerator tile, EntityPlayer player, float partialTicks)
    {
        Minecraft mc = Minecraft.getMinecraft();
        double interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

        if (ShieldRenderer.SPHERE == null)
        {
            ShieldRenderer.updateModels();
        }

        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.depthMask(false);
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

        if (tile.getBubbleVisible())
        {
            GlStateManager.pushMatrix();
            float x = (float) (tile.getPos().getX() - interpPosX);
            float y = (float) (tile.getPos().getY() - interpPosY);
            float z = (float) (tile.getPos().getZ() - interpPosZ);
            GlStateManager.translate(x + 0.5F, y + 1.0F, z + 0.5F);
            GlStateManager.scale(tile.getBubbleSize(), tile.getBubbleSize(), tile.getBubbleSize());
            int capacity = tile.shieldCapacity * 171 / tile.maxShieldCapacity;
            int color = ColorUtils.to32BitColor(capacity + 84, 255, 255, 255);
            ClientUtil.drawBakedModelColored(ShieldRenderer.SPHERE, color);
            GlStateManager.popMatrix();
        }
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
    }
}

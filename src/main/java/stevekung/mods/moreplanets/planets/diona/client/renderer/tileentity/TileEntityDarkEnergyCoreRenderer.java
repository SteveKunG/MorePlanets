package stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelDarkEnergyBlock;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.stevekunglib.client.event.ClientEventHandler;

@SideOnly(Side.CLIENT)
public class TileEntityDarkEnergyCoreRenderer extends TileEntitySpecialRenderer<TileEntityDarkEnergyCore>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/dark_energy_core.png");
    private static final ResourceLocation LIGHT0 = new ResourceLocation("moreplanets:textures/model/dark_energy_core_glow.png");
    private final ModelDarkEnergyBlock model = new ModelDarkEnergyBlock();

    @Override
    public void render(TileEntityDarkEnergyCore tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        float renderPartialTicks = tile.getWorld() == null ? ClientEventHandler.ticks : tile.renderTicks + partialTicks;
        float ticks = MathHelper.sin(renderPartialTicks / 8) / 10.0F + 1F;
        ticks = ticks * ticks + ticks;
        float lightTime = (MathHelper.sin(renderPartialTicks / 4) + 1F) / 2F + 0.15F;
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindTexture(TileEntityDarkEnergyCoreRenderer.TEXTURE);
        this.model.renderAll(tile.getWorld() == null ? ticks * 0.225F : ticks * 0.2F);
        GlStateManager.color(lightTime, lightTime, lightTime);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(TileEntityDarkEnergyCoreRenderer.LIGHT0);
        this.model.renderAll(ticks * 0.2F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
package stevekung.mods.moreplanets.planets.chalos.client.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderBaseMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class SkyProviderChalos extends SkyProviderBaseMP
{
    private static final ResourceLocation LAZENDUS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/lazendus.png");
    private static final ResourceLocation DIONA = new ResourceLocation("moreplanets:textures/gui/celestialbodies/diona.png");

    public SkyProviderChalos(float solarSize)
    {
        this.solarSize = 70.0F * solarSize;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
        this.renderSolarAura(14.0F, 30.0F, world.getStarBrightness(partialTicks), ColorUtils.stringToRGB("217, 123, 38, 102"), partialTicks);
        this.renderSolar(SkyProviderChalos.LAZENDUS, this.solarSize, false, true, 4.0F);
        this.renderObject(1.75F, 0.0F, 220.0F, false, SkyProviderChalos.DIONA, partialTicks);
    }

    @Override
    protected void renderStars(float starBrightness)
    {
        starBrightness = this.hasRain || this.useDefaultStarBrightness() ? starBrightness : this.getStarBrightness();
        GlStateManager.color(1.0F, 1.0F, 1.0F, this.hasRain || this.useDefaultStarBrightness() ? starBrightness : 1.0F);
    }

    @Override
    protected boolean useDefaultStarBrightness()
    {
        return true;
    }

    @Override
    protected float getStarBrightness()
    {
        return 0.0F;
    }

    @Override
    protected int getStarCount()
    {
        return 50000;
    }

    @Override
    protected double getStarSpreadMultiplier()
    {
        return 150.0D;
    }
}
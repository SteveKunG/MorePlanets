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
    private static final ResourceLocation NIBIRU = new ResourceLocation("moreplanets:textures/gui/celestialbodies/nibiru.png");
    private static final ResourceLocation FRONOS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/fronos.png");

    public SkyProviderChalos(float solarSize)
    {
        this.solarSize = 70.0F * solarSize;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
        this.renderSolarAura(14.0F, 30.0F, world.getStarBrightness(partialTicks), ColorUtils.stringToRGB("217, 123, 38, 102"), partialTicks);
        this.renderSolar(LAZENDUS, this.solarSize, false, true, 4.0F);
        this.renderObject(1.2F, 240.0F, 200.0F, true, NIBIRU, partialTicks, 0.2f);
        this.renderObject(0.8F, 200.0F, 220.0F, true, DIONA, partialTicks, 0.1f);
        this.renderObject(0.6F, -50.0F, 180.0F, true, FRONOS, partialTicks, 0.05f);
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
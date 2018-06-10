package stevekung.mods.moreplanets.moons.koentus.client.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderBaseMP;

public class SkyProviderKoentus extends SkyProviderBaseMP
{
    private static final ResourceLocation LAZENDUS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/lazendus.png");
    private static final ResourceLocation DIONA = new ResourceLocation("moreplanets:textures/gui/celestialbodies/diona.png");
    private static final ResourceLocation CHALOS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/chalos.png");

    public SkyProviderKoentus(float solarSize)
    {
        this.solarSize = 47.5F * solarSize;
        this.useColoredStar = false;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
        this.renderSolar(SkyProviderKoentus.LAZENDUS, this.solarSize, true, true, 4.0F);
        this.renderObject(2.0F, 50.0F, 200.0F, true, SkyProviderKoentus.DIONA, partialTicks);
        this.renderObject(1.0F, -50.0F, -110.0F, true, SkyProviderKoentus.CHALOS, partialTicks);
    }

    @Override
    protected void renderStars(float starBrightness)
    {
        GlStateManager.color(starBrightness, starBrightness, starBrightness, this.getStarBrightness());
    }

    @Override
    protected float getStarBrightness()
    {
        return 0.35F;
    }

    @Override
    protected int getStarCount()
    {
        return 75000;
    }

    @Override
    protected double getStarSpreadMultiplier()
    {
        return 150.0D;
    }
}
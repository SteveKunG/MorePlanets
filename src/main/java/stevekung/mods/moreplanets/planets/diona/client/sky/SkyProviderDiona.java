package stevekung.mods.moreplanets.planets.diona.client.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderBaseMP;

public class SkyProviderDiona extends SkyProviderBaseMP
{
    private static final ResourceLocation LAZENDUS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/lazendus.png");
    private static final ResourceLocation CHALOS = new ResourceLocation("moreplanets:textures/gui/celestialbodies/chalos.png");
    private static final ResourceLocation NIBIRU = new ResourceLocation("moreplanets:textures/gui/celestialbodies/nibiru.png");

    public SkyProviderDiona(float solarSize)
    {
        this.solarSize = 50.0F * solarSize;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
        this.renderSolar(SkyProviderDiona.LAZENDUS, this.solarSize, true, true, 4.0F);
        this.renderObject(2.0F, 50.0F, 200.0F, true, SkyProviderDiona.CHALOS, partialTicks);
        this.renderObject(1.5F, -150.0F, -200.0F, true, SkyProviderDiona.NIBIRU, partialTicks);
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
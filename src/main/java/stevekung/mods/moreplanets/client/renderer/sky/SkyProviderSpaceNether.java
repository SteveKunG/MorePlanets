package stevekung.mods.moreplanets.client.renderer.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class SkyProviderSpaceNether extends SkyProviderBaseMP
{
    private static final ResourceLocation SUN = new ResourceLocation("galacticraftcore:textures/gui/planets/orbitalsun.png");
    private static final ResourceLocation MERCURY = new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/mercury.png");

    public SkyProviderSpaceNether(float solarSize)
    {
        this.solarSize = 65.0F * solarSize;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
        this.renderSolar(SkyProviderSpaceNether.SUN, this.solarSize, true, true, 4.0F);
        this.renderObject(3.0F, 50.0F, 200.0F, true, SkyProviderSpaceNether.MERCURY, partialTicks);
    }

    @Override
    protected void renderStars(float starBrightness)
    {
        GlStateManager.color(starBrightness, starBrightness, starBrightness, 1.0F);
    }

    @Override
    protected float getStarBrightness()
    {
        return 0.25F;
    }

    @Override
    protected int getStarCount()
    {
        return 30000;
    }

    @Override
    protected double getStarSpreadMultiplier()
    {
        return 100.0D;
    }
}
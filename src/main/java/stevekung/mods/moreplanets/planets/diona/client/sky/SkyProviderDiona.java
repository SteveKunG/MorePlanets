package stevekung.mods.moreplanets.planets.diona.client.sky;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderBaseMP;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

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
    protected void renderStars()//TODO More color
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Random rand = new Random(10842L);
        buffer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION);

        for (int i = 0; i < this.getStarCount(); ++i)
        {
            double d0 = rand.nextFloat() * 2.0F - 1.0F;
            double d1 = rand.nextFloat() * 2.0F - 1.0F;
            double d2 = rand.nextFloat() * 2.0F - 1.0F;
            double d3 = 0.15F + rand.nextFloat() * 0.1F;
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d4 < 1.0D && d4 > 0.01D)
            {
                d4 = 1.0D / Math.sqrt(d4);
                d0 *= d4;
                d1 *= d4;
                d2 *= d4;
                double d5 = d0 * (rand.nextDouble() * this.getStarSpreadMultiplier() + 100D);
                double d6 = d1 * (rand.nextDouble() * this.getStarSpreadMultiplier() + 100D);
                double d7 = d2 * (rand.nextDouble() * this.getStarSpreadMultiplier() + 100D);
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = rand.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for (int j = 0; j < 4; ++j)
                {
                    double d18 = ((j & 2) - 1) * d3;
                    double d19 = ((j + 1 & 2) - 1) * d3;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    buffer.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }
        tessellator.draw();
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
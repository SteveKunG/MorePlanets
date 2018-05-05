package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer.LayerZeliusCreeperCharge;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusCreeper;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderZeliusCreeper extends RenderLiving<EntityZeliusCreeper>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/zelius_creeper.png");

    public RenderZeliusCreeper(RenderManager manager)
    {
        super(manager, new ModelCreeper(), 0.5F);
        this.addLayer(new LayerGlowingTexture(this, "zelius_creeper_glow", true));
        this.addLayer(new LayerZeliusCreeperCharge(this));
    }

    @Override
    protected void preRenderCallback(EntityZeliusCreeper entity, float partialTicks)
    {
        float f1 = entity.getCreeperFlashIntensity(partialTicks);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }
        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GlStateManager.scale(f3, f4, f3);
    }

    @Override
    protected int getColorMultiplier(EntityZeliusCreeper entity, float lightBrightness, float partialTicks)
    {
        float f2 = entity.getCreeperFlashIntensity(partialTicks);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0)
            {
                i = 0;
            }
            if (i > 255)
            {
                i = 255;
            }
            short short1 = 255;
            short short2 = 255;
            short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZeliusCreeper entity)
    {
        return RenderZeliusCreeper.TEXTURE;
    }
}
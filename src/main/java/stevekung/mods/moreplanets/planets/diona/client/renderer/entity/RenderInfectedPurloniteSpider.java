package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSpider;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedPurloniteSpider extends RenderLiving<EntityInfectedPurloniteSpider>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_purlonite_spider.png");

    public RenderInfectedPurloniteSpider(RenderManager manager)
    {
        super(manager, new ModelSpider(), 1.0F);
        this.shadowSize *= 0.7F;
        this.addLayer(new LayerGlowingTexture<>(this, "infected_purlonite_spider_eyes", true));
    }

    @Override
    protected float getDeathMaxRotation(EntityInfectedPurloniteSpider entity)
    {
        return 180.0F;
    }

    @Override
    protected void preRenderCallback(EntityInfectedPurloniteSpider entity, float partialTicks)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedPurloniteSpider entity)
    {
        return RenderInfectedPurloniteSpider.TEXTURE;
    }
}
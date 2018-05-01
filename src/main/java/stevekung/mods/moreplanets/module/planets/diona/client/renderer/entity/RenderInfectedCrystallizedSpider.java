package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizedSpider;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizedSpider extends RenderLiving<EntityInfectedCrystallizedSpider>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_spider.png");

    public RenderInfectedCrystallizedSpider(RenderManager manager)
    {
        super(manager, new ModelSpider(), 1.0F);
        this.shadowSize *= 0.7F;
        this.addLayer(new LayerGlowingTexture(this, "infected_crystallized_spider_eyes", true));
    }

    @Override
    protected float getDeathMaxRotation(EntityInfectedCrystallizedSpider entity)
    {
        return 180.0F;
    }

    @Override
    protected void preRenderCallback(EntityInfectedCrystallizedSpider entity, float partialTicks)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizedSpider entity)
    {
        return RenderInfectedCrystallizedSpider.TEXTURE;
    }
}
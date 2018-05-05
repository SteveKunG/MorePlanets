package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity.layer.LayerInfectedCaveSpiderEyes;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedCaveSpider;

@SideOnly(Side.CLIENT)
public class RenderInfectedCaveSpider extends RenderLiving<EntityInfectedCaveSpider>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_cave_spider.png");

    public RenderInfectedCaveSpider(RenderManager manager)
    {
        super(manager, new ModelSpider(), 0.7F);
        this.addLayer(new LayerInfectedCaveSpiderEyes(this));
    }

    @Override
    protected float getDeathMaxRotation(EntityInfectedCaveSpider entity)
    {
        return 180.0F;
    }

    @Override
    protected void preRenderCallback(EntityInfectedCaveSpider entity, float partialTickTime)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCaveSpider entity)
    {
        return RenderInfectedCaveSpider.TEXTURE;
    }
}
package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedPurloniteWorm;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteWorm;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedPurloniteWorm extends RenderLiving<EntityInfectedPurloniteWorm>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_purlonite_worm.png");

    public RenderInfectedPurloniteWorm(RenderManager manager)
    {
        super(manager, new ModelInfectedPurloniteWorm(), 0.4F);
        this.addLayer(new LayerGlowingTexture(this, "infected_purlonite_worm_eyes", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedPurloniteWorm entity)
    {
        return RenderInfectedPurloniteWorm.TEXTURE;
    }
}
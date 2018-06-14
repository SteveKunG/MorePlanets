package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderZeliusZombie extends RenderBiped<EntityZeliusZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/zelius_zombie.png");

    public RenderZeliusZombie(RenderManager manager)
    {
        super(manager, new ModelZombie(), 0.5F);
        this.addLayer(new LayerGlowingTexture(this, "zelius_zombie_glow", true));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            @Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZeliusZombie entity)
    {
        return RenderZeliusZombie.TEXTURE;
    }
}
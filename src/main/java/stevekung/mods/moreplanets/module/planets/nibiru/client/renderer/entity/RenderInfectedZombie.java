package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelInfectedZombie;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedZombie;

@SideOnly(Side.CLIENT)
public class RenderInfectedZombie extends RenderBiped<EntityInfectedZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_zombie.png");

    public RenderInfectedZombie(RenderManager manager)
    {
        super(manager, new ModelInfectedZombie(), 0.5F);
        this.addLayer(new LayerHeldItem(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            @Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelInfectedZombie(0.5F, true);
                this.modelArmor = new ModelInfectedZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedZombie entity)
    {
        return RenderInfectedZombie.TEXTURE;
    }
}
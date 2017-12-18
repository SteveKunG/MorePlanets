package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizeWorm;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeWorm;
import stevekung.mods.moreplanets.util.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizeWorm extends RenderLiving<EntityInfectedCrystallizeWorm>
{
    public RenderInfectedCrystallizeWorm(RenderManager manager)
    {
        super(manager, new ModelInfectedCrystallizeWorm(), 0.4F);
        this.addLayer(new LayerGlowingTexture(this, "infected_crystallize_worm_eyes", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizeWorm entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_crystallize_worm.png");
    }
}
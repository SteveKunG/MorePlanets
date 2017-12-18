package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import micdoodle8.mods.galacticraft.planets.mars.client.model.ModelSludgeling;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlbetiusWorm;
import stevekung.mods.moreplanets.util.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderAlbetiusWorm extends RenderLiving<EntityAlbetiusWorm>
{
    public RenderAlbetiusWorm(RenderManager manager)
    {
        super(manager, new ModelSludgeling(), 0.2F);
        this.addLayer(new LayerGlowingTexture(this, "albetius_worm_eye", true));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlbetiusWorm entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/albetius_worm.png");
    }
}
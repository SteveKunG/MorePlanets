package stevekung.mods.moreplanets.module.planets.fronos.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.fronos.client.model.ModelBearry;
import stevekung.mods.moreplanets.module.planets.fronos.entity.EntityBearry;

@SideOnly(Side.CLIENT)
public class RenderBearry extends RenderLiving<EntityBearry>
{
    public RenderBearry(RenderManager manager)
    {
        super(manager, new ModelBearry(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBearry entity)
    {
        if (entity.isCloseEye())
        {
            return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry_close_eye.png");
        }
        if (entity.isHungry())
        {
            return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry_hungry.png");
        }
        if (entity.isPanic())
        {
            return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry_panic.png");
        }
        return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry.png");
    }
}
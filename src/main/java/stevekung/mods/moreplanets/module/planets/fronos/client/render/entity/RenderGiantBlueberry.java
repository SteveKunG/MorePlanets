package stevekung.mods.moreplanets.module.planets.fronos.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.fronos.client.model.ModelGiantBlueberry;
import stevekung.mods.moreplanets.module.planets.fronos.entity.EntityGiantBlueberry;

@SideOnly(Side.CLIENT)
public class RenderGiantBlueberry extends RenderLiving<EntityGiantBlueberry>
{
    public RenderGiantBlueberry(RenderManager manager)
    {
        super(manager, new ModelGiantBlueberry(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGiantBlueberry entity)
    {
        if (entity.isCloseEye())
        {
            return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry_close_eye.png");
        }
        if (entity.isHungry())
        {
            return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry_hungry.png");
        }
        if (entity.isPanic())
        {
            return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry_panic.png");
        }
        return new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry.png");
    }
}
package stevekung.mods.moreplanets.planets.fronos.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelGiantBlueberry;
import stevekung.mods.moreplanets.planets.fronos.entity.EntityGiantBlueberry;

@SideOnly(Side.CLIENT)
public class RenderGiantBlueberry extends RenderLiving<EntityGiantBlueberry>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry.png");
    private static final ResourceLocation CLOSE_EYE = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry_close_eye.png");
    private static final ResourceLocation HUNGRY = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry_hungry.png");
    private static final ResourceLocation PANIC = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/giant_blueberry_panic.png");

    public RenderGiantBlueberry(RenderManager manager)
    {
        super(manager, new ModelGiantBlueberry(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGiantBlueberry entity)
    {
        if (entity.isPanic())
        {
            return RenderGiantBlueberry.PANIC;
        }
        else
        {
            if (entity.isCloseEye())
            {
                return RenderGiantBlueberry.CLOSE_EYE;
            }
            if (entity.isHungry())
            {
                return RenderGiantBlueberry.HUNGRY;
            }
            return RenderGiantBlueberry.TEXTURE;
        }
    }
}
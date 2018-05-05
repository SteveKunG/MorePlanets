package stevekung.mods.moreplanets.planets.fronos.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.client.model.ModelBearry;
import stevekung.mods.moreplanets.planets.fronos.entity.EntityBearry;

@SideOnly(Side.CLIENT)
public class RenderBearry extends RenderLiving<EntityBearry>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry.png");
    private static final ResourceLocation CLOSE_EYE = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry_close_eye.png");
    private static final ResourceLocation HUNGRY = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry_hungry.png");
    private static final ResourceLocation PANIC = new ResourceLocation("moreplanets:textures/entity/fronos_fruits/bearry_panic.png");

    public RenderBearry(RenderManager manager)
    {
        super(manager, new ModelBearry(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBearry entity)
    {
        if (entity.isCloseEye())
        {
            return RenderBearry.CLOSE_EYE;
        }
        if (entity.isHungry())
        {
            return RenderBearry.HUNGRY;
        }
        if (entity.isPanic())
        {
            return RenderBearry.PANIC;
        }
        return RenderBearry.TEXTURE;
    }
}
package stevekung.mods.moreplanets.module.planets.fronos.client.render.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.fronos.client.model.ModelMarshmallow;
import stevekung.mods.moreplanets.module.planets.fronos.entity.EntityMarshmallow;

@SideOnly(Side.CLIENT)
public class RenderMarshmallow extends RenderLiving<EntityMarshmallow>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow.png");
    private static final ResourceLocation CLOSE_EYE = new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow_close_eye.png");
    private static final ResourceLocation HUNGRY = new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow_hungry.png");
    private static final ResourceLocation PANIC = new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow_panic.png");

    public RenderMarshmallow(RenderManager manager)
    {
        super(manager, new ModelMarshmallow(), 0.35F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMarshmallow entity)
    {
        if (entity.isCloseEye())
        {
            return RenderMarshmallow.CLOSE_EYE;
        }
        if (entity.isHungry())
        {
            return RenderMarshmallow.HUNGRY;
        }
        if (entity.isPanic())
        {
            return RenderMarshmallow.PANIC;
        }
        return RenderMarshmallow.TEXTURE;
    }

    @Override
    public void preRenderCallback(EntityMarshmallow entity, float partialTicks)
    {
        if (entity.isSitting())
        {
            GlStateManager.scale(0.8F, 0.8F, 0.8F);
        }
    }
}
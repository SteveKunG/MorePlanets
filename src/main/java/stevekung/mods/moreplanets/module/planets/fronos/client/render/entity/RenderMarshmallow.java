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
    public RenderMarshmallow(RenderManager manager)
    {
        super(manager, new ModelMarshmallow(), 0.35F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMarshmallow entity)
    {
        if (entity.isCloseEye())
        {
            return new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow_close_eye.png");
        }
        if (entity.isHungry())
        {
            return new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow_hungry.png");
        }
        if (entity.isPanic())
        {
            return new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow_panic.png");
        }
        return new ResourceLocation("moreplanets:textures/entity/marshmallow/marshmallow.png");
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
package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelGiantWorm;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityGiantWorm;

@SideOnly(Side.CLIENT)
public class RenderGiantWorm extends RenderLiving<EntityGiantWorm>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/giant_worm.png");

    public RenderGiantWorm(RenderManager manager)
    {
        super(manager, new ModelGiantWorm(), 0.65F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGiantWorm entity)
    {
        return RenderGiantWorm.TEXTURE;
    }

    @Override
    protected float getDeathMaxRotation(EntityGiantWorm entity)
    {
        return 180.0F;
    }

    @Override
    protected void preRenderCallback(EntityGiantWorm entity, float partialTicks)
    {
        GlStateManager.scale(0.25F, 0.25F, 0.25F);
    }
}
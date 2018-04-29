package stevekung.mods.moreplanets.module.planets.fronos.client.render.entity;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.fronos.entity.EntityJellySlime;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerBaseSlimeGel;

@SideOnly(Side.CLIENT)
public class RenderJellySlime extends RenderLiving<EntityJellySlime>
{
    private static final ResourceLocation strawberrySlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/strawberry.png");
    private static final ResourceLocation berrySlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/berry.png");
    private static final ResourceLocation raspberrySlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/raspberry.png");
    private static final ResourceLocation orangeSlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/orange.png");
    private static final ResourceLocation grapeSlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/grape.png");
    private static final ResourceLocation limeSlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/lime.png");
    private static final ResourceLocation greenSlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/green.png");
    private static final ResourceLocation lemonSlimeTexture = new ResourceLocation("moreplanets:textures/entity/jelly_slime/lemon.png");

    public RenderJellySlime(RenderManager render)
    {
        super(render, new ModelSlime(16), 0.25F);
        this.addLayer(new LayerBaseSlimeGel(this));
    }

    @Override
    protected void preRenderCallback(EntityJellySlime entity, float partialTickTime)
    {
        float f1 = entity.getSlimeSize();
        float f2 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityJellySlime entity)
    {
        switch (entity.getJellySlimeType())
        {
        case 0:
        default:
            return RenderJellySlime.grapeSlimeTexture;
        case 1:
            return RenderJellySlime.raspberrySlimeTexture;
        case 2:
            return RenderJellySlime.strawberrySlimeTexture;
        case 3:
            return RenderJellySlime.berrySlimeTexture;
        case 4:
            return RenderJellySlime.limeSlimeTexture;
        case 5:
            return RenderJellySlime.orangeSlimeTexture;
        case 6:
            return RenderJellySlime.greenSlimeTexture;
        case 7:
            return RenderJellySlime.lemonSlimeTexture;
        }
    }

    @Override
    public void doRender(EntityJellySlime entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
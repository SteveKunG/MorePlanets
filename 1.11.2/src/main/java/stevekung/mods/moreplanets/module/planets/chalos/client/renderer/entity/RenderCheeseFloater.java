package stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.client.model.ModelCheeseFloater;
import stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.layer.LayerCheeseFloaterEye;
import stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.layer.LayerCheeseFloaterSlime;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseFloater;

@SideOnly(Side.CLIENT)
public class RenderCheeseFloater extends RenderLiving<EntityCheeseFloater>
{
    public RenderCheeseFloater(RenderManager manager)
    {
        super(manager, new ModelCheeseFloater(), 0.5F);
        this.addLayer(new LayerCheeseFloaterEye(this));
        this.addLayer(new LayerCheeseFloaterSlime(this));
    }

    @Override
    protected void preRenderCallback(EntityCheeseFloater entity, float partialTicks)
    {
        float partialTicksTime = entity.ticksExisted + partialTicks;
        float hoverTime = MathHelper.sin(partialTicksTime / 12) / 30.0F + 0.5F;
        hoverTime = hoverTime * hoverTime + hoverTime;
        GlStateManager.translate(0.0F, 0.5F - hoverTime, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCheeseFloater entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/cheese_floater.png");
    }
}
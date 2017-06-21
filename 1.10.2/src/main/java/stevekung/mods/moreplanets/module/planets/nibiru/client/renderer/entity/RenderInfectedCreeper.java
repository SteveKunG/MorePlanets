package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelInfectedCreeper;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity.layer.LayerInfectedCreeperCharge;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedCreeper;

@SideOnly(Side.CLIENT)
public class RenderInfectedCreeper extends RenderLiving<EntityInfectedCreeper>
{
    public RenderInfectedCreeper(RenderManager manager)
    {
        super(manager, new ModelInfectedCreeper(), 0.5F);
        this.addLayer(new LayerInfectedCreeperCharge(this));
    }

    @Override
    protected void preRenderCallback(EntityInfectedCreeper entity, float partialTickTime)
    {
        float f = entity.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp_float(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }

    @Override
    protected int getColorMultiplier(EntityInfectedCreeper entity, float lightBrightness, float partialTickTime)
    {
        float f = entity.getCreeperFlashIntensity(partialTickTime);

        if ((int)(f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp_int(i, 0, 255);
            return i << 24 | 16777215;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCreeper entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_creeper.png");
    }
}
package stevekung.mods.moreplanets.planets.pluto.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.pluto.entities.EntityPlutoAlien;
import stevekung.mods.moreplanets.planets.pluto.model.ModelPlutoAlien;

@SideOnly(Side.CLIENT)
public class RenderPlutoAlien extends RenderLiving
{
    private ResourceLocation texture = new ResourceLocation("pluto:textures/model/pluto_alien.png");

    public RenderPlutoAlien()
    {
        super(new ModelPlutoAlien(), 0.5F);
    }

    protected void preRenderCallback(EntityPlutoAlien entity, float partialTicks)
    {
        float f1 = entity.getCreeperFlashIntensity(partialTicks);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }
        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }
        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GL11.glScalef(f3, f4, f3);
    }

    protected int getColorMultiplier(EntityPlutoAlien entity, float p_77030_2_, float partialTicks)
    {
        float f2 = entity.getCreeperFlashIntensity(partialTicks);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0)
            {
                i = 0;
            }
            if (i > 255)
            {
                i = 255;
            }
            short short1 = 255;
            short short2 = 255;
            short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTicks)
    {
        this.preRenderCallback((EntityPlutoAlien)entity, partialTicks);
    }

    @Override
    protected int getColorMultiplier(EntityLivingBase entity, float p_77030_2_, float partialTicks)
    {
        return this.getColorMultiplier((EntityPlutoAlien)entity, p_77030_2_, partialTicks);
    }

    @Override
    protected int inheritRenderPass(EntityLivingBase entity, int p_77035_2_, float partialTicks)
    {
        return -1;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.texture;
    }
}
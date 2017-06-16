/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaSquid;

@SideOnly(Side.CLIENT)
public class RenderEuropaSquid extends RenderLiving
{
    private ResourceLocation squidTextures = new ResourceLocation("europa:textures/model/europa_squid.png");
    private ResourceLocation squidGlowingTextures = new ResourceLocation("europa:textures/model/europa_squid_glowing.png");

    public RenderEuropaSquid()
    {
        super(new ModelSquid(), 0.7F);
        this.setRenderPassModel(new ModelSquid());
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase entity, float p_77044_2_)
    {
        return ((EntityEuropaSquid)entity).lastTentacleAngle + (((EntityEuropaSquid)entity).tentacleAngle - ((EntityEuropaSquid)entity).lastTentacleAngle) * p_77044_2_;
    }

    @Override
    protected void rotateCorpse(EntityLivingBase entity, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        float f3 = ((EntityEuropaSquid)entity).prevSquidPitch + (((EntityEuropaSquid)entity).squidPitch - ((EntityEuropaSquid)entity).prevSquidPitch) * p_77043_4_;
        float f4 = ((EntityEuropaSquid)entity).prevSquidYaw + (((EntityEuropaSquid)entity).squidYaw - ((EntityEuropaSquid)entity).prevSquidYaw) * p_77043_4_;
        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
        GL11.glRotatef(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(f4, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, -1.2F, 0.0F);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.squidBrightness((EntityEuropaSquid)p_77032_1_, p_77032_2_);
    }

    protected int squidBrightness(EntityEuropaSquid p_77032_1_, int p_77032_2_)
    {
        if (p_77032_2_ != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(this.squidGlowingTextures);
            float var4 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if (p_77032_1_.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
            return 1;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.squidTextures;
    }
}
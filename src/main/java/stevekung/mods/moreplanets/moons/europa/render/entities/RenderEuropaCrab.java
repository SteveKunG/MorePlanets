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
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.moons.europa.entities.EntityEuropaCrab;
import stevekung.mods.moreplanets.moons.europa.entities.models.ModelEuropaCrab;

@SideOnly(Side.CLIENT)
public class RenderEuropaCrab extends RenderLiving
{
    private ResourceLocation textures1 = new ResourceLocation("europa:textures/model/europa_crab.png");
    private ResourceLocation textures2 = new ResourceLocation("europa:textures/model/black_europa_crab.png");
    private ResourceLocation textures3 = new ResourceLocation("europa:textures/model/europa_crab_glow.png");
    private ResourceLocation texturesGlowing = new ResourceLocation("europa:textures/model/europa_crab_glowing.png");

    public RenderEuropaCrab()
    {
        super(new ModelEuropaCrab(), 0.3F);
        this.setRenderPassModel(new ModelEuropaCrab());
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float par3)
    {
        if (((EntityEuropaCrab)entity).getCrabType() == 2)
        {
            return this.brightness((EntityEuropaCrab)entity, pass);
        }
        return super.shouldRenderPass(entity, pass, par3);
    }

    protected int brightness(EntityEuropaCrab entity, int pass)
    {
        if (pass != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(this.texturesGlowing);
            float var4 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if (entity.isInvisible())
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
        EntityEuropaCrab crab = (EntityEuropaCrab)entity;

        if (crab.getCrabType() == 1)
        {
            return this.textures2;
        }
        else if (crab.getCrabType() == 2)
        {
            return this.textures3;
        }
        return this.textures1;
    }
}
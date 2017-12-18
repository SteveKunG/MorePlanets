/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityEvolvedInfectedSpiderBoss;

@SideOnly(Side.CLIENT)
public class RenderInfectedEvolvedSpiderBoss extends RenderLiving
{
    private ResourceLocation spiderEyesTextures = new ResourceLocation("nibiru:textures/model/infected_spider_eyes.png");
    private ResourceLocation spiderTextures = new ResourceLocation("nibiru:textures/model/infected_spider_boss.png");

    public RenderInfectedEvolvedSpiderBoss()
    {
        super(new ModelSpider(), 1.0F);
        this.setRenderPassModel(new ModelSpider());
    }

    protected int shouldRenderPass(EntityEvolvedInfectedSpiderBoss spider, int par2)
    {
        if (par2 != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(this.spiderEyesTextures);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (spider.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }
            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            return 1;
        }
    }

    @Override
    public void doRender(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.setBossStatus((IBossDisplayData) entity, false);
        super.doRender(entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float par2)
    {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }

    protected ResourceLocation getEntityTexture(EntityEvolvedInfectedSpiderBoss entity)
    {
        return this.spiderTextures;
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase entity)
    {
        return 180.0F;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int par2, float par3)
    {
        return this.shouldRenderPass((EntityEvolvedInfectedSpiderBoss)entity, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityEvolvedInfectedSpiderBoss)entity);
    }
}
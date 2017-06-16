/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.render.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntityEvolvedSiriusBlazeBoss;

public class RenderEvolvedSiriusBlazeBoss extends RenderLiving
{
    private ResourceLocation blazeTextures = new ResourceLocation("siriusb:textures/model/evolved_sirius_blaze_boss.png");
    private ResourceLocation powerTexture = new ResourceLocation("galacticraftcore:textures/model/power.png");

    public RenderEvolvedSiriusBlazeBoss()
    {
        super(new ModelBlaze(), 0.5F);
    }

    protected ResourceLocation getBlazeTextures(EntityEvolvedSiriusBlazeBoss par1EntityBlaze)
    {
        return this.blazeTextures;
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.setBossStatus((IBossDisplayData) par1EntityLiving, false);
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getBlazeTextures((EntityEvolvedSiriusBlazeBoss)par1Entity);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
    {
        GL11.glScalef(4.0F, 4.0F, 4.0F);
    }

    @Override
    protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
    {
        return super.getColorMultiplier(par1EntityLivingBase, par2, par3);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_27006_a((EntityEvolvedSiriusBlazeBoss) par1EntityLivingBase, par2, par3);
    }

    @Override
    protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return -1;
    }

    protected int func_27006_a(EntityEvolvedSiriusBlazeBoss blaze, int par2, float par3)
    {
        if (par2 == 1)
        {
            if (blaze.getHealth() <= blaze.getMaxHealth() / 2.0F)
            {
                float var4 = blaze.ticksExisted + par3;
                this.bindTexture(this.powerTexture);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float var5 = var4 * 0.01F;
                float var6 = var4 * 0.01F;
                GL11.glTranslatef(var5, var6, 0.0F);
                this.setRenderPassModel(this.mainModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float var7 = 0.5F;
                GL11.glColor4f(var7, var7, var7, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                return 1;
            }
        }
        if (par2 == 2)
        {
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
        }
        return -1;
    }
}
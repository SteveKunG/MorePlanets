/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.render.entities.projectiles;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.diona.entities.models.ModelLaser;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;

@SideOnly(Side.CLIENT)
public class RenderLaser extends Render
{
    public ModelBase laser = new ModelLaser();

    public void renderLaser(EntityLaserMP laser, double par2, double par4, double par6, float par8, float par9)
    {
        this.bindEntityTexture(laser);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(laser.prevRotationYaw + (laser.rotationYaw - laser.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(laser.prevRotationPitch + (laser.rotationPitch - laser.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        this.laser.render(laser, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLaser((EntityLaserMP)par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityLaserMP)entity);
    }

    protected ResourceLocation getEntityTexture(EntityLaserMP entity)
    {
        if (entity.getLaserType() == 0)
        {
            return new ResourceLocation("diona:textures/model/laser/laser.png");
        }
        else if (entity.getLaserType() == 1)
        {
            return new ResourceLocation("diona:textures/model/laser/hyper.png");
        }
        else if (entity.getLaserType() == 2)
        {
            return new ResourceLocation("diona:textures/model/laser/emp.png");
        }
        else if (entity.getLaserType() == 3)
        {
            return new ResourceLocation("diona:textures/model/laser/uranium.png");
        }
        else if (entity.getLaserType() == 4)
        {
            return new ResourceLocation("diona:textures/model/laser/icy_poison.png");
        }
        return null;
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
import stevekung.mods.moreplanets.planets.siriusb.entities.models.ModelSiriusMagmaCube;

@SideOnly(Side.CLIENT)
public class RenderSiriusMagmaCube extends RenderLiving
{
    private static final ResourceLocation magmaCubeTextures = new ResourceLocation("siriusb:textures/model/sirius_magma_cube.png");

    public RenderSiriusMagmaCube()
    {
        super(new ModelSiriusMagmaCube(), 0.25F);
    }

    protected ResourceLocation getMagmaCubeTextures(EntitySiriusMagmaCube par1EntityMagmaCube)
    {
        return RenderSiriusMagmaCube.magmaCubeTextures;
    }

    protected void scaleMagmaCube(EntitySiriusMagmaCube par1EntityMagmaCube, float par2)
    {
        final int i = par1EntityMagmaCube.getSlimeSize();
        final float f1 = (par1EntityMagmaCube.prevSquishFactor + (par1EntityMagmaCube.squishFactor - par1EntityMagmaCube.prevSquishFactor) * par2) / (i * 0.5F + 1.0F);
        final float f2 = 1.0F / (f1 + 1.0F);
        final float f3 = i;
        GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scaleMagmaCube((EntitySiriusMagmaCube)par1EntityLivingBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getMagmaCubeTextures((EntitySiriusMagmaCube)par1Entity);
    }
}
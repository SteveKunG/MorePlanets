/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityMarshmallow;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelMarshmallow;

@SideOnly(Side.CLIENT)
public class RenderMarshmallow extends RenderLiving
{
    private static ResourceLocation marshmallowTextures = new ResourceLocation("fronos:textures/model/marshmallow.png");

    public RenderMarshmallow()
    {
        super(new ModelMarshmallow(), 0.35F);
    }

    protected ResourceLocation marshmallowTexture(EntityMarshmallow marshmallow)
    {
        return RenderMarshmallow.marshmallowTextures;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.rendermarshmallow((EntityMarshmallow)par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.rendermarshmallow((EntityMarshmallow)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.marshmallowTexture((EntityMarshmallow)par1Entity);
    }

    @Override
    public void preRenderCallback(EntityLivingBase living, float par2)
    {
        this.preRenderCallback((EntityMarshmallow)living, par2);
    }

    public void preRenderCallback(EntityMarshmallow living, float par2)
    {
        if (living.isSitting())
        {
            GL11.glScaled(0.8F, 0.8F, 0.8F);
        }
    }

    public void rendermarshmallow(EntityMarshmallow marshmallow, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(marshmallow, par2, par4, par6, par8, par9);
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
    {
        return 90.0F;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
    {
        return -1;
    }
}
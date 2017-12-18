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
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.models.ModelGiantWorm;

@SideOnly(Side.CLIENT)
public class RenderGiantWorm extends RenderLiving
{
    private static final ResourceLocation giantWormTexture = new ResourceLocation("nibiru:textures/model/giant_worm.png");

    protected void scaleMob(float f)
    {
        GL11.glScalef(f, f, f);
    }

    public RenderGiantWorm()
    {
        super(new ModelGiantWorm(5.0F), 0.65F);
    }

    protected ResourceLocation giantWormTexture(EntityGiantWorm par1EntityGiantWorm)
    {
        return RenderGiantWorm.giantWormTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.giantWormTexture((EntityGiantWorm) par1Entity);
    }

    public void renderGiantWorm(EntityGiantWorm par1EntityGiantWorm, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityGiantWorm, par2, par4, par6, par8, par9);
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
    {
        return 180.0F;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
    {
        return -1;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
    {
        GL11.glScalef(0.25F, 0.25F, 0.25F);
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGiantWorm((EntityGiantWorm) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGiantWorm((EntityGiantWorm) par1Entity, par2, par4, par6, par8, par9);
    }
}
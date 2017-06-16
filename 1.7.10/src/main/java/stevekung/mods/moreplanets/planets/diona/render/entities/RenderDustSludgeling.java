/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.render.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.planets.mars.client.model.ModelSludgeling;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDustSludgeling;

@SideOnly(Side.CLIENT)
public class RenderDustSludgeling extends RenderLiving
{
    private static final ResourceLocation delriumSludgelingTexture = new ResourceLocation("diona:textures/model/dust_sludgeling.png");

    public RenderDustSludgeling()
    {
        super(new ModelSludgeling(), 0.2F);
    }

    protected ResourceLocation delriumWormTexture(EntityDustSludgeling entityDelriumWorm)
    {
        return RenderDustSludgeling.delriumSludgelingTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.delriumWormTexture((EntityDustSludgeling) par1Entity);
    }

    public void renderDelriumWorm(EntityDustSludgeling entityDelriumWorm, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(entityDelriumWorm, par2, par4, par6, par8, par9);
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
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderDelriumWorm((EntityDustSludgeling) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderDelriumWorm((EntityDustSludgeling) par1Entity, par2, par4, par6, par8, par9);
    }
}
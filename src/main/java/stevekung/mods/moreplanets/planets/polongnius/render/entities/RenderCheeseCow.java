/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.render.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCow;

@SideOnly(Side.CLIENT)
public class RenderCheeseCow extends RenderLiving
{
    private static final ResourceLocation cheeseCowTextures = new ResourceLocation("polongnius:textures/model/cheese_cow.png");

    public RenderCheeseCow()
    {
        super(new ModelCow(), 0.6F);
    }

    public void renderLivingMooshroom(EntityCheeseCow par1EntityMooshroom, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityMooshroom, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getMooshroomTextures(EntityCheeseCow par1EntityMooshroom)
    {
        return RenderCheeseCow.cheeseCowTextures;
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMooshroom((EntityCheeseCow)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMooshroom((EntityCheeseCow)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getMooshroomTextures((EntityCheeseCow)par1Entity);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMooshroom((EntityCheeseCow)par1Entity, par2, par4, par6, par8, par9);
    }
}
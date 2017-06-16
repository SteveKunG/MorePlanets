/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStarfish;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelStarfish;

@SideOnly(Side.CLIENT)
public class RenderStarfish extends RenderLiving
{
    private static final ResourceLocation starfishTextures = new ResourceLocation("fronos:textures/model/space_starfish.png");

    public RenderStarfish()
    {
        super(new ModelStarfish(), 0.3F);
    }

    protected ResourceLocation starFishTexture(EntityStarfish starFish)
    {
        return RenderStarfish.starfishTextures;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderStarFish((EntityStarfish)par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderStarFish((EntityStarfish)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.starFishTexture((EntityStarfish)par1Entity);
    }

    public void renderStarFish(EntityStarfish starFish, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(starFish, par2, par4, par6, par8, par9);
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
    {
        return 0.0F;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
    {
        return -1;
    }
}
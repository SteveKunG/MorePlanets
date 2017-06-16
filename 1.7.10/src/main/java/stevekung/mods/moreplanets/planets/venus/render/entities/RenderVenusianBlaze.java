/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.render.entities;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;

public class RenderVenusianBlaze extends RenderLiving
{
    private ResourceLocation blazeTextures = new ResourceLocation("venus:textures/model/venusian_blaze.png");
    private int field_77068_a;

    public RenderVenusianBlaze()
    {
        super(new ModelBlaze(), 0.5F);
        this.field_77068_a = ((ModelBlaze)this.mainModel).func_78104_a();
    }

    public void renderBlaze(EntityVenusianBlaze par1EntityBlaze, double par2, double par4, double par6, float par8, float par9)
    {
        int i = ((ModelBlaze)this.mainModel).func_78104_a();

        if (i != this.field_77068_a)
        {
            this.field_77068_a = i;
            this.mainModel = new ModelBlaze();
        }
        super.doRender(par1EntityBlaze, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getBlazeTextures(EntityVenusianBlaze par1EntityBlaze)
    {
        return this.blazeTextures;
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBlaze((EntityVenusianBlaze)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBlaze((EntityVenusianBlaze)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getBlazeTextures((EntityVenusianBlaze)par1Entity);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBlaze((EntityVenusianBlaze)par1Entity, par2, par4, par6, par8, par9);
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.render.entities;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;

public class RenderSiriusBlaze extends RenderLiving
{
    private static ResourceLocation blazeTextures = new ResourceLocation("siriusb:textures/model/sirius_blaze.png");
    private int field_77068_a;

    public RenderSiriusBlaze()
    {
        super(new ModelBlaze(), 0.5F);
        this.field_77068_a = ((ModelBlaze)this.mainModel).func_78104_a();
    }

    public void renderBlaze(EntitySiriusBlaze par1EntityBlaze, double par2, double par4, double par6, float par8, float par9)
    {
        int i = ((ModelBlaze)this.mainModel).func_78104_a();

        if (i != this.field_77068_a)
        {
            this.field_77068_a = i;
            this.mainModel = new ModelBlaze();
        }
        super.doRender(par1EntityBlaze, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getBlazeTextures(EntitySiriusBlaze par1EntityBlaze)
    {
        return RenderSiriusBlaze.blazeTextures;
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBlaze((EntitySiriusBlaze)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBlaze((EntitySiriusBlaze)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getBlazeTextures((EntitySiriusBlaze)par1Entity);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBlaze((EntitySiriusBlaze)par1Entity, par2, par4, par6, par8, par9);
    }
}
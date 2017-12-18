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
import stevekung.mods.moreplanets.planets.fronos.entities.EntityLemonDuck;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelLemonDuck;

@SideOnly(Side.CLIENT)
public class RenderLemonDuck extends RenderLiving
{
    private static final ResourceLocation lemonDuckTextures = new ResourceLocation("fronos:textures/model/lemon_duck.png");

    public RenderLemonDuck()
    {
        super(new ModelLemonDuck(), 0.35F);
    }

    protected ResourceLocation giviTexture(EntityLemonDuck par1)
    {
        return RenderLemonDuck.lemonDuckTextures;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGivi((EntityLemonDuck)par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGivi((EntityLemonDuck)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.giviTexture((EntityLemonDuck)par1Entity);
    }

    public void renderGivi(EntityLemonDuck marshmallow, double par2, double par4, double par6, float par8, float par9)
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
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStrawberryChicken;

@SideOnly(Side.CLIENT)
public class RenderStrawberryChicken extends RenderLiving
{
    private static final ResourceLocation chickenTextures = new ResourceLocation("fronos:textures/model/strawberry_chicken.png");

    public RenderStrawberryChicken()
    {
        super(new ModelChicken(), 0.3F);
    }

    public void renderChicken(EntityStrawberryChicken par1EntityChicken, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityChicken, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getChickenTextures(EntityStrawberryChicken par1EntityChicken)
    {
        return RenderStrawberryChicken.chickenTextures;
    }

    protected float getWingRotation(EntityStrawberryChicken par1EntityChicken, float par2)
    {
        final float f1 = par1EntityChicken.field_70888_h + (par1EntityChicken.field_70886_e - par1EntityChicken.field_70888_h) * par2;
        final float f2 = par1EntityChicken.field_70884_g + (par1EntityChicken.destPos - par1EntityChicken.field_70884_g) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityStrawberryChicken)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getWingRotation((EntityStrawberryChicken)par1EntityLivingBase, par2);
    }

    @Override
    public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityStrawberryChicken)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getChickenTextures((EntityStrawberryChicken)par1Entity);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntityStrawberryChicken)par1Entity, par2, par4, par6, par8, par9);
    }
}
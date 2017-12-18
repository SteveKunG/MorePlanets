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
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;

@SideOnly(Side.CLIENT)
public class RenderCreamCat extends RenderLiving
{
    private static final ResourceLocation chocolateCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/chocolate.png");
    private static final ResourceLocation spaceCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/default.png");
    private static final ResourceLocation vanillaCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/vanilla.png");
    private static final ResourceLocation strawberryCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/strawberry.png");
    private static final ResourceLocation orangeCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/orange.png");
    private static final ResourceLocation teaCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/tea.png");
    private static final ResourceLocation lemonCatTextures = new ResourceLocation("fronos:textures/model/cream_cat/lemon.png");

    public RenderCreamCat(ModelBase model)
    {
        super(model, 0.1F);
    }

    public void renderLivingOcelot(EntityCreamCat par1EntityOcelot, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityOcelot, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110874_a(EntityCreamCat par1EntityOcelot)
    {
        switch (par1EntityOcelot.getTameSkin())
        {
        case 0:
        default:
            return RenderCreamCat.spaceCatTextures;
        case 1:
            return RenderCreamCat.vanillaCatTextures;
        case 2:
            return RenderCreamCat.chocolateCatTextures;
        case 3:
            return RenderCreamCat.strawberryCatTextures;
        case 4:
            return RenderCreamCat.orangeCatTextures;
        case 5:
            return RenderCreamCat.teaCatTextures;
        case 6:
            return RenderCreamCat.lemonCatTextures;
        }
    }

    protected void preRenderOcelot(EntityCreamCat par1EntityOcelot, float par2)
    {
        super.preRenderCallback(par1EntityOcelot, par2);

        if (par1EntityOcelot.isTamed())
        {
            GL11.glScalef(0.8F, 0.8F, 0.8F);
        }
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingOcelot((EntityCreamCat)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderOcelot((EntityCreamCat)par1EntityLivingBase, par2);
    }

    @Override
    public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingOcelot((EntityCreamCat)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110874_a((EntityCreamCat)par1Entity);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingOcelot((EntityCreamCat)par1Entity, par2, par4, par6, par8, par9);
    }
}
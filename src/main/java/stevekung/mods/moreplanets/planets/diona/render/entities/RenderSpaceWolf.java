/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;

@SideOnly(Side.CLIENT)
public class RenderSpaceWolf extends RenderLiving
{
    private static final ResourceLocation wolfTextures = new ResourceLocation("diona:textures/model/space_wolf/space_wolf.png");
    private static final ResourceLocation tamedWolfTextures = new ResourceLocation("diona:textures/model/space_wolf/space_wolf_tamed.png");
    private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("diona:textures/model/space_wolf/space_wolf_angry.png");
    private static final ResourceLocation wolfCollarTextures = new ResourceLocation("diona:textures/model/space_wolf/space_wolf_collar.png");

    public RenderSpaceWolf(ModelBase par1ModelBase, ModelBase par2ModelBase)
    {
        super(par1ModelBase, 0.5F);
        this.setRenderPassModel(par2ModelBase);
    }

    protected float getTailRotation(EntitySpaceWolf par1EntityWolf, float par2)
    {
        return par1EntityWolf.getTailRotation();
    }

    protected int func_82447_a(EntitySpaceWolf par1EntityWolf, int par2, float par3)
    {
        float f1;

        if (par2 == 0 && par1EntityWolf.getWolfShaking())
        {
            f1 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
            this.bindTexture(RenderSpaceWolf.wolfTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (par2 == 1 && par1EntityWolf.isTamed())
        {
            this.bindTexture(RenderSpaceWolf.wolfCollarTextures);
            f1 = 1.0F;
            final int j = par1EntityWolf.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110914_a(EntitySpaceWolf par1EntityWolf)
    {
        return par1EntityWolf.isTamed() ? RenderSpaceWolf.tamedWolfTextures : par1EntityWolf.isAngry() ? RenderSpaceWolf.anrgyWolfTextures : RenderSpaceWolf.wolfTextures;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntitySpaceWolf)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getTailRotation((EntitySpaceWolf)par1EntityLivingBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((EntitySpaceWolf)par1Entity);
    }
}
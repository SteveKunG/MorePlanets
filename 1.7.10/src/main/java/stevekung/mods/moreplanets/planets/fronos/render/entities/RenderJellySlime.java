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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;

@SideOnly(Side.CLIENT)
public class RenderJellySlime extends RenderLiving
{
    private static ResourceLocation strawberrySlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/strawberry.png");
    private static ResourceLocation berrySlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/berry.png");
    private static ResourceLocation raspberrySlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/raspberry.png");
    private static ResourceLocation orangeSlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/orange.png");
    private static ResourceLocation grapeSlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/grape.png");
    private static ResourceLocation limeSlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/lime.png");
    private static ResourceLocation greenSlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/green.png");
    private static ResourceLocation lemonSlimeTextures = new ResourceLocation("fronos:textures/model/jelly_slime/lemon.png");

    private ModelBase scaleAmount;

    public RenderJellySlime(ModelBase model1, ModelBase model2)
    {
        super(model1, 0.25F);
        this.scaleAmount = model2;
    }

    protected ResourceLocation func_110874_a(EntityJellySlime par1Entity)
    {
        switch (par1Entity.getJellySlimeType())
        {
        case 0:
        default:
            return RenderJellySlime.grapeSlimeTextures;
        case 1:
            return RenderJellySlime.raspberrySlimeTextures;
        case 2:
            return RenderJellySlime.strawberrySlimeTextures;
        case 3:
            return RenderJellySlime.berrySlimeTextures;
        case 4:
            return RenderJellySlime.limeSlimeTextures;
        case 5:
            return RenderJellySlime.orangeSlimeTextures;
        case 6:
            return RenderJellySlime.greenSlimeTextures;
        case 7:
            return RenderJellySlime.lemonSlimeTextures;
        }
    }

    protected int shouldSlimeRenderPass(EntityJellySlime par1EntitySlime, int par2, float par3)
    {
        if (par1EntitySlime.isInvisible())
        {
            return 0;
        }
        else if (par2 == 0)
        {
            this.setRenderPassModel(this.scaleAmount);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }
        else
        {
            if (par2 == 1)
            {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
            return -1;
        }
    }

    protected void scaleSlime(EntityJellySlime par1EntitySlime, float par2)
    {
        float f1 = par1EntitySlime.getSlimeSize();
        float f2 = (par1EntitySlime.prevSquishFactor + (par1EntitySlime.squishFactor - par1EntitySlime.prevSquishFactor) * par2) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scaleSlime((EntityJellySlime)par1EntityLivingBase, par2);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.shouldSlimeRenderPass((EntityJellySlime)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110874_a((EntityJellySlime)par1Entity);
    }
}
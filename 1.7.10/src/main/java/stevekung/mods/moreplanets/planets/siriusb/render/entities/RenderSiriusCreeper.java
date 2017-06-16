/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.render.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;

@SideOnly(Side.CLIENT)
public class RenderSiriusCreeper extends RenderLiving
{
    private static final ResourceLocation creeperTextures = new ResourceLocation("siriusb:textures/model/sirius_creeper.png");

    private final ModelBase creeperModel = new ModelCreeper(2.0F);

    public RenderSiriusCreeper()
    {
        super(new ModelCreeper(), 0.5F);
    }

    protected void updateCreeperScale(EntitySiriusCreeper par1EntityCreeper, float par2)
    {
        float f1 = par1EntityCreeper.getCreeperFlashIntensity(par2);
        final float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        f1 *= f1;
        f1 *= f1;
        final float f3 = (1.0F + f1 * 0.4F) * f2;
        final float f4 = (1.0F + f1 * 0.1F) / f2;
        GL11.glScalef(f3, f4, f3);
    }

    protected int updateCreeperColorMultiplier(EntitySiriusCreeper par1EntityCreeper, float par2, float par3)
    {
        final float f2 = par1EntityCreeper.getCreeperFlashIntensity(par3);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0)
            {
                i = 0;
            }

            if (i > 255)
            {
                i = 255;
            }
            final short short1 = 255;
            final short short2 = 255;
            final short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
    }

    protected int func_77061_b(EntitySiriusCreeper par1EntityCreeper, int par2, float par3)
    {
        return -1;
    }

    protected ResourceLocation getCreeperTextures(EntitySiriusCreeper par1EntityCreeper)
    {
        return RenderSiriusCreeper.creeperTextures;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.updateCreeperScale((EntitySiriusCreeper)par1EntityLivingBase, par2);
    }

    @Override
    protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
    {
        return this.updateCreeperColorMultiplier((EntitySiriusCreeper)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_77061_b((EntitySiriusCreeper)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getCreeperTextures((EntitySiriusCreeper)par1Entity);
    }
}
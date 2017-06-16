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
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;

@SideOnly(Side.CLIENT)
public class RenderGrappy extends RenderLiving
{
    private static final ResourceLocation grappyTextures = new ResourceLocation("fronos:textures/model/grappy/grappy_sheared.png");
    private static final ResourceLocation shearedGrappyTextures = new ResourceLocation("fronos:textures/model/grappy/grappy.png");

    public RenderGrappy(ModelBase par1ModelBase, ModelBase par2ModelBase)
    {
        super(par1ModelBase, 0.6F);
        this.setRenderPassModel(par2ModelBase);
    }

    protected int setWoolColorAndRender(EntityGrappy grappy, int par2, float par3)
    {
        if (par2 == 0 && !grappy.getSheared())
        {
            this.bindTexture(RenderGrappy.grappyTextures);

            if (grappy.hasCustomNameTag() && "steve_".equals(grappy.getCustomNameTag()))
            {
                final int j = grappy.ticksExisted / 5 + grappy.getEntityId();
                final int k = j % EntityGrappy.fleeceColorTable.length;
                final int m = (j + 1) % EntityGrappy.fleeceColorTable.length;
                final float f = (grappy.ticksExisted % 5 + par3) / 5.0F;
                GL11.glColor3f(EntityGrappy.fleeceColorTable[k][0] * (1.0F - f) + EntityGrappy.fleeceColorTable[m][0] * f, EntityGrappy.fleeceColorTable[k][1] * (1.0F - f) + EntityGrappy.fleeceColorTable[m][1] * f, EntityGrappy.fleeceColorTable[k][2] * (1.0F - f) + EntityGrappy.fleeceColorTable[m][2] * f);
            }
            else
            {
                final float f1 = 1.0F;
                final int j = grappy.getFleeceColor();
                GL11.glColor3f(f1 * EntityGrappy.fleeceColorTable[j][0], f1 * EntityGrappy.fleeceColorTable[j][1], f1 * EntityGrappy.fleeceColorTable[j][2]);
            }
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110883_a(EntityGrappy grappy)
    {
        return RenderGrappy.shearedGrappyTextures;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.setWoolColorAndRender((EntityGrappy)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110883_a((EntityGrappy)par1Entity);
    }
}
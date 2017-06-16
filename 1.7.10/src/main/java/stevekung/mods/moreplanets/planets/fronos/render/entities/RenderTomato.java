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
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityTomato;
import stevekung.mods.moreplanets.planets.fronos.models.entities.ModelTomato;

@SideOnly(Side.CLIENT)
public class RenderTomato extends RenderLiving
{
    private static final ResourceLocation tomatoTextures = new ResourceLocation("fronos:textures/model/tomato.png");

    public RenderTomato()
    {
        super(new ModelTomato(), 0.75F);
    }

    protected ResourceLocation berryTexture(EntityTomato par1EntityBerry)
    {
        return RenderTomato.tomatoTextures;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBerry((EntityTomato)par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBerry((EntityTomato)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.berryTexture((EntityTomato)par1Entity);
    }

    public void renderBerry(EntityTomato par1EntityBerry, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityBerry, par2, par4, par6, par8, par9);
    }

    protected void renderMooshroomEquippedItems(EntityTomato par1EntityMooshroom, float par2)
    {
        super.renderEquippedItems(par1EntityMooshroom, par2);

        if (!par1EntityMooshroom.isChild())
        {
            this.bindTexture(TextureMap.locationBlocksTexture);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPushMatrix();
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.0F, -0.15F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            this.field_147909_c.renderBlockAsItem(FronosBlocks.coral, 4, 1.0F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.renderMooshroomEquippedItems((EntityTomato)par1EntityLivingBase, par2);
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
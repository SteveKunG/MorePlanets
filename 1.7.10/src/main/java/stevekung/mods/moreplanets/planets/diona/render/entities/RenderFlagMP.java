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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFlagMP;
import stevekung.mods.moreplanets.planets.diona.entities.models.ModelFlagMP;

@SideOnly(Side.CLIENT)
public class RenderFlagMP extends Render
{
    public static ResourceLocation[] flagTexture;

    private static String[] flag = { "thai", "laos", "singapore", "myanmar", "malaysia", "vietnam", "indonesia", "philippines", "cambodia", "brunei" };

    static
    {
        RenderFlagMP.flagTexture = new ResourceLocation[flag.length];

        for (int i = 0; i < flag.length; i++)
        {
            RenderFlagMP.flagTexture[i] = new ResourceLocation("diona:textures/model/flag/" + flag[i] + ".png");
        }
    }

    protected ModelFlagMP modelFlag;

    public RenderFlagMP()
    {
        this.shadowSize = 1F;
        this.modelFlag = new ModelFlagMP();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        if (((EntityFlagMP)entity).getType() == -1)
        {
            return null;
        }
        return RenderFlagMP.flagTexture[((EntityFlagMP)entity).getType()];
    }

    public void renderFlag(EntityFlagMP entity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        long var10 = entity.getEntityId() * 493286711L;
        var10 = var10 * var10 * 4392167121L + var10 * 98761L;
        final float var12 = (((var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        final float var13 = (((var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        final float var14 = (((var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GL11.glTranslatef(var12, var13, var14);
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glRotatef(180.0F - entity.getFacingAngle(), 0.0F, 1.0F, 0.0F);
        this.bindEntityTexture(entity);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelFlag.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderFlag((EntityFlagMP) par1Entity, par2, par4, par6, par8, par9);
    }
}
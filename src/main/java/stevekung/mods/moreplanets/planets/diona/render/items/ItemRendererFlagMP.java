/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.render.items;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFlagMP;
import stevekung.mods.moreplanets.planets.diona.entities.models.ModelFlagMP;
import stevekung.mods.moreplanets.planets.diona.render.entities.RenderFlagMP;

public class ItemRendererFlagMP implements IItemRenderer
{
    private EntityFlagMP entityFlagDummy = new EntityFlagMP(FMLClientHandler.instance().getClient().theWorld);
    private ModelFlagMP modelFlag = new ModelFlagMP();

    private void renderFlag(ItemRenderType type, ItemStack item)
    {
        GL11.glPushMatrix();
        long var10 = this.entityFlagDummy.getEntityId() * 493286711L;
        var10 = var10 * var10 * 4392167121L + var10 * 98761L;
        final float var12 = (((var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        final float var13 = (((var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        final float var14 = (((var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;

        this.entityFlagDummy.worldObj = FMLClientHandler.instance().getClient().theWorld;
        this.entityFlagDummy.ticksExisted = (int) FMLClientHandler.instance().getWorldClient().getTotalWorldTime();
        this.entityFlagDummy.setType(item.getItemDamage());

        if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glScalef(7F, 7F, 7F);
            GL11.glTranslatef(0.0F, 0.7F, 0.1F);
        }
        if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glRotatef(170F, 0F, 0F, 1F);
            GL11.glRotatef(-10F, 1F, 0F, 0F);
            GL11.glTranslatef(-0.25F, 1.3F, 0.15F);
            GL11.glRotatef(-145F, 0F, 1F, 0F);
        }
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            if (FMLClientHandler.instance().getClient().thePlayer.getItemInUseCount() > 0)
            {
                float var13b;
                float var14b;
                var13b = item.getMaxItemUseDuration() - (FMLClientHandler.instance().getClient().thePlayer.getItemInUseCount() + 1.0F);
                var14b = var13b / 20.0F;
                var14b = (var14b * var14b + var14b * 2.0F) / 3.0F;

                if (var14b > 1.0F)
                {
                    var14b = 1.0F;
                }
                GL11.glRotatef(MathHelper.sin((var13b - 0.1F) * 0.3F) * 0.01F * (var14b - 0.1F) * 60, 1F, 0F, 0F);
                GL11.glRotatef(var14b * 60F, 1F, 0F, 1F);
                GL11.glTranslatef(0F, -(var14b * 0.2F), 0F);
            }
        }

        GL11.glTranslatef(var12, var13 - 0.1F, var14);
        GL11.glScalef(-0.4F, -0.4F, 0.4F);

        if (type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY)
        {
            if (type == ItemRenderType.INVENTORY)
            {
                GL11.glScalef(1.1F, 1.137F, 1.1F);
                GL11.glRotatef(30F, 1F, 0F, 1F);
                GL11.glRotatef(110F, 0F, 1F, 0F);
                GL11.glTranslatef(-0.5F, 0.3F, 0);
            }
            else
            {
                GL11.glTranslatef(0, -0.9F, 0);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
            }
            GL11.glScalef(1.3F, 1.3F, 1.3F);
            GL11.glTranslatef(0, -0.6F, 0);
            GL11.glEnable(GL11.GL_BLEND);
        }
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderFlagMP.flagTexture[item.getItemDamage()]);
        this.modelFlag.render(this.entityFlagDummy, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
        case ENTITY:
            return true;
        case EQUIPPED:
            return true;
        case EQUIPPED_FIRST_PERSON:
            return true;
        case INVENTORY:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
        case EQUIPPED:
            this.renderFlag(type, item);
            break;
        case EQUIPPED_FIRST_PERSON:
            this.renderFlag(type, item);
            break;
        case INVENTORY:
            this.renderFlag(type, item);
            break;
        case ENTITY:
            this.renderFlag(type, item);
            break;
        default:
        }
    }
}
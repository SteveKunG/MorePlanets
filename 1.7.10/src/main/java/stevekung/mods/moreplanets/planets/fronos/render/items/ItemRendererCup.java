/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.render.items;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererCup implements IItemRenderer
{
    private static ResourceLocation emptyCupTextures = new ResourceLocation("fronos:textures/model/cup/ovantine.png");
    private static ResourceLocation ovantineCup = new ResourceLocation("fronos:textures/model/cup/ovantine.png");
    private static ResourceLocation mineralWaterCup = new ResourceLocation("fronos:textures/model/cup/mineral_water.png");
    private static ResourceLocation coconutMilkCup = new ResourceLocation("fronos:textures/model/cup/coconut_milk.png");
    private static ResourceLocation cheeseOfMilkCup = new ResourceLocation("fronos:textures/model/cup/cheese_of_milk.png");
    private static ResourceLocation teaCup = new ResourceLocation("fronos:textures/model/cup/tea.png");
    private static ResourceLocation caramelCup = new ResourceLocation("fronos:textures/model/cup/caramel.png");

    private ModelBase emptyCup;
    private ModelBase fullCup;

    public ItemRendererCup(ModelBase emptyCup, ModelBase fullCup)
    {
        this.emptyCup = emptyCup;
        this.fullCup = fullCup;
    }

    private void renderOvantineCup(ItemRenderType type, ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();

        GL11.glPushMatrix();
        this.transform(type);

        if (meta == 0)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.emptyCupTextures);
            this.emptyCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        else if (meta == 1)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.mineralWaterCup);
            this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        else if (meta == 2)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.ovantineCup);
            this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        else if (meta == 3)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.coconutMilkCup);
            this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        else if (meta == 4)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.cheeseOfMilkCup);
            this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        else if (meta == 5)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.teaCup);
            this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        else if (meta == 6)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ItemRendererCup.caramelCup);
            this.fullCup.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
    }

    public void transform(ItemRenderType type)
    {
        GL11.glScalef(2.0F, 2.0F, 2.0F);

        if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glRotatef(180, 0.0F, 0, 0);
            GL11.glRotatef(1, 0.0F, 1, 1);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(-0.25F, -1.5F, -0.25F);
        }
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glRotatef(30F, 0, 1F, 0);
            GL11.glRotatef(180F, 1.01F, 0, 0);
            GL11.glTranslatef(0.0F, -1.6F, -0.35F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
        }
        if (type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY)
        {
            GL11.glTranslatef(0, 0.0F, 0);

            if (type == ItemRenderType.INVENTORY)
            {
                GL11.glTranslatef(0, 1.25F, 0);
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glRotatef(180.0F, 1.0F, 0, 0);
                GL11.glRotatef(90F, 0F, 1F, 0F);
            }
            else
            {
                GL11.glTranslatef(0, 1.0F, 0);
                GL11.glScalef(0.7F, 0.7F, 0.7F);
                GL11.glRotatef(180.0F, 1.0F, 0, 0);
            }
            GL11.glScalef(1.0F, 1.0F, 1.0F);
        }
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
            this.renderOvantineCup(type, item);
            break;
        case EQUIPPED_FIRST_PERSON:
            this.renderOvantineCup(type, item);
            break;
        case INVENTORY:
            this.renderOvantineCup(type, item);
            break;
        case ENTITY:
            this.renderOvantineCup(type, item);
            break;
        default:
            break;
        }
    }
}
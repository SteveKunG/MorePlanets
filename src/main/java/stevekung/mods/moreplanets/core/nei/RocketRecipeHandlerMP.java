/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.nei;

import java.util.ArrayList;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public abstract class RocketRecipeHandlerMP extends TemplateRecipeHandler
{
    public class CachedRocketRecipeMP extends CachedRecipe
    {
        public ArrayList<PositionedStack> input;
        public PositionedStack output;

        public CachedRocketRecipeMP(ArrayList<PositionedStack> pstack1, PositionedStack pstack2)
        {
            super();
            this.input = pstack1;
            this.output = pstack2;
        }

        public CachedRocketRecipeMP(Map.Entry<ArrayList<PositionedStack>, PositionedStack> recipe)
        {
            this(recipe.getKey(), recipe.getValue());
        }

        @Override
        public ArrayList<PositionedStack> getIngredients()
        {
            return this.input;
        }

        @Override
        public PositionedStack getResult()
        {
            return this.output;
        }
    }

    @Override
    public void drawForeground(int recipe)
    {
    }

    @Override
    public int recipiesPerPage()
    {
        return 1;
    }

    @Override
    public String getRecipeName()
    {
        return "NASA Workbench";
    }

    @Override
    public void drawBackground(int i)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(this.getRocketGuiTexture());
        GuiDraw.drawTexturedModalRect(0, -8, 3, 4, 168, 140);
    }

    @Override
    public void drawExtras(int i)
    {
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public void loadTransferRects()
    {
    }

    @Override
    public String getGuiTexture()
    {
        return this.getRocketGuiTexture();
    }

    public abstract String getRocketGuiTexture();
}
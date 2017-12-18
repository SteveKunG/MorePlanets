/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.nei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.recipe.CandyExtractorRecipes;

public class FuelRecipeHandlerMP extends CandyExtractorRecipeHandler
{
    public class CachedFuelRecipe extends CachedRecipe
    {
        public FuelPair fuel;

        public CachedFuelRecipe(FuelPair fuel)
        {
            this.fuel = fuel;
        }

        @Override
        public PositionedStack getIngredient()
        {
            return FuelRecipeHandlerMP.this.mfurnace.get(FuelRecipeHandlerMP.this.cycleticks / 48 % FuelRecipeHandlerMP.this.mfurnace.size()).ingred;
        }

        @Override
        public PositionedStack getResult()
        {
            return FuelRecipeHandlerMP.this.mfurnace.get(FuelRecipeHandlerMP.this.cycleticks / 48 % FuelRecipeHandlerMP.this.mfurnace.size()).result;
        }

        @Override
        public PositionedStack getOtherStack()
        {
            return this.fuel.stack;
        }
    }

    private final ArrayList<SmeltingPair> mfurnace = new ArrayList<CandyExtractorRecipeHandler.SmeltingPair>();

    public FuelRecipeHandlerMP()
    {
        super();
        this.loadAllSmelting();
    }

    @Override
    public String getRecipeName()
    {
        return "Fuel";
    }

    private void loadAllSmelting()
    {
        final Map<ItemStack, ItemStack> recipes = CandyExtractorRecipes.extracting().getExtractingList();

        for (final Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
        {
            this.mfurnace.add(new SmeltingPair(recipe.getKey(), recipe.getValue()));
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("fuel") && this.getClass() == FuelRecipeHandlerMP.class)
        {
            for (final FuelPair fuel : CandyExtractorRecipeHandler.afuels)
            {
                this.arecipes.add(new CachedFuelRecipe(fuel));
            }
        }

    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        for (final FuelPair fuel : CandyExtractorRecipeHandler.afuels)
        {
            if (fuel.stack.contains(ingredient))
            {
                this.arecipes.add(new CachedFuelRecipe(fuel));
            }
        }
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "fuel";
    }

    @Override
    public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe)
    {
        final CachedFuelRecipe crecipe = (CachedFuelRecipe) this.arecipes.get(recipe);
        final FuelPair fuel = crecipe.fuel;
        float burnTime = fuel.burnTime / 200F;

        if (gui.isMouseOver(fuel.stack, recipe) && burnTime < 1)
        {
            burnTime = 1F / burnTime;
            String s_time = Float.toString(burnTime);
            if (burnTime == Math.round(burnTime))
            {
                s_time = Integer.toString((int) burnTime);
            }
            currenttip.add(NEIClientUtils.translate("recipe.fuel.required", s_time));
        }
        else if ((gui.isMouseOver(crecipe.getResult(), recipe) || gui.isMouseOver(crecipe.getIngredient(), recipe)) && burnTime > 1)
        {
            String s_time = Float.toString(burnTime);

            if (burnTime == Math.round(burnTime))
            {
                s_time = Integer.toString((int) burnTime);
            }
            currenttip.add(NEIClientUtils.translate("recipe.fuel." + (gui.isMouseOver(crecipe.getResult(), recipe) ? "produced" : "processed"), s_time));
        }
        return currenttip;
    }
}
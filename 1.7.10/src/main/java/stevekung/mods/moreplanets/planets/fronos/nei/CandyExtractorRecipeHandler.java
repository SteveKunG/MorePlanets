/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.nei;

import java.util.*;
import java.util.Map.Entry;

import codechicken.nei.ItemList;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import stevekung.mods.moreplanets.core.recipe.CandyExtractorRecipes;
import stevekung.mods.moreplanets.planets.fronos.gui.GuiCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

public class CandyExtractorRecipeHandler extends TemplateRecipeHandler
{
    public static ArrayList<FuelPair> afuels;
    public static HashSet<Block> efuels;

    public class SmeltingPair extends CachedRecipe
    {
        PositionedStack ingred;
        PositionedStack result;

        public SmeltingPair(ItemStack ingred, ItemStack result)
        {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 6);
            this.result = new PositionedStack(result, 111, 24);
        }

        @Override
        public List<PositionedStack> getIngredients()
        {
            return this.getCycledIngredients(CandyExtractorRecipeHandler.this.cycleticks / 48, Arrays.asList(this.ingred));
        }

        @Override
        public PositionedStack getResult()
        {
            return this.result;
        }

        @Override
        public PositionedStack getOtherStack()
        {
            return CandyExtractorRecipeHandler.afuels.get(CandyExtractorRecipeHandler.this.cycleticks / 48 % CandyExtractorRecipeHandler.afuels.size()).stack;
        }
    }

    public static class FuelPair
    {
        public PositionedStack stack;
        public int burnTime;

        public FuelPair(ItemStack ingred, int burnTime)
        {
            this.stack = new PositionedStack(ingred, 51, 42, false);
            this.burnTime = burnTime;
        }
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiCandyExtractor.class;
    }

    @Override
    public String getRecipeName()
    {
        return EnumChatFormatting.DARK_BLUE + "Candy Extractor";
    }

    @Override
    public TemplateRecipeHandler newInstance()
    {
        if (CandyExtractorRecipeHandler.afuels == null)
        {
            CandyExtractorRecipeHandler.findFuels();
        }
        return super.newInstance();
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("smelting") && this.getClass() == CandyExtractorRecipeHandler.class)
        {
            Map<ItemStack, ItemStack> recipes = CandyExtractorRecipes.extracting().getExtractingList();

            for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            {
                this.arecipes.add(new SmeltingPair(recipe.getKey(), recipe.getValue()));
            }
        }
        super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        Map<ItemStack, ItemStack> recipes = CandyExtractorRecipes.extracting().getExtractingList();

        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
        {
            if (NEIServerUtils.areStacksSameType(recipe.getValue(), result))
            {
                this.arecipes.add(new SmeltingPair(recipe.getKey(), recipe.getValue()));
            }
        }
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients)
    {
        if (inputId.equals("fuel") && this.getClass() == CandyExtractorRecipeHandler.class)
        {
            this.loadCraftingRecipes("smelting");
        }
        super.loadUsageRecipes(inputId, ingredients);
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        Map<ItemStack, ItemStack> recipes = CandyExtractorRecipes.extracting().getExtractingList();

        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient))
            {
                SmeltingPair arecipe = new SmeltingPair(recipe.getKey(), recipe.getValue());
                arecipe.setIngredientPermutation(Arrays.asList(arecipe.ingred), ingredient);
                this.arecipes.add(arecipe);
            }
        }
    }

    @Override
    public String getGuiTexture()
    {
        return "fronos:textures/gui/candy_extractor_nei.png";
    }

    @Override
    public void drawExtras(int recipe)
    {
        this.drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        this.drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

    private static void findFuels()
    {
        CandyExtractorRecipeHandler.afuels = new ArrayList<FuelPair>();

        for (ItemStack item : ItemList.items)
        {
            int burnTime = TileEntityCandyExtractor.getItemExtractTime(item);

            if (burnTime > 0)
            {
                CandyExtractorRecipeHandler.afuels.add(new FuelPair(item.copy(), burnTime));
            }
        }
    }
}
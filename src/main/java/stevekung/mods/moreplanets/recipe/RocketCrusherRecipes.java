package stevekung.mods.moreplanets.recipe;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class RocketCrusherRecipes
{
    private static List<IRecipe> recipes = Lists.newArrayList();

    public static ShapedRecipes addRecipe(ItemStack output, Object... inputList)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (inputList[i] instanceof String[])
        {
            String[] astring = (String[]) inputList[i++];

            for (String s1 : astring)
            {
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (inputList[i] instanceof String)
            {
                String s2 = (String) inputList[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap<Character, ItemStack> hashmap;

        for (hashmap = Maps.newHashMap(); i < inputList.length; i += 2)
        {
            Character character = (Character) inputList[i];
            ItemStack itemstack1 = null;

            if (inputList[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item) inputList[i + 1]);
            }
            else if (inputList[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block) inputList[i + 1], 1, 32767);
            }
            else if (inputList[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack) inputList[i + 1];
            }
            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = hashmap.get(Character.valueOf(c0)).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }
        ShapedRecipes recipes = new ShapedRecipes(j, k, aitemstack, output);
        RocketCrusherRecipes.recipes.add(recipes);
        return recipes;
    }

    public static ItemStack findMatchingRecipe(IInventory inventory)
    {
        List<IRecipe> theRecipes = RocketCrusherRecipes.getRecipeList();

        for (int i = 0; i < theRecipes.size(); ++i)
        {
            IRecipe IRecipe = theRecipes.get(i);

            if (IRecipe instanceof ShapedRecipes && RocketCrusherRecipes.matches((ShapedRecipes) IRecipe, inventory))
            {
                return IRecipe.getRecipeOutput();
            }
        }
        return null;
    }

    private static boolean matches(ShapedRecipes recipe, IInventory inventory)
    {
        for (int i = 0; i <= 3 - recipe.recipeWidth; ++i)
        {
            for (int j = 0; j <= 3 - recipe.recipeHeight; ++j)
            {
                if (RocketCrusherRecipes.checkMatch(recipe, inventory, i, j, true))
                {
                    return true;
                }
                if (RocketCrusherRecipes.checkMatch(recipe, inventory, i, j, false))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkMatch(ShapedRecipes recipe, IInventory inventory, int par2, int par3, boolean par4)
    {
        for (int k = 0; k < 3; ++k)
        {
            for (int l = 0; l < 3; ++l)
            {
                int i1 = k - par2;
                int j1 = l - par3;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < recipe.recipeWidth && j1 < recipe.recipeHeight)
                {
                    if (par4)
                    {
                        itemstack = recipe.recipeItems[recipe.recipeWidth - i1 - 1 + j1 * recipe.recipeWidth];
                    }
                    else
                    {
                        itemstack = recipe.recipeItems[i1 + j1 * recipe.recipeWidth];
                    }
                }

                ItemStack itemstack1 = null;

                if (k >= 0 && l < 3)
                {
                    int k2 = k + l * 3;
                    itemstack1 = inventory.getStackInSlot(k2);
                }
                if (itemstack1 != null || itemstack != null)
                {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                    {
                        return false;
                    }
                    if (itemstack.getItem() != itemstack1.getItem())
                    {
                        return false;
                    }
                    if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<IRecipe> getRecipeList()
    {
        return RocketCrusherRecipes.recipes;
    }
}
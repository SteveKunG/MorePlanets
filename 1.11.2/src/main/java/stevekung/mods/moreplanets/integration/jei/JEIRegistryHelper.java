package stevekung.mods.moreplanets.integration.jei;

import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeHandler;
import net.minecraft.item.ItemStack;

public class JEIRegistryHelper
{
    public static IModRegistry registry;
    public static IGuiHelper guiHelper;

    public static void registerRecipe(List recipes)
    {
        JEIRegistryHelper.registry.addRecipes(recipes);
    }

    public static void registerRecipeCategories(IRecipeCategory recipe)
    {
        JEIRegistryHelper.registry.addRecipeCategories(recipe);
    }

    public static void registerRecipeHandlers(IRecipeHandler handler)
    {
        JEIRegistryHelper.registry.addRecipeHandlers(handler);
    }

    public static void registerStackDisplayRecipe(ItemStack itemStack, String recipe)
    {
        JEIRegistryHelper.registry.addRecipeCategoryCraftingItem(itemStack, recipe);
    }
}
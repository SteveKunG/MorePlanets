package stevekung.mods.moreplanets.integration.jei;

import java.util.Collection;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;

public class JEIRegistryHelper
{
    public static IModRegistry registry;
    public static IGuiHelper guiHelper;
    public static IRecipeCategoryRegistration categoryRegistration;

    public static void registerRecipe(Collection recipes, String recipe)
    {
        JEIRegistryHelper.registry.addRecipes(recipes, recipe);
    }

    public static void registerRecipeCategories(IRecipeCategory recipe)
    {
        JEIRegistryHelper.categoryRegistration.addRecipeCategories(recipe);
    }

    public static <T> void registerRecipeHandlers(Class<T> recipeClass, IRecipeWrapperFactory<T> recipeWrapper, String recipe)
    {
        JEIRegistryHelper.registry.handleRecipes(recipeClass, recipeWrapper, recipe);
    }

    public static void registerStackDisplayRecipe(ItemStack itemStack, String... recipe)
    {
        JEIRegistryHelper.registry.addRecipeCatalyst(itemStack, recipe);
    }
}
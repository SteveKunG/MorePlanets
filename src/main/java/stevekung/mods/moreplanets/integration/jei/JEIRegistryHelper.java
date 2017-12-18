package stevekung.mods.moreplanets.integration.jei;

import java.util.Collection;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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

    public static void addInfo(Block block, String... desc)
    {
        JEIRegistryHelper.addInfo(new ItemStack(block), desc);
    }

    public static void addInfo(Item item, String... desc)
    {
        JEIRegistryHelper.addInfo(new ItemStack(item), desc);
    }

    public static void addInfo(ItemStack itemStack, String... desc)
    {
        JEIRegistryHelper.registry.addIngredientInfo(itemStack, ItemStack.class, desc);
    }
}
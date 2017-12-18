package stevekung.mods.moreplanets.integration.jei;

import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
        JEIRegistryHelper.registry.addDescription(itemStack, desc);
    }
}
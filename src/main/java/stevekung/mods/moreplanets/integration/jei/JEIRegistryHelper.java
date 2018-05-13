package stevekung.mods.moreplanets.integration.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JEIRegistryHelper
{
    public static IModRegistry registry;
    public static IGuiHelper guiHelper;
    public static IRecipeCategoryRegistration categoryRegistration;
    public static IVanillaRecipeFactory vanillaRecipe;

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

    public static void registerAnvilRecipe(Block repairItem, List<ItemStack> toolList)
    {
        JEIRegistryHelper.registerAnvilRecipe(Item.getItemFromBlock(repairItem), toolList);
    }

    public static void registerAnvilRecipe(Item repairItem, List<ItemStack> toolList)
    {
        for (ItemStack ingredient : toolList)
        {
            ItemStack damaged1 = ingredient.copy();
            damaged1.setItemDamage(damaged1.getMaxDamage());
            ItemStack damaged2 = ingredient.copy();
            damaged2.setItemDamage(damaged2.getMaxDamage() * 3 / 4);
            ItemStack damaged3 = ingredient.copy();
            damaged3.setItemDamage(damaged3.getMaxDamage() * 2 / 4);
            List<IRecipeWrapper> recipes = new ArrayList<>();
            recipes.add(JEIRegistryHelper.vanillaRecipe.createAnvilRecipe(damaged1, Collections.singletonList(new ItemStack(repairItem)), Collections.singletonList(damaged2)));
            recipes.add(JEIRegistryHelper.vanillaRecipe.createAnvilRecipe(damaged2, Collections.singletonList(damaged2), Collections.singletonList(damaged3)));
            JEIRegistryHelper.registerRecipe(recipes, VanillaRecipeCategoryUid.ANVIL);
        }
    }

    @Deprecated
    public static void registerAnvilRecipe(ItemStack repairItem, List<ItemStack> toolList)
    {
        for (ItemStack ingredient : toolList)
        {
            ItemStack damaged1 = ingredient.copy();
            damaged1.setItemDamage(damaged1.getMaxDamage());
            ItemStack damaged2 = ingredient.copy();
            damaged2.setItemDamage(damaged2.getMaxDamage() * 3 / 4);
            ItemStack damaged3 = ingredient.copy();
            damaged3.setItemDamage(damaged3.getMaxDamage() * 2 / 4);
            List<IRecipeWrapper> recipes = new ArrayList<>();
            recipes.add(JEIRegistryHelper.vanillaRecipe.createAnvilRecipe(damaged1, Collections.singletonList(repairItem), Collections.singletonList(damaged2)));
            recipes.add(JEIRegistryHelper.vanillaRecipe.createAnvilRecipe(damaged2, Collections.singletonList(damaged2), Collections.singletonList(damaged3)));
            JEIRegistryHelper.registerRecipe(recipes, VanillaRecipeCategoryUid.ANVIL);
        }
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
package stevekung.mods.moreplanets.util.helper;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import stevekung.mods.moreplanets.recipe.RocketCrusherRecipes;

public class RecipeHelper
{
    public static void addRecipe(ItemStack output, Object... obj)
    {
        GameRegistry.addRecipe(output, obj);
    }

    @SuppressWarnings("unchecked")
    public static void addOreRecipe(ItemStack output, Object... obj)
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, obj));
    }

    public static void addShapelessRecipe(ItemStack output, Object... obj)
    {
        GameRegistry.addShapelessRecipe(output, obj);
    }

    public static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addCompressorRecipe(ItemStack output, Object... obj)
    {
        CompressorRecipes.addRecipe(output, obj);
    }

    public static void addShapelessCompressorRecipe(ItemStack output, Object... obj)
    {
        CompressorRecipes.addShapelessRecipe(output, obj);
    }

    public static void addRocketCrusherRecipe(ItemStack output, Object... obj)
    {
        RocketCrusherRecipes.addRecipe(output, obj);
    }
}
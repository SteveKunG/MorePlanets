package stevekung.mods.moreplanets.integration.jei;

import java.util.Map;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MPJEIRecipes
{
    public static String ROCKET_CRUSHER = "moreplanets.rocket_crusher_recipes";
    public static String DARK_ENERGY_TRANSFORM = "moreplanets.dark_energy_transform_recipes";
    public static String BLACK_HOLE_STORAGE = "moreplanets.black_hole_storage_recipes";
    public static String TIER_4_ROCKET = "moreplanets.tier_4_rocket_recipes";
    public static String TIER_5_ROCKET = "moreplanets.tier_5_rocket_recipes";
    public static String TIER_6_ROCKET = "moreplanets.tier_6_rocket_recipes";

    public static int countChests(INasaWorkbenchRecipe recipe)
    {
        int count = 0;
        ItemStack chest = new ItemStack(Blocks.CHEST);

        for (Map.Entry<Integer, ItemStack> entry : recipe.getRecipeInput().entrySet())
        {
            if (ItemStack.areItemsEqual(chest, entry.getValue()))
            {
                count++;
            }
        }
        return count;
    }
}
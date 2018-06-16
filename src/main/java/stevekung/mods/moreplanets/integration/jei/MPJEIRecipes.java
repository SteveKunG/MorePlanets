package stevekung.mods.moreplanets.integration.jei;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MPJEIRecipes
{
    public static final String DARK_ENERGY_TRANSFORM = "moreplanets.dark_energy_transform_recipes";
    public static final String BLACK_HOLE_STORAGE = "moreplanets.black_hole_storage_recipes";

    public static final Map<ItemStack, Item> ILLENIUM = new HashMap<>();
    public static final Map<ItemStack, Item> DIREMSIUM = new HashMap<>();
    public static final Map<ItemStack, Item> CHEESE_SPORE = new HashMap<>();
    public static final Map<ItemStack, Item> MULTALIC_CRYSTAL = new HashMap<>();
    public static final Map<ItemStack, Item> INFECTED_WOOD = new HashMap<>();
    public static final Map<ItemStack, Item> NIBIRU_STONE = new HashMap<>();

    public static void collectAnvilList(String name, Item toAdd, Item toRepair)
    {
        if (name.contains("illenium"))
        {
            MPJEIRecipes.ILLENIUM.put(new ItemStack(toAdd), toRepair);
        }
        else if (name.contains("diremsium"))
        {
            MPJEIRecipes.DIREMSIUM.put(new ItemStack(toAdd), toRepair);
        }
        else if (name.contains("cheese_spore"))
        {
            MPJEIRecipes.CHEESE_SPORE.put(new ItemStack(toAdd), toRepair);
        }
        else if (name.contains("multalic_crystal"))
        {
            MPJEIRecipes.MULTALIC_CRYSTAL.put(new ItemStack(toAdd), toRepair);
        }
        else if (name.contains("infected_wood"))
        {
            MPJEIRecipes.INFECTED_WOOD.put(new ItemStack(toAdd), toRepair);
        }
        else if (name.contains("nibiru_stone"))
        {
            MPJEIRecipes.NIBIRU_STONE.put(new ItemStack(toAdd), toRepair);
        }
    }
}
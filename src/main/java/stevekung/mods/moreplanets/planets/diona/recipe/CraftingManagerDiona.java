package stevekung.mods.moreplanets.planets.diona.recipe;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerDiona
{
    public static void init()
    {
        CraftingManagerDiona.addBlockRecipe();
        CraftingManagerDiona.addItemRecipe();
        CraftingManagerDiona.addBlockSmelting();
        CraftingManagerDiona.addItemSmelting();
    }

    private static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ILLENIUM_BLOCK), new Object[] { "III", "III", "III", 'I', MPItems.ILLENIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SETRORIUM_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.SETRORIUM_SHARD });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GLOWING_IRON_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.GLOWING_IRON_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT), new Object[] { "BSB", "BSB", "BSB", 'S', MPItems.INFECTED_CRYSTALLIZED_SHARD, 'B', MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_GENERATOR), new Object[] { "HPH", "ACA", "WFW", 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'P', MPItems.DARK_ENERGY_PEARL, 'A', MPItems.ALIEN_MINER_PART, 'C', MPItems.MULTALIC_CRYSTAL_PIECES, 'F', new ItemStack(GCItems.basicItem, 1, 14), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.DIONA_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.DIONA_DUNGEON_BRICK });
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CRYSTALLIZED_TORCH, 4), new Object[] { "I", "S", 'I', MPItems.INFECTED_CRYSTALLIZED_SHARD, 'S', "stickWood" });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.ILLENIUM_INGOT, 9), MPBlocks.ILLENIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.SETRORIUM_SHARD, 9), MPBlocks.SETRORIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.GLOWING_IRON_INGOT, 9), MPBlocks.GLOWING_IRON_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL, 9), MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);

        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_CRYSTALLIZED_BOMB, 4), new Object[] { "III", "IGI", "III", 'I', MPItems.INFECTED_CRYSTALLIZED_SHARD, 'G', "gunpowder" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_CRYSTALLIZED_ARROW, 6), new Object[] { "X", "S", "Y", 'Y', "feather", 'X', MPItems.INFECTED_CRYSTALLIZED_SHARD, 'S', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.BREATHABLE_ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', GCItems.oxMask, 'X', MPItems.COMPRESSED_ILLENIUM });

        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_SWORD), new Object[] { "X", "X", "Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE), new ItemStack(MPBlocks.DIONA_ROCK), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.SETRORIUM_ORE), new ItemStack(MPItems.SETRORIUM_SHARD), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.ILLENIUM_ORE), new ItemStack(MPItems.ILLENIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIONA_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
    }
}
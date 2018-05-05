package stevekung.mods.moreplanets.planets.diona.recipe;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
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
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.ILLENIUM_BLOCK), new Object[] { "III", "III", "III", 'I', DionaItems.ILLENIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.SETRORIUM_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', DionaItems.SETRORIUM_SHARD });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.GLOWING_IRON_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', DionaItems.GLOWING_IRON_INGOT });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT), new Object[] { "BSB", "BSB", "BSB", 'S', DionaItems.INFECTED_CRYSTALLIZED_SHARD, 'B', DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_GENERATOR), new Object[] { "HPH", "ACA", "WFW", 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'P', DionaItems.DARK_ENERGY_PEARL, 'A', DionaItems.ALIEN_MINER_PART, 'C', NibiruItems.MULTALIC_CRYSTAL_PIECES, 'F', new ItemStack(GCItems.basicItem, 1, 14), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DIONA_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', DionaBlocks.DIONA_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', DionaBlocks.DIONA_DUNGEON_BRICK });
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.INFECTED_CRYSTALLIZED_TORCH, 4), new Object[] { "I", "S", 'I', DionaItems.INFECTED_CRYSTALLIZED_SHARD, 'S', "stickWood" });
        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.ILLENIUM_INGOT, 9), DionaBlocks.ILLENIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.SETRORIUM_SHARD, 9), DionaBlocks.SETRORIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.GLOWING_IRON_INGOT, 9), DionaBlocks.GLOWING_IRON_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL, 9), DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);

        RecipeHelper.addRecipe(new ItemStack(DionaItems.INFECTED_CRYSTALLIZED_BOMB, 4), new Object[] { "III", "IGI", "III", 'I', DionaItems.INFECTED_CRYSTALLIZED_SHARD, 'G', "gunpowder" });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.INFECTED_CRYSTALLIZED_ARROW, 6), new Object[] { "X", "S", "Y", 'Y', "feather", 'X', DionaItems.INFECTED_CRYSTALLIZED_SHARD, 'S', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', DionaItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', DionaItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', DionaItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', DionaItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.BREATHABLE_ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', GCItems.oxMask, 'X', DionaItems.COMPRESSED_ILLENIUM });

        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', DionaItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', DionaItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', DionaItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_SWORD), new Object[] { "X", "X", "Y", 'X', DionaItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', DionaItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_COBBLESTONE), new ItemStack(DionaBlocks.DIONA_ROCK), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.SETRORIUM_ORE), new ItemStack(DionaItems.SETRORIUM_SHARD), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.ILLENIUM_ORE), new ItemStack(DionaItems.ILLENIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
    }
}
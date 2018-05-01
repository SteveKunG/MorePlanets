package stevekung.mods.moreplanets.utils;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SchematicsRegistry
{
    public static void registerSchematicRecipe(ISchematicPage page)
    {
        SchematicRegistry.registerSchematicRecipe(page);
    }

    public static void registerSchematicDungeonLoot(int tier, ItemStack loot)
    {
        GalacticraftRegistry.addDungeonLoot(tier, loot);
    }

    public static int registerSchematicItem(ItemStack itemStack)
    {
        return SchematicRegistry.registerSchematicItem(itemStack);
    }

    public static void registerSchematicTexture(String texture)
    {
        SchematicRegistry.registerTexture(new ResourceLocation("moreplanets:textures/items/" + texture + ".png"));
    }
}
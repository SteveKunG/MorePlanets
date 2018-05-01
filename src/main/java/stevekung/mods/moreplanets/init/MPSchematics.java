package stevekung.mods.moreplanets.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.items.ItemBlackHoleStorageSchematic;
import stevekung.mods.moreplanets.items.ItemIonCannonSchematic;
import stevekung.mods.moreplanets.schematic.BlackHoleStorageSchematic;
import stevekung.mods.moreplanets.utils.SchematicsRegistry;

public class MPSchematics
{
    public static void init()
    {
        SchematicsRegistry.registerSchematicRecipe(new BlackHoleStorageSchematic());
        ItemBlackHoleStorageSchematic.SCHEMATIC_INDEX = SchematicsRegistry.registerSchematicItem(new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC));
        SchematicsRegistry.registerSchematicItem(new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC));
        ItemIonCannonSchematic.SCHEMATIC_INDEX = SchematicsRegistry.registerSchematicItem(new ItemStack(MPItems.ION_CANNON_SCHEMATIC));
        SchematicsRegistry.registerSchematicItem(new ItemStack(MPItems.ION_CANNON_SCHEMATIC));
    }

    @SideOnly(Side.CLIENT)
    public static void registerSchematicTexture()
    {
        SchematicsRegistry.registerSchematicTexture("ion_cannon_schematic");
        SchematicsRegistry.registerSchematicTexture("black_hole_storage_schematic");
    }
}
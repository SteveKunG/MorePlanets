package stevekung.mods.moreplanets.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.items.ItemBlackHoleStorageSchematic;
import stevekung.mods.moreplanets.items.ItemIonCannonSchematic;
import stevekung.mods.moreplanets.schematic.BlackHoleStorageSchematic;
import stevekung.mods.moreplanets.utils.helper.RocketRegisterHelper;

public class MPSchematics
{
    public static void init()
    {
        RocketRegisterHelper.registerSchematicRecipe(new BlackHoleStorageSchematic());
        ItemBlackHoleStorageSchematic.SCHEMATIC_INDEX = RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC));
        RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC));
        ItemIonCannonSchematic.SCHEMATIC_INDEX = RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.ION_CANNON_SCHEMATIC));
        RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.ION_CANNON_SCHEMATIC));
    }

    @SideOnly(Side.CLIENT)
    public static void registerSchematicTexture()
    {
        RocketRegisterHelper.registerSchematicTexture("ion_cannon_schematic");
        RocketRegisterHelper.registerSchematicTexture("black_hole_storage_schematic");
    }
}
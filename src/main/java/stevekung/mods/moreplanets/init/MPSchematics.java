package stevekung.mods.moreplanets.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.items.ItemSpecialSchematic;
import stevekung.mods.moreplanets.schematic.BlackHoleStorageSchematic;
import stevekung.mods.moreplanets.util.helper.RocketRegisterHelper;

public class MPSchematics
{
    public static void init()
    {
        RocketRegisterHelper.registerSchematicRecipe(new BlackHoleStorageSchematic());
        ItemSpecialSchematic.SCHEMATIC_INDEX = RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.SPECIAL_SCHEMATIC, 1, 0));
        RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.SPECIAL_SCHEMATIC, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public static void registerSchematicTexture()
    {
        RocketRegisterHelper.registerSchematicTexture("ion_cannon_schematic");
        RocketRegisterHelper.registerSchematicTexture("black_hole_storage_schematic");
    }
}
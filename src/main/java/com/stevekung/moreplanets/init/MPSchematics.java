package com.stevekung.moreplanets.init;

import com.stevekung.moreplanets.items.ItemBlackHoleStorageSchematic;
import com.stevekung.moreplanets.items.ItemIonCannonSchematic;
import com.stevekung.moreplanets.schematic.BlackHoleStorageSchematic;
import com.stevekung.moreplanets.utils.SchematicsRegistry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MPSchematics
{
    public static void init()
    {
        SchematicsRegistry.registerSchematicRecipe(new BlackHoleStorageSchematic());
        ItemIonCannonSchematic.SCHEMATIC_INDEX = SchematicsRegistry.registerSchematicItem(new ItemStack(MPItems.ION_CANNON_SCHEMATIC));
        ItemBlackHoleStorageSchematic.SCHEMATIC_INDEX = SchematicsRegistry.registerSchematicItem(new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC));
    }

    @SideOnly(Side.CLIENT)
    public static void registerSchematicTexture()
    {
        SchematicsRegistry.registerSchematicTexture("ion_cannon_schematic");
        SchematicsRegistry.registerSchematicTexture("black_hole_storage_schematic");
    }
}
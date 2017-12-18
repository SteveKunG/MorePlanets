package stevekung.mods.moreplanets.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.items.ItemSpecialSchematic;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.chalos.items.ItemTier6RocketSchematic;
import stevekung.mods.moreplanets.module.planets.chalos.schematic.Tier5RocketSchematic;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.items.ItemTier5RocketSchematic;
import stevekung.mods.moreplanets.module.planets.diona.schematic.Tier4RocketSchematic;
import stevekung.mods.moreplanets.module.planets.nibiru.schematic.Tier6RocketSchematic;
import stevekung.mods.moreplanets.schematic.BlackHoleStorageSchematic;
import stevekung.mods.moreplanets.util.helper.RocketRegisterHelper;

public class MPSchematics
{
    public static void init()
    {
        RocketRegisterHelper.registerSchematicRecipe(new BlackHoleStorageSchematic());

        if (ConfigManagerMP.enableTier4RocketSchematic)
        {
            RocketRegisterHelper.registerSchematicRecipe(new Tier4RocketSchematic());
            RocketRegisterHelper.registerSchematicDungeonLoot(3, new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 0));
        }
        if (ConfigManagerMP.enableTier5RocketSchematic)
        {
            RocketRegisterHelper.registerSchematicRecipe(new Tier5RocketSchematic());
            RocketRegisterHelper.registerSchematicDungeonLoot(4, new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 1));
        }
        if (ConfigManagerMP.enableTier6RocketSchematic)
        {
            RocketRegisterHelper.registerSchematicRecipe(new Tier6RocketSchematic());
            RocketRegisterHelper.registerSchematicDungeonLoot(5, new ItemStack(ChalosItems.TIER_6_ROCKET_SCHEMATIC, 1, 0));
        }

        ItemTier5RocketSchematic.SCHEMATIC_INDEX = RocketRegisterHelper.registerSchematicItem(new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 0));
        RocketRegisterHelper.registerSchematicItem(new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 1));
        ItemTier6RocketSchematic.SCHEMATIC_INDEX = RocketRegisterHelper.registerSchematicItem(new ItemStack(ChalosItems.TIER_6_ROCKET_SCHEMATIC, 1, 0));
        ItemSpecialSchematic.SCHEMATIC_INDEX = RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.SPECIAL_SCHEMATIC, 1, 0));
        RocketRegisterHelper.registerSchematicItem(new ItemStack(MPItems.SPECIAL_SCHEMATIC, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public static void registerSchematicTexture()
    {
        RocketRegisterHelper.registerSchematicTexture("tier_4_rocket_schematic");
        RocketRegisterHelper.registerSchematicTexture("tier_5_rocket_schematic");
        RocketRegisterHelper.registerSchematicTexture("tier_6_rocket_schematic");
        RocketRegisterHelper.registerSchematicTexture("ion_cannon_schematic");
        RocketRegisterHelper.registerSchematicTexture("black_hole_storage_schematic");
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.recipe;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class CompressorRecipesMP
{
    public static void registerCompressorRecipes()
    {
        CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.diona_item, 1, 2), new ItemStack(DionaItems.diona_item, 1, 0), new ItemStack(DionaItems.diona_item, 1, 0));
        CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.diona_item, 1, 3), new ItemStack(DionaItems.diona_item, 1, 1), new ItemStack(DionaItems.diona_item, 1, 1));
        CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.tier4_rocket_module, 2, 1), new ItemStack(AsteroidsItems.basicItem, 1, 0), new ItemStack(AsteroidsItems.basicItem, 1, 6));
        CompressorRecipes.addShapelessRecipe(new ItemStack(DionaItems.diona_item, 2, 4), new ItemStack(DionaItems.tier4_rocket_module, 1, 1), new ItemStack(DionaItems.diona_item, 1, 3), new ItemStack(DionaItems.diona_item, 1, 2));

        CompressorRecipes.addShapelessRecipe(new ItemStack(PolongniusItems.polongnius_item, 1, 6), new ItemStack(PolongniusItems.polongnius_item, 1, 4), new ItemStack(PolongniusItems.polongnius_item, 1, 4));
        CompressorRecipes.addShapelessRecipe(new ItemStack(PolongniusItems.polongnius_item, 1, 7), new ItemStack(PolongniusItems.polongnius_item, 1, 5), new ItemStack(PolongniusItems.polongnius_item, 1, 5));
        CompressorRecipes.addShapelessRecipe(new ItemStack(PolongniusItems.tier6_rocket_module, 2, 2), new ItemStack(PolongniusItems.polongnius_item, 1, 7), new ItemStack(PolongniusItems.polongnius_item, 1, 6), new ItemStack(DionaItems.diona_item, 1, 4));

        CompressorRecipes.addShapelessRecipe(new ItemStack(NibiruItems.nibiru_item, 1, 2), new ItemStack(NibiruItems.nibiru_item, 1, 0), new ItemStack(NibiruItems.nibiru_item, 1, 0));
        CompressorRecipes.addShapelessRecipe(new ItemStack(NibiruItems.nibiru_item, 1, 3), new ItemStack(NibiruItems.nibiru_item, 1, 1), new ItemStack(NibiruItems.nibiru_item, 1, 1));
        CompressorRecipes.addShapelessRecipe(new ItemStack(NibiruItems.tier7_rocket_module, 2, 2), new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2), new ItemStack(NibiruItems.nibiru_item, 1, 3), new ItemStack(NibiruItems.nibiru_item, 1, 2));

        CompressorRecipes.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 1, 5), new ItemStack(KoentusItems.koentus_item, 1, 0), new ItemStack(KoentusItems.koentus_item, 1, 0));
        CompressorRecipes.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 1, 6), new ItemStack(KoentusItems.koentus_item, 1, 4), new ItemStack(KoentusItems.koentus_item, 1, 4));

        CompressorRecipes.addShapelessRecipe(new ItemStack(FronosItems.fronos_item, 1, 4), new ItemStack(FronosItems.fronos_item, 1, 2), new ItemStack(FronosItems.fronos_item, 1, 2));
        CompressorRecipes.addShapelessRecipe(new ItemStack(FronosItems.fronos_item, 1, 5), "ingotIridium", "ingotIridium");
        CompressorRecipes.addShapelessRecipe(new ItemStack(FronosItems.tier8_rocket_module, 2, 2), new ItemStack(FronosItems.fronos_item, 1, 4), new ItemStack(FronosItems.fronos_item, 1, 5), new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));

        CompressorRecipes.addShapelessRecipe(new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0), new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0));

        CompressorRecipes.addShapelessRecipe(new ItemStack(SiriusBItems.sirius_b_item, 1, 0), new ItemStack(SiriusBBlocks.sirius_b_block, 9, 3));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SiriusBItems.sirius_b_item, 1, 4), new ItemStack(SiriusBItems.sirius_b_item, 1, 3), new ItemStack(SiriusBItems.sirius_b_item, 1, 3));

        CompressorRecipes.addShapelessRecipe(new ItemStack(MercuryItems.mercury_item, 1, 4), new ItemStack(MercuryItems.mercury_item, 1, 2), new ItemStack(MercuryItems.mercury_item, 1, 2));
        CompressorRecipes.addShapelessRecipe(new ItemStack(MercuryItems.mercury_item, 1, 5), new ItemStack(MercuryItems.mercury_item, 1, 3), new ItemStack(MercuryItems.mercury_item, 1, 3));
    }
}
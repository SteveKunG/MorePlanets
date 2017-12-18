/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.integration;

import stevekung.mods.moreplanets.core.util.CompatibilityUtilMP;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;

public class MicroBlockIntegrationMP
{
    public static void init()
    {
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 0, "tile.diona_block.surface_rock.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 1, "tile.diona_block.sub_surface.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 2, "tile.diona_block.rock.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 3, "tile.diona_block.cobblestone.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 10, "tile.diona_block.quontonium_block.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 11, "tile.diona_block.smooth_quontonium.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 12, "tile.diona_block.quontonium_brick.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 13, "tile.diona_block.chiseled_quontonium.name");
        CompatibilityUtilMP.registerMicroBlocks(DionaBlocks.diona_block, 14, "tile.diona_block.dungeon_brick.name");
    }
}
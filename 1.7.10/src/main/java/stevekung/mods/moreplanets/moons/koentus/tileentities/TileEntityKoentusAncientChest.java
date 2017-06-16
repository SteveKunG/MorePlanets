/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityAncientChestMP;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;

public class TileEntityKoentusAncientChest extends TileEntityAncientChestMP
{
    @Override
    public Block getAncientChestBlock()
    {
        return KoentusBlocks.koentus_ancient_chest;
    }

    @Override
    public String getGuiName()
    {
        return "koentus";
    }
}
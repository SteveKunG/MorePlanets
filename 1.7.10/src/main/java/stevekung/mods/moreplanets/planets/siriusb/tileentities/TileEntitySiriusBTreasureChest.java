/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class TileEntitySiriusBTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntitySiriusBTreasureChest()
    {
        super(8);
    }

    @Override
    public int getTreasureChestTier()
    {
        return 8;
    }

    @Override
    public String getTreasureChestName()
    {
        return "siriusb";
    }

    @Override
    public Block getTreasureChestBlock()
    {
        return SiriusBBlocks.sirius_b_treasure_chest;
    }
}
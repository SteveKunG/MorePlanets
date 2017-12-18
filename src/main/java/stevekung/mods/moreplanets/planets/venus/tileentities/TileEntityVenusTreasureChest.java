/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;

public class TileEntityVenusTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityVenusTreasureChest()
    {
        super(3);
    }

    @Override
    public int getTreasureChestTier()
    {
        return 3;
    }

    @Override
    public String getTreasureChestName()
    {
        return "venus";
    }

    @Override
    public Block getTreasureChestBlock()
    {
        return VenusBlocks.venus_treasure_chest;
    }
}
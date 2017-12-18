/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class TileEntityPolongniusTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityPolongniusTreasureChest()
    {
        super(5);
    }

    @Override
    public int getTreasureChestTier()
    {
        return 5;
    }

    @Override
    public String getTreasureChestName()
    {
        return "polongnius";
    }

    @Override
    public Block getTreasureChestBlock()
    {
        return PolongniusBlocks.polongnius_treasure_chest;
    }
}
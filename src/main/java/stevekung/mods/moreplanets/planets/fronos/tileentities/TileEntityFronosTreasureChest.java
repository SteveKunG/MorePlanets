/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class TileEntityFronosTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityFronosTreasureChest()
    {
        super(7);
    }

    @Override
    public int getTreasureChestTier()
    {
        return 7;
    }

    @Override
    public String getTreasureChestName()
    {
        return "fronos";
    }

    @Override
    public Block getTreasureChestBlock()
    {
        return FronosBlocks.fronos_treasure_chest;
    }
}
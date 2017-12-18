/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class TileEntityNibiruTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityNibiruTreasureChest()
    {
        super(6);
    }

    @Override
    public int getTreasureChestTier()
    {
        return 6;
    }

    @Override
    public String getTreasureChestName()
    {
        return "nibiru";
    }

    @Override
    public Block getTreasureChestBlock()
    {
        return NibiruBlocks.nibiru_treasure_chest;
    }
}
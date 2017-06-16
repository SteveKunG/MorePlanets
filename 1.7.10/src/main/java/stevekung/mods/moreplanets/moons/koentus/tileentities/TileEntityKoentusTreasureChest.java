/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.tileentities;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.tileentities.TileEntityTreasureChestMP;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;

public class TileEntityKoentusTreasureChest extends TileEntityTreasureChestMP
{
    public TileEntityKoentusTreasureChest()
    {
        super(4);
    }

    @Override
    public int getTreasureChestTier()
    {
        return 4;
    }

    @Override
    public String getTreasureChestName()
    {
        return "koentus";
    }

    @Override
    public Block getTreasureChestBlock()
    {
        return KoentusBlocks.koentus_treasure_chest;
    }
}
package com.stevekung.moreplanets.planets.nibiru.blocks;

import com.stevekung.moreplanets.planets.nibiru.tileentity.TileEntityAlienBerryChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

public class BlockAlienBerryChest extends BlockChestMP
{
    public BlockAlienBerryChest(String name)
    {
        super(name);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityAlienBerryChest();
    }
}
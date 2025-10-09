package com.stevekung.moreplanets.planets.chalos.blocks;

import com.stevekung.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

public class BlockCheeseSporeChest extends BlockChestMP
{
    public BlockCheeseSporeChest(String name)
    {
        super(name);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityCheeseSporeChest();
    }
}
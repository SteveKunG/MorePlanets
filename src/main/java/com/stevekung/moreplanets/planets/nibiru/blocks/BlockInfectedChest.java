package com.stevekung.moreplanets.planets.nibiru.blocks;

import com.stevekung.moreplanets.planets.nibiru.tileentity.TileEntityInfectedChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

public class BlockInfectedChest extends BlockChestMP
{
    public BlockInfectedChest(String name)
    {
        super(name);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityInfectedChest();
    }
}
package com.stevekung.moreplanets.planets.diona.blocks;

import com.stevekung.moreplanets.planets.diona.tileentity.TileEntityDionaAncientChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.blocks.chest.CustomChestContainer;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

import net.minecraft.block.Block;

public class BlockDionaAncientChest extends BlockChestMP implements CustomChestContainer
{
    public BlockDionaAncientChest(String name)
    {
        super(name);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityDionaAncientChest();
    }

    @Override
    public String getCustomContainerName(Block block)
    {
        return "container.diona.ancientchest.name";
    }
}
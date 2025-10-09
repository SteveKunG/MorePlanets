package com.stevekung.moreplanets.planets.chalos.blocks;

import com.stevekung.moreplanets.planets.chalos.tileentity.TileEntityChalosAncientChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.blocks.chest.CustomChestContainer;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

import net.minecraft.block.Block;

public class BlockChalosAncientChest extends BlockChestMP implements CustomChestContainer
{
    public BlockChalosAncientChest(String name)
    {
        super(name);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityChalosAncientChest();
    }

    @Override
    public String getCustomContainerName(Block block)
    {
        return "container.chalos.ancientchest.name";
    }
}
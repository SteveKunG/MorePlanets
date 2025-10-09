package com.stevekung.moreplanets.planets.nibiru.blocks;

import com.stevekung.moreplanets.planets.nibiru.tileentity.TileEntityNibiruAncientChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.blocks.chest.CustomChestContainer;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

import net.minecraft.block.Block;

public class BlockNibiruAncientChest extends BlockChestMP implements CustomChestContainer
{
    public BlockNibiruAncientChest(String name)
    {
        super(name);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityNibiruAncientChest();
    }

    @Override
    public String getCustomContainerName(Block block)
    {
        return "container.nibiru.ancientchest.name";
    }
}
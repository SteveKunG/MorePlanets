package com.stevekung.moreplanets.blocks;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.tileentity.TileEntityAlienChest;
import com.stevekung.moreplanets.utils.blocks.BlockChestMP;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAlienChest extends BlockChestMP
{
    public BlockAlienChest(String name)
    {
        super(name, Material.IRON);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.METAL);
    }

    @Override
    protected TileEntityChestMP getChestTile()
    {
        return new TileEntityAlienChest();
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return ColorUtils.stringToRGB(ItemRarity.ALIEN);
    }
}
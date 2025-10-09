package com.stevekung.moreplanets.utils.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockCobblestoneDrop extends BlockBaseMP
{
    private Block block;

    public BlockCobblestoneDrop(String name)
    {
        super(name, Material.ROCK);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this.block);
    }

    @Override
    public BlockCobblestoneDrop setHardness(float hardness)
    {
        super.setHardness(hardness);
        return this;
    }

    public void setDrop(Block block)
    {
        this.block = block;
    }
}
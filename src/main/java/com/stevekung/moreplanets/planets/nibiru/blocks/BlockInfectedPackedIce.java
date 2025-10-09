package com.stevekung.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import com.stevekung.moreplanets.utils.blocks.BlockBaseMP;
import com.stevekung.moreplanets.utils.blocks.IIce;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockInfectedPackedIce extends BlockBaseMP implements IIce
{
    public BlockInfectedPackedIce(String name)
    {
        super(name, Material.PACKED_ICE);
        this.setDefaultSlipperiness(0.98F);
        this.setHardness(0.5F);
        this.setSoundType(SoundType.GLASS);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }
}
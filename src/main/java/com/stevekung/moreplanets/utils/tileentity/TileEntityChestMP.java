package com.stevekung.moreplanets.utils.tileentity;

import com.stevekung.lib.utils.LangUtils;

import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntityChest;

import javax.annotation.Nullable;

public abstract class TileEntityChestMP extends TileEntityChest
{
    @Nullable
    private final String name;

    public TileEntityChestMP(String name)
    {
        super(BlockChest.Type.BASIC);
        this.name = name;
    }

    public TileEntityChestMP()
    {
        this(null);
    }

    @Override
    public String getName()
    {
        return this.name != null ? LangUtils.translate(this.name) : super.getName();
    }
}
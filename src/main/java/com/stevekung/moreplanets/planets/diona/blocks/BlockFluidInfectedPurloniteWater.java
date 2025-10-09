package com.stevekung.moreplanets.planets.diona.blocks;

import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPLootTables;
import com.stevekung.moreplanets.utils.EnumParticleTypesMP;
import com.stevekung.moreplanets.utils.blocks.BlockFluidBaseMP;
import com.stevekung.moreplanets.utils.blocks.IFishableLiquidBlock;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class BlockFluidInfectedPurloniteWater extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidInfectedPurloniteWater(String name)
    {
        super(name, MPBlocks.INFECTED_PURLONITE_WATER_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.INFECTED_PURLONITE_WATER_FISHING;
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.INFECTED_PURLONITE_WATER_DRIP;
    }
}
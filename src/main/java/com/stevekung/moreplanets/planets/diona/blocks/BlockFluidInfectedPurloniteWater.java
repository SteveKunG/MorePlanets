package com.stevekung.moreplanets.planets.diona.blocks;

import javax.annotation.Nullable;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPLootTables;
import com.stevekung.moreplanets.utils.EnumParticleTypesMP;
import com.stevekung.moreplanets.utils.blocks.BlockFluidBaseMP;
import com.stevekung.moreplanets.utils.blocks.IFishableLiquidBlock;

public class BlockFluidInfectedPurloniteWater extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidInfectedPurloniteWater(String name)
    {
        super(MPBlocks.INFECTED_PURLONITE_WATER_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setTranslationKey(name);
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
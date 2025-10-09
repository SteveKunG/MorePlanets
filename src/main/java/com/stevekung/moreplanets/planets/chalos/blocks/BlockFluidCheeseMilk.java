package com.stevekung.moreplanets.planets.chalos.blocks;

import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPLootTables;
import com.stevekung.moreplanets.utils.EnumParticleTypesMP;
import com.stevekung.moreplanets.utils.blocks.BlockFluidBaseMP;
import com.stevekung.moreplanets.utils.blocks.IFishableLiquidBlock;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class BlockFluidCheeseMilk extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidCheeseMilk(String name)
    {
        super(name, MPBlocks.CHEESE_MILK_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.CHEESE_MILK_FISHING;
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.CHEESE_MILK_DRIP;
    }
}
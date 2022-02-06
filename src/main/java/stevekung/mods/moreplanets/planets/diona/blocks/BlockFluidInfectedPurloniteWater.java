package stevekung.mods.moreplanets.planets.diona.blocks;

import javax.annotation.Nullable;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;

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
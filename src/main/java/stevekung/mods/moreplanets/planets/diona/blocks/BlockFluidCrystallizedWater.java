package stevekung.mods.moreplanets.planets.diona.blocks;

import javax.annotation.Nullable;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;

public class BlockFluidCrystallizedWater extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidCrystallizedWater(String name)
    {
        super(MPBlocks.CRYSTALLIZED_WATER_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.CRYSTALLIZED_WATER_FISHING;
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.CRYSTALLIZED_WATER_DRIP;
    }
}
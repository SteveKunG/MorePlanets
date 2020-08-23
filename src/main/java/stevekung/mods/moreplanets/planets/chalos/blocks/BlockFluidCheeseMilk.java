package stevekung.mods.moreplanets.planets.chalos.blocks;

import javax.annotation.Nullable;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;

public class BlockFluidCheeseMilk extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidCheeseMilk(String name)
    {
        super(MPBlocks.CHEESE_MILK_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
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
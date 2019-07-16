package stevekung.mods.moreplanets.planets.chalos.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockFarmlandMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockCheeseFarmland extends BlockFarmlandMP
{
    public BlockCheeseFarmland(String name)
    {
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, 0));
        this.setUnlocalizedName(name);
    }

    @Override
    protected Block getDirtBlock()
    {
        return MPBlocks.CHEESE_DIRT;
    }

    @Override
    protected Block getSourceBlock()
    {
        return MPBlocks.CHEESE_MILK_FLUID_BLOCK;
    }
}
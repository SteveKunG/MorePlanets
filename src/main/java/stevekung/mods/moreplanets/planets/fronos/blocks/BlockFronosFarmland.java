package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.blocks.BlockFarmlandMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockFronosFarmland extends BlockFarmlandMP
{
    public BlockFronosFarmland(String name)
    {
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, 0));
        this.setTranslationKey(name);
    }

    @Override
    protected Block getDirtBlock()
    {
        return MPBlocks.FRONOS_DIRT;
    }
}
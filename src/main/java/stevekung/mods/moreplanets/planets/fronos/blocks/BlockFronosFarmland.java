package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.utils.blocks.BlockFarmlandMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockFronosFarmland extends BlockFarmlandMP
{
    public BlockFronosFarmland(String name)
    {
        super();
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, Integer.valueOf(0)));
        this.setUnlocalizedName(name);
    }

    @Override
    protected Block getDirtBlock()
    {
        return FronosBlocks.FRONOS_DIRT;
    }
}
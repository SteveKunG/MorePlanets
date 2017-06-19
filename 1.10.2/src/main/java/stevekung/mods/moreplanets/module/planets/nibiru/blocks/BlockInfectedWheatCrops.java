package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.blocks.BlockCropsMP;

public class BlockInfectedWheatCrops extends BlockCropsMP
{
    public BlockInfectedWheatCrops(String name)
    {
        super();
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block block = world.getBlockState(pos.down()).getBlock();
        return super.canBlockStay(world, pos, state) && block == NibiruBlocks.INFECTED_FARMLAND;
    }

    @Override
    protected Item getSeed()
    {
        return NibiruItems.INFECTED_WHEAT_SEEDS;
    }

    @Override
    protected Item getCrop()
    {
        return NibiruItems.INFECTED_WHEAT;
    }
}
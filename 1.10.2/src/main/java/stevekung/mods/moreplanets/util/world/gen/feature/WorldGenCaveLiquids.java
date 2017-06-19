package stevekung.mods.moreplanets.util.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCaveLiquids extends WorldGenerator
{
    private Block block;
    private Block blockToGen;
    private int metaToGen;

    public WorldGenCaveLiquids(Block block, Block blockToGen)
    {
        this.block = block;
        this.blockToGen = blockToGen;
    }

    public WorldGenCaveLiquids(Block block, Block blockToGen, int metaToGen)
    {
        this.block = block;
        this.blockToGen = blockToGen;
        this.metaToGen = metaToGen;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if (world.getBlockState(pos.up()).getBlock() != this.blockToGen && world.getBlockState(pos.up()).getBlock().getMetaFromState(world.getBlockState(pos.up())) != 2)
        {
            return false;
        }
        else if (world.getBlockState(pos.down()).getBlock() != this.blockToGen && world.getBlockState(pos.down()).getBlock().getMetaFromState(world.getBlockState(pos.down())) != 2)
        {
            return false;
        }
        else if (world.getBlockState(pos).getBlock().getMaterial() != Material.air && world.getBlockState(pos).getBlock() != this.blockToGen && world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)) != 2)
        {
            return false;
        }
        else
        {
            int i = 0;

            if (world.getBlockState(pos.west()).getBlock() == this.blockToGen && world.getBlockState(pos.west()).getBlock().getMetaFromState(world.getBlockState(pos.west())) == 2)
            {
                ++i;
            }
            if (world.getBlockState(pos.east()).getBlock() == this.blockToGen && world.getBlockState(pos.east()).getBlock().getMetaFromState(world.getBlockState(pos.east())) == 2)
            {
                ++i;
            }
            if (world.getBlockState(pos.north()).getBlock() == this.blockToGen && world.getBlockState(pos.north()).getBlock().getMetaFromState(world.getBlockState(pos.north())) == 2)
            {
                ++i;
            }
            if (world.getBlockState(pos.south()).getBlock() == this.blockToGen && world.getBlockState(pos.south()).getBlock().getMetaFromState(world.getBlockState(pos.south())) == 2)
            {
                ++i;
            }
            if (world.getBlockState(pos.west()).getBlock() == this.blockToGen && world.getBlockState(pos.west()).getBlock().getMetaFromState(world.getBlockState(pos.west())) == this.metaToGen)
            {
                ++i;
            }
            if (world.getBlockState(pos.east()).getBlock() == this.blockToGen && world.getBlockState(pos.east()).getBlock().getMetaFromState(world.getBlockState(pos.east())) == this.metaToGen)
            {
                ++i;
            }
            if (world.getBlockState(pos.north()).getBlock() == this.blockToGen && world.getBlockState(pos.north()).getBlock().getMetaFromState(world.getBlockState(pos.north())) == this.metaToGen)
            {
                ++i;
            }
            if (world.getBlockState(pos.south()).getBlock() == this.blockToGen && world.getBlockState(pos.south()).getBlock().getMetaFromState(world.getBlockState(pos.south())) == this.metaToGen)
            {
                ++i;
            }

            int j = 0;

            if (world.isAirBlock(pos.west()))
            {
                ++j;
            }
            if (world.isAirBlock(pos.east()))
            {
                ++j;
            }
            if (world.isAirBlock(pos.north()))
            {
                ++j;
            }
            if (world.isAirBlock(pos.south()))
            {
                ++j;
            }

            if (i == 3 && j == 1)
            {
                world.setBlockState(pos, this.block.getDefaultState(), 2);
                world.forceBlockUpdateTick(this.block, pos, rand);
            }
            return true;
        }
    }
}
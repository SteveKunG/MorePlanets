package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityMultalicCrystal;

public class WorldGenCrystalObelisk extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int height = 8 + rand.nextInt(4);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < 2; x++)
            {
                for (int z = 0; z < 2; z++)
                {
                    Block block = world.getBlockState(pos.down()).getBlock();

                    if ((block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.INFECTED_SAND || block == NibiruBlocks.GREEN_VEIN_GRASS) && world.isAirBlock(pos.add(x, height, z)))
                    {
                        world.setBlockState(pos.add(x, y, z), Blocks.OBSIDIAN.getDefaultState(), 2);

                        switch (rand.nextInt(4))
                        {
                        case 0:
                        default:
                            world.setBlockState(pos.add(0, y + 1, 0), Blocks.OBSIDIAN.getDefaultState(), 2);
                            world.setBlockState(pos.add(0, y + 2, 0), rand.nextInt(5) == 0 ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.LAPIS_BLOCK.getDefaultState(), 2);
                            this.genCrystal(world, pos.add(0, y + 3, 0));
                            break;
                        case 1:
                            world.setBlockState(pos.add(x, y + 1, 0), Blocks.OBSIDIAN.getDefaultState(), 2);
                            world.setBlockState(pos.add(x, y + 2, 0), rand.nextInt(5) == 0 ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.LAPIS_BLOCK.getDefaultState(), 2);
                            this.genCrystal(world, pos.add(x, y + 3, 0));
                            break;
                        case 2:
                            world.setBlockState(pos.add(1, y + 1, 0), Blocks.OBSIDIAN.getDefaultState(), 2);
                            world.setBlockState(pos.add(1, y + 2, 0), rand.nextInt(5) == 0 ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.LAPIS_BLOCK.getDefaultState(), 2);
                            this.genCrystal(world, pos.add(1, y + 3, 0));
                            break;
                        case 3:
                            world.setBlockState(pos.add(x, y + 1, 1), Blocks.OBSIDIAN.getDefaultState(), 2);
                            world.setBlockState(pos.add(x, y + 2, 1), rand.nextInt(5) == 0 ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.LAPIS_BLOCK.getDefaultState(), 2);
                            this.genCrystal(world, pos.add(x, y + 3, 1));
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void genCrystal(World world, BlockPos pos)
    {
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            Block block = NibiruBlocks.MULTALIC_CRYSTAL;

            if (world.isAirBlock(pos) && block.canPlaceBlockOnSide(world, pos, facing))
            {
                world.setBlockState(pos, block.getDefaultState(), 2);
                TileEntityMultalicCrystal ts = (TileEntityMultalicCrystal)world.getTileEntity(pos);
                ts.facing = facing.getIndex();
            }
        }
    }
}
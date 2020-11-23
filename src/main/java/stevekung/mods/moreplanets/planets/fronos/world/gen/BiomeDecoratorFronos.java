package stevekung.mods.moreplanets.planets.fronos.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.fronos.world.gen.feature.WorldGenFronosClay;
import stevekung.mods.moreplanets.planets.fronos.world.gen.feature.WorldGenFronosSand;
import stevekung.mods.moreplanets.planets.fronos.world.gen.feature.WorldGenFronosSugarCane;
import stevekung.mods.moreplanets.utils.world.gen.biome.BiomeMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeDecoratorFronos extends BiomeDecoratorMP
{
    public int largeWheatPerChunk;

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        for (int i = 0; i < this.grassPerChunk; ++i)
        {
            WorldDecorateUtils.generatePlants(biome.getRandomWorldGenForGrass(rand), world, rand, this.chunkPos);
        }
        for (int i = 0; i < this.flowersPerChunk; ++i)
        {
            if (!(biome instanceof BiomeMP))
            {
                return;
            }

            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = world.getHeight(this.chunkPos.add(x, 0, z)).getY() + 32;

            if (y > 0)
            {
                int randY = rand.nextInt(y);
                BlockPos blockpos1 = this.chunkPos.add(x, randY, z);
                IBlockState state = ((BiomeMP)biome).pickRandomModdedFlower(rand, blockpos1);

                if (state.getMaterial() != Material.AIR)
                {
                    WorldGenFlowersBase worldGen = new WorldGenFlowersBase(state);
                    worldGen.generate(world, rand, blockpos1);
                }
            }
        }
        //        for (int i = 0; i < this.waterlilyPerChunk; ++i)
        //        {
        //            int x = rand.nextInt(16) + 8;
        //            int z = rand.nextInt(16) + 8;
        //            int y = world.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;
        //
        //            if (y > 0)
        //            {
        //                int y1 = rand.nextInt(y);
        //                BlockPos blockpos4;
        //                BlockPos blockpos7;
        //
        //                for (blockpos4 = this.chunkPos.add(x, y1, z); blockpos4.getY() > 0; blockpos4 = blockpos7)
        //                {
        //                    blockpos7 = blockpos4.down();
        //
        //                    if (!world.isAirBlock(blockpos7))
        //                    {
        //                        break;
        //                    }
        //                }
        //                new WorldGenSporelily().generate(world, rand, blockpos4);
        //            }
        //        }
        for (int i = 0; i < this.clayPerChunk; ++i)
        {
            int l1 = rand.nextInt(16) + 8;
            int i6 = rand.nextInt(16) + 8;
            new WorldGenFronosClay(4).generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(l1, 0, i6)));
        }
        for (int i = 0; i < this.reedsPerChunk; ++i)
        {
            WorldDecorateUtils.generatePlants(new WorldGenFronosSugarCane(), world, rand, this.chunkPos);
        }
        for (int i = 0; i < this.sandPatchesPerChunk; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            new WorldGenFronosSand(Blocks.SAND.getDefaultState(), 7).generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(j, 0, k)));
        }
        for (int i = 0; i < this.gravelPatchesPerChunk; ++i)
        {
            int i2 = rand.nextInt(16) + 8;
            int j6 = rand.nextInt(16) + 8;
            new WorldGenFronosSand(Blocks.GRAVEL.getDefaultState(), 6).generate(world, rand, world.getTopSolidOrLiquidBlock(this.chunkPos.add(i2, 0, j6)));
        }

        int treesPerChunk = this.treesPerChunk;

        if (rand.nextFloat() < this.extraTreeChance)
        {
            ++treesPerChunk;
        }

        for (int i = 0; i < treesPerChunk; ++i)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            BlockPos pos = world.getHeight(this.chunkPos.add(x, 0, z));
            WorldGenAbstractTree tree = biome.getRandomTreeFeature(rand);
            tree.setDecorationDefaults();

            if (tree.generate(world, rand, pos))
            {
                tree.generateSaplings(world, rand, pos);
            }
        }

        if (this.largeWheatPerChunk > 0)
        {
            /*int roseBushPerChunk = rand.nextInt(5) - this.largeWheatPerChunk;TODO

            for (int i = 0; i < roseBushPerChunk; ++i)
            {
                for (int i2 = 0; i2 < 5; ++i2)
                {
                    int y = rand.nextInt(world.getHeight(this.chunkPos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)).getY() + 32);
                    WorldGenDoublePlantMP worldgen = new WorldGenDoublePlantMP(MPBlocks.INFECTED_ORANGE_ROSE_BUSH);

                    if (worldgen.generate(world, rand, new BlockPos(this.chunkPos.getX() + rand.nextInt(16) + 8, y, this.chunkPos.getZ() + rand.nextInt(16) + 8)))
                    {
                        break;
                    }
                }
            }*/
        }
    }
}
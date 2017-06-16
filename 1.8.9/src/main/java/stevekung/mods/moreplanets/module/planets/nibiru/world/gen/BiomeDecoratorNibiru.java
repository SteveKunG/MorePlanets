package stevekung.mods.moreplanets.module.planets.nibiru.world.gen;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.*;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.*;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenFlowersMP;

public class BiomeDecoratorNibiru extends BiomeDecoratorMP
{
    public int infectedTallGrassPerChunk;
    public int infectedFernPerChunk;
    public int infectedCactusPerChunk;
    public int deadBushPerChunk;
    public int infectedTreesPerChunk;
    public int pureHurbPerChunk;
    public int terrapuffHurbPerChunk;
    public int greenVeinTallGrassPerChunk;
    public int orangeBushPerChunk;
    public int batasiaDandelionPerChunk;
    public int pyoloniaPerChunk;
    public int philipyPerChunk;
    public int whiteTailPerChunk;
    public int vealiumVinePerChunk;
    public int seaweedPerChunk;

    @Override
    protected void generate(BiomeGenBase biome)
    {
        int i;

        for (i = 0; i < this.infectedTallGrassPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.INFECTED_TALL_GRASS)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.pureHurbPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.PURE_HURB)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.terrapuffHurbPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.TERRAPUFF_HURB)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.batasiaDandelionPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.BATASIA_DANDELION)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.pyoloniaPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.PYOLONIA_FLOWER)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.philipyPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.PHILIPY_FLOWER)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.whiteTailPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.WHITE_TAIL)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.vealiumVinePerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_FLOWER.getDefaultState().withProperty(BlockNibiruFlower.VARIANT, BlockNibiruFlower.BlockType.VEALIUM_VINE_FLOWER)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.seaweedPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenNibiruSeaweed(NibiruBlocks.NIBIRU_SEAWEED.getDefaultState().withProperty(BlockNibiruSeaweed.VARIANT, BlockNibiruSeaweed.BlockType.NIBIRU_SEAWEED)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.infectedCactusPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenInfectedCactus(), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.deadBushPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenDeadBush(), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.infectedFernPerChunk; ++i)
        {
            DecorateHelper.generatePlants(biome.getRandomWorldGenForGrass(this.randomGenerator), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.greenVeinTallGrassPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenFlowersMP(NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.GREEN_VEIN_TALL_GRASS)), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.waterlilyPerChunk; ++i)
        {
            int x = this.randomGenerator.nextInt(16) + 8;
            int z = this.randomGenerator.nextInt(16) + 8;
            int y = this.currentWorld.getHeight(this.field_180294_c.add(x, 0, z)).getY() * 2;

            if (y > 0)
            {
                int y1 = this.randomGenerator.nextInt(y);
                BlockPos blockpos4;
                BlockPos blockpos7;

                for (blockpos4 = this.field_180294_c.add(x, y1, z); blockpos4.getY() > 0; blockpos4 = blockpos7)
                {
                    blockpos7 = blockpos4.down();

                    if (!this.currentWorld.isAirBlock(blockpos7))
                    {
                        break;
                    }
                }
                new WorldGenSporelily().generate(this.currentWorld, this.randomGenerator, blockpos4);
            }
        }
        for (i = 0; i < this.clayPerChunk; ++i)
        {
            int l1 = this.randomGenerator.nextInt(16) + 8;
            int i6 = this.randomGenerator.nextInt(16) + 8;
            new WorldGenNibiruClay(4).generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(l1, 0, i6)));
        }
        for (i = 0; i < this.reedsPerChunk; ++i)
        {
            DecorateHelper.generatePlants(new WorldGenInfectedSugarCane(), this.currentWorld, this.randomGenerator, this.field_180294_c);
        }
        for (i = 0; i < this.sandPerChunk2; ++i)
        {
            int j = this.randomGenerator.nextInt(16) + 8;
            int k = this.randomGenerator.nextInt(16) + 8;
            new WorldGenNibiruSand(NibiruBlocks.INFECTED_SAND, 7).generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(j, 0, k)));
        }
        for (i = 0; i < this.sandPerChunk; ++i)
        {
            int i2 = this.randomGenerator.nextInt(16) + 8;
            int j6 = this.randomGenerator.nextInt(16) + 8;
            new WorldGenNibiruSand(NibiruBlocks.INFECTED_GRAVEL, 6).generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(i2, 0, j6)));
        }

        int treesPerChunk = this.infectedTreesPerChunk;

        if (this.randomGenerator.nextInt(10) == 0)
        {
            ++treesPerChunk;
        }

        for (i = 0; i < treesPerChunk; ++i)
        {
            int k6 = this.randomGenerator.nextInt(16) + 8;
            int l = this.randomGenerator.nextInt(16) + 8;
            BlockPos blockpos = this.currentWorld.getHeight(this.field_180294_c.add(k6, 0, l));
            WorldGenAbstractTree worldgenabstracttree = biome.genBigTreeChance(this.randomGenerator);

            if (worldgenabstracttree.generate(this.currentWorld, this.randomGenerator, blockpos))
            {
                worldgenabstracttree.func_180711_a(this.currentWorld, this.randomGenerator, blockpos);
            }
        }

        if (this.randomGenerator.nextInt(1000) == 0)
        {
            int x = this.randomGenerator.nextInt(16) + 8;
            int z = this.randomGenerator.nextInt(16) + 8;
            new WorldGenCrystalObelisk().generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(x, 0, z)));
        }

        if (this.orangeBushPerChunk > 0)
        {
            int roseBushPerChunk = this.randomGenerator.nextInt(5) - this.orangeBushPerChunk;

            for (i = 0; i < roseBushPerChunk; ++i)
            {
                for (int i2 = 0; i2 < 5; ++i2)
                {
                    int y = this.randomGenerator.nextInt(this.currentWorld.getHeight(this.field_180294_c.add(this.randomGenerator.nextInt(16) + 8, 0, this.randomGenerator.nextInt(16) + 8)).getY() + 32);

                    if (new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.INFECTED_ORANGE_ROSE_BUSH).generate(this.currentWorld, this.randomGenerator, new BlockPos(this.field_180294_c.getX() + this.randomGenerator.nextInt(16) + 8, y, this.field_180294_c.getZ() + this.randomGenerator.nextInt(16) + 8)))
                    {
                        break;
                    }
                }
            }
        }

        for (i = 0; i < 4; i++)
        {
            new WorldGenMultalicCrystal().generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(this.randomGenerator.nextInt(16) + 8, this.randomGenerator.nextInt(36), this.randomGenerator.nextInt(16) + 8));
        }
    }
}
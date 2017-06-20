package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruTallGrass;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.*;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenFlowersMP;

public class BiomeInfectedJungle extends BiomeNibiru
{
    private IBlockState log = NibiruBlocks.NIBIRU_LOG.getDefaultState();
    private IBlockState leaves = NibiruBlocks.NIBIRU_LEAVES.getDefaultState();

    public BiomeInfectedJungle(BiomeProperties properties)
    {
        super(properties);
        properties.setTemperature(0.95F);
        properties.setRainfall(0.9F);
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0.2F);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 128;
        this.getBiomeDecorator().vealiumVinePerChunk = 4;
        this.getBiomeDecorator().pyoloniaPerChunk = 4;
        this.getBiomeDecorator().reedsPerChunk = 20;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.getBiomeDecorator().infectedTreesPerChunk = 50;
        this.getBiomeDecorator().infectedFernPerChunk = 25;
        this.getBiomeDecorator().pureHurbPerChunk = 4;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return rand.nextInt(10) == 0 ? new WorldGenInfectedBigTree(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : rand.nextInt(2) == 0 ? new WorldGenNibiruShrub(this.log, this.leaves) : rand.nextInt(3) == 0 ? new WorldGenInfectedMegaJungle(false, 10, 20) : new WorldGenInfectedJungleTrees(true, 4 + rand.nextInt(7), true);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return rand.nextInt(4) == 0 ? new WorldGenFlowersMP(NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.INFECTED_FERN)) : new WorldGenFlowersMP(NibiruBlocks.NIBIRU_TALL_GRASS.getDefaultState().withProperty(BlockNibiruTallGrass.VARIANT, BlockNibiruTallGrass.BlockType.INFECTED_TALL_GRASS));
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        int height = world.getHeight(pos.add(i, 0, j)).getY() * 2;

        if (height < 1)
        {
            height = 1;
        }

        int k = rand.nextInt(height);
        new WorldGenNibiruMelon().generate(world, rand, pos.add(i, k, j));
        WorldGenInfectedVines worldgenvines = new WorldGenInfectedVines();

        for (j = 0; j < 50; ++j)
        {
            k = rand.nextInt(16) + 8;
            int i1 = rand.nextInt(16) + 8;
            worldgenvines.generate(world, rand, pos.add(k, 128, i1));
        }
        for (int i1 = 0; i1 < 7; ++i1)
        {
            int j1 = rand.nextInt(16) + 8;
            int k1 = rand.nextInt(16) + 8;
            int l1 = rand.nextInt(world.getHeight(pos.add(j1, 0, k1)).getY() + 32);
            new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_FERN).generate(world, rand, pos.add(j1, l1, k1));
        }
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
        }
    }
}
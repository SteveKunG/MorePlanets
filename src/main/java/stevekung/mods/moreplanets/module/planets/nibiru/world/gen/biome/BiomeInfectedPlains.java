package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedBigTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruDoublePlant;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;

public class BiomeInfectedPlains extends BiomeNibiru
{
    public BiomeInfectedPlains(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 10;
        this.getBiomeDecorator().extraTreeChance = 0.05F;
        this.getBiomeDecorator().infectedTreesPerChunk = 0;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (d0 < -0.8D)
        {
            this.getBiomeDecorator().infectedTallGrassPerChunk = 86;
        }
        else
        {
            this.getBiomeDecorator().infectedTallGrassPerChunk = 128;

            for (int i = 0; i < 7; ++i)
            {
                new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_GRASS).generate(world, rand, DecorateHelper.getSimplePos(world, pos, rand));
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(3) == 0 ? new WorldGenInfectedBigTree(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : new WorldGenInfectedTrees(true, NibiruBlocks.NIBIRU_LOG.getDefaultState(), NibiruBlocks.NIBIRU_LEAVES.getDefaultState());
    }
}
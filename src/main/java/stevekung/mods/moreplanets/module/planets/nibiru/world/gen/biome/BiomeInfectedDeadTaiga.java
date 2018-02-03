package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.CONIFEROUS;
import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga1;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruDoublePlant;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.helper.DecorateHelper;

public class BiomeInfectedDeadTaiga extends BiomeNibiru
{
    public BiomeInfectedDeadTaiga(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTreesPerChunk = 10;
        this.getBiomeDecorator().infectedTallGrassPerChunk = 7;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        CommonRegisterHelper.registerBiomeType(biome, COLD, CONIFEROUS, DEAD);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, DecorateHelper.getSimplePos(world, pos, rand));
        }
        for (int i = 0; i < 7; ++i)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            new WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType.DOUBLE_INFECTED_FERN).generate(world, rand, pos.add(x, y, z));
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(false) : new WorldGenInfectedDeadTaiga2(false);
        }
        else
        {
            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(true) : new WorldGenInfectedDeadTaiga2(true);
        }
    }
}
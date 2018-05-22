package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedChicken;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.*;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeInfectedJungle extends BiomeNibiru
{
    private static final WorldGenFlowersBase GRASS = new WorldGenFlowersBase(MPBlocks.INFECTED_GRASS.getDefaultState());
    private static final WorldGenFlowersBase FERN = new WorldGenFlowersBase(MPBlocks.INFECTED_FERN.getDefaultState());
    private static final WorldGenNibiruMelon MELON = new WorldGenNibiruMelon();
    private static final WorldGenInfectedVines VINES = new WorldGenInfectedVines();
    private static final WorldGenInfectedMegaJungleTree MEGA_JUNGLE_TREE = new WorldGenInfectedMegaJungleTree();
    private static final WorldGenInfectedShrub SHRUB = new WorldGenInfectedShrub(MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    private boolean isEdge;

    public BiomeInfectedJungle(BiomeProperties prop, boolean isEdge)
    {
        super(prop);
        this.isEdge = isEdge;

        if (isEdge)
        {
            this.decorator.infectedTreesPerChunk = 2;
        }
        else
        {
            this.decorator.infectedTreesPerChunk = 50;
        }

        this.decorator.infectedTallGrassPerChunk = 25;
        this.decorator.vealiumVinePerChunk = 4;
        this.decorator.pyoloniaPerChunk = 4;
        this.decorator.infectedFernPerChunk = 25;
        this.decorator.pureHurbPerChunk = 4;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityInfectedChicken.class, 10, 4, 4));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        WorldGenInfectedJungleTrees jungleTree = new WorldGenInfectedJungleTrees(true, 4 + rand.nextInt(7), true);

        if (rand.nextInt(10) == 0)
        {
            return BiomeNibiru.BIG_TREE;
        }
        else if (rand.nextInt(2) == 0)
        {
            return BiomeInfectedJungle.SHRUB;
        }
        else
        {
            return !this.isEdge && rand.nextInt(3) == 0 ? BiomeInfectedJungle.MEGA_JUNGLE_TREE : jungleTree;
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return rand.nextInt(4) == 0 ? BiomeInfectedJungle.FERN : BiomeInfectedJungle.GRASS;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int height = world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)).getY() * 2;

        if (height < 1)
        {
            height = 1;
        }

        BiomeInfectedJungle.MELON.generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(height), rand.nextInt(16) + 8));

        for (int i = 0; i < 50; ++i)
        {
            BiomeInfectedJungle.VINES.generate(world, rand, pos.add(rand.nextInt(16) + 8, 128, rand.nextInt(16) + 8));
        }
        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
    }
}
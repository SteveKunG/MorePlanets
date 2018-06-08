package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.*;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeInfectedJungle extends BiomeNibiru
{
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
            this.decorator.treesPerChunk = 2;
        }
        else
        {
            this.decorator.treesPerChunk = 50;
        }
        this.decorator.grassPerChunk = 115;
        this.decorator.flowersPerChunk = 4;
    }

    @Override
    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return rand.nextInt(6) > 3 ? MPBlocks.PURE_HERB.getDefaultState() : rand.nextInt(3) == 0 ? MPBlocks.PYOLONIA.getDefaultState() : MPBlocks.VEALIUM_VINES.getDefaultState();
    }

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(MPBlocks.PURE_HERB.getDefaultState(), 20);
        this.addFlower(MPBlocks.PYOLONIA.getDefaultState(), 20);
        this.addFlower(MPBlocks.VEALIUM_VINES.getDefaultState(), 10);
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
        return rand.nextInt(4) == 0 ? BiomeInfectedJungle.FERN : BiomeNibiru.GRASS;
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
package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedSnowman;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadSpruce;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedIcePath;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedIceSpike;

public class BiomeInfectedSnow extends BiomeNibiru
{
    private final boolean superIcy;
    private static final WorldGenInfectedIceSpike ICE_SPIKE = new WorldGenInfectedIceSpike();
    private static final WorldGenInfectedIcePath ICE_PATH = new WorldGenInfectedIcePath(4);
    private static final WorldGenInfectedDeadSpruce DEAD_SPRUCE = new WorldGenInfectedDeadSpruce(true);
    private static final WorldGenInfectedDeadSpruce DEAD_SPRUCE_NO_LEAVES = new WorldGenInfectedDeadSpruce(false);

    public BiomeInfectedSnow(BiomeProperties prop, boolean superIcy)
    {
        super(prop);
        this.superIcy = superIcy;

        if (superIcy)
        {
            this.topBlock = MPBlocks.INFECTED_SNOW.getDefaultState();
        }
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityInfectedSnowman.class, 50, 1, 1));
    }

    @Override
    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return rand.nextInt(3) > 0 ? MPBlocks.PURE_HERB.getDefaultState() : MPBlocks.PHILIPY.getDefaultState();
    }

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(MPBlocks.PURE_HERB.getDefaultState(), 20);
        this.addFlower(MPBlocks.PHILIPY.getDefaultState(), 10);
    }

    @Override
    public float getSpawningChance()
    {
        return 0.07F;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (this.superIcy)
        {
            for (int i = 0; i < 3; ++i)
            {
                BiomeInfectedSnow.ICE_SPIKE.generate(world, rand, world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)));
            }
            for (int l = 0; l < 2; ++l)
            {
                BiomeInfectedSnow.ICE_PATH.generate(world, rand, world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)));
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(20) == 0 ? BiomeInfectedSnow.DEAD_SPRUCE_NO_LEAVES : BiomeInfectedSnow.DEAD_SPRUCE;
    }
}
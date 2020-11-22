package stevekung.mods.moreplanets.planets.fronos.world.gen.biome;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.fronos.world.gen.BiomeDecoratorFronos;
import stevekung.mods.moreplanets.utils.world.gen.biome.BiomeMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeFronos extends BiomeMP
{
    protected IBlockState stoneBlock;
    protected IBlockState liquidBlock;
    protected final BiomeDecoratorFronos decorator = new BiomeDecoratorFronos();
    //    protected static final WorldGenInfectedBigTree BIG_TREE = new WorldGenInfectedBigTree(true, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    //    protected static final WorldGenInfectedBigTree BIG_TREE_NO_LEAVES = new WorldGenInfectedBigTree(false, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    //    protected static final WorldGenInfectedTrees TREE = new WorldGenInfectedTrees(true, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    //    protected static final WorldGenInfectedTrees TREE_NO_LEAVES = new WorldGenInfectedTrees(false, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    //    protected static final WorldGenInfectedVinesDirt SCATTERED_DIRT = new WorldGenInfectedVinesDirt();
    //    protected static final WorldGenNibiruFossils FOSSILS = new WorldGenNibiruFossils();
    protected static final WorldGenFlowersBase GRASS = new WorldGenFlowersBase(MPBlocks.FRONOS_GRASS.getDefaultState());

    public BiomeFronos(BiomeProperties prop)
    {
        super(prop);
        this.topBlock = MPBlocks.FRONOS_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.FRONOS_DIRT.getDefaultState();
        this.stoneBlock = MPBlocks.FRONOS_STONE.getDefaultState();
        this.liquidBlock = Blocks.WATER.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 10, 4, 4));
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 95, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 5, 1, 1));
        this.spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntitySquid.class, 10, 4, 4));

        //        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityInfectedZombie.class, 100, 4, 4));
        //        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityInfectedSkeleton.class, 100, 4, 4));
        //        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityInfectedCreeper.class, 100, 4, 4));
        //        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGiantWorm.class, 20, 2, 4));
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityInfectedChicken.class, 10, 4, 4));
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityInfectedCow.class, 8, 4, 4));
        //        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityShlime.class, 12, 4, 4));
        //        this.spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntityInfectedSquid.class, 10, 4, 4));
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return this == MPBiomes.FRONOS_RIVER ? ColorUtils.rgbToDecimal(104, 176, 99) : super.getGrassColorAtPos(pos);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        this.decorator.decorate(world, rand, this, pos);
    }

    //    @Override
    //    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    //    {
    //        return rand.nextInt(10) == 0 ? BiomeFronos.BIG_TREE : BiomeFronos.TREE;
    //    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return BiomeFronos.GRASS;
    }

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(MPBlocks.NEMOPHILA.getDefaultState(), 10);
    }

    @Override
    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);
        return d0 > -0.5D ? MPBlocks.NEMOPHILA.getDefaultState() : MPBlocks.PINK_BLECHNUM.getDefaultState();
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        int seaLevel = world.getSeaLevel();
        IBlockState topState = this.topBlock;
        IBlockState fillerState = this.fillerBlock;
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int x = chunkZ & 15;// WTF??
        int z = chunkX & 15;// WTF??
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int y = 255; y >= 0; --y)
        {
            if (y <= rand.nextInt(5))
            {
                primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
            }
            else
            {
                IBlockState stoneState = primer.getBlockState(x, y, z);

                if (stoneState.getMaterial() == Material.AIR)
                {
                    j = -1;
                }
                else if (stoneState.getBlock() == MPBlocks.FRONOS_STONE)
                {
                    primer.setBlockState(x, y, z, this.stoneBlock);

                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            topState = null;
                            fillerState = MPBlocks.FRONOS_STONE.getDefaultState();
                        }
                        else if (y >= seaLevel - 4 && y <= seaLevel + 1)
                        {
                            topState = this.topBlock;
                            fillerState = this.fillerBlock;
                        }

                        if (y < seaLevel && (topState == null || topState.getMaterial() == Material.AIR))
                        {
                            if (this.getTemperature(mutablePos.setPos(chunkX, y, chunkZ)) < 0.15F)
                            {
                                topState = Blocks.ICE.getDefaultState();
                            }
                            else
                            {
                                topState = this.liquidBlock;
                            }
                        }

                        j = k;

                        if (y >= seaLevel - 1)
                        {
                            primer.setBlockState(x, y, z, topState);
                        }
                        else if (y < seaLevel - 7 - k)
                        {
                            topState = null;
                            fillerState = MPBlocks.FRONOS_STONE.getDefaultState();
                            primer.setBlockState(x, y, z, Blocks.GRAVEL.getDefaultState());
                        }
                        else
                        {
                            primer.setBlockState(x, y, z, fillerState);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        primer.setBlockState(x, y, z, fillerState);

                        if (j == 0 && fillerState.getBlock() == Blocks.SAND)
                        {
                            j = rand.nextInt(4) + Math.max(0, y - 63);
                            fillerState = Blocks.SANDSTONE.getDefaultState();
                        }
                    }
                }
            }
        }
    }
}
package stevekung.mods.moreplanets.planets.fronos.world.gen;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.world.gen.ChunkGeneratorBaseMP;
import stevekung.mods.stevekunglib.world.gen.MapGenCavesBase;
import stevekung.mods.stevekunglib.world.gen.MapGenRavineBase;
import stevekung.mods.stevekunglib.world.gen.WorldGenLiquidLake;

public class ChunkGeneratorFronos extends ChunkGeneratorBaseMP
{
    private final MapGenCavesBase caveGenerator = new MapGenCavesBase(Sets.newHashSet(MPBlocks.FRONOS_GRASS_BLOCK), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_STONE), Sets.newHashSet(Blocks.WATER));
    private final MapGenRavineBase ravineGenerator = new MapGenRavineBase(Sets.newHashSet(MPBlocks.FRONOS_GRASS_BLOCK), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_STONE), Sets.newHashSet(Blocks.WATER));
    //    private final MapGenNibiruStronghold strongholdGenerator = new MapGenStronghold();TODO
    private final BiomeDecoratorFronosOre decorator = new BiomeDecoratorFronosOre();

    public ChunkGeneratorFronos(World world, long seed)
    {
        super(world, seed);
        this.isSingleBiomePlanet = false;
    }

    @Override
    protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ) {}

    @Override
    protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.caveGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.ravineGenerator.generate(this.world, chunkX, chunkZ, primer);
        //        this.mineshaftGenerator.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        this.decorator.decorate(this.world, this.rand, biome, pos);
        //        this.mineshaftGenerator.generateStructure(this.world, this.rand, chunkpos);

        if (this.rand.nextInt(4) == 0)
        {
            new WorldGenLiquidLake(Blocks.WATER.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (this.rand.nextInt(8) == 0)
        {
            int y = this.rand.nextInt(this.rand.nextInt(248) + 8);

            if (y < 63 || this.rand.nextInt(10) == 0)
            {
                new WorldGenLiquidLake(Blocks.LAVA.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), true).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
            }
        }

        for (int i = 0; i < 8; ++i)
        {
            new WorldGenDungeons().generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        biome.decorate(this.world, this.rand, pos);
    }

    @Override
    protected IBlockState getTopBlock()
    {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    protected IBlockState getSubBlock()
    {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    protected IBlockState getStoneBlock()
    {
        return MPBlocks.FRONOS_STONE.getDefaultState();
    }

    @Override
    protected IBlockState getLiquidBlock()
    {
        return Blocks.WATER.getDefaultState();
    }

    @Override
    public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored)
    {
        return null;
        //        if ("NibiruStronghold".equals(name) && this.strongholdGenerator != null)
        //        {
        //            return this.strongholdGenerator.getNearestStructurePos(world, pos, findUnexplored);
        //        }
    }

    @Override
    public boolean isInsideStructure(World world, String name, BlockPos pos)
    {
        return false;
        //        if ("NibiruStronghold".equals(name) && this.strongholdGenerator != null)
        //        {
        //            return this.strongholdGenerator.isInsideStructure(pos);
        //        }
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        //        this.mineshaftGenerator.generate(this.world, chunkX, chunkZ, null);
    }
}
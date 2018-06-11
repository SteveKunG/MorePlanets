package stevekung.mods.moreplanets.moons.koentus.world.gen;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.moons.koentus.world.gen.feature.WorldGenGravityHarvester;
import stevekung.mods.moreplanets.planets.diona.world.gen.BiomeDecoratorDiona;
import stevekung.mods.moreplanets.planets.diona.world.gen.MapGenCrashedAlienShipFeature;
import stevekung.mods.moreplanets.planets.diona.world.gen.feature.WorldGenCrashedAlienProbe;
import stevekung.mods.moreplanets.utils.world.gen.ChunkGeneratorBaseMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenSpaceDungeons;
import stevekung.mods.stevekunglib.world.gen.MapGenCavesBase;

public class ChunkGeneratorKoentus extends ChunkGeneratorBaseMP
{
    private BiomeDecoratorDiona decorator = new BiomeDecoratorDiona();
    private MapGenCavesBase caveGenerator = new MapGenCavesBase(MPBlocks.KOENTUS_REGOLITH.getDefaultState(), Blocks.LAVA.getDefaultState(), Sets.newHashSet(MPBlocks.KOENTUS_FINE_REGOLITH, MPBlocks.KOENTUS_ROCK));
    private MapGenCrashedAlienShipFeature alienShipFeatureGenerator = new MapGenCrashedAlienShipFeature();

    public ChunkGeneratorKoentus(World world, long seed)
    {
        super(world, seed);
    }

    @Override
    protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.createCraters(chunkX, chunkZ, primer);
    }

    @Override
    protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.caveGenerator.generate(this.world, chunkX, chunkZ, primer);
        this.alienShipFeatureGenerator.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        //int y = this.rand.nextInt(this.rand.nextInt(248) + 8);
        this.decorator.decorate(this.world, this.rand, biome, pos);
        this.alienShipFeatureGenerator.generateStructure(this.world, this.rand, chunkpos);

        //        if (this.rand.nextInt(4) == 0)
        //        {
        //            if (y < 48)
        //            {
        //                new WorldGenLiquidLake(MPBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
        //            }
        //        }
        //        if (this.rand.nextInt(8) == 0)
        //        {
        //            if (y < 48)
        //            {
        //                new WorldGenLiquidLake(MPBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), true).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
        //            }
        //        }
        if (this.rand.nextInt(20) == 0)
        {
            int posY = this.world.getTopSolidOrLiquidBlock(pos.add(0, 0, 0)).getY();
            new WorldGenGravityHarvester().generate(this.world, this.rand, pos.add(0, posY + 2 + this.rand.nextInt(3), 0));
        }
        for (int i = 0; i < 8; ++i)
        {
            new WorldGenSpaceDungeons(MPBlocks.DIONA_ANCIENT_CHEST.getDefaultState(), MPBlocks.KOENTUS_COBBLESTONE.getDefaultState(), MPBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState()).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
        }
        if (this.rand.nextInt(250) == 0)
        {
            int posX = this.rand.nextInt(16) + 8;
            int posZ = this.rand.nextInt(16) + 8;
            int posY = this.world.getTopSolidOrLiquidBlock(pos.add(posX, 0, posZ)).getY();
            new WorldGenCrashedAlienProbe().generate(this.world, this.rand, pos.add(posX, posY, posZ));
        }
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.alienShipFeatureGenerator.generate(this.world, chunkX, chunkZ, null);
    }

    @Override
    public boolean isInsideStructure(World world, String name, BlockPos pos)
    {
        return "AlienShip".equals(name) && this.alienShipFeatureGenerator != null ? this.alienShipFeatureGenerator.isInsideStructure(pos) : false;
    }

    @Override
    @Nullable
    public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored)
    {
        return "AlienShip".equals(name) && this.alienShipFeatureGenerator != null ? this.alienShipFeatureGenerator.getNearestStructurePos(world, pos, findUnexplored) : null;
    }

    @Override
    protected IBlockState getTopBlock()
    {
        return MPBlocks.KOENTUS_REGOLITH.getDefaultState();
    }

    @Override
    protected IBlockState getSubBlock()
    {
        return MPBlocks.KOENTUS_FINE_REGOLITH.getDefaultState();
    }

    @Override
    protected IBlockState getStoneBlock()
    {
        return MPBlocks.KOENTUS_ROCK.getDefaultState();
    }
}
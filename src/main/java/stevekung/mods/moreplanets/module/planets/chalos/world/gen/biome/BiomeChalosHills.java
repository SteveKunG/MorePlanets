package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class BiomeChalosHills extends BiomeChalos
{
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150638_aH;

    public BiomeChalosHills()
    {
        super(ConfigManagerMP.idBiomeChalosHills);
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150638_aH = this.field_150635_aE;
        this.setTemperatureRainfall(0.2F, 0.3F);
        this.setHeight(new Height(1.0F, 0.5F));
        this.topBlock = ChalosBlocks.CHEESE_GRASS.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
        this.getBiomeDecorator().cheeseSporeFlowerPerChunk = 2;
        this.getBiomeDecorator().cheeseTallGrassPerChunk = 128;
        this.getBiomeDecorator().cheeseSporeTreePerChunk = 1;
        this.getBiomeDecorator().cheeseSporeStemPerChunk = 1;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noise)
    {
        this.topBlock = ChalosBlocks.CHEESE_GRASS.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_DIRT.getDefaultState();

        if (noise > 1.0D && this.field_150638_aH != this.field_150636_aF)
        {
            this.topBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
            this.fillerBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
        }
        this.genChalosBiomeTerrain(world, rand, chunkPrimer, chunkX, chunkZ, noise);
    }
}
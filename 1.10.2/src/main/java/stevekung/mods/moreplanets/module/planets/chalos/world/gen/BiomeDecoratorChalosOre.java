package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.EnumOreGen;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenCaveLiquids;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenMinableMP;

public class BiomeDecoratorChalosOre extends BiomeDecoratorMP
{
    private WorldGenerator dirtGen;
    private WorldGenerator diremsiumGen;
    private WorldGenerator zyptoriumGen;
    private WorldGenerator ironGen;
    private WorldGenerator cheeseGen;
    private WorldGenerator aluminumGen;
    private WorldGenerator tinGen;
    private WorldGenerator copperGen;

    public BiomeDecoratorChalosOre()
    {
        this.dirtGen = new WorldGenMinableMP(ChalosBlocks.CHEESE_DIRT.getDefaultState(), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), EnumOreGen.DIRT);
        this.diremsiumGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(2), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), 9);
        this.zyptoriumGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(3), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), 9);
        this.ironGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(4), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), EnumOreGen.IRON);
        this.cheeseGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(5), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), 4);
        this.tinGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(6), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(7), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), EnumOreGen.COPPER);
        this.aluminumGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_BLOCK.getStateFromMeta(8), ChalosBlocks.CHALOS_BLOCK.getDefaultState(), EnumOreGen.ALUMINUM);
    }

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;

        this.generateOre(this.zyptoriumGen, 8, 0, 24, world, rand);
        this.generateOre(this.diremsiumGen, 16, 0, 64, world, rand);
        this.generateOre(this.cheeseGen, 5, 0, 256, world, rand);
        this.generateOre(this.ironGen, EnumOreGen.IRON, world, rand);
        this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM, world, rand);
        this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
        this.generateOre(this.copperGen, EnumOreGen.COPPER, world, rand);
        this.generateOre(this.dirtGen, EnumOreGen.DIRT, world, rand);

        for (int j = 0; j < 50; ++j)
        {
            int y = rand.nextInt(rand.nextInt(248) + 8);
            new WorldGenCaveLiquids(ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK, ChalosBlocks.CHALOS_BLOCK, 0).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int j = 0; j < 20; ++j)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquids(Blocks.FLOWING_LAVA, ChalosBlocks.CHALOS_BLOCK, 0).generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
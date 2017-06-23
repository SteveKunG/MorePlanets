package stevekung.mods.moreplanets.module.planets.diona.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.feature.WorldGenInfectedCrystal;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.EnumOreGen;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenCaveLiquids;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenMinableMP;

public class BiomeDecoratorDiona extends BiomeDecoratorMP
{
    private WorldGenerator dirtGen;
    private WorldGenerator setroriumGen;
    private WorldGenerator illeniumGen;
    private WorldGenerator aluminumGen;
    private WorldGenerator tinGen;
    private WorldGenerator copperGen;
    private WorldGenerator wormEggGen;

    public BiomeDecoratorDiona()
    {
        this.dirtGen = new WorldGenMinableMP(DionaBlocks.DIONA_BLOCK.getStateFromMeta(1), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), EnumOreGen.DIRT);
        this.setroriumGen = new WorldGenMinableMP(DionaBlocks.DIONA_BLOCK.getStateFromMeta(4), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), 9);
        this.illeniumGen = new WorldGenMinableMP(DionaBlocks.DIONA_BLOCK.getStateFromMeta(5), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), 4);
        this.tinGen = new WorldGenMinableMP(DionaBlocks.DIONA_BLOCK.getStateFromMeta(6), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableMP(DionaBlocks.DIONA_BLOCK.getStateFromMeta(7), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), EnumOreGen.COPPER);
        this.aluminumGen = new WorldGenMinableMP(DionaBlocks.DIONA_BLOCK.getStateFromMeta(8), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), EnumOreGen.ALUMINUM);
        this.wormEggGen = new WorldGenMinableMP(DionaBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState(), DionaBlocks.DIONA_BLOCK.getStateFromMeta(2), 8);
    }

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;

        this.generateOre(this.illeniumGen, 8, 0, 24, world, rand);
        this.generateOre(this.setroriumGen, 16, 0, 64, world, rand);
        this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM, world, rand);
        this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
        this.generateOre(this.copperGen, EnumOreGen.COPPER, world, rand);
        this.generateOre(this.dirtGen, EnumOreGen.DIRT, world, rand);
        this.generateOre(this.wormEggGen, 8, 0, 64, world, rand);

        for (int i = 0; i < 50; ++i)
        {
            int y = rand.nextInt(rand.nextInt(248) + 8);
            new WorldGenCaveLiquids(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK, DionaBlocks.DIONA_BLOCK).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int i = 0; i < 20; ++i)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquids(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, DionaBlocks.DIONA_BLOCK).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int i = 0; i < 16; ++i)
        {
            int y = rand.nextInt(48);
            new WorldGenInfectedCrystal().generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
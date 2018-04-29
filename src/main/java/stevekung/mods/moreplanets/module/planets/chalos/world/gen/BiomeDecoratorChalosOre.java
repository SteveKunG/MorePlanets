package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.EnumOreGen;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenMinableMP;
import stevekung.mods.stevekunglib.world.gen.WorldGenCaveLiquid;

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
        this.dirtGen = new WorldGenMinableMP(ChalosBlocks.CHEESE_DIRT.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.DIRT);
        this.diremsiumGen = new WorldGenMinableMP(ChalosBlocks.DIREMSIUM_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), 9);
        this.zyptoriumGen = new WorldGenMinableMP(ChalosBlocks.ZYPTORIUM_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), 9);
        this.ironGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_IRON_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.IRON);
        this.cheeseGen = new WorldGenMinableMP(ChalosBlocks.CHEESE_MILK_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), 4);
        this.tinGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_TIN_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_COPPER_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.COPPER);
        this.aluminumGen = new WorldGenMinableMP(ChalosBlocks.CHALOS_ALUMINUM_ORE.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.ALUMINUM);
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
            new WorldGenCaveLiquid(ChalosBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int j = 0; j < 20; ++j)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquid(Blocks.FLOWING_LAVA.getDefaultState(), ChalosBlocks.CHALOS_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
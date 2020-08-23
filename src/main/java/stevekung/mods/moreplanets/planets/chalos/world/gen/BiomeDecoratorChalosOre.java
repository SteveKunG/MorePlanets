package stevekung.mods.moreplanets.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.stevekunglib.world.gen.EnumOreGen;
import stevekung.mods.stevekunglib.world.gen.WorldGenCaveLiquid;
import stevekung.mods.stevekunglib.world.gen.WorldGenMinableBase;

public class BiomeDecoratorChalosOre extends BiomeDecoratorMP
{
    private final WorldGenerator dirtGen;
    private final WorldGenerator diremsiumGen;
    private final WorldGenerator zyptoriumGen;
    private final WorldGenerator ironGen;
    private final WorldGenerator cheeseGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator tinGen;
    private final WorldGenerator copperGen;

    public BiomeDecoratorChalosOre()
    {
        this.dirtGen = new WorldGenMinableBase(MPBlocks.CHEESE_DIRT.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.DIRT);
        this.diremsiumGen = new WorldGenMinableBase(MPBlocks.DIREMSIUM_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), 9);
        this.zyptoriumGen = new WorldGenMinableBase(MPBlocks.ZYPTORIUM_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), 9);
        this.ironGen = new WorldGenMinableBase(MPBlocks.CHALOS_IRON_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.IRON);
        this.cheeseGen = new WorldGenMinableBase(MPBlocks.CHEESE_MILK_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), 4);
        this.tinGen = new WorldGenMinableBase(MPBlocks.CHALOS_TIN_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableBase(MPBlocks.CHALOS_COPPER_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.COPPER);
        this.aluminumGen = new WorldGenMinableBase(MPBlocks.CHALOS_ALUMINUM_ORE.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState(), EnumOreGen.ALUMINUM);
    }

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;

        this.generateOre(this.zyptoriumGen, 8, 0, 24, world, rand);
        this.generateOre(this.diremsiumGen, 16, 0, 64, world, rand);
        this.generateOre(this.cheeseGen, 5, 0, 256, world, rand);

        if (ConfigManagerMP.moreplanets_world_gen_settings.enableCommonOreGenAllPlanets || ConfigManagerMP.moreplanets_world_gen_settings.enableCommonChalosOre)
        {
            this.generateOre(this.ironGen, EnumOreGen.IRON, world, rand);
            this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM, world, rand);
            this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
            this.generateOre(this.copperGen, EnumOreGen.COPPER, world, rand);
        }

        this.generateOre(this.dirtGen, EnumOreGen.DIRT, world, rand);

        for (int j = 0; j < 50; ++j)
        {
            int y = rand.nextInt(rand.nextInt(248) + 8);
            new WorldGenCaveLiquid(MPBlocks.CHEESE_MILK_FLUID_BLOCK.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int j = 0; j < 20; ++j)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquid(Blocks.FLOWING_LAVA.getDefaultState(), MPBlocks.CHALOS_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
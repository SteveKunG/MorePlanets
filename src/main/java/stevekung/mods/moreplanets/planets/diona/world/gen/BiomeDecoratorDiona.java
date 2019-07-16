package stevekung.mods.moreplanets.planets.diona.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.world.gen.feature.WorldGenLargeInfectedCrystallized;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.stevekunglib.world.gen.EnumOreGen;
import stevekung.mods.stevekunglib.world.gen.WorldGenCaveLiquid;
import stevekung.mods.stevekunglib.world.gen.WorldGenMinableBase;

public class BiomeDecoratorDiona extends BiomeDecoratorMP
{
    private final WorldGenerator dirtGen;
    private final WorldGenerator setroriumGen;
    private final WorldGenerator illeniumGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator tinGen;
    private final WorldGenerator copperGen;
    private final WorldGenerator wormEggGen;

    public BiomeDecoratorDiona()
    {
        this.dirtGen = new WorldGenMinableBase(MPBlocks.DIONA_SUB_SURFACE_ROCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), EnumOreGen.DIRT);
        this.setroriumGen = new WorldGenMinableBase(MPBlocks.SETRORIUM_ORE.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), 9);
        this.illeniumGen = new WorldGenMinableBase(MPBlocks.ILLENIUM_ORE.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), 4);
        this.tinGen = new WorldGenMinableBase(MPBlocks.DIONA_TIN_ORE.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableBase(MPBlocks.DIONA_COPPER_ORE.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), EnumOreGen.COPPER);
        this.aluminumGen = new WorldGenMinableBase(MPBlocks.DIONA_ALUMINUM_ORE.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), EnumOreGen.ALUMINUM);
        this.wormEggGen = new WorldGenMinableBase(MPBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), 8);
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
            new WorldGenCaveLiquid(MPBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int i = 0; i < 20; ++i)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquid(MPBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (int i = 0; i < 16; ++i)
        {
            int y = rand.nextInt(48);
            new WorldGenLargeInfectedCrystallized().generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
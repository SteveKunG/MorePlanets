package stevekung.mods.moreplanets.planets.nibiru.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.BiomeGreenVeinFields;
import stevekung.mods.moreplanets.utils.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.stevekunglib.world.gen.EnumOreGen;
import stevekung.mods.stevekunglib.world.gen.WorldGenCaveLiquid;
import stevekung.mods.stevekunglib.world.gen.WorldGenMinableBase;

public class BiomeDecoratorNibiruOre extends BiomeDecoratorMP
{
    private WorldGenerator ironGen;
    private WorldGenerator coalGen;
    private WorldGenerator aluminumGen;
    private WorldGenerator tinGen;
    private WorldGenerator copperGen;
    private WorldGenerator lapisGen;
    private WorldGenerator dirtGen;
    private WorldGenerator infectedGravelGen;
    private WorldGenerator purifiedGravelGen;
    private WorldGenerator goldGen;
    private WorldGenerator diamondGen;
    private WorldGenerator siliconGen;
    private WorldGenerator inferumiteGen;
    private WorldGenerator oilGen;
    private WorldGenerator redstoneGen;

    public BiomeDecoratorNibiruOre()
    {
        this.dirtGen = new WorldGenMinableBase(MPBlocks.INFECTED_DIRT.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.DIRT);
        this.ironGen = new WorldGenMinableBase(MPBlocks.INFECTED_IRON_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.IRON);
        this.coalGen = new WorldGenMinableBase(MPBlocks.INFECTED_COAL_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.COAL);
        this.aluminumGen = new WorldGenMinableBase(MPBlocks.INFECTED_ALUMINUM_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.ALUMINUM);
        this.tinGen = new WorldGenMinableBase(MPBlocks.INFECTED_TIN_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableBase(MPBlocks.INFECTED_COPPER_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.COPPER);
        this.lapisGen = new WorldGenMinableBase(MPBlocks.INFECTED_LAPIS_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.LAPIS);
        this.goldGen = new WorldGenMinableBase(MPBlocks.INFECTED_GOLD_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.GOLD);
        this.diamondGen = new WorldGenMinableBase(MPBlocks.INFECTED_DIAMOND_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.DIAMOND);
        this.siliconGen = new WorldGenMinableBase(MPBlocks.INFECTED_SILICON_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.SILICON);
        this.inferumiteGen = new WorldGenMinableBase(MPBlocks.INFERUMITE_CRYSTAL_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), 4);
        this.redstoneGen = new WorldGenMinableBase(MPBlocks.INFECTED_REDSTONE_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.REDSTONE);
        this.oilGen = new WorldGenMinableBase(MPBlocks.OIL_ORE.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), 4);
        this.infectedGravelGen = new WorldGenMinableBase(MPBlocks.INFECTED_GRAVEL.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.GRAVEL);
        this.purifiedGravelGen = new WorldGenMinableBase(MPBlocks.PURIFIED_GRAVEL.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.GRAVEL);
    }

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int i;
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;

        this.generateOre(this.dirtGen, EnumOreGen.DIRT, world, rand);
        this.generateOre(this.coalGen, EnumOreGen.COAL, world, rand);
        this.generateOre(this.ironGen, EnumOreGen.IRON, world, rand);
        this.generateOre(this.goldGen, EnumOreGen.GOLD, world, rand);
        this.generateOre(this.redstoneGen, EnumOreGen.REDSTONE, world, rand);
        this.generateOre(this.diamondGen, EnumOreGen.DIAMOND, world, rand);
        this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
        this.generateOre(this.copperGen, EnumOreGen.COPPER, world, rand);
        this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM, world, rand);
        this.generateOre(this.siliconGen, EnumOreGen.SILICON, world, rand);
        this.generateOre(biome instanceof BiomeGreenVeinFields ? this.purifiedGravelGen : this.infectedGravelGen, EnumOreGen.GRAVEL, world, rand);
        this.generateOre(this.inferumiteGen, 16, 0, 64, world, rand);
        this.generateOre(this.oilGen, 3, 0, 36, world, rand);
        this.generateLapis(this.lapisGen, EnumOreGen.LAPIS, world, rand);

        for (i = 0; i < 50; ++i)
        {
            int y = rand.nextInt(rand.nextInt(248) + 8);
            new WorldGenCaveLiquid(MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (i = 0; i < 20; ++i)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquid(Blocks.FLOWING_LAVA.getDefaultState(), MPBlocks.NIBIRU_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
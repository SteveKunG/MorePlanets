package stevekung.mods.moreplanets.module.planets.nibiru.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.EnumOreGen;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenMinableMP;
import stevekung.mods.stevekunglib.world.gen.WorldGenCaveLiquid;

public class BiomeDecoratorNibiruOre extends BiomeDecoratorMP
{
    private WorldGenerator ironGen;
    private WorldGenerator coalGen;
    private WorldGenerator aluminumGen;
    private WorldGenerator tinGen;
    private WorldGenerator copperGen;
    private WorldGenerator lapisGen;
    private WorldGenerator dirtGen;
    private WorldGenerator gravelGen;
    private WorldGenerator goldGen;
    private WorldGenerator diamondGen;
    private WorldGenerator siliconGen;
    private WorldGenerator inferumiteGen;
    private WorldGenerator oilGen;
    private WorldGenerator redstoneGen;

    public BiomeDecoratorNibiruOre()
    {
        this.dirtGen = new WorldGenMinableMP(NibiruBlocks.INFECTED_DIRT.getDefaultState(), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.DIRT);
        this.ironGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(1), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.IRON);
        this.coalGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(0), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.COAL);
        this.aluminumGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(7), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.ALUMINUM);
        this.tinGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(9), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(8), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.COPPER);
        this.lapisGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(5), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.LAPIS);
        this.goldGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(2), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.GOLD);
        this.diamondGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(3), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.DIAMOND);
        this.siliconGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(10), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.SILICON);
        this.inferumiteGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(11), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), 4);
        this.redstoneGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(4), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.REDSTONE);
        this.oilGen = new WorldGenMinableMP(NibiruBlocks.OIL_ORE.getDefaultState(), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), 4);
        this.gravelGen = new WorldGenMinableMP(NibiruBlocks.INFECTED_GRAVEL.getDefaultState(), NibiruBlocks.NIBIRU_ROCK.getDefaultState(), EnumOreGen.GRAVEL);
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
        this.generateOre(this.gravelGen, EnumOreGen.GRAVEL, world, rand);
        this.generateOre(this.inferumiteGen, 16, 0, 64, world, rand);
        this.generateOre(this.oilGen, 3, 0, 36, world, rand);
        this.generateLapis(this.lapisGen, EnumOreGen.LAPIS, world, rand);

        for (i = 0; i < 50; ++i)
        {
            int y = rand.nextInt(rand.nextInt(248) + 8);
            new WorldGenCaveLiquid(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), NibiruBlocks.NIBIRU_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (i = 0; i < 20; ++i)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquid(Blocks.FLOWING_LAVA.getDefaultState(), NibiruBlocks.NIBIRU_ROCK.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
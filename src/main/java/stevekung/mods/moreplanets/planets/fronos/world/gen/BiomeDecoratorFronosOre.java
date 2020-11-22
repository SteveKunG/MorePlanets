package stevekung.mods.moreplanets.planets.fronos.world.gen;

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

public class BiomeDecoratorFronosOre extends BiomeDecoratorMP
{
    private final WorldGenerator ironGen;
    private final WorldGenerator coalGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator tinGen;
    private final WorldGenerator copperGen;
    private final WorldGenerator lapisGen;
    private final WorldGenerator dirtGen;
    private final WorldGenerator gravelGen;
    private final WorldGenerator goldGen;
    private final WorldGenerator diamondGen;
    private final WorldGenerator siliconGen;
    private final WorldGenerator redstoneGen;

    public BiomeDecoratorFronosOre()
    {
        this.dirtGen = new WorldGenMinableBase(MPBlocks.FRONOS_DIRT.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.DIRT);
        this.ironGen = new WorldGenMinableBase(MPBlocks.FRONOS_IRON_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.IRON);
        this.coalGen = new WorldGenMinableBase(MPBlocks.FRONOS_COAL_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.COAL);
        this.aluminumGen = new WorldGenMinableBase(MPBlocks.FRONOS_ALUMINUM_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.ALUMINUM);
        this.tinGen = new WorldGenMinableBase(MPBlocks.FRONOS_TIN_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableBase(MPBlocks.FRONOS_COPPER_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.COPPER);
        this.lapisGen = new WorldGenMinableBase(MPBlocks.FRONOS_LAPIS_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.LAPIS);
        this.goldGen = new WorldGenMinableBase(MPBlocks.FRONOS_GOLD_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.GOLD);
        this.diamondGen = new WorldGenMinableBase(MPBlocks.FRONOS_DIAMOND_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.DIAMOND);
        this.siliconGen = new WorldGenMinableBase(MPBlocks.FRONOS_SILICON_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.SILICON);
        this.redstoneGen = new WorldGenMinableBase(MPBlocks.FRONOS_REDSTONE_ORE.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.REDSTONE);
        this.gravelGen = new WorldGenMinableBase(Blocks.GRAVEL.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState(), EnumOreGen.GRAVEL);
    }

    @Override
    protected void generate(Biome biome, World world, Random rand)
    {
        int i;
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;

        this.generateOre(this.dirtGen, EnumOreGen.DIRT, world, rand);

        if (ConfigManagerMP.moreplanets_world_gen_settings.enableCommonOreGenAllPlanets || ConfigManagerMP.moreplanets_world_gen_settings.enableCommonFronosOre)
        {
            this.generateOre(this.coalGen, EnumOreGen.COAL, world, rand);
            this.generateOre(this.ironGen, EnumOreGen.IRON, world, rand);
            this.generateOre(this.goldGen, EnumOreGen.GOLD, world, rand);
            this.generateOre(this.redstoneGen, EnumOreGen.REDSTONE, world, rand);
            this.generateOre(this.diamondGen, EnumOreGen.DIAMOND, world, rand);
            this.generateOre(this.tinGen, EnumOreGen.TIN, world, rand);
            this.generateOre(this.copperGen, EnumOreGen.COPPER, world, rand);
            this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM, world, rand);
            this.generateOre(this.siliconGen, EnumOreGen.SILICON, world, rand);
            this.generateLapis(this.lapisGen, EnumOreGen.LAPIS, world, rand);
        }

        this.generateOre(this.gravelGen, EnumOreGen.GRAVEL, world, rand);

        for (i = 0; i < 50; ++i)
        {
            int y = rand.nextInt(rand.nextInt(248) + 8);
            new WorldGenCaveLiquid(Blocks.WATER.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
        for (i = 0; i < 20; ++i)
        {
            int y = rand.nextInt(rand.nextInt(rand.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquid(Blocks.FLOWING_LAVA.getDefaultState(), MPBlocks.FRONOS_STONE.getDefaultState()).generate(world, rand, this.chunkPos.add(x, y, z));
        }
    }
}
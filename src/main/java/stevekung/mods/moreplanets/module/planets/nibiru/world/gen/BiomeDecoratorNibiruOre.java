package stevekung.mods.moreplanets.module.planets.nibiru.world.gen;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.world.gen.feature.BiomeDecoratorMP;
import stevekung.mods.moreplanets.util.world.gen.feature.EnumOreGen;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenCaveLiquids;
import stevekung.mods.moreplanets.util.world.gen.feature.WorldGenMinableMP;

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
        this.dirtGen = new WorldGenMinableMP(NibiruBlocks.INFECTED_DIRT.getDefaultState(), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.DIRT);
        this.ironGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(1), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.IRON);
        this.coalGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(0), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.COAL);
        this.aluminumGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(7), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.ALUMINUM);
        this.tinGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(9), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.TIN);
        this.copperGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(8), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.COPPER);
        this.lapisGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(5), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.LAPIS);
        this.goldGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(2), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.GOLD);
        this.diamondGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(3), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.DIAMOND);
        this.siliconGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(10), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.SILICON);
        this.inferumiteGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(11), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), 4);
        this.redstoneGen = new WorldGenMinableMP(NibiruBlocks.NIBIRU_ORE.getStateFromMeta(4), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.REDSTONE);
        this.oilGen = new WorldGenMinableMP(NibiruBlocks.OIL_ORE.getDefaultState(), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), 4);
        this.gravelGen = new WorldGenMinableMP(NibiruBlocks.INFECTED_GRAVEL.getDefaultState(), NibiruBlocks.NIBIRU_BLOCK.getDefaultState(), EnumOreGen.GRAVEL);
    }

    @Override
    protected void generate(BiomeGenBase biome)
    {
        int i;
        int x = this.randomGenerator.nextInt(16) + 8;
        int z = this.randomGenerator.nextInt(16) + 8;

        this.generateOre(this.dirtGen, EnumOreGen.DIRT);
        this.generateOre(this.coalGen, EnumOreGen.COAL);
        this.generateOre(this.ironGen, EnumOreGen.IRON);
        this.generateOre(this.goldGen, EnumOreGen.GOLD);
        this.generateOre(this.redstoneGen, EnumOreGen.REDSTONE);
        this.generateOre(this.diamondGen, EnumOreGen.DIAMOND);
        this.generateOre(this.tinGen, EnumOreGen.TIN);
        this.generateOre(this.copperGen, EnumOreGen.COPPER);
        this.generateOre(this.aluminumGen, EnumOreGen.ALUMINUM);
        this.generateOre(this.siliconGen, EnumOreGen.SILICON);
        this.generateOre(this.gravelGen, EnumOreGen.GRAVEL);
        this.generateOre(this.inferumiteGen, 16, 0, 64);
        this.generateOre(this.oilGen, 3, 0, 36);
        this.generateLapis(this.lapisGen, EnumOreGen.LAPIS);

        for (i = 0; i < 50; ++i)
        {
            int y = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
            new WorldGenCaveLiquids(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK, NibiruBlocks.NIBIRU_BLOCK, 0).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
        }
        for (i = 0; i < 20; ++i)
        {
            int y = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
            new WorldGenCaveLiquids(Blocks.flowing_lava, NibiruBlocks.NIBIRU_BLOCK, 0).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
        }
    }
}
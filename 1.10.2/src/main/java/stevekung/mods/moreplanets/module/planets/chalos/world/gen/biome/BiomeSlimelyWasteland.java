package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class BiomeSlimelyWasteland extends BiomeChalos
{
    public BiomeSlimelyWasteland()
    {
        super(ConfigManagerMP.idBiomeSlimelyWasteland);
        this.setHeight(new Height(0.2F, 0.2F));
        this.topBlock = ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
    }
}
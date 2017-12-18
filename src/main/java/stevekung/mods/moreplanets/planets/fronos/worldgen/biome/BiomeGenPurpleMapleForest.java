/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BiomeGenPurpleMapleForest extends BiomeGenBaseFronos
{
    public BiomeGenPurpleMapleForest()
    {
        super(ConfigManagerMP.idPurpleMapleForestBiome);
        this.enableRain = true;
        this.rainfall = 0.5F;
        this.temperature = 0.5F;
        this.topBlock = FronosBlocks.purple_grass;
        this.topMeta = 0;
        this.fillerBlock = FronosBlocks.fronos_dirt;
        this.fillerMeta = 0;
        this.stoneBlock = FronosBlocks.fronos_block;
        this.stoneMeta = 0;
        this.getBiomeDecorator().purpleTallGrassPerChunk = 200;
        this.getBiomeDecorator().deadBushPerChunk = 1;
        this.getBiomeDecorator().reedsPerChunk = 200;
        this.getBiomeDecorator().normalSandPerChunk = 3;
        this.getBiomeDecorator().fronosSandPerChunk = 2;
        this.getBiomeDecorator().whiteSandPerChunk = 2;
        this.getBiomeDecorator().cheeseSandPerChunk = 1;
        this.getBiomeDecorator().gravelPerChunk = 1;
        this.getBiomeDecorator().clayPerChunk = 1;
        this.getBiomeDecorator().lakesPerChunk = 1;
        this.getBiomeDecorator().littleSunFlowerPerChunk = 1;
        this.getBiomeDecorator().dandelionPerChunk = 2;
        this.getBiomeDecorator().poppyPerChunk = 2;
        this.getBiomeDecorator().purpleMapleTreePerChunk = 10;
        this.getBiomeDecorator().redMapleTreePerChunk = -999;
        this.getBiomeDecorator().yellowMapleTreePerChunk = -999;
        this.getBiomeDecorator().mapleIvyPerChunk = 12;
        this.getBiomeDecorator().bluePoisonMushroomPerChunk = 8;
        this.getBiomeDecorator().purpleSpikeFlowerPerChunk = 8;
    }
}
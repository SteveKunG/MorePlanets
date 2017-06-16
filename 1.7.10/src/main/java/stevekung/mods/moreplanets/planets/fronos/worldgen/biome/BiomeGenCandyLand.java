/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BiomeGenCandyLand extends BiomeGenBaseFronos
{
    public BiomeGenCandyLand()
    {
        super(ConfigManagerMP.idCandyLandBiome);
        this.enableRain = true;
        this.rainfall = 0.5F;
        this.temperature = 0.5F;
        this.topBlock = FronosBlocks.frosted_cake;
        this.topMeta = 3;
        this.fillerBlock = FronosBlocks.frosted_cake;
        this.fillerMeta = 0;
        this.stoneBlock = FronosBlocks.fronos_block;
        this.stoneMeta = 0;
        this.getBiomeDecorator().reedsPerChunk = -999;
        this.getBiomeDecorator().normalSandPerChunk = -999;
        this.getBiomeDecorator().fronosSandPerChunk = -999;
        this.getBiomeDecorator().whiteSandPerChunk = -999;
        this.getBiomeDecorator().cheeseSandPerChunk = -999;
        this.getBiomeDecorator().gravelPerChunk = -999;
        this.getBiomeDecorator().clayPerChunk = -999;
        this.getBiomeDecorator().lakesPerChunk = -999;
        this.getBiomeDecorator().coralPerChunk = -999;
        this.getBiomeDecorator().spaceShellPerChunk = -999;
        this.getBiomeDecorator().candyFlowerPerChunk = 64;
        this.getBiomeDecorator().candyCanePerChunk = 256;
    }
}
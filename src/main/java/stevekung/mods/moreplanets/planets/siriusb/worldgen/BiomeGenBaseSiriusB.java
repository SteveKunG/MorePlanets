/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.world.biome.BiomeGenBaseMP;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;

public class BiomeGenBaseSiriusB extends BiomeGenBaseMP
{
    public static BiomeGenBase siriusB = new BiomeGenBaseSiriusB().setBiomeName("Sirius B");

    public BiomeGenBaseSiriusB()
    {
        super(ConfigManagerMP.idSiriusBBiome);
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySiriusBlaze.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySiriusCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySiriusMagmaCube.class, 100, 4, 4));
    }
}
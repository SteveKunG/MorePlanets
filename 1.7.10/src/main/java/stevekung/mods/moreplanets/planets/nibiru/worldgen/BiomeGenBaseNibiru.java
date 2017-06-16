/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.world.biome.BiomeGenBaseMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityGiantWorm;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.tree.WorldGenNibiruFruitTree;

public class BiomeGenBaseNibiru extends BiomeGenBaseMP
{
    public static BiomeGenBase nibiru = new BiomeGenBaseNibiru().setBiomeName("Nibiru");

    public BiomeGenBaseNibiru()
    {
        super(ConfigManagerMP.idNibiruBiome);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedWitch.class, 5, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityGiantWorm.class, 100, 2, 4));
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand)
    {
        if (rand.nextInt(1) == 0)
        {
            return new WorldGenNibiruFruitTree(NibiruBlocks.nibiru_log, NibiruBlocks.ancient_dark_leaves, 0, true);
        }
        else if (rand.nextInt(2) == 0)
        {
            return new WorldGenNibiruFruitTree(NibiruBlocks.nibiru_log, NibiruBlocks.ancient_dark_leaves, 1, true);
        }
        return null;
    }
}
package stevekung.mods.moreplanets.util.world.gen.biome;

import java.util.LinkedList;

import micdoodle8.mods.galacticraft.api.world.IMobSpawnBiome;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;

public class BiomeBaseMP extends Biome implements IMobSpawnBiome
{
    public BiomeBaseMP(int id)
    {
        super(id);
        this.rainfall = 0.0F;
        this.color = 747097;
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        for (SpawnListEntry entry : mobInfo)
        {
            Class<?> mobClass = entry.entityClass;

            if (EntityWaterMob.class.isAssignableFrom(mobClass))
            {
                this.spawnableWaterCreatureList.add(entry);
            }
            else if (EntityAmbientCreature.class.isAssignableFrom(mobClass))
            {
                this.spawnableCaveCreatureList.add(entry);
            }
            else if (EntityMob.class.isAssignableFrom(mobClass))
            {
                this.spawnableMonsterList.add(entry);
            }
            else
            {
                this.spawnableCreatureList.add(entry);
            }
        }
    }
}
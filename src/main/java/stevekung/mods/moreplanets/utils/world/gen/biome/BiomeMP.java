package stevekung.mods.moreplanets.utils.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.world.IMobSpawnBiome;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeMP extends Biome implements IMobSpawnBiome
{
    public BiomeMP(BiomeProperties prop)
    {
        super(prop);
    }

    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return null;
    }

    @Override
    public void plantFlower(World world, Random rand, BlockPos pos)
    {
        if (this.flowers.isEmpty())
        {
            return;
        }

        FlowerEntry flower = WeightedRandom.getRandomItem(rand, this.flowers);

        if (flower == null || flower.state == null || !flower.state.getBlock().canPlaceBlockAt(world, pos))
        {
            return;
        }
        world.setBlockState(pos, flower.state, 3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 747097;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        for (Biome.SpawnListEntry entry : mobInfo)
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
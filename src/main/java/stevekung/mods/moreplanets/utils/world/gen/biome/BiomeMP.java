package stevekung.mods.moreplanets.utils.world.gen.biome;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeMP extends Biome
{
    public BiomeMP(BiomeProperties prop)
    {
        super(prop);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEvolvedWitch.class, 5, 1, 1));
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
        return -8798118;
    }

    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return null;
    }
}
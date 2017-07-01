package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;

public class RoomSpawnerNibiru extends RoomEmptyNibiru
{
    public RoomSpawnerNibiru() {}

    public RoomSpawnerNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, sizeX, sizeY, sizeZ, entranceDir);
    }

    public RoomSpawnerNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, rand.nextInt(4) + 6, configuration.getRoomHeight(), rand.nextInt(4) + 6, entranceDir);
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox boundingBox)
    {
        if (super.addComponentParts(world, rand, boundingBox))
        {
            for (int i = 1; i <= this.sizeX - 1; ++i)
            {
                for (int j = 1; j <= this.sizeY - 1; ++j)
                {
                    for (int k = 1; k <= this.sizeZ - 1; ++k)
                    {
                        if (rand.nextFloat() < 0.025F)
                        {
                            this.setBlockState(world, this.configuration.getWebBlock(), i, j, k, boundingBox);
                        }
                    }
                }
            }

            this.setBlockState(world, Blocks.MOB_SPAWNER.getDefaultState(), 1, 0, 1, boundingBox);
            this.setBlockState(world, Blocks.MOB_SPAWNER.getDefaultState(), this.sizeX - 1, 0, this.sizeZ - 1, boundingBox);
            BlockPos blockpos = new BlockPos(this.getXWithOffset(1, 1), this.getYWithOffset(0), this.getZWithOffset(1, 1));
            TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(blockpos);

            if (spawner != null)
            {
                spawner.getSpawnerBaseLogic().setEntityId(this.getRandomMob(rand));
            }

            blockpos = new BlockPos(this.getXWithOffset(this.sizeX - 1, this.sizeZ - 1), this.getYWithOffset(0), this.getZWithOffset(this.sizeX - 1, this.sizeZ - 1));
            spawner = (TileEntityMobSpawner) world.getTileEntity(blockpos);

            if (spawner != null)
            {
                spawner.getSpawnerBaseLogic().setEntityId(this.getRandomMob(rand));
            }
            return true;
        }
        return false;
    }

    protected ResourceLocation getRandomMob(Random rand)
    {
        switch (rand.nextInt(6))
        {
        case 0:
        default:
            return new ResourceLocation("moreplanets:infected_zombie");
        case 1:
            return new ResourceLocation("moreplanets:infected_creeper");
        case 2:
            return new ResourceLocation("moreplanets:infected_skeleton");
        case 3:
            return new ResourceLocation("galacticraftcore:evolved_spider");
        case 4:
            return new ResourceLocation("galacticraftcore:evolved_creeper");
        case 5:
            return new ResourceLocation("galacticraftcore:evolved_skeleton");
        case 6:
            return new ResourceLocation("galacticraftcore:evolved_zombie");
        }
    }
}
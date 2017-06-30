package stevekung.mods.moreplanets.util.world.gen.dungeon;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class RoomSpawnerMP extends RoomEmptyMP
{
    public RoomSpawnerMP() {}

    public RoomSpawnerMP(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, sizeX, sizeY, sizeZ, entranceDir);
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
                        if (rand.nextFloat() < 0.05F)
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
                spawner.getSpawnerBaseLogic().setEntityName(this.getRandomMob(rand));
            }

            blockpos = new BlockPos(this.getXWithOffset(this.sizeX - 1, this.sizeZ - 1), this.getYWithOffset(0), this.getZWithOffset(this.sizeX - 1, this.sizeZ - 1));
            spawner = (TileEntityMobSpawner) world.getTileEntity(blockpos);

            if (spawner != null)
            {
                spawner.getSpawnerBaseLogic().setEntityName(this.getRandomMob(rand));
            }
            return true;
        }
        return false;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
        super.readStructureFromNBT(tagCompound);
    }

    protected String getRandomMob(Random rand)
    {
        switch (rand.nextInt(4))
        {
        case 0:
            return "galacticraftcore.evolved_spider";
        case 1:
            return "galacticraftcore.evolved_creeper";
        case 2:
            return "galacticraftcore.evolved_skeleton";
        case 3:
        default:
            return "galacticraftcore.evolved_zombie";
        }
    }
}
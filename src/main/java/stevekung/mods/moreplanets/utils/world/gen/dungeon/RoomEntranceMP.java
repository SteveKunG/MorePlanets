package stevekung.mods.moreplanets.utils.world.gen.dungeon;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class RoomEntranceMP extends SizedPieceMP
{
    public RoomEntranceMP() {}

    public RoomEntranceMP(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ)
    {
        super(configuration, rand.nextInt(4) + 6, 12, rand.nextInt(4) + 6, EnumFacing.Plane.HORIZONTAL.random(rand));
        this.setCoordBaseMode(EnumFacing.SOUTH);
        int sX = this.sizeX / 2;
        int sZ = this.sizeZ / 2;
        this.boundingBox = new StructureBoundingBox(blockPosX - sX, configuration.getYPosition(), blockPosZ - sZ, blockPosX - sX + this.sizeX, configuration.getYPosition() + this.sizeY, blockPosZ - sZ + this.sizeZ);
        LoggerMP.debug("Generating boss dungeon at {} {} {}", blockPosX, configuration.getYPosition() + 1, blockPosZ);
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        for (int i = 0; i <= this.sizeX; i++)
        {
            for (int j = 0; j <= this.sizeY; j++)
            {
                for (int k = 0; k <= this.sizeZ; k++)
                {
                    if (i == 0 || i == this.sizeX || j == 0 || k == 0 || k == this.sizeZ)
                    {
                        this.setBlockState(world, this.configuration.getBrickBlock(), i, j, k, this.boundingBox);
                    }
                    else
                    {
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), i, j, k, this.boundingBox);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public PieceMP getNextPiece(DungeonStartMP startPiece, Random rand)
    {
        return this.getCorridor(rand, startPiece, 10, false);
    }
}
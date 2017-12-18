package stevekung.mods.moreplanets.util.world.gen.dungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.util.MPLog;

public class RoomEntranceMP extends SizedPieceMP
{
    private int range = 4;

    public RoomEntranceMP() {}

    public RoomEntranceMP(World world, DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ)
    {
        super(configuration, rand.nextInt(4) + 6, rand.nextInt(2) + 5, rand.nextInt(4) + 6, EnumFacing.Plane.HORIZONTAL.random(rand));
        this.coordBaseMode = EnumFacing.SOUTH;
        this.boundingBox = new StructureBoundingBox(blockPosX - this.range, configuration.getYPosition(), blockPosZ - this.range, blockPosX + this.range, 150, blockPosZ + this.range);
        MPLog.debug("Generating boss dungeon at " + blockPosX + " " + Integer.valueOf(configuration.getYPosition() + 1) + " " + blockPosZ);
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        IBlockState block1;
        int maxLevel = 0;

        for (int i = -this.range; i <= this.range; i++)
        {
            for (int k = -this.range; k <= this.range; k++)
            {
                int j = this.boundingBox.getYSize();

                while (j >= 0)
                {
                    j--;
                    Block block = this.getBlockStateFromPos(worldIn, i + this.range, j, k + this.range, this.boundingBox).getBlock();

                    if (Blocks.air != block && block != null)
                    {
                        break;
                    }
                }
                maxLevel = Math.max(maxLevel, j + 3);
            }
        }

        int startX = this.range - this.sizeX / 2;
        int startZ = this.range - this.sizeZ / 2;
        int endX = this.range + this.sizeX / 2;
        int endZ = this.range + this.sizeZ / 2;

        for (int i = startX; i <= endX; i++)
        {
            for (int j = 0; j <= this.sizeY; j++)
            {
                for (int k = startZ; k <= endZ; k++)
                {
                    if (i == startX || i == endX || j == 0 || j == this.sizeY || k == startZ || k == endZ)
                    {
                        this.setBlockState(worldIn, this.configuration.getBrickBlock(), i, j, k, this.boundingBox);
                    }
                    else
                    {
                        this.setBlockState(worldIn, Blocks.air.getDefaultState(), i, j, k, this.boundingBox);
                    }
                }
            }
        }

        for (int i = -this.range; i < this.range; i++)
        {
            for (int k = -this.range; k < this.range; k++)
            {
                double xDev = i / 10D;
                double zDev = k / 10D;
                double distance = xDev * xDev + zDev * zDev;
                int depth = (int) Math.abs(0.5 / (distance + .00001D));
                int helper = 0;

                for (int j = maxLevel; j > 1 && helper <= depth; j--)
                {
                    block1 = this.getBlockStateFromPos(worldIn, i + this.range, j, k + this.range, this.boundingBox);

                    if (block1 == this.configuration.getBrickBlock() || j != this.sizeY)
                    {
                        this.setBlockState(worldIn, Blocks.air.getDefaultState(), i + this.range, j, k + this.range, this.boundingBox);
                        helper++;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public PieceMP getNextPiece(DungeonStartMP startPiece, Random rand)
    {
        if (startPiece.attachedComponents.isEmpty())
        {
            return this.getCorridor(rand, startPiece, 10, false);
        }
        return null;
    }

    @Override
    protected StructureBoundingBox getExtension(EnumFacing direction, int length, int width)
    {
        int blockX, blockZ, sizeX, sizeZ;
        int startX = this.boundingBox.minX + this.range - this.sizeX / 2;
        int startZ = this.boundingBox.minZ + this.range - this.sizeZ / 2;
        int endX = this.boundingBox.minX + this.range + this.sizeX / 2;
        int endZ = this.boundingBox.minZ + this.range + this.sizeZ / 2;

        switch (direction)
        {
        case NORTH:
            sizeX = width;
            sizeZ = length;
            blockX = startX + (endX - startX) / 2 - sizeX / 2;
            blockZ = startZ - sizeZ;
            break;
        case EAST:
            sizeX = length;
            sizeZ = width;
            blockX = endX;
            blockZ = startZ + (endZ - startZ) / 2 - sizeZ / 2;
            break;
        case SOUTH:
            sizeX = width;
            sizeZ = length;
            blockX = startX + (endX - startX) / 2 - sizeX / 2;
            blockZ = endZ;
            break;
        case WEST:
        default:
            sizeX = length;
            sizeZ = width;
            blockX = startX - sizeX;
            blockZ = startZ + (endZ - startZ) / 2 - sizeZ / 2;
            break;
        }
        return new StructureBoundingBox(blockX, blockZ, blockX + sizeX, blockZ + sizeZ);
    }
}
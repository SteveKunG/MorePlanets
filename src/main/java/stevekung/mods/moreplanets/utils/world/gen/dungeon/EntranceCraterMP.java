package stevekung.mods.moreplanets.utils.world.gen.dungeon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class EntranceCraterMP extends SizedPieceMP
{
    private final int range = 16;

    public EntranceCraterMP() {}

    public EntranceCraterMP(World world, DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ)
    {
        super(configuration, rand.nextInt(4) + 6, 12, rand.nextInt(4) + 6, EnumFacing.Plane.HORIZONTAL.random(rand));
        this.setCoordBaseMode(EnumFacing.SOUTH);
        this.boundingBox = new StructureBoundingBox(blockPosX - this.range, configuration.getYPosition() + 11, blockPosZ - this.range, blockPosX + this.range, 150, blockPosZ + this.range);
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        int maxLevel = 0;

        for (int i = -this.range; i <= this.range; i++)
        {
            for (int k = -this.range; k <= this.range; k++)
            {
                int j = 150;
                int x = this.getXWithOffset(i + this.range, k + this.range);
                int z = this.getZWithOffset(i + this.range, k + this.range);

                while (j >= 0)
                {
                    j--;

                    int y = this.getYWithOffset(j);
                    BlockPos blockpos = new BlockPos(x, y, z);
                    Block block = world.getBlockState(blockpos).getBlock();

                    if (Blocks.AIR != block)
                    {
                        break;
                    }
                }
                maxLevel = Math.max(maxLevel, j + 3);
            }
        }

        Mirror mirror = Mirror.NONE;
        Rotation rotation = Rotation.NONE;

        if (this.getCoordBaseMode() != null)
        {
            switch (this.getCoordBaseMode())
            {
            case SOUTH:
                mirror = Mirror.LEFT_RIGHT;
                break;
            case WEST:
                mirror = Mirror.LEFT_RIGHT;
                rotation = Rotation.CLOCKWISE_90;
                break;
            case EAST:
                rotation = Rotation.CLOCKWISE_90;
                break;
            default:
                break;
            }
        }

        for (int i = -this.range; i < this.range; i++)
        {
            for (int k = -this.range; k < this.range; k++)
            {
                double xDev = i / 20D;
                double zDev = k / 20D;
                double distance = xDev * xDev + zDev * zDev;
                int depth = (int) Math.abs(0.5 / (distance + .00001D));
                int helper = 0;

                for (int j = maxLevel; j > 1 && helper <= depth; j--)
                {
                    BlockPos blockpos = new BlockPos(this.getXWithOffset(i + this.range, k + this.range), this.getYWithOffset(j), this.getZWithOffset(i + this.range, k + this.range));
                    IBlockState state = Blocks.AIR.getDefaultState();

                    if (mirror != Mirror.NONE)
                    {
                        state = state.withMirror(mirror);
                    }
                    if (rotation != Rotation.NONE)
                    {
                        state = state.withRotation(rotation);
                    }
                    world.setBlockState(blockpos, state, 2);
                    helper++;
                }
            }
        }
        return true;
    }

    @Override
    public PieceMP getNextPiece(DungeonStartMP startPiece, Random rand)
    {
        return new RoomEntranceMP(this.configuration, rand, this.boundingBox.minX + this.boundingBox.getXSize() / 2, this.boundingBox.minZ + this.boundingBox.getZSize() / 2);
    }
}
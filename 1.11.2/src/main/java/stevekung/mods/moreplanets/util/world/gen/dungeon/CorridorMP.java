package stevekung.mods.moreplanets.util.world.gen.dungeon;

import java.lang.reflect.Constructor;
import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class CorridorMP extends SizedPieceMP
{
    public CorridorMP() {}

    public CorridorMP(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing direction)
    {
        super(configuration, sizeX, sizeY, sizeZ, direction);
        this.setCoordBaseMode(EnumFacing.SOUTH);
        this.boundingBox = new StructureBoundingBox(blockPosX, configuration.getYPosition(), blockPosZ, blockPosX + sizeX, configuration.getYPosition() + sizeY, blockPosZ + sizeZ);
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
    {
        for (int i = 0; i < this.boundingBox.getXSize(); i++)
        {
            for (int j = 0; j < this.boundingBox.getYSize(); j++)
            {
                for (int k = 0; k < this.boundingBox.getZSize(); k++)
                {
                    if (this.getDirection().getAxis() == EnumFacing.Axis.Z && (i == 0 || i == this.boundingBox.getXSize() - 1) || j == 0 || j == this.boundingBox.getYSize() - 1 || this.getDirection().getAxis() == EnumFacing.Axis.X && (k == 0 || k == this.boundingBox.getZSize() - 1))
                    {
                        this.setBlockState(world, this.configuration.getBrickBlock(), i, j, k, this.boundingBox);
                    }
                    else
                    {
                        if (j == this.boundingBox.getYSize() - 2)
                        {
                            if (this.getDirection().getAxis() == EnumFacing.Axis.Z && (k + 1) % 4 == 0 && (i == 1 || i == this.boundingBox.getXSize() - 2))
                            {
                                this.setBlockState(world, this.configuration.getTorchBlock().withProperty(BlockTorch.FACING, i == 1 ? EnumFacing.WEST.getOpposite() : EnumFacing.EAST.getOpposite()), i, j, k, this.boundingBox);
                                continue;
                            }
                            else if (this.getDirection().getAxis() == EnumFacing.Axis.X && (i + 1) % 4 == 0 && (k == 1 || k == this.boundingBox.getZSize() - 2))
                            {
                                this.setBlockState(world, this.configuration.getTorchBlock().withProperty(BlockTorch.FACING, k == 1 ? EnumFacing.NORTH.getOpposite() : EnumFacing.SOUTH.getOpposite()), i, j, k, this.boundingBox);
                                continue;
                            }
                        }
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), i, j, k, this.boundingBox);
                    }
                }
            }
        }
        return true;
    }

    private <T extends SizedPieceMP> T getRoom(Class<? extends StructureComponent> clazz, DungeonStartMP startPiece, Random rand)
    {
        try
        {
            Constructor<? extends T> c0 = (Constructor<? extends T>) clazz.getConstructor(DungeonConfigurationMP.class, Random.class, Integer.TYPE, Integer.TYPE, EnumFacing.class);
            T dummy = c0.newInstance(this.configuration, rand, 0, 0, this.getDirection().getOpposite());
            StructureBoundingBox extension = this.getExtension(this.getDirection(), this.getDirection().getAxis() == EnumFacing.Axis.X ? dummy.getSizeX() : dummy.getSizeZ(), this.getDirection().getAxis() == EnumFacing.Axis.X ? dummy.getSizeZ() : dummy.getSizeX());

            if (startPiece.checkIntersection(extension))
            {
                return null;
            }
            int sizeX = extension.maxX - extension.minX;
            int sizeZ = extension.maxZ - extension.minZ;
            int sizeY = dummy.sizeY;
            int blockX = extension.minX;
            int blockZ = extension.minZ;
            Constructor<? extends T> c1 = (Constructor<? extends T>) clazz.getConstructor(DungeonConfigurationMP.class, Random.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, EnumFacing.class);
            return c1.newInstance(this.configuration, rand, blockX, blockZ, sizeX, sizeY, sizeZ, this.getDirection().getOpposite());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PieceMP getNextPiece(DungeonStartMP startPiece, Random rand)
    {
        if (startPiece.attachedComponents.size() > 2 && startPiece.attachedComponents.get(startPiece.attachedComponents.size() - 2) instanceof RoomBossMP)
        {
            try
            {
                return this.getRoom(this.configuration.getTreasureRoom(), startPiece, rand);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            int bossRoomChance = Math.max((int) (1.0 / Math.pow(startPiece.attachedComponents.size() / 55.0, 2)), 5);
            boolean bossRoom = rand.nextInt(bossRoomChance) == 0;

            if (bossRoom)
            {
                try
                {
                    return this.getRoom(this.configuration.getBossRoom(), startPiece, rand);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                StructureBoundingBox extension = this.getExtension(this.getDirection(), rand.nextInt(4) + 6, rand.nextInt(4) + 6);

                if (startPiece.checkIntersection(extension))
                {
                    return null;
                }

                int sizeX = extension.maxX - extension.minX;
                int sizeZ = extension.maxZ - extension.minZ;
                int sizeY = this.configuration.getRoomHeight();
                int blockX = extension.minX;
                int blockZ = extension.minZ;

                if (Math.abs(startPiece.getBoundingBox().maxZ - this.boundingBox.minZ) > 200)
                {
                    return null;
                }
                if (Math.abs(startPiece.getBoundingBox().maxX - this.boundingBox.minX) > 200)
                {
                    return null;
                }

                switch (rand.nextInt(3))
                {
                case 0:
                    try
                    {
                        return this.getRoom(this.configuration.getSpawnerRoom(), startPiece, rand);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                case 1:
                    try
                    {
                        return this.getRoom(this.configuration.getChestRoom(), startPiece, rand);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                default:
                case 2:
                    return new RoomEmptyMP(this.configuration, rand, blockX, blockZ, sizeX, sizeY, sizeZ, this.getDirection().getOpposite());
                }
            }
        }
        return null;
    }
}
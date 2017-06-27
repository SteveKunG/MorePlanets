package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import java.lang.reflect.Constructor;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;

public class CorridorNibiru extends SizedPieceNibiru
{
    public CorridorNibiru() {}

    public CorridorNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing direction)
    {
        super(configuration, sizeX, sizeY, sizeZ, direction);
        this.setCoordBaseMode(EnumFacing.SOUTH);
        this.boundingBox = new StructureBoundingBox(blockPosX, configuration.getYPosition(), blockPosZ, blockPosX + sizeX, configuration.getYPosition() + sizeY, blockPosZ + sizeZ);
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        for (int i = 0; i < this.boundingBox.getXSize(); i++)
        {
            for (int j = 0; j < this.boundingBox.getYSize(); j++)
            {
                for (int k = 0; k < this.boundingBox.getZSize(); k++)
                {
                    if (j == 2 && this.getDirection().getAxis() == EnumFacing.Axis.Z && (k + 1) % 1 == 0 && k != this.boundingBox.getZSize() - 1)
                    {
                        if (i == 0 || i == this.boundingBox.getXSize() - 1)
                        {
                            this.setBlockState(worldIn, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), i, j, k + 1, this.boundingBox);
                        }
                        else if (i == 1 || i == this.boundingBox.getXSize() - 2)
                        {
                            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), i, j, k + 1, this.boundingBox);
                        }
                        else
                        {
                            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), i, j, k, this.boundingBox);
                        }
                    }
                    else if (j == 2 && this.getDirection().getAxis() == EnumFacing.Axis.X && (i + 1) % 1 == 0 && i != this.boundingBox.getXSize() - 1)
                    {
                        if (k == 0 || k == this.boundingBox.getZSize() - 1)
                        {
                            this.setBlockState(worldIn, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), i + 1, j, k, this.boundingBox);
                        }
                        else if (k == 1 || k == this.boundingBox.getZSize() - 2)
                        {
                            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), i + 1, j, k, this.boundingBox);
                        }
                        else
                        {
                            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), i, j, k, this.boundingBox);
                        }
                    }
                    else if (this.getDirection().getAxis() == EnumFacing.Axis.Z && (i == 1 || i == this.boundingBox.getXSize() - 2) || j == 0 || j == this.boundingBox.getYSize() - 1 || this.getDirection().getAxis() == EnumFacing.Axis.X && (k == 1 || k == this.boundingBox.getZSize() - 2))
                    {
                        this.setBlockState(worldIn, j == 0 || j == this.boundingBox.getYSize() - 1 ? NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(9) : this.configuration.getBrickBlock(), i, j, k, this.boundingBox);
                    }
                    else if (this.getDirection().getAxis() == EnumFacing.Axis.Z && (i == 0 || i == this.boundingBox.getXSize() - 1) || this.getDirection().getAxis() == EnumFacing.Axis.X && (k == 0 || k == this.boundingBox.getZSize() - 1))
                    {
                        this.setBlockState(worldIn, j == 0 || j == this.boundingBox.getYSize() - 1 ? NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(9) : this.configuration.getBrickBlock(), i, j, k, this.boundingBox);
                    }
                    else
                    {
                        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), i, j, k, this.boundingBox);
                    }
                }
            }
        }
        return true;
    }

    private <T extends SizedPieceNibiru> T getRoom(Class<?> clazz, DungeonStartNibiru startPiece, Random rand)
    {
        try
        {
            Constructor<?> c0 = clazz.getConstructor(DungeonConfigurationMP.class, Random.class, Integer.TYPE, Integer.TYPE, EnumFacing.class);
            T dummy = (T) c0.newInstance(this.configuration, rand, 0, 0, this.getDirection().getOpposite());
            StructureBoundingBox extension = this.getExtension(this.getDirection(), this.getDirection().getAxis() == EnumFacing.Axis.X ? dummy.getSizeX() : dummy.getSizeZ(), this.getDirection().getAxis() == EnumFacing.Axis.X ? dummy.getSizeZ() : dummy.getSizeX());
            if (startPiece.checkIntersection(extension))
            {
                return null;
            }
            int sizeX = extension.maxX - extension.minX;
            int sizeZ = extension.maxZ - extension.minZ;
            int sizeY = dummy.getSizeY();
            int blockX = extension.minX;
            int blockZ = extension.minZ;
            Constructor<?> c1 = clazz.getConstructor(DungeonConfigurationMP.class, Random.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, EnumFacing.class);
            return (T) c1.newInstance(this.configuration, rand, blockX, blockZ, sizeX, sizeY, sizeZ, this.getDirection().getOpposite());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PieceNibiru getNextPiece(DungeonStartNibiru startPiece, Random rand)
    {
        if (startPiece.attachedComponents.size() > 2 && startPiece.attachedComponents.get(startPiece.attachedComponents.size() - 2) instanceof RoomBossNibiru)
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

                PieceNibiru lastPiece = startPiece.attachedComponents.size() <= 2 ? null : (PieceNibiru) startPiece.attachedComponents.get(startPiece.attachedComponents.size() - 2);

                if (!(lastPiece instanceof RoomSpawnerNibiru))
                {
                    return new RoomSpawnerNibiru(this.configuration, rand, blockX, blockZ, sizeX, sizeY, sizeZ, this.getDirection().getOpposite());
                }
                else
                {
                    if (rand.nextInt(2) == 0)
                    {
                        return new RoomEmptyNibiru(this.configuration, rand, blockX, blockZ, sizeX, sizeY, sizeZ, this.getDirection().getOpposite());
                    }
                    else
                    {
                        return new RoomChestNibiru(this.configuration, rand, blockX, blockZ, sizeX, sizeY, sizeZ, this.getDirection().getOpposite());
                    }
                }
            }
        }
        return null;
    }
}
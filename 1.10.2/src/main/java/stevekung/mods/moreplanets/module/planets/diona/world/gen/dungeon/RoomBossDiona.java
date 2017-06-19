package stevekung.mods.moreplanets.module.planets.diona.world.gen.dungeon;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.blocks.BlockSpaceDungeonSpawner;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDionaDungeonSpawner;
import stevekung.mods.moreplanets.util.world.gen.dungeon.*;

public class RoomBossDiona extends RoomBossMP
{
    public RoomBossDiona() {}

    public RoomBossDiona(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, sizeX, sizeY, sizeZ, entranceDir);
    }

    public RoomBossDiona(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, 24, 11, 24, entranceDir);
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox boundingBox)
    {
        for (int i = 0; i <= this.sizeX; i++)
        {
            for (int j = 0; j <= this.sizeY; j++)
            {
                for (int k = 0; k <= this.sizeZ; k++)
                {
                    if (i == 0 || i == this.sizeX || j == 0 || k == 0 || k == this.sizeZ)
                    {
                        boolean placeBlock = true;

                        if (this.getDirection().getAxis() == EnumFacing.Axis.Z)
                        {
                            int start = (this.boundingBox.maxX - this.boundingBox.minX) / 2 - 1;
                            int end = (this.boundingBox.maxX - this.boundingBox.minX) / 2 + 1;

                            if (i > start && i <= end && j < 3 && j > 0)
                            {
                                if (this.getDirection() == EnumFacing.SOUTH && k == 0)
                                {
                                    placeBlock = false;
                                }
                                else if (this.getDirection() == EnumFacing.NORTH && k == this.sizeZ)
                                {
                                    placeBlock = false;
                                }
                            }
                        }
                        else
                        {
                            int start = (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 - 1;
                            int end = (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 + 1;

                            if (k > start && k <= end && j < 3 && j > 0)
                            {
                                if (this.getDirection() == EnumFacing.EAST && i == 0)
                                {
                                    placeBlock = false;
                                }
                                else if (this.getDirection() == EnumFacing.WEST && i == this.sizeX)
                                {
                                    placeBlock = false;
                                }
                            }
                        }
                        if (placeBlock)
                        {
                            this.setBlockState(world, this.configuration.getBrickBlock(), i, j, k, boundingBox);
                        }
                        else
                        {
                            this.setBlockState(world, Blocks.air.getDefaultState(), i, j, k, boundingBox);
                        }
                    }
                    else if (j == this.sizeY)
                    {
                        if ((i <= 2 || k <= 2 || i >= this.sizeX - 2 || k >= this.sizeZ - 2) && rand.nextInt(4) == 0)
                        {
                            this.setBlockState(world, this.configuration.getGlowstoneBlock(), i, j, k, boundingBox);
                        }
                        else
                        {
                            this.setBlockState(world, this.configuration.getBrickBlock(), i, j, k, boundingBox);
                        }
                    }
                    else if (j == 1 && (i <= 2 || k <= 2 || i >= this.sizeX - 2 || k >= this.sizeZ - 2))
                    {
                        if (rand.nextInt(4) == 0)
                        {
                            this.setBlockState(world, this.configuration.getGlowstoneBlock(), i, j - 1, k, boundingBox);
                        }
                        else
                        {
                            this.setBlockState(world, Blocks.air.getDefaultState(), i, j, k, boundingBox);
                        }

                        if (rand.nextInt(6) == 0)
                        {
                            this.setBlockState(world, DionaBlocks.ZELIUS_EGG.getDefaultState(), i, j, k, boundingBox);
                        }
                        else
                        {
                            this.setBlockState(world, Blocks.air.getDefaultState(), i, j, k, boundingBox);
                        }
                    }
                    else
                    {
                        this.setBlockState(world, Blocks.air.getDefaultState(), i, j, k, boundingBox);
                    }
                }
            }
        }

        int spawnerX = this.sizeX / 2;
        int spawnerY = 1;
        int spawnerZ = this.sizeZ / 2;
        this.setBlockState(world, MPBlocks.SPACE_DUNGEON_SPAWNER.getDefaultState().withProperty(BlockSpaceDungeonSpawner.PLANET, BlockSpaceDungeonSpawner.DungeonType.DIONA), spawnerX, spawnerY, spawnerZ, boundingBox);
        BlockPos blockpos = new BlockPos(this.getXWithOffset(spawnerX, spawnerZ), this.getYWithOffset(spawnerY), this.getZWithOffset(spawnerX, spawnerZ));
        TileEntityDionaDungeonSpawner spawner = (TileEntityDionaDungeonSpawner) world.getTileEntity(blockpos);

        if (spawner == null)
        {
            spawner = new TileEntityDionaDungeonSpawner();
            world.setTileEntity(blockpos, spawner);
        }
        spawner.setRoom(new Vector3(this.boundingBox.minX + 1, this.boundingBox.minY + 1, this.boundingBox.minZ + 1), new Vector3(this.sizeX - 1, this.sizeY - 1, this.sizeZ - 1));
        spawner.setChestPos(this.chestPos);
        return true;
    }

    @Override
    public PieceMP getCorridor(Random rand, DungeonStartMP startPiece, int maxAttempts, boolean small)
    {
        EnumFacing randomDir;
        int blockX;
        int blockZ;
        int sizeX;
        int sizeZ;
        boolean valid;
        int attempts = maxAttempts;

        do
        {
            int randDir = rand.nextInt(4);
            randomDir = EnumFacing.getHorizontal((randDir == this.getDirection().getOpposite().getHorizontalIndex() ? randDir + 1 : randDir) % 4);
            StructureBoundingBox extension = this.getExtension(randomDir, this.configuration.getHallwayLengthMin() + rand.nextInt(this.configuration.getHallwayLengthMax() - this.configuration.getHallwayLengthMin()), 3);
            blockX = extension.minX;
            blockZ = extension.minZ;
            sizeX = extension.maxX - extension.minX;
            sizeZ = extension.maxZ - extension.minZ;
            valid = !startPiece.checkIntersection(extension);
            attempts--;
        }
        while (!valid && attempts > 0);

        if (!valid)
        {
            return null;
        }
        return new CorridorMP(this.configuration, rand, blockX, blockZ, sizeX, small ? 3 : this.configuration.getHallwayHeight(), sizeZ, randomDir);
    }
}
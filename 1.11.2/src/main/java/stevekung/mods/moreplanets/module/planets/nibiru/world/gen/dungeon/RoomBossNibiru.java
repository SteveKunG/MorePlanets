package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.blocks.BlockSpaceDungeonSpawner;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNibiruDungeonSpawner;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;

public class RoomBossNibiru extends SizedPieceNibiru
{
    protected BlockPos chestPos;

    public RoomBossNibiru() {}

    public RoomBossNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir)
    {
        super(configuration, sizeX, sizeY, sizeZ, entranceDir.getOpposite());
        this.setCoordBaseMode(EnumFacing.SOUTH);
        this.sizeX = sizeX;
        this.sizeZ = sizeZ;
        this.sizeY = sizeY;
        int yPos = configuration.getYPosition();
        this.boundingBox = new StructureBoundingBox(blockPosX, yPos, blockPosZ, blockPosX + this.sizeX, yPos + this.sizeY, blockPosZ + this.sizeZ);
    }

    public RoomBossNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, EnumFacing entranceDir)
    {
        this(configuration, rand, blockPosX, blockPosZ, 24, 11, 24, entranceDir);
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
                            this.setBlockState(world, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
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
                            this.setBlockState(world, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
                        }
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
                    }
                    else
                    {
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
                    }
                }
            }
        }

        int spawnerX = this.sizeX / 2;
        int spawnerY = 1;
        int spawnerZ = this.sizeZ / 2;
        this.setBlockState(world, MPBlocks.SPACE_DUNGEON_SPAWNER.getDefaultState().withProperty(BlockSpaceDungeonSpawner.PLANET, BlockSpaceDungeonSpawner.DungeonType.NIBIRU), spawnerX, spawnerY, spawnerZ, boundingBox);
        BlockPos blockpos = new BlockPos(this.getXWithOffset(spawnerX, spawnerZ), this.getYWithOffset(spawnerY), this.getZWithOffset(spawnerX, spawnerZ));
        TileEntityNibiruDungeonSpawner spawner = (TileEntityNibiruDungeonSpawner) world.getTileEntity(blockpos);

        if (spawner == null)
        {
            spawner = new TileEntityNibiruDungeonSpawner();
            world.setTileEntity(blockpos, spawner);
        }
        spawner.setRoom(new Vector3(this.boundingBox.minX + 1, this.boundingBox.minY + 1, this.boundingBox.minZ + 1), new Vector3(this.sizeX - 1, this.sizeY - 1, this.sizeZ - 1));
        spawner.setChestPos(this.chestPos);
        return true;
    }

    @Override
    public PieceNibiru getNextPiece(DungeonStartNibiru startPiece, Random rand)
    {
        return this.getCorridor(rand, startPiece, 10, true);
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setInteger("chestX", this.chestPos.getX());
        tagCompound.setInteger("chestY", this.chestPos.getY());
        tagCompound.setInteger("chestZ", this.chestPos.getZ());
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager)
    {
        super.readStructureFromNBT(tagCompound, manager);
        this.chestPos = new BlockPos(tagCompound.getInteger("chestX"), tagCompound.getInteger("chestY"), tagCompound.getInteger("chestZ"));
    }

    public BlockPos getChestPos()
    {
        return this.chestPos;
    }

    public void setChestPos(BlockPos chestPos)
    {
        this.chestPos = chestPos;
    }
}
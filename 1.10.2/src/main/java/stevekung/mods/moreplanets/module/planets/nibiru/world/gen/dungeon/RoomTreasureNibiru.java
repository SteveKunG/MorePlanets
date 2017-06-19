package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNibiruTreasureChest;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;

public class RoomTreasureNibiru extends SizedPieceNibiru
{
    public RoomTreasureNibiru() {}

    public RoomTreasureNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, EnumFacing entranceDir)
    {
        this(configuration, rand, blockPosX, blockPosZ, rand.nextInt(4) + 6, configuration.getRoomHeight(), rand.nextInt(4) + 6, entranceDir);
    }

    public RoomTreasureNibiru(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir)
    {
        super(configuration, sizeX, sizeY, sizeZ, entranceDir.getOpposite());
        this.coordBaseMode = EnumFacing.SOUTH;
        int yPos = configuration.getYPosition();
        this.boundingBox = new StructureBoundingBox(blockPosX, yPos, blockPosZ, blockPosX + this.sizeX, yPos + this.sizeY, blockPosZ + this.sizeZ);
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
                    if (i == 0 || i == this.sizeX || j == 0 || j == this.sizeY || k == 0 || k == this.sizeZ)
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
                    else if (i == 1 && k == 1 || i == 1 && k == this.sizeZ - 1 || i == this.sizeX - 1 && k == 1 || i == this.sizeX - 1 && k == this.sizeZ - 1)
                    {
                        this.setBlockState(world, this.configuration.getGlowstoneBlock(), i, j, k, boundingBox);
                    }
                    else if (i == this.sizeX / 2 && j == 1 && k == this.sizeZ / 2)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_TREASURE_CHEST.getDefaultState().withProperty(BlockStateHelper.FACING, this.getDirection().getOpposite()), i, j, k, boundingBox);
                        BlockPos blockpos = new BlockPos(this.getXWithOffset(i, k), this.getYWithOffset(j), this.getZWithOffset(i, k));

                        if (world.getTileEntity(blockpos) == null)
                        {
                            world.setTileEntity(blockpos, new TileEntityNibiruTreasureChest());
                        }
                    }
                    else
                    {
                        this.setBlockState(world, Blocks.air.getDefaultState(), i, j, k, boundingBox);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public PieceNibiru getNextPiece(DungeonStartNibiru startPiece, Random rand)
    {
        if (startPiece.attachedComponents.size() > 2)
        {
            StructureComponent component = startPiece.attachedComponents.get(startPiece.attachedComponents.size() - 3);

            if (component instanceof RoomBossNibiru)
            {
                BlockPos blockpos = new BlockPos(this.getXWithOffset(this.sizeX / 2, this.sizeZ / 2), this.getYWithOffset(1), this.getZWithOffset(this.sizeX / 2, this.sizeZ / 2));
                ((RoomBossNibiru) component).setChestPos(new BlockPos(blockpos));
            }
        }
        return null;
    }
}
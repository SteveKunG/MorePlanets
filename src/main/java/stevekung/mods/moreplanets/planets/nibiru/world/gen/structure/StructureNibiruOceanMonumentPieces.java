package stevekung.mods.moreplanets.planets.nibiru.world.gen.structure;

import java.util.*;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedElderGuardian;

public class StructureNibiruOceanMonumentPieces
{
    public static void registerOceanMonumentPieces()
    {
        MapGenStructureIO.registerStructureComponent(MonumentBuilding.class, "NibiruOMB");
        MapGenStructureIO.registerStructureComponent(MonumentCoreRoom.class, "NibiruOMCR");
        MapGenStructureIO.registerStructureComponent(DoubleXRoom.class, "NibiruOMDXR");
        MapGenStructureIO.registerStructureComponent(DoubleXYRoom.class, "NibiruOMDXYR");
        MapGenStructureIO.registerStructureComponent(DoubleYRoom.class, "NibiruOMDYR");
        MapGenStructureIO.registerStructureComponent(DoubleYZRoom.class, "NibiruOMDYZR");
        MapGenStructureIO.registerStructureComponent(DoubleZRoom.class, "NibiruOMDZR");
        MapGenStructureIO.registerStructureComponent(EntryRoom.class, "NibiruOMEntry");
        MapGenStructureIO.registerStructureComponent(Penthouse.class, "NibiruOMPenthouse");
        MapGenStructureIO.registerStructureComponent(SimpleRoom.class, "NibiruOMSimple");
        MapGenStructureIO.registerStructureComponent(SimpleTopRoom.class, "NibiruOMSimpleT");
    }

    public static class DoubleXRoom extends Piece
    {
        public DoubleXRoom() {}

        public DoubleXRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 2, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            RoomDefinition roomdefinition = this.roomDefinition.connections[EnumFacing.EAST.getIndex()];
            RoomDefinition roomdefinition1 = this.roomDefinition;

            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 8, 0, roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
                this.generateDefaultFloor(world, box, 0, 0, roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
            }
            if (roomdefinition1.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 4, 1, 7, 4, 6, this.roughPrismarine);
            }
            if (roomdefinition.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 8, 4, 1, 14, 4, 6, this.roughPrismarine);
            }

            this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 15, 3, 0, 15, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 0, 15, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 7, 14, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 15, 2, 0, 15, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 0, 15, 2, 0, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 7, 14, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 15, 1, 0, 15, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 15, 1, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 7, 14, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 1, 0, 10, 1, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 2, 0, 9, 2, 3, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 5, 3, 0, 10, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.setBlockState(world, this.seaLantern, 6, 2, 3, box);
            this.setBlockState(world, this.seaLantern, 9, 2, 3, box);

            if (roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 7, 4, 2, 7, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 11, 1, 0, 12, 2, 0, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 11, 1, 7, 12, 2, 7, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 15, 1, 3, 15, 2, 4, false);
            }
            return true;
        }
    }

    public static class DoubleXYRoom extends Piece
    {
        public DoubleXYRoom() {}

        public DoubleXYRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 2, 2, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            RoomDefinition roomdefinition = this.roomDefinition.connections[EnumFacing.EAST.getIndex()];
            RoomDefinition roomdefinition1 = this.roomDefinition;
            RoomDefinition roomdefinition2 = roomdefinition1.connections[EnumFacing.UP.getIndex()];
            RoomDefinition roomdefinition3 = roomdefinition.connections[EnumFacing.UP.getIndex()];

            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 8, 0, roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
                this.generateDefaultFloor(world, box, 0, 0, roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
            }
            if (roomdefinition2.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 8, 1, 7, 8, 6, this.roughPrismarine);
            }
            if (roomdefinition3.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 8, 8, 1, 14, 8, 6, this.roughPrismarine);
            }

            for (int i = 1; i <= 7; ++i)
            {
                IBlockState iblockstate = this.bricksPrismarine;

                if (i == 2 || i == 6)
                {
                    iblockstate = this.roughPrismarine;
                }

                this.fillWithBlocks(world, box, 0, i, 0, 0, i, 7, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 15, i, 0, 15, i, 7, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 0, 15, i, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 7, 14, i, 7, iblockstate, iblockstate, false);
            }

            this.fillWithBlocks(world, box, 2, 1, 3, 2, 7, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 2, 4, 7, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 5, 4, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 13, 1, 3, 13, 7, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 11, 1, 2, 12, 7, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 11, 1, 5, 12, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 1, 3, 5, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 1, 3, 10, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 7, 2, 10, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 5, 2, 5, 7, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 5, 2, 10, 7, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 5, 5, 5, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 5, 5, 10, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.setBlockState(world, this.bricksPrismarine, 6, 6, 2, box);
            this.setBlockState(world, this.bricksPrismarine, 9, 6, 2, box);
            this.setBlockState(world, this.bricksPrismarine, 6, 6, 5, box);
            this.setBlockState(world, this.bricksPrismarine, 9, 6, 5, box);
            this.fillWithBlocks(world, box, 5, 4, 3, 6, 4, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 9, 4, 3, 10, 4, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            this.setBlockState(world, this.seaLantern, 5, 4, 2, box);
            this.setBlockState(world, this.seaLantern, 5, 4, 5, box);
            this.setBlockState(world, this.seaLantern, 10, 4, 2, box);
            this.setBlockState(world, this.seaLantern, 10, 4, 5, box);

            if (roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 7, 4, 2, 7, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 11, 1, 0, 12, 2, 0, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 11, 1, 7, 12, 2, 7, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 15, 1, 3, 15, 2, 4, false);
            }
            if (roomdefinition2.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 5, 0, 4, 6, 0, false);
            }
            if (roomdefinition2.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 5, 7, 4, 6, 7, false);
            }
            if (roomdefinition2.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 5, 3, 0, 6, 4, false);
            }
            if (roomdefinition3.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 11, 5, 0, 12, 6, 0, false);
            }
            if (roomdefinition3.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 11, 5, 7, 12, 6, 7, false);
            }
            if (roomdefinition3.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 15, 5, 3, 15, 6, 4, false);
            }
            return true;
        }
    }

    public static class DoubleYRoom extends Piece
    {
        public DoubleYRoom() {}

        public DoubleYRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 1, 2, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
            }

            RoomDefinition roomdefinition = this.roomDefinition.connections[EnumFacing.UP.getIndex()];

            if (roomdefinition.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 8, 1, 6, 8, 6, this.roughPrismarine);
            }

            this.fillWithBlocks(world, box, 0, 4, 0, 0, 4, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 7, 4, 0, 7, 4, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 4, 0, 6, 4, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 4, 7, 6, 4, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 4, 1, 2, 4, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 4, 2, 1, 4, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 4, 1, 5, 4, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 4, 2, 6, 4, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 4, 5, 2, 4, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 4, 5, 1, 4, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 4, 5, 5, 4, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 4, 5, 6, 4, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            RoomDefinition roomdefinition1 = this.roomDefinition;

            for (int i = 1; i <= 5; i += 4)
            {
                int j = 0;

                if (roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 2, i, j, 2, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 5, i, j, 5, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 3, i + 2, j, 4, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, i, j, 7, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 0, i + 1, j, 7, i + 1, j, this.roughPrismarine, this.roughPrismarine, false);
                }

                j = 7;

                if (roomdefinition1.hasOpening[EnumFacing.NORTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 2, i, j, 2, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 5, i, j, 5, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 3, i + 2, j, 4, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, i, j, 7, i + 2, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 0, i + 1, j, 7, i + 1, j, this.roughPrismarine, this.roughPrismarine, false);
                }

                int k = 0;

                if (roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
                {
                    this.fillWithBlocks(world, box, k, i, 2, k, i + 2, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, k, i, 5, k, i + 2, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, k, i + 2, 3, k, i + 2, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, k, i, 0, k, i + 2, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, k, i + 1, 0, k, i + 1, 7, this.roughPrismarine, this.roughPrismarine, false);
                }

                k = 7;

                if (roomdefinition1.hasOpening[EnumFacing.EAST.getIndex()])
                {
                    this.fillWithBlocks(world, box, k, i, 2, k, i + 2, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, k, i, 5, k, i + 2, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, k, i + 2, 3, k, i + 2, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, k, i, 0, k, i + 2, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, k, i + 1, 0, k, i + 1, 7, this.roughPrismarine, this.roughPrismarine, false);
                }
                roomdefinition1 = roomdefinition;
            }
            return true;
        }
    }

    public static class DoubleYZRoom extends Piece
    {
        public DoubleYZRoom() {}

        public DoubleYZRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 1, 2, 2);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            RoomDefinition roomdefinition = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
            RoomDefinition roomdefinition1 = this.roomDefinition;
            RoomDefinition roomdefinition2 = roomdefinition.connections[EnumFacing.UP.getIndex()];
            RoomDefinition roomdefinition3 = roomdefinition1.connections[EnumFacing.UP.getIndex()];

            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 0, 8, roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
                this.generateDefaultFloor(world, box, 0, 0, roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
            }
            if (roomdefinition3.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 8, 1, 6, 8, 7, this.roughPrismarine);
            }
            if (roomdefinition2.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 8, 8, 6, 8, 14, this.roughPrismarine);
            }

            for (int i = 1; i <= 7; ++i)
            {
                IBlockState iblockstate = this.bricksPrismarine;

                if (i == 2 || i == 6)
                {
                    iblockstate = this.roughPrismarine;
                }
                this.fillWithBlocks(world, box, 0, i, 0, 0, i, 15, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 7, i, 0, 7, i, 15, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 0, 6, i, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 15, 6, i, 15, iblockstate, iblockstate, false);
            }

            for (int j = 1; j <= 7; ++j)
            {
                IBlockState iblockstate1 = this.darkPrismarine;

                if (j == 2 || j == 6)
                {
                    iblockstate1 = this.seaLantern;
                }
                this.fillWithBlocks(world, box, 3, j, 7, 4, j, 8, iblockstate1, iblockstate1, false);
            }

            if (roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 7, 1, 3, 7, 2, 4, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 15, 4, 2, 15, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 11, 0, 2, 12, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 7, 1, 11, 7, 2, 12, false);
            }
            if (roomdefinition3.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 5, 0, 4, 6, 0, false);
            }
            if (roomdefinition3.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 7, 5, 3, 7, 6, 4, false);
                this.fillWithBlocks(world, box, 5, 4, 2, 6, 4, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 1, 2, 6, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 1, 5, 6, 3, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            }
            if (roomdefinition3.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 5, 3, 0, 6, 4, false);
                this.fillWithBlocks(world, box, 1, 4, 2, 2, 4, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 1, 2, 1, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 1, 5, 1, 3, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            }
            if (roomdefinition2.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 5, 15, 4, 6, 15, false);
            }
            if (roomdefinition2.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 5, 11, 0, 6, 12, false);
                this.fillWithBlocks(world, box, 1, 4, 10, 2, 4, 13, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 1, 10, 1, 3, 10, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 1, 13, 1, 3, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            }
            if (roomdefinition2.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 7, 5, 11, 7, 6, 12, false);
                this.fillWithBlocks(world, box, 5, 4, 10, 6, 4, 13, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 1, 10, 6, 3, 10, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 1, 13, 6, 3, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            }
            return true;
        }
    }

    public static class DoubleZRoom extends Piece
    {
        public DoubleZRoom() {}

        public DoubleZRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 1, 1, 2);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            RoomDefinition roomdefinition = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
            RoomDefinition roomdefinition1 = this.roomDefinition;

            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 0, 8, roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
                this.generateDefaultFloor(world, box, 0, 0, roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
            }
            if (roomdefinition1.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 4, 1, 6, 4, 7, this.roughPrismarine);
            }
            if (roomdefinition.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 4, 8, 6, 4, 14, this.roughPrismarine);
            }

            this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 15, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 7, 3, 0, 7, 3, 15, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 0, 7, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 15, 6, 3, 15, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 15, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 15, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 0, 7, 2, 0, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 15, 6, 2, 15, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 15, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 15, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 7, 1, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 15, 6, 1, 15, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 1, 1, 1, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 1, 1, 6, 1, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 1, 1, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 3, 1, 6, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 13, 1, 1, 14, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 1, 13, 6, 1, 14, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 13, 1, 3, 14, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 3, 13, 6, 3, 14, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 1, 6, 2, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 1, 6, 5, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 1, 9, 2, 3, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 1, 9, 5, 3, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 3, 2, 6, 4, 2, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 3, 2, 9, 4, 2, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 2, 7, 2, 2, 8, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 2, 7, 5, 2, 8, this.bricksPrismarine, this.bricksPrismarine, false);
            this.setBlockState(world, this.seaLantern, 2, 2, 5, box);
            this.setBlockState(world, this.seaLantern, 5, 2, 5, box);
            this.setBlockState(world, this.seaLantern, 2, 2, 10, box);
            this.setBlockState(world, this.seaLantern, 5, 2, 10, box);
            this.setBlockState(world, this.bricksPrismarine, 2, 3, 5, box);
            this.setBlockState(world, this.bricksPrismarine, 5, 3, 5, box);
            this.setBlockState(world, this.bricksPrismarine, 2, 3, 10, box);
            this.setBlockState(world, this.bricksPrismarine, 5, 3, 10, box);

            if (roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 7, 1, 3, 7, 2, 4, false);
            }
            if (roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 15, 4, 2, 15, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 11, 0, 2, 12, false);
            }
            if (roomdefinition.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 7, 1, 11, 7, 2, 12, false);
            }
            return true;
        }
    }

    public static class EntryRoom extends Piece
    {
        public EntryRoom() {}

        public EntryRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 1, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithBlocks(world, box, 0, 3, 0, 2, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 3, 0, 7, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 1, 2, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 2, 0, 7, 2, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 7, 7, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 2, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 1, 0, 6, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);

            if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 7, 4, 2, 7, false);
            }
            if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()])
            {
                this.generateWaterBox(world, box, 0, 1, 3, 1, 2, 4, false);
            }
            if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()])
            {
                this.generateWaterBox(world, box, 6, 1, 3, 7, 2, 4, false);
            }
            return true;
        }
    }

    static class FitSimpleRoomHelper implements MonumentRoomFitHelper
    {
        private FitSimpleRoomHelper() {}

        @Override
        public boolean fits(RoomDefinition room)
        {
            return true;
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            room.claimed = true;
            return new SimpleRoom(facing, room);
        }
    }

    static class FitSimpleRoomTopHelper implements MonumentRoomFitHelper
    {
        private FitSimpleRoomTopHelper() {}

        @Override
        public boolean fits(RoomDefinition room)
        {
            return !room.hasOpening[EnumFacing.WEST.getIndex()] && !room.hasOpening[EnumFacing.EAST.getIndex()] && !room.hasOpening[EnumFacing.NORTH.getIndex()] && !room.hasOpening[EnumFacing.SOUTH.getIndex()] && !room.hasOpening[EnumFacing.UP.getIndex()];
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            room.claimed = true;
            return new SimpleTopRoom(facing, room);
        }
    }

    public static class MonumentBuilding extends Piece
    {
        private RoomDefinition sourceRoom;
        private RoomDefinition coreRoom;
        private List<Piece> childPieces = new ArrayList<>();

        public MonumentBuilding() {}

        public MonumentBuilding(Random rand, int x, int z, EnumFacing facing)
        {
            super(0);
            this.setCoordBaseMode(facing);
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing.getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(x, 39, z, x + 58 - 1, 61, z + 58 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(x, 39, z, x + 58 - 1, 61, z + 58 - 1);
            }

            List<RoomDefinition> list = this.generateRoomGraph(rand);
            this.sourceRoom.claimed = true;
            this.childPieces.add(new EntryRoom(enumfacing, this.sourceRoom));
            this.childPieces.add(new MonumentCoreRoom(enumfacing, this.coreRoom));
            List<MonumentRoomFitHelper> list1 = new ArrayList<>();
            list1.add(new XYDoubleRoomFitHelper());
            list1.add(new YZDoubleRoomFitHelper());
            list1.add(new ZDoubleRoomFitHelper());
            list1.add(new XDoubleRoomFitHelper());
            list1.add(new YDoubleRoomFitHelper());
            list1.add(new FitSimpleRoomTopHelper());
            list1.add(new FitSimpleRoomHelper());
            label294:
                for (RoomDefinition roomdefinition : list)
                {
                    if (!roomdefinition.claimed && !roomdefinition.isSpecial())
                    {
                        Iterator iterator = list1.iterator();
                        MonumentRoomFitHelper helper;

                        while (true)
                        {
                            if (!iterator.hasNext())
                            {
                                continue label294;
                            }

                            helper = (MonumentRoomFitHelper)iterator.next();

                            if (helper.fits(roomdefinition))
                            {
                                break;
                            }
                        }
                        this.childPieces.add(helper.create(enumfacing, roomdefinition));
                    }
                }

            int j = this.boundingBox.minY;
            int k = this.getXWithOffset(9, 22);
            int l = this.getZWithOffset(9, 22);

            for (Piece piece : this.childPieces)
            {
                piece.getBoundingBox().offset(k, j, l);
            }
            StructureBoundingBox structureboundingbox1 = StructureBoundingBox.createProper(this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1), this.getXWithOffset(23, 21), this.getYWithOffset(8), this.getZWithOffset(23, 21));
            StructureBoundingBox structureboundingbox2 = StructureBoundingBox.createProper(this.getXWithOffset(34, 1), this.getYWithOffset(1), this.getZWithOffset(34, 1), this.getXWithOffset(56, 21), this.getYWithOffset(8), this.getZWithOffset(56, 21));
            StructureBoundingBox structureboundingbox = StructureBoundingBox.createProper(this.getXWithOffset(22, 22), this.getYWithOffset(13), this.getZWithOffset(22, 22), this.getXWithOffset(35, 35), this.getYWithOffset(17), this.getZWithOffset(35, 35));
            int i = rand.nextInt();
            this.childPieces.add(new WingRoom(enumfacing, structureboundingbox1, i++));
            this.childPieces.add(new WingRoom(enumfacing, structureboundingbox2, i++));
            this.childPieces.add(new Penthouse(enumfacing, structureboundingbox));
        }

        private List<RoomDefinition> generateRoomGraph(Random rand)
        {
            RoomDefinition[] aroomdefinition = new RoomDefinition[75];

            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 4; ++j)
                {
                    int l = getRoomIndex(i, 0, j);
                    aroomdefinition[l] = new RoomDefinition(l);
                }
            }
            for (int i2 = 0; i2 < 5; ++i2)
            {
                for (int l2 = 0; l2 < 4; ++l2)
                {
                    int j4 = getRoomIndex(i2, 1, l2);
                    aroomdefinition[j4] = new RoomDefinition(j4);
                }
            }
            for (int j2 = 1; j2 < 4; ++j2)
            {
                for (int i3 = 0; i3 < 2; ++i3)
                {
                    int k4 = getRoomIndex(j2, 2, i3);
                    aroomdefinition[k4] = new RoomDefinition(k4);
                }
            }

            this.sourceRoom = aroomdefinition[this.gridroomSourceIndex];

            for (int k2 = 0; k2 < 5; ++k2)
            {
                for (int j3 = 0; j3 < 5; ++j3)
                {
                    for (int i4 = 0; i4 < 3; ++i4)
                    {
                        int l4 = getRoomIndex(k2, i4, j3);

                        if (aroomdefinition[l4] != null)
                        {
                            for (EnumFacing enumfacing : EnumFacing.VALUES)
                            {
                                int i1 = k2 + enumfacing.getFrontOffsetX();
                                int j1 = i4 + enumfacing.getFrontOffsetY();
                                int k1 = j3 + enumfacing.getFrontOffsetZ();

                                if (i1 >= 0 && i1 < 5 && k1 >= 0 && k1 < 5 && j1 >= 0 && j1 < 3)
                                {
                                    int l1 = getRoomIndex(i1, j1, k1);

                                    if (aroomdefinition[l1] != null)
                                    {
                                        if (k1 == j3)
                                        {
                                            aroomdefinition[l4].setConnection(enumfacing, aroomdefinition[l1]);
                                        }
                                        else
                                        {
                                            aroomdefinition[l4].setConnection(enumfacing.getOpposite(), aroomdefinition[l1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            RoomDefinition roomdefinition = new RoomDefinition(1003);
            RoomDefinition roomdefinition1 = new RoomDefinition(1001);
            RoomDefinition roomdefinition2 = new RoomDefinition(1002);
            aroomdefinition[this.gridroomTopConnectIndex].setConnection(EnumFacing.UP, roomdefinition);
            aroomdefinition[this.gridroomLeftwingConnectIndex].setConnection(EnumFacing.SOUTH, roomdefinition1);
            aroomdefinition[this.gridroomRightwingConnectIndex].setConnection(EnumFacing.SOUTH, roomdefinition2);
            roomdefinition.claimed = true;
            roomdefinition1.claimed = true;
            roomdefinition2.claimed = true;
            this.sourceRoom.isSource = true;
            this.coreRoom = aroomdefinition[getRoomIndex(rand.nextInt(4), 0, 2)];
            this.coreRoom.claimed = true;
            this.coreRoom.connections[EnumFacing.EAST.getIndex()].claimed = true;
            this.coreRoom.connections[EnumFacing.NORTH.getIndex()].claimed = true;
            this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].claimed = true;
            this.coreRoom.connections[EnumFacing.UP.getIndex()].claimed = true;
            this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
            this.coreRoom.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
            this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
            List<RoomDefinition> list = new ArrayList<>();

            for (RoomDefinition roomdefinition4 : aroomdefinition)
            {
                if (roomdefinition4 != null)
                {
                    roomdefinition4.updateOpenings();
                    list.add(roomdefinition4);
                }
            }

            roomdefinition.updateOpenings();
            Collections.shuffle(list, rand);
            int i5 = 1;

            for (RoomDefinition roomdefinition3 : list)
            {
                int j5 = 0;
                int k5 = 0;

                while (j5 < 2 && k5 < 5)
                {
                    ++k5;
                    int l5 = rand.nextInt(6);

                    if (roomdefinition3.hasOpening[l5])
                    {
                        int i6 = EnumFacing.getFront(l5).getOpposite().getIndex();
                        roomdefinition3.hasOpening[l5] = false;
                        roomdefinition3.connections[l5].hasOpening[i6] = false;

                        if (roomdefinition3.findSource(i5++) && roomdefinition3.connections[l5].findSource(i5++))
                        {
                            ++j5;
                        }
                        else
                        {
                            roomdefinition3.hasOpening[l5] = true;
                            roomdefinition3.connections[l5].hasOpening[i6] = true;
                        }
                    }
                }
            }
            list.add(roomdefinition);
            list.add(roomdefinition1);
            list.add(roomdefinition2);
            return list;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            int i = Math.max(world.getSeaLevel(), 64) - this.boundingBox.minY;
            this.generateWaterBox(world, box, 0, 0, 0, 58, i, 58, false);
            this.generateWing(false, 0, world, box);
            this.generateWing(true, 33, world, box);
            this.generateEntranceArchs(world, box);
            this.generateEntranceWall(world, box);
            this.generateRoofPiece(world, box);
            this.generateLowerWall(world, box);
            this.generateMiddleWall(world, box);
            this.generateUpperWall(world, box);

            for (int j = 0; j < 7; ++j)
            {
                int k = 0;

                while (k < 7)
                {
                    if (k == 0 && j == 3)
                    {
                        k = 6;
                    }

                    int l = j * 9;
                    int i1 = k * 9;

                    for (int j1 = 0; j1 < 4; ++j1)
                    {
                        for (int k1 = 0; k1 < 4; ++k1)
                        {
                            this.setBlockState(world, this.bricksPrismarine, l + j1, 0, i1 + k1, box);
                            this.replaceAirAndLiquidDownwards(world, this.bricksPrismarine, l + j1, -1, i1 + k1, box);
                        }
                    }

                    if (j != 0 && j != 6)
                    {
                        k += 6;
                    }
                    else
                    {
                        ++k;
                    }
                }
            }
            for (int l1 = 0; l1 < 5; ++l1)
            {
                this.generateWaterBox(world, box, -1 - l1, 0 + l1 * 2, -1 - l1, -1 - l1, 23, 58 + l1, false);
                this.generateWaterBox(world, box, 58 + l1, 0 + l1 * 2, -1 - l1, 58 + l1, 23, 58 + l1, false);
                this.generateWaterBox(world, box, 0 - l1, 0 + l1 * 2, -1 - l1, 57 + l1, 23, -1 - l1, false);
                this.generateWaterBox(world, box, 0 - l1, 0 + l1 * 2, 58 + l1, 57 + l1, 23, 58 + l1, false);
            }
            for (Piece piece : this.childPieces)
            {
                if (piece.getBoundingBox().intersectsWith(box))
                {
                    piece.addComponentParts(world, rand, box);
                }
            }
            return true;
        }

        private void generateWing(boolean par0, int size, World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, size, 0, size + 23, 20))
            {
                this.fillWithBlocks(world, box, size + 0, 0, 0, size + 24, 0, 20, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, size + 0, 1, 0, size + 24, 10, 20, false);

                for (int j = 0; j < 4; ++j)
                {
                    this.fillWithBlocks(world, box, size + j, j + 1, j, size + j, j + 1, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, size + j + 7, j + 5, j + 7, size + j + 7, j + 5, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, size + 17 - j, j + 5, j + 7, size + 17 - j, j + 5, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, size + 24 - j, j + 1, j, size + 24 - j, j + 1, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, size + j + 1, j + 1, j, size + 23 - j, j + 1, j, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, size + j + 8, j + 5, j + 7, size + 16 - j, j + 5, j + 7, this.bricksPrismarine, this.bricksPrismarine, false);
                }

                this.fillWithBlocks(world, box, size + 4, 4, 4, size + 6, 4, 20, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, size + 7, 4, 4, size + 17, 4, 6, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, size + 18, 4, 4, size + 20, 4, 20, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, size + 11, 8, 11, size + 13, 8, 20, this.roughPrismarine, this.roughPrismarine, false);
                this.setBlockState(world, this.bricksPrismarine, size + 12, 9, 12, box);
                this.setBlockState(world, this.bricksPrismarine, size + 12, 9, 15, box);
                this.setBlockState(world, this.bricksPrismarine, size + 12, 9, 18, box);
                int j1 = size + (par0 ? 19 : 5);
                int k = size + (par0 ? 5 : 19);

                for (int l = 20; l >= 5; l -= 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, j1, 5, l, box);
                }
                for (int k1 = 19; k1 >= 7; k1 -= 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, k, 5, k1, box);
                }
                for (int l1 = 0; l1 < 4; ++l1)
                {
                    int i1 = par0 ? size + 24 - (17 - l1 * 3) : size + 17 - l1 * 3;
                    this.setBlockState(world, this.bricksPrismarine, i1, 5, 5, box);
                }
                this.setBlockState(world, this.bricksPrismarine, k, 5, 5, box);
                this.fillWithBlocks(world, box, size + 11, 1, 12, size + 13, 7, 12, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, size + 12, 1, 11, size + 12, 7, 13, this.roughPrismarine, this.roughPrismarine, false);
            }
        }

        private void generateEntranceArchs(World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, 22, 5, 35, 17))
            {
                this.generateWaterBox(world, box, 25, 0, 0, 32, 8, 20, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.setBlockState(world, this.bricksPrismarine, 25, 5, 5 + i * 4, box);
                    this.setBlockState(world, this.bricksPrismarine, 26, 6, 5 + i * 4, box);
                    this.setBlockState(world, this.seaLantern, 26, 5, 5 + i * 4, box);
                    this.fillWithBlocks(world, box, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.setBlockState(world, this.bricksPrismarine, 32, 5, 5 + i * 4, box);
                    this.setBlockState(world, this.bricksPrismarine, 31, 6, 5 + i * 4, box);
                    this.setBlockState(world, this.seaLantern, 31, 5, 5 + i * 4, box);
                    this.fillWithBlocks(world, box, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, this.roughPrismarine, this.roughPrismarine, false);
                }
            }
        }

        private void generateEntranceWall(World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, 15, 20, 42, 21))
            {
                this.fillWithBlocks(world, box, 15, 0, 21, 42, 0, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 26, 1, 21, 31, 3, 21, false);
                this.fillWithBlocks(world, box, 21, 12, 21, 36, 12, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 17, 11, 21, 40, 11, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 16, 10, 21, 41, 10, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 15, 7, 21, 42, 9, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 16, 6, 21, 41, 6, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 17, 5, 21, 40, 5, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 21, 4, 21, 36, 4, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 22, 3, 21, 26, 3, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 31, 3, 21, 35, 3, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 23, 2, 21, 25, 2, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 32, 2, 21, 34, 2, 21, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 28, 4, 20, 29, 4, 21, this.bricksPrismarine, this.bricksPrismarine, false);
                this.setBlockState(world, this.bricksPrismarine, 27, 3, 21, box);
                this.setBlockState(world, this.bricksPrismarine, 30, 3, 21, box);
                this.setBlockState(world, this.bricksPrismarine, 26, 2, 21, box);
                this.setBlockState(world, this.bricksPrismarine, 31, 2, 21, box);
                this.setBlockState(world, this.bricksPrismarine, 25, 1, 21, box);
                this.setBlockState(world, this.bricksPrismarine, 32, 1, 21, box);

                for (int i = 0; i < 7; ++i)
                {
                    this.setBlockState(world, this.darkPrismarine, 28 - i, 6 + i, 21, box);
                    this.setBlockState(world, this.darkPrismarine, 29 + i, 6 + i, 21, box);
                }
                for (int j = 0; j < 4; ++j)
                {
                    this.setBlockState(world, this.darkPrismarine, 28 - j, 9 + j, 21, box);
                    this.setBlockState(world, this.darkPrismarine, 29 + j, 9 + j, 21, box);
                }

                this.setBlockState(world, this.darkPrismarine, 28, 12, 21, box);
                this.setBlockState(world, this.darkPrismarine, 29, 12, 21, box);

                for (int k = 0; k < 3; ++k)
                {
                    this.setBlockState(world, this.darkPrismarine, 22 - k * 2, 8, 21, box);
                    this.setBlockState(world, this.darkPrismarine, 22 - k * 2, 9, 21, box);
                    this.setBlockState(world, this.darkPrismarine, 35 + k * 2, 8, 21, box);
                    this.setBlockState(world, this.darkPrismarine, 35 + k * 2, 9, 21, box);
                }

                this.generateWaterBox(world, box, 15, 13, 21, 42, 15, 21, false);
                this.generateWaterBox(world, box, 15, 1, 21, 15, 6, 21, false);
                this.generateWaterBox(world, box, 16, 1, 21, 16, 5, 21, false);
                this.generateWaterBox(world, box, 17, 1, 21, 20, 4, 21, false);
                this.generateWaterBox(world, box, 21, 1, 21, 21, 3, 21, false);
                this.generateWaterBox(world, box, 22, 1, 21, 22, 2, 21, false);
                this.generateWaterBox(world, box, 23, 1, 21, 24, 1, 21, false);
                this.generateWaterBox(world, box, 42, 1, 21, 42, 6, 21, false);
                this.generateWaterBox(world, box, 41, 1, 21, 41, 5, 21, false);
                this.generateWaterBox(world, box, 37, 1, 21, 40, 4, 21, false);
                this.generateWaterBox(world, box, 36, 1, 21, 36, 3, 21, false);
                this.generateWaterBox(world, box, 33, 1, 21, 34, 1, 21, false);
                this.generateWaterBox(world, box, 35, 1, 21, 35, 2, 21, false);
            }
        }

        private void generateRoofPiece(World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, 21, 21, 36, 36))
            {
                this.fillWithBlocks(world, box, 21, 0, 22, 36, 0, 36, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 21, 1, 22, 36, 23, 36, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                this.fillWithBlocks(world, box, 25, 16, 25, 32, 16, 32, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 25, 17, 25, 25, 19, 25, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 32, 17, 25, 32, 19, 25, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 25, 17, 32, 25, 19, 32, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 32, 17, 32, 32, 19, 32, this.bricksPrismarine, this.bricksPrismarine, false);
                this.setBlockState(world, this.bricksPrismarine, 26, 20, 26, box);
                this.setBlockState(world, this.bricksPrismarine, 27, 21, 27, box);
                this.setBlockState(world, this.seaLantern, 27, 20, 27, box);
                this.setBlockState(world, this.bricksPrismarine, 26, 20, 31, box);
                this.setBlockState(world, this.bricksPrismarine, 27, 21, 30, box);
                this.setBlockState(world, this.seaLantern, 27, 20, 30, box);
                this.setBlockState(world, this.bricksPrismarine, 31, 20, 31, box);
                this.setBlockState(world, this.bricksPrismarine, 30, 21, 30, box);
                this.setBlockState(world, this.seaLantern, 30, 20, 30, box);
                this.setBlockState(world, this.bricksPrismarine, 31, 20, 26, box);
                this.setBlockState(world, this.bricksPrismarine, 30, 21, 27, box);
                this.setBlockState(world, this.seaLantern, 30, 20, 27, box);
                this.fillWithBlocks(world, box, 28, 21, 27, 29, 21, 27, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 27, 21, 28, 27, 21, 29, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 28, 21, 30, 29, 21, 30, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 30, 21, 28, 30, 21, 29, this.roughPrismarine, this.roughPrismarine, false);
            }
        }

        private void generateLowerWall(World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, 0, 21, 6, 58))
            {
                this.fillWithBlocks(world, box, 0, 0, 21, 6, 0, 57, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 0, 1, 21, 6, 7, 57, false);
                this.fillWithBlocks(world, box, 4, 4, 21, 6, 4, 53, this.roughPrismarine, this.roughPrismarine, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, i, i + 1, 21, i, i + 1, 57 - i, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int j = 23; j < 53; j += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, 5, 5, j, box);
                }

                this.setBlockState(world, this.bricksPrismarine, 5, 5, 52, box);

                for (int k = 0; k < 4; ++k)
                {
                    this.fillWithBlocks(world, box, k, k + 1, 21, k, k + 1, 57 - k, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                this.fillWithBlocks(world, box, 4, 1, 52, 6, 3, 52, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 5, 1, 51, 5, 3, 53, this.roughPrismarine, this.roughPrismarine, false);
            }

            if (this.doesChunkIntersect(box, 51, 21, 58, 58))
            {
                this.fillWithBlocks(world, box, 51, 0, 21, 57, 0, 57, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 51, 1, 21, 57, 7, 57, false);
                this.fillWithBlocks(world, box, 51, 4, 21, 53, 4, 53, this.roughPrismarine, this.roughPrismarine, false);

                for (int l = 0; l < 4; ++l)
                {
                    this.fillWithBlocks(world, box, 57 - l, l + 1, 21, 57 - l, l + 1, 57 - l, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int i1 = 23; i1 < 53; i1 += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, 52, 5, i1, box);
                }
                this.setBlockState(world, this.bricksPrismarine, 52, 5, 52, box);
                this.fillWithBlocks(world, box, 51, 1, 52, 53, 3, 52, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 52, 1, 51, 52, 3, 53, this.roughPrismarine, this.roughPrismarine, false);
            }

            if (this.doesChunkIntersect(box, 0, 51, 57, 57))
            {
                this.fillWithBlocks(world, box, 7, 0, 51, 50, 0, 57, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 7, 1, 51, 50, 10, 57, false);

                for (int j1 = 0; j1 < 4; ++j1)
                {
                    this.fillWithBlocks(world, box, j1 + 1, j1 + 1, 57 - j1, 56 - j1, j1 + 1, 57 - j1, this.bricksPrismarine, this.bricksPrismarine, false);
                }
            }
        }

        private void generateMiddleWall(World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, 7, 21, 13, 50))
            {
                this.fillWithBlocks(world, box, 7, 0, 21, 13, 0, 50, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 7, 1, 21, 13, 10, 50, false);
                this.fillWithBlocks(world, box, 11, 8, 21, 13, 8, 53, this.roughPrismarine, this.roughPrismarine, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, i + 7, i + 5, 21, i + 7, i + 5, 54, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int j = 21; j <= 45; j += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, 12, 9, j, box);
                }
            }

            if (this.doesChunkIntersect(box, 44, 21, 50, 54))
            {
                this.fillWithBlocks(world, box, 44, 0, 21, 50, 0, 50, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 44, 1, 21, 50, 10, 50, false);
                this.fillWithBlocks(world, box, 44, 8, 21, 46, 8, 53, this.roughPrismarine, this.roughPrismarine, false);

                for (int k = 0; k < 4; ++k)
                {
                    this.fillWithBlocks(world, box, 50 - k, k + 5, 21, 50 - k, k + 5, 54, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int l = 21; l <= 45; l += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, 45, 9, l, box);
                }
            }

            if (this.doesChunkIntersect(box, 8, 44, 49, 54))
            {
                this.fillWithBlocks(world, box, 14, 0, 44, 43, 0, 50, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 14, 1, 44, 43, 10, 50, false);

                for (int i1 = 12; i1 <= 45; i1 += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, i1, 9, 45, box);
                    this.setBlockState(world, this.bricksPrismarine, i1, 9, 52, box);

                    if (i1 == 12 || i1 == 18 || i1 == 24 || i1 == 33 || i1 == 39 || i1 == 45)
                    {
                        this.setBlockState(world, this.bricksPrismarine, i1, 9, 47, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 9, 50, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 10, 45, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 10, 46, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 10, 51, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 10, 52, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 11, 47, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 11, 50, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 12, 48, box);
                        this.setBlockState(world, this.bricksPrismarine, i1, 12, 49, box);
                    }
                }
                for (int j1 = 0; j1 < 3; ++j1)
                {
                    this.fillWithBlocks(world, box, 8 + j1, 5 + j1, 54, 49 - j1, 5 + j1, 54, this.roughPrismarine, this.roughPrismarine, false);
                }
                this.fillWithBlocks(world, box, 11, 8, 54, 46, 8, 54, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 14, 8, 44, 43, 8, 53, this.roughPrismarine, this.roughPrismarine, false);
            }
        }

        private void generateUpperWall(World world, StructureBoundingBox box)
        {
            if (this.doesChunkIntersect(box, 14, 21, 20, 43))
            {
                this.fillWithBlocks(world, box, 14, 0, 21, 20, 0, 43, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 14, 1, 22, 20, 14, 43, false);
                this.fillWithBlocks(world, box, 18, 12, 22, 20, 12, 39, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 18, 12, 21, 20, 12, 21, this.bricksPrismarine, this.bricksPrismarine, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, i + 14, i + 9, 21, i + 14, i + 9, 43 - i, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int j = 23; j <= 39; j += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, 19, 13, j, box);
                }
            }

            if (this.doesChunkIntersect(box, 37, 21, 43, 43))
            {
                this.fillWithBlocks(world, box, 37, 0, 21, 43, 0, 43, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 37, 1, 22, 43, 14, 43, false);
                this.fillWithBlocks(world, box, 37, 12, 22, 39, 12, 39, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 37, 12, 21, 39, 12, 21, this.bricksPrismarine, this.bricksPrismarine, false);

                for (int k = 0; k < 4; ++k)
                {
                    this.fillWithBlocks(world, box, 43 - k, k + 9, 21, 43 - k, k + 9, 43 - k, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int l = 23; l <= 39; l += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, 38, 13, l, box);
                }
            }

            if (this.doesChunkIntersect(box, 15, 37, 42, 43))
            {
                this.fillWithBlocks(world, box, 21, 0, 37, 36, 0, 43, this.roughPrismarine, this.roughPrismarine, false);
                this.generateWaterBox(world, box, 21, 1, 37, 36, 14, 43, false);
                this.fillWithBlocks(world, box, 21, 12, 37, 36, 12, 39, this.roughPrismarine, this.roughPrismarine, false);

                for (int i1 = 0; i1 < 4; ++i1)
                {
                    this.fillWithBlocks(world, box, 15 + i1, i1 + 9, 43 - i1, 42 - i1, i1 + 9, 43 - i1, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                for (int j1 = 21; j1 <= 36; j1 += 3)
                {
                    this.setBlockState(world, this.bricksPrismarine, j1, 13, 38, box);
                }
            }
        }
    }

    public static class MonumentCoreRoom extends Piece
    {
        public MonumentCoreRoom() {}

        public MonumentCoreRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 2, 2, 2);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.generateBoxOnFillOnly(world, box, 1, 8, 0, 14, 8, 14, this.roughPrismarine);
            int i = 7;
            IBlockState iblockstate = this.bricksPrismarine;
            this.fillWithBlocks(world, box, 0, 7, 0, 0, 7, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(world, box, 15, 7, 0, 15, 7, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(world, box, 1, 7, 0, 15, 7, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(world, box, 1, 7, 15, 14, 7, 15, iblockstate, iblockstate, false);

            for (i = 1; i <= 6; ++i)
            {
                iblockstate = this.bricksPrismarine;

                if (i == 2 || i == 6)
                {
                    iblockstate = this.roughPrismarine;
                }
                for (int j = 0; j <= 15; j += 15)
                {
                    this.fillWithBlocks(world, box, j, i, 0, j, i, 1, iblockstate, iblockstate, false);
                    this.fillWithBlocks(world, box, j, i, 6, j, i, 9, iblockstate, iblockstate, false);
                    this.fillWithBlocks(world, box, j, i, 14, j, i, 15, iblockstate, iblockstate, false);
                }
                this.fillWithBlocks(world, box, 1, i, 0, 1, i, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 6, i, 0, 9, i, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 14, i, 0, 14, i, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 15, 14, i, 15, iblockstate, iblockstate, false);
            }

            this.fillWithBlocks(world, box, 6, 3, 6, 9, 6, 9, this.darkPrismarine, this.darkPrismarine, false);
            boolean crystal = rand.nextInt(50) == 0;
            this.fillWithBlocks(world, box, 7, 4, 7, 8, 5, 8, crystal ? MPBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.GOLD_BLOCK.getDefaultState(), crystal ? MPBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.GOLD_BLOCK.getDefaultState(), false);

            for (i = 3; i <= 6; i += 3)
            {
                for (int k = 6; k <= 9; k += 3)
                {
                    this.setBlockState(world, this.seaLantern, k, i, 6, box);
                    this.setBlockState(world, this.seaLantern, k, i, 9, box);
                }
            }
            this.fillWithBlocks(world, box, 5, 1, 6, 5, 2, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 1, 9, 5, 2, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 1, 6, 10, 2, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 1, 9, 10, 2, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 1, 5, 6, 2, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 9, 1, 5, 9, 2, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, 1, 10, 6, 2, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 9, 1, 10, 9, 2, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 2, 5, 5, 6, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 2, 10, 5, 6, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 2, 5, 10, 6, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 2, 10, 10, 6, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 7, 1, 5, 7, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 7, 1, 10, 7, 6, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 5, 7, 9, 5, 7, 14, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 10, 7, 9, 10, 7, 14, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 7, 5, 6, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 7, 10, 6, 7, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 9, 7, 5, 14, 7, 5, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 9, 7, 10, 14, 7, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 1, 2, 2, 1, 3, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 2, 3, 1, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 13, 1, 2, 13, 1, 3, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 12, 1, 2, 12, 1, 2, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 2, 1, 12, 2, 1, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 13, 3, 1, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 13, 1, 12, 13, 1, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 12, 1, 13, 12, 1, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            return true;
        }
    }

    interface MonumentRoomFitHelper
    {
        boolean fits(RoomDefinition room);
        Piece create(EnumFacing facing, RoomDefinition room);
    }

    public static class Penthouse extends Piece
    {
        public Penthouse() {}

        public Penthouse(EnumFacing facing, StructureBoundingBox box)
        {
            super(facing, box);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithBlocks(world, box, 2, -1, 2, 11, -1, 11, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, -1, 0, 1, -1, 11, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 12, -1, 0, 13, -1, 11, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 2, -1, 0, 11, -1, 1, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 2, -1, 12, 11, -1, 13, this.roughPrismarine, this.roughPrismarine, false);
            this.fillWithBlocks(world, box, 0, 0, 0, 0, 0, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 13, 0, 0, 13, 0, 13, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 0, 0, 12, 0, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 0, 13, 12, 0, 13, this.bricksPrismarine, this.bricksPrismarine, false);

            for (int i = 2; i <= 11; i += 3)
            {
                this.setBlockState(world, this.seaLantern, 0, 0, i, box);
                this.setBlockState(world, this.seaLantern, 13, 0, i, box);
                this.setBlockState(world, this.seaLantern, i, 0, 0, box);
            }

            this.fillWithBlocks(world, box, 2, 0, 3, 4, 0, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 9, 0, 3, 11, 0, 9, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 4, 0, 9, 9, 0, 11, this.bricksPrismarine, this.bricksPrismarine, false);
            this.setBlockState(world, this.bricksPrismarine, 5, 0, 8, box);
            this.setBlockState(world, this.bricksPrismarine, 8, 0, 8, box);
            this.setBlockState(world, this.bricksPrismarine, 10, 0, 10, box);
            this.setBlockState(world, this.bricksPrismarine, 3, 0, 10, box);
            this.fillWithBlocks(world, box, 3, 0, 3, 3, 0, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 10, 0, 3, 10, 0, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 6, 0, 10, 7, 0, 10, this.darkPrismarine, this.darkPrismarine, false);
            int l = 3;

            for (int j = 0; j < 2; ++j)
            {
                for (int k = 2; k <= 8; k += 3)
                {
                    this.fillWithBlocks(world, box, l, 0, k, l, 2, k, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                l = 10;
            }
            this.fillWithBlocks(world, box, 5, 0, 10, 5, 2, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 8, 0, 10, 8, 2, 10, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 6, -1, 7, 7, -1, 8, this.darkPrismarine, this.darkPrismarine, false);
            this.generateWaterBox(world, box, 6, -1, 3, 7, -1, 4, false);
            this.spawnElder(world, box, 6, 1, 6);
            return true;
        }
    }

    public abstract static class Piece extends StructureComponent
    {
        protected IBlockState roughPrismarine = MPBlocks.INFECTED_PRISMARINE.getDefaultState();
        protected IBlockState bricksPrismarine = MPBlocks.INFECTED_PRISMARINE_BRICKS.getDefaultState();
        protected IBlockState darkPrismarine = MPBlocks.INFECTED_DARK_PRISMARINE.getDefaultState();
        protected IBlockState seaLantern = MPBlocks.INFECTED_SEA_LANTERN.getDefaultState();
        protected IBlockState water = MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();
        protected int gridroomSourceIndex = getRoomIndex(2, 0, 0);
        protected int gridroomTopConnectIndex = getRoomIndex(2, 2, 0);
        protected int gridroomLeftwingConnectIndex = getRoomIndex(0, 1, 0);
        protected int gridroomRightwingConnectIndex = getRoomIndex(4, 1, 0);
        protected RoomDefinition roomDefinition;

        protected static int getRoomIndex(int x, int y, int z)
        {
            return y * 25 + z * 5 + x;
        }

        public Piece()
        {
            super(0);
        }

        public Piece(int type)
        {
            super(type);
        }

        public Piece(EnumFacing facing, StructureBoundingBox box)
        {
            super(1);
            this.setCoordBaseMode(facing);
            this.boundingBox = box;
        }

        protected Piece(int type, EnumFacing facing, RoomDefinition room, int x, int y, int z)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.roomDefinition = room;
            int i = room.index;
            int j = i % 5;
            int k = i / 5 % 5;
            int l = i / 25;

            if (facing != EnumFacing.NORTH && facing != EnumFacing.SOUTH)
            {
                this.boundingBox = new StructureBoundingBox(0, 0, 0, z * 8 - 1, y * 4 - 1, x * 8 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(0, 0, 0, x * 8 - 1, y * 4 - 1, z * 8 - 1);
            }

            switch (facing)
            {
            case NORTH:
                this.boundingBox.offset(j * 8, l * 4, -(k + z) * 8 + 1);
                break;
            case SOUTH:
                this.boundingBox.offset(j * 8, l * 4, k * 8);
                break;
            case WEST:
                this.boundingBox.offset(-(k + z) * 8 + 1, l * 4, j * 8);
                break;
            default:
                this.boundingBox.offset(k * 8, l * 4, j * 8);
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt) {}

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager) {}

        protected void generateWaterBox(World world, StructureBoundingBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean isAir)
        {
            for (int i = minY; i <= maxY; ++i)
            {
                for (int j = minX; j <= maxX; ++j)
                {
                    for (int k = minZ; k <= maxZ; ++k)
                    {
                        if (!isAir || this.getBlockStateFromPos(world, j, i, k, box).getMaterial() != Material.AIR)
                        {
                            if (this.getYWithOffset(i) >= world.getSeaLevel())
                            {
                                this.setBlockState(world, Blocks.AIR.getDefaultState(), j, i, k, box);
                            }
                            else
                            {
                                this.setBlockState(world, this.water, j, i, k, box);
                            }
                        }
                    }
                }
            }
        }

        protected void generateDefaultFloor(World world, StructureBoundingBox box, int x, int z, boolean open)
        {
            if (open)
            {
                this.fillWithBlocks(world, box, x + 0, 0, z + 0, x + 2, 0, z + 8 - 1, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, x + 5, 0, z + 0, x + 8 - 1, 0, z + 8 - 1, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, x + 3, 0, z + 0, x + 4, 0, z + 2, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, x + 3, 0, z + 5, x + 4, 0, z + 8 - 1, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, x + 3, 0, z + 2, x + 4, 0, z + 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, x + 3, 0, z + 5, x + 4, 0, z + 5, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, x + 2, 0, z + 3, x + 2, 0, z + 4, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, x + 5, 0, z + 3, x + 5, 0, z + 4, this.bricksPrismarine, this.bricksPrismarine, false);
            }
            else
            {
                this.fillWithBlocks(world, box, x + 0, 0, z + 0, x + 8 - 1, 0, z + 8 - 1, this.roughPrismarine, this.roughPrismarine, false);
            }
        }

        protected void generateBoxOnFillOnly(World world, StructureBoundingBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, IBlockState state)
        {
            for (int i = minY; i <= maxY; ++i)
            {
                for (int j = minX; j <= maxX; ++j)
                {
                    for (int k = minZ; k <= maxZ; ++k)
                    {
                        if (this.getBlockStateFromPos(world, j, i, k, box) == this.water)
                        {
                            this.setBlockState(world, state, j, i, k, box);
                        }
                    }
                }
            }
        }

        protected boolean doesChunkIntersect(StructureBoundingBox box, int minX, int minZ, int maxX, int maxZ)
        {
            int i = this.getXWithOffset(minX, minZ);
            int j = this.getZWithOffset(minX, minZ);
            int k = this.getXWithOffset(maxX, maxZ);
            int l = this.getZWithOffset(maxX, maxZ);
            return box.intersectsWith(Math.min(i, k), Math.min(j, l), Math.max(i, k), Math.max(j, l));
        }

        protected boolean spawnElder(World world, StructureBoundingBox box, int x, int y, int z)
        {
            int i = this.getXWithOffset(x, z);
            int j = this.getYWithOffset(y);
            int k = this.getZWithOffset(x, z);

            if (box.isVecInside(new BlockPos(i, j, k)))
            {
                EntityInfectedElderGuardian entityguardian = new EntityInfectedElderGuardian(world);
                entityguardian.heal(entityguardian.getMaxHealth());
                entityguardian.setLocationAndAngles(i + 0.5D, j, k + 0.5D, 0.0F, 0.0F);
                entityguardian.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityguardian)), (IEntityLivingData)null);
                world.spawnEntity(entityguardian);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    static class RoomDefinition
    {
        int index;
        RoomDefinition[] connections = new RoomDefinition[6];
        boolean[] hasOpening = new boolean[6];
        boolean claimed;
        boolean isSource;
        int scanIndex;

        public RoomDefinition(int index)
        {
            this.index = index;
        }

        public void setConnection(EnumFacing facing, RoomDefinition room)
        {
            this.connections[facing.getIndex()] = room;
            room.connections[facing.getOpposite().getIndex()] = this;
        }

        public void updateOpenings()
        {
            for (int i = 0; i < 6; ++i)
            {
                this.hasOpening[i] = this.connections[i] != null;
            }
        }

        public boolean findSource(int index)
        {
            if (this.isSource)
            {
                return true;
            }
            else
            {
                this.scanIndex = index;

                for (int i = 0; i < 6; ++i)
                {
                    if (this.connections[i] != null && this.hasOpening[i] && this.connections[i].scanIndex != index && this.connections[i].findSource(index))
                    {
                        return true;
                    }
                }
                return false;
            }
        }

        public boolean isSpecial()
        {
            return this.index >= 75;
        }

        public int countOpenings()
        {
            int i = 0;

            for (int j = 0; j < 6; ++j)
            {
                if (this.hasOpening[j])
                {
                    ++i;
                }
            }
            return i;
        }
    }

    public static class SimpleRoom extends Piece
    {
        private int mainDesign;

        public SimpleRoom() {}

        public SimpleRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 1, 1, 1);
            this.mainDesign = new Random().nextInt(3);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
            }
            if (this.roomDefinition.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 4, 1, 6, 4, 6, this.roughPrismarine);
            }

            boolean flag = this.mainDesign != 0 && rand.nextBoolean() && !this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()] && !this.roomDefinition.hasOpening[EnumFacing.UP.getIndex()] && this.roomDefinition.countOpenings() > 1;

            if (this.mainDesign == 0)
            {
                this.fillWithBlocks(world, box, 0, 1, 0, 2, 1, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 3, 0, 2, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 2, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 0, 2, 2, 0, this.roughPrismarine, this.roughPrismarine, false);
                this.setBlockState(world, this.seaLantern, 1, 2, 1, box);
                this.fillWithBlocks(world, box, 5, 1, 0, 7, 1, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 5, 3, 0, 7, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 2, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 5, 2, 0, 6, 2, 0, this.roughPrismarine, this.roughPrismarine, false);
                this.setBlockState(world, this.seaLantern, 6, 2, 1, box);
                this.fillWithBlocks(world, box, 0, 1, 5, 2, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 3, 5, 2, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 2, 5, 0, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 7, 2, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
                this.setBlockState(world, this.seaLantern, 1, 2, 6, box);
                this.fillWithBlocks(world, box, 5, 1, 5, 7, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 5, 3, 5, 7, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 2, 5, 7, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 5, 2, 7, 6, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
                this.setBlockState(world, this.seaLantern, 6, 2, 6, box);

                if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 3, 3, 0, 4, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 3, 3, 0, 4, 3, 1, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 3, 2, 0, 4, 2, 0, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 3, 1, 0, 4, 1, 1, this.bricksPrismarine, this.bricksPrismarine, false);
                }

                if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 3, 3, 7, 4, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 3, 3, 6, 4, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 3, 2, 7, 4, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 3, 1, 6, 4, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                }

                if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 0, 3, 3, 0, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, 3, 3, 1, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 0, 2, 3, 0, 2, 4, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 0, 1, 3, 1, 1, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                }

                if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 7, 3, 3, 7, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 6, 3, 3, 7, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 7, 2, 3, 7, 2, 4, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 6, 1, 3, 7, 1, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                }
            }
            else if (this.mainDesign == 1)
            {
                this.fillWithBlocks(world, box, 2, 1, 2, 2, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 2, 1, 5, 2, 3, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 5, 1, 5, 5, 3, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 5, 1, 2, 5, 3, 2, this.bricksPrismarine, this.bricksPrismarine, false);
                this.setBlockState(world, this.seaLantern, 2, 2, 2, box);
                this.setBlockState(world, this.seaLantern, 2, 2, 5, box);
                this.setBlockState(world, this.seaLantern, 5, 2, 5, box);
                this.setBlockState(world, this.seaLantern, 5, 2, 2, box);
                this.fillWithBlocks(world, box, 0, 1, 0, 1, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 1, 1, 0, 3, 1, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 1, 7, 1, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 1, 6, 0, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 1, 7, 7, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 6, 7, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 1, 0, 7, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 1, 7, 3, 1, this.bricksPrismarine, this.bricksPrismarine, false);
                this.setBlockState(world, this.roughPrismarine, 1, 2, 0, box);
                this.setBlockState(world, this.roughPrismarine, 0, 2, 1, box);
                this.setBlockState(world, this.roughPrismarine, 1, 2, 7, box);
                this.setBlockState(world, this.roughPrismarine, 0, 2, 6, box);
                this.setBlockState(world, this.roughPrismarine, 6, 2, 7, box);
                this.setBlockState(world, this.roughPrismarine, 7, 2, 6, box);
                this.setBlockState(world, this.roughPrismarine, 6, 2, 0, box);
                this.setBlockState(world, this.roughPrismarine, 7, 2, 1, box);

                if (!this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 1, 3, 0, 6, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 1, 2, 0, 6, 2, 0, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 1, 1, 0, 6, 1, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                if (!this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 1, 3, 7, 6, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 1, 2, 7, 6, 2, 7, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 1, 1, 7, 6, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                if (!this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 0, 3, 1, 0, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 0, 2, 1, 0, 2, 6, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 0, 1, 1, 0, 1, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                }
                if (!this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 7, 3, 1, 7, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, 7, 2, 1, 7, 2, 6, this.roughPrismarine, this.roughPrismarine, false);
                    this.fillWithBlocks(world, box, 7, 1, 1, 7, 1, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                }
            }
            else if (this.mainDesign == 2)
            {
                this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 1, 0, 6, 1, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 1, 7, 6, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 0, 6, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 7, 6, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 3, 0, 7, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 3, 0, 6, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 1, 3, 7, 6, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 0, 1, 3, 0, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 3, 7, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 3, 1, 0, 4, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 3, 1, 7, 4, 2, 7, this.darkPrismarine, this.darkPrismarine, false);

                if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
                {
                    this.generateWaterBox(world, box, 3, 1, 0, 4, 2, 0, false);
                }
                if (this.roomDefinition.hasOpening[EnumFacing.NORTH.getIndex()])
                {
                    this.generateWaterBox(world, box, 3, 1, 7, 4, 2, 7, false);
                }
                if (this.roomDefinition.hasOpening[EnumFacing.WEST.getIndex()])
                {
                    this.generateWaterBox(world, box, 0, 1, 3, 0, 2, 4, false);
                }
                if (this.roomDefinition.hasOpening[EnumFacing.EAST.getIndex()])
                {
                    this.generateWaterBox(world, box, 7, 1, 3, 7, 2, 4, false);
                }
            }
            if (flag)
            {
                this.fillWithBlocks(world, box, 3, 1, 3, 4, 1, 4, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 3, 2, 3, 4, 2, 4, this.roughPrismarine, this.roughPrismarine, false);
                this.fillWithBlocks(world, box, 3, 3, 3, 4, 3, 4, this.bricksPrismarine, this.bricksPrismarine, false);
            }
            return true;
        }
    }

    public static class SimpleTopRoom extends Piece
    {
        public SimpleTopRoom() {}

        public SimpleTopRoom(EnumFacing facing, RoomDefinition room)
        {
            super(1, facing, room, 1, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.roomDefinition.index / 25 > 0)
            {
                this.generateDefaultFloor(world, box, 0, 0, this.roomDefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
            }

            if (this.roomDefinition.connections[EnumFacing.UP.getIndex()] == null)
            {
                this.generateBoxOnFillOnly(world, box, 1, 4, 1, 6, 4, 6, this.roughPrismarine);
            }

            for (int i = 1; i <= 6; ++i)
            {
                for (int j = 1; j <= 6; ++j)
                {
                    if (rand.nextInt(3) != 0)
                    {
                        int k = 2 + (rand.nextInt(4) == 0 ? 0 : 1);
                        this.fillWithBlocks(world, box, i, k, j, i, 3, j, MPBlocks.INFECTED_WET_SPONGE.getDefaultState(), MPBlocks.INFECTED_WET_SPONGE.getDefaultState(), false);
                    }
                }
            }

            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 6, 1, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 1, 7, 6, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 0, 6, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 7, 6, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 7, 3, 0, 7, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 0, 6, 3, 0, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 1, 3, 7, 6, 3, 7, this.bricksPrismarine, this.bricksPrismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 3, 0, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 7, 1, 3, 7, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 0, 4, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 7, 4, 2, 7, this.darkPrismarine, this.darkPrismarine, false);

            if (this.roomDefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
            {
                this.generateWaterBox(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            return true;
        }
    }

    public static class WingRoom extends Piece
    {
        private int mainDesign;

        public WingRoom() {}

        public WingRoom(EnumFacing facing, StructureBoundingBox box, int type)
        {
            super(facing, box);
            this.mainDesign = type & 1;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.mainDesign == 0)
            {
                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, 10 - i, 3 - i, 20 - i, 12 + i, 3 - i, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                }

                this.fillWithBlocks(world, box, 7, 0, 6, 15, 0, 16, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 6, 0, 6, 6, 3, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 16, 0, 6, 16, 3, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 7, 7, 1, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 15, 1, 7, 15, 1, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 6, 9, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 13, 1, 6, 15, 3, 6, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 8, 1, 7, 9, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 13, 1, 7, 14, 1, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 9, 0, 5, 13, 0, 5, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 10, 0, 7, 12, 0, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 8, 0, 10, 8, 0, 12, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 14, 0, 10, 14, 0, 12, this.darkPrismarine, this.darkPrismarine, false);

                for (int i1 = 18; i1 >= 7; i1 -= 3)
                {
                    this.setBlockState(world, this.seaLantern, 6, 3, i1, box);
                    this.setBlockState(world, this.seaLantern, 16, 3, i1, box);
                }
                this.setBlockState(world, this.seaLantern, 10, 0, 10, box);
                this.setBlockState(world, this.seaLantern, 12, 0, 10, box);
                this.setBlockState(world, this.seaLantern, 10, 0, 12, box);
                this.setBlockState(world, this.seaLantern, 12, 0, 12, box);
                this.setBlockState(world, this.seaLantern, 8, 3, 6, box);
                this.setBlockState(world, this.seaLantern, 14, 3, 6, box);
                this.setBlockState(world, this.bricksPrismarine, 4, 2, 4, box);
                this.setBlockState(world, this.seaLantern, 4, 1, 4, box);
                this.setBlockState(world, this.bricksPrismarine, 4, 0, 4, box);
                this.setBlockState(world, this.bricksPrismarine, 18, 2, 4, box);
                this.setBlockState(world, this.seaLantern, 18, 1, 4, box);
                this.setBlockState(world, this.bricksPrismarine, 18, 0, 4, box);
                this.setBlockState(world, this.bricksPrismarine, 4, 2, 18, box);
                this.setBlockState(world, this.seaLantern, 4, 1, 18, box);
                this.setBlockState(world, this.bricksPrismarine, 4, 0, 18, box);
                this.setBlockState(world, this.bricksPrismarine, 18, 2, 18, box);
                this.setBlockState(world, this.seaLantern, 18, 1, 18, box);
                this.setBlockState(world, this.bricksPrismarine, 18, 0, 18, box);
                this.setBlockState(world, this.bricksPrismarine, 9, 7, 20, box);
                this.setBlockState(world, this.bricksPrismarine, 13, 7, 20, box);
                this.fillWithBlocks(world, box, 6, 0, 21, 7, 4, 21, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 15, 0, 21, 16, 4, 21, this.bricksPrismarine, this.bricksPrismarine, false);
                this.spawnElder(world, box, 11, 2, 16);
            }
            else if (this.mainDesign == 1)
            {
                this.fillWithBlocks(world, box, 9, 3, 18, 13, 3, 20, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 9, 0, 18, 9, 2, 18, this.bricksPrismarine, this.bricksPrismarine, false);
                this.fillWithBlocks(world, box, 13, 0, 18, 13, 2, 18, this.bricksPrismarine, this.bricksPrismarine, false);
                int j1 = 9;

                for (int l = 0; l < 2; ++l)
                {
                    this.setBlockState(world, this.bricksPrismarine, j1, 6, 20, box);
                    this.setBlockState(world, this.seaLantern, j1, 5, 20, box);
                    this.setBlockState(world, this.bricksPrismarine, j1, 4, 20, box);
                    j1 = 13;
                }

                this.fillWithBlocks(world, box, 7, 3, 7, 15, 3, 14, this.bricksPrismarine, this.bricksPrismarine, false);
                j1 = 10;

                for (int k1 = 0; k1 < 2; ++k1)
                {
                    this.fillWithBlocks(world, box, j1, 0, 10, j1, 6, 10, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, j1, 0, 12, j1, 6, 12, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.setBlockState(world, this.seaLantern, j1, 0, 10, box);
                    this.setBlockState(world, this.seaLantern, j1, 0, 12, box);
                    this.setBlockState(world, this.seaLantern, j1, 4, 10, box);
                    this.setBlockState(world, this.seaLantern, j1, 4, 12, box);
                    j1 = 12;
                }

                j1 = 8;

                for (int l1 = 0; l1 < 2; ++l1)
                {
                    this.fillWithBlocks(world, box, j1, 0, 7, j1, 2, 7, this.bricksPrismarine, this.bricksPrismarine, false);
                    this.fillWithBlocks(world, box, j1, 0, 14, j1, 2, 14, this.bricksPrismarine, this.bricksPrismarine, false);
                    j1 = 14;
                }
                this.fillWithBlocks(world, box, 8, 3, 8, 8, 3, 13, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 14, 3, 8, 14, 3, 13, this.darkPrismarine, this.darkPrismarine, false);
                this.spawnElder(world, box, 11, 5, 13);
            }
            return true;
        }
    }

    static class XDoubleRoomFitHelper implements MonumentRoomFitHelper
    {
        private XDoubleRoomFitHelper() {}

        @Override
        public boolean fits(RoomDefinition room)
        {
            return room.hasOpening[EnumFacing.EAST.getIndex()] && !room.connections[EnumFacing.EAST.getIndex()].claimed;
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            room.claimed = true;
            room.connections[EnumFacing.EAST.getIndex()].claimed = true;
            return new DoubleXRoom(facing, room);
        }
    }

    static class XYDoubleRoomFitHelper implements MonumentRoomFitHelper
    {
        private XYDoubleRoomFitHelper() {}

        @Override
        public boolean fits(RoomDefinition room)
        {
            if (room.hasOpening[EnumFacing.EAST.getIndex()] && !room.connections[EnumFacing.EAST.getIndex()].claimed && room.hasOpening[EnumFacing.UP.getIndex()] && !room.connections[EnumFacing.UP.getIndex()].claimed)
            {
                RoomDefinition definition = room.connections[EnumFacing.EAST.getIndex()];
                return definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed;
            }
            else
            {
                return false;
            }
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            room.claimed = true;
            room.connections[EnumFacing.EAST.getIndex()].claimed = true;
            room.connections[EnumFacing.UP.getIndex()].claimed = true;
            room.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
            return new DoubleXYRoom(facing, room);
        }
    }

    static class YDoubleRoomFitHelper implements MonumentRoomFitHelper
    {
        private YDoubleRoomFitHelper() {}

        @Override
        public boolean fits(RoomDefinition room)
        {
            return room.hasOpening[EnumFacing.UP.getIndex()] && !room.connections[EnumFacing.UP.getIndex()].claimed;
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            room.claimed = true;
            room.connections[EnumFacing.UP.getIndex()].claimed = true;
            return new DoubleYRoom(facing, room);
        }
    }

    static class YZDoubleRoomFitHelper implements MonumentRoomFitHelper
    {
        private YZDoubleRoomFitHelper() {}

        @Override
        public boolean fits(RoomDefinition definition)
        {
            if (definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed && definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed)
            {
                RoomDefinition roomdefinition = definition.connections[EnumFacing.NORTH.getIndex()];
                return roomdefinition.hasOpening[EnumFacing.UP.getIndex()] && !roomdefinition.connections[EnumFacing.UP.getIndex()].claimed;
            }
            else
            {
                return false;
            }
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            room.claimed = true;
            room.connections[EnumFacing.NORTH.getIndex()].claimed = true;
            room.connections[EnumFacing.UP.getIndex()].claimed = true;
            room.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
            return new DoubleYZRoom(facing, room);
        }
    }

    static class ZDoubleRoomFitHelper implements MonumentRoomFitHelper
    {
        private ZDoubleRoomFitHelper() {}

        @Override
        public boolean fits(RoomDefinition definition)
        {
            return definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed;
        }

        @Override
        public Piece create(EnumFacing facing, RoomDefinition room)
        {
            RoomDefinition roomdefinition = room;

            if (!room.hasOpening[EnumFacing.NORTH.getIndex()] || room.connections[EnumFacing.NORTH.getIndex()].claimed)
            {
                roomdefinition = room.connections[EnumFacing.SOUTH.getIndex()];
            }
            roomdefinition.claimed = true;
            roomdefinition.connections[EnumFacing.NORTH.getIndex()].claimed = true;
            return new DoubleZRoom(facing, roomdefinition);
        }
    }
}
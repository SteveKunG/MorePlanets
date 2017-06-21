package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

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
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedGuardian;

public class StructureNibiruOceanMonumentPieces
{
    public static void registerOceanMonumentPieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.MonumentBuilding.class, "NOMB");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.MonumentCoreRoom.class, "NOMCR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.DoubleXRoom.class, "NOMDXR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.DoubleXYRoom.class, "NOMDXYR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.DoubleYRoom.class, "NOMDYR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.DoubleYZRoom.class, "NOMDYZR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.DoubleZRoom.class, "NOMDZR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.EntryRoom.class, "NOMEntry");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.Penthouse.class, "NOMPenthouse");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.SimpleRoom.class, "NOMSimple");
        MapGenStructureIO.registerStructureComponent(StructureNibiruOceanMonumentPieces.SimpleTopRoom.class, "NOMSimpleT");
    }

    public static class DoubleXRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public DoubleXRoom() {}

        public DoubleXRoom(EnumFacing p_i45597_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45597_2_, Random p_i45597_3_)
        {
            super(1, p_i45597_1_, p_i45597_2_, 2, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = this.field_175830_k.field_175965_b[EnumFacing.EAST.getIndex()];
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.field_175830_k;

            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 8, 0, structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.DOWN.getIndex()]);
                this.func_175821_a(world, box, 0, 0, structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }

            if (structureoceanmonumentpieces$roomdefinition1.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 4, 1, 7, 4, 6, this.prismarine);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 8, 4, 1, 14, 4, 6, this.prismarine);
            }

            this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 15, 3, 0, 15, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 0, 15, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 7, 14, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 7, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 15, 2, 0, 15, 2, 7, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 0, 15, 2, 0, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 7, 14, 2, 7, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 15, 1, 0, 15, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 15, 1, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 7, 14, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 1, 0, 10, 1, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 2, 0, 9, 2, 3, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 5, 3, 0, 10, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.setBlockState(world, this.seaLantern, 6, 2, 3, box);
            this.setBlockState(world, this.seaLantern, 9, 2, 3, box);

            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 7, 4, 2, 7, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 11, 1, 0, 12, 2, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 11, 1, 7, 12, 2, 7, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 15, 1, 3, 15, 2, 4, false);
            }
            return true;
        }
    }

    public static class DoubleXYRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public DoubleXYRoom() {}

        public DoubleXYRoom(EnumFacing p_i45596_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45596_2_, Random p_i45596_3_)
        {
            super(1, p_i45596_1_, p_i45596_2_, 2, 2, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = this.field_175830_k.field_175965_b[EnumFacing.EAST.getIndex()];
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.field_175830_k;
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition2 = structureoceanmonumentpieces$roomdefinition1.field_175965_b[EnumFacing.UP.getIndex()];
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition3 = structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()];

            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 8, 0, structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.DOWN.getIndex()]);
                this.func_175821_a(world, box, 0, 0, structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }

            if (structureoceanmonumentpieces$roomdefinition2.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 8, 1, 7, 8, 6, this.prismarine);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 8, 8, 1, 14, 8, 6, this.prismarine);
            }

            for (int i = 1; i <= 7; ++i)
            {
                IBlockState iblockstate = this.prismarineBricks;

                if (i == 2 || i == 6)
                {
                    iblockstate = this.prismarine;
                }
                this.fillWithBlocks(world, box, 0, i, 0, 0, i, 7, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 15, i, 0, 15, i, 7, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 0, 15, i, 0, iblockstate, iblockstate, false);
                this.fillWithBlocks(world, box, 1, i, 7, 14, i, 7, iblockstate, iblockstate, false);
            }

            this.fillWithBlocks(world, box, 2, 1, 3, 2, 7, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 3, 1, 2, 4, 7, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 3, 1, 5, 4, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 13, 1, 3, 13, 7, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 11, 1, 2, 12, 7, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 11, 1, 5, 12, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 1, 3, 5, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 1, 3, 10, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 7, 2, 10, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 5, 2, 5, 7, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 5, 2, 10, 7, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 5, 5, 5, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 5, 5, 10, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.setBlockState(world, this.prismarineBricks, 6, 6, 2, box);
            this.setBlockState(world, this.prismarineBricks, 9, 6, 2, box);
            this.setBlockState(world, this.prismarineBricks, 6, 6, 5, box);
            this.setBlockState(world, this.prismarineBricks, 9, 6, 5, box);
            this.fillWithBlocks(world, box, 5, 4, 3, 6, 4, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 9, 4, 3, 10, 4, 4, this.prismarineBricks, this.prismarineBricks, false);
            this.setBlockState(world, this.seaLantern, 5, 4, 2, box);
            this.setBlockState(world, this.seaLantern, 5, 4, 5, box);
            this.setBlockState(world, this.seaLantern, 10, 4, 2, box);
            this.setBlockState(world, this.seaLantern, 10, 4, 5, box);

            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 7, 4, 2, 7, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 11, 1, 0, 12, 2, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 11, 1, 7, 12, 2, 7, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 15, 1, 3, 15, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 5, 0, 4, 6, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 5, 7, 4, 6, 7, false);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 5, 3, 0, 6, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 11, 5, 0, 12, 6, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 11, 5, 7, 12, 6, 7, false);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 15, 5, 3, 15, 6, 4, false);
            }
            return true;
        }
    }

    public static class DoubleYRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public DoubleYRoom() {}

        public DoubleYRoom(EnumFacing p_i45595_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45595_2_, Random p_i45595_3_)
        {
            super(1, p_i45595_1_, p_i45595_2_, 1, 2, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }

            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = this.field_175830_k.field_175965_b[EnumFacing.UP.getIndex()];

            if (structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 8, 1, 6, 8, 6, this.prismarine);
            }

            this.fillWithBlocks(world, box, 0, 4, 0, 0, 4, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 7, 4, 0, 7, 4, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 4, 0, 6, 4, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 4, 7, 6, 4, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 4, 1, 2, 4, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 4, 2, 1, 4, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 4, 1, 5, 4, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 4, 2, 6, 4, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 4, 5, 2, 4, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 4, 5, 1, 4, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 4, 5, 5, 4, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 4, 5, 6, 4, 5, this.prismarineBricks, this.prismarineBricks, false);
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.field_175830_k;

            for (int i = 1; i <= 5; i += 4)
            {
                int j = 0;

                if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.SOUTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 2, i, j, 2, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 5, i, j, 5, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 3, i + 2, j, 4, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, i, j, 7, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 0, i + 1, j, 7, i + 1, j, this.prismarine, this.prismarine, false);
                }

                j = 7;

                if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.NORTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 2, i, j, 2, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 5, i, j, 5, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 3, i + 2, j, 4, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, i, j, 7, i + 2, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 0, i + 1, j, 7, i + 1, j, this.prismarine, this.prismarine, false);
                }

                int k = 0;

                if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.WEST.getIndex()])
                {
                    this.fillWithBlocks(world, box, k, i, 2, k, i + 2, 2, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, k, i, 5, k, i + 2, 5, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, k, i + 2, 3, k, i + 2, 4, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, k, i, 0, k, i + 2, 7, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, k, i + 1, 0, k, i + 1, 7, this.prismarine, this.prismarine, false);
                }

                k = 7;

                if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.EAST.getIndex()])
                {
                    this.fillWithBlocks(world, box, k, i, 2, k, i + 2, 2, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, k, i, 5, k, i + 2, 5, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, k, i + 2, 3, k, i + 2, 4, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, k, i, 0, k, i + 2, 7, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, k, i + 1, 0, k, i + 1, 7, this.prismarine, this.prismarine, false);
                }
                structureoceanmonumentpieces$roomdefinition1 = structureoceanmonumentpieces$roomdefinition;
            }
            return true;
        }
    }

    public static class DoubleYZRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public DoubleYZRoom() {}

        public DoubleYZRoom(EnumFacing p_i45594_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45594_2_, Random p_i45594_3_)
        {
            super(1, p_i45594_1_, p_i45594_2_, 1, 2, 2);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = this.field_175830_k.field_175965_b[EnumFacing.NORTH.getIndex()];
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.field_175830_k;
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition2 = structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()];
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition3 = structureoceanmonumentpieces$roomdefinition1.field_175965_b[EnumFacing.UP.getIndex()];

            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 0, 8, structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.DOWN.getIndex()]);
                this.func_175821_a(world, box, 0, 0, structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }

            if (structureoceanmonumentpieces$roomdefinition3.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 8, 1, 6, 8, 7, this.prismarine);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 8, 8, 6, 8, 14, this.prismarine);
            }

            for (int i = 1; i <= 7; ++i)
            {
                IBlockState iblockstate = this.prismarineBricks;

                if (i == 2 || i == 6)
                {
                    iblockstate = this.prismarine;
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

            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 7, 1, 3, 7, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 15, 4, 2, 15, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 11, 0, 2, 12, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 7, 1, 11, 7, 2, 12, false);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 5, 0, 4, 6, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 7, 5, 3, 7, 6, 4, false);
                this.fillWithBlocks(world, box, 5, 4, 2, 6, 4, 5, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 1, 2, 6, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 1, 5, 6, 3, 5, this.prismarineBricks, this.prismarineBricks, false);
            }
            if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 5, 3, 0, 6, 4, false);
                this.fillWithBlocks(world, box, 1, 4, 2, 2, 4, 5, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 1, 2, 1, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 1, 5, 1, 3, 5, this.prismarineBricks, this.prismarineBricks, false);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 5, 15, 4, 6, 15, false);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 5, 11, 0, 6, 12, false);
                this.fillWithBlocks(world, box, 1, 4, 10, 2, 4, 13, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 1, 10, 1, 3, 10, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 1, 13, 1, 3, 13, this.prismarineBricks, this.prismarineBricks, false);
            }
            if (structureoceanmonumentpieces$roomdefinition2.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 7, 5, 11, 7, 6, 12, false);
                this.fillWithBlocks(world, box, 5, 4, 10, 6, 4, 13, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 1, 10, 6, 3, 10, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 1, 13, 6, 3, 13, this.prismarineBricks, this.prismarineBricks, false);
            }
            return true;
        }
    }

    public static class DoubleZRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public DoubleZRoom() {}

        public DoubleZRoom(EnumFacing p_i45593_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45593_2_, Random p_i45593_3_)
        {
            super(1, p_i45593_1_, p_i45593_2_, 1, 1, 2);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = this.field_175830_k.field_175965_b[EnumFacing.NORTH.getIndex()];
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.field_175830_k;

            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 0, 8, structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.DOWN.getIndex()]);
                this.func_175821_a(world, box, 0, 0, structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }

            if (structureoceanmonumentpieces$roomdefinition1.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 4, 1, 6, 4, 7, this.prismarine);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 4, 8, 6, 4, 14, this.prismarine);
            }

            this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 15, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 7, 3, 0, 7, 3, 15, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 0, 7, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 15, 6, 3, 15, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 15, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 15, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 0, 7, 2, 0, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 15, 6, 2, 15, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 15, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 15, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 7, 1, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 15, 6, 1, 15, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 1, 1, 1, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 1, 1, 6, 1, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 1, 1, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 3, 1, 6, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 13, 1, 1, 14, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 1, 13, 6, 1, 14, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 13, 1, 3, 14, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 3, 13, 6, 3, 14, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 1, 6, 2, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 1, 6, 5, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 1, 9, 2, 3, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 1, 9, 5, 3, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 3, 2, 6, 4, 2, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 3, 2, 9, 4, 2, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 2, 7, 2, 2, 8, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 2, 7, 5, 2, 8, this.prismarineBricks, this.prismarineBricks, false);
            this.setBlockState(world, this.seaLantern, 2, 2, 5, box);
            this.setBlockState(world, this.seaLantern, 5, 2, 5, box);
            this.setBlockState(world, this.seaLantern, 2, 2, 10, box);
            this.setBlockState(world, this.seaLantern, 5, 2, 10, box);
            this.setBlockState(world, this.prismarineBricks, 2, 3, 5, box);
            this.setBlockState(world, this.prismarineBricks, 5, 3, 5, box);
            this.setBlockState(world, this.prismarineBricks, 2, 3, 10, box);
            this.setBlockState(world, this.prismarineBricks, 5, 3, 10, box);

            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 7, 1, 3, 7, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition1.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 3, 0, 2, 4, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 15, 4, 2, 15, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 11, 0, 2, 12, false);
            }
            if (structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 7, 1, 11, 7, 2, 12, false);
            }
            return true;
        }
    }

    public static class EntryRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public EntryRoom() {}

        public EntryRoom(EnumFacing p_i45592_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45592_2_)
        {
            super(1, p_i45592_1_, p_i45592_2_, 1, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithBlocks(world, box, 0, 3, 0, 2, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 3, 0, 7, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 1, 2, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 2, 0, 7, 2, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 1, 7, 7, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 2, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 1, 0, 6, 3, 0, this.prismarineBricks, this.prismarineBricks, false);

            if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 7, 4, 2, 7, false);
            }
            if (this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()])
            {
                this.func_181655_a(world, box, 0, 1, 3, 1, 2, 4, false);
            }
            if (this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
            {
                this.func_181655_a(world, box, 6, 1, 3, 7, 2, 4, false);
            }
            return true;
        }
    }

    static class FitSimpleRoomHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private FitSimpleRoomHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            return true;
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            p_175968_2_.field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.SimpleRoom(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    static class FitSimpleRoomTopHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private FitSimpleRoomTopHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            return !p_175969_1_.field_175966_c[EnumFacing.WEST.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.EAST.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.NORTH.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.SOUTH.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()];
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            p_175968_2_.field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.SimpleTopRoom(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    public static class MonumentBuilding extends StructureNibiruOceanMonumentPieces.Piece
    {
        private StructureNibiruOceanMonumentPieces.RoomDefinition field_175845_o;
        private StructureNibiruOceanMonumentPieces.RoomDefinition field_175844_p;
        private List<StructureNibiruOceanMonumentPieces.Piece> field_175843_q = Lists.<StructureNibiruOceanMonumentPieces.Piece>newArrayList();

        public MonumentBuilding() {}

        public MonumentBuilding(Random p_i45599_1_, int p_i45599_2_, int p_i45599_3_, EnumFacing p_i45599_4_)
        {
            super(0);
            this.coordBaseMode = p_i45599_4_;

            switch (this.coordBaseMode)
            {
            case NORTH:
            case SOUTH:
                this.boundingBox = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
            }

            List<StructureNibiruOceanMonumentPieces.RoomDefinition> list = this.func_175836_a(p_i45599_1_);
            this.field_175845_o.field_175963_d = true;
            this.field_175843_q.add(new StructureNibiruOceanMonumentPieces.EntryRoom(this.coordBaseMode, this.field_175845_o));
            this.field_175843_q.add(new StructureNibiruOceanMonumentPieces.MonumentCoreRoom(this.coordBaseMode, this.field_175844_p, p_i45599_1_));
            List<StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper> list1 = Lists.<StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper>newArrayList();
            list1.add(new StructureNibiruOceanMonumentPieces.XYDoubleRoomFitHelper());
            list1.add(new StructureNibiruOceanMonumentPieces.YZDoubleRoomFitHelper());
            list1.add(new StructureNibiruOceanMonumentPieces.ZDoubleRoomFitHelper());
            list1.add(new StructureNibiruOceanMonumentPieces.XDoubleRoomFitHelper());
            list1.add(new StructureNibiruOceanMonumentPieces.YDoubleRoomFitHelper());
            list1.add(new StructureNibiruOceanMonumentPieces.FitSimpleRoomTopHelper());
            list1.add(new StructureNibiruOceanMonumentPieces.FitSimpleRoomHelper());
            label319:

                for (StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition : list)
                {
                    if (!structureoceanmonumentpieces$roomdefinition.field_175963_d && !structureoceanmonumentpieces$roomdefinition.func_175961_b())
                    {
                        Iterator iterator = list1.iterator();
                        StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper structureoceanmonumentpieces$monumentroomfithelper;

                        while (true)
                        {
                            if (!iterator.hasNext())
                            {
                                continue label319;
                            }

                            structureoceanmonumentpieces$monumentroomfithelper = (StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper)iterator.next();

                            if (structureoceanmonumentpieces$monumentroomfithelper.func_175969_a(structureoceanmonumentpieces$roomdefinition))
                            {
                                break;
                            }
                        }
                        this.field_175843_q.add(structureoceanmonumentpieces$monumentroomfithelper.func_175968_a(this.coordBaseMode, structureoceanmonumentpieces$roomdefinition, p_i45599_1_));
                    }
                }

            int j = this.boundingBox.minY;
            int k = this.getXWithOffset(9, 22);
            int l = this.getZWithOffset(9, 22);

            for (StructureNibiruOceanMonumentPieces.Piece structureoceanmonumentpieces$piece : this.field_175843_q)
            {
                structureoceanmonumentpieces$piece.getBoundingBox().offset(k, j, l);
            }
            StructureBoundingBox structureboundingbox1 = StructureBoundingBox.func_175899_a(this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1), this.getXWithOffset(23, 21), this.getYWithOffset(8), this.getZWithOffset(23, 21));
            StructureBoundingBox structureboundingbox2 = StructureBoundingBox.func_175899_a(this.getXWithOffset(34, 1), this.getYWithOffset(1), this.getZWithOffset(34, 1), this.getXWithOffset(56, 21), this.getYWithOffset(8), this.getZWithOffset(56, 21));
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175899_a(this.getXWithOffset(22, 22), this.getYWithOffset(13), this.getZWithOffset(22, 22), this.getXWithOffset(35, 35), this.getYWithOffset(17), this.getZWithOffset(35, 35));
            int i = p_i45599_1_.nextInt();
            this.field_175843_q.add(new StructureNibiruOceanMonumentPieces.WingRoom(this.coordBaseMode, structureboundingbox1, i++));
            this.field_175843_q.add(new StructureNibiruOceanMonumentPieces.WingRoom(this.coordBaseMode, structureboundingbox2, i++));
            this.field_175843_q.add(new StructureNibiruOceanMonumentPieces.Penthouse(this.coordBaseMode, structureboundingbox));
        }

        private List<StructureNibiruOceanMonumentPieces.RoomDefinition> func_175836_a(Random p_175836_1_)
        {
            StructureNibiruOceanMonumentPieces.RoomDefinition[] astructureoceanmonumentpieces$roomdefinition = new StructureNibiruOceanMonumentPieces.RoomDefinition[75];

            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 4; ++j)
                {
                    int k = 0;
                    int l = func_175820_a(i, k, j);
                    astructureoceanmonumentpieces$roomdefinition[l] = new StructureNibiruOceanMonumentPieces.RoomDefinition(l);
                }
            }
            for (int i2 = 0; i2 < 5; ++i2)
            {
                for (int l2 = 0; l2 < 4; ++l2)
                {
                    int k3 = 1;
                    int j4 = func_175820_a(i2, k3, l2);
                    astructureoceanmonumentpieces$roomdefinition[j4] = new StructureNibiruOceanMonumentPieces.RoomDefinition(j4);
                }
            }
            for (int j2 = 1; j2 < 4; ++j2)
            {
                for (int i3 = 0; i3 < 2; ++i3)
                {
                    int l3 = 2;
                    int k4 = func_175820_a(j2, l3, i3);
                    astructureoceanmonumentpieces$roomdefinition[k4] = new StructureNibiruOceanMonumentPieces.RoomDefinition(k4);
                }
            }

            this.field_175845_o = astructureoceanmonumentpieces$roomdefinition[this.field_175823_g];

            for (int k2 = 0; k2 < 5; ++k2)
            {
                for (int j3 = 0; j3 < 5; ++j3)
                {
                    for (int i4 = 0; i4 < 3; ++i4)
                    {
                        int l4 = func_175820_a(k2, i4, j3);

                        if (astructureoceanmonumentpieces$roomdefinition[l4] != null)
                        {
                            for (EnumFacing enumfacing : EnumFacing.VALUES)
                            {
                                int i1 = k2 + enumfacing.getFrontOffsetX();
                                int j1 = i4 + enumfacing.getFrontOffsetY();
                                int k1 = j3 + enumfacing.getFrontOffsetZ();

                                if (i1 >= 0 && i1 < 5 && k1 >= 0 && k1 < 5 && j1 >= 0 && j1 < 3)
                                {
                                    int l1 = func_175820_a(i1, j1, k1);

                                    if (astructureoceanmonumentpieces$roomdefinition[l1] != null)
                                    {
                                        if (k1 != j3)
                                        {
                                            astructureoceanmonumentpieces$roomdefinition[l4].func_175957_a(enumfacing.getOpposite(), astructureoceanmonumentpieces$roomdefinition[l1]);
                                        }
                                        else
                                        {
                                            astructureoceanmonumentpieces$roomdefinition[l4].func_175957_a(enumfacing, astructureoceanmonumentpieces$roomdefinition[l1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition;
            astructureoceanmonumentpieces$roomdefinition[this.field_175831_h].func_175957_a(EnumFacing.UP, structureoceanmonumentpieces$roomdefinition = new StructureNibiruOceanMonumentPieces.RoomDefinition(1003));
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition1;
            astructureoceanmonumentpieces$roomdefinition[this.field_175832_i].func_175957_a(EnumFacing.SOUTH, structureoceanmonumentpieces$roomdefinition1 = new StructureNibiruOceanMonumentPieces.RoomDefinition(1001));
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition2;
            astructureoceanmonumentpieces$roomdefinition[this.field_175829_j].func_175957_a(EnumFacing.SOUTH, structureoceanmonumentpieces$roomdefinition2 = new StructureNibiruOceanMonumentPieces.RoomDefinition(1002));
            structureoceanmonumentpieces$roomdefinition.field_175963_d = true;
            structureoceanmonumentpieces$roomdefinition1.field_175963_d = true;
            structureoceanmonumentpieces$roomdefinition2.field_175963_d = true;
            this.field_175845_o.field_175964_e = true;
            this.field_175844_p = astructureoceanmonumentpieces$roomdefinition[func_175820_a(p_175836_1_.nextInt(4), 0, 2)];
            this.field_175844_p.field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            List<StructureNibiruOceanMonumentPieces.RoomDefinition> list = Lists.<StructureNibiruOceanMonumentPieces.RoomDefinition>newArrayList();

            for (StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition4 : astructureoceanmonumentpieces$roomdefinition)
            {
                if (structureoceanmonumentpieces$roomdefinition4 != null)
                {
                    structureoceanmonumentpieces$roomdefinition4.func_175958_a();
                    list.add(structureoceanmonumentpieces$roomdefinition4);
                }
            }

            structureoceanmonumentpieces$roomdefinition.func_175958_a();
            Collections.shuffle(list, p_175836_1_);
            int i5 = 1;

            for (StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition3 : list)
            {
                int j5 = 0;
                int k5 = 0;

                while (j5 < 2 && k5 < 5)
                {
                    ++k5;
                    int l5 = p_175836_1_.nextInt(6);

                    if (structureoceanmonumentpieces$roomdefinition3.field_175966_c[l5])
                    {
                        int i6 = EnumFacing.getFront(l5).getOpposite().getIndex();
                        structureoceanmonumentpieces$roomdefinition3.field_175966_c[l5] = false;
                        structureoceanmonumentpieces$roomdefinition3.field_175965_b[l5].field_175966_c[i6] = false;

                        if (structureoceanmonumentpieces$roomdefinition3.func_175959_a(i5++) && structureoceanmonumentpieces$roomdefinition3.field_175965_b[l5].func_175959_a(i5++))
                        {
                            ++j5;
                        }
                        else
                        {
                            structureoceanmonumentpieces$roomdefinition3.field_175966_c[l5] = true;
                            structureoceanmonumentpieces$roomdefinition3.field_175965_b[l5].field_175966_c[i6] = true;
                        }
                    }
                }
            }
            list.add(structureoceanmonumentpieces$roomdefinition);
            list.add(structureoceanmonumentpieces$roomdefinition1);
            list.add(structureoceanmonumentpieces$roomdefinition2);
            return list;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            int i = Math.max(world.getSeaLevel(), 64) - this.boundingBox.minY;
            this.func_181655_a(world, box, 0, 0, 0, 58, i, 58, false);
            this.func_175840_a(false, 0, world, rand, box);
            this.func_175840_a(true, 33, world, rand, box);
            this.func_175839_b(world, rand, box);
            this.func_175837_c(world, rand, box);
            this.func_175841_d(world, rand, box);
            this.func_175835_e(world, rand, box);
            this.func_175842_f(world, rand, box);
            this.func_175838_g(world, rand, box);

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
                            this.setBlockState(world, this.prismarineBricks, l + j1, 0, i1 + k1, box);
                            this.replaceAirAndLiquidDownwards(world, this.prismarineBricks, l + j1, -1, i1 + k1, box);
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
                this.func_181655_a(world, box, -1 - l1, 0 + l1 * 2, -1 - l1, -1 - l1, 23, 58 + l1, false);
                this.func_181655_a(world, box, 58 + l1, 0 + l1 * 2, -1 - l1, 58 + l1, 23, 58 + l1, false);
                this.func_181655_a(world, box, 0 - l1, 0 + l1 * 2, -1 - l1, 57 + l1, 23, -1 - l1, false);
                this.func_181655_a(world, box, 0 - l1, 0 + l1 * 2, 58 + l1, 57 + l1, 23, 58 + l1, false);
            }

            for (StructureNibiruOceanMonumentPieces.Piece structureoceanmonumentpieces$piece : this.field_175843_q)
            {
                if (structureoceanmonumentpieces$piece.getBoundingBox().intersectsWith(box))
                {
                    structureoceanmonumentpieces$piece.addComponentParts(world, rand, box);
                }
            }
            return true;
        }

        private void func_175840_a(boolean p_175840_1_, int p_175840_2_, World world, Random p_175840_4_, StructureBoundingBox p_175840_5_)
        {
            if (this.func_175818_a(p_175840_5_, p_175840_2_, 0, p_175840_2_ + 23, 20))
            {
                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 0, 0, 0, p_175840_2_ + 24, 0, 20, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175840_5_, p_175840_2_ + 0, 1, 0, p_175840_2_ + 24, 10, 20, false);

                for (int j = 0; j < 4; ++j)
                {
                    this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + j, j + 1, j, p_175840_2_ + j, j + 1, 20, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + j + 7, j + 5, j + 7, p_175840_2_ + j + 7, j + 5, 20, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 17 - j, j + 5, j + 7, p_175840_2_ + 17 - j, j + 5, 20, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 24 - j, j + 1, j, p_175840_2_ + 24 - j, j + 1, 20, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + j + 1, j + 1, j, p_175840_2_ + 23 - j, j + 1, j, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + j + 8, j + 5, j + 7, p_175840_2_ + 16 - j, j + 5, j + 7, this.prismarineBricks, this.prismarineBricks, false);
                }

                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 4, 4, 4, p_175840_2_ + 6, 4, 20, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 7, 4, 4, p_175840_2_ + 17, 4, 6, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 18, 4, 4, p_175840_2_ + 20, 4, 20, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 11, 8, 11, p_175840_2_ + 13, 8, 20, this.prismarine, this.prismarine, false);
                this.setBlockState(world, this.prismarineBricks, p_175840_2_ + 12, 9, 12, p_175840_5_);
                this.setBlockState(world, this.prismarineBricks, p_175840_2_ + 12, 9, 15, p_175840_5_);
                this.setBlockState(world, this.prismarineBricks, p_175840_2_ + 12, 9, 18, p_175840_5_);
                int j1 = p_175840_1_ ? p_175840_2_ + 19 : p_175840_2_ + 5;
                int k = p_175840_1_ ? p_175840_2_ + 5 : p_175840_2_ + 19;

                for (int l = 20; l >= 5; l -= 3)
                {
                    this.setBlockState(world, this.prismarineBricks, j1, 5, l, p_175840_5_);
                }
                for (int k1 = 19; k1 >= 7; k1 -= 3)
                {
                    this.setBlockState(world, this.prismarineBricks, k, 5, k1, p_175840_5_);
                }
                for (int l1 = 0; l1 < 4; ++l1)
                {
                    int i1 = p_175840_1_ ? p_175840_2_ + 24 - (17 - l1 * 3) : p_175840_2_ + 17 - l1 * 3;
                    this.setBlockState(world, this.prismarineBricks, i1, 5, 5, p_175840_5_);
                }
                this.setBlockState(world, this.prismarineBricks, k, 5, 5, p_175840_5_);
                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 11, 1, 12, p_175840_2_ + 13, 7, 12, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175840_5_, p_175840_2_ + 12, 1, 11, p_175840_2_ + 12, 7, 13, this.prismarine, this.prismarine, false);
            }
        }

        private void func_175839_b(World world, Random p_175839_2_, StructureBoundingBox p_175839_3_)
        {
            if (this.func_175818_a(p_175839_3_, 22, 5, 35, 17))
            {
                this.func_181655_a(world, p_175839_3_, 25, 0, 0, 32, 8, 20, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, p_175839_3_, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175839_3_, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, this.prismarineBricks, this.prismarineBricks, false);
                    this.setBlockState(world, this.prismarineBricks, 25, 5, 5 + i * 4, p_175839_3_);
                    this.setBlockState(world, this.prismarineBricks, 26, 6, 5 + i * 4, p_175839_3_);
                    this.setBlockState(world, this.seaLantern, 26, 5, 5 + i * 4, p_175839_3_);
                    this.fillWithBlocks(world, p_175839_3_, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175839_3_, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, this.prismarineBricks, this.prismarineBricks, false);
                    this.setBlockState(world, this.prismarineBricks, 32, 5, 5 + i * 4, p_175839_3_);
                    this.setBlockState(world, this.prismarineBricks, 31, 6, 5 + i * 4, p_175839_3_);
                    this.setBlockState(world, this.seaLantern, 31, 5, 5 + i * 4, p_175839_3_);
                    this.fillWithBlocks(world, p_175839_3_, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, this.prismarine, this.prismarine, false);
                }
            }
        }

        private void func_175837_c(World world, Random p_175837_2_, StructureBoundingBox p_175837_3_)
        {
            if (this.func_175818_a(p_175837_3_, 15, 20, 42, 21))
            {
                this.fillWithBlocks(world, p_175837_3_, 15, 0, 21, 42, 0, 21, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175837_3_, 26, 1, 21, 31, 3, 21, false);
                this.fillWithBlocks(world, p_175837_3_, 21, 12, 21, 36, 12, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 17, 11, 21, 40, 11, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 16, 10, 21, 41, 10, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 15, 7, 21, 42, 9, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 16, 6, 21, 41, 6, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 17, 5, 21, 40, 5, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 21, 4, 21, 36, 4, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 22, 3, 21, 26, 3, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 31, 3, 21, 35, 3, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 23, 2, 21, 25, 2, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 32, 2, 21, 34, 2, 21, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175837_3_, 28, 4, 20, 29, 4, 21, this.prismarineBricks, this.prismarineBricks, false);
                this.setBlockState(world, this.prismarineBricks, 27, 3, 21, p_175837_3_);
                this.setBlockState(world, this.prismarineBricks, 30, 3, 21, p_175837_3_);
                this.setBlockState(world, this.prismarineBricks, 26, 2, 21, p_175837_3_);
                this.setBlockState(world, this.prismarineBricks, 31, 2, 21, p_175837_3_);
                this.setBlockState(world, this.prismarineBricks, 25, 1, 21, p_175837_3_);
                this.setBlockState(world, this.prismarineBricks, 32, 1, 21, p_175837_3_);

                for (int i = 0; i < 7; ++i)
                {
                    this.setBlockState(world, this.darkPrismarine, 28 - i, 6 + i, 21, p_175837_3_);
                    this.setBlockState(world, this.darkPrismarine, 29 + i, 6 + i, 21, p_175837_3_);
                }
                for (int j = 0; j < 4; ++j)
                {
                    this.setBlockState(world, this.darkPrismarine, 28 - j, 9 + j, 21, p_175837_3_);
                    this.setBlockState(world, this.darkPrismarine, 29 + j, 9 + j, 21, p_175837_3_);
                }

                this.setBlockState(world, this.darkPrismarine, 28, 12, 21, p_175837_3_);
                this.setBlockState(world, this.darkPrismarine, 29, 12, 21, p_175837_3_);

                for (int k = 0; k < 3; ++k)
                {
                    this.setBlockState(world, this.darkPrismarine, 22 - k * 2, 8, 21, p_175837_3_);
                    this.setBlockState(world, this.darkPrismarine, 22 - k * 2, 9, 21, p_175837_3_);
                    this.setBlockState(world, this.darkPrismarine, 35 + k * 2, 8, 21, p_175837_3_);
                    this.setBlockState(world, this.darkPrismarine, 35 + k * 2, 9, 21, p_175837_3_);
                }
                this.func_181655_a(world, p_175837_3_, 15, 13, 21, 42, 15, 21, false);
                this.func_181655_a(world, p_175837_3_, 15, 1, 21, 15, 6, 21, false);
                this.func_181655_a(world, p_175837_3_, 16, 1, 21, 16, 5, 21, false);
                this.func_181655_a(world, p_175837_3_, 17, 1, 21, 20, 4, 21, false);
                this.func_181655_a(world, p_175837_3_, 21, 1, 21, 21, 3, 21, false);
                this.func_181655_a(world, p_175837_3_, 22, 1, 21, 22, 2, 21, false);
                this.func_181655_a(world, p_175837_3_, 23, 1, 21, 24, 1, 21, false);
                this.func_181655_a(world, p_175837_3_, 42, 1, 21, 42, 6, 21, false);
                this.func_181655_a(world, p_175837_3_, 41, 1, 21, 41, 5, 21, false);
                this.func_181655_a(world, p_175837_3_, 37, 1, 21, 40, 4, 21, false);
                this.func_181655_a(world, p_175837_3_, 36, 1, 21, 36, 3, 21, false);
                this.func_181655_a(world, p_175837_3_, 33, 1, 21, 34, 1, 21, false);
                this.func_181655_a(world, p_175837_3_, 35, 1, 21, 35, 2, 21, false);
            }
        }

        private void func_175841_d(World world, Random p_175841_2_, StructureBoundingBox p_175841_3_)
        {
            if (this.func_175818_a(p_175841_3_, 21, 21, 36, 36))
            {
                this.fillWithBlocks(world, p_175841_3_, 21, 0, 22, 36, 0, 36, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175841_3_, 21, 1, 22, 36, 23, 36, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, p_175841_3_, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175841_3_, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175841_3_, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, p_175841_3_, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, this.prismarineBricks, this.prismarineBricks, false);
                }
                this.fillWithBlocks(world, p_175841_3_, 25, 16, 25, 32, 16, 32, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175841_3_, 25, 17, 25, 25, 19, 25, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175841_3_, 32, 17, 25, 32, 19, 25, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175841_3_, 25, 17, 32, 25, 19, 32, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175841_3_, 32, 17, 32, 32, 19, 32, this.prismarineBricks, this.prismarineBricks, false);
                this.setBlockState(world, this.prismarineBricks, 26, 20, 26, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 27, 21, 27, p_175841_3_);
                this.setBlockState(world, this.seaLantern, 27, 20, 27, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 26, 20, 31, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 27, 21, 30, p_175841_3_);
                this.setBlockState(world, this.seaLantern, 27, 20, 30, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 31, 20, 31, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 30, 21, 30, p_175841_3_);
                this.setBlockState(world, this.seaLantern, 30, 20, 30, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 31, 20, 26, p_175841_3_);
                this.setBlockState(world, this.prismarineBricks, 30, 21, 27, p_175841_3_);
                this.setBlockState(world, this.seaLantern, 30, 20, 27, p_175841_3_);
                this.fillWithBlocks(world, p_175841_3_, 28, 21, 27, 29, 21, 27, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175841_3_, 27, 21, 28, 27, 21, 29, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175841_3_, 28, 21, 30, 29, 21, 30, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175841_3_, 30, 21, 28, 30, 21, 29, this.prismarine, this.prismarine, false);
            }
        }

        private void func_175835_e(World world, Random p_175835_2_, StructureBoundingBox p_175835_3_)
        {
            if (this.func_175818_a(p_175835_3_, 0, 21, 6, 58))
            {
                this.fillWithBlocks(world, p_175835_3_, 0, 0, 21, 6, 0, 57, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175835_3_, 0, 1, 21, 6, 7, 57, false);
                this.fillWithBlocks(world, p_175835_3_, 4, 4, 21, 6, 4, 53, this.prismarine, this.prismarine, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, p_175835_3_, i, i + 1, 21, i, i + 1, 57 - i, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int j = 23; j < 53; j += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, 5, 5, j, p_175835_3_);
                }

                this.setBlockState(world, this.prismarineBricks, 5, 5, 52, p_175835_3_);

                for (int k = 0; k < 4; ++k)
                {
                    this.fillWithBlocks(world, p_175835_3_, k, k + 1, 21, k, k + 1, 57 - k, this.prismarineBricks, this.prismarineBricks, false);
                }
                this.fillWithBlocks(world, p_175835_3_, 4, 1, 52, 6, 3, 52, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175835_3_, 5, 1, 51, 5, 3, 53, this.prismarine, this.prismarine, false);
            }

            if (this.func_175818_a(p_175835_3_, 51, 21, 58, 58))
            {
                this.fillWithBlocks(world, p_175835_3_, 51, 0, 21, 57, 0, 57, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175835_3_, 51, 1, 21, 57, 7, 57, false);
                this.fillWithBlocks(world, p_175835_3_, 51, 4, 21, 53, 4, 53, this.prismarine, this.prismarine, false);

                for (int l = 0; l < 4; ++l)
                {
                    this.fillWithBlocks(world, p_175835_3_, 57 - l, l + 1, 21, 57 - l, l + 1, 57 - l, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int i1 = 23; i1 < 53; i1 += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, 52, 5, i1, p_175835_3_);
                }
                this.setBlockState(world, this.prismarineBricks, 52, 5, 52, p_175835_3_);
                this.fillWithBlocks(world, p_175835_3_, 51, 1, 52, 53, 3, 52, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175835_3_, 52, 1, 51, 52, 3, 53, this.prismarine, this.prismarine, false);
            }

            if (this.func_175818_a(p_175835_3_, 0, 51, 57, 57))
            {
                this.fillWithBlocks(world, p_175835_3_, 7, 0, 51, 50, 0, 57, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175835_3_, 7, 1, 51, 50, 10, 57, false);

                for (int j1 = 0; j1 < 4; ++j1)
                {
                    this.fillWithBlocks(world, p_175835_3_, j1 + 1, j1 + 1, 57 - j1, 56 - j1, j1 + 1, 57 - j1, this.prismarineBricks, this.prismarineBricks, false);
                }
            }
        }

        private void func_175842_f(World world, Random p_175842_2_, StructureBoundingBox p_175842_3_)
        {
            if (this.func_175818_a(p_175842_3_, 7, 21, 13, 50))
            {
                this.fillWithBlocks(world, p_175842_3_, 7, 0, 21, 13, 0, 50, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175842_3_, 7, 1, 21, 13, 10, 50, false);
                this.fillWithBlocks(world, p_175842_3_, 11, 8, 21, 13, 8, 53, this.prismarine, this.prismarine, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, p_175842_3_, i + 7, i + 5, 21, i + 7, i + 5, 54, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int j = 21; j <= 45; j += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, 12, 9, j, p_175842_3_);
                }
            }

            if (this.func_175818_a(p_175842_3_, 44, 21, 50, 54))
            {
                this.fillWithBlocks(world, p_175842_3_, 44, 0, 21, 50, 0, 50, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175842_3_, 44, 1, 21, 50, 10, 50, false);
                this.fillWithBlocks(world, p_175842_3_, 44, 8, 21, 46, 8, 53, this.prismarine, this.prismarine, false);

                for (int k = 0; k < 4; ++k)
                {
                    this.fillWithBlocks(world, p_175842_3_, 50 - k, k + 5, 21, 50 - k, k + 5, 54, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int l = 21; l <= 45; l += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, 45, 9, l, p_175842_3_);
                }
            }

            if (this.func_175818_a(p_175842_3_, 8, 44, 49, 54))
            {
                this.fillWithBlocks(world, p_175842_3_, 14, 0, 44, 43, 0, 50, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175842_3_, 14, 1, 44, 43, 10, 50, false);

                for (int i1 = 12; i1 <= 45; i1 += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, i1, 9, 45, p_175842_3_);
                    this.setBlockState(world, this.prismarineBricks, i1, 9, 52, p_175842_3_);

                    if (i1 == 12 || i1 == 18 || i1 == 24 || i1 == 33 || i1 == 39 || i1 == 45)
                    {
                        this.setBlockState(world, this.prismarineBricks, i1, 9, 47, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 9, 50, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 10, 45, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 10, 46, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 10, 51, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 10, 52, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 11, 47, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 11, 50, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 12, 48, p_175842_3_);
                        this.setBlockState(world, this.prismarineBricks, i1, 12, 49, p_175842_3_);
                    }
                }
                for (int j1 = 0; j1 < 3; ++j1)
                {
                    this.fillWithBlocks(world, p_175842_3_, 8 + j1, 5 + j1, 54, 49 - j1, 5 + j1, 54, this.prismarine, this.prismarine, false);
                }
                this.fillWithBlocks(world, p_175842_3_, 11, 8, 54, 46, 8, 54, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175842_3_, 14, 8, 44, 43, 8, 53, this.prismarine, this.prismarine, false);
            }
        }

        private void func_175838_g(World world, Random p_175838_2_, StructureBoundingBox p_175838_3_)
        {
            if (this.func_175818_a(p_175838_3_, 14, 21, 20, 43))
            {
                this.fillWithBlocks(world, p_175838_3_, 14, 0, 21, 20, 0, 43, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175838_3_, 14, 1, 22, 20, 14, 43, false);
                this.fillWithBlocks(world, p_175838_3_, 18, 12, 22, 20, 12, 39, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175838_3_, 18, 12, 21, 20, 12, 21, this.prismarineBricks, this.prismarineBricks, false);

                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, p_175838_3_, i + 14, i + 9, 21, i + 14, i + 9, 43 - i, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int j = 23; j <= 39; j += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, 19, 13, j, p_175838_3_);
                }
            }

            if (this.func_175818_a(p_175838_3_, 37, 21, 43, 43))
            {
                this.fillWithBlocks(world, p_175838_3_, 37, 0, 21, 43, 0, 43, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175838_3_, 37, 1, 22, 43, 14, 43, false);
                this.fillWithBlocks(world, p_175838_3_, 37, 12, 22, 39, 12, 39, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175838_3_, 37, 12, 21, 39, 12, 21, this.prismarineBricks, this.prismarineBricks, false);

                for (int k = 0; k < 4; ++k)
                {
                    this.fillWithBlocks(world, p_175838_3_, 43 - k, k + 9, 21, 43 - k, k + 9, 43 - k, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int l = 23; l <= 39; l += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, 38, 13, l, p_175838_3_);
                }
            }

            if (this.func_175818_a(p_175838_3_, 15, 37, 42, 43))
            {
                this.fillWithBlocks(world, p_175838_3_, 21, 0, 37, 36, 0, 43, this.prismarine, this.prismarine, false);
                this.func_181655_a(world, p_175838_3_, 21, 1, 37, 36, 14, 43, false);
                this.fillWithBlocks(world, p_175838_3_, 21, 12, 37, 36, 12, 39, this.prismarine, this.prismarine, false);

                for (int i1 = 0; i1 < 4; ++i1)
                {
                    this.fillWithBlocks(world, p_175838_3_, 15 + i1, i1 + 9, 43 - i1, 42 - i1, i1 + 9, 43 - i1, this.prismarineBricks, this.prismarineBricks, false);
                }
                for (int j1 = 21; j1 <= 36; j1 += 3)
                {
                    this.setBlockState(world, this.prismarineBricks, j1, 13, 38, p_175838_3_);
                }
            }
        }
    }

    public static class MonumentCoreRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public MonumentCoreRoom() {}

        public MonumentCoreRoom(EnumFacing p_i45598_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45598_2_, Random p_i45598_3_)
        {
            super(1, p_i45598_1_, p_i45598_2_, 2, 2, 2);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.func_175819_a(world, box, 1, 8, 0, 14, 8, 14, this.prismarine);
            int i = 7;
            IBlockState iblockstate = this.prismarineBricks;
            this.fillWithBlocks(world, box, 0, i, 0, 0, i, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(world, box, 15, i, 0, 15, i, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(world, box, 1, i, 0, 15, i, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(world, box, 1, i, 15, 14, i, 15, iblockstate, iblockstate, false);

            for (i = 1; i <= 6; ++i)
            {
                iblockstate = this.prismarineBricks;

                if (i == 2 || i == 6)
                {
                    iblockstate = this.prismarine;
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
            this.fillWithBlocks(world, box, 7, 4, 7, 8, 5, 8, rand.nextInt(50) == 0 ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.gold_block.getDefaultState(), rand.nextInt(50) == 0 ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.gold_block.getDefaultState(), false);

            for (i = 3; i <= 6; i += 3)
            {
                for (int k = 6; k <= 9; k += 3)
                {
                    this.setBlockState(world, this.seaLantern, k, i, 6, box);
                    this.setBlockState(world, this.seaLantern, k, i, 9, box);
                }
            }

            this.fillWithBlocks(world, box, 5, 1, 6, 5, 2, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 1, 9, 5, 2, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 1, 6, 10, 2, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 1, 9, 10, 2, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 1, 5, 6, 2, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 9, 1, 5, 9, 2, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, 1, 10, 6, 2, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 9, 1, 10, 9, 2, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 2, 5, 5, 6, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 2, 10, 5, 6, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 2, 5, 10, 6, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 2, 10, 10, 6, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 7, 1, 5, 7, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 7, 1, 10, 7, 6, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 5, 7, 9, 5, 7, 14, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 10, 7, 9, 10, 7, 14, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 7, 5, 6, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 7, 10, 6, 7, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 9, 7, 5, 14, 7, 5, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 9, 7, 10, 14, 7, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 1, 2, 2, 1, 3, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 3, 1, 2, 3, 1, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 13, 1, 2, 13, 1, 3, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 12, 1, 2, 12, 1, 2, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 2, 1, 12, 2, 1, 13, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 3, 1, 13, 3, 1, 13, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 13, 1, 12, 13, 1, 13, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 12, 1, 13, 12, 1, 13, this.prismarineBricks, this.prismarineBricks, false);
            return true;
        }
    }

    interface MonumentRoomFitHelper
    {
        boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_);
        StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_);
    }

    public static class Penthouse extends StructureNibiruOceanMonumentPieces.Piece
    {
        public Penthouse() {}

        public Penthouse(EnumFacing p_i45591_1_, StructureBoundingBox p_i45591_2_)
        {
            super(p_i45591_1_, p_i45591_2_);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithBlocks(world, box, 2, -1, 2, 11, -1, 11, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, -1, 0, 1, -1, 11, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 12, -1, 0, 13, -1, 11, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 2, -1, 0, 11, -1, 1, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 2, -1, 12, 11, -1, 13, this.prismarine, this.prismarine, false);
            this.fillWithBlocks(world, box, 0, 0, 0, 0, 0, 13, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 13, 0, 0, 13, 0, 13, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 0, 0, 12, 0, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 0, 13, 12, 0, 13, this.prismarineBricks, this.prismarineBricks, false);

            for (int i = 2; i <= 11; i += 3)
            {
                this.setBlockState(world, this.seaLantern, 0, 0, i, box);
                this.setBlockState(world, this.seaLantern, 13, 0, i, box);
                this.setBlockState(world, this.seaLantern, i, 0, 0, box);
            }

            this.fillWithBlocks(world, box, 2, 0, 3, 4, 0, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 9, 0, 3, 11, 0, 9, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 4, 0, 9, 9, 0, 11, this.prismarineBricks, this.prismarineBricks, false);
            this.setBlockState(world, this.prismarineBricks, 5, 0, 8, box);
            this.setBlockState(world, this.prismarineBricks, 8, 0, 8, box);
            this.setBlockState(world, this.prismarineBricks, 10, 0, 10, box);
            this.setBlockState(world, this.prismarineBricks, 3, 0, 10, box);
            this.fillWithBlocks(world, box, 3, 0, 3, 3, 0, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 10, 0, 3, 10, 0, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 6, 0, 10, 7, 0, 10, this.darkPrismarine, this.darkPrismarine, false);
            int l = 3;

            for (int j = 0; j < 2; ++j)
            {
                for (int k = 2; k <= 8; k += 3)
                {
                    this.fillWithBlocks(world, box, l, 0, k, l, 2, k, this.prismarineBricks, this.prismarineBricks, false);
                }
                l = 10;
            }
            this.fillWithBlocks(world, box, 5, 0, 10, 5, 2, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 8, 0, 10, 8, 2, 10, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 6, -1, 7, 7, -1, 8, this.darkPrismarine, this.darkPrismarine, false);
            this.func_181655_a(world, box, 6, -1, 3, 7, -1, 4, false);
            this.func_175817_a(world, box, 6, 1, 6);
            return true;
        }
    }

    public abstract static class Piece extends StructureComponent
    {
        protected IBlockState prismarine = NibiruBlocks.INFECTED_PRISMARINE.getStateFromMeta(0);
        protected IBlockState prismarineBricks = NibiruBlocks.INFECTED_PRISMARINE.getStateFromMeta(1);
        protected IBlockState darkPrismarine = NibiruBlocks.INFECTED_PRISMARINE.getStateFromMeta(2);
        protected IBlockState seaLantern = NibiruBlocks.INFECTED_SEA_LANTERN.getDefaultState();
        protected IBlockState water = NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();
        protected int field_175823_g = func_175820_a(2, 0, 0);
        protected int field_175831_h = func_175820_a(2, 2, 0);
        protected int field_175832_i = func_175820_a(0, 1, 0);
        protected int field_175829_j = func_175820_a(4, 1, 0);
        protected StructureNibiruOceanMonumentPieces.RoomDefinition field_175830_k;

        protected static int func_175820_a(int p_175820_0_, int p_175820_1_, int p_175820_2_)
        {
            return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
        }

        public Piece()
        {
            super(0);
        }

        public Piece(int p_i45588_1_)
        {
            super(p_i45588_1_);
        }

        public Piece(EnumFacing p_i45589_1_, StructureBoundingBox p_i45589_2_)
        {
            super(1);
            this.coordBaseMode = p_i45589_1_;
            this.boundingBox = p_i45589_2_;
        }

        protected Piece(int p_i45590_1_, EnumFacing p_i45590_2_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45590_3_, int p_i45590_4_, int p_i45590_5_, int p_i45590_6_)
        {
            super(p_i45590_1_);
            this.coordBaseMode = p_i45590_2_;
            this.field_175830_k = p_i45590_3_;
            int i = p_i45590_3_.field_175967_a;
            int j = i % 5;
            int k = i / 5 % 5;
            int l = i / 25;

            if (p_i45590_2_ != EnumFacing.NORTH && p_i45590_2_ != EnumFacing.SOUTH)
            {
                this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_4_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_6_ * 8 - 1);
            }

            switch (p_i45590_2_)
            {
            case NORTH:
                this.boundingBox.offset(j * 8, l * 4, -(k + p_i45590_6_) * 8 + 1);
                break;
            case SOUTH:
                this.boundingBox.offset(j * 8, l * 4, k * 8);
                break;
            case WEST:
                this.boundingBox.offset(-(k + p_i45590_6_) * 8 + 1, l * 4, j * 8);
                break;
            default:
                this.boundingBox.offset(k * 8, l * 4, j * 8);
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {}

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound) {}

        protected void func_181655_a(World p_181655_1_, StructureBoundingBox p_181655_2_, int p_181655_3_, int p_181655_4_, int p_181655_5_, int p_181655_6_, int p_181655_7_, int p_181655_8_, boolean p_181655_9_)
        {
            for (int i = p_181655_4_; i <= p_181655_7_; ++i)
            {
                for (int j = p_181655_3_; j <= p_181655_6_; ++j)
                {
                    for (int k = p_181655_5_; k <= p_181655_8_; ++k)
                    {
                        if (!p_181655_9_ || this.getBlockStateFromPos(p_181655_1_, j, i, k, p_181655_2_).getBlock().getMaterial() != Material.AIR)
                        {
                            if (this.getYWithOffset(i) >= p_181655_1_.getSeaLevel())
                            {
                                this.setBlockState(p_181655_1_, Blocks.AIR.getDefaultState(), j, i, k, p_181655_2_);
                            }
                            else
                            {
                                this.setBlockState(p_181655_1_, this.water, j, i, k, p_181655_2_);
                            }
                        }
                    }
                }
            }
        }

        protected void func_175821_a(World world, StructureBoundingBox p_175821_2_, int p_175821_3_, int p_175821_4_, boolean p_175821_5_)
        {
            if (p_175821_5_)
            {
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 2, 0, p_175821_4_ + 8 - 1, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 0, p_175821_3_ + 4, 0, p_175821_4_ + 2, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 8 - 1, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 2, p_175821_3_ + 4, 0, p_175821_4_ + 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 5, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 2, 0, p_175821_4_ + 3, p_175821_3_ + 2, 0, p_175821_4_ + 4, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 3, p_175821_3_ + 5, 0, p_175821_4_ + 4, this.prismarineBricks, this.prismarineBricks, false);
            }
            else
            {
                this.fillWithBlocks(world, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, this.prismarine, this.prismarine, false);
            }
        }

        protected void func_175819_a(World world, StructureBoundingBox p_175819_2_, int p_175819_3_, int p_175819_4_, int p_175819_5_, int p_175819_6_, int p_175819_7_, int p_175819_8_, IBlockState p_175819_9_)
        {
            for (int i = p_175819_4_; i <= p_175819_7_; ++i)
            {
                for (int j = p_175819_3_; j <= p_175819_6_; ++j)
                {
                    for (int k = p_175819_5_; k <= p_175819_8_; ++k)
                    {
                        if (this.getBlockStateFromPos(world, j, i, k, p_175819_2_) == this.water)
                        {
                            this.setBlockState(world, p_175819_9_, j, i, k, p_175819_2_);
                        }
                    }
                }
            }
        }

        protected boolean func_175818_a(StructureBoundingBox p_175818_1_, int p_175818_2_, int p_175818_3_, int p_175818_4_, int p_175818_5_)
        {
            int i = this.getXWithOffset(p_175818_2_, p_175818_3_);
            int j = this.getZWithOffset(p_175818_2_, p_175818_3_);
            int k = this.getXWithOffset(p_175818_4_, p_175818_5_);
            int l = this.getZWithOffset(p_175818_4_, p_175818_5_);
            return p_175818_1_.intersectsWith(Math.min(i, k), Math.min(j, l), Math.max(i, k), Math.max(j, l));
        }

        protected boolean func_175817_a(World world, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_)
        {
            int i = this.getXWithOffset(p_175817_3_, p_175817_5_);
            int j = this.getYWithOffset(p_175817_4_);
            int k = this.getZWithOffset(p_175817_3_, p_175817_5_);

            if (p_175817_2_.isVecInside(new BlockPos(i, j, k)))
            {
                EntityInfectedGuardian entityguardian = new EntityInfectedGuardian(world);
                entityguardian.setElder(true);
                entityguardian.heal(entityguardian.getMaxHealth());
                entityguardian.setLocationAndAngles(i + 0.5D, j, k + 0.5D, 0.0F, 0.0F);
                entityguardian.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityguardian)), (IEntityLivingData)null);
                world.spawnEntityInWorld(entityguardian);
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
        int field_175967_a;
        StructureNibiruOceanMonumentPieces.RoomDefinition[] field_175965_b = new StructureNibiruOceanMonumentPieces.RoomDefinition[6];
        boolean[] field_175966_c = new boolean[6];
        boolean field_175963_d;
        boolean field_175964_e;
        int field_175962_f;

        public RoomDefinition(int p_i45584_1_)
        {
            this.field_175967_a = p_i45584_1_;
        }

        public void func_175957_a(EnumFacing p_175957_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175957_2_)
        {
            this.field_175965_b[p_175957_1_.getIndex()] = p_175957_2_;
            p_175957_2_.field_175965_b[p_175957_1_.getOpposite().getIndex()] = this;
        }

        public void func_175958_a()
        {
            for (int i = 0; i < 6; ++i)
            {
                this.field_175966_c[i] = this.field_175965_b[i] != null;
            }
        }

        public boolean func_175959_a(int p_175959_1_)
        {
            if (this.field_175964_e)
            {
                return true;
            }
            else
            {
                this.field_175962_f = p_175959_1_;

                for (int i = 0; i < 6; ++i)
                {
                    if (this.field_175965_b[i] != null && this.field_175966_c[i] && this.field_175965_b[i].field_175962_f != p_175959_1_ && this.field_175965_b[i].func_175959_a(p_175959_1_))
                    {
                        return true;
                    }
                }
                return false;
            }
        }

        public boolean func_175961_b()
        {
            return this.field_175967_a >= 75;
        }

        public int func_175960_c()
        {
            int i = 0;

            for (int j = 0; j < 6; ++j)
            {
                if (this.field_175966_c[j])
                {
                    ++i;
                }
            }
            return i;
        }
    }

    public static class SimpleRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        private int field_175833_o;

        public SimpleRoom() {}

        public SimpleRoom(EnumFacing p_i45587_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45587_2_, Random p_i45587_3_)
        {
            super(1, p_i45587_1_, p_i45587_2_, 1, 1, 1);
            this.field_175833_o = p_i45587_3_.nextInt(3);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }
            if (this.field_175830_k.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 4, 1, 6, 4, 6, this.prismarine);
            }

            boolean flag = this.field_175833_o != 0 && rand.nextBoolean() && !this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()] && !this.field_175830_k.field_175966_c[EnumFacing.UP.getIndex()] && this.field_175830_k.func_175960_c() > 1;

            if (this.field_175833_o == 0)
            {
                this.fillWithBlocks(world, box, 0, 1, 0, 2, 1, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 3, 0, 2, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 2, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 0, 2, 2, 0, this.prismarine, this.prismarine, false);
                this.setBlockState(world, this.seaLantern, 1, 2, 1, box);
                this.fillWithBlocks(world, box, 5, 1, 0, 7, 1, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 5, 3, 0, 7, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 2, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, box, 5, 2, 0, 6, 2, 0, this.prismarine, this.prismarine, false);
                this.setBlockState(world, this.seaLantern, 6, 2, 1, box);
                this.fillWithBlocks(world, box, 0, 1, 5, 2, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 3, 5, 2, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 2, 5, 0, 2, 7, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 7, 2, 2, 7, this.prismarine, this.prismarine, false);
                this.setBlockState(world, this.seaLantern, 1, 2, 6, box);
                this.fillWithBlocks(world, box, 5, 1, 5, 7, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 5, 3, 5, 7, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 2, 5, 7, 2, 7, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, box, 5, 2, 7, 6, 2, 7, this.prismarine, this.prismarine, false);
                this.setBlockState(world, this.seaLantern, 6, 2, 6, box);

                if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 3, 3, 0, 4, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 3, 3, 0, 4, 3, 1, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 3, 2, 0, 4, 2, 0, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 3, 1, 0, 4, 1, 1, this.prismarineBricks, this.prismarineBricks, false);
                }

                if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 3, 3, 7, 4, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 3, 3, 6, 4, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 3, 2, 7, 4, 2, 7, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 3, 1, 6, 4, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                }

                if (this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 0, 3, 3, 0, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, 3, 3, 1, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 0, 2, 3, 0, 2, 4, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 0, 1, 3, 1, 1, 4, this.prismarineBricks, this.prismarineBricks, false);
                }

                if (this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 7, 3, 3, 7, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 6, 3, 3, 7, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 7, 2, 3, 7, 2, 4, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 6, 1, 3, 7, 1, 4, this.prismarineBricks, this.prismarineBricks, false);
                }
            }
            else if (this.field_175833_o == 1)
            {
                this.fillWithBlocks(world, box, 2, 1, 2, 2, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 2, 1, 5, 2, 3, 5, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 5, 1, 5, 5, 3, 5, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 5, 1, 2, 5, 3, 2, this.prismarineBricks, this.prismarineBricks, false);
                this.setBlockState(world, this.seaLantern, 2, 2, 2, box);
                this.setBlockState(world, this.seaLantern, 2, 2, 5, box);
                this.setBlockState(world, this.seaLantern, 5, 2, 5, box);
                this.setBlockState(world, this.seaLantern, 5, 2, 2, box);
                this.fillWithBlocks(world, box, 0, 1, 0, 1, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 1, 1, 0, 3, 1, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 1, 7, 1, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 1, 6, 0, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 1, 7, 7, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 1, 6, 7, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 1, 0, 7, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 1, 1, 7, 3, 1, this.prismarineBricks, this.prismarineBricks, false);
                this.setBlockState(world, this.prismarine, 1, 2, 0, box);
                this.setBlockState(world, this.prismarine, 0, 2, 1, box);
                this.setBlockState(world, this.prismarine, 1, 2, 7, box);
                this.setBlockState(world, this.prismarine, 0, 2, 6, box);
                this.setBlockState(world, this.prismarine, 6, 2, 7, box);
                this.setBlockState(world, this.prismarine, 7, 2, 6, box);
                this.setBlockState(world, this.prismarine, 6, 2, 0, box);
                this.setBlockState(world, this.prismarine, 7, 2, 1, box);

                if (!this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 1, 3, 0, 6, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 1, 2, 0, 6, 2, 0, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 1, 1, 0, 6, 1, 0, this.prismarineBricks, this.prismarineBricks, false);
                }
                if (!this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()])
                {
                    this.fillWithBlocks(world, box, 1, 3, 7, 6, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 1, 2, 7, 6, 2, 7, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 1, 1, 7, 6, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                }
                if (!this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 0, 3, 1, 0, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 0, 2, 1, 0, 2, 6, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 0, 1, 1, 0, 1, 6, this.prismarineBricks, this.prismarineBricks, false);
                }
                if (!this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
                {
                    this.fillWithBlocks(world, box, 7, 3, 1, 7, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, 7, 2, 1, 7, 2, 6, this.prismarine, this.prismarine, false);
                    this.fillWithBlocks(world, box, 7, 1, 1, 7, 1, 6, this.prismarineBricks, this.prismarineBricks, false);
                }
            }
            else if (this.field_175833_o == 2)
            {
                this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 1, 0, 6, 1, 0, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 1, 7, 6, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 0, 6, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 1, 2, 7, 6, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 3, 0, 7, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 3, 0, 6, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 1, 3, 7, 6, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 0, 1, 3, 0, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 7, 1, 3, 7, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 3, 1, 0, 4, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 3, 1, 7, 4, 2, 7, this.darkPrismarine, this.darkPrismarine, false);

                if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()])
                {
                    this.func_181655_a(world, box, 3, 1, 0, 4, 2, 0, false);
                }
                if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()])
                {
                    this.func_181655_a(world, box, 3, 1, 7, 4, 2, 7, false);
                }
                if (this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()])
                {
                    this.func_181655_a(world, box, 0, 1, 3, 0, 2, 4, false);
                }
                if (this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
                {
                    this.func_181655_a(world, box, 7, 1, 3, 7, 2, 4, false);
                }
            }

            if (flag)
            {
                this.fillWithBlocks(world, box, 3, 1, 3, 4, 1, 4, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 3, 2, 3, 4, 2, 4, this.prismarine, this.prismarine, false);
                this.fillWithBlocks(world, box, 3, 3, 3, 4, 3, 4, this.prismarineBricks, this.prismarineBricks, false);
            }
            return true;
        }
    }

    public static class SimpleTopRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        public SimpleTopRoom() {}

        public SimpleTopRoom(EnumFacing p_i45586_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_i45586_2_, Random p_i45586_3_)
        {
            super(1, p_i45586_1_, p_i45586_2_, 1, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_175830_k.field_175967_a / 25 > 0)
            {
                this.func_175821_a(world, box, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()]);
            }
            if (this.field_175830_k.field_175965_b[EnumFacing.UP.getIndex()] == null)
            {
                this.func_175819_a(world, box, 1, 4, 1, 6, 4, 6, this.prismarine);
            }

            for (int i = 1; i <= 6; ++i)
            {
                for (int j = 1; j <= 6; ++j)
                {
                    if (rand.nextInt(3) != 0)
                    {
                        int k = 2 + (rand.nextInt(4) == 0 ? 0 : 1);
                        this.fillWithBlocks(world, box, i, k, j, i, 3, j, NibiruBlocks.INFECTED_SPONGE.getStateFromMeta(1), NibiruBlocks.INFECTED_SPONGE.getStateFromMeta(1), false);
                    }
                }
            }

            this.fillWithBlocks(world, box, 0, 1, 0, 0, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 7, 1, 0, 7, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 0, 6, 1, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 1, 7, 6, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 2, 0, 0, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 7, 2, 0, 7, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 0, 6, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 1, 2, 7, 6, 2, 7, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 0, 3, 0, 0, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 7, 3, 0, 7, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 0, 6, 3, 0, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 1, 3, 7, 6, 3, 7, this.prismarineBricks, this.prismarineBricks, false);
            this.fillWithBlocks(world, box, 0, 1, 3, 0, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 7, 1, 3, 7, 2, 4, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 0, 4, 2, 0, this.darkPrismarine, this.darkPrismarine, false);
            this.fillWithBlocks(world, box, 3, 1, 7, 4, 2, 7, this.darkPrismarine, this.darkPrismarine, false);

            if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()])
            {
                this.func_181655_a(world, box, 3, 1, 0, 4, 2, 0, false);
            }
            return true;
        }
    }

    public static class WingRoom extends StructureNibiruOceanMonumentPieces.Piece
    {
        private int field_175834_o;

        public WingRoom() {}

        public WingRoom(EnumFacing p_i45585_1_, StructureBoundingBox p_i45585_2_, int p_i45585_3_)
        {
            super(p_i45585_1_, p_i45585_2_);
            this.field_175834_o = p_i45585_3_ & 1;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_175834_o == 0)
            {
                for (int i = 0; i < 4; ++i)
                {
                    this.fillWithBlocks(world, box, 10 - i, 3 - i, 20 - i, 12 + i, 3 - i, 20, this.prismarineBricks, this.prismarineBricks, false);
                }

                this.fillWithBlocks(world, box, 7, 0, 6, 15, 0, 16, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 6, 0, 6, 6, 3, 20, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 16, 0, 6, 16, 3, 20, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 1, 7, 7, 1, 20, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 15, 1, 7, 15, 1, 20, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 7, 1, 6, 9, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 13, 1, 6, 15, 3, 6, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 8, 1, 7, 9, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 13, 1, 7, 14, 1, 7, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 9, 0, 5, 13, 0, 5, this.prismarineBricks, this.prismarineBricks, false);
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
                this.setBlockState(world, this.prismarineBricks, 4, 2, 4, box);
                this.setBlockState(world, this.seaLantern, 4, 1, 4, box);
                this.setBlockState(world, this.prismarineBricks, 4, 0, 4, box);
                this.setBlockState(world, this.prismarineBricks, 18, 2, 4, box);
                this.setBlockState(world, this.seaLantern, 18, 1, 4, box);
                this.setBlockState(world, this.prismarineBricks, 18, 0, 4, box);
                this.setBlockState(world, this.prismarineBricks, 4, 2, 18, box);
                this.setBlockState(world, this.seaLantern, 4, 1, 18, box);
                this.setBlockState(world, this.prismarineBricks, 4, 0, 18, box);
                this.setBlockState(world, this.prismarineBricks, 18, 2, 18, box);
                this.setBlockState(world, this.seaLantern, 18, 1, 18, box);
                this.setBlockState(world, this.prismarineBricks, 18, 0, 18, box);
                this.setBlockState(world, this.prismarineBricks, 9, 7, 20, box);
                this.setBlockState(world, this.prismarineBricks, 13, 7, 20, box);
                this.fillWithBlocks(world, box, 6, 0, 21, 7, 4, 21, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 15, 0, 21, 16, 4, 21, this.prismarineBricks, this.prismarineBricks, false);
                this.func_175817_a(world, box, 11, 2, 16);
            }
            else if (this.field_175834_o == 1)
            {
                this.fillWithBlocks(world, box, 9, 3, 18, 13, 3, 20, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 9, 0, 18, 9, 2, 18, this.prismarineBricks, this.prismarineBricks, false);
                this.fillWithBlocks(world, box, 13, 0, 18, 13, 2, 18, this.prismarineBricks, this.prismarineBricks, false);
                int j1 = 9;
                int j = 20;
                int k = 5;

                for (int l = 0; l < 2; ++l)
                {
                    this.setBlockState(world, this.prismarineBricks, j1, k + 1, j, box);
                    this.setBlockState(world, this.seaLantern, j1, k, j, box);
                    this.setBlockState(world, this.prismarineBricks, j1, k - 1, j, box);
                    j1 = 13;
                }

                this.fillWithBlocks(world, box, 7, 3, 7, 15, 3, 14, this.prismarineBricks, this.prismarineBricks, false);
                j1 = 10;

                for (int k1 = 0; k1 < 2; ++k1)
                {
                    this.fillWithBlocks(world, box, j1, 0, 10, j1, 6, 10, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, j1, 0, 12, j1, 6, 12, this.prismarineBricks, this.prismarineBricks, false);
                    this.setBlockState(world, this.seaLantern, j1, 0, 10, box);
                    this.setBlockState(world, this.seaLantern, j1, 0, 12, box);
                    this.setBlockState(world, this.seaLantern, j1, 4, 10, box);
                    this.setBlockState(world, this.seaLantern, j1, 4, 12, box);
                    j1 = 12;
                }

                j1 = 8;

                for (int l1 = 0; l1 < 2; ++l1)
                {
                    this.fillWithBlocks(world, box, j1, 0, 7, j1, 2, 7, this.prismarineBricks, this.prismarineBricks, false);
                    this.fillWithBlocks(world, box, j1, 0, 14, j1, 2, 14, this.prismarineBricks, this.prismarineBricks, false);
                    j1 = 14;
                }
                this.fillWithBlocks(world, box, 8, 3, 8, 8, 3, 13, this.darkPrismarine, this.darkPrismarine, false);
                this.fillWithBlocks(world, box, 14, 3, 8, 14, 3, 13, this.darkPrismarine, this.darkPrismarine, false);
                this.func_175817_a(world, box, 11, 5, 13);
            }
            return true;
        }
    }

    static class XDoubleRoomFitHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private XDoubleRoomFitHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            return p_175969_1_.field_175966_c[EnumFacing.EAST.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d;
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            p_175968_2_.field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.DoubleXRoom(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    static class XYDoubleRoomFitHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private XYDoubleRoomFitHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            if (p_175969_1_.field_175966_c[EnumFacing.EAST.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d && p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d)
            {
                StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = p_175969_1_.field_175965_b[EnumFacing.EAST.getIndex()];
                return structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.UP.getIndex()] && !structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
            }
            else
            {
                return false;
            }
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            p_175968_2_.field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.DoubleXYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    static class YDoubleRoomFitHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private YDoubleRoomFitHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            return p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            p_175968_2_.field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.DoubleYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    static class YZDoubleRoomFitHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private YZDoubleRoomFitHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            if (p_175969_1_.field_175966_c[EnumFacing.NORTH.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d && p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d)
            {
                StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = p_175969_1_.field_175965_b[EnumFacing.NORTH.getIndex()];
                return structureoceanmonumentpieces$roomdefinition.field_175966_c[EnumFacing.UP.getIndex()] && !structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
            }
            else
            {
                return false;
            }
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            p_175968_2_.field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            p_175968_2_.field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.DoubleYZRoom(p_175968_1_, p_175968_2_, p_175968_3_);
        }
    }

    static class ZDoubleRoomFitHelper implements StructureNibiruOceanMonumentPieces.MonumentRoomFitHelper
    {
        private ZDoubleRoomFitHelper() {}

        @Override
        public boolean func_175969_a(StructureNibiruOceanMonumentPieces.RoomDefinition p_175969_1_)
        {
            return p_175969_1_.field_175966_c[EnumFacing.NORTH.getIndex()] && !p_175969_1_.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d;
        }

        @Override
        public StructureNibiruOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureNibiruOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_)
        {
            StructureNibiruOceanMonumentPieces.RoomDefinition structureoceanmonumentpieces$roomdefinition = p_175968_2_;

            if (!p_175968_2_.field_175966_c[EnumFacing.NORTH.getIndex()] || p_175968_2_.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d)
            {
                structureoceanmonumentpieces$roomdefinition = p_175968_2_.field_175965_b[EnumFacing.SOUTH.getIndex()];
            }
            structureoceanmonumentpieces$roomdefinition.field_175963_d = true;
            structureoceanmonumentpieces$roomdefinition.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
            return new StructureNibiruOceanMonumentPieces.DoubleZRoom(p_175968_1_, structureoceanmonumentpieces$roomdefinition, p_175968_3_);
        }
    }
}
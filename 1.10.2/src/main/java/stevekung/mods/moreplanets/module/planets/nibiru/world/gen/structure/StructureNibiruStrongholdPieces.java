package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;

public class StructureNibiruStrongholdPieces
{
    private static PieceWeight[] PIECE_WEIGHTS = new PieceWeight[] {new PieceWeight(Straight.class, 40, 0), new PieceWeight(Prison.class, 5, 5), new PieceWeight(LeftTurn.class, 20, 0), new PieceWeight(RightTurn.class, 20, 0), new PieceWeight(RoomCrossing.class, 10, 6), new PieceWeight(StairsStraight.class, 5, 5), new PieceWeight(Stairs.class, 5, 5), new PieceWeight(Crossing.class, 5, 4), new PieceWeight(ChestCorridor.class, 5, 4), new PieceWeight(Library.class, 10, 2)
    {
        @Override
        public boolean canSpawnMoreStructuresOfType(int count)
        {
            return super.canSpawnMoreStructuresOfType(count) && count > 4;
        }
    }, new PieceWeight(PortalRoom.class, 20, 1)
    {
        @Override
        public boolean canSpawnMoreStructuresOfType(int count)
        {
            return super.canSpawnMoreStructuresOfType(count) && count > 5;
        }
    }
    };

    private static List<PieceWeight> structurePieceList;
    private static Class<? extends Stronghold> strongComponentType;
    private static int totalWeight;
    private static Stones STRONGHOLD_STONES = new Stones();

    public static void registerStrongholdPieces()
    {
        MapGenStructureIO.registerStructureComponent(ChestCorridor.class, "SHCC");
        MapGenStructureIO.registerStructureComponent(Corridor.class, "SHFC");
        MapGenStructureIO.registerStructureComponent(Crossing.class, "SH5C");
        MapGenStructureIO.registerStructureComponent(LeftTurn.class, "SHLT");
        MapGenStructureIO.registerStructureComponent(Library.class, "SHLi");
        MapGenStructureIO.registerStructureComponent(PortalRoom.class, "SHPR");
        MapGenStructureIO.registerStructureComponent(Prison.class, "SHPH");
        MapGenStructureIO.registerStructureComponent(RightTurn.class, "SHRT");
        MapGenStructureIO.registerStructureComponent(RoomCrossing.class, "SHRC");
        MapGenStructureIO.registerStructureComponent(Stairs.class, "SHSD");
        MapGenStructureIO.registerStructureComponent(Stairs2.class, "SHStart");
        MapGenStructureIO.registerStructureComponent(Straight.class, "SHS");
        MapGenStructureIO.registerStructureComponent(StairsStraight.class, "SHSSD");
    }

    public static void prepareStructurePieces()
    {
        StructureNibiruStrongholdPieces.structurePieceList = Lists.newArrayList();

        for (PieceWeight piece : StructureNibiruStrongholdPieces.PIECE_WEIGHTS)
        {
            piece.instancesSpawned = 0;
            StructureNibiruStrongholdPieces.structurePieceList.add(piece);
        }
        StructureNibiruStrongholdPieces.strongComponentType = null;
    }

    public static class ChestCorridor extends Stronghold
    {
        private boolean hasMadeChest;

        public ChestCorridor() {}

        public ChestCorridor(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("Chest", this.hasMadeChest);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.hasMadeChest = nbt.getBoolean("Chest");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            this.getNextComponentNormal((Stairs2)component, list, rand, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 4, 6, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 1, 0);
                this.placeDoor(world, box, Door.OPENING, 1, 1, 6);
                this.fillWithBlocks(world, box, 3, 1, 2, 3, 1, 4, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), false);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 1, 1, box);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 1, 5, box);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 2, 2, box);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 2, 4, box);

                for (int i = 2; i <= 4; ++i)
                {
                    this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 2, 1, i, box);
                }
                if (!this.hasMadeChest && box.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3))))
                {
                    this.hasMadeChest = true;
                    this.generateChest(world, box, rand, 3, 2, 3, MPLootTables.NIBIRU_STRONGHOLD_CORRIDOR);
                }
                return true;
            }
        }
    }

    public static class Corridor extends Stronghold
    {
        private int steps;

        public Corridor() {}

        public Corridor(int type, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = box;
            this.steps = facing != EnumFacing.NORTH && facing != EnumFacing.SOUTH ? box.getXSize() : box.getZSize();
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setInteger("Steps", this.steps);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.steps = nbt.getInteger("Steps");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < this.steps; ++i)
                {
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 0, 0, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 0, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 0, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 0, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 4, 0, i, box);

                    for (int j = 1; j <= 3; ++j)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 0, j, i, box);
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, j, i, box);
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, j, i, box);
                        this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, j, i, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 4, j, i, box);
                    }
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 0, 4, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 4, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 4, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 4, i, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 4, 4, i, box);
                }
                return true;
            }
        }
    }

    public static class Crossing extends Stronghold
    {
        private boolean leftLow;
        private boolean leftHigh;
        private boolean rightLow;
        private boolean rightHigh;

        public Crossing() {}

        public Crossing(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
            this.leftLow = rand.nextBoolean();
            this.leftHigh = rand.nextBoolean();
            this.rightLow = rand.nextBoolean();
            this.rightHigh = rand.nextInt(3) > 0;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("leftLow", this.leftLow);
            nbt.setBoolean("leftHigh", this.leftHigh);
            nbt.setBoolean("rightLow", this.rightLow);
            nbt.setBoolean("rightHigh", this.rightHigh);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.leftLow = nbt.getBoolean("leftLow");
            this.leftHigh = nbt.getBoolean("leftHigh");
            this.rightLow = nbt.getBoolean("rightLow");
            this.rightHigh = nbt.getBoolean("rightHigh");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            int i = 3;
            int j = 5;
            EnumFacing enumfacing = this.getCoordBaseMode();
            this.getNextComponentNormal((Stairs2)component, list, rand, 5, 1);

            if (enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.NORTH)
            {
                i = 8 - i;
                j = 8 - j;
            }
            if (this.leftLow)
            {
                this.getNextComponentX((Stairs2)component, list, rand, i, 1);
            }
            if (this.leftHigh)
            {
                this.getNextComponentX((Stairs2)component, list, rand, j, 7);
            }
            if (this.rightLow)
            {
                this.getNextComponentZ((Stairs2)component, list, rand, i, 1);
            }
            if (this.rightHigh)
            {
                this.getNextComponentZ((Stairs2)component, list, rand, j, 7);
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 9, 8, 10, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 4, 3, 0);

                if (this.leftLow)
                {
                    this.fillWithBlocks(world, box, 0, 3, 1, 0, 5, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                if (this.rightLow)
                {
                    this.fillWithBlocks(world, box, 9, 3, 1, 9, 5, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                if (this.leftHigh)
                {
                    this.fillWithBlocks(world, box, 0, 5, 7, 0, 7, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                if (this.rightHigh)
                {
                    this.fillWithBlocks(world, box, 9, 5, 7, 9, 7, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                this.fillWithBlocks(world, box, 5, 1, 10, 7, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithRandomizedBlocks(world, box, 1, 2, 1, 8, 2, 6, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 5, 4, 4, 9, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 8, 1, 5, 8, 4, 9, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 1, 4, 7, 3, 4, 9, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 1, 3, 5, 3, 3, 6, false, rand, STRONGHOLD_STONES);
                this.fillWithBlocks(world, box, 1, 3, 4, 3, 3, 4, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.fillWithBlocks(world, box, 1, 4, 6, 3, 4, 6, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.fillWithRandomizedBlocks(world, box, 5, 1, 7, 7, 1, 8, false, rand, STRONGHOLD_STONES);
                this.fillWithBlocks(world, box, 5, 1, 9, 7, 1, 9, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.fillWithBlocks(world, box, 5, 2, 7, 7, 2, 7, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.fillWithBlocks(world, box, 4, 5, 7, 4, 5, 9, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.fillWithBlocks(world, box, 8, 5, 7, 8, 5, 9, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.fillWithBlocks(world, box, 5, 5, 7, 7, 5, 9, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 6, 5, 6, box);
                return true;
            }
        }
    }

    public static class LeftTurn extends Stronghold
    {
        public LeftTurn() {}

        public LeftTurn(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST)
            {
                this.getNextComponentZ((Stairs2)component, list, rand, 1, 1);
            }
            else
            {
                this.getNextComponentX((Stairs2)component, list, rand, 1, 1);
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 4, 4, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 1, 0);
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST)
                {
                    this.fillWithBlocks(world, box, 4, 1, 1, 4, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 0, 1, 1, 0, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                return true;
            }
        }
    }

    public static class Library extends Stronghold
    {
        private boolean isLargeRoom;

        public Library() {}

        public Library(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
            this.isLargeRoom = box.getYSize() > 6;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("Tall", this.isLargeRoom);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.isLargeRoom = nbt.getBoolean("Tall");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                int i = 11;

                if (!this.isLargeRoom)
                {
                    i = 6;
                }

                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 13, i - 1, 14, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 4, 1, 0);
                this.func_189914_a(world, box, rand, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.WEB.getDefaultState(), Blocks.WEB.getDefaultState(), false, 0);

                for (int l = 1; l <= 13; ++l)
                {
                    if ((l - 1) % 4 == 0)
                    {
                        this.fillWithBlocks(world, box, 1, 1, l, 1, 4, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                        this.fillWithBlocks(world, box, 12, 1, l, 12, 4, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                        this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST), 2, 3, l, box);
                        this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), 11, 3, l, box);

                        if (this.isLargeRoom)
                        {
                            this.fillWithBlocks(world, box, 1, 6, l, 1, 9, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                            this.fillWithBlocks(world, box, 12, 6, l, 12, 9, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                        }
                    }
                    else
                    {
                        this.fillWithBlocks(world, box, 1, 1, l, 1, 4, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                        this.fillWithBlocks(world, box, 12, 1, l, 12, 4, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);

                        if (this.isLargeRoom)
                        {
                            this.fillWithBlocks(world, box, 1, 6, l, 1, 9, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                            this.fillWithBlocks(world, box, 12, 6, l, 12, 9, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                        }
                    }
                }
                for (int k1 = 3; k1 < 12; k1 += 2)
                {
                    this.fillWithBlocks(world, box, 3, 1, k1, 4, 3, k1, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 6, 1, k1, 7, 3, k1, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 9, 1, k1, 10, 3, k1, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                }

                if (this.isLargeRoom)
                {
                    this.fillWithBlocks(world, box, 1, 5, 1, 3, 5, 13, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 10, 5, 1, 12, 5, 13, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 4, 5, 1, 9, 5, 2, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 4, 5, 12, 9, 5, 13, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 9, 5, 11, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 5, 11, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 9, 5, 10, box);
                    this.fillWithBlocks(world, box, 3, 6, 2, 3, 6, 12, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 10, 6, 2, 10, 6, 10, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 4, 6, 2, 9, 6, 2, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 4, 6, 12, 8, 6, 12, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 9, 6, 11, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 8, 6, 11, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 9, 6, 10, box);
                    IBlockState iblockstate1 = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH);
                    this.setBlockState(world, iblockstate1, 10, 1, 13, box);
                    this.setBlockState(world, iblockstate1, 10, 2, 13, box);
                    this.setBlockState(world, iblockstate1, 10, 3, 13, box);
                    this.setBlockState(world, iblockstate1, 10, 4, 13, box);
                    this.setBlockState(world, iblockstate1, 10, 5, 13, box);
                    this.setBlockState(world, iblockstate1, 10, 6, 13, box);
                    this.setBlockState(world, iblockstate1, 10, 7, 13, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 6, 9, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 7, 9, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 6, 8, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 7, 8, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 6, 7, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 7, 7, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 5, 7, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 8, 7, 7, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 6, 7, 6, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 6, 7, 8, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 7, 7, 6, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 7, 7, 8, box);
                    IBlockState iblockstate = NibiruBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.UP);
                    this.setBlockState(world, iblockstate, 5, 8, 7, box);
                    this.setBlockState(world, iblockstate, 8, 8, 7, box);
                    this.setBlockState(world, iblockstate, 6, 8, 6, box);
                    this.setBlockState(world, iblockstate, 6, 8, 8, box);
                    this.setBlockState(world, iblockstate, 7, 8, 6, box);
                    this.setBlockState(world, iblockstate, 7, 8, 8, box);
                }

                this.generateChest(world, box, rand, 3, 3, 5, MPLootTables.NIBIRU_STRONGHOLD_LIBRARY);

                if (this.isLargeRoom)
                {
                    this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, 9, 1, box);
                    this.generateChest(world, box, rand, 12, 8, 1, MPLootTables.NIBIRU_STRONGHOLD_LIBRARY);
                }
                return true;
            }
        }
    }

    static class PieceWeight
    {
        public Class<? extends Stronghold> pieceClass;
        public int pieceWeight;
        public int instancesSpawned;
        public int instancesLimit;

        public PieceWeight(Class<? extends Stronghold> clazz, int weight, int limit)
        {
            this.pieceClass = clazz;
            this.pieceWeight = weight;
            this.instancesLimit = limit;
        }

        public boolean canSpawnMoreStructuresOfType(int count)
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }

        public boolean canSpawnMoreStructures()
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
    }

    public static class PortalRoom extends Stronghold
    {
        private boolean hasSpawner;

        public PortalRoom() {}

        public PortalRoom(int type, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = box;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("Mob", this.hasSpawner);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.hasSpawner = nbt.getBoolean("Mob");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            if (component != null)
            {
                ((Stairs2)component).strongholdPortalRoom = this;
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 10, 7, 15, false, rand, STRONGHOLD_STONES);
            this.placeDoor(world, box, Door.GRATES, 4, 1, 0);
            int i = 6;
            this.fillWithRandomizedBlocks(world, box, 1, i, 1, 1, i, 14, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 9, i, 1, 9, i, 14, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 2, i, 1, 8, i, 2, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 2, i, 14, 8, i, 14, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 1, 2, 1, 4, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 8, 1, 1, 9, 1, 4, false, rand, STRONGHOLD_STONES);
            this.fillWithBlocks(world, box, 1, 1, 1, 1, 1, 3, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 1, 1, 9, 1, 3, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 3, 1, 8, 7, 1, 12, false, rand, STRONGHOLD_STONES);
            this.fillWithBlocks(world, box, 4, 1, 9, 6, 1, 11, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), false);
            this.createWasteTank(new BlockPos(this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1)), world, rand, box);
            this.createWasteTank(new BlockPos(this.getXWithOffset(1, 2), this.getYWithOffset(1), this.getZWithOffset(1, 2)), world, rand, box);
            this.createWasteTank(new BlockPos(this.getXWithOffset(9, 1), this.getYWithOffset(1), this.getZWithOffset(9, 1)), world, rand, box);
            this.createWasteTank(new BlockPos(this.getXWithOffset(9, 2), this.getYWithOffset(1), this.getZWithOffset(9, 2)), world, rand, box);
            this.createWasteTank(new BlockPos(this.getXWithOffset(1, 14), this.getYWithOffset(1), this.getZWithOffset(1, 14)), world, rand, box);
            this.createWasteTank(new BlockPos(this.getXWithOffset(9, 14), this.getYWithOffset(1), this.getZWithOffset(9, 14)), world, rand, box);

            for (int j = 3; j < 14; j += 2)
            {
                this.fillWithBlocks(world, box, 0, 3, j, 0, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                this.fillWithBlocks(world, box, 10, 3, j, 10, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            }
            for (int i1 = 2; i1 < 9; i1 += 2)
            {
                this.fillWithBlocks(world, box, i1, 3, 15, i1, 4, 15, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            }

            IBlockState iblockstate3 = NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
            this.fillWithRandomizedBlocks(world, box, 4, 1, 5, 6, 1, 7, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 4, 2, 6, 6, 2, 7, false, rand, STRONGHOLD_STONES);
            this.fillWithRandomizedBlocks(world, box, 4, 3, 7, 6, 3, 7, false, rand, STRONGHOLD_STONES);

            for (int k = 4; k <= 6; ++k)
            {
                this.setBlockState(world, iblockstate3, k, 1, 4, box);
                this.setBlockState(world, iblockstate3, k, 2, 5, box);
                this.setBlockState(world, iblockstate3, k, 3, 6, box);
            }

            IBlockState iblockstate4 = NibiruBlocks.VEIN_FRAME.getDefaultState().withProperty(BlockVeinFrame.FACING, EnumFacing.NORTH);
            IBlockState iblockstate = NibiruBlocks.VEIN_FRAME.getDefaultState().withProperty(BlockVeinFrame.FACING, EnumFacing.SOUTH);
            IBlockState iblockstate1 = NibiruBlocks.VEIN_FRAME.getDefaultState().withProperty(BlockVeinFrame.FACING, EnumFacing.EAST);
            IBlockState iblockstate2 = NibiruBlocks.VEIN_FRAME.getDefaultState().withProperty(BlockVeinFrame.FACING, EnumFacing.WEST);
            boolean flag = true;
            boolean[] aboolean = new boolean[12];

            for (int l = 0; l < aboolean.length; ++l)
            {
                aboolean[l] = rand.nextFloat() > 0.9F;
                flag &= aboolean[l];
            }

            this.setBlockState(world, iblockstate4.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[0])), 4, 3, 8, box);
            this.setBlockState(world, iblockstate4.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[1])), 5, 3, 8, box);
            this.setBlockState(world, iblockstate4.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[2])), 6, 3, 8, box);
            this.setBlockState(world, iblockstate.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[3])), 4, 3, 12, box);
            this.setBlockState(world, iblockstate.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[4])), 5, 3, 12, box);
            this.setBlockState(world, iblockstate.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[5])), 6, 3, 12, box);
            this.setBlockState(world, iblockstate1.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[6])), 3, 3, 9, box);
            this.setBlockState(world, iblockstate1.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[7])), 3, 3, 10, box);
            this.setBlockState(world, iblockstate1.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[8])), 3, 3, 11, box);
            this.setBlockState(world, iblockstate2.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[9])), 7, 3, 9, box);
            this.setBlockState(world, iblockstate2.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[10])), 7, 3, 10, box);
            this.setBlockState(world, iblockstate2.withProperty(BlockVeinFrame.EYE, Boolean.valueOf(aboolean[11])), 7, 3, 11, box);

            if (flag)
            {
                IBlockState iblockstate5 = Blocks.END_PORTAL.getDefaultState();
                this.setBlockState(world, iblockstate5, 4, 3, 9, box);
                this.setBlockState(world, iblockstate5, 5, 3, 9, box);
                this.setBlockState(world, iblockstate5, 6, 3, 9, box);
                this.setBlockState(world, iblockstate5, 4, 3, 10, box);
                this.setBlockState(world, iblockstate5, 5, 3, 10, box);
                this.setBlockState(world, iblockstate5, 6, 3, 10, box);
                this.setBlockState(world, iblockstate5, 4, 3, 11, box);
                this.setBlockState(world, iblockstate5, 5, 3, 11, box);
                this.setBlockState(world, iblockstate5, 6, 3, 11, box);
            }

            if (!this.hasSpawner)
            {
                this.createSpawner(world, new BlockPos(this.getXWithOffset(5, 6), this.getYWithOffset(3), this.getZWithOffset(5, 6)), box, "infected_worm");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(1, 1), this.getYWithOffset(0), this.getZWithOffset(1, 1)), box, "zergius");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(9, 1), this.getYWithOffset(0), this.getZWithOffset(9, 1)), box, "zergius");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(1, 14), this.getYWithOffset(0), this.getZWithOffset(1, 14)), box, "zergius");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(9, 14), this.getYWithOffset(0), this.getZWithOffset(9, 14)), box, "zergius");
            }
            return true;
        }

        private void createSpawner(World world, BlockPos pos, StructureBoundingBox box, String name)
        {
            if (box.isVecInside(pos))
            {
                this.hasSpawner = true;
                world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                TileEntity tileentity = world.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner)
                {
                    ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName("moreplanets." + name);
                }
            }
        }
    }

    public static class Prison extends Stronghold
    {
        public Prison() {}

        public Prison(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            this.getNextComponentNormal((Stairs2)component, list, rand, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 8, 4, 10, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 1, 0);
                this.fillWithBlocks(world, box, 1, 1, 10, 3, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 1, 4, 3, 1, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 3, 4, 3, 3, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 7, 4, 3, 7, false, rand, STRONGHOLD_STONES);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 9, 4, 3, 9, false, rand, STRONGHOLD_STONES);
                this.fillWithBlocks(world, box, 4, 1, 4, 4, 3, 6, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                this.fillWithBlocks(world, box, 5, 1, 5, 7, 3, 5, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), 4, 3, 2, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), 4, 3, 8, box);
                IBlockState iblockstate = Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.WEST);
                IBlockState iblockstate1 = Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.WEST).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER);
                this.setBlockState(world, iblockstate, 4, 1, 2, box);
                this.setBlockState(world, iblockstate1, 4, 2, 2, box);
                this.setBlockState(world, iblockstate, 4, 1, 8, box);
                this.setBlockState(world, iblockstate1, 4, 2, 8, box);
                return true;
            }
        }
    }

    public static class RightTurn extends LeftTurn
    {
        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST)
            {
                this.getNextComponentX((Stairs2)component, list, rand, 1, 1);
            }
            else
            {
                this.getNextComponentZ((Stairs2)component, list, rand, 1, 1);
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 4, 4, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 1, 0);
                EnumFacing enumfacing = this.getCoordBaseMode();

                if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.EAST)
                {
                    this.fillWithBlocks(world, box, 0, 1, 1, 0, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                else
                {
                    this.fillWithBlocks(world, box, 4, 1, 1, 4, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                return true;
            }
        }
    }

    public static class RoomCrossing extends Stronghold
    {
        protected int roomType;

        public RoomCrossing() {}

        public RoomCrossing(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
            this.roomType = rand.nextInt(5);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setInteger("Type", this.roomType);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.roomType = nbt.getInteger("Type");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            this.getNextComponentNormal((Stairs2)component, list, rand, 4, 1);
            this.getNextComponentX((Stairs2)component, list, rand, 1, 4);
            this.getNextComponentZ((Stairs2)component, list, rand, 1, 4);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 10, 6, 10, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 4, 1, 0);
                this.fillWithBlocks(world, box, 4, 1, 10, 6, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(world, box, 0, 1, 4, 0, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(world, box, 10, 1, 4, 10, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                switch (this.roomType)
                {
                case 0:
                    this.createWasteTank(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5)), world, rand, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 4, 1, 4, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 4, 1, 5, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 4, 1, 6, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 6, 1, 4, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 6, 1, 5, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 6, 1, 6, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 5, 1, 4, box);
                    this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 5, 1, 6, box);
                    break;
                case 1:
                    for (int i1 = 0; i1 < 5; ++i1)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 1, 3 + i1, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 7, 1, 3 + i1, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3 + i1, 1, 3, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3 + i1, 1, 7, box);
                    }
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 5, 1, 5, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 5, 2, 5, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 5, 3, 5, box);
                    this.setBlockState(world, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), 5, 4, 5, box);
                    break;
                case 2:
                    for (int i = 1; i <= 9; ++i)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 1, 3, i, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 9, 3, i, box);
                    }
                    for (int j = 1; j <= 9; ++j)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), j, 3, 1, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), j, 3, 9, box);
                    }
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 1, 4, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 1, 6, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 3, 4, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 3, 6, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, 1, 5, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, 1, 5, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, 3, 5, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, 3, 5, box);

                    for (int k = 1; k <= 3; ++k)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, k, 4, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, k, 4, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, k, 6, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, k, 6, box);
                    }

                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), 5, 3, 5, box);

                    for (int l = 2; l <= 8; ++l)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 2, 3, l, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 3, 3, l, box);

                        if (l <= 3 || l >= 7)
                        {
                            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 4, 3, l, box);
                            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 5, 3, l, box);
                            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 6, 3, l, box);
                        }
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 7, 3, l, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 3, l, box);
                    }
                    IBlockState iblockstate = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.WEST);
                    this.setBlockState(world, iblockstate, 9, 1, 3, box);
                    this.setBlockState(world, iblockstate, 9, 2, 3, box);
                    this.setBlockState(world, iblockstate, 9, 3, 3, box);
                    this.generateChest(world, box, rand, 3, 4, 8, MPLootTables.NIBIRU_STRONGHOLD_CROSSING);
                }
                return true;
            }
        }
    }

    public static class Stairs extends Stronghold
    {
        private boolean source;

        public Stairs() {}

        public Stairs(int type, Random rand, int x, int z)
        {
            super(type);
            this.source = true;
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));
            this.entryDoor = Door.OPENING;

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
            }
        }

        public Stairs(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.source = false;
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("Source", this.source);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.source = nbt.getBoolean("Source");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            if (this.source)
            {
                StructureNibiruStrongholdPieces.strongComponentType = Crossing.class;
            }
            this.getNextComponentNormal((Stairs2)component, list, rand, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 10, 4, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 7, 0);
                this.placeDoor(world, box, Door.OPENING, 1, 1, 4);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 6, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 5, 1, box);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 6, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 5, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 4, 3, box);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 5, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 4, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 3, 3, box);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 3, 4, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 3, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 2, 1, box);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 3, 3, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 2, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 1, 1, box);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 2, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 1, 2, box);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 1, 3, box);
                return true;
            }
        }
    }

    public static class Stairs2 extends Stairs
    {
        public PieceWeight strongholdPieceWeight;
        public PortalRoom strongholdPortalRoom;
        public List<StructureComponent> pendingChildren = Lists.newArrayList();

        public Stairs2() {}

        public Stairs2(Random rand, int x, int z)
        {
            super(0, rand, x, z);
        }

        @Override
        public BlockPos getBoundingBoxCenter()
        {
            return this.strongholdPortalRoom != null ? this.strongholdPortalRoom.getBoundingBoxCenter() : super.getBoundingBoxCenter();
        }
    }

    public static class StairsStraight extends Stronghold
    {
        public StairsStraight() {}

        public StairsStraight(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            this.getNextComponentNormal((Stairs2)component, list, rand, 1, 1);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 10, 7, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 7, 0);
                this.placeDoor(world, box, Door.OPENING, 1, 1, 7);
                IBlockState iblockstate = NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH);

                for (int i = 0; i < 6; ++i)
                {
                    this.setBlockState(world, iblockstate, 1, 6 - i, 1 + i, box);
                    this.setBlockState(world, iblockstate, 2, 6 - i, 1 + i, box);
                    this.setBlockState(world, iblockstate, 3, 6 - i, 1 + i, box);

                    if (i < 5)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 5 - i, 1 + i, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 5 - i, 1 + i, box);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 5 - i, 1 + i, box);
                    }
                }
                return true;
            }
        }
    }

    static class Stones extends StructureComponent.BlockSelector
    {
        private Stones() {}

        @Override
        public void selectBlocks(Random rand, int x, int y, int z, boolean wall)
        {
            if (wall)
            {
                float f = rand.nextFloat();

                if (f < 0.2F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_CRACKED_STONE_BRICKS);
                }
                else if (f < 0.5F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_VEIN_STONE_BRICKS);
                }
                else if (f < 0.55F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_SILVERFISH_STONE.getStateFromMeta(2);
                }
                else
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS);
                }
            }
            else
            {
                this.blockstate = Blocks.AIR.getDefaultState();
            }
        }
    }

    public static class Straight extends Stronghold
    {
        private boolean expandsX;
        private boolean expandsZ;

        public Straight() {}

        public Straight(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.entryDoor = this.getRandomDoor(rand);
            this.boundingBox = box;
            this.expandsX = rand.nextInt(2) == 0;
            this.expandsZ = rand.nextInt(2) == 0;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("Left", this.expandsX);
            nbt.setBoolean("Right", this.expandsZ);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            super.readStructureFromNBT(nbt);
            this.expandsX = nbt.getBoolean("Left");
            this.expandsZ = nbt.getBoolean("Right");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            this.getNextComponentNormal((Stairs2)component, list, rand, 1, 1);

            if (this.expandsX)
            {
                this.getNextComponentX((Stairs2)component, list, rand, 1, 2);
            }
            if (this.expandsZ)
            {
                this.getNextComponentZ((Stairs2)component, list, rand, 1, 2);
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.isLiquidInStructureBoundingBox(world, box))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 4, 6, true, rand, STRONGHOLD_STONES);
                this.placeDoor(world, box, this.entryDoor, 1, 1, 0);
                this.placeDoor(world, box, Door.OPENING, 1, 1, 6);
                IBlockState iblockstate = NibiruBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST);
                IBlockState iblockstate1 = NibiruBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST);
                this.randomlyPlaceBlock(world, box, rand, 0.1F, 1, 2, 1, iblockstate);
                this.randomlyPlaceBlock(world, box, rand, 0.1F, 3, 2, 1, iblockstate1);
                this.randomlyPlaceBlock(world, box, rand, 0.1F, 1, 2, 5, iblockstate);
                this.randomlyPlaceBlock(world, box, rand, 0.1F, 3, 2, 5, iblockstate1);

                if (this.expandsX)
                {
                    this.fillWithBlocks(world, box, 0, 1, 2, 0, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                if (this.expandsZ)
                {
                    this.fillWithBlocks(world, box, 4, 1, 2, 4, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                return true;
            }
        }
    }

    public abstract static class Stronghold extends StructureComponent
    {
        protected Door entryDoor = Door.OPENING;

        public Stronghold() {}

        protected Stronghold(int type)
        {
            super(type);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            nbt.setString("EntryDoor", this.entryDoor.name());
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt)
        {
            this.entryDoor = Door.valueOf(nbt.getString("EntryDoor"));
        }

        protected void placeDoor(World world, StructureBoundingBox box, Door door, int x, int y, int z)
        {
            switch (door)
            {
            case OPENING:
                this.fillWithBlocks(world, box, x, y, z, x + 3 - 1, y + 3 - 1, z, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                break;
            case WOOD_DOOR:
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x, y, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x, y + 1, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x, y + 2, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 1, y + 2, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 2, y + 2, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 2, y + 1, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 2, y, z, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_DOOR_BLOCK.getDefaultState(), x + 1, y, z, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_DOOR_BLOCK.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), x + 1, y + 1, z, box);
                break;
            case GRATES:
                this.setBlockState(world, Blocks.AIR.getDefaultState(), x + 1, y, z, box);
                this.setBlockState(world, Blocks.AIR.getDefaultState(), x + 1, y + 1, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x, y, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x, y + 1, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x, y + 2, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x + 1, y + 2, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x + 2, y + 2, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x + 2, y + 1, z, box);
                this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), x + 2, y, z, box);
                break;
            case IRON_DOOR:
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x, y, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x, y + 1, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x, y + 2, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 1, y + 2, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 2, y + 2, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 2, y + 1, z, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), x + 2, y, z, box);
                this.setBlockState(world, Blocks.IRON_DOOR.getDefaultState(), x + 1, y, z, box);
                this.setBlockState(world, Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), x + 1, y + 1, z, box);
                this.setBlockState(world, Blocks.STONE_BUTTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.NORTH), x + 2, y + 1, z + 1, box);
                this.setBlockState(world, Blocks.STONE_BUTTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.SOUTH), x + 2, y + 1, z - 1, box);
            }
        }

        protected Door getRandomDoor(Random rand)
        {
            int i = rand.nextInt(5);

            switch (i)
            {
            case 0:
            case 1:
            default:
                return Door.OPENING;
            case 2:
                return Door.WOOD_DOOR;
            case 3:
                return Door.GRATES;
            case 4:
                return Door.IRON_DOOR;
            }
        }

        protected StructureComponent getNextComponentNormal(Stairs2 stairs, List<StructureComponent> list, Random rand, int x, int z)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                case NORTH:
                default:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX + x, this.boundingBox.minY + z, this.boundingBox.minZ - 1, enumfacing, this.getComponentType());
                case SOUTH:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX + x, this.boundingBox.minY + z, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType());
                case WEST:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + z, this.boundingBox.minZ + x, enumfacing, this.getComponentType());
                case EAST:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + z, this.boundingBox.minZ + x, enumfacing, this.getComponentType());
                }
            }
            return null;
        }

        protected StructureComponent getNextComponentX(Stairs2 stairs, List<StructureComponent> list, Random rand, int x, int z)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                case NORTH:
                default:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, EnumFacing.WEST, this.getComponentType());
                case SOUTH:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, EnumFacing.WEST, this.getComponentType());
                case WEST:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                case EAST:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }
            return null;
        }

        protected StructureComponent getNextComponentZ(Stairs2 stairs, List<StructureComponent> list, Random rand, int x, int z)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                case NORTH:
                default:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, EnumFacing.EAST, this.getComponentType());
                case SOUTH:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, EnumFacing.EAST, this.getComponentType());
                case WEST:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                case EAST:
                    return this.generateAndAddPiece(stairs, list, rand, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
            return null;
        }

        private StructureBoundingBox findPieceBox(List<StructureComponent> list, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 5, 5, 4, facing);
            StructureComponent structurecomponent = StructureComponent.findIntersecting(list, box);

            if (structurecomponent == null)
            {
                return null;
            }
            else
            {
                if (structurecomponent.getBoundingBox().minY == box.minY)
                {
                    for (int j = 3; j >= 1; --j)
                    {
                        box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 5, 5, j - 1, facing);

                        if (!structurecomponent.getBoundingBox().intersectsWith(box))
                        {
                            return StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 5, 5, j, facing);
                        }
                    }
                }
                return null;
            }
        }

        private boolean canAddStructurePieces()
        {
            boolean flag = false;
            StructureNibiruStrongholdPieces.totalWeight = 0;

            for (PieceWeight piece : StructureNibiruStrongholdPieces.structurePieceList)
            {
                if (piece.instancesLimit > 0 && piece.instancesSpawned < piece.instancesLimit)
                {
                    flag = true;
                }
                StructureNibiruStrongholdPieces.totalWeight += piece.pieceWeight;
            }
            return flag;
        }

        private Stronghold findAndCreatePieceFactory(Class<? extends Stronghold> clazz, List<StructureComponent> list, Random rand, int x, int y, int z, @Nullable EnumFacing facing, int type)
        {
            Stronghold stronghold = null;

            if (clazz == Straight.class)
            {
                stronghold = this.createStraightPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == Prison.class)
            {
                stronghold = this.createPrisonPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == LeftTurn.class)
            {
                stronghold = this.createLeftTurnPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == RightTurn.class)
            {
                stronghold = this.createLeftTurnPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == RoomCrossing.class)
            {
                stronghold = this.createRoomCrossingPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == StairsStraight.class)
            {
                stronghold = this.createStairsStraightPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == Stairs.class)
            {
                stronghold = this.createStairsPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == Crossing.class)
            {
                stronghold = this.createCrossingPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == ChestCorridor.class)
            {
                stronghold = this.createChestCorridorPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == Library.class)
            {
                stronghold = this.createLibraryPiece(list, rand, x, y, z, facing, type);
            }
            else if (clazz == PortalRoom.class)
            {
                stronghold = this.createPortalRoomPiece(list, x, y, z, facing, type);
            }
            return stronghold;
        }

        private Stronghold generatePieceFromSmallDoor(Stairs2 stairs, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            if (!this.canAddStructurePieces())
            {
                return null;
            }
            else
            {
                int j = 0;

                if (StructureNibiruStrongholdPieces.strongComponentType != null)
                {
                    Stronghold stronghold = this.findAndCreatePieceFactory(StructureNibiruStrongholdPieces.strongComponentType, list, rand, x, y, z, facing, type);
                    StructureNibiruStrongholdPieces.strongComponentType = null;

                    if (stronghold != null)
                    {
                        return stronghold;
                    }
                }
                while (j < 5)
                {
                    ++j;
                    int i = rand.nextInt(StructureNibiruStrongholdPieces.totalWeight);

                    for (PieceWeight piece : StructureNibiruStrongholdPieces.structurePieceList)
                    {
                        i -= piece.pieceWeight;

                        if (i < 0)
                        {
                            if (!piece.canSpawnMoreStructuresOfType(type) || piece == stairs.strongholdPieceWeight)
                            {
                                break;
                            }

                            Stronghold structurestrongholdpieces$stronghold1 = this.findAndCreatePieceFactory(piece.pieceClass, list, rand, x, y, z, facing, type);

                            if (structurestrongholdpieces$stronghold1 != null)
                            {
                                ++piece.instancesSpawned;
                                stairs.strongholdPieceWeight = piece;

                                if (!piece.canSpawnMoreStructures())
                                {
                                    StructureNibiruStrongholdPieces.structurePieceList.remove(piece);
                                }
                                return structurestrongholdpieces$stronghold1;
                            }
                        }
                    }
                }

                StructureBoundingBox box = this.findPieceBox(list, x, y, z, facing);

                if (box != null && box.minY > 1)
                {
                    return new Corridor(type, box, facing);
                }
                else
                {
                    return null;
                }
            }
        }

        private StructureComponent generateAndAddPiece(Stairs2 stairs, List<StructureComponent> list, Random rand, int x, int y, int z, @Nullable EnumFacing facing, int type)
        {
            if (type > 50)
            {
                return null;
            }
            else if (Math.abs(x - stairs.getBoundingBox().minX) <= 112 && Math.abs(z - stairs.getBoundingBox().minZ) <= 112)
            {
                StructureComponent component = this.generatePieceFromSmallDoor(stairs, list, rand, x, y, z, facing, type + 1);

                if (component != null)
                {
                    list.add(component);
                    stairs.pendingChildren.add(component);
                }
                return component;
            }
            else
            {
                return null;
            }
        }

        protected boolean canStrongholdGoDeeper(StructureBoundingBox box)
        {
            return box != null && box.minY > 10;
        }

        private Straight createStraightPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 5, 5, 7, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new Straight(type, rand, box, facing) : null;
        }

        private Prison createPrisonPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 9, 5, 11, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new Prison(type, rand, box, facing) : null;
        }

        private LeftTurn createLeftTurnPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 5, 5, 5, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new LeftTurn(type, rand, box, facing) : null;
        }

        private RoomCrossing createRoomCrossingPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 11, 7, 11, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new RoomCrossing(type, rand, box, facing) : null;
        }

        private StairsStraight createStairsStraightPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -7, 0, 5, 11, 8, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new StairsStraight(type, rand, box, facing) : null;
        }

        private Stairs createStairsPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -7, 0, 5, 11, 5, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new Stairs(type, rand, box, facing) : null;
        }

        private Crossing createCrossingPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -4, -3, 0, 10, 9, 11, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new Crossing(type, rand, box, facing) : null;
        }

        private ChestCorridor createChestCorridorPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -1, 0, 5, 5, 7, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new ChestCorridor(type, rand, box, facing) : null;
        }

        private Library createLibraryPiece(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 14, 11, 15, facing);

            if (!this.canStrongholdGoDeeper(box) || StructureComponent.findIntersecting(list, box) != null)
            {
                box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 14, 6, 15, facing);

                if (!this.canStrongholdGoDeeper(box) || StructureComponent.findIntersecting(list, box) != null)
                {
                    return null;
                }
            }
            return new Library(type, rand, box, facing);
        }

        private PortalRoom createPortalRoomPiece(List<StructureComponent> list, int x, int y, int z, EnumFacing facing, int type)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 11, 8, 16, facing);
            return this.canStrongholdGoDeeper(box) && StructureComponent.findIntersecting(list, box) == null ? new PortalRoom(type, box, facing) : null;
        }

        protected void createWasteTank(BlockPos pos, World world, Random rand, StructureBoundingBox box)
        {
            if (box.isVecInside(pos))
            {
                world.setBlockState(pos, NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, Boolean.valueOf(rand.nextFloat() > 0.9F) ? BlockNuclearWasteTank.BlockType.DEPLETE : BlockNuclearWasteTank.BlockType.NONE), 2);
                world.setBlockState(pos.up(), MPBlocks.DUMMY_BLOCK.getStateFromMeta(5), 2);
                world.setBlockState(pos.up(2), MPBlocks.DUMMY_BLOCK.getStateFromMeta(6), 2);
                TileEntity tile = world.getTileEntity(pos);
                TileEntity tile1 = world.getTileEntity(pos.up());
                TileEntity tile2 = world.getTileEntity(pos.up(2));

                if (tile1 instanceof TileEntityDummy && tile2 instanceof TileEntityDummy)
                {
                    TileEntityDummy dummy1 = (TileEntityDummy) tile1;
                    TileEntityDummy dummy2 = (TileEntityDummy) tile2;
                    dummy1.mainBlockPosition = pos;
                    dummy2.mainBlockPosition = pos;
                }
                if (tile instanceof TileEntityNuclearWasteTank)
                {
                    TileEntityNuclearWasteTank waste = (TileEntityNuclearWasteTank) tile;
                    waste.onCreate(world, pos);
                }
            }
        }

        @Override
        protected boolean generateChest(World world, StructureBoundingBox box, Random rand, int x, int y, int z, ResourceLocation loot)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

            if (box.isVecInside(blockpos) && world.getBlockState(blockpos).getBlock() != NibiruBlocks.INFECTED_CHEST)
            {
                IBlockState iblockstate = NibiruBlocks.INFECTED_CHEST.getDefaultState();
                world.setBlockState(blockpos, NibiruBlocks.INFECTED_CHEST.correctFacing(world, blockpos, iblockstate), 2);
                TileEntity tileentity = world.getTileEntity(blockpos);

                if (tileentity instanceof TileEntityChestMP)
                {
                    ((TileEntityChestMP)tileentity).setLootTable(loot, rand.nextLong());
                }

                return true;
            }
            else
            {
                return false;
            }
        }

        public static enum Door
        {
            OPENING,
            WOOD_DOOR,
            GRATES,
            IRON_DOOR;
        }
    }
}
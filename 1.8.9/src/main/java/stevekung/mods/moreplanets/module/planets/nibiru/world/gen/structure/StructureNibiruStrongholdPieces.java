package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockVeinFrame;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;
import stevekung.mods.moreplanets.util.blocks.BlockChestMP;
import stevekung.mods.moreplanets.util.helper.ItemLootHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;
import stevekung.mods.moreplanets.util.world.gen.structure.StructureComponentMP;

@SuppressWarnings("incomplete-switch")
public class StructureNibiruStrongholdPieces
{
    private static StructureNibiruStrongholdPieces.PieceWeight[] pieceWeightArray = new StructureNibiruStrongholdPieces.PieceWeight[] {new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.Straight.class, 40, 0), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.Prison.class, 5, 5), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.LeftTurn.class, 20, 0), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.RightTurn.class, 20, 0), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.RoomCrossing.class, 10, 6), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.StairsStraight.class, 5, 5), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.Stairs.class, 5, 5), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.Crossing.class, 5, 4), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.ChestCorridor.class, 5, 4), new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.Library.class, 10, 2)
    {
        @Override
        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 4;
        }
    }, new StructureNibiruStrongholdPieces.PieceWeight(StructureNibiruStrongholdPieces.PortalRoom.class, 20, 1)
    {
        @Override
        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 5;
        }
    }
    };
    private static List<StructureNibiruStrongholdPieces.PieceWeight> structurePieceList;
    private static Class <? extends StructureNibiruStrongholdPieces.Stronghold > strongComponentType;
    static int totalWeight;
    private static StructureNibiruStrongholdPieces.Stones strongholdStones = new StructureNibiruStrongholdPieces.Stones();

    public static void registerStrongholdPieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.ChestCorridor.class, "NSHCC");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Corridor.class, "NSHFC");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Crossing.class, "NSH5C");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.LeftTurn.class, "NSHLT");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Library.class, "NSHLi");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.PortalRoom.class, "NSHPR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Prison.class, "NSHPH");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.RightTurn.class, "NSHRT");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.RoomCrossing.class, "NSHRC");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Stairs.class, "NSHSD");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Stairs2.class, "NSHStart");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.Straight.class, "NSHS");
        MapGenStructureIO.registerStructureComponent(StructureNibiruStrongholdPieces.StairsStraight.class, "NSHSSD");
    }

    public static void prepareStructurePieces()
    {
        structurePieceList = Lists.<StructureNibiruStrongholdPieces.PieceWeight>newArrayList();

        for (StructureNibiruStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : pieceWeightArray)
        {
            structurestrongholdpieces$pieceweight.instancesSpawned = 0;
            structurePieceList.add(structurestrongholdpieces$pieceweight);
        }
        strongComponentType = null;
    }

    private static boolean canAddStructurePieces()
    {
        boolean flag = false;
        totalWeight = 0;

        for (StructureNibiruStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : structurePieceList)
        {
            if (structurestrongholdpieces$pieceweight.instancesLimit > 0 && structurestrongholdpieces$pieceweight.instancesSpawned < structurestrongholdpieces$pieceweight.instancesLimit)
            {
                flag = true;
            }
            totalWeight += structurestrongholdpieces$pieceweight.pieceWeight;
        }
        return flag;
    }

    private static StructureNibiruStrongholdPieces.Stronghold func_175954_a(Class <? extends StructureNibiruStrongholdPieces.Stronghold > p_175954_0_, List<StructureComponent> p_175954_1_, Random p_175954_2_, int p_175954_3_, int p_175954_4_, int p_175954_5_, EnumFacing p_175954_6_, int p_175954_7_)
    {
        StructureNibiruStrongholdPieces.Stronghold structurestrongholdpieces$stronghold = null;

        if (p_175954_0_ == StructureNibiruStrongholdPieces.Straight.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.Straight.func_175862_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.Prison.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.Prison.func_175860_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.LeftTurn.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.LeftTurn.func_175867_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.RightTurn.class)
        {
            structurestrongholdpieces$stronghold = LeftTurn.func_175867_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.RoomCrossing.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.RoomCrossing.func_175859_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.StairsStraight.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.StairsStraight.func_175861_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.Stairs.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.Stairs.func_175863_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.Crossing.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.Crossing.func_175866_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.ChestCorridor.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.ChestCorridor.func_175868_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.Library.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.Library.func_175864_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StructureNibiruStrongholdPieces.PortalRoom.class)
        {
            structurestrongholdpieces$stronghold = StructureNibiruStrongholdPieces.PortalRoom.func_175865_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        return structurestrongholdpieces$stronghold;
    }

    private static StructureNibiruStrongholdPieces.Stronghold func_175955_b(StructureNibiruStrongholdPieces.Stairs2 p_175955_0_, List<StructureComponent> p_175955_1_, Random p_175955_2_, int p_175955_3_, int p_175955_4_, int p_175955_5_, EnumFacing p_175955_6_, int p_175955_7_)
    {
        if (!canAddStructurePieces())
        {
            return null;
        }
        else
        {
            if (strongComponentType != null)
            {
                StructureNibiruStrongholdPieces.Stronghold structurestrongholdpieces$stronghold = func_175954_a(strongComponentType, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
                strongComponentType = null;

                if (structurestrongholdpieces$stronghold != null)
                {
                    return structurestrongholdpieces$stronghold;
                }
            }

            int j = 0;

            while (j < 5)
            {
                ++j;
                int i = p_175955_2_.nextInt(totalWeight);

                for (StructureNibiruStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : structurePieceList)
                {
                    i -= structurestrongholdpieces$pieceweight.pieceWeight;

                    if (i < 0)
                    {
                        if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructuresOfType(p_175955_7_) || structurestrongholdpieces$pieceweight == p_175955_0_.strongholdPieceWeight)
                        {
                            break;
                        }

                        StructureNibiruStrongholdPieces.Stronghold structurestrongholdpieces$stronghold1 = func_175954_a(structurestrongholdpieces$pieceweight.pieceClass, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);

                        if (structurestrongholdpieces$stronghold1 != null)
                        {
                            ++structurestrongholdpieces$pieceweight.instancesSpawned;
                            p_175955_0_.strongholdPieceWeight = structurestrongholdpieces$pieceweight;

                            if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructures())
                            {
                                structurePieceList.remove(structurestrongholdpieces$pieceweight);
                            }
                            return structurestrongholdpieces$stronghold1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = StructureNibiruStrongholdPieces.Corridor.func_175869_a(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);

            if (structureboundingbox != null && structureboundingbox.minY > 1)
            {
                return new StructureNibiruStrongholdPieces.Corridor(p_175955_7_, p_175955_2_, structureboundingbox, p_175955_6_);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_175953_c(StructureNibiruStrongholdPieces.Stairs2 p_175953_0_, List<StructureComponent> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, EnumFacing p_175953_6_, int p_175953_7_)
    {
        if (p_175953_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_175953_3_ - p_175953_0_.getBoundingBox().minX) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.getBoundingBox().minZ) <= 112)
        {
            StructureComponent structurecomponent = func_175955_b(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);

            if (structurecomponent != null)
            {
                p_175953_1_.add(structurecomponent);
                p_175953_0_.field_75026_c.add(structurecomponent);
            }
            return structurecomponent;
        }
        else
        {
            return null;
        }
    }

    public static class ChestCorridor extends StructureNibiruStrongholdPieces.Stronghold
    {
        private boolean hasMadeChest;

        public ChestCorridor() {}

        static
        {
            ItemLootHelper.register(ItemLootHelper.NIBIRU_STRONGHOLD_CORRIDOR, ItemLootHelper.NIBIRU_STRONGHOLD_CORRIDOR_LOOT, 2, 4);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_STRONGHOLD_CORRIDOR, ItemLootHelper.ENCHANTED_BOOK);
        }

        public ChestCorridor(int p_i45582_1_, Random p_i45582_2_, StructureBoundingBox p_i45582_3_, EnumFacing p_i45582_4_)
        {
            super(p_i45582_1_);
            this.coordBaseMode = p_i45582_4_;
            this.field_143013_d = this.getRandomDoor(p_i45582_2_);
            this.boundingBox = p_i45582_3_;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Chest", this.hasMadeChest);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.hasMadeChest = tagCompound.getBoolean("Chest");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
        }

        public static StructureNibiruStrongholdPieces.ChestCorridor func_175868_a(List<StructureComponent> p_175868_0_, Random p_175868_1_, int p_175868_2_, int p_175868_3_, int p_175868_4_, EnumFacing p_175868_5_, int p_175868_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175868_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.ChestCorridor(p_175868_6_, p_175868_1_, structureboundingbox, p_175868_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 6, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 1, 0);
                this.placeDoor(world, rand, structureBoundingBox, StructureNibiruStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
                this.fillWithBlocks(world, structureBoundingBox, 3, 1, 2, 3, 1, 4, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), false);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 1, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 1, 5, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 2, 2, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 3, 2, 4, structureBoundingBox);

                for (int i = 2; i <= 4; ++i)
                {
                    this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 2, 1, i, structureBoundingBox);
                }

                if (!this.hasMadeChest && structureBoundingBox.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3))))
                {
                    this.hasMadeChest = true;
                    this.generateChestContents(world, structureBoundingBox, rand, 3, 2, 3, ChestGenHooks.getItems(ItemLootHelper.NIBIRU_STRONGHOLD_CORRIDOR, rand), ChestGenHooks.getCount(ItemLootHelper.NIBIRU_STRONGHOLD_CORRIDOR, rand));
                }
                return true;
            }
        }
    }

    public static class Corridor extends StructureNibiruStrongholdPieces.Stronghold
    {
        private int field_74993_a;

        public Corridor() {}

        public Corridor(int p_i45581_1_, Random p_i45581_2_, StructureBoundingBox p_i45581_3_, EnumFacing p_i45581_4_)
        {
            super(p_i45581_1_);
            this.coordBaseMode = p_i45581_4_;
            this.boundingBox = p_i45581_3_;
            this.field_74993_a = p_i45581_4_ != EnumFacing.NORTH && p_i45581_4_ != EnumFacing.SOUTH ? p_i45581_3_.getXSize() : p_i45581_3_.getZSize();
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Steps", this.field_74993_a);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.field_74993_a = tagCompound.getInteger("Steps");
        }

        public static StructureBoundingBox func_175869_a(List<StructureComponent> p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, EnumFacing p_175869_5_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
            StructureComponent structurecomponent = StructureComponent.findIntersecting(p_175869_0_, structureboundingbox);

            if (structurecomponent == null)
            {
                return null;
            }
            else
            {
                if (structurecomponent.getBoundingBox().minY == structureboundingbox.minY)
                {
                    for (int j = 3; j >= 1; --j)
                    {
                        structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j - 1, p_175869_5_);

                        if (!structurecomponent.getBoundingBox().intersectsWith(structureboundingbox))
                        {
                            return StructureBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j, p_175869_5_);
                        }
                    }
                }
                return null;
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < this.field_74993_a; ++i)
                {
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 0, 0, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 0, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 0, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 0, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 4, 0, i, structureBoundingBox);

                    for (int j = 1; j <= 3; ++j)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 0, j, i, structureBoundingBox);
                        this.setBlockState(world, Blocks.air.getDefaultState(), 1, j, i, structureBoundingBox);
                        this.setBlockState(world, Blocks.air.getDefaultState(), 2, j, i, structureBoundingBox);
                        this.setBlockState(world, Blocks.air.getDefaultState(), 3, j, i, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 4, j, i, structureBoundingBox);
                    }
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 0, 4, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 4, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 4, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 4, i, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 4, 4, i, structureBoundingBox);
                }
                return true;
            }
        }
    }

    public static class Crossing extends StructureNibiruStrongholdPieces.Stronghold
    {
        private boolean field_74996_b;
        private boolean field_74997_c;
        private boolean field_74995_d;
        private boolean field_74999_h;

        public Crossing() {}

        public Crossing(int p_i45580_1_, Random p_i45580_2_, StructureBoundingBox p_i45580_3_, EnumFacing p_i45580_4_)
        {
            super(p_i45580_1_);
            this.coordBaseMode = p_i45580_4_;
            this.field_143013_d = this.getRandomDoor(p_i45580_2_);
            this.boundingBox = p_i45580_3_;
            this.field_74996_b = p_i45580_2_.nextBoolean();
            this.field_74997_c = p_i45580_2_.nextBoolean();
            this.field_74995_d = p_i45580_2_.nextBoolean();
            this.field_74999_h = p_i45580_2_.nextInt(3) > 0;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("leftLow", this.field_74996_b);
            tagCompound.setBoolean("leftHigh", this.field_74997_c);
            tagCompound.setBoolean("rightLow", this.field_74995_d);
            tagCompound.setBoolean("rightHigh", this.field_74999_h);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.field_74996_b = tagCompound.getBoolean("leftLow");
            this.field_74997_c = tagCompound.getBoolean("leftHigh");
            this.field_74995_d = tagCompound.getBoolean("rightLow");
            this.field_74999_h = tagCompound.getBoolean("rightHigh");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            int i = 3;
            int j = 5;

            if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.NORTH)
            {
                i = 8 - i;
                j = 8 - j;
            }

            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 5, 1);

            if (this.field_74996_b)
            {
                this.getNextComponentX((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, i, 1);
            }

            if (this.field_74997_c)
            {
                this.getNextComponentX((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, j, 7);
            }

            if (this.field_74995_d)
            {
                this.getNextComponentZ((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, i, 1);
            }

            if (this.field_74999_h)
            {
                this.getNextComponentZ((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, j, 7);
            }
        }

        public static StructureNibiruStrongholdPieces.Crossing func_175866_a(List<StructureComponent> p_175866_0_, Random p_175866_1_, int p_175866_2_, int p_175866_3_, int p_175866_4_, EnumFacing p_175866_5_, int p_175866_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175866_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.Crossing(p_175866_6_, p_175866_1_, structureboundingbox, p_175866_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 9, 8, 10, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 4, 3, 0);

                if (this.field_74996_b)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 0, 3, 1, 0, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                if (this.field_74995_d)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 9, 3, 1, 9, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                if (this.field_74997_c)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 0, 5, 7, 0, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                if (this.field_74999_h)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 9, 5, 7, 9, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                this.fillWithBlocks(world, structureBoundingBox, 5, 1, 10, 7, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 2, 1, 8, 2, 6, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 5, 4, 4, 9, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 8, 1, 5, 8, 4, 9, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 4, 7, 3, 4, 9, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, 3, 5, 3, 3, 6, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithBlocks(world, structureBoundingBox, 1, 3, 4, 3, 3, 4, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM),  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), false);
                this.fillWithBlocks(world, structureBoundingBox, 1, 4, 6, 3, 4, 6,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM),  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), false);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 5, 1, 7, 7, 1, 8, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithBlocks(world, structureBoundingBox, 5, 1, 9, 7, 1, 9,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM),  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), false);
                this.fillWithBlocks(world, structureBoundingBox, 5, 2, 7, 7, 2, 7,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM),  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), false);
                this.fillWithBlocks(world, structureBoundingBox, 4, 5, 7, 4, 5, 9,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM),  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), false);
                this.fillWithBlocks(world, structureBoundingBox, 8, 5, 7, 8, 5, 9,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM),  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), false);
                this.fillWithBlocks(world, structureBoundingBox, 5, 5, 7, 7, 5, 9, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
                this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), 6, 5, 6, structureBoundingBox);
                return true;
            }
        }
    }

    public static class LeftTurn extends StructureNibiruStrongholdPieces.Stronghold
    {
        public LeftTurn() {}

        public LeftTurn(int p_i45579_1_, Random p_i45579_2_, StructureBoundingBox p_i45579_3_, EnumFacing p_i45579_4_)
        {
            super(p_i45579_1_);
            this.coordBaseMode = p_i45579_4_;
            this.field_143013_d = this.getRandomDoor(p_i45579_2_);
            this.boundingBox = p_i45579_3_;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST)
            {
                this.getNextComponentZ((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
            }
            else
            {
                this.getNextComponentX((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
            }
        }

        public static StructureNibiruStrongholdPieces.LeftTurn func_175867_a(List<StructureComponent> p_175867_0_, Random p_175867_1_, int p_175867_2_, int p_175867_3_, int p_175867_4_, EnumFacing p_175867_5_, int p_175867_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175867_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.LeftTurn(p_175867_6_, p_175867_1_, structureboundingbox, p_175867_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 4, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 1, 0);

                if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                else
                {
                    this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                return true;
            }
        }
    }

    public static class Library extends StructureNibiruStrongholdPieces.Stronghold
    {
        private static List<WeightedRandomChestContent> strongholdLibraryChestContents = Lists.newArrayList(new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.book, 0, 1, 3, 20), new WeightedRandomChestContent(Items.paper, 0, 2, 7, 20), new WeightedRandomChestContent(Items.map, 0, 1, 1, 1), new WeightedRandomChestContent(Items.compass, 0, 1, 1, 1)});
        private boolean isLargeRoom;

        static
        {
            ItemLootHelper.register(ChestGenHooks.STRONGHOLD_LIBRARY, strongholdLibraryChestContents, 1, 5);
            ItemLootHelper.add(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(Items.enchanted_book, 1, 0), 1, 5, 2));
        }

        public Library() {}

        public Library(int p_i45578_1_, Random p_i45578_2_, StructureBoundingBox p_i45578_3_, EnumFacing p_i45578_4_)
        {
            super(p_i45578_1_);
            this.coordBaseMode = p_i45578_4_;
            this.field_143013_d = this.getRandomDoor(p_i45578_2_);
            this.boundingBox = p_i45578_3_;
            this.isLargeRoom = p_i45578_3_.getYSize() > 6;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Tall", this.isLargeRoom);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.isLargeRoom = tagCompound.getBoolean("Tall");
        }

        public static StructureNibiruStrongholdPieces.Library func_175864_a(List<StructureComponent> p_175864_0_, Random p_175864_1_, int p_175864_2_, int p_175864_3_, int p_175864_4_, EnumFacing p_175864_5_, int p_175864_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);

            if (!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.findIntersecting(p_175864_0_, structureboundingbox) != null)
            {
                structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_);

                if (!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.findIntersecting(p_175864_0_, structureboundingbox) != null)
                {
                    return null;
                }
            }
            return new StructureNibiruStrongholdPieces.Library(p_175864_6_, p_175864_1_, structureboundingbox, p_175864_5_);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
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

                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 13, i - 1, 14, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 4, 1, 0);
                this.func_175805_a(world, structureBoundingBox, rand, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.web.getDefaultState(), Blocks.web.getDefaultState(), false);

                for (int l = 1; l <= 13; ++l)
                {
                    if ((l - 1) % 4 == 0)
                    {
                        this.fillWithBlocks(world, structureBoundingBox, 1, 1, l, 1, 4, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                        this.fillWithBlocks(world, structureBoundingBox, 12, 1, l, 12, 4, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                        this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), 2, 3, l, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), 11, 3, l, structureBoundingBox);

                        if (this.isLargeRoom)
                        {
                            this.fillWithBlocks(world, structureBoundingBox, 1, 6, l, 1, 9, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                            this.fillWithBlocks(world, structureBoundingBox, 12, 6, l, 12, 9, l, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                        }
                    }
                    else
                    {
                        this.fillWithBlocks(world, structureBoundingBox, 1, 1, l, 1, 4, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                        this.fillWithBlocks(world, structureBoundingBox, 12, 1, l, 12, 4, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);

                        if (this.isLargeRoom)
                        {
                            this.fillWithBlocks(world, structureBoundingBox, 1, 6, l, 1, 9, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                            this.fillWithBlocks(world, structureBoundingBox, 12, 6, l, 12, 9, l, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                        }
                    }
                }

                for (int k1 = 3; k1 < 12; k1 += 2)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 3, 1, k1, 4, 3, k1, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 6, 1, k1, 7, 3, k1, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 9, 1, k1, 10, 3, k1, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
                }

                if (this.isLargeRoom)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 1, 5, 1, 3, 5, 13, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 10, 5, 1, 12, 5, 13, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 4, 5, 1, 9, 5, 2, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 4, 5, 12, 9, 5, 13, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), false);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 9, 5, 11, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 5, 11, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 9, 5, 10, structureBoundingBox);
                    this.fillWithBlocks(world, structureBoundingBox, 3, 6, 2, 3, 6, 12, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 10, 6, 2, 10, 6, 10, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 4, 6, 2, 9, 6, 2, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.fillWithBlocks(world, structureBoundingBox, 4, 6, 12, 8, 6, 12, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 9, 6, 11, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 8, 6, 11, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 9, 6, 10, structureBoundingBox);
                    int l1 = this.getMetadataWithOffset(Blocks.ladder, 3);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 1, 13, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 2, 13, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 3, 13, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 4, 13, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 5, 13, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 6, 13, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(l1), 10, 7, 13, structureBoundingBox);
                    int i1 = 7;
                    int j1 = 7;
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 - 1, 9, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1, 9, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 - 1, 8, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1, 8, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 - 1, 7, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1, 7, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 - 2, 7, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 + 1, 7, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 - 1, 7, j1 - 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1 - 1, 7, j1 + 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1, 7, j1 - 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), i1, 7, j1 + 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), i1 - 2, 8, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), i1 + 1, 8, j1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), i1 - 1, 8, j1 - 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), i1 - 1, 8, j1 + 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), i1, 8, j1 - 1, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), i1, 8, j1 + 1, structureBoundingBox);
                }

                ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
                this.generateChestContents(world, structureBoundingBox, rand, 3, 3, 5, info.getItems(rand), info.getCount(rand));

                if (this.isLargeRoom)
                {
                    this.setBlockState(world, Blocks.air.getDefaultState(), 12, 9, 1, structureBoundingBox);
                    this.generateChestContents(world, structureBoundingBox, rand, 12, 8, 1, info.getItems(rand), info.getCount(rand));
                }
                return true;
            }
        }
    }

    static class PieceWeight
    {
        public Class <? extends StructureNibiruStrongholdPieces.Stronghold > pieceClass;
        public int pieceWeight;
        public int instancesSpawned;
        public int instancesLimit;

        public PieceWeight(Class <? extends StructureNibiruStrongholdPieces.Stronghold > p_i2076_1_, int p_i2076_2_, int p_i2076_3_)
        {
            this.pieceClass = p_i2076_1_;
            this.pieceWeight = p_i2076_2_;
            this.instancesLimit = p_i2076_3_;
        }

        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }

        public boolean canSpawnMoreStructures()
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
    }

    public static class PortalRoom extends StructureNibiruStrongholdPieces.Stronghold
    {
        private boolean hasSpawner;

        public PortalRoom() {}

        public PortalRoom(int p_i45577_1_, Random p_i45577_2_, StructureBoundingBox p_i45577_3_, EnumFacing p_i45577_4_)
        {
            super(p_i45577_1_);
            this.coordBaseMode = p_i45577_4_;
            this.boundingBox = p_i45577_3_;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Mob", this.hasSpawner);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.hasSpawner = tagCompound.getBoolean("Mob");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            if (componentIn != null)
            {
                ((StructureNibiruStrongholdPieces.Stairs2)componentIn).strongholdPortalRoom = this;
            }
        }

        public static StructureNibiruStrongholdPieces.PortalRoom func_175865_a(List<StructureComponent> p_175865_0_, Random p_175865_1_, int p_175865_2_, int p_175865_3_, int p_175865_4_, EnumFacing p_175865_5_, int p_175865_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175865_2_, p_175865_3_, p_175865_4_, -4, -1, 0, 11, 8, 16, p_175865_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175865_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.PortalRoom(p_175865_6_, p_175865_1_, structureboundingbox, p_175865_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 10, 7, 15, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.placeDoor(world, rand, structureBoundingBox, StructureNibiruStrongholdPieces.Stronghold.Door.GRATES, 4, 1, 0);
            int i = 6;
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 1, i, 1, 1, i, 14, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 9, i, 1, 9, i, 14, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, i, 1, 8, i, 2, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 2, i, 14, 8, i, 14, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.createWasteTank(new BlockPos(this.getXWithOffset(1, 1), this.getYWithOffset(1), this.getZWithOffset(1, 1)), world, rand, structureBoundingBox);
            this.createWasteTank(new BlockPos(this.getXWithOffset(1, 2), this.getYWithOffset(1), this.getZWithOffset(1, 2)), world, rand, structureBoundingBox);
            this.createWasteTank(new BlockPos(this.getXWithOffset(9, 1), this.getYWithOffset(1), this.getZWithOffset(9, 1)), world, rand, structureBoundingBox);
            this.createWasteTank(new BlockPos(this.getXWithOffset(9, 2), this.getYWithOffset(1), this.getZWithOffset(9, 2)), world, rand, structureBoundingBox);
            this.createWasteTank(new BlockPos(this.getXWithOffset(1, 14), this.getYWithOffset(1), this.getZWithOffset(1, 14)), world, rand, structureBoundingBox);
            this.createWasteTank(new BlockPos(this.getXWithOffset(9, 14), this.getYWithOffset(1), this.getZWithOffset(9, 14)), world, rand, structureBoundingBox);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 3, 1, 8, 7, 1, 12, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.fillWithBlocks(world, structureBoundingBox, 4, 1, 9, 6, 1, 11, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState(), false);

            for (int j = 3; j < 14; j += 2)
            {
                this.fillWithBlocks(world, structureBoundingBox, 0, 3, j, 0, 4, j, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
                this.fillWithBlocks(world, structureBoundingBox, 10, 3, j, 10, 4, j, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            }
            for (int k1 = 2; k1 < 9; k1 += 2)
            {
                this.fillWithBlocks(world, structureBoundingBox, k1, 3, 15, k1, 4, 15, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            }

            int l1 = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS, 3);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 5, 6, 1, 7, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 2, 6, 6, 2, 7, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 3, 7, 6, 3, 7, false, rand, StructureNibiruStrongholdPieces.strongholdStones);

            for (int k = 4; k <= 6; ++k)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS.getStateFromMeta(l1), k, 1, 4, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS.getStateFromMeta(l1), k, 2, 5, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS.getStateFromMeta(l1), k, 3, 6, structureBoundingBox);
            }

            int i2 = EnumFacing.NORTH.getHorizontalIndex();
            int l = EnumFacing.SOUTH.getHorizontalIndex();
            int i1 = EnumFacing.EAST.getHorizontalIndex();
            int j1 = EnumFacing.WEST.getHorizontalIndex();

            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case SOUTH:
                    i2 = EnumFacing.SOUTH.getHorizontalIndex();
                    l = EnumFacing.NORTH.getHorizontalIndex();
                    break;
                case WEST:
                    i2 = EnumFacing.WEST.getHorizontalIndex();
                    l = EnumFacing.EAST.getHorizontalIndex();
                    i1 = EnumFacing.SOUTH.getHorizontalIndex();
                    j1 = EnumFacing.NORTH.getHorizontalIndex();
                    break;
                case EAST:
                    i2 = EnumFacing.EAST.getHorizontalIndex();
                    l = EnumFacing.WEST.getHorizontalIndex();
                    i1 = EnumFacing.SOUTH.getHorizontalIndex();
                    j1 = EnumFacing.NORTH.getHorizontalIndex();
                }
            }

            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(i2).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 4, 3, 8, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(i2).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 5, 3, 8, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(i2).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 6, 3, 8, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(l).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 4, 3, 12, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(l).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 5, 3, 12, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(l).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 6, 3, 12, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(i1).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 3, 3, 9, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(i1).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 3, 3, 10, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(i1).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 3, 3, 11, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(j1).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 7, 3, 9, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(j1).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 7, 3, 10, structureBoundingBox);
            this.setBlockState(world, NibiruBlocks.VEIN_FRAME.getStateFromMeta(j1).withProperty(BlockVeinFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 7, 3, 11, structureBoundingBox);

            if (!this.hasSpawner)
            {
                this.createSpawner(world, new BlockPos(this.getXWithOffset(5, 6), this.getYWithOffset(3), this.getZWithOffset(5, 6)), structureBoundingBox, "infected_worm");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(1, 1), this.getYWithOffset(0), this.getZWithOffset(1, 1)), structureBoundingBox, "zergius");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(9, 1), this.getYWithOffset(0), this.getZWithOffset(9, 1)), structureBoundingBox, "zergius");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(1, 14), this.getYWithOffset(0), this.getZWithOffset(1, 14)), structureBoundingBox, "zergius");
                this.createSpawner(world, new BlockPos(this.getXWithOffset(9, 14), this.getYWithOffset(0), this.getZWithOffset(9, 14)), structureBoundingBox, "zergius");
            }
            return true;
        }

        private void createSpawner(World world, BlockPos pos, StructureBoundingBox structureBoundingBox, String entity)
        {
            if (structureBoundingBox.isVecInside(pos))
            {
                this.hasSpawner = true;
                world.setBlockState(pos, Blocks.mob_spawner.getDefaultState(), 2);
                TileEntity tileentity = world.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner)
                {
                    ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName("moreplanets." + entity);
                }
            }
        }
    }

    public static class Prison extends StructureNibiruStrongholdPieces.Stronghold
    {
        public Prison() {}

        public Prison(int p_i45576_1_, Random p_i45576_2_, StructureBoundingBox p_i45576_3_, EnumFacing p_i45576_4_)
        {
            super(p_i45576_1_);
            this.coordBaseMode = p_i45576_4_;
            this.field_143013_d = this.getRandomDoor(p_i45576_2_);
            this.boundingBox = p_i45576_3_;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
        }

        public static StructureNibiruStrongholdPieces.Prison func_175860_a(List<StructureComponent> p_175860_0_, Random p_175860_1_, int p_175860_2_, int p_175860_3_, int p_175860_4_, EnumFacing p_175860_5_, int p_175860_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175860_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.Prison(p_175860_6_, p_175860_1_, structureboundingbox, p_175860_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 8, 4, 10, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 1, 0);
                this.fillWithBlocks(world, structureBoundingBox, 1, 1, 10, 3, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 1, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 3, 4, 3, 3, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 7, 4, 3, 7, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 4, 1, 9, 4, 3, 9, false, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.fillWithBlocks(world, structureBoundingBox, 4, 1, 4, 4, 3, 6, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
                this.fillWithBlocks(world, structureBoundingBox, 5, 1, 5, 7, 3, 5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), 4, 3, 2, structureBoundingBox);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), 4, 3, 8, structureBoundingBox);
                this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 2, structureBoundingBox);
                this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 2, structureBoundingBox);
                this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 8, structureBoundingBox);
                this.setBlockState(world, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 8, structureBoundingBox);
                return true;
            }
        }
    }

    public static class RightTurn extends StructureNibiruStrongholdPieces.LeftTurn
    {
        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST)
            {
                this.getNextComponentX((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
            }
            else
            {
                this.getNextComponentZ((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
            }
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 4, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 1, 0);

                if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                else
                {
                    this.fillWithBlocks(world, structureBoundingBox, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                return true;
            }
        }
    }

    public static class RoomCrossing extends StructureNibiruStrongholdPieces.Stronghold
    {
        protected int roomType;

        static
        {
            ItemLootHelper.register(ItemLootHelper.NIBIRU_STRONGHOLD_CROSSING, ItemLootHelper.NIBIRU_STRONGHOLD_CROSSING_LOOT, 1, 5);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_STRONGHOLD_CROSSING, ItemLootHelper.ENCHANTED_BOOK);
        }

        public RoomCrossing() {}

        public RoomCrossing(int p_i45575_1_, Random p_i45575_2_, StructureBoundingBox p_i45575_3_, EnumFacing p_i45575_4_)
        {
            super(p_i45575_1_);
            this.coordBaseMode = p_i45575_4_;
            this.field_143013_d = this.getRandomDoor(p_i45575_2_);
            this.boundingBox = p_i45575_3_;
            this.roomType = p_i45575_2_.nextInt(5);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Type", this.roomType);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.roomType = tagCompound.getInteger("Type");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 4, 1);
            this.getNextComponentX((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 4);
            this.getNextComponentZ((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 4);
        }

        public static StructureNibiruStrongholdPieces.RoomCrossing func_175859_a(List<StructureComponent> p_175859_0_, Random p_175859_1_, int p_175859_2_, int p_175859_3_, int p_175859_4_, EnumFacing p_175859_5_, int p_175859_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175859_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.RoomCrossing(p_175859_6_, p_175859_1_, structureboundingbox, p_175859_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 10, 6, 10, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 4, 1, 0);
                this.fillWithBlocks(world, structureBoundingBox, 4, 1, 10, 6, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.fillWithBlocks(world, structureBoundingBox, 0, 1, 4, 0, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.fillWithBlocks(world, structureBoundingBox, 10, 1, 4, 10, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

                switch (this.roomType)
                {
                case 0:
                    this.createWasteTank(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5)), world, rand, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 4, 1, 4, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 4, 1, 5, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 4, 1, 6, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 6, 1, 4, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 6, 1, 5, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 6, 1, 6, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 5, 1, 4, structureBoundingBox);
                    this.setBlockState(world,  NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 5, 1, 6, structureBoundingBox);
                    break;
                case 1:
                    for (int i1 = 0; i1 < 5; ++i1)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 1, 3 + i1, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 7, 1, 3 + i1, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3 + i1, 1, 3, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3 + i1, 1, 7, structureBoundingBox);
                    }
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 5, 1, 5, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 5, 2, 5, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 5, 3, 5, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), 5, 4, 5, structureBoundingBox);
                    break;
                case 2:
                    for (int i = 1; i <= 9; ++i)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 1, 3, i, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 9, 3, i, structureBoundingBox);
                    }
                    for (int j = 1; j <= 9; ++j)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), j, 3, 1, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), j, 3, 9, structureBoundingBox);
                    }

                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 1, 4, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 1, 6, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 3, 4, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 5, 3, 6, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, 1, 5, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, 1, 5, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, 3, 5, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, 3, 5, structureBoundingBox);

                    for (int k = 1; k <= 3; ++k)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, k, 4, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, k, 4, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 4, k, 6, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE), 6, k, 6, structureBoundingBox);
                    }

                    this.setBlockState(world, NibiruBlocks.INFECTED_TORCH.getDefaultState(), 5, 3, 5, structureBoundingBox);

                    for (int l = 2; l <= 8; ++l)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 2, 3, l, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 3, 3, l, structureBoundingBox);

                        if (l <= 3 || l >= 7)
                        {
                            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 4, 3, l, structureBoundingBox);
                            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 5, 3, l, structureBoundingBox);
                            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 6, 3, l, structureBoundingBox);
                        }
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 7, 3, l, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 3, l, structureBoundingBox);
                    }
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 1, 3, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 2, 3, structureBoundingBox);
                    this.setBlockState(world, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 3, 3, structureBoundingBox);
                    this.generateChestContents(world, structureBoundingBox, rand, 3, 4, 8, ChestGenHooks.getItems(ItemLootHelper.NIBIRU_STRONGHOLD_CROSSING, rand), ChestGenHooks.getCount(ItemLootHelper.NIBIRU_STRONGHOLD_CROSSING, rand));
                }
                return true;
            }
        }
    }

    public static class Stairs extends StructureNibiruStrongholdPieces.Stronghold
    {
        private boolean field_75024_a;

        public Stairs() {}

        public Stairs(int p_i2081_1_, Random p_i2081_2_, int p_i2081_3_, int p_i2081_4_)
        {
            super(p_i2081_1_);
            this.field_75024_a = true;
            this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2081_2_);
            this.field_143013_d = StructureNibiruStrongholdPieces.Stronghold.Door.OPENING;

            switch (this.coordBaseMode)
            {
            case NORTH:
            case SOUTH:
                this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
            }
        }

        public Stairs(int p_i45574_1_, Random p_i45574_2_, StructureBoundingBox p_i45574_3_, EnumFacing p_i45574_4_)
        {
            super(p_i45574_1_);
            this.field_75024_a = false;
            this.coordBaseMode = p_i45574_4_;
            this.field_143013_d = this.getRandomDoor(p_i45574_2_);
            this.boundingBox = p_i45574_3_;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Source", this.field_75024_a);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.field_75024_a = tagCompound.getBoolean("Source");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            if (this.field_75024_a)
            {
                StructureNibiruStrongholdPieces.strongComponentType = StructureNibiruStrongholdPieces.Crossing.class;
            }
            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
        }

        public static StructureNibiruStrongholdPieces.Stairs func_175863_a(List<StructureComponent> p_175863_0_, Random p_175863_1_, int p_175863_2_, int p_175863_3_, int p_175863_4_, EnumFacing p_175863_5_, int p_175863_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175863_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.Stairs(p_175863_6_, p_175863_1_, structureboundingbox, p_175863_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 10, 4, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 7, 0);
                this.placeDoor(world, rand, structureBoundingBox, StructureNibiruStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 4);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 6, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 5, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 6, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 5, 2, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 4, 3, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 5, 3, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 4, 3, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 3, 3, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 3, 4, 3, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 3, 2, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 2, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 3, 3, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 2, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 1, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 2, 1, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 1, 2, structureBoundingBox);
                this.setBlockState(world, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getStateFromMeta(1), 1, 1, 3, structureBoundingBox);
                return true;
            }
        }
    }

    public static class Stairs2 extends StructureNibiruStrongholdPieces.Stairs
    {
        public StructureNibiruStrongholdPieces.PieceWeight strongholdPieceWeight;
        public StructureNibiruStrongholdPieces.PortalRoom strongholdPortalRoom;
        public List<StructureComponent> field_75026_c = Lists.<StructureComponent>newArrayList();

        public Stairs2() {}

        public Stairs2(int p_i2083_1_, Random p_i2083_2_, int p_i2083_3_, int p_i2083_4_)
        {
            super(0, p_i2083_2_, p_i2083_3_, p_i2083_4_);
        }

        @Override
        public BlockPos getBoundingBoxCenter()
        {
            return this.strongholdPortalRoom != null ? this.strongholdPortalRoom.getBoundingBoxCenter() : super.getBoundingBoxCenter();
        }
    }

    public static class StairsStraight extends StructureNibiruStrongholdPieces.Stronghold
    {
        public StairsStraight() {}

        public StairsStraight(int p_i45572_1_, Random p_i45572_2_, StructureBoundingBox p_i45572_3_, EnumFacing p_i45572_4_)
        {
            super(p_i45572_1_);
            this.coordBaseMode = p_i45572_4_;
            this.field_143013_d = this.getRandomDoor(p_i45572_2_);
            this.boundingBox = p_i45572_3_;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);
        }

        public static StructureNibiruStrongholdPieces.StairsStraight func_175861_a(List<StructureComponent> p_175861_0_, Random p_175861_1_, int p_175861_2_, int p_175861_3_, int p_175861_4_, EnumFacing p_175861_5_, int p_175861_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175861_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.StairsStraight(p_175861_6_, p_175861_1_, structureboundingbox, p_175861_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 10, 7, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 7, 0);
                this.placeDoor(world, rand, structureBoundingBox, StructureNibiruStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 7);
                int i = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 2);

                for (int j = 0; j < 6; ++j)
                {
                    this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 1, 6 - j, 1 + j, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 2, 6 - j, 1 + j, structureBoundingBox);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 3, 6 - j, 1 + j, structureBoundingBox);

                    if (j < 5)
                    {
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 1, 5 - j, 1 + j, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 2, 5 - j, 1 + j, structureBoundingBox);
                        this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), 3, 5 - j, 1 + j, structureBoundingBox);
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
        public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
        {
            if (p_75062_5_)
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
                this.blockstate = Blocks.air.getDefaultState();
            }
        }
    }

    public static class Straight extends StructureNibiruStrongholdPieces.Stronghold
    {
        private boolean expandsX;
        private boolean expandsZ;

        public Straight() {}

        public Straight(int p_i45573_1_, Random p_i45573_2_, StructureBoundingBox p_i45573_3_, EnumFacing p_i45573_4_)
        {
            super(p_i45573_1_);
            this.coordBaseMode = p_i45573_4_;
            this.field_143013_d = this.getRandomDoor(p_i45573_2_);
            this.boundingBox = p_i45573_3_;
            this.expandsX = p_i45573_2_.nextInt(2) == 0;
            this.expandsZ = p_i45573_2_.nextInt(2) == 0;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Left", this.expandsX);
            tagCompound.setBoolean("Right", this.expandsZ);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.expandsX = tagCompound.getBoolean("Left");
            this.expandsZ = tagCompound.getBoolean("Right");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            this.getNextComponentNormal((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 1);

            if (this.expandsX)
            {
                this.getNextComponentX((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 2);
            }
            if (this.expandsZ)
            {
                this.getNextComponentZ((StructureNibiruStrongholdPieces.Stairs2)componentIn, listIn, rand, 1, 2);
            }
        }

        public static StructureNibiruStrongholdPieces.Straight func_175862_a(List<StructureComponent> p_175862_0_, Random p_175862_1_, int p_175862_2_, int p_175862_3_, int p_175862_4_, EnumFacing p_175862_5_, int p_175862_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
            return canStrongholdGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175862_0_, structureboundingbox) == null ? new StructureNibiruStrongholdPieces.Straight(p_175862_6_, p_175862_1_, structureboundingbox, p_175862_5_) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (this.isLiquidInStructureBoundingBox(world, structureBoundingBox))
            {
                return false;
            }
            else
            {
                this.fillWithRandomizedBlocks(world, structureBoundingBox, 0, 0, 0, 4, 4, 6, true, rand, StructureNibiruStrongholdPieces.strongholdStones);
                this.placeDoor(world, rand, structureBoundingBox, this.field_143013_d, 1, 1, 0);
                this.placeDoor(world, rand, structureBoundingBox, StructureNibiruStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
                this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 1, 2, 1, NibiruBlocks.INFECTED_TORCH.getDefaultState());
                this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 3, 2, 1, NibiruBlocks.INFECTED_TORCH.getDefaultState());
                this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 1, 2, 5, NibiruBlocks.INFECTED_TORCH.getDefaultState());
                this.randomlyPlaceBlock(world, structureBoundingBox, rand, 0.1F, 3, 2, 5, NibiruBlocks.INFECTED_TORCH.getDefaultState());

                if (this.expandsX)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 0, 1, 2, 0, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                if (this.expandsZ)
                {
                    this.fillWithBlocks(world, structureBoundingBox, 4, 1, 2, 4, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                }
                return true;
            }
        }
    }

    public abstract static class Stronghold extends StructureComponentMP
    {
        protected StructureNibiruStrongholdPieces.Stronghold.Door field_143013_d = StructureNibiruStrongholdPieces.Stronghold.Door.OPENING;

        public Stronghold() {}

        protected Stronghold(int p_i2087_1_)
        {
            super(p_i2087_1_);
        }

        @Override
        protected boolean generateChestContents(World world, StructureBoundingBox boundingBoxIn, Random rand, int x, int y, int z, List<WeightedRandomChestContent> listIn, int max)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

            if (boundingBoxIn.isVecInside(blockpos) && world.getBlockState(blockpos).getBlock() != NibiruBlocks.INFECTED_CHEST)
            {
                IBlockState iblockstate = NibiruBlocks.INFECTED_CHEST.getDefaultState();
                world.setBlockState(blockpos, ((BlockChestMP)NibiruBlocks.INFECTED_CHEST).correctFacing(world, blockpos, iblockstate), 2);
                TileEntity tileentity = world.getTileEntity(blockpos);

                if (tileentity instanceof TileEntityChestMP)
                {
                    WeightedRandomChestContent.generateChestContents(rand, listIn, (TileEntityChestMP)tileentity, max);
                }
                return true;
            }
            else
            {
                return false;
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setString("EntryDoor", this.field_143013_d.name());
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            this.field_143013_d = StructureNibiruStrongholdPieces.Stronghold.Door.valueOf(tagCompound.getString("EntryDoor"));
        }

        protected void placeDoor(World world, Random p_74990_2_, StructureBoundingBox p_74990_3_, StructureNibiruStrongholdPieces.Stronghold.Door p_74990_4_, int p_74990_5_, int p_74990_6_, int p_74990_7_)
        {
            switch (p_74990_4_)
            {
            case OPENING:
            default:
                this.fillWithBlocks(world, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                break;
            case WOOD_DOOR:
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.INFECTED_DOOR_BLOCK.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.INFECTED_DOOR_BLOCK.getStateFromMeta(8), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                break;
            case GRATES:
                this.setBlockState(world, Blocks.air.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.air.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                break;
            case IRON_DOOR:
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.INFECTED_STONE_BRICKS), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_door.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.iron_door.getStateFromMeta(8), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                this.setBlockState(world, Blocks.stone_button.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_button, 4)), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
                this.setBlockState(world, Blocks.stone_button.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_button, 3)), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
            }
        }

        protected StructureNibiruStrongholdPieces.Stronghold.Door getRandomDoor(Random p_74988_1_)
        {
            int i = p_74988_1_.nextInt(5);

            switch (i)
            {
            case 0:
            case 1:
            default:
                return StructureNibiruStrongholdPieces.Stronghold.Door.OPENING;
            case 2:
                return StructureNibiruStrongholdPieces.Stronghold.Door.WOOD_DOOR;
            case 3:
                return StructureNibiruStrongholdPieces.Stronghold.Door.GRATES;
            case 4:
                return StructureNibiruStrongholdPieces.Stronghold.Door.IRON_DOOR;
            }
        }

        protected StructureComponent getNextComponentNormal(StructureNibiruStrongholdPieces.Stairs2 p_74986_1_, List<StructureComponent> p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, this.coordBaseMode, this.getComponentType());
                case SOUTH:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, this.coordBaseMode, this.getComponentType());
                case WEST:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, this.coordBaseMode, this.getComponentType());
                case EAST:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, this.coordBaseMode, this.getComponentType());
                }
            }
            return null;
        }

        protected StructureComponent getNextComponentX(StructureNibiruStrongholdPieces.Stairs2 p_74989_1_, List<StructureComponent> p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
                case SOUTH:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
                case WEST:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                case EAST:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }
            return null;
        }

        protected StructureComponent getNextComponentZ(StructureNibiruStrongholdPieces.Stairs2 p_74987_1_, List<StructureComponent> p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
                case SOUTH:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
                case WEST:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                case EAST:
                    return StructureNibiruStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
            return null;
        }

        protected static boolean canStrongholdGoDeeper(StructureBoundingBox p_74991_0_)
        {
            return p_74991_0_ != null && p_74991_0_.minY > 10;
        }

        protected void createWasteTank(BlockPos basePos, World world, Random rand, StructureBoundingBox structureBoundingBox)
        {
            if (structureBoundingBox.isVecInside(basePos))
            {
                world.setBlockState(basePos, NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, Boolean.valueOf(rand.nextFloat() > 0.9F)), 2);
                world.setBlockState(basePos.up(), MPBlocks.DUMMY_BLOCK.getStateFromMeta(5), 2);
                world.setBlockState(basePos.up(2), MPBlocks.DUMMY_BLOCK.getStateFromMeta(6), 2);
                TileEntity tile = world.getTileEntity(basePos);
                TileEntity tile1 = world.getTileEntity(basePos.up());
                TileEntity tile2 = world.getTileEntity(basePos.up(2));

                if (tile1 instanceof TileEntityDummy && tile2 instanceof TileEntityDummy)
                {
                    TileEntityDummy dummy1 = (TileEntityDummy) tile1;
                    TileEntityDummy dummy2 = (TileEntityDummy) tile2;
                    dummy1.mainBlockPosition = basePos;
                    dummy2.mainBlockPosition = basePos;
                }
                if (tile instanceof TileEntityNuclearWasteTank)
                {
                    TileEntityNuclearWasteTank waste = (TileEntityNuclearWasteTank) tile;
                    waste.hasRod = true;
                    waste.onCreate(world, basePos);
                }
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
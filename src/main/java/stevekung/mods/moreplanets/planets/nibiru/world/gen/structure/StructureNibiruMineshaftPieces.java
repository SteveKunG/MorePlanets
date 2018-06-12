package stevekung.mods.moreplanets.planets.nibiru.world.gen.structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.entity.EntitySpaceMinecartChest;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedCaveSpider;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class StructureNibiruMineshaftPieces
{
    public static void registerStructurePieces()
    {
        MapGenStructureIO.registerStructureComponent(Corridor.class, "NibiruMSCorridor");
        MapGenStructureIO.registerStructureComponent(Cross.class, "NibiruMSCrossing");
        MapGenStructureIO.registerStructureComponent(Room.class, "NibiruMSRoom");
        MapGenStructureIO.registerStructureComponent(Stairs.class, "NibiruMSStairs");
    }

    public static class Corridor extends Piece
    {
        private boolean hasRails;
        private boolean hasSpiders;
        private boolean spawnerPlaced;
        private int sectionCount;

        public Corridor() {}

        public Corridor(int type, Random rand, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = box;
            this.hasRails = rand.nextInt(3) == 0;
            this.hasSpiders = !this.hasRails && rand.nextInt(23) == 0;

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
            {
                this.sectionCount = box.getZSize() / 5;
            }
            else
            {
                this.sectionCount = box.getXSize() / 5;
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            nbt.setBoolean("hr", this.hasRails);
            nbt.setBoolean("sc", this.hasSpiders);
            nbt.setBoolean("hps", this.spawnerPlaced);
            nbt.setInteger("Num", this.sectionCount);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
        {
            this.hasRails = nbt.getBoolean("hr");
            this.hasSpiders = nbt.getBoolean("sc");
            this.spawnerPlaced = nbt.getBoolean("hps");
            this.sectionCount = nbt.getInteger("Num");
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            int i = this.getComponentType();
            int j = rand.nextInt(4);
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                case NORTH:
                default:
                    if (j <= 1)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, enumfacing, i);
                    }
                    else if (j == 2)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.WEST, i);
                    }
                    else
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.EAST, i);
                    }
                    break;
                case SOUTH:
                    if (j <= 1)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, enumfacing, i);
                    }
                    else if (j == 2)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.WEST, i);
                    }
                    else
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.EAST, i);
                    }
                    break;
                case WEST:
                    if (j <= 1)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
                    }
                    else if (j == 2)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                    }
                    else
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                    }
                    break;
                case EAST:
                    if (j <= 1)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
                    }
                    else if (j == 2)
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                    }
                    else
                    {
                        this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                    }
                }
            }

            if (i < 8)
            {
                if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.SOUTH)
                {
                    for (int i1 = this.boundingBox.minX + 3; i1 + 3 <= this.boundingBox.maxX; i1 += 5)
                    {
                        int j1 = rand.nextInt(5);

                        if (j1 == 0)
                        {
                            this.generateAndAddPiece(component, list, rand, i1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i + 1);
                        }
                        else if (j1 == 1)
                        {
                            this.generateAndAddPiece(component, list, rand, i1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i + 1);
                        }
                    }
                }
                else
                {
                    for (int k = this.boundingBox.minZ + 3; k + 3 <= this.boundingBox.maxZ; k += 5)
                    {
                        int l = rand.nextInt(5);

                        if (l == 0)
                        {
                            this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, k, EnumFacing.WEST, i + 1);
                        }
                        else if (l == 1)
                        {
                            this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, k, EnumFacing.EAST, i + 1);
                        }
                    }
                }
            }
        }

        @Override
        protected boolean generateChest(World world, StructureBoundingBox structurebb, Random rand, int x, int y, int z, ResourceLocation loot)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

            if (structurebb.isVecInside(blockpos) && world.getBlockState(blockpos).getMaterial() == Material.AIR && world.getBlockState(blockpos.down()).getMaterial() != Material.AIR)
            {
                IBlockState iblockstate = Blocks.RAIL.getDefaultState().withProperty(BlockRail.SHAPE, rand.nextBoolean() ? BlockRailBase.EnumRailDirection.NORTH_SOUTH : BlockRailBase.EnumRailDirection.EAST_WEST);
                this.setBlockState(world, iblockstate, x, y, z, structurebb);
                EntitySpaceMinecartChest entityminecartchest = new EntitySpaceMinecartChest(world, blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F);
                entityminecartchest.setLootTable(loot, rand.nextLong());
                entityminecartchest.setDisplayTile(MPBlocks.INFECTED_CHEST.getDefaultState().withProperty(BlockStateProperty.FACING_HORIZON, EnumFacing.NORTH));
                entityminecartchest.setDisplayTileOffset(8);
                world.spawnEntity(entityminecartchest);
                return true;
            }
            else
            {
                return false;
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
                int i1 = this.sectionCount * 5 - 1;
                IBlockState iblockstate = this.getPlanks();
                this.fillWithBlocks(world, box, 0, 0, 0, 2, 1, i1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.generateMaybeBox(world, box, rand, 0.8F, 0, 2, 0, 2, 2, i1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false, 0);

                if (this.hasSpiders)
                {
                    this.generateMaybeBox(world, box, rand, 0.6F, 0, 0, 0, 2, 1, i1, Blocks.WEB.getDefaultState(), Blocks.AIR.getDefaultState(), false, 8);
                }

                for (int j1 = 0; j1 < this.sectionCount; ++j1)
                {
                    int k1 = 2 + j1 * 5;
                    this.placeSupport(world, box, 0, 0, k1, 2, 2, rand);
                    this.placeCobWeb(world, box, rand, 0.1F, 0, 2, k1 - 1);
                    this.placeCobWeb(world, box, rand, 0.1F, 2, 2, k1 - 1);
                    this.placeCobWeb(world, box, rand, 0.1F, 0, 2, k1 + 1);
                    this.placeCobWeb(world, box, rand, 0.1F, 2, 2, k1 + 1);
                    this.placeCobWeb(world, box, rand, 0.05F, 0, 2, k1 - 2);
                    this.placeCobWeb(world, box, rand, 0.05F, 2, 2, k1 - 2);
                    this.placeCobWeb(world, box, rand, 0.05F, 0, 2, k1 + 2);
                    this.placeCobWeb(world, box, rand, 0.05F, 2, 2, k1 + 2);

                    if (rand.nextInt(100) == 0)
                    {
                        this.generateChest(world, box, rand, 2, 0, k1 - 1, MPLootTables.COMMON_SPACE_MINESHAFT);
                    }
                    if (this.hasSpiders && !this.spawnerPlaced)
                    {
                        int l1 = this.getYWithOffset(0);
                        int i2 = k1 - 1 + rand.nextInt(3);
                        int j2 = this.getXWithOffset(1, i2);
                        int k2 = this.getZWithOffset(1, i2);
                        BlockPos blockpos = new BlockPos(j2, l1, k2);

                        if (box.isVecInside(blockpos) && this.getSkyBrightness(world, 1, 0, i2, box) < 8)
                        {
                            this.spawnerPlaced = true;
                            world.setBlockState(blockpos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                            TileEntity tileentity = world.getTileEntity(blockpos);

                            if (tileentity instanceof TileEntityMobSpawner)
                            {
                                ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityInfectedCaveSpider.class));
                            }
                        }
                    }
                }

                for (int l2 = 0; l2 <= 2; ++l2)
                {
                    for (int i3 = 0; i3 <= i1; ++i3)
                    {
                        IBlockState iblockstate3 = this.getBlockStateFromPos(world, l2, -1, i3, box);

                        if (iblockstate3.getMaterial() == Material.AIR && this.getSkyBrightness(world, l2, -1, i3, box) < 8)
                        {
                            this.setBlockState(world, iblockstate, l2, -1, i3, box);
                        }
                    }
                }

                if (this.hasRails)
                {
                    IBlockState iblockstate1 = Blocks.RAIL.getDefaultState().withProperty(BlockRail.SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH);

                    for (int j3 = 0; j3 <= i1; ++j3)
                    {
                        IBlockState iblockstate2 = this.getBlockStateFromPos(world, 1, -1, j3, box);

                        if (iblockstate2.getMaterial() != Material.AIR && iblockstate2.isFullBlock())
                        {
                            float f = this.getSkyBrightness(world, 1, 0, j3, box) > 8 ? 0.9F : 0.7F;
                            this.randomlyPlaceBlock(world, box, rand, f, 1, 0, j3, iblockstate1);
                        }
                    }
                }
                return true;
            }
        }

        private void placeSupport(World world, StructureBoundingBox box, int par3, int par4, int par5, int par6, int par7, Random rand)
        {
            if (this.isSupportingBox(world, box, par3, par7, par6, par5))
            {
                IBlockState iblockstate = this.getPlanks();
                IBlockState iblockstate1 = this.getFence();
                IBlockState iblockstate2 = Blocks.AIR.getDefaultState();
                this.fillWithBlocks(world, box, par3, par4, par5, par3, par6 - 1, par5, iblockstate1, iblockstate2, false);
                this.fillWithBlocks(world, box, par7, par4, par5, par7, par6 - 1, par5, iblockstate1, iblockstate2, false);

                if (rand.nextInt(4) == 0)
                {
                    this.fillWithBlocks(world, box, par3, par6, par5, par3, par6, par5, iblockstate, iblockstate2, false);
                    this.fillWithBlocks(world, box, par7, par6, par5, par7, par6, par5, iblockstate, iblockstate2, false);
                }
                else
                {
                    this.fillWithBlocks(world, box, par3, par6, par5, par7, par6, par5, iblockstate, iblockstate2, false);
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, par3 + 1, par6, par5 - 1, MPBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, par3 + 1, par6, par5 + 1, MPBlocks.INFECTED_TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
                }
            }
        }

        private void placeCobWeb(World world, StructureBoundingBox box, Random rand, float chance, int x, int y, int z)
        {
            if (this.getSkyBrightness(world, x, y, z, box) < 8)
            {
                this.randomlyPlaceBlock(world, box, rand, chance, x, y, z, Blocks.WEB.getDefaultState());
            }
        }
    }

    public static class Cross extends Piece
    {
        private EnumFacing corridorDirection;
        private boolean isMultipleFloors;

        public Cross() {}

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            nbt.setBoolean("tf", this.isMultipleFloors);
            nbt.setInteger("D", this.corridorDirection.getHorizontalIndex());
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
        {
            this.isMultipleFloors = nbt.getBoolean("tf");
            this.corridorDirection = EnumFacing.getHorizontal(nbt.getInteger("D"));
        }

        public Cross(int type, StructureBoundingBox box, @Nullable EnumFacing facing)
        {
            super(type);
            this.corridorDirection = facing;
            this.boundingBox = box;
            this.isMultipleFloors = box.getYSize() > 3;
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            int i = this.getComponentType();

            switch (this.corridorDirection)
            {
            case NORTH:
            default:
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
                break;
            case SOUTH:
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
                break;
            case WEST:
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                break;
            case EAST:
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
            }

            if (this.isMultipleFloors)
            {
                if (rand.nextBoolean())
                {
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                }
                if (rand.nextBoolean())
                {
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                }
                if (rand.nextBoolean())
                {
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
                }
                if (rand.nextBoolean())
                {
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                }
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
                IBlockState iblockstate = this.getPlanks();

                if (this.isMultipleFloors)
                {
                    this.fillWithBlocks(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ - 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    this.fillWithBlocks(world, box, this.boundingBox.minX + 1, this.boundingBox.maxY - 2, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.maxY - 2, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    this.fillWithBlocks(world, box, this.boundingBox.minX + 1, this.boundingBox.minY + 3, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.minY + 3, this.boundingBox.maxZ - 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                else
                {
                    this.fillWithBlocks(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }

                this.placeSupportPillar(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY);
                this.placeSupportPillar(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY);
                this.placeSupportPillar(world, box, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY);
                this.placeSupportPillar(world, box, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY);

                for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
                {
                    for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
                    {
                        if (this.getBlockStateFromPos(world, i, this.boundingBox.minY - 1, j, box).getMaterial() == Material.AIR && this.getSkyBrightness(world, i, this.boundingBox.minY - 1, j, box) < 8)
                        {
                            this.setBlockState(world, iblockstate, i, this.boundingBox.minY - 1, j, box);
                        }
                    }
                }
                return true;
            }
        }

        private void placeSupportPillar(World world, StructureBoundingBox box, int x, int minY, int z, int maxY)
        {
            if (this.getBlockStateFromPos(world, x, maxY + 1, z, box).getMaterial() != Material.AIR)
            {
                this.fillWithBlocks(world, box, x, minY, z, x, maxY, z, this.getPlanks(), Blocks.AIR.getDefaultState(), false);
            }
        }
    }

    abstract static class Piece extends StructureComponent
    {
        public Piece() {}

        public Piece(int type)
        {
            super(type);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt) {}

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager) {}

        protected IBlockState getPlanks()
        {
            return MPBlocks.INFECTED_OAK_PLANKS.getDefaultState();
        }

        protected IBlockState getFence()
        {
            return MPBlocks.INFECTED_OAK_FENCE.getDefaultState();
        }

        protected boolean isSupportingBox(World world, StructureBoundingBox box, int xMin, int x, int y, int z)
        {
            for (int i = xMin; i <= x; ++i)
            {
                if (this.getBlockStateFromPos(world, i,y + 1, z, box).getMaterial() == Material.AIR)
                {
                    return false;
                }
            }
            return true;
        }

        protected Piece generateAndAddPiece(StructureComponent component, List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing, int type)
        {
            if (type > 8)
            {
                return null;
            }
            else if (Math.abs(x - component.getBoundingBox().minX) <= 80 && Math.abs(z - component.getBoundingBox().minZ) <= 80)
            {
                Piece piece = this.createRandomShaftPiece(list, rand, x, y, z, facing, type + 1);

                if (piece != null)
                {
                    list.add(piece);
                    piece.buildComponent(component, list, rand);
                }
                return piece;
            }
            else
            {
                return null;
            }
        }

        private Piece createRandomShaftPiece(List<StructureComponent> component, Random rand, int x, int y, int z, @Nullable EnumFacing facing, int type)
        {
            int i = rand.nextInt(100);

            if (i >= 80)
            {
                StructureBoundingBox box = this.findCrossing(component, rand, x, y, z, facing);

                if (box != null)
                {
                    return new Cross(type, box, facing);
                }
            }
            else if (i >= 70)
            {
                StructureBoundingBox box = this.findStairs(component, rand, x, y, z, facing);

                if (box != null)
                {
                    return new Stairs(type, box, facing);
                }
            }
            else
            {
                StructureBoundingBox box = this.findCorridorSize(component, rand, x, y, z, facing);

                if (box != null)
                {
                    return new Corridor(type, rand, box, facing);
                }
            }
            return null;
        }

        private StructureBoundingBox findCorridorSize(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox box = new StructureBoundingBox(x, y, z, x, y + 2, z);
            int i;

            for (i = rand.nextInt(3) + 2; i > 0; --i)
            {
                int j = i * 5;

                switch (facing)
                {
                case NORTH:
                default:
                    box.maxX = x + 2;
                    box.minZ = z - (j - 1);
                    break;
                case SOUTH:
                    box.maxX = x + 2;
                    box.maxZ = z + j - 1;
                    break;
                case WEST:
                    box.minX = x - (j - 1);
                    box.maxZ = z + 2;
                    break;
                case EAST:
                    box.maxX = x + j - 1;
                    box.maxZ = z + 2;
                }

                if (StructureComponent.findIntersecting(list, box) == null)
                {
                    break;
                }
            }
            return i > 0 ? box : null;
        }

        private StructureBoundingBox findCrossing(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox box = new StructureBoundingBox(x, y, z, x, y + 2, z);

            if (rand.nextInt(4) == 0)
            {
                box.maxY += 4;
            }

            switch (facing)
            {
            case NORTH:
            default:
                box.minX = x - 1;
                box.maxX = x + 3;
                box.minZ = z - 4;
                break;
            case SOUTH:
                box.minX = x - 1;
                box.maxX = x + 3;
                box.maxZ = z + 3 + 1;
                break;
            case WEST:
                box.minX = x - 4;
                box.minZ = z - 1;
                box.maxZ = z + 3;
                break;
            case EAST:
                box.maxX = x + 3 + 1;
                box.minZ = z - 1;
                box.maxZ = z + 3;
            }
            return StructureComponent.findIntersecting(list, box) != null ? null : box;
        }

        private StructureBoundingBox findStairs(List<StructureComponent> list, Random rand, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox box = new StructureBoundingBox(x, y - 5, z, x, y + 2, z);

            switch (facing)
            {
            case NORTH:
            default:
                box.maxX = x + 2;
                box.minZ = z - 8;
                break;
            case SOUTH:
                box.maxX = x + 2;
                box.maxZ = z + 8;
                break;
            case WEST:
                box.minX = x - 8;
                box.maxZ = z + 2;
                break;
            case EAST:
                box.maxX = x + 8;
                box.maxZ = z + 2;
            }
            return StructureComponent.findIntersecting(list, box) != null ? null : box;
        }
    }

    public static class Room extends Piece
    {
        private List<StructureBoundingBox> roomsLinkedToTheRoom = new LinkedList<>();

        public Room() {}

        public Room(int type, Random rand, int x, int z)
        {
            super(type);
            this.boundingBox = new StructureBoundingBox(x, 50, z, x + 7 + rand.nextInt(6), 54 + rand.nextInt(6), z + 7 + rand.nextInt(6));
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            int i = this.getComponentType();
            int k = this.boundingBox.getYSize() - 3 - 1;
            int l;

            if (k <= 0)
            {
                k = 1;
            }
            for (int j = 0; j < this.boundingBox.getXSize(); j = l + 4)
            {
                l = j + rand.nextInt(this.boundingBox.getXSize());

                if (l + 3 > this.boundingBox.getXSize())
                {
                    break;
                }

                Piece piece = this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);

                if (piece != null)
                {
                    StructureBoundingBox box = piece.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(box.minX, box.minY, this.boundingBox.minZ, box.maxX, box.maxY, this.boundingBox.minZ + 1));
                }
            }

            for (int i1 = 0; i1 < this.boundingBox.getXSize(); i1 = l + 4)
            {
                l = i1 + rand.nextInt(this.boundingBox.getXSize());

                if (l + 3 > this.boundingBox.getXSize())
                {
                    break;
                }

                Piece piece = this.generateAndAddPiece(component, list, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);

                if (piece != null)
                {
                    StructureBoundingBox box1 = piece.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(box1.minX, box1.minY, this.boundingBox.maxZ - 1, box1.maxX, box1.maxY, this.boundingBox.maxZ));
                }
            }

            for (int j1 = 0; j1 < this.boundingBox.getZSize(); j1 = l + 4)
            {
                l = j1 + rand.nextInt(this.boundingBox.getZSize());

                if (l + 3 > this.boundingBox.getZSize())
                {
                    break;
                }

                Piece piece = this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, EnumFacing.WEST, i);

                if (piece != null)
                {
                    StructureBoundingBox box2 = piece.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.minX, box2.minY, box2.minZ, this.boundingBox.minX + 1, box2.maxY, box2.maxZ));
                }
            }

            for (int k1 = 0; k1 < this.boundingBox.getZSize(); k1 = l + 4)
            {
                l = k1 + rand.nextInt(this.boundingBox.getZSize());

                if (l + 3 > this.boundingBox.getZSize())
                {
                    break;
                }

                StructureComponent structurecomponent = this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, EnumFacing.EAST, i);

                if (structurecomponent != null)
                {
                    StructureBoundingBox box3 = structurecomponent.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.maxX - 1, box3.minY, box3.minZ, this.boundingBox.maxX, box3.maxY, box3.maxZ));
                }
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
                this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, MPBlocks.INFECTED_DIRT.getDefaultState(), Blocks.AIR.getDefaultState(), true);
                this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                for (StructureBoundingBox room : this.roomsLinkedToTheRoom)
                {
                    this.fillWithBlocks(world, room, room.minX, room.maxY - 2, room.minZ, room.maxX, room.maxY, room.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                this.randomlyRareFillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), false);
                return true;
            }
        }

        @Override
        public void offset(int x, int y, int z)
        {
            super.offset(x, y, z);

            for (StructureBoundingBox box : this.roomsLinkedToTheRoom)
            {
                box.offset(x, y, z);
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (StructureBoundingBox box : this.roomsLinkedToTheRoom)
            {
                nbttaglist.appendTag(box.toNBTTagIntArray());
            }
            nbt.setTag("Entrances", nbttaglist);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
        {
            NBTTagList nbttaglist = nbt.getTagList("Entrances", 11);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                this.roomsLinkedToTheRoom.add(new StructureBoundingBox(nbttaglist.getIntArrayAt(i)));
            }
        }
    }

    public static class Stairs extends Piece
    {
        public Stairs() {}

        public Stairs(int type, StructureBoundingBox box, EnumFacing facing)
        {
            super(type);
            this.setCoordBaseMode(facing);
            this.boundingBox = box;
        }

        @Override
        public void buildComponent(StructureComponent component, List<StructureComponent> list, Random rand)
        {
            int i = this.getComponentType();
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                case NORTH:
                default:
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                    break;
                case SOUTH:
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                    break;
                case WEST:
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, i);
                    break;
                case EAST:
                    this.generateAndAddPiece(component, list, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, i);
                }
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
                this.fillWithBlocks(world, box, 0, 5, 0, 2, 7, 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(world, box, 0, 0, 7, 2, 2, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                for (int i = 0; i < 5; ++i)
                {
                    this.fillWithBlocks(world, box, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                return true;
            }
        }
    }
}
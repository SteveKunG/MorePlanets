package stevekung.mods.moreplanets.module.planets.diona.world.gen.structure;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.entity.EntitySpaceMinecartChest;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.helper.ItemLootHelper;
import stevekung.mods.moreplanets.util.world.gen.structure.StructureComponentMP;

@SuppressWarnings("incomplete-switch")
public class StructureDionaMineshaftPieces
{
    public static void registerStructurePieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureDionaMineshaftPieces.Corridor.class, "DMSCorridor");
        MapGenStructureIO.registerStructureComponent(StructureDionaMineshaftPieces.Cross.class, "DMSCrossing");
        MapGenStructureIO.registerStructureComponent(StructureDionaMineshaftPieces.Room.class, "DMSRoom");
        MapGenStructureIO.registerStructureComponent(StructureDionaMineshaftPieces.Stairs.class, "DMSStairs");
    }

    static
    {
        ItemLootHelper.register(ItemLootHelper.COMMON_SPACE_MINESHAFT, ItemLootHelper.COMMON_SPACE_MINESHAFT_LOOT, 8, 8);
        ItemLootHelper.add(ItemLootHelper.COMMON_SPACE_MINESHAFT, ItemLootHelper.ENCHANTED_BOOK);
    }

    private static StructureComponent func_175892_a(List<StructureComponent> listIn, Random rand, int x, int y, int z, EnumFacing facing, int type)
    {
        int i = rand.nextInt(100);

        if (i >= 80)
        {
            StructureBoundingBox structureboundingbox = StructureDionaMineshaftPieces.Cross.func_175813_a(listIn, rand, x, y, z, facing);

            if (structureboundingbox != null)
            {
                return new StructureDionaMineshaftPieces.Cross(type, rand, structureboundingbox, facing);
            }
        }
        else if (i >= 70)
        {
            StructureBoundingBox structureboundingbox1 = StructureDionaMineshaftPieces.Stairs.func_175812_a(listIn, rand, x, y, z, facing);

            if (structureboundingbox1 != null)
            {
                return new StructureDionaMineshaftPieces.Stairs(type, rand, structureboundingbox1, facing);
            }
        }
        else
        {
            StructureBoundingBox structureboundingbox2 = StructureDionaMineshaftPieces.Corridor.func_175814_a(listIn, rand, x, y, z, facing);

            if (structureboundingbox2 != null)
            {
                return new StructureDionaMineshaftPieces.Corridor(type, rand, structureboundingbox2, facing);
            }
        }
        return null;
    }

    private static StructureComponent func_175890_b(StructureComponent componentIn, List<StructureComponent> listIn, Random rand, int x, int y, int z, EnumFacing facing, int type)
    {
        if (type > 8)
        {
            return null;
        }
        else if (Math.abs(x - componentIn.getBoundingBox().minX) <= 80 && Math.abs(z - componentIn.getBoundingBox().minZ) <= 80)
        {
            StructureComponent structurecomponent = func_175892_a(listIn, rand, x, y, z, facing, type + 1);

            if (structurecomponent != null)
            {
                listIn.add(structurecomponent);
                structurecomponent.buildComponent(componentIn, listIn, rand);
            }
            return structurecomponent;
        }
        else
        {
            return null;
        }
    }

    public static class Corridor extends StructureComponentMP
    {
        private boolean hasRails;
        private boolean hasSpiders;
        private boolean spawnerPlaced;
        private int sectionCount;

        public Corridor() {}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setBoolean("hr", this.hasRails);
            tagCompound.setBoolean("sc", this.hasSpiders);
            tagCompound.setBoolean("hps", this.spawnerPlaced);
            tagCompound.setInteger("Num", this.sectionCount);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            this.hasRails = tagCompound.getBoolean("hr");
            this.hasSpiders = tagCompound.getBoolean("sc");
            this.spawnerPlaced = tagCompound.getBoolean("hps");
            this.sectionCount = tagCompound.getInteger("Num");
        }

        public Corridor(int type, Random rand, StructureBoundingBox structurebb, EnumFacing facing)
        {
            super(type);
            this.coordBaseMode = facing;
            this.boundingBox = structurebb;
            this.hasRails = rand.nextInt(3) == 0;
            this.hasSpiders = !this.hasRails && rand.nextInt(23) == 0;

            if (this.getCoordBaseMode() != EnumFacing.NORTH && this.getCoordBaseMode() != EnumFacing.SOUTH)
            {
                this.sectionCount = structurebb.getXSize() / 5;
            }
            else
            {
                this.sectionCount = structurebb.getZSize() / 5;
            }
        }

        public static StructureBoundingBox func_175814_a(List<StructureComponent> p_175814_0_, Random rand, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox structureboundingbox = new StructureBoundingBox(x, y, z, x, y + 2, z);
            int i;

            for (i = rand.nextInt(3) + 2; i > 0; --i)
            {
                int j = i * 5;

                switch (facing)
                {
                case NORTH:
                    structureboundingbox.maxX = x + 2;
                    structureboundingbox.minZ = z - (j - 1);
                    break;
                case SOUTH:
                    structureboundingbox.maxX = x + 2;
                    structureboundingbox.maxZ = z + j - 1;
                    break;
                case WEST:
                    structureboundingbox.minX = x - (j - 1);
                    structureboundingbox.maxZ = z + 2;
                    break;
                case EAST:
                    structureboundingbox.maxX = x + j - 1;
                    structureboundingbox.maxZ = z + 2;
                }

                if (StructureComponent.findIntersecting(p_175814_0_, structureboundingbox) == null)
                {
                    break;
                }
            }
            return i > 0 ? structureboundingbox : null;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            int i = this.getComponentType();
            int j = rand.nextInt(4);

            if (this.getCoordBaseMode() != null)
            {
                switch (this.getCoordBaseMode())
                {
                case NORTH:
                    if (j <= 1)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, this.coordBaseMode, i);
                    }
                    else if (j == 2)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.WEST, i);
                    }
                    else
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.EAST, i);
                    }
                    break;
                case SOUTH:
                    if (j <= 1)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, this.coordBaseMode, i);
                    }
                    else if (j == 2)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.WEST, i);
                    }
                    else
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.EAST, i);
                    }
                    break;
                case WEST:
                    if (j <= 1)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, i);
                    }
                    else if (j == 2)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                    }
                    else
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                    }
                    break;
                case EAST:
                    if (j <= 1)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, this.getCoordBaseMode(), i);
                    }
                    else if (j == 2)
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                    }
                    else
                    {
                        StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                    }
                }
            }

            if (i < 8)
            {
                if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.SOUTH)
                {
                    for (int i1 = this.boundingBox.minX + 3; i1 + 3 <= this.boundingBox.maxX; i1 += 5)
                    {
                        int j1 = rand.nextInt(5);

                        if (j1 == 0)
                        {
                            StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, i1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i + 1);
                        }
                        else if (j1 == 1)
                        {
                            StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, i1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i + 1);
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
                            StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, k, EnumFacing.WEST, i + 1);
                        }
                        else if (l == 1)
                        {
                            StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, k, EnumFacing.EAST, i + 1);
                        }
                    }
                }
            }
        }

        @Override
        protected boolean generateChestContents(World world, StructureBoundingBox boundingBoxIn, Random rand, int x, int y, int z, List<WeightedRandomChestContent> listIn, int max)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

            if (boundingBoxIn.isVecInside(blockpos) && world.getBlockState(blockpos).getBlock().getMaterial() == Material.AIR)
            {
                int i = rand.nextBoolean() ? 1 : 0;
                world.setBlockState(blockpos, Blocks.RAIL.getStateFromMeta(this.getMetadataWithOffset(Blocks.RAIL, i)), 2);
                EntitySpaceMinecartChest entityminecartchest = new EntitySpaceMinecartChest(world, blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F);
                entityminecartchest.setBlockForDisplaying(DionaBlocks.DIONA_ANCIENT_CHEST.getDefaultState().withProperty(BlockStateHelper.FACING, EnumFacing.NORTH));
                entityminecartchest.setInventoryName("container.diona.ancientchest.name");
                entityminecartchest.setDisplayOffset(8);
                WeightedRandomChestContent.generateChestContents(rand, listIn, entityminecartchest, max);
                world.spawnEntityInWorld(entityminecartchest);
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
                this.fillWithBlocks(world, box, 0, 0, 0, 2, 1, i1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.func_175805_a(world, box, rand, 0.8F, 0, 2, 0, 2, 2, i1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                if (this.hasSpiders)
                {
                    this.func_175805_a(world, box, rand, 0.6F, 0, 0, 0, 2, 1, i1, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }

                for (int j1 = 0; j1 < this.sectionCount; ++j1)
                {
                    int k1 = 2 + j1 * 5;
                    this.fillWithBlocks(world, box, 0, 0, k1, 0, 1, k1, DionaBlocks.INFECTED_CRYSTALLIZE_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    this.fillWithBlocks(world, box, 2, 0, k1, 2, 1, k1, DionaBlocks.INFECTED_CRYSTALLIZE_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                    if (rand.nextInt(4) == 0)
                    {
                        this.fillWithBlocks(world, box, 0, 2, k1, 0, 2, k1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                        this.fillWithBlocks(world, box, 2, 2, k1, 2, 2, k1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    }
                    else
                    {
                        this.fillWithBlocks(world, box, 0, 2, k1, 2, 2, k1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    }

                    this.randomlyPlaceBlock(world, box, rand, 0.1F, 0, 2, k1 - 1, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.1F, 2, 2, k1 - 1, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.1F, 0, 2, k1 + 1, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.1F, 2, 2, k1 + 1, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, 0, 2, k1 - 2, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, 2, 2, k1 - 2, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, 0, 2, k1 + 2, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, 2, 2, k1 + 2, DionaBlocks.INFECTED_CRYSTALLIZE_WEB.getDefaultState());
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, 1, 2, k1 - 1, DionaBlocks.INFECTED_CRYSTALLIZE_TORCH.getStateFromMeta(EnumFacing.UP.getIndex()));
                    this.randomlyPlaceBlock(world, box, rand, 0.05F, 1, 2, k1 + 1, DionaBlocks.INFECTED_CRYSTALLIZE_TORCH.getStateFromMeta(EnumFacing.UP.getIndex()));

                    ChestGenHooks info = ChestGenHooks.getInfo(ItemLootHelper.COMMON_SPACE_MINESHAFT);

                    if (rand.nextInt(100) == 0)
                    {
                        this.generateChestContents(world, box, rand, 2, 0, k1 - 1, info.getItems(rand), info.getCount(rand));
                    }
                    if (rand.nextInt(100) == 0)
                    {
                        this.generateChestContents(world, box, rand, 0, 0, k1 + 1, info.getItems(rand), info.getCount(rand));
                    }

                    if (this.hasSpiders && !this.spawnerPlaced)
                    {
                        int l1 = this.getYWithOffset(0);
                        int i2 = k1 - 1 + rand.nextInt(3);
                        int j2 = this.getXWithOffset(1, i2);
                        i2 = this.getZWithOffset(1, i2);
                        BlockPos blockpos = new BlockPos(j2, l1, i2);

                        if (box.isVecInside(blockpos))
                        {
                            this.spawnerPlaced = true;
                            world.setBlockState(blockpos, Blocks.mob_spawner.getDefaultState(), 2);
                            TileEntity tileentity = world.getTileEntity(blockpos);

                            if (tileentity instanceof TileEntityMobSpawner)
                            {
                                ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName("MorePlanets.InfectedCrystallizeSpider");
                            }
                        }
                    }
                }

                for (int k2 = 0; k2 <= 2; ++k2)
                {
                    for (int i3 = 0; i3 <= i1; ++i3)
                    {
                        int j3 = -1;
                        IBlockState iblockstate1 = this.getBlockStateFromPos(world, k2, j3, i3, box);

                        if (iblockstate1.getBlock().getMaterial() == Material.AIR)
                        {
                            int k3 = -1;
                            this.setBlockState(world, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), k2, k3, i3, box);
                        }
                    }
                }

                if (this.hasRails)
                {
                    for (int l2 = 0; l2 <= i1; ++l2)
                    {
                        IBlockState iblockstate = this.getBlockStateFromPos(world, 1, -1, l2, box);

                        if (iblockstate.getBlock().getMaterial() != Material.AIR && iblockstate.getBlock().isFullBlock())
                        {
                            this.randomlyPlaceBlock(world, box, rand, 0.7F, 1, 0, l2, Blocks.RAIL.getStateFromMeta(this.getMetadataWithOffset(Blocks.RAIL, 0)));
                        }
                    }
                }
                return true;
            }
        }
    }

    public static class Cross extends StructureComponentMP
    {
        private EnumFacing corridorDirection;
        private boolean isMultipleFloors;

        public Cross() {}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setBoolean("tf", this.isMultipleFloors);
            tagCompound.setInteger("D", this.corridorDirection.getHorizontalIndex());
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            this.isMultipleFloors = tagCompound.getBoolean("tf");
            this.corridorDirection = EnumFacing.getHorizontal(tagCompound.getInteger("D"));
        }

        public Cross(int type, Random rand, StructureBoundingBox structurebb, EnumFacing facing)
        {
            super(type);
            this.corridorDirection = facing;
            this.boundingBox = structurebb;
            this.isMultipleFloors = structurebb.getYSize() > 3;
        }

        public static StructureBoundingBox func_175813_a(List<StructureComponent> listIn, Random rand, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox structureboundingbox = new StructureBoundingBox(x, y, z, x, y + 2, z);

            if (rand.nextInt(4) == 0)
            {
                structureboundingbox.maxY += 4;
            }

            switch (facing)
            {
            case NORTH:
                structureboundingbox.minX = x - 1;
                structureboundingbox.maxX = x + 3;
                structureboundingbox.minZ = z - 4;
                break;
            case SOUTH:
                structureboundingbox.minX = x - 1;
                structureboundingbox.maxX = x + 3;
                structureboundingbox.maxZ = z + 4;
                break;
            case WEST:
                structureboundingbox.minX = x - 4;
                structureboundingbox.minZ = z - 1;
                structureboundingbox.maxZ = z + 3;
                break;
            case EAST:
                structureboundingbox.maxX = x + 4;
                structureboundingbox.minZ = z - 1;
                structureboundingbox.maxZ = z + 3;
            }
            return StructureComponent.findIntersecting(listIn, structureboundingbox) != null ? null : structureboundingbox;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            int i = this.getComponentType();

            switch (this.corridorDirection)
            {
            case NORTH:
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
                break;
            case SOUTH:
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
                break;
            case WEST:
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                break;
            case EAST:
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
            }

            if (this.isMultipleFloors)
            {
                if (rand.nextBoolean())
                {
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                }
                if (rand.nextBoolean())
                {
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.WEST, i);
                }
                if (rand.nextBoolean())
                {
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.EAST, i);
                }
                if (rand.nextBoolean())
                {
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
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

                this.fillWithBlocks(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(world, box, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                this.fillWithBlocks(world, box, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
                {
                    for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
                    {
                        if (this.getBlockStateFromPos(world, i, this.boundingBox.minY - 1, j, box).getBlock().getMaterial() == Material.AIR)
                        {
                            this.setBlockState(world, DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS.getDefaultState(), i, this.boundingBox.minY - 1, j, box);
                        }
                    }
                }
                return true;
            }
        }
    }

    public static class Room extends StructureComponentMP
    {
        private List<StructureBoundingBox> roomsLinkedToTheRoom = Lists.<StructureBoundingBox>newLinkedList();

        public Room() {}

        public Room(int type, Random rand, int x, int z)
        {
            super(type);
            this.boundingBox = new StructureBoundingBox(x, 50, z, x + 7 + rand.nextInt(6), 54 + rand.nextInt(6), z + 7 + rand.nextInt(6));
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            int i = this.getComponentType();
            int k = this.boundingBox.getYSize() - 3 - 1;

            if (k <= 0)
            {
                k = 1;
            }

            int l;

            for (int j = 0; j < this.boundingBox.getXSize(); j = l + 4)
            {
                l = j + rand.nextInt(this.boundingBox.getXSize());

                if (l + 3 > this.boundingBox.getXSize())
                {
                    break;
                }

                StructureComponent structurecomponent = StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);

                if (structurecomponent != null)
                {
                    StructureBoundingBox structureboundingbox = structurecomponent.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(structureboundingbox.minX, structureboundingbox.minY, this.boundingBox.minZ, structureboundingbox.maxX, structureboundingbox.maxY, this.boundingBox.minZ + 1));
                }
            }

            for (int i1 = 0; i1 < this.boundingBox.getXSize(); i1 = l + 4)
            {
                l = i1 + rand.nextInt(this.boundingBox.getXSize());

                if (l + 3 > this.boundingBox.getXSize())
                {
                    break;
                }

                StructureComponent structurecomponent1 = StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);

                if (structurecomponent1 != null)
                {
                    StructureBoundingBox structureboundingbox1 = structurecomponent1.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(structureboundingbox1.minX, structureboundingbox1.minY, this.boundingBox.maxZ - 1, structureboundingbox1.maxX, structureboundingbox1.maxY, this.boundingBox.maxZ));
                }
            }

            for (int j1 = 0; j1 < this.boundingBox.getZSize(); j1 = l + 4)
            {
                l = j1 + rand.nextInt(this.boundingBox.getZSize());

                if (l + 3 > this.boundingBox.getZSize())
                {
                    break;
                }

                StructureComponent structurecomponent2 = StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, EnumFacing.WEST, i);

                if (structurecomponent2 != null)
                {
                    StructureBoundingBox structureboundingbox2 = structurecomponent2.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.minX, structureboundingbox2.minY, structureboundingbox2.minZ, this.boundingBox.minX + 1, structureboundingbox2.maxY, structureboundingbox2.maxZ));
                }
            }

            for (int k1 = 0; k1 < this.boundingBox.getZSize(); k1 = l + 4)
            {
                l = k1 + rand.nextInt(this.boundingBox.getZSize());

                if (l + 3 > this.boundingBox.getZSize())
                {
                    break;
                }

                StructureComponent structurecomponent3 = StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, EnumFacing.EAST, i);

                if (structurecomponent3 != null)
                {
                    StructureBoundingBox structureboundingbox3 = structurecomponent3.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.maxX - 1, structureboundingbox3.minY, structureboundingbox3.minZ, this.boundingBox.maxX, structureboundingbox3.maxY, structureboundingbox3.maxZ));
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
                this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, DionaBlocks.DIONA_BLOCK.getStateFromMeta(1), Blocks.AIR.getDefaultState(), true);
                this.fillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

                for (StructureBoundingBox structureboundingbox : this.roomsLinkedToTheRoom)
                {
                    this.fillWithBlocks(world, box, structureboundingbox.minX, structureboundingbox.maxY - 2, structureboundingbox.minZ, structureboundingbox.maxX, structureboundingbox.maxY, structureboundingbox.maxZ, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                }
                this.randomlyRareFillWithBlocks(world, box, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.AIR.getDefaultState(), false);
                return true;
            }
        }

        @Override
        public void func_181138_a(int p_181138_1_, int p_181138_2_, int p_181138_3_)
        {
            super.func_181138_a(p_181138_1_, p_181138_2_, p_181138_3_);

            for (StructureBoundingBox structureboundingbox : this.roomsLinkedToTheRoom)
            {
                structureboundingbox.offset(p_181138_1_, p_181138_2_, p_181138_3_);
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (StructureBoundingBox structureboundingbox : this.roomsLinkedToTheRoom)
            {
                nbttaglist.appendTag(structureboundingbox.toNBTTagIntArray());
            }
            tagCompound.setTag("Entrances", nbttaglist);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            NBTTagList nbttaglist = tagCompound.getTagList("Entrances", 11);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                this.roomsLinkedToTheRoom.add(new StructureBoundingBox(nbttaglist.getIntArrayAt(i)));
            }
        }
    }

    public static class Stairs extends StructureComponentMP
    {
        public Stairs() {}

        public Stairs(int type, Random rand, StructureBoundingBox structurebb, EnumFacing facing)
        {
            super(type);
            this.coordBaseMode = facing;
            this.boundingBox = structurebb;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound) {}

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound) {}

        public static StructureBoundingBox func_175812_a(List<StructureComponent> listIn, Random rand, int x, int y, int z, EnumFacing facing)
        {
            StructureBoundingBox structureboundingbox = new StructureBoundingBox(x, y - 5, z, x, y + 2, z);

            switch (facing)
            {
            case NORTH:
                structureboundingbox.maxX = x + 2;
                structureboundingbox.minZ = z - 8;
                break;
            case SOUTH:
                structureboundingbox.maxX = x + 2;
                structureboundingbox.maxZ = z + 8;
                break;
            case WEST:
                structureboundingbox.minX = x - 8;
                structureboundingbox.maxZ = z + 2;
                break;
            case EAST:
                structureboundingbox.maxX = x + 8;
                structureboundingbox.maxZ = z + 2;
            }
            return StructureComponent.findIntersecting(listIn, structureboundingbox) != null ? null : structureboundingbox;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            int i = this.getComponentType();

            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
                    break;
                case SOUTH:
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
                    break;
                case WEST:
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, i);
                    break;
                case EAST:
                    StructureDionaMineshaftPieces.func_175890_b(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, i);
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
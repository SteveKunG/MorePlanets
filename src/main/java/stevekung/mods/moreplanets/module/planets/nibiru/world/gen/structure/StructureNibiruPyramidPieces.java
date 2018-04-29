package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.Random;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;

public abstract class StructureNibiruPyramidPieces extends StructureComponent
{
    protected int scatteredFeatureSizeX;
    protected int scatteredFeatureSizeY;
    protected int scatteredFeatureSizeZ;
    protected int horizontalPos = -1;

    public StructureNibiruPyramidPieces() {}

    protected StructureNibiruPyramidPieces(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
    {
        super(0);
        this.scatteredFeatureSizeX = sizeX;
        this.scatteredFeatureSizeY = sizeY;
        this.scatteredFeatureSizeZ = sizeZ;
        this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

        if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
        {
            this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
        }
        else
        {
            this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
        }
    }

    @Override
    protected boolean generateChest(World world, StructureBoundingBox box, Random randomIn, int x, int y, int z, ResourceLocation loot)
    {
        BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if (box.isVecInside(blockpos) && world.getBlockState(blockpos).getBlock() != NibiruBlocks.INFECTED_CHEST)
        {
            IBlockState iblockstate = NibiruBlocks.INFECTED_CHEST.getDefaultState();
            world.setBlockState(blockpos, NibiruBlocks.INFECTED_CHEST.correctFacing(world, blockpos, iblockstate), 2);
            TileEntity tileentity = world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChestMP)
            {
                ((TileEntityChestMP)tileentity).setLootTable(loot, randomIn.nextLong());
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("Width", this.scatteredFeatureSizeX);
        nbt.setInteger("Height", this.scatteredFeatureSizeY);
        nbt.setInteger("Depth", this.scatteredFeatureSizeZ);
        nbt.setInteger("HPos", this.horizontalPos);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
    {
        this.scatteredFeatureSizeX = nbt.getInteger("Width");
        this.scatteredFeatureSizeY = nbt.getInteger("Height");
        this.scatteredFeatureSizeZ = nbt.getInteger("Depth");
        this.horizontalPos = nbt.getInteger("HPos");
    }

    protected boolean offsetToAverageGroundLevel(World world, StructureBoundingBox box, int yOffset)
    {
        if (this.horizontalPos >= 0)
        {
            return true;
        }
        else
        {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
            {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                {
                    blockpos$mutableblockpos.setPos(l, 64, k);

                    if (box.isVecInside(blockpos$mutableblockpos))
                    {
                        i += Math.max(world.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), world.provider.getAverageGroundLevel());
                        ++j;
                    }
                }
            }

            if (j == 0)
            {
                return false;
            }
            else
            {
                this.horizontalPos = i / j;
                this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
                return true;
            }
        }
    }

    public static class NibiruPyramid extends StructureNibiruPyramidPieces
    {
        private boolean[] hasPlacedChest = new boolean[4];

        public NibiruPyramid() {}

        public NibiruPyramid(Random rand, int x, int z)
        {
            super(rand, x, 64, z, 21, 15, 21);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("hasPlacedChest0", this.hasPlacedChest[0]);
            nbt.setBoolean("hasPlacedChest1", this.hasPlacedChest[1]);
            nbt.setBoolean("hasPlacedChest2", this.hasPlacedChest[2]);
            nbt.setBoolean("hasPlacedChest3", this.hasPlacedChest[3]);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
        {
            super.readStructureFromNBT(nbt, manager);
            this.hasPlacedChest[0] = nbt.getBoolean("hasPlacedChest0");
            this.hasPlacedChest[1] = nbt.getBoolean("hasPlacedChest1");
            this.hasPlacedChest[2] = nbt.getBoolean("hasPlacedChest2");
            this.hasPlacedChest[3] = nbt.getBoolean("hasPlacedChest3");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithBlocks(world, box, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);

            for (int i = 1; i <= 9; ++i)
            {
                this.fillWithBlocks(world, box, i, i, i, this.scatteredFeatureSizeX - 1 - i, i, this.scatteredFeatureSizeZ - 1 - i, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
                this.fillWithBlocks(world, box, i + 1, i, i + 1, this.scatteredFeatureSizeX - 2 - i, i, this.scatteredFeatureSizeZ - 2 - i, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }
            for (int i2 = 0; i2 < this.scatteredFeatureSizeX; ++i2)
            {
                for (int j = 0; j < this.scatteredFeatureSizeZ; ++j)
                {
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), i2, -5, j, box);
                }
            }

            IBlockState iblockstate1 = NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
            IBlockState iblockstate2 = NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
            IBlockState iblockstate3 = NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST);
            IBlockState iblockstate = NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST);
            int l = ~EnumDyeColor.ORANGE.getDyeDamage() & 15;
            int i1 = ~EnumDyeColor.RED.getDyeDamage() & 15;
            this.fillWithBlocks(world, box, 0, 0, 0, 4, 9, 4, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 10, 1, 3, 10, 3, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, iblockstate1, 2, 10, 0, box);
            this.setBlockState(world, iblockstate2, 2, 10, 4, box);
            this.setBlockState(world, iblockstate3, 0, 10, 2, box);
            this.setBlockState(world, iblockstate, 4, 10, 2, box);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 5, 0, 0, this.scatteredFeatureSizeX - 1, 9, 4, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 4, 10, 1, this.scatteredFeatureSizeX - 2, 10, 3, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, iblockstate1, this.scatteredFeatureSizeX - 3, 10, 0, box);
            this.setBlockState(world, iblockstate2, this.scatteredFeatureSizeX - 3, 10, 4, box);
            this.setBlockState(world, iblockstate3, this.scatteredFeatureSizeX - 5, 10, 2, box);
            this.setBlockState(world, iblockstate, this.scatteredFeatureSizeX - 1, 10, 2, box);
            this.fillWithBlocks(world, box, 8, 0, 0, 12, 4, 4, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 1, 0, 11, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 9, 1, 1, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 9, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 9, 3, 1, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 10, 3, 1, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 11, 3, 1, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 11, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 11, 1, 1, box);
            this.fillWithBlocks(world, box, 4, 1, 1, 8, 3, 3, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 4, 1, 2, 8, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 12, 1, 1, 16, 3, 3, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 12, 1, 2, 16, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 5, 4, 5, this.scatteredFeatureSizeX - 6, 4, this.scatteredFeatureSizeZ - 6, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 4, 9, 11, 4, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, 1, 8, 8, 3, 8, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 12, 1, 8, 12, 3, 8, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, 1, 12, 8, 3, 12, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 12, 1, 12, 12, 3, 12, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 1, 5, 4, 4, 11, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 5, 1, 5, this.scatteredFeatureSizeX - 2, 4, 11, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 6, 7, 9, 6, 7, 11, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 7, 7, 9, this.scatteredFeatureSizeX - 7, 7, 11, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 5, 5, 9, 5, 7, 11, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 6, 5, 9, this.scatteredFeatureSizeX - 6, 7, 11, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 5, 5, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 5, 6, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 6, 6, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), this.scatteredFeatureSizeX - 6, 5, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), this.scatteredFeatureSizeX - 6, 6, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), this.scatteredFeatureSizeX - 7, 6, 10, box);
            this.fillWithBlocks(world, box, 2, 4, 4, 2, 6, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 3, 4, 4, this.scatteredFeatureSizeX - 3, 6, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, iblockstate1, 2, 4, 5, box);
            this.setBlockState(world, iblockstate1, 2, 3, 4, box);
            this.setBlockState(world, iblockstate1, this.scatteredFeatureSizeX - 3, 4, 5, box);
            this.setBlockState(world, iblockstate1, this.scatteredFeatureSizeX - 3, 3, 4, box);
            this.fillWithBlocks(world, box, 1, 1, 3, 2, 2, 3, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 3, 1, 3, this.scatteredFeatureSizeX - 2, 2, 3, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), 1, 1, 2, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), this.scatteredFeatureSizeX - 2, 1, 2, box);
            this.setBlockState(world, NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB.getDefaultState(), 1, 2, 2, box);
            this.setBlockState(world, NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB.getDefaultState(), this.scatteredFeatureSizeX - 2, 2, 2, box);
            this.setBlockState(world, iblockstate, 2, 1, 2, box);
            this.setBlockState(world, iblockstate3, this.scatteredFeatureSizeX - 3, 1, 2, box);
            this.fillWithBlocks(world, box, 4, 3, 5, 4, 3, 18, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 5, 3, 5, this.scatteredFeatureSizeX - 5, 3, 17, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 3, 1, 5, 4, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 6, 1, 5, this.scatteredFeatureSizeX - 5, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            for (int j1 = 5; j1 <= 17; j1 += 2)
            {
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 4, 1, j1, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), 4, 2, j1, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), this.scatteredFeatureSizeX - 5, 1, j1, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), this.scatteredFeatureSizeX - 5, 2, j1, box);
            }

            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 7, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 8, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 9, 0, 9, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 11, 0, 9, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 8, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 12, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 7, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 13, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 9, 0, 11, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 11, 0, 11, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 12, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 10, 0, 13, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 10, 0, 10, box);

            for (int j2 = 0; j2 <= this.scatteredFeatureSizeX - 1; j2 += this.scatteredFeatureSizeX - 1)
            {
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 2, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 2, 2, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 2, 3, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 3, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 3, 2, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 3, 3, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 4, 1, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), j2, 4, 2, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 4, 3, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 5, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 5, 2, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 5, 3, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 6, 1, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), j2, 6, 2, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 6, 3, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 7, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 7, 2, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), j2, 7, 3, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 8, 1, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 8, 2, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), j2, 8, 3, box);
            }

            for (int k2 = 2; k2 <= this.scatteredFeatureSizeX - 3; k2 += this.scatteredFeatureSizeX - 3 - 2)
            {
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 - 1, 2, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 2, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 + 1, 2, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 - 1, 3, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 3, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 + 1, 3, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 - 1, 4, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), k2, 4, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 + 1, 4, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 - 1, 5, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 + 1, 5, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 - 1, 6, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), k2, 6, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 + 1, 6, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 - 1, 7, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2, 7, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), k2 + 1, 7, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 - 1, 8, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2, 8, 0, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), k2 + 1, 8, 0, box);
            }

            this.fillWithBlocks(world, box, 8, 4, 0, 12, 6, 0, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 8, 6, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, 6, 0, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 9, 5, 0, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), 10, 5, 0, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(l), 11, 5, 0, box);
            this.fillWithBlocks(world, box, 8, -14, 8, 12, -11, 12, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, -10, 8, 12, -10, 12, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, -9, 8, 12, -9, 12, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, -8, 8, 12, -1, 12, NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), NibiruBlocks.INFECTED_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, -11, 9, 11, -1, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, -13, 9, 11, -13, 11, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            BlockPos blockpos = new BlockPos(this.getXWithOffset(10, 10), this.getYWithOffset(-11), this.getZWithOffset(10, 10));

            if (box.isVecInside(blockpos))
            {
                world.setBlockState(blockpos, NibiruBlocks.JUICER_EGG.getDefaultState(), 2);
            }

            this.setBlockState(world, Blocks.AIR.getDefaultState(), 8, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 8, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), 7, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 7, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), 13, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 13, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -11, 8, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -10, 8, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), 10, -10, 7, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 10, -11, 7, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -11, 12, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -10, 12, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_SANDSTONE.getDefaultState(), 10, -10, 13, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_CUT_SANDSTONE.getDefaultState(), 10, -11, 13, box);

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (!this.hasPlacedChest[enumfacing.getHorizontalIndex()])
                {
                    int k1 = enumfacing.getFrontOffsetX() * 2;
                    int l1 = enumfacing.getFrontOffsetZ() * 2;
                    this.hasPlacedChest[enumfacing.getHorizontalIndex()] = this.generateChest(world, box, rand, 10 + k1, -11, 10 + l1, MPLootTables.NIBIRU_DESERT_PYRAMID);
                }
            }
            return true;
        }
    }
}
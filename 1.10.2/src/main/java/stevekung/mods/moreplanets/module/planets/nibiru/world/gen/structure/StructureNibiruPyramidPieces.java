package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.blocks.BlockChestMP;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;
import stevekung.mods.moreplanets.util.world.gen.structure.StructureComponentMP;

public abstract class StructureNibiruPyramidPieces extends StructureComponentMP
{
    protected int scatteredFeatureSizeX;
    protected int scatteredFeatureSizeY;
    protected int scatteredFeatureSizeZ;
    protected int field_74936_d = -1;

    public StructureNibiruPyramidPieces() {}

    protected StructureNibiruPyramidPieces(Random p_i2065_1_, int p_i2065_2_, int p_i2065_3_, int p_i2065_4_, int p_i2065_5_, int p_i2065_6_, int p_i2065_7_)
    {
        super(0);
        this.scatteredFeatureSizeX = p_i2065_5_;
        this.scatteredFeatureSizeY = p_i2065_6_;
        this.scatteredFeatureSizeZ = p_i2065_7_;
        this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2065_1_);

        switch (this.coordBaseMode)
        {
        case NORTH:
        case SOUTH:
            this.boundingBox = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_5_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_7_ - 1);
            break;
        default:
            this.boundingBox = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_7_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_5_ - 1);
        }
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
        tagCompound.setInteger("Width", this.scatteredFeatureSizeX);
        tagCompound.setInteger("Height", this.scatteredFeatureSizeY);
        tagCompound.setInteger("Depth", this.scatteredFeatureSizeZ);
        tagCompound.setInteger("HPos", this.field_74936_d);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
        this.scatteredFeatureSizeX = tagCompound.getInteger("Width");
        this.scatteredFeatureSizeY = tagCompound.getInteger("Height");
        this.scatteredFeatureSizeZ = tagCompound.getInteger("Depth");
        this.field_74936_d = tagCompound.getInteger("HPos");
    }

    protected boolean func_74935_a(World world, StructureBoundingBox p_74935_2_, int p_74935_3_)
    {
        if (this.field_74936_d >= 0)
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
                    blockpos$mutableblockpos.set(l, 64, k);

                    if (p_74935_2_.isVecInside(blockpos$mutableblockpos))
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
                this.field_74936_d = i / j;
                this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + p_74935_3_, 0);
                return true;
            }
        }
    }

    public static class NibiruPyramid extends StructureNibiruPyramidPieces
    {
        private boolean[] chestLists = new boolean[4];

        public NibiruPyramid() {} {}

        static
        {
            ItemLootHelper.register(ItemLootHelper.NIBIRU_PYRAMID, ItemLootHelper.NIBIRU_PYRAMID_LOOT, 2, 7);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_PYRAMID, ItemLootHelper.ENCHANTED_BOOK);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_PYRAMID, ItemLootHelper.ENCHANTED_INFECTED_GOLDEN_APPLE);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_PYRAMID, ItemLootHelper.GOLDEN_ALIEN_BERRY);
        }

        public NibiruPyramid(Random p_i2062_1_, int p_i2062_2_, int p_i2062_3_)
        {
            super(p_i2062_1_, p_i2062_2_, 64, p_i2062_3_, 21, 15, 21);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("hasPlacedChest0", this.chestLists[0]);
            tagCompound.setBoolean("hasPlacedChest1", this.chestLists[1]);
            tagCompound.setBoolean("hasPlacedChest2", this.chestLists[2]);
            tagCompound.setBoolean("hasPlacedChest3", this.chestLists[3]);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.chestLists[0] = tagCompound.getBoolean("hasPlacedChest0");
            this.chestLists[1] = tagCompound.getBoolean("hasPlacedChest1");
            this.chestLists[2] = tagCompound.getBoolean("hasPlacedChest2");
            this.chestLists[3] = tagCompound.getBoolean("hasPlacedChest3");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            this.fillWithBlocks(world, box, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);

            for (int i = 1; i <= 9; ++i)
            {
                this.fillWithBlocks(world, box, i, i, i, this.scatteredFeatureSizeX - 1 - i, i, this.scatteredFeatureSizeZ - 1 - i, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
                this.fillWithBlocks(world, box, i + 1, i, i + 1, this.scatteredFeatureSizeX - 2 - i, i, this.scatteredFeatureSizeZ - 2 - i, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            for (int j2 = 0; j2 < this.scatteredFeatureSizeX; ++j2)
            {
                for (int j = 0; j < this.scatteredFeatureSizeZ; ++j)
                {
                    int k = -5;
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), j2, k, j, box);
                }
            }

            int k2 = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, 3);
            int l2 = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, 2);
            int i3 = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, 0);
            int l = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, 1);
            int i1 = ~EnumDyeColor.ORANGE.getDyeDamage() & 15;
            int j1 = ~EnumDyeColor.RED.getDyeDamage() & 15;
            this.fillWithBlocks(world, box, 0, 0, 0, 4, 9, 4, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 10, 1, 3, 10, 3, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(k2), 2, 10, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(l2), 2, 10, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(i3), 0, 10, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(l), 4, 10, 2, box);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 5, 0, 0, this.scatteredFeatureSizeX - 1, 9, 4, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 4, 10, 1, this.scatteredFeatureSizeX - 2, 10, 3, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(k2), this.scatteredFeatureSizeX - 3, 10, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(l2), this.scatteredFeatureSizeX - 3, 10, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(i3), this.scatteredFeatureSizeX - 5, 10, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(l), this.scatteredFeatureSizeX - 1, 10, 2, box);
            this.fillWithBlocks(world, box, 8, 0, 0, 12, 4, 4, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 1, 0, 11, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 9, 1, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 9, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 9, 3, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 10, 3, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 11, 3, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 11, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 11, 1, 1, box);
            this.fillWithBlocks(world, box, 4, 1, 1, 8, 3, 3, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 4, 1, 2, 8, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 12, 1, 1, 16, 3, 3, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 12, 1, 2, 16, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 5, 4, 5, this.scatteredFeatureSizeX - 6, 4, this.scatteredFeatureSizeZ - 6, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 4, 9, 11, 4, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, 1, 8, 8, 3, 8, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, 12, 1, 8, 12, 3, 8, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, 8, 1, 12, 8, 3, 12, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, 12, 1, 12, 12, 3, 12, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, 1, 1, 5, 4, 4, 11, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 5, 1, 5, this.scatteredFeatureSizeX - 2, 4, 11, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 6, 7, 9, 6, 7, 11, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 7, 7, 9, this.scatteredFeatureSizeX - 7, 7, 11, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 5, 5, 9, 5, 7, 11, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 6, 5, 9, this.scatteredFeatureSizeX - 6, 7, 11, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 5, 5, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 5, 6, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 6, 6, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), this.scatteredFeatureSizeX - 6, 5, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), this.scatteredFeatureSizeX - 6, 6, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), this.scatteredFeatureSizeX - 7, 6, 10, box);
            this.fillWithBlocks(world, box, 2, 4, 4, 2, 6, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 3, 4, 4, this.scatteredFeatureSizeX - 3, 6, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(k2), 2, 4, 5, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(k2), 2, 3, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(k2), this.scatteredFeatureSizeX - 3, 4, 5, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(k2), this.scatteredFeatureSizeX - 3, 3, 4, box);
            this.fillWithBlocks(world, box, 1, 1, 3, 2, 2, 3, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 3, 1, 3, this.scatteredFeatureSizeX - 2, 2, 3, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState(), 1, 1, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState(), this.scatteredFeatureSizeX - 2, 1, 2, box);
            this.setBlockState(world, NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), 1, 2, 2, box);
            this.setBlockState(world, NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM), this.scatteredFeatureSizeX - 2, 2, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(l), 2, 1, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getStateFromMeta(i3), this.scatteredFeatureSizeX - 3, 1, 2, box);
            this.fillWithBlocks(world, box, 4, 3, 5, 4, 3, 18, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 5, 3, 5, this.scatteredFeatureSizeX - 5, 3, 17, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 3, 1, 5, 4, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, this.scatteredFeatureSizeX - 6, 1, 5, this.scatteredFeatureSizeX - 5, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            for (int k1 = 5; k1 <= 17; k1 += 2)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 4, 1, k1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), 4, 2, k1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), this.scatteredFeatureSizeX - 5, 1, k1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), this.scatteredFeatureSizeX - 5, 2, k1, box);
            }

            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 10, 0, 7, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 10, 0, 8, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 9, 0, 9, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 11, 0, 9, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 8, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 12, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 7, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 13, 0, 10, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 9, 0, 11, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 11, 0, 11, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 10, 0, 12, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 10, 0, 13, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(j1), 10, 0, 10, box);

            for (int j3 = 0; j3 <= this.scatteredFeatureSizeX - 1; j3 += this.scatteredFeatureSizeX - 1)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 2, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 2, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 2, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 3, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 3, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 3, 3, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 4, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), j3, 4, 2, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 4, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 5, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 5, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 5, 3, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 6, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), j3, 6, 2, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 6, 3, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 7, 1, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 7, 2, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), j3, 7, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 8, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 8, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), j3, 8, 3, box);
            }

            for (int k3 = 2; k3 <= this.scatteredFeatureSizeX - 3; k3 += this.scatteredFeatureSizeX - 3 - 2)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 - 1, 2, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3, 2, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 + 1, 2, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 - 1, 3, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3, 3, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 + 1, 3, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3 - 1, 4, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), k3, 4, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3 + 1, 4, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 - 1, 5, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 + 1, 5, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3 - 1, 6, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), k3, 6, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3 + 1, 6, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3 - 1, 7, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3, 7, 0, box);
                this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), k3 + 1, 7, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 - 1, 8, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3, 8, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), k3 + 1, 8, 0, box);
            }

            this.fillWithBlocks(world, box, 8, 4, 0, 12, 6, 0, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 8, 6, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, 6, 0, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 9, 5, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), 10, 5, 0, box);
            this.setBlockState(world, Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(i1), 11, 5, 0, box);
            this.fillWithBlocks(world, box, 8, -14, 8, 12, -11, 12, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, 8, -10, 8, 12, -10, 12, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), false);
            this.fillWithBlocks(world, box, 8, -9, 8, 12, -9, 12, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), false);
            this.fillWithBlocks(world, box, 8, -8, 8, 12, -1, 12, NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, -11, 9, 11, -1, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            BlockPos blockpos = new BlockPos(this.getXWithOffset(10, 10), this.getYWithOffset(-11), this.getZWithOffset(10, 10));

            if (box.isVecInside(blockpos))
            {
                world.setBlockState(blockpos, NibiruBlocks.JUICER_EGG.getDefaultState(), 2);
            }

            this.fillWithBlocks(world, box, 9, -13, 9, 11, -13, 11, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 8, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 8, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), 7, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 7, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), 13, -10, 10, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 13, -11, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -11, 8, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -10, 8, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), 10, -10, 7, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 10, -11, 7, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -11, 12, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, -10, 12, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1), 10, -10, 13, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2), 10, -11, 13, box);

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (!this.chestLists[enumfacing.getHorizontalIndex()])
                {
                    int l1 = enumfacing.getFrontOffsetX() * 2;
                    int i2 = enumfacing.getFrontOffsetZ() * 2;
                    this.chestLists[enumfacing.getHorizontalIndex()] = this.generateChestContents(world, box, rand, 10 + l1, -11, 10 + i2, ChestGenHooks.getItems(ItemLootHelper.NIBIRU_PYRAMID, rand), ChestGenHooks.getCount(ItemLootHelper.NIBIRU_PYRAMID, rand));
                }
            }
            return true;
        }
    }
}
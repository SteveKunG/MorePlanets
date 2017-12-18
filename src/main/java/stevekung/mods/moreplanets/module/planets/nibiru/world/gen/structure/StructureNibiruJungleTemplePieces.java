package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.blocks.BlockChestMP;
import stevekung.mods.moreplanets.util.helper.ItemLootHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;
import stevekung.mods.moreplanets.util.world.gen.structure.StructureComponentMP;

public abstract class StructureNibiruJungleTemplePieces extends StructureComponentMP
{
    protected int scatteredFeatureSizeX;
    protected int scatteredFeatureSizeY;
    protected int scatteredFeatureSizeZ;
    protected int height = -1;

    public StructureNibiruJungleTemplePieces() {}

    protected StructureNibiruJungleTemplePieces(Random rand, int p_i2065_2_, int p_i2065_3_, int p_i2065_4_, int p_i2065_5_, int p_i2065_6_, int p_i2065_7_)
    {
        super(0);
        this.scatteredFeatureSizeX = p_i2065_5_;
        this.scatteredFeatureSizeY = p_i2065_6_;
        this.scatteredFeatureSizeZ = p_i2065_7_;
        this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);

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
    protected boolean generateChestContents(World world, StructureBoundingBox box, Random rand, int x, int y, int z, List<WeightedRandomChestContent> list, int max)
    {
        BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if (box.isVecInside(blockpos) && world.getBlockState(blockpos).getBlock() != NibiruBlocks.INFECTED_CHEST)
        {
            IBlockState iblockstate = NibiruBlocks.INFECTED_CHEST.getDefaultState();
            world.setBlockState(blockpos, ((BlockChestMP) NibiruBlocks.INFECTED_CHEST).correctFacing(world, blockpos, iblockstate), 2);
            TileEntity tileentity = world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChestMP)
            {
                WeightedRandomChestContent.generateChestContents(rand, list, (TileEntityChestMP)tileentity, max);
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
        tagCompound.setInteger("HPos", this.height);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
        this.scatteredFeatureSizeX = tagCompound.getInteger("Width");
        this.scatteredFeatureSizeY = tagCompound.getInteger("Height");
        this.scatteredFeatureSizeZ = tagCompound.getInteger("Depth");
        this.height = tagCompound.getInteger("HPos");
    }

    protected boolean func_74935_a(World world, StructureBoundingBox p_74935_2_, int p_74935_3_)
    {
        if (this.height >= 0)
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
                this.height = i / j;
                this.boundingBox.offset(0, this.height - this.boundingBox.minY + p_74935_3_, 0);
                return true;
            }
        }
    }

    public static class JungleTemple extends StructureNibiruJungleTemplePieces
    {
        private boolean hasMainChest;
        private boolean hasHiddenChest;
        private boolean hasRandomChest;
        private boolean hasTrap1;
        private boolean hasTrap2;
        private static List<WeightedRandomChestContent> INFECTED_ARROW = Lists.newArrayList(new WeightedRandomChestContent[] {new WeightedRandomChestContent(NibiruItems.INFECTED_ARROW, 0, 2, 7, 30)});
        private Stones scatteredStones = new Stones();

        static
        {
            ItemLootHelper.register(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, INFECTED_ARROW, 2, 2);
            ItemLootHelper.register(ItemLootHelper.NIBIRU_JUNGLE_TEMPLE, ItemLootHelper.NIBIRU_JUNGLE_TEMPLE_LOOT, 2, 7);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_JUNGLE_TEMPLE, ItemLootHelper.ENCHANTED_BOOK);
        }

        public JungleTemple() {}

        public JungleTemple(Random rand, int p_i2064_2_, int p_i2064_3_)
        {
            super(rand, p_i2064_2_, 64, p_i2064_3_, 12, 10, 15);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("placedMainChest", this.hasMainChest);
            tagCompound.setBoolean("placedHiddenChest", this.hasHiddenChest);
            tagCompound.setBoolean("placedRandomChest", this.hasRandomChest);
            tagCompound.setBoolean("placedTrap1", this.hasTrap1);
            tagCompound.setBoolean("placedTrap2", this.hasTrap2);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.hasMainChest = tagCompound.getBoolean("placedMainChest");
            this.hasHiddenChest = tagCompound.getBoolean("placedHiddenChest");
            this.hasRandomChest = tagCompound.getBoolean("placedRandomChest");
            this.hasTrap1 = tagCompound.getBoolean("placedTrap1");
            this.hasTrap2 = tagCompound.getBoolean("placedTrap2");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (!this.func_74935_a(world, box, 0))
            {
                return false;
            }
            else
            {
                ChestGenHooks dispenser = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER);
                ChestGenHooks chest = ChestGenHooks.getInfo(ItemLootHelper.NIBIRU_JUNGLE_TEMPLE);
                int i = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3);
                int j = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 2);
                int k = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 0);
                int l = this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 1);
                this.fillWithRandomizedBlocks(world, box, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 2, 1, 2, 9, 2, 2, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 2, 1, 12, 9, 2, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 2, 1, 3, 2, 2, 11, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 9, 1, 3, 9, 2, 11, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 1, 3, 1, 10, 6, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 1, 3, 13, 10, 6, 13, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 1, 3, 2, 1, 6, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 10, 3, 2, 10, 6, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 2, 3, 2, 9, 3, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 2, 6, 2, 9, 6, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 3, 7, 3, 8, 7, 11, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 4, 8, 4, 7, 8, 10, false, rand, this.scatteredStones);
                this.fillWithAir(world, box, 3, 1, 3, 8, 2, 11);
                this.fillWithAir(world, box, 4, 3, 6, 7, 3, 9);
                this.fillWithAir(world, box, 2, 4, 2, 9, 5, 12);
                this.fillWithAir(world, box, 4, 6, 5, 7, 6, 9);
                this.fillWithAir(world, box, 5, 7, 6, 6, 7, 8);
                this.fillWithAir(world, box, 5, 1, 2, 6, 2, 2);
                this.fillWithAir(world, box, 5, 2, 12, 6, 2, 12);
                this.fillWithAir(world, box, 5, 5, 1, 6, 5, 1);
                this.fillWithAir(world, box, 5, 5, 13, 6, 5, 13);
                this.setBlockState(world, Blocks.air.getDefaultState(), 1, 5, 5, box);
                this.setBlockState(world, Blocks.air.getDefaultState(), 10, 5, 5, box);
                this.setBlockState(world, Blocks.air.getDefaultState(), 1, 5, 9, box);
                this.setBlockState(world, Blocks.air.getDefaultState(), 10, 5, 9, box);

                for (int i1 = 0; i1 <= 14; i1 += 14)
                {
                    this.fillWithRandomizedBlocks(world, box, 2, 4, i1, 2, 5, i1, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, 4, 4, i1, 4, 5, i1, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, 7, 4, i1, 7, 5, i1, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, 9, 4, i1, 9, 5, i1, false, rand, this.scatteredStones);
                }

                this.fillWithRandomizedBlocks(world, box, 5, 6, 0, 6, 6, 0, false, rand, this.scatteredStones);

                for (int k1 = 0; k1 <= 11; k1 += 11)
                {
                    for (int j1 = 2; j1 <= 12; j1 += 2)
                    {
                        this.fillWithRandomizedBlocks(world, box, k1, 4, j1, k1, 5, j1, false, rand, this.scatteredStones);
                    }
                    this.fillWithRandomizedBlocks(world, box, k1, 6, 5, k1, 6, 5, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, k1, 6, 9, k1, 6, 9, false, rand, this.scatteredStones);
                }

                this.fillWithRandomizedBlocks(world, box, 2, 7, 2, 2, 9, 2, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 9, 7, 2, 9, 9, 2, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 2, 7, 12, 2, 9, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 9, 7, 12, 9, 9, 12, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 4, 9, 4, 4, 9, 4, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 7, 9, 4, 7, 9, 4, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 4, 9, 10, 4, 9, 10, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 7, 9, 10, 7, 9, 10, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 5, 9, 7, 6, 9, 7, false, rand, this.scatteredStones);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 5, 9, 6, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 6, 9, 6, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(j), 5, 9, 8, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(j), 6, 9, 8, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 4, 0, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 5, 0, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 6, 0, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 7, 0, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 4, 1, 8, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 4, 2, 9, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 4, 3, 10, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 7, 1, 8, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 7, 2, 9, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(i), 7, 3, 10, box);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 9, 4, 1, 9, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 7, 1, 9, 7, 1, 9, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 10, 7, 2, 10, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 5, 4, 5, 6, 4, 5, false, rand, this.scatteredStones);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(k), 4, 4, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(l), 7, 4, 5, box);

                if (!this.hasRandomChest)
                {
                    if (rand.nextBoolean())
                    {
                        this.generateChestContents(world, box, rand, 6, 5, 5, chest.getItems(rand), chest.getCount(rand));
                        this.generateChestContents(world, box, rand, 5, 5, 5, chest.getItems(rand), chest.getCount(rand));
                        this.hasRandomChest = true;
                    }
                }

                this.setBlockState(world, rand.nextBoolean() ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.air.getDefaultState(), 6, 10, 7, box);
                this.setBlockState(world, rand.nextBoolean() ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.air.getDefaultState(), 5, 10, 7, box);

                for (int l1 = 0; l1 < 4; ++l1)
                {
                    this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(j), 5, 0 - l1, 6 + l1, box);
                    this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(j), 6, 0 - l1, 6 + l1, box);
                    this.fillWithAir(world, box, 5, 0 - l1, 7 + l1, 6, 0 - l1, 9 + l1);
                }

                this.fillWithAir(world, box, 1, -3, 12, 10, -1, 13);
                this.fillWithAir(world, box, 1, -3, 1, 3, -1, 13);
                this.fillWithAir(world, box, 1, -3, 1, 9, -1, 5);

                for (int i2 = 1; i2 <= 13; i2 += 2)
                {
                    this.fillWithRandomizedBlocks(world, box, 1, -3, i2, 1, -2, i2, false, rand, this.scatteredStones);
                }
                for (int j2 = 2; j2 <= 12; j2 += 2)
                {
                    this.fillWithRandomizedBlocks(world, box, 1, -1, j2, 3, -1, j2, false, rand, this.scatteredStones);
                }

                this.fillWithRandomizedBlocks(world, box, 2, -2, 1, 5, -2, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 7, -2, 1, 9, -2, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 6, -3, 1, 6, -3, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 6, -1, 1, 6, -1, 1, false, rand, this.scatteredStones);
                this.setBlockState(world, Blocks.tripwire_hook.getStateFromMeta(this.getMetadataWithOffset(Blocks.tripwire_hook, EnumFacing.EAST.getHorizontalIndex())).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, box);
                this.setBlockState(world, Blocks.tripwire_hook.getStateFromMeta(this.getMetadataWithOffset(Blocks.tripwire_hook, EnumFacing.WEST.getHorizontalIndex())).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, box);
                this.setBlockState(world, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, box);
                this.setBlockState(world, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 7, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 6, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 5, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 4, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 3, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 2, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 5, -3, 1, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 4, -3, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 3, -3, 1, box);

                if (!this.hasTrap1)
                {
                    this.hasTrap1 = this.generateDispenserContents(world, box, rand, 3, -2, 1, EnumFacing.NORTH.getIndex(), dispenser.getItems(rand), dispenser.getCount(rand));
                }

                this.setBlockState(world, NibiruBlocks.INFECTED_VINES.getStateFromMeta(15), 3, -2, 2, box);
                this.setBlockState(world, Blocks.tripwire_hook.getStateFromMeta(this.getMetadataWithOffset(Blocks.tripwire_hook, EnumFacing.NORTH.getHorizontalIndex())).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, box);
                this.setBlockState(world, Blocks.tripwire_hook.getStateFromMeta(this.getMetadataWithOffset(Blocks.tripwire_hook, EnumFacing.SOUTH.getHorizontalIndex())).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, box);
                this.setBlockState(world, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, box);
                this.setBlockState(world, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, box);
                this.setBlockState(world, Blocks.tripwire.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 8, -3, 6, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 9, -3, 6, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 9, -3, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 9, -3, 4, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 9, -2, 4, box);

                if (!this.hasTrap2)
                {
                    this.hasTrap2 = this.generateDispenserContents(world, box, rand, 9, -2, 3, EnumFacing.WEST.getIndex(), dispenser.getItems(rand), dispenser.getCount(rand));
                }

                this.setBlockState(world, NibiruBlocks.INFECTED_VINES.getStateFromMeta(15), 8, -1, 3, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_VINES.getStateFromMeta(15), 8, -2, 3, box);

                if (!this.hasMainChest)
                {
                    this.hasMainChest = this.generateChestContents(world, box, rand, 8, -3, 3, chest.getItems(rand), chest.getCount(rand));
                }

                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 9, -3, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 8, -3, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, -3, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 5, -2, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 5, -1, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 6, -3, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 7, -2, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 7, -1, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 8, -3, 5, box);
                this.fillWithRandomizedBlocks(world, box, 9, -1, 1, 9, -1, 5, false, rand, this.scatteredStones);
                this.fillWithAir(world, box, 8, -3, 8, 10, -1, 10);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(BlockNibiru.BlockType.INFECTED_CHISELED_STONE_BRICKS.ordinal()), 8, -2, 11, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(BlockNibiru.BlockType.INFECTED_CHISELED_STONE_BRICKS.ordinal()), 9, -2, 11, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(BlockNibiru.BlockType.INFECTED_CHISELED_STONE_BRICKS.ordinal()), 10, -2, 11, box);
                this.setBlockState(world, Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 8, -2, 12, box);
                this.setBlockState(world, Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 9, -2, 12, box);
                this.setBlockState(world, Blocks.lever.getStateFromMeta(BlockLever.getMetadataForFacing(EnumFacing.getFront(this.getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 10, -2, 12, box);
                this.fillWithRandomizedBlocks(world, box, 8, -3, 8, 8, -3, 10, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 10, -3, 8, 10, -3, 10, false, rand, this.scatteredStones);
                this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 10, -2, 9, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 8, -2, 9, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 8, -2, 10, box);
                this.setBlockState(world, Blocks.redstone_wire.getDefaultState(), 10, -1, 9, box);
                this.setBlockState(world, Blocks.sticky_piston.getStateFromMeta(EnumFacing.UP.getIndex()), 9, -2, 8, box);
                this.setBlockState(world, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -2, 8, box);
                this.setBlockState(world, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -1, 8, box);
                this.setBlockState(world, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.NORTH.getHorizontalIndex())), 10, -2, 10, box);

                if (!this.hasHiddenChest)
                {
                    this.hasHiddenChest = this.generateChestContents(world, box, rand, 9, -3, 10, chest.getItems(rand), chest.getCount(rand));
                }
                return true;
            }
        }

        static class Stones extends StructureComponent.BlockSelector
        {
            private Stones() {}

            @Override
            public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
            {
                if (rand.nextFloat() < 0.01F)
                {
                    this.blockstate = Blocks.web.getDefaultState();
                }
                else if (rand.nextFloat() < 0.05F)
                {
                    this.blockstate = Blocks.air.getDefaultState();
                }
                else if (rand.nextFloat() < 0.4F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1);
                }
                else
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(2);
                }
            }
        }
    }
}
package stevekung.mods.moreplanets.planets.nibiru.world.gen.structure;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;

public abstract class StructureNibiruJungleTemplePieces extends StructureComponent
{
    protected int scatteredFeatureSizeX;
    protected int scatteredFeatureSizeY;
    protected int scatteredFeatureSizeZ;
    protected int horizontalPos = -1;

    public StructureNibiruJungleTemplePieces() {}

    protected StructureNibiruJungleTemplePieces(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
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

    public static class JungleTemple extends StructureNibiruJungleTemplePieces
    {
        private boolean placedMainChest;
        private boolean placedHiddenChest;
        private boolean hasRandomChest;
        private boolean placedTrap1;
        private boolean placedTrap2;
        private Stones scatteredStones = new Stones();

        public JungleTemple() {}

        public JungleTemple(Random rand, int x, int z)
        {
            super(rand, x, 64, z, 12, 10, 15);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbt)
        {
            super.writeStructureToNBT(nbt);
            nbt.setBoolean("placedMainChest", this.placedMainChest);
            nbt.setBoolean("placedHiddenChest", this.placedHiddenChest);
            nbt.setBoolean("placedRandomChest", this.hasRandomChest);
            nbt.setBoolean("placedTrap1", this.placedTrap1);
            nbt.setBoolean("placedTrap2", this.placedTrap2);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
        {
            super.readStructureFromNBT(nbt, manager);
            this.placedMainChest = nbt.getBoolean("placedMainChest");
            this.placedHiddenChest = nbt.getBoolean("placedHiddenChest");
            this.hasRandomChest = nbt.getBoolean("placedRandomChest");
            this.placedTrap1 = nbt.getBoolean("placedTrap1");
            this.placedTrap2 = nbt.getBoolean("placedTrap2");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (!this.offsetToAverageGroundLevel(world, box, 0))
            {
                return false;
            }
            else
            {
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
                this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 5, 5, box);
                this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, 5, 5, box);
                this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 5, 9, box);
                this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, 5, 9, box);

                for (int i = 0; i <= 14; i += 14)
                {
                    this.fillWithRandomizedBlocks(world, box, 2, 4, i, 2, 5, i, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, 4, 4, i, 4, 5, i, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, 7, 4, i, 7, 5, i, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, 9, 4, i, 9, 5, i, false, rand, this.scatteredStones);
                }

                this.fillWithRandomizedBlocks(world, box, 5, 6, 0, 6, 6, 0, false, rand, this.scatteredStones);

                for (int l = 0; l <= 11; l += 11)
                {
                    for (int j = 2; j <= 12; j += 2)
                    {
                        this.fillWithRandomizedBlocks(world, box, l, 4, j, l, 5, j, false, rand, this.scatteredStones);
                    }

                    this.fillWithRandomizedBlocks(world, box, l, 6, 5, l, 6, 5, false, rand, this.scatteredStones);
                    this.fillWithRandomizedBlocks(world, box, l, 6, 9, l, 6, 9, false, rand, this.scatteredStones);
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
                IBlockState iblockstate2 = NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST);
                IBlockState iblockstate3 = NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST);
                IBlockState iblockstate = NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
                IBlockState iblockstate1 = NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
                this.setBlockState(world, iblockstate1, 5, 9, 6, box);
                this.setBlockState(world, iblockstate1, 6, 9, 6, box);
                this.setBlockState(world, iblockstate, 5, 9, 8, box);
                this.setBlockState(world, iblockstate, 6, 9, 8, box);
                this.setBlockState(world, iblockstate1, 4, 0, 0, box);
                this.setBlockState(world, iblockstate1, 5, 0, 0, box);
                this.setBlockState(world, iblockstate1, 6, 0, 0, box);
                this.setBlockState(world, iblockstate1, 7, 0, 0, box);
                this.setBlockState(world, iblockstate1, 4, 1, 8, box);
                this.setBlockState(world, iblockstate1, 4, 2, 9, box);
                this.setBlockState(world, iblockstate1, 4, 3, 10, box);
                this.setBlockState(world, iblockstate1, 7, 1, 8, box);
                this.setBlockState(world, iblockstate1, 7, 2, 9, box);
                this.setBlockState(world, iblockstate1, 7, 3, 10, box);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 9, 4, 1, 9, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 7, 1, 9, 7, 1, 9, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 4, 1, 10, 7, 2, 10, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 5, 4, 5, 6, 4, 5, false, rand, this.scatteredStones);
                this.setBlockState(world, iblockstate2, 4, 4, 5, box);
                this.setBlockState(world, iblockstate3, 7, 4, 5, box);

                if (!this.hasRandomChest)
                {
                    if (rand.nextBoolean())
                    {
                        this.generateChest(world, box, rand, 6, 5, 5, MPLootTables.NIBIRU_JUNGLE_TEMPLE);
                        this.generateChest(world, box, rand, 5, 5, 5, MPLootTables.NIBIRU_JUNGLE_TEMPLE);
                        this.hasRandomChest = true;
                    }
                }

                this.setBlockState(world, rand.nextBoolean() ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.AIR.getDefaultState(), 6, 10, 7, box);
                this.setBlockState(world, rand.nextBoolean() ? NibiruBlocks.MULTALIC_CRYSTAL_BLOCK.getDefaultState() : Blocks.AIR.getDefaultState(), 5, 10, 7, box);

                for (int k = 0; k < 4; ++k)
                {
                    this.setBlockState(world, iblockstate, 5, 0 - k, 6 + k, box);
                    this.setBlockState(world, iblockstate, 6, 0 - k, 6 + k, box);
                    this.fillWithAir(world, box, 5, 0 - k, 7 + k, 6, 0 - k, 9 + k);
                }

                this.fillWithAir(world, box, 1, -3, 12, 10, -1, 13);
                this.fillWithAir(world, box, 1, -3, 1, 3, -1, 13);
                this.fillWithAir(world, box, 1, -3, 1, 9, -1, 5);

                for (int i1 = 1; i1 <= 13; i1 += 2)
                {
                    this.fillWithRandomizedBlocks(world, box, 1, -3, i1, 1, -2, i1, false, rand, this.scatteredStones);
                }

                for (int j1 = 2; j1 <= 12; j1 += 2)
                {
                    this.fillWithRandomizedBlocks(world, box, 1, -1, j1, 3, -1, j1, false, rand, this.scatteredStones);
                }

                this.fillWithRandomizedBlocks(world, box, 2, -2, 1, 5, -2, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 7, -2, 1, 9, -2, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 6, -3, 1, 6, -3, 1, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 6, -1, 1, 6, -1, 1, false, rand, this.scatteredStones);
                this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().withProperty(BlockTripWireHook.FACING, EnumFacing.EAST).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, box);
                this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().withProperty(BlockTripWireHook.FACING, EnumFacing.WEST).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, box);
                this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, box);
                this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 7, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 6, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 5, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 4, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 3, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 2, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 5, -3, 1, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 4, -3, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 3, -3, 1, box);

                if (!this.placedTrap1)
                {
                    this.placedTrap1 = this.createDispenser(world, box, rand, 3, -2, 1, EnumFacing.NORTH, MPLootTables.NIBIRU_JUNGLE_TEMPLE_DISPENSER);
                }

                this.setBlockState(world, NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(BlockVine.SOUTH, Boolean.valueOf(true)), 3, -2, 2, box);
                this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().withProperty(BlockTripWireHook.FACING, EnumFacing.NORTH).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, box);
                this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().withProperty(BlockTripWireHook.FACING, EnumFacing.SOUTH).withProperty(BlockTripWireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, box);
                this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, box);
                this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, box);
                this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().withProperty(BlockTripWire.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 8, -3, 6, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 9, -3, 6, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 9, -3, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 9, -3, 4, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 9, -2, 4, box);

                if (!this.placedTrap2)
                {
                    this.placedTrap2 = this.createDispenser(world, box, rand, 9, -2, 3, EnumFacing.WEST, MPLootTables.NIBIRU_JUNGLE_TEMPLE_DISPENSER);
                }

                this.setBlockState(world, NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(BlockVine.EAST, Boolean.valueOf(true)), 8, -1, 3, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(BlockVine.EAST, Boolean.valueOf(true)), 8, -2, 3, box);

                if (!this.placedMainChest)
                {
                    this.placedMainChest = this.generateChest(world, box, rand, 8, -3, 3, MPLootTables.NIBIRU_JUNGLE_TEMPLE);
                }

                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 9, -3, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 8, -3, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 4, -3, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 5, -2, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 5, -1, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 6, -3, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 7, -2, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 7, -1, 5, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 8, -3, 5, box);
                this.fillWithRandomizedBlocks(world, box, 9, -1, 1, 9, -1, 5, false, rand, this.scatteredStones);
                this.fillWithAir(world, box, 8, -3, 8, 10, -1, 10);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS.getDefaultState(), 8, -2, 11, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS.getDefaultState(), 9, -2, 11, box);
                this.setBlockState(world, NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS.getDefaultState(), 10, -2, 11, box);
                IBlockState iblockstate4 = Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.NORTH);
                this.setBlockState(world, iblockstate4, 8, -2, 12, box);
                this.setBlockState(world, iblockstate4, 9, -2, 12, box);
                this.setBlockState(world, iblockstate4, 10, -2, 12, box);
                this.fillWithRandomizedBlocks(world, box, 8, -3, 8, 8, -3, 10, false, rand, this.scatteredStones);
                this.fillWithRandomizedBlocks(world, box, 10, -3, 8, 10, -3, 10, false, rand, this.scatteredStones);
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState(), 10, -2, 9, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 8, -2, 9, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 8, -2, 10, box);
                this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 10, -1, 9, box);
                this.setBlockState(world, Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.UP), 9, -2, 8, box);
                this.setBlockState(world, Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.WEST), 10, -2, 8, box);
                this.setBlockState(world, Blocks.STICKY_PISTON.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.WEST), 10, -1, 8, box);
                this.setBlockState(world, Blocks.UNPOWERED_REPEATER.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH), 10, -2, 10, box);

                if (!this.placedHiddenChest)
                {
                    this.placedHiddenChest = this.generateChest(world, box, rand, 9, -3, 10, MPLootTables.NIBIRU_JUNGLE_TEMPLE);
                }
                return true;
            }
        }

        static class Stones extends StructureComponent.BlockSelector
        {
            private Stones() {}

            @Override
            public void selectBlocks(Random rand, int x, int y, int z, boolean wall)
            {
                if (rand.nextFloat() < 0.01F)
                {
                    this.blockstate = Blocks.WEB.getDefaultState();
                }
                else if (rand.nextFloat() < 0.05F)
                {
                    this.blockstate = Blocks.AIR.getDefaultState();
                }
                else if (rand.nextFloat() < 0.4F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_COBBLESTONE.getDefaultState();
                }
                else
                {
                    this.blockstate = NibiruBlocks.NIBIRU_VEIN_COBBLESTONE.getDefaultState();
                }
            }
        }
    }
}
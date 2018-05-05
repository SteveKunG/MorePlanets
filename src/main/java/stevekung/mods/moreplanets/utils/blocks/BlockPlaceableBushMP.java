package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenTerrashroom;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;

public class BlockPlaceableBushMP extends BlockBushMP implements IShearable, IGrowable
{
    private static final AxisAlignedBB GRASS = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    private static final AxisAlignedBB FLOWER = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.8D, 0.7D);
    private static final AxisAlignedBB PURE_HERB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
    private static final AxisAlignedBB PHILIPY = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6D, 0.75D);
    private static final AxisAlignedBB WHITE_TAIL = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.9D, 0.75D);
    private static final AxisAlignedBB VEALIUM_VINES = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    private static final AxisAlignedBB TERRASHROOM = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
    private BlockType type;

    public BlockPlaceableBushMP(String name, BlockType type)
    {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return !this.type.isFlower() ? Items.AIR : Item.getItemFromBlock(this);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        return 1 + rand.nextInt(fortune * 2 + 1);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (!this.type.isFlower())
        {
            return BlockPlaceableBushMP.GRASS;
        }
        else if (this.type == BlockType.PURE_HERB)
        {
            return BlockPlaceableBushMP.PURE_HERB;
        }
        else if (this.type == BlockType.PHILIPY)
        {
            return BlockPlaceableBushMP.PHILIPY;
        }
        else if (this.type == BlockType.WHITE_TAIL)
        {
            return BlockPlaceableBushMP.WHITE_TAIL;
        }
        else if (this.type == BlockType.VEALIUM_VINES)
        {
            return BlockPlaceableBushMP.VEALIUM_VINES;
        }
        else if (this.type == BlockType.TERRASHROOM)
        {
            return BlockPlaceableBushMP.TERRASHROOM;
        }
        return BlockPlaceableBushMP.FLOWER;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.type == BlockType.TERRASHROOM && rand.nextInt(25) == 0)
        {
            int i = 5;

            for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)))
            {
                if (world.getBlockState(blockpos).getBlock() == this)
                {
                    --i;

                    if (i <= 0)
                    {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for (int k = 0; k < 4; ++k)
            {
                if (world.isAirBlock(blockpos1) && this.canPlaceBlockAt(world, blockpos1))
                {
                    pos = blockpos1;
                }
                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }
            if (world.isAirBlock(blockpos1) && this.canPlaceBlockAt(world, blockpos1))
            {
                world.setBlockState(blockpos1, this.getDefaultState(), 2);
            }
        }
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (this.type == BlockType.PURE_HERB)
        {
            return 2;
        }
        else if (this.type == BlockType.TERRAPUFF_HERB || this.type == BlockType.VEALIUM_VINES)
        {
            return 4;
        }
        else if (this.type == BlockType.TERRASHROOM)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        if (RANDOM.nextInt(8) == 0)
        {
            if (this.type == BlockType.CHEESE_GRASS)
            {
                drops.add(new ItemStack(ChalosItems.CHEESE_SPORE_SEED));
            }
            else if (this.type == BlockType.INFECTED_GRASS || this.type == BlockType.INFECTED_FERN)
            {
                drops.add(new ItemStack(NibiruItems.INFECTED_WHEAT_SEEDS));
            }
        }
        if (this.type == BlockType.GREEN_VEIN_GRASS && RANDOM.nextInt(24) == 0)
        {
            drops.add(new ItemStack(NibiruItems.TERRABERRY));
        }
        if (this.type.isFlower())
        {
            drops.add(new ItemStack(this));
        }
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return !this.type.isFlower();
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return this.type.isFlower() ? EnumOffsetType.XZ : EnumOffsetType.XYZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return !this.type.isFlower();
    }

    @Override
    public NonNullList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        return !this.type.isFlower() ? NonNullList.withSize(1, new ItemStack(this)) : NonNullList.create();
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return this.type == BlockType.TERRAPUFF_HERB && CompatibilityManagerMP.isCTMLoaded ? layer == BlockRenderLayer.CUTOUT : this.type == BlockType.TERRASHROOM ? layer == BlockRenderLayer.TRANSLUCENT : super.canRenderInLayer(state, layer);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        Block blockUp = world.getBlockState(pos.up()).getBlock();

        if (this.type == BlockType.VEALIUM_VINES)
        {
            return blockUp == NibiruBlocks.INFECTED_JUNGLE_LEAVES || blockUp == this;
        }
        else if (this.type == BlockType.TERRASHROOM)
        {
            return super.canPlaceBlockAt(world, pos) || world.getLight(pos) < 13 && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.DOWN);
        }
        else
        {
            return super.canPlaceBlockAt(world, pos);
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        Block blockUp = world.getBlockState(pos.up()).getBlock();

        if (this.type == BlockType.TERRASHROOM)
        {
            return super.canBlockStay(world, pos, state) || world.getLight(pos) < 13 && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.DOWN);
        }
        else if (this.type == BlockType.VEALIUM_VINES)
        {
            return blockUp == NibiruBlocks.INFECTED_JUNGLE_LEAVES || blockUp == this;
        }
        return super.canBlockStay(world, pos, state);
    }

    @Override
    protected boolean validBlock(Block block)
    {
        if (this.type == BlockType.CHEESE_GRASS)
        {
            return block == ChalosBlocks.CHEESE_GRASS_BLOCK || block == ChalosBlocks.CHEESE_DIRT || block == ChalosBlocks.CHEESE_COARSE_DIRT || block == ChalosBlocks.CHEESE_FARMLAND;
        }
        else if (this.type == BlockType.GREEN_VEIN_GRASS || this.type == BlockType.TERRAPUFF_HERB)
        {
            return block == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.INFECTED_GRASS || this.type == BlockType.INFECTED_FERN || this.type == BlockType.PURE_HERB || this.type == BlockType.PYOLONIA || this.type == BlockType.PHILIPY || this.type == BlockType.WHITE_TAIL)
        {
            return block == NibiruBlocks.INFECTED_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.BATASIA_DANDELION)
        {
            return block == NibiruBlocks.INFECTED_SAND;
        }
        else if (this.type == BlockType.TERRASHROOM)
        {
            return block == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.TERRASTONE || block == NibiruBlocks.PURIFIED_GRAVEL;
        }
        return false;
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        if (this.type == BlockType.CHEESE_GRASS)
        {
            return ChalosBlocks.CHEESE_TALL_GRASS.canPlaceBlockAt(world, pos);
        }
        else if (this.type == BlockType.INFECTED_GRASS)
        {
            return NibiruBlocks.INFECTED_TALL_GRASS.canPlaceBlockAt(world, pos);
        }
        else if (this.type == BlockType.INFECTED_FERN)
        {
            return NibiruBlocks.INFECTED_LARGE_FERN.canPlaceBlockAt(world, pos);
        }
        else if (this.type == BlockType.GREEN_VEIN_GRASS)
        {
            return NibiruBlocks.GREEN_VEIN_TALL_GRASS.canPlaceBlockAt(world, pos);
        }
        return this.type == BlockType.TERRASHROOM;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (this.type == BlockType.CHEESE_GRASS)
        {
            return ChalosBlocks.CHEESE_TALL_GRASS.canPlaceBlockAt(world, pos);
        }
        else if (this.type == BlockType.INFECTED_GRASS)
        {
            return NibiruBlocks.INFECTED_TALL_GRASS.canPlaceBlockAt(world, pos);
        }
        else if (this.type == BlockType.INFECTED_FERN)
        {
            return NibiruBlocks.INFECTED_LARGE_FERN.canPlaceBlockAt(world, pos);
        }
        else if (this.type == BlockType.GREEN_VEIN_GRASS)
        {
            return NibiruBlocks.GREEN_VEIN_TALL_GRASS.canPlaceBlockAt(world, pos);
        }
        return this.type == BlockType.TERRASHROOM && rand.nextFloat() < 0.4D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (this.type == BlockType.CHEESE_GRASS)
        {
            if (ChalosBlocks.CHEESE_TALL_GRASS.canPlaceBlockAt(world, pos))
            {
                ChalosBlocks.CHEESE_TALL_GRASS.placeAt(world, pos, ChalosBlocks.CHEESE_TALL_GRASS, 2);
            }
        }
        else if (this.type == BlockType.INFECTED_GRASS)
        {
            if (NibiruBlocks.INFECTED_TALL_GRASS.canPlaceBlockAt(world, pos))
            {
                NibiruBlocks.INFECTED_TALL_GRASS.placeAt(world, pos, NibiruBlocks.INFECTED_TALL_GRASS, 2);
            }
        }
        else if (this.type == BlockType.INFECTED_FERN)
        {
            if (NibiruBlocks.INFECTED_LARGE_FERN.canPlaceBlockAt(world, pos))
            {
                NibiruBlocks.INFECTED_LARGE_FERN.placeAt(world, pos, NibiruBlocks.INFECTED_LARGE_FERN, 2);
            }
        }
        else if (this.type == BlockType.GREEN_VEIN_GRASS)
        {
            if (NibiruBlocks.GREEN_VEIN_TALL_GRASS.canPlaceBlockAt(world, pos))
            {
                NibiruBlocks.GREEN_VEIN_TALL_GRASS.placeAt(world, pos, NibiruBlocks.GREEN_VEIN_TALL_GRASS, 2);
            }
        }
        else if (this.type == BlockType.TERRASHROOM)
        {
            this.generateBigMushroom(world, pos, state, rand);
        }
    }

    private boolean generateBigMushroom(World world, BlockPos pos, IBlockState state, Random rand)
    {
        world.setBlockToAir(pos);
        WorldGenTerrashroom worldGen = new WorldGenTerrashroom();

        if (worldGen.generate(world, rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }

    public static enum BlockType
    {
        CHEESE_GRASS(false),
        INFECTED_GRASS(false),
        INFECTED_FERN(false),
        GREEN_VEIN_GRASS(false),
        PURE_HERB(true),
        TERRAPUFF_HERB(true),
        BATASIA_DANDELION(true),
        PYOLONIA(true),
        PHILIPY(true),
        WHITE_TAIL(true),
        VEALIUM_VINES(true),
        TERRASHROOM(true);

        private boolean isFlower;

        BlockType(boolean isFlower)
        {
            this.isFlower = isFlower;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        public boolean isFlower()
        {
            return this.isFlower;
        }
    }
}
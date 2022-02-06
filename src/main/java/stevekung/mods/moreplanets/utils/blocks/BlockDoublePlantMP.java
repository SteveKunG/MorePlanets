package stevekung.mods.moreplanets.utils.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;

public class BlockDoublePlantMP extends BlockBushMP implements IGrowable, IShearable
{
    private BlockType type;

    public BlockDoublePlantMP(String name, BlockType type)
    {
        super(Material.VINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER));
        this.setTranslationKey(name);
        this.type = type;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return super.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() != this)
        {
            return true;
        }
        else
        {
            return this.type.isGrass();
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() != this)
        {
            return super.canBlockStay(world, pos, state);
        }
        if (state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER)
        {
            return world.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
            IBlockState otherState = world.getBlockState(pos.up());
            return otherState.getBlock() == this && super.canBlockStay(world, pos, otherState);
        }
    }

    @Override
    protected boolean validBlock(Block block)
    {
        if (this.type == BlockType.CHEESE_TALL_GRASS)
        {
            return block == MPBlocks.CHEESE_GRASS_BLOCK || block == MPBlocks.CHEESE_DIRT || block == MPBlocks.CHEESE_COARSE_DIRT || block == MPBlocks.CHEESE_FARMLAND;
        }
        else if (this.type == BlockType.GREEN_VEIN_TALL_GRASS)
        {
            return block == MPBlocks.GREEN_VEIN_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.INFECTED_ORANGE_ROSE_BUSH || this.type == BlockType.INFECTED_TALL_GRASS || this.type == BlockType.INFECTED_LARGE_FERN)
        {
            return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.LARGE_WHEAT || this.type == BlockType.FRONOS_TALL_GRASS)
        {
            return block == MPBlocks.FRONOS_GRASS_BLOCK || block == MPBlocks.FRONOS_DIRT || block == MPBlocks.FRONOS_COARSE_DIRT || block == MPBlocks.FRONOS_FARMLAND;
        }
        return super.validBlock(block);
    }

    @Override
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(world, pos, state))
        {
            boolean flag = state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = flag ? this : world.getBlockState(blockpos).getBlock();
            Block block1 = flag ? world.getBlockState(blockpos1).getBlock() : this;

            if (!flag && !this.type.isGrass())
            {
                this.dropBlockAsItem(world, pos, state, 0);
            }
            if (block == this)
            {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }
            if (block1 == this)
            {
                world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER)
        {
            return Items.AIR;
        }
        else
        {
            if (this.type == BlockType.CHEESE_TALL_GRASS)
            {
                return rand.nextInt(8) == 0 ? MPItems.CHEESE_SPORE_SEED : Items.AIR;
            }
            else if (this.type == BlockType.GREEN_VEIN_TALL_GRASS)
            {
                return rand.nextInt(8) == 0 ? MPItems.TERRABERRY : Items.AIR;
            }
            else if (this.type == BlockType.INFECTED_TALL_GRASS)
            {
                return rand.nextInt(8) == 0 ? MPItems.INFECTED_WHEAT_SEEDS : Items.AIR;
            }
            else if (this.type == BlockType.FRONOS_TALL_GRASS)
            {
                return rand.nextInt(8) == 0 ? Items.WHEAT_SEEDS : Items.AIR;
            }
            else
            {
                return this.type.isGrass() || this.type == BlockType.LARGE_WHEAT ? Items.AIR : Item.getItemFromBlock(this);
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        world.setBlockState(pos.up(), this.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), 2);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        boolean isShears = !player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == Items.SHEARS;

        if (state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER)
        {
            if (world.getBlockState(pos.down()).getBlock() == this)
            {
                if (player.capabilities.isCreativeMode)
                {
                    world.setBlockToAir(pos.down());
                }
                else
                {
                    IBlockState iblockstate = world.getBlockState(pos.down());

                    if (!this.type.isGrass())
                    {
                        world.destroyBlock(pos.down(), true);
                    }
                    else if (world.isRemote)
                    {
                        world.setBlockToAir(pos.down());
                    }
                    else if (isShears)
                    {
                        this.onHarvest(world, pos, iblockstate, player);
                        world.setBlockToAir(pos.down());
                    }
                    else
                    {
                        world.destroyBlock(pos.down(), true);
                    }
                }
            }
        }
        else if (world.getBlockState(pos.up()).getBlock() == this)
        {
            world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }

        if (!player.capabilities.isCreativeMode && this.type == BlockType.LARGE_WHEAT)
        {
            if (isShears)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(this));
            }
            else
            {
                Block.spawnAsEntity(world, pos, new ItemStack(Items.WHEAT, 4 + world.rand.nextInt(5)));
            }
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return !this.type.isGrass();
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        Block.spawnAsEntity(world, pos, new ItemStack(this));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return meta == 1 ? this.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockDoublePlant.HALF);
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.LOWER && this.type.isGrass();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        List<ItemStack> ret = new ArrayList<>();

        if (this.type == BlockType.CHEESE_TALL_GRASS)
        {
            ret.add(new ItemStack(MPBlocks.CHEESE_GRASS, 2));
        }
        if (this.type == BlockType.INFECTED_TALL_GRASS)
        {
            ret.add(new ItemStack(MPBlocks.INFECTED_GRASS, 2));
        }
        if (this.type == BlockType.INFECTED_LARGE_FERN)
        {
            ret.add(new ItemStack(MPBlocks.INFECTED_FERN, 2));
        }
        if (this.type == BlockType.GREEN_VEIN_TALL_GRASS)
        {
            ret.add(new ItemStack(MPBlocks.GREEN_VEIN_GRASS, 2));
        }
        if (this.type == BlockType.LARGE_WHEAT)
        {
            ret.add(new ItemStack(MPBlocks.LARGE_WHEAT));
        }
        if (this.type == BlockType.FRONOS_TALL_GRASS)
        {
            ret.add(new ItemStack(MPBlocks.FRONOS_TALL_GRASS));
        }
        return ret;
    }

    public void placeAt(World world, BlockPos lowerPos, Block block, int flags)
    {
        world.setBlockState(lowerPos, block.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER), flags);
        world.setBlockState(lowerPos.up(), block.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), flags);
    }

    private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (this.type.isGrass())
        {
            return false;
        }
        else
        {
            player.addStat(StatList.getBlockStats(this));
            return true;
        }
    }

    public enum BlockType
    {
        CHEESE_TALL_GRASS(true),
        INFECTED_ORANGE_ROSE_BUSH(false),
        INFECTED_TALL_GRASS(true),
        INFECTED_LARGE_FERN(true),
        GREEN_VEIN_TALL_GRASS(true),
        LARGE_WHEAT(false),
        FRONOS_TALL_GRASS(true);

        private boolean isGrass;

        BlockType(boolean isGrass)
        {
            this.isGrass = isGrass;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public boolean isGrass()
        {
            return this.isGrass;
        }
    }
}
package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;

public class BlockGrassMP extends BlockBushMP implements IShearable, IGrowable
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    private BlockType type;

    public BlockGrassMP(String name, BlockType type)
    {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockGrassMP.AABB;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        if (RANDOM.nextInt(8) != 0)
        {
            if (this.type == BlockType.CHEESE_GRASS)
            {
                drops.add(new ItemStack(ChalosItems.CHEESE_SPORE_SEED));
            }
            else if (this.type != BlockType.GREEN_VEIN_GRASS)
            {
                drops.add(new ItemStack(NibiruItems.INFECTED_WHEAT_SEEDS));
            }
        }
        if (this.type == BlockType.GREEN_VEIN_GRASS && RANDOM.nextInt(24) != 0)
        {
            drops.add(new ItemStack(NibiruItems.TERRABERRY));
        }
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XYZ;
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Arrays.asList(new ItemStack(this));
    }

    @Override
    protected boolean validBlock(Block block)
    {
        if (this.type == BlockType.CHEESE_GRASS)
        {
            return block == ChalosBlocks.CHEESE_GRASS_BLOCK || block == ChalosBlocks.CHEESE_DIRT || block == ChalosBlocks.CHEESE_COARSE_DIRT || block == ChalosBlocks.CHEESE_FARMLAND;
        }
        else if (this.type == BlockType.GREEN_VEIN_GRASS)
        {
            return block == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.INFECTED_GRASS || this.type == BlockType.INFECTED_FERN)
        {
            return block == NibiruBlocks.INFECTED_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
        }
        return super.validBlock(block);
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
        return false;
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
        return false;
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
    }

    public static enum BlockType
    {
        CHEESE_GRASS,
        INFECTED_GRASS,
        INFECTED_FERN,
        GREEN_VEIN_GRASS;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }
    }
}
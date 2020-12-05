package stevekung.mods.moreplanets.utils.blocks;

import java.util.Locale;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDropableLitOre extends BlockBasicMP implements IDetectableResource
{
    public static final PropertyBool LIT = PropertyBool.create("lit");
    private BlockType type;

    public BlockDropableLitOre(String name, BlockType type)
    {
        super(Material.ROCK);
        this.type = type;
        this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LIT, false));
        this.setUnlocalizedName(name);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.getValue(LIT) ? (int)(15.0F * 0.625F) : 0;
    }

    @Override
    public int tickRate(World world)
    {
        return 30;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this.type == BlockType.REDSTONE_ORE)
        {
            return Items.REDSTONE;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (this.type == BlockType.REDSTONE_ORE)
        {
            return 4 + rand.nextInt(2) + rand.nextInt(fortune + 1);
        }
        return this.quantityDropped(rand);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        return this.type == BlockType.REDSTONE_ORE ? this.quantityDropped(rand) + rand.nextInt(fortune + 1) : super.quantityDroppedWithBonus(fortune, rand);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        if (this.type == BlockType.REDSTONE_ORE)
        {
            return 1 + RANDOM.nextInt(5);
        }
        return MathHelper.getInt(RANDOM, 3, 7);
    }

    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
    {
        if (this.type == BlockType.REDSTONE_ORE)
        {
            this.activate(world, pos);
        }
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity)
    {
        if (this.type == BlockType.REDSTONE_ORE)
        {
            this.activate(world, pos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (this.type == BlockType.REDSTONE_ORE)
        {
            this.activate(world, pos);
        }
        return false;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (state.getValue(LIT))
        {
            world.setBlockState(pos, state.withProperty(LIT, false));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (state.getValue(LIT))
        {
            this.spawnParticles(world, pos);
        }
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        if (state.getValue(LIT))
        {
            return new ItemStack(this.getDefaultState().getBlock());
        }
        return super.getSilkTouchDrop(state);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.ORE;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LIT, meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(LIT) ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LIT);
    }

    private void activate(World world, BlockPos pos)
    {
        this.spawnParticles(world, pos);
        world.setBlockState(pos, this.getDefaultState().withProperty(LIT, true));
    }

    private void spawnParticles(World world, BlockPos pos)
    {
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = pos.getX() + world.rand.nextFloat();
            double d2 = pos.getY() + world.rand.nextFloat();
            double d3 = pos.getZ() + world.rand.nextFloat();

            if (i == 0 && !world.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = pos.getY() + d0 + 1.0D;
            }
            if (i == 1 && !world.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = pos.getY() - d0;
            }
            if (i == 2 && !world.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = pos.getZ() + d0 + 1.0D;
            }
            if (i == 3 && !world.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = pos.getZ() - d0;
            }
            if (i == 4 && !world.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = pos.getX() + d0 + 1.0D;
            }
            if (i == 5 && !world.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = pos.getX() - d0;
            }
            if (d1 < pos.getX() || d1 > pos.getX() + 1 || d2 < 0.0D || d2 > pos.getY() + 1 || d3 < pos.getZ() || d3 > pos.getZ() + 1)
            {
                world.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public enum BlockType
    {
        REDSTONE_ORE;

        @Override
        public String toString()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
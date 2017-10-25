package stevekung.mods.moreplanets.module.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.module.moons.koentus.entity.EntityFallingKoentusMeteor;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.blocks.BlockFallingMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockFallenKoentusMeteor extends BlockFallingMP
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.186D, 0.125D, 0.186D, 0.814D, 0.875D, 0.814D);

    public BlockFallenKoentusMeteor(String name)
    {
        super(name);
        this.setHardness(50.0F);
        this.setResistance(25.0F);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BlockFallenKoentusMeteor.AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);//KoentusItems.koentus_item;TODO
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 3;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (!world.getBlockState(pos.up()).isSideSolid(world, pos.up(), EnumFacing.UP))
        {
            for (int i = 0; i < 4; i++)
            {
                double d0 = pos.getX() + rand.nextFloat();
                double d2 = pos.getZ() + rand.nextFloat();
                double d3 = 0.0D;
                double d5 = 0.0D;
                d3 = (rand.nextFloat() - 0.5D) * 0.5D;
                d5 = (rand.nextFloat() - 0.5D) * 0.5D;
                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.KOENTUS_METEOR_SMOKE, d0, pos.getY() + 2, d2, d3, -2.0D, d5);
            }
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);

        if (this.getItemDropped(state, world.rand, fortune) != Item.getItemFromBlock(this))
        {
            this.dropXpOnBlockBreak(world, pos, MathHelper.getInt(world.rand, 3, 5));
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            this.checkFallable(world, pos);
        }
    }

    private void checkFallable(World world, BlockPos pos)
    {
        if ((world.isAirBlock(pos.down()) || BlockFalling.canFallThrough(world.getBlockState(pos.down()))) && pos.getY() >= 0)
        {
            if (!fallInstantly && world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!world.isRemote)
                {
                    EntityFallingKoentusMeteor meteor = new EntityFallingKoentusMeteor(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
                    world.spawnEntity(meteor);
                }
            }
            else
            {
                IBlockState state = world.getBlockState(pos);
                world.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (world.isAirBlock(blockpos) || BlockFalling.canFallThrough(world.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down()) {}

                if (blockpos.getY() > 0)
                {
                    world.setBlockState(blockpos.up(), state);
                }
            }
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }
}
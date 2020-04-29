package stevekung.mods.moreplanets.util.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.blocks.BlockGrating;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.CachedEnumUtil;

public abstract class BlockFluidLavaBaseMP extends BlockFluidBaseMP
{
    public BlockFluidLavaBaseMP(Fluid fluid)
    {
        super(fluid, Material.LAVA);
        this.setQuantaPerBlock(4);
        this.setHardness(100.0F);
        this.setResistance(100.0F);
        this.setLightLevel(1.0F);
        this.setTickRandomly(true);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block != this)
        {
            return block.getLightValue(state, world, pos);
        }
        return super.getLightValue(state, world, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);
        this.checkForMixing(world, pos, state);

        if (this.canFlowingInto(world, pos.down(), world.getBlockState(pos.down())))
        {
            if (this.blockMaterial == Material.LAVA && world.getBlockState(pos.down()).getMaterial() == Material.WATER)
            {
                world.setBlockState(pos.down(), this.getBlockFromWaterTo());
                this.triggerMixEffects(world, pos.down());
                return;
            }
        }

        if (world.getGameRules().getBoolean("doFireTick") && this.enableFire())
        {
            int i = rand.nextInt(3);

            if (i > 0)
            {
                BlockPos blockpos1 = pos;

                for (int j = 0; j < i; ++j)
                {
                    blockpos1 = blockpos1.add(rand.nextInt(3) - 1, 1, rand.nextInt(3) - 1);

                    if (world.getBlockState(blockpos1).getMaterial() == Material.AIR)
                    {
                        if (this.isSurroundingBlockFlammable(world, blockpos1))
                        {
                            world.setBlockState(blockpos1, this.getFireBlock());
                            return;
                        }
                    }
                    else if (world.getBlockState(blockpos1).getMaterial().blocksMovement())
                    {
                        return;
                    }
                }
            }
            else
            {
                for (int k = 0; k < 3; ++k)
                {
                    BlockPos blockpos2 = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);

                    if (world.isAirBlock(blockpos2.up()) && this.getCanBlockBurn(world, blockpos2))
                    {
                        world.setBlockState(blockpos2.up(), this.getFireBlock());
                    }
                }
            }
        }
    }

    @Override
    public boolean isBurning(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos)
    {
        super.neighborChanged(state, world, pos, neighborBlock, fromPos);
        this.checkForMixing(world, pos, state);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (!this.checkForMixing(world, pos, state))
        {
            world.scheduleUpdate(pos, this, this.tickRate(world));
        }
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos)
    {
        if (world.isAirBlock(pos))
        {
            return true;
        }

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block == this)
        {
            return false;
        }

        if (this.displacements.containsKey(block))
        {
            if (this.displacements.get(block))
            {
                if (!(block instanceof BlockGrating))
                {
                    this.triggerMixEffects(world, pos);
                    block.dropBlockAsItem(world, pos, state, 0);
                }
                return true;
            }
            return false;
        }

        Material material = state.getMaterial();

        if (material.blocksMovement() || material == Material.PORTAL)
        {
            return false;
        }

        int density = BlockFluidBase.getDensity(world, pos);

        if (density == Integer.MAX_VALUE)
        {
            if (!(block instanceof BlockGrating))
            {
                this.triggerMixEffects(world, pos);
                block.dropBlockAsItem(world, pos, state, 0);
            }
            return true;
        }

        if (this.density > density)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isSurroundingBlockFlammable(World world, BlockPos pos)
    {
        EnumFacing[] aenumfacing = CachedEnumUtil.valuesEnumFacingCached();
        int i = aenumfacing.length;

        for (int j = 0; j < i; ++j)
        {
            EnumFacing enumfacing = aenumfacing[j];

            if (this.getCanBlockBurn(world, pos.offset(enumfacing)))
            {
                return true;
            }
        }
        return false;
    }

    private boolean getCanBlockBurn(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getMaterial().getCanBurn();
    }

    protected boolean checkForMixing(World world, BlockPos pos, IBlockState state)
    {
        boolean flag = false;
        EnumFacing[] aenumfacing = CachedEnumUtil.valuesEnumFacingCached();
        int i = aenumfacing.length;

        for (int j = 0; j < i; ++j)
        {
            EnumFacing enumfacing = aenumfacing[j];

            if (enumfacing != EnumFacing.DOWN && world.getBlockState(pos.offset(enumfacing)).getMaterial() == Material.WATER)
            {
                flag = true;
                break;
            }
        }

        if (flag)
        {
            Integer integer = state.getValue(LEVEL);

            if (integer.intValue() == 0)
            {
                world.setBlockState(pos, this.getObsidianBlock());
                this.triggerMixEffects(world, pos);
                return true;
            }
            if (integer.intValue() <= 4)
            {
                world.setBlockState(pos, this.getCobblestoneBlock());
                this.triggerMixEffects(world, pos);
                return true;
            }
        }
        return false;
    }

    private boolean canFlowingInto(World world, BlockPos pos, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != this.blockMaterial && material != Material.LAVA && !this.isBlocked(world, pos, state);
    }

    private boolean isBlocked(World world, BlockPos pos, IBlockState state)
    {
        if (this.displacements.containsKey(state.getBlock()))
        {
            if (this.displacements.get(state.getBlock()))
            {
                return true;
            }
        }
        return false;
    }

    protected abstract IBlockState getBlockFromWaterTo();
    protected abstract IBlockState getObsidianBlock();
    protected abstract IBlockState getCobblestoneBlock();
    protected abstract IBlockState getFireBlock();
    protected abstract boolean enableFire();
}
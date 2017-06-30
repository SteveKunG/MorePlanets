package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.blocks.IFireBlock;

public class BlockElectricalFire extends BlockFire implements IFireBlock
{
    protected BlockElectricalFire(String name)
    {
        super();
        this.setLightLevel(1.0F);
        this.setUnlocalizedName(name);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isBurning(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public int tickRate(World world)
    {
        return 60;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (world.getGameRules().getBoolean("doFireTick"))
        {
            if (!this.canPlaceBlockAt(world, pos))
            {
                world.setBlockToAir(pos);
            }

            Block block = world.getBlockState(pos.down()).getBlock();
            boolean flag = block.isFireSource(world, pos.down(), EnumFacing.UP);

            if (!flag && world.isRaining() && this.canDie(world, pos))
            {
                world.setBlockToAir(pos);
            }
            else
            {
                int i = state.getValue(AGE).intValue();

                if (i < 15)
                {
                    state = state.withProperty(AGE, Integer.valueOf(i + rand.nextInt(3) / 2));
                    world.setBlockState(pos, state, 4);
                }

                world.scheduleUpdate(pos, this, this.tickRate(world) + rand.nextInt(10));

                if (!flag)
                {
                    if (!this.canNeighborCatchFire(world, pos))
                    {
                        if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) || i > 3)
                        {
                            world.setBlockToAir(pos);
                        }
                        return;
                    }

                    if (!this.canCatchFire(world, pos.down(), EnumFacing.UP) && i == 15 && rand.nextInt(4) == 0)
                    {
                        world.setBlockToAir(pos);
                        return;
                    }
                }

                boolean flag1 = world.isBlockinHighHumidity(pos);
                int j = 0;

                if (flag1)
                {
                    j = -50;
                }

                this.tryCatchFire(world, pos.east(), 300 + j, rand, i, EnumFacing.WEST);
                this.tryCatchFire(world, pos.west(), 300 + j, rand, i, EnumFacing.EAST);
                this.tryCatchFire(world, pos.down(), 250 + j, rand, i, EnumFacing.UP);
                this.tryCatchFire(world, pos.up(), 250 + j, rand, i, EnumFacing.DOWN);
                this.tryCatchFire(world, pos.north(), 300 + j, rand, i, EnumFacing.SOUTH);
                this.tryCatchFire(world, pos.south(), 300 + j, rand, i, EnumFacing.NORTH);

                for (int k = -1; k <= 1; ++k)
                {
                    for (int l = -1; l <= 1; ++l)
                    {
                        for (int i1 = -1; i1 <= 4; ++i1)
                        {
                            if (k != 0 || i1 != 0 || l != 0)
                            {
                                int j1 = 100;

                                if (i1 > 1)
                                {
                                    j1 += (i1 - 1) * 100;
                                }

                                BlockPos blockpos = pos.add(k, i1, l);
                                int k1 = this.getNeighborEncouragement(world, blockpos);

                                if (k1 > 0)
                                {
                                    int l1 = (k1 + 40 + world.getDifficulty().getDifficultyId() * 7) / (i + 30);

                                    if (flag1)
                                    {
                                        l1 /= 2;
                                    }

                                    if (l1 > 0 && rand.nextInt(j1) <= l1 && (!world.isRaining() || !this.canDie(world, blockpos)))
                                    {
                                        int i2 = i + rand.nextInt(5) / 4;

                                        if (i2 > 15)
                                        {
                                            i2 = 15;
                                        }
                                        world.setBlockState(blockpos, state.withProperty(AGE, Integer.valueOf(i2)), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void tryCatchFire(World world, BlockPos pos, int chance, Random random, int age, EnumFacing face)
    {
        int i = world.getBlockState(pos).getBlock().getFlammability(world, pos, face);

        if (random.nextInt(chance) < i)
        {
            IBlockState iblockstate = world.getBlockState(pos);

            if (random.nextInt(age + 10) < 5 && !world.isRainingAt(pos))
            {
                int j = age + random.nextInt(5) / 4;

                if (j > 15)
                {
                    j = 15;
                }
                world.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(j)), 3);
            }
            else
            {
                world.setBlockToAir(pos);
            }

            if (iblockstate.getBlock() == Blocks.TNT)
            {
                Blocks.TNT.onBlockDestroyedByPlayer(world, pos, iblockstate.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
            }
        }
    }

    private boolean canNeighborCatchFire(World world, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.VALUES)
        {
            if (this.canCatchFire(world, pos.offset(enumfacing), enumfacing.getOpposite()))
            {
                return true;
            }
        }
        return false;
    }

    private int getNeighborEncouragement(World world, BlockPos pos)
    {
        if (!world.isAirBlock(pos))
        {
            return 0;
        }
        else
        {
            int i = 0;

            for (EnumFacing enumfacing : EnumFacing.VALUES)
            {
                i = Math.max(world.getBlockState(pos.offset(enumfacing)).getBlock().getFlammability(world, pos.offset(enumfacing), enumfacing.getOpposite()), i);
            }
            return i;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand)
    {
        if (rand.nextInt(24) == 0)
        {
            world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }

        if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && !NibiruBlocks.ELECTRICAL_FIRE.canCatchFire(world, pos.down(), EnumFacing.UP))
        {
            if (NibiruBlocks.ELECTRICAL_FIRE.canCatchFire(world, pos.west(), EnumFacing.EAST))
            {
                for (int j = 0; j < 2; ++j)
                {
                    double d3 = pos.getX() + rand.nextDouble() * 0.10000000149011612D;
                    double d8 = pos.getY() + rand.nextDouble();
                    double d13 = pos.getZ() + rand.nextDouble();
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d3, d8, d13, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
            if (NibiruBlocks.ELECTRICAL_FIRE.canCatchFire(world, pos.east(), EnumFacing.WEST))
            {
                for (int k = 0; k < 2; ++k)
                {
                    double d4 = pos.getX() + 1 - rand.nextDouble() * 0.10000000149011612D;
                    double d9 = pos.getY() + rand.nextDouble();
                    double d14 = pos.getZ() + rand.nextDouble();
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d4, d9, d14, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
            if (NibiruBlocks.ELECTRICAL_FIRE.canCatchFire(world, pos.north(), EnumFacing.SOUTH))
            {
                for (int l = 0; l < 2; ++l)
                {
                    double d5 = pos.getX() + rand.nextDouble();
                    double d10 = pos.getY() + rand.nextDouble();
                    double d15 = pos.getZ() + rand.nextDouble() * 0.10000000149011612D;
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d5, d10, d15, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
            if (NibiruBlocks.ELECTRICAL_FIRE.canCatchFire(world, pos.south(), EnumFacing.NORTH))
            {
                for (int i1 = 0; i1 < 2; ++i1)
                {
                    double d6 = pos.getX() + rand.nextDouble();
                    double d11 = pos.getY() + rand.nextDouble();
                    double d16 = pos.getZ() + 1 - rand.nextDouble() * 0.10000000149011612D;
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d6, d11, d16, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
            if (NibiruBlocks.ELECTRICAL_FIRE.canCatchFire(world, pos.up(), EnumFacing.DOWN))
            {
                for (int j1 = 0; j1 < 2; ++j1)
                {
                    double d7 = pos.getX() + rand.nextDouble();
                    double d12 = pos.getY() + 1 - rand.nextDouble() * 0.10000000149011612D;
                    double d17 = pos.getZ() + rand.nextDouble();
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
        }
        else
        {
            for (int i = 0; i < 3; ++i)
            {
                double d0 = pos.getX() + rand.nextDouble();
                double d1 = pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
                double d2 = pos.getZ() + rand.nextDouble();
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && !Blocks.FIRE.canCatchFire(world, pos.down(), EnumFacing.UP))
        {
            return state.withProperty(NORTH, this.canCatchFire(world, pos.north(), EnumFacing.SOUTH)).withProperty(EAST,  this.canCatchFire(world, pos.east(), EnumFacing.WEST)).withProperty(SOUTH, this.canCatchFire(world, pos.south(), EnumFacing.NORTH)).withProperty(WEST,  this.canCatchFire(world, pos.west(), EnumFacing.EAST)).withProperty(UPPER, this.canCatchFire(world, pos.up(), EnumFacing.DOWN));
        }
        return this.getDefaultState();
    }
}
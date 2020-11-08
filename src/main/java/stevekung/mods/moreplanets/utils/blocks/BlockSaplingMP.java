package stevekung.mods.moreplanets.utils.blocks;

import java.util.Locale;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.chalos.world.gen.feature.WorldGenCheeseSporeTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.*;

public class BlockSaplingMP extends BlockBushMP implements IGrowable
{
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    public static final PropertyBool NATURAL_GEN = PropertyBool.create("natural_gen");
    private BlockType type;
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    private static final AxisAlignedBB CHEESE_SPORE = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    public BlockSaplingMP(String name, BlockType type)
    {
        this.type = type;
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0).withProperty(NATURAL_GEN, false));
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return this.type == BlockType.CHEESE_SPORE_FLOWER ? BlockSaplingMP.CHEESE_SPORE : BlockSaplingMP.AABB;
    }

    @Override
    protected boolean validBlock(Block block)
    {
        if (this.type == BlockType.ALIEN_BERRY_OAK_SAPLING)
        {
            return block == MPBlocks.GREEN_VEIN_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.INFECTED_OAK_SAPLING || this.type == BlockType.INFECTED_JUNGLE_SAPLING || this.type == BlockType.INFECTED_SPRUCE_SAPLING)
        {
            return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
        }
        else if (this.type == BlockType.CHEESE_SPORE_FLOWER)
        {
            return block == MPBlocks.CHEESE_GRASS_BLOCK || block == MPBlocks.CHEESE_DIRT || block == MPBlocks.CHEESE_COARSE_DIRT || block == MPBlocks.CHEESE_FARMLAND;
        }
        return super.validBlock(block);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, pos, state, rand);

            if (!world.isAreaLoaded(pos, 1))
            {
                return;
            }
            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(world, rand, pos, state);
            }
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.SAPLING;
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        if (state.getValue(NATURAL_GEN))
        {
            world.setBlockState(pos, state.withProperty(NATURAL_GEN, false), 4);
        }
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return world.rand.nextFloat() < (this.type == BlockType.CHEESE_SPORE_FLOWER ? 0.25D : 0.45D);
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        if (state.getValue(STAGE) == 0)
        {
            world.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            WorldGenerator worldGen = null;
            int i = 0;
            int j = 0;
            boolean flag = false;

            switch (this.type)
            {
            case INFECTED_OAK_SAPLING:
                worldGen = rand.nextInt(10) == 0 ? new WorldGenInfectedBigTree(true, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState()) : new WorldGenInfectedTrees(true, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
                break;
            case INFECTED_SPRUCE_SAPLING:
                label68:
                    for (i = 0; i >= -1; --i)
                    {
                        for (j = 0; j >= -1; --j)
                        {
                            if (this.isTwoByTwoOfType(world, pos, i, j, BlockType.INFECTED_SPRUCE_SAPLING))
                            {
                                worldGen = new WorldGenInfectedMegaPineTree(true, rand.nextBoolean());
                                flag = true;
                                break label68;
                            }
                        }
                    }
            if (!flag)
            {
                i = 0;
                j = 0;
                worldGen = new WorldGenInfectedDeadSpruce(true);
            }
            break;
            case INFECTED_JUNGLE_SAPLING:
                label269:
                    for (i = 0; i >= -1; --i)
                    {
                        for (j = 0; j >= -1; --j)
                        {
                            if (this.isTwoByTwoOfType(world, pos, i, j, BlockType.INFECTED_JUNGLE_SAPLING))
                            {
                                worldGen = new WorldGenInfectedMegaJungleTree();
                                flag = true;
                                break label269;
                            }
                        }
                    }
            if (!flag)
            {
                j = 0;
                i = 0;
                worldGen = new WorldGenInfectedJungleTrees(true, 4 + rand.nextInt(7), false);
            }
            break;
            case ALIEN_BERRY_OAK_SAPLING:
                worldGen = rand.nextInt(10) == 0 ? new WorldGenAlienBerryBigTree() : new WorldGenAlienBerryTree();
                break;
            case CHEESE_SPORE_FLOWER:
                if (!state.getValue(NATURAL_GEN))
                {
                    worldGen = new WorldGenCheeseSporeTree(6 + rand.nextInt(4), true);
                }
                break;
            }

            if (worldGen != null)
            {
                IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

                if (flag)
                {
                    world.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
                    world.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
                    world.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
                    world.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
                }
                else
                {
                    world.setBlockState(pos, iblockstate2, 4);
                }

                if (!worldGen.generate(world, rand, pos.add(i, 0, j)))
                {
                    if (flag)
                    {
                        world.setBlockState(pos.add(i, 0, j), state, 4);
                        world.setBlockState(pos.add(i + 1, 0, j), state, 4);
                        world.setBlockState(pos.add(i, 0, j + 1), state, 4);
                        world.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
                    }
                    else
                    {
                        world.setBlockState(pos, state, 4);
                    }
                }
            }
        }
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return this.type == BlockType.CHEESE_SPORE_FLOWER ? EnumOffsetType.XYZ : super.getOffsetType();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STAGE, meta & 1).withProperty(NATURAL_GEN, (meta & 4) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(STAGE);

        if (!state.getValue(NATURAL_GEN))
        {
            i |= 4;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STAGE, NATURAL_GEN);
    }

    private boolean isTwoByTwoOfType(World world, BlockPos pos, int x, int z, BlockType type)
    {
        return this.isTypeAt(world, pos.add(x, 0, z), type) && this.isTypeAt(world, pos.add(x + 1, 0, z), type) && this.isTypeAt(world, pos.add(x, 0, z + 1), type) && this.isTypeAt(world, pos.add(x + 1, 0, z + 1), type);
    }

    private boolean isTypeAt(World world, BlockPos pos, BlockType type)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        return iblockstate.getBlock() == this && this.type == type;
    }

    public static enum BlockType
    {
        CHEESE_SPORE_FLOWER,
        INFECTED_OAK_SAPLING,
        INFECTED_SPRUCE_SAPLING,
        INFECTED_JUNGLE_SAPLING,
        ALIEN_BERRY_OAK_SAPLING;

        @Override
        public String toString()
        {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
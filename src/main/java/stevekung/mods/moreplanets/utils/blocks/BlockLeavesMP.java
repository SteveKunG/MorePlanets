package stevekung.mods.moreplanets.utils.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockLeavesMP extends BlockBaseMP implements IShearable
{
    private int[] surroundings;
    private BlockType type;

    public BlockLeavesMP(String name, BlockType type)
    {
        super(Material.LEAVES);
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName(name);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (world.isAreaLoaded(new BlockPos(x - 2, y - 2, z - 2), new BlockPos(x + 2, y + 2, z + 2)))
        {
            for (int j1 = -1; j1 <= 1; ++j1)
            {
                for (int k1 = -1; k1 <= 1; ++k1)
                {
                    for (int l1 = -1; l1 <= 1; ++l1)
                    {
                        BlockPos blockpos = pos.add(j1, k1, l1);
                        IBlockState iblockstate = world.getBlockState(blockpos);

                        if (iblockstate.getBlock().isLeaves(iblockstate, world, blockpos))
                        {
                            iblockstate.getBlock().beginLeavesDecay(iblockstate, world, blockpos);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (state.getValue(BlockStateProperty.CHECK_DECAY) && state.getValue(BlockStateProperty.DECAYABLE))
            {
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                if (this.surroundings == null)
                {
                    this.surroundings = new int[32768];
                }
                if (!world.isAreaLoaded(pos, 1))
                {
                    return;
                }
                if (world.isAreaLoaded(pos, 6))
                {
                    BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();

                    for (int i2 = -4; i2 <= 4; ++i2)
                    {
                        for (int j2 = -4; j2 <= 4; ++j2)
                        {
                            for (int k2 = -4; k2 <= 4; ++k2)
                            {
                                IBlockState iblockstate = world.getBlockState(mutableblockpos.setPos(x + i2, y + j2, z + k2));
                                Block block = iblockstate.getBlock();

                                if (!block.canSustainLeaves(iblockstate, world, mutableblockpos.setPos(x + i2, y + j2, z + k2)))
                                {
                                    if (block.isLeaves(iblockstate, world, mutableblockpos.setPos(x + i2, y + j2, z + k2)))
                                    {
                                        this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -2;
                                    }
                                    else
                                    {
                                        this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -1;
                                    }
                                }
                                else
                                {
                                    this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = 0;
                                }
                            }
                        }
                    }

                    for (int i3 = 1; i3 <= 4; ++i3)
                    {
                        for (int j3 = -4; j3 <= 4; ++j3)
                        {
                            for (int k3 = -4; k3 <= 4; ++k3)
                            {
                                for (int l3 = -4; l3 <= 4; ++l3)
                                {
                                    if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16] == i3 - 1)
                                    {
                                        if (this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
                                        }
                                        if (this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
                                        }
                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] = i3;
                                        }
                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] = i3;
                                        }
                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 - 1] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 - 1] = i3;
                                        }
                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] = i3;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                int l2 = this.surroundings[16912];

                if (l2 >= 0)
                {
                    world.setBlockState(pos, state.withProperty(BlockStateProperty.CHECK_DECAY, false), 4);
                }
                else
                {
                    this.destroy(world, pos);
                }
            }
        }
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return Blocks.LEAVES.getBlockLayer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return Blocks.LEAVES.shouldSideBeRendered(state, world, pos, side);
    }

    @Override
    public boolean causesSuffocation(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (world.isRainingAt(pos.up()) && !world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && rand.nextInt(15) == 1)
        {
            double d0 = pos.getX() + rand.nextFloat();
            double d1 = pos.getY() - 0.05D;
            double d2 = pos.getZ() + rand.nextFloat();
            world.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        if (this.type == BlockType.ALIEN_BERRY_OAK_LEAVES)
        {
            if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && rand.nextInt(10) == 0)
            {
                double d0 = pos.getX() + rand.nextFloat();
                double d1 = pos.getY() - 0.05D;
                double d2 = pos.getZ() + rand.nextFloat();
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_BERRY_LEAVES, d0, d1, d2);
            }
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        int chance = 20;

        if (fortune > 0)
        {
            chance -= 2 << fortune;

            if (chance < 10)
            {
                chance = 10;
            }
        }

        if (rand.nextInt(chance) == 0)
        {
            drops.add(new ItemStack(this.getItemDropped(state, rand, fortune)));
        }

        chance = 200;

        if (fortune > 0)
        {
            chance -= 10 << fortune;

            if (chance < 40)
            {
                chance = 40;
            }
        }

        this.captureDrops(true);

        if (world instanceof World)
        {
            this.dropFruits((World)world, pos, state, chance);
        }
        drops.addAll(this.captureDrops(false));
    }

    @Override
    public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public void beginLeavesDecay(IBlockState state, World world, BlockPos pos)
    {
        if (!state.getValue(BlockStateProperty.CHECK_DECAY))
        {
            world.setBlockState(pos, state.withProperty(BlockStateProperty.CHECK_DECAY, true), 4);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this.type == BlockType.INFECTED_OAK_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.INFECTED_OAK_SAPLING);
        }
        else if (this.type == BlockType.INFECTED_JUNGLE_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.INFECTED_JUNGLE_SAPLING);
        }
        else if (this.type == BlockType.ALIEN_BERRY_OAK_LEAVES)
        {
            return Item.getItemFromBlock(MPBlocks.ALIEN_BERRY_OAK_SAPLING);
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this));
        return ret;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateProperty.DECAYABLE, BlockStateProperty.CHECK_DECAY);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateProperty.DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(BlockStateProperty.CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!state.getValue(BlockStateProperty.DECAYABLE))
        {
            i |= 4;
        }
        if (state.getValue(BlockStateProperty.CHECK_DECAY))
        {
            i |= 8;
        }
        return i;
    }

    private void dropFruits(World world, BlockPos pos, IBlockState state, int chance)
    {
        if (world.rand.nextInt(chance) == 0)
        {
            if (this.type == BlockType.INFECTED_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.INFECTED_APPLE));
            }
            else if (this.type == BlockType.ALIEN_BERRY_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.ALIEN_BERRY));
            }
        }
    }

    private void destroy(World world, BlockPos pos)
    {
        this.getItemDropped(world.getBlockState(pos), world.rand, 0);
        world.setBlockToAir(pos);
    }

    public static enum BlockType
    {
        INFECTED_OAK_LEAVES,
        INFECTED_JUNGLE_LEAVES,
        ALIEN_BERRY_OAK_LEAVES;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }
    }
}
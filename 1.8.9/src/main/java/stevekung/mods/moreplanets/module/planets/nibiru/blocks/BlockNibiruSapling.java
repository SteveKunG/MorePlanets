package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.*;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockNibiruSapling extends BlockBushMP implements IBlockVariants, IGrowable
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockNibiruSapling(String name)
    {
        super();
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.INFECTED_OAK_SAPLING));
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        int meta = this.getMetaFromState(state);
        Block block = world.getBlockState(pos.down()).getBlock();

        if (meta == 3)
        {
            return block == NibiruBlocks.GREEN_VEIN_GRASS || block == NibiruBlocks.INFECTED_DIRT;
        }
        else
        {
            return block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);
        this.canBlockStay(world, pos, state);

        if (!world.isRemote)
        {
            super.updateTick(world, pos, state, rand);

            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(world, rand, pos, state);
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    public int getDamageValue(World world, BlockPos pos)
    {
        return this.getMetaFromState(world.getBlockState(pos));
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.SAPLING;
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("infected_oak", "infected_dead_oak", "infected_jungle", "alien_berry");
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        int meta = this.getMetaFromState(state);
        Object obj = null;
        int i = 0;
        int j = 0;
        boolean flag = false;

        if (obj == null)
        {
            switch (meta)
            {
            case 0:
                obj = rand.nextInt(10) == 0 ? new WorldGenInfectedBigTree(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : new WorldGenInfectedTree(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
                break;
            case 1:
                obj = rand.nextInt(10) == 0 ? new WorldGenInfectedBigTree(true, NibiruBlocks.NIBIRU_LOG, 1, NibiruBlocks.NIBIRU_LEAVES, 1) : new WorldGenInfectedTree(true, NibiruBlocks.NIBIRU_LOG, 1, NibiruBlocks.NIBIRU_LEAVES, 1);
                break;
            case 2:
                label269:
                    for (i = 0; i >= -1; --i)
                    {
                        for (j = 0; j >= -1; --j)
                        {
                            if (this.func_181624_a(world, pos, i, j))
                            {
                                obj = new WorldGenInfectedMegaJungle(true, 10, 20);
                                flag = true;
                                break label269;
                            }
                        }
                    }
            if (!flag)
            {
                j = 0;
                i = 0;
                obj = new WorldGenInfectedJungleTrees(true, 4 + rand.nextInt(7), false);
            }
            break;
            case 3:
                obj = rand.nextInt(10) == 0 ? new WorldGenAlienBerryBigTree() : new WorldGenAlienBerryTree();
                break;
            }
        }

        if (obj != null)
        {
            IBlockState iblockstate2 = Blocks.air.getDefaultState();

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

            if (!((WorldGenerator)obj).generate(world, rand, pos.add(i, 0, j)))
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

    private boolean func_181624_a(World world, BlockPos pos, int x, int z)
    {
        return this.isTypeAt(world, pos.add(x, 0, z)) && this.isTypeAt(world, pos.add(x + 1, 0, z)) && this.isTypeAt(world, pos.add(x, 0, z + 1)) && this.isTypeAt(world, pos.add(x + 1, 0, z + 1));
    }

    public boolean isTypeAt(World world, BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        return iblockstate.getBlock() == this && iblockstate.getValue(VARIANT) == BlockType.INFECTED_JUNGLE_SAPLING;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        INFECTED_OAK_SAPLING,
        INFECTED_DEAD_OAK_SAPLING,
        INFECTED_JUNGLE_SAPLING,
        ALIEN_BERRY_SAPLING;

        private static BlockType[] values = BlockType.values();

        public static BlockType[] valuesCached()
        {
            return BlockType.values;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}
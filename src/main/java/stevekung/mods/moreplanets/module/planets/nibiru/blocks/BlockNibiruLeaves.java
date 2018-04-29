package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.VariantsName;
import stevekung.mods.moreplanets.utils.blocks.BlockLeavesMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.blocks.IBlockVariants;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockNibiruLeaves extends BlockLeavesMP implements IBlockVariants
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);
    int[] adjacentTreeBlocks;

    public BlockNibiruLeaves(String name)
    {
        super();
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.INFECTED_OAK_LEAVES).withProperty(BlockStateProperty.CHECK_DECAY, true).withProperty(BlockStateProperty.DECAYABLE, true));
        this.setUnlocalizedName(name);
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        super.randomDisplayTick(state, world, pos, rand);

        if (state.getValue(VARIANT) == BlockType.ALIEN_BERRY_OAK_LEAVES)
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
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(NibiruBlocks.NIBIRU_SAPLING);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state) & 3;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT, BlockStateProperty.DECAYABLE, BlockStateProperty.CHECK_DECAY);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[(meta & 3) % 4]).withProperty(BlockStateProperty.DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(BlockStateProperty.CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | state.getValue(VARIANT).ordinal();

        if (!state.getValue(BlockStateProperty.DECAYABLE).booleanValue())
        {
            i |= 4;
        }
        if (state.getValue(BlockStateProperty.CHECK_DECAY).booleanValue())
        {
            i |= 8;
        }
        return i;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(state) & 3);
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal()));
        return ret;
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("infected_oak", "infected_dead_oak", "infected_jungle", "alien_berry");
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    protected void dropFruits(World world, BlockPos pos, IBlockState state, int chance)
    {
        if (world.rand.nextInt(chance) == 0)
        {
            if (state.getValue(VARIANT) == BlockType.INFECTED_OAK_LEAVES || state.getValue(VARIANT) == BlockType.INFECTED_DEAD_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.INFECTED_APPLE));
            }
            else if (state.getValue(VARIANT) == BlockType.ALIEN_BERRY_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.ALIEN_BERRY));
            }
        }
    }

    public static enum BlockType implements IStringSerializable
    {
        INFECTED_OAK_LEAVES,
        INFECTED_DEAD_OAK_LEAVES,
        INFECTED_JUNGLE_LEAVES,
        ALIEN_BERRY_OAK_LEAVES;

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
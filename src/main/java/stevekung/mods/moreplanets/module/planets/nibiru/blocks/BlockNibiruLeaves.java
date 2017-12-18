package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.module.planets.nibiru.items.ItemNibiruFruits;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockLeavesMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockNibiruLeaves extends BlockLeavesMP implements IBlockVariants
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);
    int[] adjacentTreeBlocks;

    public BlockNibiruLeaves(String name)
    {
        super();
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.INFECTED_OAK_LEAVES).withProperty(BlockStateHelper.CHECK_DECAY, true).withProperty(BlockStateHelper.DECAYABLE, true));
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
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.randomDisplayTick(world, pos, state, rand);

        if (state.getValue(VARIANT) == BlockType.ALIEN_BERRY_OAK_LEAVES)
        {
            if (!World.doesBlockHaveSolidTopSurface(world, pos.down()) && rand.nextInt(10) == 0)
            {
                double d0 = pos.getX() + rand.nextFloat();
                double d1 = pos.getY() - 0.05D;
                double d2 = pos.getZ() + rand.nextFloat();
                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.ALIEN_BERRY_LEAVES, d0, d1, d2);
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
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT, BlockStateHelper.DECAYABLE, BlockStateHelper.CHECK_DECAY });
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[(meta & 3) % 4]).withProperty(BlockStateHelper.DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(BlockStateHelper.CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((BlockType)state.getValue(VARIANT)).ordinal();

        if (!state.getValue(BlockStateHelper.DECAYABLE).booleanValue())
        {
            i |= 4;
        }
        if (state.getValue(BlockStateHelper.CHECK_DECAY).booleanValue())
        {
            i |= 8;
        }
        return i;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)) & 3);
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack itemStack, IBlockAccess world, BlockPos pos, int fortune)
    {
        ArrayList<ItemStack> ret = Lists.newArrayList();
        ret.add(new ItemStack(this, 1, ((BlockType)world.getBlockState(pos).getValue(VARIANT)).ordinal()));
        return ret;
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("infected_oak", "infected_dead_oak", "infected_jungle", "alien_berry");
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
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
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, ItemNibiruFruits.ItemType.INFECTED_APPLE.ordinal()));
            }
            else if (state.getValue(VARIANT) == BlockType.ALIEN_BERRY_OAK_LEAVES)
            {
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, ItemNibiruFruits.ItemType.ALIEN_BERRY.ordinal()));
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
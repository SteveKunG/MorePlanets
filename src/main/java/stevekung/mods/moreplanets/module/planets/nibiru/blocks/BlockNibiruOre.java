package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockNibiruOre extends BlockBasicMP implements IDetectableResource, IBlockVariants
{
    private Random rand = new Random();
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockNibiruOre(String name)
    {
        super(Material.ROCK);
        this.setTickRandomly(true);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.NIBIRU_COAL_ORE));
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
    {
        if (this.getMetaFromState(world.getBlockState(pos)) == 4)
        {
            this.activeRedstoneOre(world, pos);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (this.getMetaFromState(state) == 4)
        {
            this.activeRedstoneOre(world, pos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (this.getMetaFromState(state) == 4)
        {
            this.activeRedstoneOre(world, pos);
        }
        return false;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.getMetaFromState(state) == 12)
        {
            world.setBlockState(pos, this.getStateFromMeta(4), 2);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (this.getMetaFromState(state) == 12)
        {
            this.spawnRedstoneParticles(world, pos);
        }
    }

    private void activeRedstoneOre(World world, BlockPos pos)
    {
        this.spawnRedstoneParticles(world, pos);
        world.setBlockState(pos, this.getStateFromMeta(12), 2);
    }

    private void spawnRedstoneParticles(World world, BlockPos pos)
    {
        Random random = world.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = pos.getX() + random.nextFloat();
            double d2 = pos.getY() + random.nextFloat();
            double d3 = pos.getZ() + random.nextFloat();

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
                world.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        if (this.getMetaFromState(state) == 12)
        {
            return new ItemStack(this, 1, 4);
        }
        else
        {
            return super.getSilkTouchDrop(state);
        }
    }

    @Override
    public int tickRate(World world)
    {
        return 30;
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length - 1; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 0)
        {
            return NibiruItems.NIBIRU_ITEM;
        }
        if (meta == 3)
        {
            return Items.DIAMOND;
        }
        if (meta == 4 || meta == 12)
        {
            return Items.REDSTONE;
        }
        if (meta == 5)
        {
            return Items.DYE;
        }
        if (meta == 6)
        {
            return Items.EMERALD;
        }
        if (meta == 10)
        {
            return GCItems.basicItem;
        }
        if (meta == 11)
        {
            return NibiruItems.NIBIRU_ITEM;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 5)
        {
            return 4;
        }
        if (meta == 0 || meta == 10)
        {
            return 2;
        }
        if (meta == 3 || meta == 4 || meta == 6 || meta == 11 || meta == 12)
        {
            return 0;
        }
        return meta;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 4 || meta == 12)
        {
            return 4 + rand.nextInt(2) + rand.nextInt(fortune + 1);
        }
        if (meta == 5)
        {
            return 4 + rand.nextInt(5);
        }
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune))
        {
            int j = rand.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return this.quantityDropped(rand) * (j + 1);
        }
        return super.quantityDropped(state, fortune, rand);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 4 || meta == 11 || meta == 12)
        {
            return 1 + this.rand.nextInt(5);
        }
        if (meta == 0)
        {
            return MathHelper.getInt(this.rand, 0, 2);
        }
        if (meta == 3)
        {
            return MathHelper.getInt(this.rand, 3, 7);
        }
        if (meta == 5)
        {
            return MathHelper.getInt(this.rand, 2, 5);
        }
        if (meta == 6)
        {
            return MathHelper.getInt(this.rand, 3, 7);
        }
        return 0;
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("coal_ore", "iron_ore", "gold_ore", "diamond_ore", "redstone_ore", "lapis_ore", "emerald_ore", "aluminum_ore", "copper_ore", "tin_ore", "silicon_ore", "inferumite_crystal_ore", "redstone_ore");
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.ORE;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        if (this.getMetaFromState(state) == 12)
        {
            return new ItemStack(this, 1, 4);
        }
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        NIBIRU_COAL_ORE,
        NIBIRU_IRON_ORE,
        NIBIRU_GOLD_ORE,
        NIBIRU_DIAMOND_ORE,
        NIBIRU_REDSTONE_ORE,
        NIBIRU_LAPIS_ORE,
        NIBIRU_EMERALD_ORE,
        NIBIRU_ALUMINUM_ORE,
        NIBIRU_COPPER_ORE,
        NIBIRU_TIN_ORE,
        NIBIRU_SILICON_ORE,
        INFERUMITE_CRYSTAL_ORE,
        NIBIRU_REDSTONE_ORE_ACTIVE;

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
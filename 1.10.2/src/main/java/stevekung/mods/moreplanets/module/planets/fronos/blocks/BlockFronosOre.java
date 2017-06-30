package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockFronosOre extends BlockBaseMP implements IDetectableResource, IBlockVariants
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockFronosOre(String name)
    {
        super(Material.ROCK);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.FRONOS_IRON_ORE));
        this.setUnlocalizedName(name);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setTickRandomly(true);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            return (int) (0.625F / 16);
        }
        return 0;
    }

    @Override
    public float getBlockHardness(IBlockState state, World world, BlockPos pos)
    {
        int meta = this.getMetaFromState(state);

        if (meta == BlockType.EXTRAILONITE_ORE.ordinal())
        {
            return 5.0F;
        }
        return super.getBlockHardness(state, world, pos);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            return new ItemStack(this, 1, BlockType.FRONOS_REDSTONE_ORE.ordinal());
        }
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockType.valuesCached().length - 1; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, rand, fortune))
        {
            int j = rand.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return this.quantityDropped(rand) * (j + 1);
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_LAPIS_ORE)
        {
            return 4 + rand.nextInt(5);
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE || state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            return 4 + rand.nextInt(2) + rand.nextInt(fortune + 1);
        }
        else
        {
            return this.quantityDropped(rand);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (state.getValue(VARIANT) == BlockType.FRONOS_COAL_ORE)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 0, 2);
            }
            else if (state.getValue(VARIANT) == BlockType.FRONOS_DIAMOND_ORE)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            }
            else if (state.getValue(VARIANT) == BlockType.FRONOS_EMERALD_ORE)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            }
            else if (state.getValue(VARIANT) == BlockType.FRONOS_LAPIS_ORE)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 2, 5);
            }
            else if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE || state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
            {
                i = 1 + rand.nextInt(5);
            }
            else if (state.getValue(VARIANT) == BlockType.FRONOS_QUARTZ_ORE)
            {
                i = MathHelper.getRandomIntegerInRange(rand, 2, 5);
            }
            return i;
        }
        return 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_COAL_ORE)
        {
            return Items.COAL;
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_DIAMOND_ORE)
        {
            return Items.DIAMOND;
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_LAPIS_ORE)
        {
            return Items.DYE;
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_EMERALD_ORE)
        {
            return Items.EMERALD;
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE || state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            return Items.REDSTONE;
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_SILICON_ORE)
        {
            return GCItems.basicItem;
        }
        else if (state.getValue(VARIANT) == BlockType.FRONOS_QUARTZ_ORE)
        {
            return Items.QUARTZ;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        int meta = this.getMetaFromState(state);

        if (meta == BlockType.FRONOS_COAL_ORE.ordinal() || meta >= BlockType.FRONOS_DIAMOND_ORE.ordinal() && meta <= BlockType.FRONOS_REDSTONE_ORE.ordinal() || meta == BlockType.FRONOS_REDSTONE_ORE.ordinal() || meta == BlockType.FRONOS_REDSTONE_ORE_ACTIVE.ordinal() || meta == BlockType.FRONOS_QUARTZ_ORE.ordinal())
        {
            return 0;
        }
        else if (meta == BlockType.FRONOS_LAPIS_ORE.ordinal())
        {
            return EnumDyeColor.BLUE.getDyeDamage();
        }
        else if (meta == BlockType.FRONOS_SILICON_ORE.ordinal())
        {
            return 2;
        }
        return meta;
    }

    @Override
    public int tickRate(World world)
    {
        return 30;
    }

    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
    {
        if (world.getBlockState(pos).getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE)
        {
            this.activate(world, pos);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE)
        {
            this.activate(world, pos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE)
        {
            this.activate(world, pos);
        }
        return false;
    }

    private void activate(World world, BlockPos pos)
    {
        this.spawnParticles(world, pos);
        world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.FRONOS_REDSTONE_ORE_ACTIVE));
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            world.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockType.FRONOS_REDSTONE_ORE));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            this.spawnParticles(world, pos);
        }
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

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        if (state.getValue(VARIANT) == BlockType.FRONOS_REDSTONE_ORE_ACTIVE)
        {
            return new ItemStack(this, 1, BlockType.FRONOS_REDSTONE_ORE.ordinal());
        }
        else
        {
            return super.createStackedBlock(state);
        }
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { VARIANT });
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

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("iron_ore", "gold_ore", "tin_ore", "copper_ore", "aluminum_ore", "lead_ore", "coal_ore", "lapis_ore", "diamond_ore", "emerald_ore", "redstone_ore", "silicon_ore", "quartz_ore", "extrailonite_ore");
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.ORE;
    }

    public static enum BlockType implements IStringSerializable
    {
        FRONOS_IRON_ORE,
        FRONOS_GOLD_ORE,
        FRONOS_TIN_ORE,
        FRONOS_COPPER_ORE,
        FRONOS_ALUMINUM_ORE,
        FRONOS_LEAD_ORE,
        FRONOS_COAL_ORE,
        FRONOS_LAPIS_ORE,
        FRONOS_DIAMOND_ORE,
        FRONOS_EMERALD_ORE,
        FRONOS_REDSTONE_ORE,
        FRONOS_SILICON_ORE,
        FRONOS_QUARTZ_ORE,
        EXTRAILONITE_ORE,
        FRONOS_REDSTONE_ORE_ACTIVE;

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
package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockDiona extends BlockBasicMP implements IDetectableResource, ITerraformableBlock, IBlockVariants
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockDiona(String name)
    {
        super(Material.ROCK);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.DIONA_SURFACE_ROCK));
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
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
    {
        return this.getMetaFromState(world.getBlockState(pos)) == 9 || this.getMetaFromState(world.getBlockState(pos)) == 10;
    }

    @Override
    public float getBlockHardness(IBlockState state, World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block != this)
        {
            return 0;
        }

        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 0 || meta == 1)
        {
            return 1.25F;
        }
        if (meta == 2)
        {
            return 1.5F;
        }
        if (meta == 3)
        {
            return 2.0F;
        }
        if (meta >= 4 && meta <= 8)
        {
            return 3.0F;
        }
        if (meta == 9 || meta == 10)
        {
            return 5.0F;
        }
        return 4.0F;
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
    {
        if (this.getMetaFromState(world.getBlockState(pos)) == 11)
        {
            return 40.0F;
        }
        return super.getExplosionResistance(world, pos, exploder, explosion);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this.getMetaFromState(state) == 4)
        {
            return DionaItems.DIONA_ITEM;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        if (this.getMetaFromState(state) == 2)
        {
            return 3;
        }
        else if (this.getMetaFromState(state) == 4)
        {
            return 1;
        }
        return this.getMetaFromState(state);
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        if (this.getMetaFromState(state) >= 4 && this.getMetaFromState(state) <= 8)
        {
            return true;
        }
        return false;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (this.getMetaFromState(state) == 4)
        {
            int j = rand.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return 1 + rand.nextInt(3) * j;
        }
        return this.quantityDropped(rand);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        return this.getMetaFromState(state) == 4 ? MathHelper.getRandomIntegerInRange(new Random(), 3, 7) : 0;
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if ((meta == 0 || meta == 1) && !world.getBlockState(pos.up()).isOpaqueCube())
        {
            return true;
        }
        return false;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        switch (meta)
        {
        case 0:
        case 1:
        case 2:
        case 3:
        default:
            return EnumSortCategoryBlock.BUILDING_BLOCK;
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
            return EnumSortCategoryBlock.ORE;
        case 9:
        case 10:
            return EnumSortCategoryBlock.INGOT_BLOCK;
        case 11:
            return EnumSortCategoryBlock.DUNGEON_BRICK;
        }
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("surface_rock", "sub_surface", "rock", "cobblestone", "setrorium_ore", "illenium_ore", "tin_ore", "copper_ore", "aluminum_ore", "setrorium_block", "illenium_block", "dungeon_brick");
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

    public static enum BlockType implements IStringSerializable
    {
        DIONA_SURFACE_ROCK,
        DIONA_SUB_SURFACE_ROCK,
        DIONA_ROCK,
        DIONA_COBBLESTONE,
        SETRORIUM_ORE,
        ILLENIUM_ORE,
        DIONA_TIN_ORE,
        DIONA_COPPER_ORE,
        DIONA_ALUMINUM_ORE,
        SETRORIUM_BLOCK,
        ILLENIUM_BLOCK,
        DIONA_DUNGEON_BRICK;

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
package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockFronos extends BlockBaseMP implements IBlockVariants
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockFronos(String name)
    {
        super(Material.rock);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.FRONOS_STONE));
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
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
    public float getBlockHardness(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block != this)
        {
            return 0;
        }

        switch (this.getMetaFromState(world.getBlockState(pos)))
        {
        case 0:
        case 2:
        case 3:
        case 4:
        case 5:
            return 1.5F;
        case 6:
            return 4.0F;
        default:
            return 2.0F;
        }
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity entity, Explosion explosion)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 6)
        {
            return 40.0F;
        }
        return super.getExplosionResistance(world, pos, entity, explosion);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        if (this.getMetaFromState(state) == 0)
        {
            return 1;
        }
        return this.getMetaFromState(state);
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

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("stone", "cobblestone", "stone_brick", "mossy_stone_brick", "cracked_stone_brick", "chiseled_stone_brick", "dungeon_brick");
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return meta == 6 ? EnumSortCategoryBlock.DUNGEON_BRICK : EnumSortCategoryBlock.BUILDING_BLOCK;
    }

    public static enum BlockType implements IStringSerializable
    {
        FRONOS_STONE,
        FRONOS_COBBLESTONE,
        FRONOS_STONE_BRICK,
        FRONOS_MOSSY_STONE_BRICK,
        FRONOS_CRACKED_STONE_BRICK,
        FRONOS_CHISELED_STONE_BRICK,
        FRONOS_DUNGEON_BRICK;

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
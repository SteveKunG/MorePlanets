package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockNibiru extends BlockBaseMP implements IBlockVariants
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockNibiru(String name)
    {
        super(Material.rock);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.NIBIRU_ROCK));
        this.setUnlocalizedName(name);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
    {
        return this.getMetaFromState(world.getBlockState(pos)) == 7;
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

        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 0 || meta >= 3 && meta <= 6)
        {
            return 1.5F;
        }
        if (meta == 1 || meta == 2)
        {
            return 2.0F;
        }
        if (meta == 7)
        {
            return 5.0F;
        }
        return 4.0F;
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
    {
        if (this.getMetaFromState(world.getBlockState(pos)) == 8 || this.getMetaFromState(world.getBlockState(pos)) == 9)
        {
            return 40.0F;
        }
        return super.getExplosionResistance(world, pos, exploder, explosion);
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
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        switch (meta)
        {
        case 7:
            return EnumSortCategoryBlock.INGOT_BLOCK;
        case 8:
        case 9:
            return EnumSortCategoryBlock.DUNGEON_BRICK;
        default:
            return EnumSortCategoryBlock.BUILDING_BLOCK;
        }
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("nibiru_rock", "nibiru_cobblestone", "nibiru_vein_cobblestone", "infected_stone_bricks", "infected_vein_stone_bricks", "infected_cracked_stone_bricks", "infected_chiseled_stone_bricks", "inferumite_block", "nibiru_dungeon_brick", "mossy_nibiru_dungeon_brick");
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
        NIBIRU_ROCK,
        NIBIRU_COBBLESTONE,
        NIBIRU_VEIN_COBBLESTONE,
        INFECTED_STONE_BRICKS,
        INFECTED_VEIN_STONE_BRICKS,
        INFECTED_CRACKED_STONE_BRICKS,
        INFECTED_CHISELED_STONE_BRICKS,
        INFERUMITE_BLOCK,
        NIBIRU_DUNGEON_BRICK,
        MOSSY_NIBIRU_DUNGEON_BRICK;

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
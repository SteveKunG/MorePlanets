package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;
import stevekung.mods.moreplanets.util.inventory.ContainerWorkbenchMP;

public class BlockNibiruCraftingTable extends BlockBaseMP implements IBlockVariants
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);

    protected BlockNibiruCraftingTable(String name)
    {
        super(Material.WOOD);
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.NIBIRU_CRAFTING_TABLE));
        this.setUnlocalizedName(name);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            player.displayGui(new InterfaceCraftingTable(world, pos));
            return true;
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("nibiru", "alien_berry");
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
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

    public static class InterfaceCraftingTable implements IInteractionObject
    {
        private World world;
        private BlockPos position;

        public InterfaceCraftingTable(World world, BlockPos pos)
        {
            this.world = world;
            this.position = pos;
        }

        @Override
        public String getName()
        {
            return null;
        }

        @Override
        public boolean hasCustomName()
        {
            return false;
        }

        @Override
        public ITextComponent getDisplayName()
        {
            return null;
        }

        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
        {
            return new ContainerWorkbenchMP(playerInventory, this.world, this.position, NibiruBlocks.NIBIRU_CRAFTING_TABLE);
        }

        @Override
        public String getGuiID()
        {
            return "minecraft:crafting_table";
        }
    }

    public static enum BlockType implements IStringSerializable
    {
        NIBIRU_CRAFTING_TABLE,
        ALIEN_BERRY_CRAFTING_TABLE;

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
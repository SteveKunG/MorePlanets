package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.utils.inventory.ContainerWorkbenchMP;

public class BlockCraftingTableMP extends BlockBaseMP
{
    public BlockCraftingTableMP(String name)
    {
        super(Material.WOOD);
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            player.displayGui(new InterfaceCraftingTable(world, pos, this));
            return true;
        }
        return true;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    public static class InterfaceCraftingTable implements IInteractionObject
    {
        private final World world;
        private final BlockPos pos;
        private final Block block;

        public InterfaceCraftingTable(World world, BlockPos pos, Block block)
        {
            this.world = world;
            this.pos = pos;
            this.block = block;
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
            return new TextComponentString(this.block.getLocalizedName());
        }

        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
        {
            return new ContainerWorkbenchMP(playerInventory, this.world, this.pos, this.block);
        }

        @Override
        public String getGuiID()
        {
            return "minecraft:crafting_table";
        }
    }
}
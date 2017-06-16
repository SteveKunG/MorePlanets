package stevekung.mods.moreplanets.entity;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntitySpaceMinecartChest extends EntityMinecartContainer
{
    private String inventoryName = "container.minecart";
    private IBlockState blockForDisplay = Blocks.chest.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.NORTH);
    private int offset = 8;

    public EntitySpaceMinecartChest(World world)
    {
        super(world);
    }

    public EntitySpaceMinecartChest(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    public void killMinecart(DamageSource source)
    {
        super.killMinecart(source);
        this.dropItemWithOffset(Item.getItemFromBlock(this.blockForDisplay.getBlock()), 1, 0.0F);
    }

    @Override
    public int getSizeInventory()
    {
        return 27;
    }

    @Override
    public EnumMinecartType getMinecartType()
    {
        return EnumMinecartType.CHEST;
    }

    @Override
    public String getName()
    {
        return this.inventoryName;
    }

    @Override
    public IBlockState getDefaultDisplayTile()
    {
        return this.blockForDisplay;
    }

    @Override
    public int getDefaultDisplayTileOffset()
    {
        return this.offset;
    }

    @Override
    public String getGuiID()
    {
        return "minecraft:chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
    {
        return new ContainerChest(playerInventory, this, player);
    }

    public void setBlockForDisplaying(IBlockState state)
    {
        this.blockForDisplay = state;
        this.func_174899_a(state);
    }

    public void setInventoryName(String name)
    {
        this.inventoryName = name;
    }

    public void setDisplayOffset(int offset)
    {
        this.offset = offset;
        this.setDisplayTileOffset(offset);
    }
}
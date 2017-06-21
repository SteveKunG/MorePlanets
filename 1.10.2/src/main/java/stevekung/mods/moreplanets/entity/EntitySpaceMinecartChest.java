package stevekung.mods.moreplanets.entity;

import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySpaceMinecartChest extends EntityMinecartContainer
{
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
        this.dropItemWithOffset(Item.getItemFromBlock(this.getDefaultDisplayTile().getBlock()), 1, 0.0F);
    }

    @Override
    public int getSizeInventory()
    {
        return 27;
    }

    @Override
    public Type getType()
    {
        return Type.CHEST;
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
}
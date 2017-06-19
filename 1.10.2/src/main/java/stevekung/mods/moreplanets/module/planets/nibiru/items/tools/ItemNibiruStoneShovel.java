package stevekung.mods.moreplanets.module.planets.nibiru.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.items.tools.ItemShovelMP;

public class ItemNibiruStoneShovel extends ItemShovelMP
{
    public ItemNibiruStoneShovel(String name, ToolMaterial material)
    {
        super(material);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
        {
            if (entity.ticksExisted % 600 == 0)
            {
                int i = itemStack.getItemDamage();
                itemStack.setItemDamage(--i);
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        ItemStack itemStack = item.getEntityItem();

        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
        {
            if (item.ticksExisted % 600 == 0)
            {
                int i = itemStack.getItemDamage();
                itemStack.setItemDamage(--i);
                item.lifespan = 6000;
                item.worldObj.playAuxSFX(2005, new BlockPos(item.posX, item.posY, item.posZ), 0);
            }
        }
        return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() == Item.getItemFromBlock(NibiruBlocks.NIBIRU_BLOCK) && repair.getItemDamage() == 0)
        {
            return true;
        }
        return false;
    }
}
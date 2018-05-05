package stevekung.mods.moreplanets.planets.nibiru.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.utils.items.tools.ItemAxeMP;
import stevekung.mods.stevekunglib.utils.EnumToolSpeed;

public class ItemInfectedWoodAxe extends ItemAxeMP
{
    public ItemInfectedWoodAxe(String name, ToolMaterial material)
    {
        super(name, material, NibiruBlocks.INFECTED_OAK_PLANKS, EnumToolSpeed.WOOD);
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
        ItemStack itemStack = item.getItem();

        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
        {
            if (item.ticksExisted % 600 == 0)
            {
                int i = itemStack.getItemDamage();
                itemStack.setItemDamage(--i);
                item.lifespan = 6000;
                item.world.playEvent(2005, new BlockPos(item.posX, item.posY, item.posZ), 0);
            }
        }
        return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (repair.getItem() == Item.getItemFromBlock(NibiruBlocks.INFECTED_OAK_PLANKS))
        {
            return true;
        }
        return false;
    }
}
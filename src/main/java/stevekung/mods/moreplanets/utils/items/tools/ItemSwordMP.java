package stevekung.mods.moreplanets.utils.items.tools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ISortableItem;

public class ItemSwordMP extends ItemSword implements ISortableItem
{
    private Item repairItem;
    private int repairItemMeta;

    public ItemSwordMP(String name, ToolMaterial material, Item item)
    {
        this(name, material, item, -1);
    }

    public ItemSwordMP(String name, ToolMaterial material, Block block)
    {
        this(name, material, Item.getItemFromBlock(block), -1);
    }

    public ItemSwordMP(String name, ToolMaterial material, Block block, int meta)
    {
        this(name, material, Item.getItemFromBlock(block), meta);
    }

    public ItemSwordMP(String name, ToolMaterial material, Item item, int meta)
    {
        super(material);
        this.repairItem = item;
        this.repairItemMeta = meta;
        this.setUnlocalizedName(name);
    }

    public ItemSwordMP(ToolMaterial material)
    {
        super(material);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (this.repairItemMeta == -1)
        {
            if (repair.getItem() == this.repairItem)
            {
                return true;
            }
        }
        else
        {
            if (repair.getItem() == this.repairItem && repair.getItemDamage() == this.repairItemMeta)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.SWORD;
    }
}
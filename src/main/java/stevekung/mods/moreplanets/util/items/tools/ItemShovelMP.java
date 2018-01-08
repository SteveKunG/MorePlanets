package stevekung.mods.moreplanets.util.items.tools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public class ItemShovelMP extends ItemSpade implements ISortableItem
{
    private Item repairItem;
    private int repairItemMeta;

    public ItemShovelMP(String name, ToolMaterial material, Item item)
    {
        this(name, material, item, -1);
    }

    public ItemShovelMP(String name, ToolMaterial material, Block block)
    {
        this(name, material, Item.getItemFromBlock(block), -1);
    }

    public ItemShovelMP(String name, ToolMaterial material, Block block, int meta)
    {
        this(name, material, Item.getItemFromBlock(block), meta);
    }

    public ItemShovelMP(String name, ToolMaterial material, Item item, int meta)
    {
        super(material);
        this.repairItem = item;
        this.repairItemMeta = meta;
        this.setUnlocalizedName(name);
    }

    public ItemShovelMP(ToolMaterial material)
    {
        super(material);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
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
        return EnumSortCategoryItem.SHOVEL;
    }
}
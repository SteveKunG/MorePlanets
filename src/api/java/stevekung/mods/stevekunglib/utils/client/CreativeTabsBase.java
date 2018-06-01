package stevekung.mods.stevekunglib.utils.client;

import java.util.Collections;
import java.util.Comparator;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabsBase extends CreativeTabs
{
    private ItemStack itemStack;
    private Comparator<ItemStack> tabSorter;

    public CreativeTabsBase(String name)
    {
        super(CreativeTabs.getNextID(), name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        return this.itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem()
    {
        return this.itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list)
    {
        super.displayAllRelevantItems(list);

        if (this.tabSorter != null)
        {
            try
            {
                Collections.sort(list, this.tabSorter);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setTabSorter(Comparator<ItemStack> tabSorter)
    {
        this.tabSorter = tabSorter;
    }

    public void setDisplayItemStack(ItemStack itemStack)
    {
        this.itemStack = itemStack;
    }
}
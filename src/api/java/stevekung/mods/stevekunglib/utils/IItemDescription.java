package stevekung.mods.stevekunglib.utils;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IItemDescription
{
    void addDescription(ItemStack itemStack, List<String> list);
}
package stevekung.mods.moreplanets.utils;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ItemDescription
{
    void addDescription(ItemStack itemStack, List<String> list);
}
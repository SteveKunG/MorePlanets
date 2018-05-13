package stevekung.mods.moreplanets.utils.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public interface IDungeonKeyable
{
    boolean onActivated(EntityPlayer player, Item item, boolean isKey);
    Item getDungeonKey();
}
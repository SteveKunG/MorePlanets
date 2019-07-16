package stevekung.mods.moreplanets.utils.items;

import net.minecraft.item.ItemStack;

public class ItemBeaconPayment extends ItemBaseMP
{
    public ItemBeaconPayment(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean isBeaconPayment(ItemStack itemStack)
    {
        return true;
    }
}
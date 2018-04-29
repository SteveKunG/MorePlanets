package stevekung.mods.moreplanets.util.items;

import net.minecraft.item.ItemStack;

public class ItemBeaconPayment extends ItemBaseMP
{
    public ItemBeaconPayment(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean isBeaconPayment(ItemStack itemStack)
    {
        return true;
    }
}
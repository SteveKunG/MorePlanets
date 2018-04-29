package stevekung.mods.moreplanets.utils.items;

import net.minecraft.item.ItemStack;

public class ItemCompressedMetal extends ItemBaseMP
{
    public ItemCompressedMetal(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public float getSmeltingExperience(ItemStack itemStack)
    {
        return 1.0F;
    }
}
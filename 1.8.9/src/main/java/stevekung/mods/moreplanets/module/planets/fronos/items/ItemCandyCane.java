package stevekung.mods.moreplanets.module.planets.fronos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemCandyCane extends ItemFoodVariantsMP
{
    public ItemCandyCane(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    protected String[] getItemVariantsName()
    {
        return new String[] {"red", "green", "blue", "orange", "pink", "yellow", "purple", "rainbow"};
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return 4;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return 0.5F;
    }

    public static enum ItemType
    {
        RED_CANDY_CANE_ITEM,
        GREEN_CANDY_CANE_ITEM,
        BLUE_CANDY_CANE_ITEM,
        ORANGE_CANDY_CANE_ITEM,
        PINK_CANDY_CANE_ITEM,
        YELLOW_CANDY_CANE_ITEM,
        PURPLE_CANDY_CANE_ITEM,
        RAINBOW_CANDY_CANE_ITEM;
    }
}
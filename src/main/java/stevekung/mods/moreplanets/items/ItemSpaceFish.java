package stevekung.mods.moreplanets.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemSpaceFish extends ItemFoodVariantsMP
{
    public ItemSpaceFish(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.FOOD;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "zelius", "glowing_alien", "cheese" };
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return ItemType.valuesCached()[itemStack.getItemDamage()].hunger;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return ItemType.valuesCached()[itemStack.getItemDamage()].saturation;
    }

    public static enum ItemType
    {
        ZELIUS_FISH(2, 0.1F),
        GLOWING_ALIEN_FISH(2, 0.1F),
        CHEESE_FISH(2, 0.1F);

        int hunger;
        float saturation;
        private static ItemType[] values = ItemType.values();

        ItemType(int hunger, float saturation)
        {
            this.hunger = hunger;
            this.saturation = saturation;
        }

        public static ItemType[] valuesCached()
        {
            return ItemType.values;
        }
    }
}
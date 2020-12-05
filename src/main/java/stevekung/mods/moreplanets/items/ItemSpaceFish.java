package stevekung.mods.moreplanets.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ItemFoodMP;

public class ItemSpaceFish extends ItemFoodMP
{
    private final ItemType type;

    public ItemSpaceFish(String name, ItemType type)
    {
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.FOOD;
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return this.type.getHunger();
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return this.type.getSaturation();
    }

    public enum ItemType
    {
        ZELIUS_FISH(2, 0.1F),
        GLOWING_ALIEN_FISH(2, 0.1F),
        CHEESE_FISH(2, 0.1F);

        private int hunger;
        private float saturation;

        ItemType(int hunger, float saturation)
        {
            this.hunger = hunger;
            this.saturation = saturation;
        }

        public int getHunger()
        {
            return this.hunger;
        }

        public float getSaturation()
        {
            return this.saturation;
        }
    }
}
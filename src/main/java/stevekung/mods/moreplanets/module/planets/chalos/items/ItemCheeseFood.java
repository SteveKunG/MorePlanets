package stevekung.mods.moreplanets.module.planets.chalos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemCheeseFood extends ItemFoodVariantsMP
{
    public ItemCheeseFood(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() == 0)
        {
            return 8;
        }
        return 32;
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

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "cheese_milk_curd", "raw_cheese_beef", "cooked_cheese_beef", "cheese_spore_berry" };
    }

    public static enum ItemType
    {
        CHEESE_MILK_CURD(3, 0.35F),
        RAW_CHEESE_BEEF(3, 0.2F),
        COOKED_CHEESE_BEEF(8, 0.8F),
        CHEESE_SPORE_BERRY(4, 0.4F);

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
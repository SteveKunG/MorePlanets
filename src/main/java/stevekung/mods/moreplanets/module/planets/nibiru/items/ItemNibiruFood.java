package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemNibiruFood extends ItemFoodVariantsMP
{
    public ItemNibiruFood(String name)
    {
        super();
        this.setUnlocalizedName(name);
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
    protected String[] getItemVariantsName()
    {
        return new String[] { "raw_shlime_meat", "cooked_shlime_meat" };
    }

    public static enum ItemType
    {
        RAW_SHLIME_MEAT(3, 0.3F),
        COOKED_SHLIME_MEAT(8, 0.8F);

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
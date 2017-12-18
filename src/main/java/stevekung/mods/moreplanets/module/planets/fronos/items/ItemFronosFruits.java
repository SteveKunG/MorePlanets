package stevekung.mods.moreplanets.module.planets.fronos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemFronosFruits extends ItemFoodVariantsMP
{
    public ItemFronosFruits(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
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
    protected String[] getItemVariantsName()
    {
        return new String[] { "strawberry", "giant_blueberry" };
    }

    public static enum ItemType
    {
        STRAWBERRY(4, 0.25F),
        GIANT_BLUEBERRY(6, 0.45F);

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
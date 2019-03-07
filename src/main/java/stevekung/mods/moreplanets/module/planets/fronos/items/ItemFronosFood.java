package stevekung.mods.moreplanets.module.planets.fronos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemFronosFood extends ItemFoodVariantsMP
{
    public ItemFronosFood(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == 1 ? 8 : 32;
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return ItemType.valuesCached()[itemStack.getItemDamage() % ItemType.valuesCached().length].hunger;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return ItemType.valuesCached()[itemStack.getItemDamage() % ItemType.valuesCached().length].saturation;
    }

    @Override
    protected String[] getItemVariantsName()
    {
        return new String[] { "chocolate_bar", "jelly_beans", "marshmallow", "cooked_marshmallow" };
    }

    public static enum ItemType
    {
        CHOCOLATE_BAR(5, 0.6F),
        JELLY_BEANS(1, 0.05F),
        MARSHMALLOW(3, 0.15F),
        COOKED_MARSHMALLOW(6, 0.35F);

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
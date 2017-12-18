package stevekung.mods.moreplanets.module.planets.fronos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemJelly extends ItemFoodVariantsMP
{
    public ItemJelly(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return 4;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return 0.35F;
    }

    @Override
    protected String[] getItemVariantsName()
    {
        return new String[] { "grape", "raspberry", "strawberry", "berry", "lime", "orange", "green", "lemon" };
    }

    public static enum ItemType
    {
        GRAPE_JELLY,
        RASPBERRY_JELLY,
        STRAWBERRY_JELLY,
        BERRY_JELLY,
        LIME_JELLY,
        ORANGE_JELLY,
        GREEN_JELLY,
        LEMON_JELLY
    }
}
package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemNibiru extends ItemBaseVariantsMP
{
    public ItemNibiru(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public float getSmeltingExperience(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == 4 ? 2.0F : -1.0F;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return meta == 4 ? EnumSortCategoryItem.HEAVY_PLATE : EnumSortCategoryItem.GENERAL;
    }

    @Override
    public boolean isBeaconPayment(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == 1;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "inferumite_crystal", "multalic_crystal", "infected_coal", "shlime_tail", "compressed_tier_5_rocket_plate", "infected_charcoal" };
    }

    public static enum ItemType
    {
        INFERUMITE_CRYSTAL,
        MULTALIC_CRYSTAL_PIECES,
        INFECTED_COAL,
        SHLIME_TAIL,
        COMPRESSED_TIER_5_ROCKET_PLATE,
        INFECTED_CHARCOAL;
    }
}
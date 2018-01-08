package stevekung.mods.moreplanets.module.planets.diona.items;

import micdoodle8.mods.galacticraft.core.TransformerHooks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemDiona extends ItemBaseVariantsMP
{
    public ItemDiona(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public float getSmeltingExperience(ItemStack itemStack)
    {
        switch (itemStack.getItemDamage())
        {
        case 2:
        case 3:
            return 1.0F;
        }
        return -1.0F;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        switch (meta)
        {
        case 0:
        case 6:
            return EnumSortCategoryItem.INGOT;
        case 2:
        case 3:
            return EnumSortCategoryItem.PLATE;
        }
        return EnumSortCategoryItem.GENERAL;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        if (entityItem.getItem().getItemDamage() == 8)
        {
            entityItem.motionY += TransformerHooks.getItemGravity(entityItem);
        }
        return false;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == 4 ? 400 : -1;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "illenium_ingot", "setrorium_shard", "compressed_illenium", "compressed_setrorium", "infected_crystallize_shard", "alien_miner_part", "glowing_iron_ingot", "black_hole_fragment", "anti_gravity_fragment" };
    }

    public static enum ItemType
    {
        ILLENIUM_INGOT,
        SETRORIUM_SHARD,
        COMPRESSED_ILLENIUM,
        COMPRESSED_SETRORIUM,
        INFECTED_CRYSTALLIZE_SHARD,
        ALIEN_MINER_PART,
        GLOWING_IRON_INGOT,
        BLACK_HOLE_FRAGMENT,
        ANTI_GRAVITY_FRAGMENT;
    }
}
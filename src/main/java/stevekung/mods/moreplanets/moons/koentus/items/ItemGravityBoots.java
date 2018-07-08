package stevekung.mods.moreplanets.moons.koentus.items;

import micdoodle8.mods.galacticraft.api.item.IArmorGravity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.armor.ItemArmorMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class ItemGravityBoots extends ItemArmorMP implements IArmorGravity, IItemRarity
{
    public ItemGravityBoots(String name, ArmorMaterial material)
    {
        super(material, EntityEquipmentSlot.FEET);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return "moreplanets:textures/model/armor/gravity_boots.png";
    }

    @Override
    public int gravityOverrideIfLow(EntityPlayer player)
    {
        return 50;
    }

    @Override
    public int gravityOverrideIfHigh(EntityPlayer player)
    {
        return 75;
    }

    @Override
    protected Item getRepairItem()
    {
        return null;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.OTHER_TOOL;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToRGB(IItemRarity.SPECIAL);
    }
}
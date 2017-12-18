package stevekung.mods.moreplanets.module.planets.nibiru.items.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;
import stevekung.mods.moreplanets.util.items.armor.ItemArmorMP;

public class ItemArmorMultalicCrystal extends ItemArmorMP
{
    public ItemArmorMultalicCrystal(String name, ArmorMaterial material, int type)
    {
        super(material, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String layer)
    {
        if (itemStack.getItem() == NibiruItems.MULTALIC_CRYSTAL_HELMET || itemStack.getItem() == NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE || itemStack.getItem() == NibiruItems.MULTALIC_CRYSTAL_BOOTS)
        {
            return "moreplanets:textures/model/armor/multalic_crystal_1.png";
        }
        else if (itemStack.getItem() == NibiruItems.MULTALIC_CRYSTAL_LEGGINGS)
        {
            return "moreplanets:textures/model/armor/multalic_crystal_2.png";
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot, ModelBiped defaultModel)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            return ClientRegisterHelper.getTranclucentArmorModel(armorSlot, defaultModel);
        }
        return defaultModel;
    }

    @Override
    public Item getRepairItems()
    {
        return NibiruItems.NIBIRU_ITEM;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return 1;
    }
}
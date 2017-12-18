package stevekung.mods.moreplanets.items.capsule;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class CapsuleType
{
    public static ItemStack getInfectedProtectionCapsule()
    {
        ItemStack itemStack = new ItemStack(MPItems.CAPSULE, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("InfectedProtection", true);
        nbt.setInteger("Color", ColorHelper.rgbToDecimal(232, 62, 19));
        itemStack.setTagCompound(nbt);
        return itemStack;
    }

    public static ItemStack getDarkEnergyProtectionCapsule()
    {
        ItemStack itemStack = new ItemStack(MPItems.CAPSULE, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("DarkEnergyProtection", true);
        nbt.setInteger("Color", ColorHelper.rgbToDecimal(75, 75, 75));
        itemStack.setTagCompound(nbt);
        return itemStack;
    }
}
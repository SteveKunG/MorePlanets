package stevekung.mods.moreplanets.items;

import java.util.List;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemCreativeSpaceKit extends ItemBaseMP
{
    public ItemCreativeSpaceKit(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean advanced)
    {
        list.add(TextFormatting.RED + GCCoreUtil.translate("gui.creative_only.desc"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (player instanceof EntityPlayerMP)
        {
            GCPlayerStats stats = GCPlayerStats.get(player);
            stats.getExtendedInventory().setInventorySlotContents(0, new ItemStack(GCItems.oxMask)); //Oxygen Mask
            stats.getExtendedInventory().setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear)); //Oxygen Gear
            stats.getExtendedInventory().setInventorySlotContents(2, new ItemStack(GCItems.oxygenCanisterInfinite)); //Creative Oxygen Tank
            stats.getExtendedInventory().setInventorySlotContents(3, new ItemStack(GCItems.oxygenCanisterInfinite)); //Creative Oxygen Tank
            stats.getExtendedInventory().setInventorySlotContents(4, new ItemStack(GCItems.parachute)); //Parachute
            stats.getExtendedInventory().setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19)); //Frequency Module
            stats.getExtendedInventory().setInventorySlotContents(6, new ItemStack(VenusItems.thermalPaddingTier2, 1, 0)); //Thermal Armor Tier 2
            stats.getExtendedInventory().setInventorySlotContents(7, new ItemStack(VenusItems.thermalPaddingTier2, 1, 1)); //Thermal Armor Tier 2
            stats.getExtendedInventory().setInventorySlotContents(8, new ItemStack(VenusItems.thermalPaddingTier2, 1, 2)); //Thermal Armor Tier 2
            stats.getExtendedInventory().setInventorySlotContents(9, new ItemStack(VenusItems.thermalPaddingTier2, 1, 3)); //Thermal Armor Tier 2
            stats.getExtendedInventory().setInventorySlotContents(10, new ItemStack(VenusItems.basicItem, 1, 0)); //Shield Controller
        }
        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public String getName()
    {
        return "creative_space_kit";
    }
}
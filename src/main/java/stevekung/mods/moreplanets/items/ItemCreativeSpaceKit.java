package stevekung.mods.moreplanets.items;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.client.util.ITooltipFlag;
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
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemCreativeSpaceKit extends ItemBaseMP
{
    public ItemCreativeSpaceKit(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        list.add(TextFormatting.RED + LangUtils.translate("gui.creative_only.desc"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (player instanceof EntityPlayerMP)
        {
            GCPlayerStats stats = GCPlayerStats.get(player);
            InventoryExtended inv = stats.getExtendedInventory();
            inv.setInventorySlotContents(0, new ItemStack(GCItems.oxMask)); //Oxygen Mask
            inv.setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear)); //Oxygen Gear
            inv.setInventorySlotContents(2, new ItemStack(GCItems.oxygenCanisterInfinite)); //Creative Oxygen Tank
            inv.setInventorySlotContents(3, new ItemStack(GCItems.oxygenCanisterInfinite)); //Creative Oxygen Tank
            inv.setInventorySlotContents(4, new ItemStack(GCItems.parachute)); //Parachute
            inv.setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19)); //Frequency Module
            inv.setInventorySlotContents(6, new ItemStack(VenusItems.thermalPaddingTier2, 1, 0)); //Thermal Armor Tier 2
            inv.setInventorySlotContents(7, new ItemStack(VenusItems.thermalPaddingTier2, 1, 1)); //Thermal Armor Tier 2
            inv.setInventorySlotContents(8, new ItemStack(VenusItems.thermalPaddingTier2, 1, 2)); //Thermal Armor Tier 2
            inv.setInventorySlotContents(9, new ItemStack(VenusItems.thermalPaddingTier2, 1, 3)); //Thermal Armor Tier 2
            inv.setInventorySlotContents(10, new ItemStack(VenusItems.basicItem, 1, 0)); //Shield Controller
        }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
}
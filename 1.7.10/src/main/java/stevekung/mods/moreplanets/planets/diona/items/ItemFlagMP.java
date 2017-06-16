/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items;

import java.util.Calendar;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityFlagMP;

public class ItemFlagMP extends ItemBaseMP implements IHoldableItem
{
    private Calendar calendar = Calendar.getInstance();

    public ItemFlagMP(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.setTextureName("mpcore:blank");

        if (this.calendar.get(2) + 1 >= 1 && this.calendar.get(5) >= 1)
        {
            this.setHasSubtypes(true);
        }
        else
        {
            this.setHasSubtypes(false);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        if (!ConfigManagerMP.enableThaiFlagAndCanvas)
        {
            return null;
        }
        else
        {
            return MorePlanetsCore.mpItemsTab;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        if (this.calendar.get(2) + 1 >= 1 && this.calendar.get(5) >= 1)
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
        else
        {
            for (int i = 0; i < 1; i++)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
    {
        int useTime = this.getMaxItemUseDuration(itemStack) - tick;
        boolean placed = false;
        MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(world, player, true);
        float var7 = useTime / 20.0F;

        var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

        if (var7 > 1.0F)
        {
            var7 = 1.0F;
        }

        if (var7 == 1.0F && var12 != null && var12.typeOfHit == MovingObjectType.BLOCK)
        {
            int x = var12.blockX;
            int y = var12.blockY;
            int z = var12.blockZ;

            if (!world.isRemote)
            {
                EntityFlagMP flag = new EntityFlagMP(world, x + 0.5F, y + 1.0F, z + 0.5F, (int) (player.rotationYaw - 90));

                if (world.getEntitiesWithinAABB(EntityFlagMP.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 3, z + 1)).isEmpty())
                {
                    world.spawnEntityInWorld(flag);
                    flag.setType(itemStack.getItemDamage());
                    placed = true;
                }
                else
                {
                    player.addChatMessage(new ChatComponentText(GCCoreUtil.translate("gui.flag.alreadyPlaced")));
                }
            }

            if (placed)
            {
                int var2 = this.getInventorySlotContainItem(player, itemStack);

                if (var2 >= 0 && !player.capabilities.isCreativeMode)
                {
                    if (--player.inventory.mainInventory[var2].stackSize <= 0)
                    {
                        player.inventory.mainInventory[var2] = null;
                    }
                }
            }
        }
    }

    private int getInventorySlotContainItem(EntityPlayer player, ItemStack itemStack)
    {
        for (int var2 = 0; var2 < player.inventory.mainInventory.length; ++var2)
        {
            if (player.inventory.mainInventory[var2] != null && player.inventory.mainInventory[var2].isItemEqual(itemStack))
            {
                return var2;
            }
        }
        return -1;
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.none;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    @Override
    public boolean shouldHoldLeftHandUp(EntityPlayer player)
    {
        return false;
    }

    @Override
    public boolean shouldHoldRightHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldCrouch(EntityPlayer player)
    {
        return false;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "thai", "laos", "singapore", "myanmar", "malaysia", "vietnam", "indonesia", "philippines", "cambodia", "brunei" };
    }

    @Override
    public String getTexturesFolder()
    {
        return null;
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.armor;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.armor.ItemArmorMP;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ArmorUranium extends ItemArmorMP
{
    public ArmorUranium(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == KapteynBArmorItems.uranium_helmet || stack.getItem() == KapteynBArmorItems.uranium_chestplate || stack.getItem() == KapteynBArmorItems.uranium_boots)
        {
            return "kapteynb:textures/model/armor/uranium_1.png";
        }
        if (stack.getItem() == KapteynBArmorItems.uranium_leggings)
        {
            return "kapteynb:textures/model/armor/uranium_2.png";
        }
        return null;
    }

    @Override
    public String getTextureLocation()
    {
        return "kapteynb";
    }

    @Override
    public Item getRepairItems()
    {
        return KapteynBItems.kapteyn_b_item;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        if (player.worldObj.isRemote)
        {
            if (itemStack.getItem() == KapteynBArmorItems.uranium_helmet)
            {
                list.add(EnumChatFormatting.GRAY + "Ice Crystal Helmet Upgrade Required : 24");
            }
            else if (itemStack.getItem() == KapteynBArmorItems.uranium_chestplate)
            {
                list.add(EnumChatFormatting.GRAY + "Ice Crystal Chestplate Upgrade Required : 32");
            }
            else if (itemStack.getItem() == KapteynBArmorItems.uranium_leggings)
            {
                list.add(EnumChatFormatting.GRAY + "Ice Crystal Leggings Upgrade Required : 27");
            }
            else if (itemStack.getItem() == KapteynBArmorItems.uranium_boots)
            {
                list.add(EnumChatFormatting.GRAY + "Ice Crystal Boots Upgrade Required : 22");
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        ItemStack itemStack = item.getEntityItem();
        World world = item.worldObj;
        List<EntityItem> item1 = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(item.posX, item.posY, item.posZ, item.posX + 1, item.posY + 1, item.posZ + 1));

        if (item.worldObj.getBlock((int)Math.floor(item.posX), (int)Math.floor(item.posY), (int)Math.floor(item.posZ)) == KapteynBBlocks.frozen_water)
        {
            int stackSize = 0;

            if (item1.get(0).getEntityItem().getItem() == KapteynBItems.kapteyn_b_item && item1.get(0).getEntityItem().getItemDamage() == 5)
            {
                if (item1.get(0).getEntityItem().stackSize >= 24 && itemStack.getItem() == KapteynBArmorItems.uranium_helmet)
                {
                    item.setEntityItemStack(new ItemStack(KapteynBArmorItems.ice_crystal_helmet));
                    stackSize = 24;
                }
                else if (item1.get(0).getEntityItem().stackSize >= 32 && itemStack.getItem() == KapteynBArmorItems.uranium_chestplate)
                {
                    item.setEntityItemStack(new ItemStack(KapteynBArmorItems.ice_crystal_chestplate));
                    stackSize = 32;
                }
                else if (item1.get(0).getEntityItem().stackSize >= 27 && itemStack.getItem() == KapteynBArmorItems.uranium_leggings)
                {
                    item.setEntityItemStack(new ItemStack(KapteynBArmorItems.ice_crystal_leggings));
                    stackSize = 27;
                }
                else if (item1.get(0).getEntityItem().stackSize >= 22 && itemStack.getItem() == KapteynBArmorItems.uranium_boots)
                {
                    item.setEntityItemStack(new ItemStack(KapteynBArmorItems.ice_crystal_boots));
                    stackSize = 22;
                }
                item1.get(0).setEntityItemStack(new ItemStack(KapteynBItems.kapteyn_b_item, item1.get(0).getEntityItem().stackSize - stackSize, 5));
            }
        }
        return false;
    }
}
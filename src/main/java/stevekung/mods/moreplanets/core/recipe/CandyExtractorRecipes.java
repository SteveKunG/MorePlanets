/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.recipe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CandyExtractorRecipes
{
    private static CandyExtractorRecipes extractingBase = new CandyExtractorRecipes();
    private Map extractingList = new HashMap();
    private Map experienceList = new HashMap();

    public static CandyExtractorRecipes extracting()
    {
        return CandyExtractorRecipes.extractingBase;
    }

    public void blockToItemStack(Block block, ItemStack itemStack, float exp)
    {
        this.itemToItemStack(Item.getItemFromBlock(block), itemStack, exp);
    }

    public void itemToItemStack(Item item, ItemStack itemStack, float exp)
    {
        this.itemStackToItemStack(new ItemStack(item, 1, 32767), itemStack, exp);
    }

    public void itemStackToItemStack(ItemStack itemStack1, ItemStack itemStack2, float exp)
    {
        this.extractingList.put(itemStack1, itemStack2);
        this.experienceList.put(itemStack2, Float.valueOf(exp));
    }

    public ItemStack getExtractingResult(ItemStack itemStack)
    {
        Iterator iterator = this.extractingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }
            entry = (Entry)iterator.next();
        }
        while (!this.getStack(itemStack, (ItemStack)entry.getKey()));
        return (ItemStack)entry.getValue();
    }

    private boolean getStack(ItemStack itemStack1, ItemStack itemStack2)
    {
        return itemStack2.getItem() == itemStack1.getItem() && (itemStack2.getItemDamage() == 32767 || itemStack2.getItemDamage() == itemStack1.getItemDamage());
    }

    public Map getExtractingList()
    {
        return this.extractingList;
    }

    public float getSlot(ItemStack itemStack)
    {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);
        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        if (ret != -1.0F)
        {
            return ret;
        }
        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }
            entry = (Entry)iterator.next();
        }
        while (!this.getStack(itemStack, (ItemStack)entry.getKey()));
        return ((Float)entry.getValue()).floatValue();
    }
}
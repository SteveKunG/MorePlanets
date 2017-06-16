/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class ItemCheeseOfMilkBucket extends ItemBucket
{
    public ItemCheeseOfMilkBucket(String name)
    {
        super(PolongniusBlocks.cheese_of_milk);
        this.setUnlocalizedName(name);
        this.setContainerItem(Items.bucket);
        this.setTextureName("polongnius:cheese_of_milk_bucket");
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpItemsTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }
}
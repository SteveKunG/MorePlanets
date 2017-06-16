/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.blocks.BlockDungeonBrickSlab1;

public class ItemBlockDungeonBrickSlab1 extends ItemSlab
{
    public ItemBlockDungeonBrickSlab1(Block block, BlockDungeonBrickSlab1 singleSlab, BlockDungeonBrickSlab1 doubleSlab)
    {
        super(block, singleSlab, doubleSlab, block == doubleSlab);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta & 7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        BlockDungeonBrickSlab1 slab = (BlockDungeonBrickSlab1)Block.getBlockFromItem(itemStack.getItem());
        return super.getUnlocalizedName() + "." + new StringBuilder().append(slab.func_150002_b(itemStack.getItemDamage())).toString();
    }
}
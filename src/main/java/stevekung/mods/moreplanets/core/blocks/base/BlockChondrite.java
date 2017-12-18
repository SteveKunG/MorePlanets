/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks.base;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockChondrite extends BlockBaseMP
{
    private IIcon[] chondriteBlockIcon;

    public BlockChondrite(String name)
    {
        super(Material.rock);
        this.setHardness(1.5F);
        this.setResistance(9.0F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.chondriteBlockIcon = new IIcon[16];
        this.chondriteBlockIcon[0] = par1IconRegister.registerIcon("mpcore:chondrite_rock");
        this.chondriteBlockIcon[1] = par1IconRegister.registerIcon("mpcore:polished_chondrite");
        this.chondriteBlockIcon[2] = par1IconRegister.registerIcon("mpcore:chondrite_stone_brick");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.chondriteBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 3; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
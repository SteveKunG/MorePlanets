/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockPolishedSpaceDecoration extends BlockBaseMP
{
    private IIcon[] spaceBlockIcon;

    public BlockPolishedSpaceDecoration(String name)
    {
        super(Material.rock);
        this.setHardness(1.0F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.spaceBlockIcon = new IIcon[16];
        this.spaceBlockIcon[0] = par1IconRegister.registerIcon("mpcore:polished_tin_decoration_block");
        this.spaceBlockIcon[1] = par1IconRegister.registerIcon("mpcore:polished_aluminum_decoration_block");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.spaceBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
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
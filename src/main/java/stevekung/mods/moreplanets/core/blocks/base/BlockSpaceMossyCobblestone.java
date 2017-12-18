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
import net.minecraft.world.IBlockAccess;

public class BlockSpaceMossyCobblestone extends BlockBaseMP
{
    private IIcon[] mossyBlockIcon;

    public BlockSpaceMossyCobblestone(String name)
    {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setBlockName(name);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 5 ? 15 : 0;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.mossyBlockIcon = new IIcon[16];
        this.mossyBlockIcon[0] = par1IconRegister.registerIcon("mpcore:mossy_diona_cobblestone");
        this.mossyBlockIcon[1] = par1IconRegister.registerIcon("mpcore:mossy_polongnius_cobblestone");
        this.mossyBlockIcon[2] = par1IconRegister.registerIcon("mpcore:mossy_nibiru_cobblestone");
        this.mossyBlockIcon[3] = par1IconRegister.registerIcon("mpcore:mossy_koentus_cobblestone");
        this.mossyBlockIcon[4] = par1IconRegister.registerIcon("mpcore:mossy_kapteyn_b_cobblestone");
        this.mossyBlockIcon[5] = par1IconRegister.registerIcon("mpcore:mossy_sirius_b_cobblestone");
        this.mossyBlockIcon[6] = par1IconRegister.registerIcon("mpcore:mossy_venus_cobblestone");
        this.mossyBlockIcon[7] = par1IconRegister.registerIcon("mpcore:mossy_mercury_cobblestone");
        this.mossyBlockIcon[8] = par1IconRegister.registerIcon("mpcore:mossy_pluto_cobblestone");
        this.mossyBlockIcon[9] = par1IconRegister.registerIcon("mpcore:mossy_martian_cobblestone");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.mossyBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 10; ++i)
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
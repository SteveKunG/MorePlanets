/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.blocks.BlockCakeMP;

public class BlockPinkCake extends BlockCakeMP
{
    private IIcon[] pinkCakeIcons;

    public BlockPinkCake(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.pinkCakeIcons = new IIcon[4];
        this.pinkCakeIcons[0] = par1IconRegister.registerIcon("fronos:cake_pink_top");
        this.pinkCakeIcons[1] = par1IconRegister.registerIcon("fronos:cake_bottom");
        this.pinkCakeIcons[2] = par1IconRegister.registerIcon("fronos:cake_pink_inner");
        this.pinkCakeIcons[3] = par1IconRegister.registerIcon("fronos:cake_pink_side");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.pinkCakeIcons[0] : par1 == 0 ? this.pinkCakeIcons[1] : par2 > 0 && par1 == 4 ? this.pinkCakeIcons[2] : this.pinkCakeIcons[3];
    }

    @Override
    public int getFoodAmount()
    {
        return 3;
    }

    @Override
    public float getSaturationAmount()
    {
        return 0.5F;
    }
}
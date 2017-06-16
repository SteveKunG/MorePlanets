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

public class BlockWhiteCake extends BlockCakeMP
{
    private IIcon[] chocolateCakeIcons;

    public BlockWhiteCake(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.chocolateCakeIcons = new IIcon[4];
        this.chocolateCakeIcons[0] = par1IconRegister.registerIcon("fronos:white_cake_top");
        this.chocolateCakeIcons[1] = par1IconRegister.registerIcon("fronos:white_cake_bottom");
        this.chocolateCakeIcons[2] = par1IconRegister.registerIcon("fronos:white_cake_inner");
        this.chocolateCakeIcons[3] = par1IconRegister.registerIcon("fronos:white_cake_side2");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.chocolateCakeIcons[0] : par1 == 0 ? this.chocolateCakeIcons[1] : par2 > 0 && par1 == 4 ? this.chocolateCakeIcons[2] : this.chocolateCakeIcons[3];
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
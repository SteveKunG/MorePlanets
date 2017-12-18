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

public class BlockChocolateCakeBread extends BlockCakeMP
{
    private IIcon[] cakeBlockIcons;

    public BlockChocolateCakeBread(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.cakeBlockIcons = new IIcon[4];
        this.cakeBlockIcons[0] = par1IconRegister.registerIcon("fronos:chocolate_cake_bottom");
        this.cakeBlockIcons[1] = par1IconRegister.registerIcon("fronos:chocolate_cake_bottom");
        this.cakeBlockIcons[2] = par1IconRegister.registerIcon("fronos:chocolate_cake_de_block_side");
        this.cakeBlockIcons[3] = par1IconRegister.registerIcon("fronos:chocolate_cake_de_block_side");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.cakeBlockIcons[0] : par1 == 0 ? this.cakeBlockIcons[1] : par2 > 0 && par1 == 4 ? this.cakeBlockIcons[2] : this.cakeBlockIcons[3];
    }

    @Override
    public int getFoodAmount()
    {
        return 2;
    }

    @Override
    public float getSaturationAmount()
    {
        return 0.3F;
    }
}
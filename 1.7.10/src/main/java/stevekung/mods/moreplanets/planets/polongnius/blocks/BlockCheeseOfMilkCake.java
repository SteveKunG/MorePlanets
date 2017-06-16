/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc.IBlockShiftDesc;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.blocks.BlockCakeMP;

public class BlockCheeseOfMilkCake extends BlockCakeMP implements IBlockShiftDesc
{
    private IIcon[] cheeseOfMilkIcons;

    public BlockCheeseOfMilkCake(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.cheeseOfMilkIcons = new IIcon[3];
        this.cheeseOfMilkIcons[0] = par1IconRegister.registerIcon("polongnius:cheese_of_milk_1");
        this.cheeseOfMilkIcons[1] = par1IconRegister.registerIcon("polongnius:cheese_of_milk_2");
        this.cheeseOfMilkIcons[2] = par1IconRegister.registerIcon("polongnius:cheese_of_milk_3");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.cheeseOfMilkIcons[0] : par1 == 0 ? this.cheeseOfMilkIcons[0] : par2 > 0 && par1 == 4 ? this.cheeseOfMilkIcons[2] : this.cheeseOfMilkIcons[1];
    }

    @Override
    public String getShiftDescription(int meta)
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
    }

    @Override
    public boolean showDescription(int meta)
    {
        return true;
    }

    @Override
    public int getFoodAmount()
    {
        return 4;
    }

    @Override
    public float getSaturationAmount()
    {
        return 0.6F;
    }
}
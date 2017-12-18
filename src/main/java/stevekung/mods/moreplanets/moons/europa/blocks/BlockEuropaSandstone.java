/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockEuropaSandstone extends BlockBaseMP
{
    private IIcon[] sandstoneIcon;

    public BlockEuropaSandstone(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
        this.setHardness(0.8F);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.sandstoneIcon = new IIcon[16];
        this.sandstoneIcon[0] = par1IconRegister.registerIcon("europa:europa_sandstone");
        this.sandstoneIcon[1] = par1IconRegister.registerIcon("europa:europa_sandstone_top");
        this.sandstoneIcon[2] = par1IconRegister.registerIcon("europa:europa_sandstone_bottom");
        this.sandstoneIcon[3] = par1IconRegister.registerIcon("europa:europa_sandstone_chiseled");
        this.sandstoneIcon[4] = par1IconRegister.registerIcon("europa:europa_sandstone_smooth");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            switch (side)
            {
            case 0:
                return this.sandstoneIcon[2]; //BOTTOM
            case 1:
                return this.sandstoneIcon[1]; //TOP
            case 2:
                return this.sandstoneIcon[0]; //Z-
            case 3:
                return this.sandstoneIcon[0]; //Z+
            case 4:
                return this.sandstoneIcon[0]; //X-
            case 5:
                return this.sandstoneIcon[0]; //X+
            }
        }
        else if (meta == 1)
        {
            switch (side)
            {
            case 0:
                return this.sandstoneIcon[1]; //BOTTOM
            case 1:
                return this.sandstoneIcon[1]; //TOP
            case 2:
                return this.sandstoneIcon[3]; //Z-
            case 3:
                return this.sandstoneIcon[3]; //Z+
            case 4:
                return this.sandstoneIcon[3]; //X-
            case 5:
                return this.sandstoneIcon[3]; //X+
            }
        }
        else if (meta == 2)
        {
            switch (side)
            {
            case 0:
                return this.sandstoneIcon[1]; //BOTTOM
            case 1:
                return this.sandstoneIcon[1]; //TOP
            case 2:
                return this.sandstoneIcon[4]; //Z-
            case 3:
                return this.sandstoneIcon[4]; //Z+
            case 4:
                return this.sandstoneIcon[4]; //X-
            case 5:
                return this.sandstoneIcon[4]; //X+
            }
        }
        return this.sandstoneIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
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
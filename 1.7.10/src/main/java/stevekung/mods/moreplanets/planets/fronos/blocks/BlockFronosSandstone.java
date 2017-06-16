/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

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

public class BlockFronosSandstone extends BlockBaseMP
{
    private IIcon[] fronosSandstoneIcon;

    public BlockFronosSandstone(String name)
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
        this.fronosSandstoneIcon = new IIcon[16];
        this.fronosSandstoneIcon[0] = par1IconRegister.registerIcon("fronos:fronos_sandstone");
        this.fronosSandstoneIcon[1] = par1IconRegister.registerIcon("fronos:fronos_sandstone_top");
        this.fronosSandstoneIcon[2] = par1IconRegister.registerIcon("fronos:fronos_sandstone_bottom");
        this.fronosSandstoneIcon[3] = par1IconRegister.registerIcon("fronos:fronos_sandstone_chiseled");
        this.fronosSandstoneIcon[4] = par1IconRegister.registerIcon("fronos:fronos_sandstone_smooth");
        this.fronosSandstoneIcon[5] = par1IconRegister.registerIcon("fronos:white_sandstone");
        this.fronosSandstoneIcon[6] = par1IconRegister.registerIcon("fronos:white_sandstone_top");
        this.fronosSandstoneIcon[7] = par1IconRegister.registerIcon("fronos:white_sandstone_bottom");
        this.fronosSandstoneIcon[8] = par1IconRegister.registerIcon("fronos:white_sandstone_chiseled");
        this.fronosSandstoneIcon[9] = par1IconRegister.registerIcon("fronos:white_sandstone_smooth");
        this.fronosSandstoneIcon[10] = par1IconRegister.registerIcon("fronos:cheese_sandstone");
        this.fronosSandstoneIcon[11] = par1IconRegister.registerIcon("fronos:cheese_sandstone_top");
        this.fronosSandstoneIcon[12] = par1IconRegister.registerIcon("fronos:cheese_sandstone_bottom");
        this.fronosSandstoneIcon[13] = par1IconRegister.registerIcon("fronos:cheese_sandstone_chiseled");
        this.fronosSandstoneIcon[14] = par1IconRegister.registerIcon("fronos:cheese_sandstone_smooth");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[2]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[1]; //TOP
            case 2:
                return this.fronosSandstoneIcon[0]; //Z-
            case 3:
                return this.fronosSandstoneIcon[0]; //Z+
            case 4:
                return this.fronosSandstoneIcon[0]; //X-
            case 5:
                return this.fronosSandstoneIcon[0]; //X+
            }
        }
        else if (meta == 1)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[1]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[1]; //TOP
            case 2:
                return this.fronosSandstoneIcon[3]; //Z-
            case 3:
                return this.fronosSandstoneIcon[3]; //Z+
            case 4:
                return this.fronosSandstoneIcon[3]; //X-
            case 5:
                return this.fronosSandstoneIcon[3]; //X+
            }
        }
        else if (meta == 2)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[1]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[1]; //TOP
            case 2:
                return this.fronosSandstoneIcon[4]; //Z-
            case 3:
                return this.fronosSandstoneIcon[4]; //Z+
            case 4:
                return this.fronosSandstoneIcon[4]; //X-
            case 5:
                return this.fronosSandstoneIcon[4]; //X+
            }
        }
        else if (meta == 3)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[7]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[6]; //TOP
            case 2:
                return this.fronosSandstoneIcon[5]; //Z-
            case 3:
                return this.fronosSandstoneIcon[5]; //Z+
            case 4:
                return this.fronosSandstoneIcon[5]; //X-
            case 5:
                return this.fronosSandstoneIcon[5]; //X+
            }
        }
        else if (meta == 4)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[6]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[6]; //TOP
            case 2:
                return this.fronosSandstoneIcon[8]; //Z-
            case 3:
                return this.fronosSandstoneIcon[8]; //Z+
            case 4:
                return this.fronosSandstoneIcon[8]; //X-
            case 5:
                return this.fronosSandstoneIcon[8]; //X+
            }
        }
        else if (meta == 5)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[6]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[6]; //TOP
            case 2:
                return this.fronosSandstoneIcon[9]; //Z-
            case 3:
                return this.fronosSandstoneIcon[9]; //Z+
            case 4:
                return this.fronosSandstoneIcon[9]; //X-
            case 5:
                return this.fronosSandstoneIcon[9]; //X+
            }
        }
        else if (meta == 6)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[12]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[11]; //TOP
            case 2:
                return this.fronosSandstoneIcon[10]; //Z-
            case 3:
                return this.fronosSandstoneIcon[10]; //Z+
            case 4:
                return this.fronosSandstoneIcon[10]; //X-
            case 5:
                return this.fronosSandstoneIcon[10]; //X+
            }
        }
        else if (meta == 7)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[11]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[11]; //TOP
            case 2:
                return this.fronosSandstoneIcon[13]; //Z-
            case 3:
                return this.fronosSandstoneIcon[13]; //Z+
            case 4:
                return this.fronosSandstoneIcon[13]; //X-
            case 5:
                return this.fronosSandstoneIcon[13]; //X+
            }
        }
        else if (meta == 8)
        {
            switch (side)
            {
            case 0:
                return this.fronosSandstoneIcon[11]; //BOTTOM
            case 1:
                return this.fronosSandstoneIcon[11]; //TOP
            case 2:
                return this.fronosSandstoneIcon[14]; //Z-
            case 3:
                return this.fronosSandstoneIcon[14]; //Z+
            case 4:
                return this.fronosSandstoneIcon[14]; //X-
            case 5:
                return this.fronosSandstoneIcon[14]; //X+
            }
        }
        return this.fronosSandstoneIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 9; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
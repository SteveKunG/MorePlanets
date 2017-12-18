/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockSiriusRedstoneLamp extends Block
{
    private boolean isLight;
    private IIcon[] lampIcon;

    public BlockSiriusRedstoneLamp(String name, boolean light)
    {
        super(Material.redstoneLight);
        this.isLight = light;
        this.setBlockName(name);
        this.setStepSound(soundTypeGlass);

        if (light)
        {
            this.setLightLevel(1.0F);
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.lampIcon = new IIcon[2];
        this.lampIcon[0] = par1IconRegister.registerIcon("siriusb:sirius_redstone_lamp_off");
        this.lampIcon[1] = par1IconRegister.registerIcon("siriusb:sirius_redstone_lamp_on");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (this.isLight)
        {
            return this.lampIcon[1];
        }
        return this.lampIcon[0];
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        if (this.isLight)
        {
            return null;
        }
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            if (this.isLight && !world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.scheduleBlockUpdate(x, y, z, this, 4);
            }
            else if (!this.isLight && world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.setBlock(x, y, z, SiriusBBlocks.sirius_redstone_lamp_on, 0, 2);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (!world.isRemote)
        {
            if (this.isLight && !world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.scheduleBlockUpdate(x, y, z, this, 4);
            }
            else if (!this.isLight && world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.setBlock(x, y, z, SiriusBBlocks.sirius_redstone_lamp_on, 0, 2);
            }
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote && this.isLight && !world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            world.setBlock(x, y, z, SiriusBBlocks.sirius_redstone_lamp_off, 0, 2);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        if (this.isLight)
        {
            return Item.getItemFromBlock(SiriusBBlocks.sirius_redstone_lamp_off);
        }
        return Item.getItemFromBlock(SiriusBBlocks.sirius_redstone_lamp_off);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(SiriusBBlocks.sirius_redstone_lamp_off);
    }

    @Override
    protected ItemStack createStackedBlock(int meta)
    {
        return new ItemStack(SiriusBBlocks.sirius_redstone_lamp_off);
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFronosSand extends BlockFalling
{
    private IIcon[] sandIcon;

    public BlockFronosSand(String name)
    {
        super();
        this.setStepSound(Block.soundTypeSand);
        this.setHardness(0.5F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.sandIcon = new IIcon[3];
        this.sandIcon[0] = par1IconRegister.registerIcon("fronos:fronos_sand");
        this.sandIcon[1] = par1IconRegister.registerIcon("fronos:white_sand");
        this.sandIcon[2] = par1IconRegister.registerIcon("fronos:cheese_sand");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.sandIcon[meta];
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 3; ++i)
        {
            list.add(new ItemStack(block, 1, i));
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
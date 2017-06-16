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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockCream extends BlockBaseMP
{
    private IIcon[] creamIcon;

    public BlockCream(String name)
    {
        super(Material.craftedSnow);
        this.setStepSound(Block.soundTypeSnow);
        this.setHardness(0.2F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.creamIcon = new IIcon[6];
        this.creamIcon[0] = par1IconRegister.registerIcon("fronos:vanilla_cream");
        this.creamIcon[1] = par1IconRegister.registerIcon("fronos:chocolate_cream");
        this.creamIcon[2] = par1IconRegister.registerIcon("fronos:strawberry_cream");
        this.creamIcon[3] = par1IconRegister.registerIcon("fronos:orange_cream");
        this.creamIcon[4] = par1IconRegister.registerIcon("fronos:tea_cream");
        this.creamIcon[5] = par1IconRegister.registerIcon("fronos:lemon_cream");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.creamIcon[meta];
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 6; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        return FronosItems.cream_ball;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 4;
    }
}
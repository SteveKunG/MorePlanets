/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;

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
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;

public class BlockCandyFlower extends BlockFlowerMP
{
    private static String[] plants = new String[] {
            "pink",
            "orange",
            "green",
            "yellow",
            "light_blue",
            "blue",
            "red",
            "purple"
    };

    private IIcon[] textures;

    public BlockCandyFlower(String name)
    {
        super(Material.plants);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockCandyFlower.plants.length];

        for (int i = 0; i < BlockCandyFlower.plants.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:" + BlockCandyFlower.plants[i] + "_candy_flower");
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= this.textures.length)
        {
            meta = 0;
        }
        return this.textures[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockCandyFlower.plants.length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int par5)
    {
        return world.getBlock(x, y - 1, z) == FronosBlocks.frosted_cake;
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
    {
        Block block = world.getBlock(x, y - 1, z);
        return block == FronosBlocks.frosted_cake;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
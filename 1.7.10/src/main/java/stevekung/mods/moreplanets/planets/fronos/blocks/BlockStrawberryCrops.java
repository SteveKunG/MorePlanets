/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockStrawberryCrops extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public BlockStrawberryCrops(String name)
    {
        super();
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName(name);
    }

    @Override
    protected Item func_149865_P()
    {
        return FronosItems.fronos_food;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 7)
        {
            if (par2 == 6)
            {
                par2 = 5;
            }
            return this.iconArray[par2 >> 1];
        }
        else
        {
            return this.iconArray[3];
        }
    }

    @Override
    protected Item func_149866_i()
    {
        return FronosItems.strawberry_seed;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == FronosBlocks.fronos_farmland;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata >= 7)
        {
            for (int i = 0; i < 4 + fortune; i++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(this.func_149865_P(), 1, 0));
                }
            }
            for (int i = 0; i < 2 + fortune; i++)
            {
                ret.add(new ItemStack(this.func_149866_i(), 1, 0));
            }
        }
        return ret;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[4];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("fronos:strawberry_bush" + "_" + i);
        }
    }
}
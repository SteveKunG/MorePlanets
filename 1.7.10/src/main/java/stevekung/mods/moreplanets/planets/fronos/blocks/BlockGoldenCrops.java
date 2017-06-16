/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockGoldenCrops extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public BlockGoldenCrops(String name)
    {
        super();
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        this.disableStats();
        this.setBlockName(name);
    }

    @Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == FronosBlocks.fronos_farmland;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);

            if (l < 7)
            {
                float f = this.getGrowthRate(par1World, par2, par3, par4);

                if (par5Random.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    ++l;
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                }
            }
        }
    }

    @Override
    public void func_149863_m(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

        if (l > 7)
        {
            l = 7;
        }
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    private float getGrowthRate(World par1World, int par2, int par3, int par4)
    {
        float f = 1.0F;
        Block l = par1World.getBlock(par2, par3, par4 - 1);
        Block i1 = par1World.getBlock(par2, par3, par4 + 1);
        Block j1 = par1World.getBlock(par2 - 1, par3, par4);
        Block k1 = par1World.getBlock(par2 + 1, par3, par4);
        Block l1 = par1World.getBlock(par2 - 1, par3, par4 - 1);
        Block i2 = par1World.getBlock(par2 + 1, par3, par4 - 1);
        Block j2 = par1World.getBlock(par2 + 1, par3, par4 + 1);
        Block k2 = par1World.getBlock(par2 - 1, par3, par4 + 1);
        boolean flag = j1 == this || k1 == this;
        boolean flag1 = l == this || i1 == this;
        boolean flag2 = l1 == this || i2 == this || j2 == this || k2 == this;

        for (int l2 = par2 - 1; l2 <= par2 + 1; ++l2)
        {
            for (int i3 = par4 - 1; i3 <= par4 + 1; ++i3)
            {
                Block j3 = par1World.getBlock(l2, par3 - 1, i3);
                float f1 = 0.0F;

                if (j3 != null && j3.canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if (j3.isFertile(par1World, l2, par3 - 1, i3))
                    {
                        f1 = 3.0F;
                    }
                }
                if (l2 != par2 || i3 != par4)
                {
                    f1 /= 4.0F;
                }
                f += f1;
            }
        }
        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }
        return f;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            par2 = 7;
        }
        return this.iconArray[par2];
    }

    @Override
    protected Item func_149866_i()
    {
        return FronosItems.golden_seeds;
    }

    @Override
    protected Item func_149865_P()
    {
        return FronosItems.fronos_item;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta == 7 ? 6 : 0;
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata >= 7)
        {
            for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(this.func_149866_i(), 1, 0));
                }
            }
        }
        return ret;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return par1 == 7 ? this.func_149865_P() : this.func_149866_i();
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return this.func_149866_i();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[8];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("fronos:golden_wheat_" + i);
        }
    }
}
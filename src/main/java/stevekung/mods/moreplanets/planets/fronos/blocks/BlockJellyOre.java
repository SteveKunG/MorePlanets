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
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockJellyOre extends BlockBaseMP implements IDetectableResource
{
    private IIcon[] fronosBlockIcon;

    public BlockJellyOre(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
        this.setHardness(3.0F);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.fronosBlockIcon = new IIcon[8];
        this.fronosBlockIcon[0] = par1IconRegister.registerIcon("fronos:grape_jelly_ore");
        this.fronosBlockIcon[1] = par1IconRegister.registerIcon("fronos:raspberry_jelly_ore");
        this.fronosBlockIcon[2] = par1IconRegister.registerIcon("fronos:strawberry_jelly_ore");
        this.fronosBlockIcon[3] = par1IconRegister.registerIcon("fronos:berry_jelly_ore");
        this.fronosBlockIcon[4] = par1IconRegister.registerIcon("fronos:lime_jelly_ore");
        this.fronosBlockIcon[5] = par1IconRegister.registerIcon("fronos:orange_jelly_ore");
        this.fronosBlockIcon[6] = par1IconRegister.registerIcon("fronos:green_jelly_ore");
        this.fronosBlockIcon[7] = par1IconRegister.registerIcon("fronos:lemon_jelly_ore");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.fronosBlockIcon[meta];
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return new ItemStack(this, 1, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 8; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        return FronosItems.jelly;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(meta, random, fortune))
        {
            int j = random.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return this.quantityDropped(random) * (j + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    @Override
    public boolean isValueable(int metadata)
    {
        return true;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this))
        {
            int var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
    }
}
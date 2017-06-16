/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockEuropaGeyser extends BlockBaseMP
{
    private IIcon[] geyserBlockIcon;

    public BlockEuropaGeyser(String name)
    {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.geyserBlockIcon = new IIcon[14];
        this.geyserBlockIcon[0] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_frozen_water_geyser");
        this.geyserBlockIcon[1] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_surface_ice");
        this.geyserBlockIcon[2] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_surface_ice");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            switch (side)
            {
            case 0:
                return this.geyserBlockIcon[2]; //BOTTOM
            case 1:
                return this.geyserBlockIcon[0]; //TOP
            case 2:
                return this.geyserBlockIcon[1]; //Z-
            case 3:
                return this.geyserBlockIcon[1]; //Z+
            case 4:
                return this.geyserBlockIcon[1]; //X-
            case 5:
                return this.geyserBlockIcon[1]; //X+
            default:
                return this.geyserBlockIcon[0];
            }
        }
        else
        {
            switch (side)
            {
            case 0:
                return this.geyserBlockIcon[2]; //BOTTOM
            case 1:
                return this.geyserBlockIcon[0]; //TOP
            case 2:
                return this.geyserBlockIcon[1]; //Z-
            case 3:
                return this.geyserBlockIcon[1]; //Z+
            case 4:
                return this.geyserBlockIcon[1]; //X-
            case 5:
                return this.geyserBlockIcon[1]; //X+
            default:
                return this.geyserBlockIcon[0];
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (!World.doesBlockHaveSolidTopSurface(world, x, y + 1, z))
        {
            if (world.getBlockMetadata(x, y, z) == 0)
            {
                for (int i = 0; i < 20; i++)
                {
                    world.spawnParticle("bubble", x + 0.5F, y + 0.5F + rand.nextFloat(), z + 0.5F, 0.0D + rand.nextDouble() - rand.nextDouble(), 0.0D + rand.nextFloat() + rand.nextInt(2), 0.0D + rand.nextDouble() - rand.nextDouble());
                }
            }
            else if (world.getBlockMetadata(x, y, z) == 1)
            {
                for (int i = 0; i < 20; i++)
                {
                    world.spawnParticle("bubble", x + 0.5F, y + 0.5F + rand.nextFloat(), z + 0.5F, 0.0D, 0.0D + rand.nextFloat() + rand.nextInt(2), 0.0D);
                    world.spawnParticle("smoke", x + 0.5F, y + 0.5F + rand.nextFloat(), z + 0.5F, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(EuropaBlocks.europa_geyser);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
    }
}
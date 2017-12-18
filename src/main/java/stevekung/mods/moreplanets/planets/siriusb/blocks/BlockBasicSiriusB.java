/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.siriusb.tileentities.TileEntitySiriusBDungeonSpawner;

public class BlockBasicSiriusB extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] siriusBBlockIcon;

    public BlockBasicSiriusB(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.siriusBBlockIcon = new IIcon[16];
        this.siriusBBlockIcon[0] = par1IconRegister.registerIcon("siriusb:sirius_b_surface_stone");
        this.siriusBBlockIcon[1] = par1IconRegister.registerIcon("siriusb:sirius_b_sub_surface_stone");
        this.siriusBBlockIcon[2] = par1IconRegister.registerIcon("siriusb:sirius_b_carbon_stone");
        this.siriusBBlockIcon[3] = par1IconRegister.registerIcon("siriusb:sirius_b_carbon_cobblestone");
        this.siriusBBlockIcon[4] = par1IconRegister.registerIcon("siriusb:sirius_b_sulfur_ore");
        this.siriusBBlockIcon[5] = par1IconRegister.registerIcon("siriusb:sirius_b_diamond_ore");
        this.siriusBBlockIcon[6] = par1IconRegister.registerIcon("siriusb:sirius_b_glowstone_ore");
        this.siriusBBlockIcon[7] = par1IconRegister.registerIcon("siriusb:sirius_black_spot");
        this.siriusBBlockIcon[8] = par1IconRegister.registerIcon("siriusb:sulfur_block");
        this.siriusBBlockIcon[9] = par1IconRegister.registerIcon("siriusb:sirius_b_dungeon_brick");
        this.siriusBBlockIcon[10] = par1IconRegister.registerIcon("galacticraftcore:blank");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 8)
        {
            return 0;
        }
        return 15;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.siriusBBlockIcon[meta];
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 10; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 9)
        {
            return 40.0F;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) == 8;
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        final int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0 || meta == 1 || meta == 2)
        {
            return 5.0F;
        }
        if (meta == 3)
        {
            return 4.5F;
        }
        if (meta >= 4 && meta <= 6 || meta == 8)
        {
            return 4.0F;
        }
        if (meta == 7)
        {
            return 3.5F;
        }
        if (meta == 9)
        {
            return 4.0F;
        }
        return 1.0F;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        if (meta == 2)
        {
            return Item.getItemFromBlock(SiriusBBlocks.sirius_b_block);
        }
        if (meta == 4)
        {
            return SiriusBItems.sirius_b_item;
        }
        if (meta == 5)
        {
            return Items.diamond;
        }
        if (meta == 6)
        {
            return SiriusBItems.sirius_glowstone_dust;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 2)
        {
            return 3;
        }
        if (meta == 4)
        {
            return 2;
        }
        if (meta == 5 || meta == 6)
        {
            return 0;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == 4)
        {
            return 3 + random.nextInt(5);
        }
        if (meta == 6)
        {
            return 2 + random.nextInt(8);
        }
        return super.quantityDropped(meta, fortune, random);
    }

    @Override
    public boolean isValueable(int meta)
    {
        if (meta >= 3 && meta <= 6)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTerraformable(World world, int x, int y, int z)
    {
        final int meta = world.getBlockMetadata(x, y, z);

        if ((meta == 0 || meta == 1 || meta == 7) && !world.getBlock(x, y + 1, z).isOpaqueCube())
        {
            return true;
        }
        return false;
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return 10;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 10;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntitySiriusBDungeonSpawner();
    }
}
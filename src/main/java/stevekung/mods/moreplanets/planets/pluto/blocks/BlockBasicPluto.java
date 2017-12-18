/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;
import stevekung.mods.moreplanets.planets.pluto.tileentities.TileEntityPlutoDungeonSpawner;

public class BlockBasicPluto extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] plutoBlockIcon;

    public BlockBasicPluto(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.plutoBlockIcon = new IIcon[14];
        this.plutoBlockIcon[0] = par1IconRegister.registerIcon("pluto:pluto_surface_rock");
        this.plutoBlockIcon[1] = par1IconRegister.registerIcon("pluto:pluto_sub_surface_rock");
        this.plutoBlockIcon[2] = par1IconRegister.registerIcon("pluto:pluto_rock");
        this.plutoBlockIcon[3] = par1IconRegister.registerIcon("pluto:pluto_cobblestone");
        this.plutoBlockIcon[4] = par1IconRegister.registerIcon("pluto:pluto_meteoric_iron_ore");
        this.plutoBlockIcon[5] = par1IconRegister.registerIcon("pluto:pluto_frozen_iron_ore");
        this.plutoBlockIcon[6] = par1IconRegister.registerIcon("pluto:pluto_iron_ore");
        this.plutoBlockIcon[7] = par1IconRegister.registerIcon("pluto:xeonium_gem_ore");
        this.plutoBlockIcon[8] = par1IconRegister.registerIcon("pluto:pluto_dungeon_brick");
        this.plutoBlockIcon[9] = par1IconRegister.registerIcon("galacticraftcore:blank");
        this.plutoBlockIcon[10] = par1IconRegister.registerIcon("pluto:pluto_surface_rock_brown");
        this.plutoBlockIcon[11] = par1IconRegister.registerIcon("pluto:pluto_surface_rock_light_brown");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.plutoBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 12; ++i)
        {
            if (i != 9)
            {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0 || meta == 1 || meta == 8)
        {
            return 4.0F;
        }
        if (meta == 2)
        {
            return 4.5F;
        }
        if (meta == 3)
        {
            return 4.25F;
        }
        if (meta >= 4 && meta <= 7)
        {
            return 5.0F;
        }
        return 4.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 8 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        if (meta == 4)
        {
            return GCItems.meteoricIronRaw;
        }
        if (meta == 7)
        {
            return PlutoItems.pluto_item;
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
        if (meta == 4 || meta == 7)
        {
            return 0;
        }
        return meta;
    }

    @Override
    public boolean isValueable(int metadata)
    {
        if (metadata >= 4 && metadata <= 7)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTerraformable(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if ((meta == 0 || meta == 1) && !world.getBlock(x, y + 1, z).isOpaqueCube())
        {
            return true;
        }
        return false;
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return 9;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 9;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityPlutoDungeonSpawner();
    }
}
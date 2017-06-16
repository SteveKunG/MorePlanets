/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.phobos.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
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

public class BlockBasicPhobos extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] phobosBlockIcon;

    public BlockBasicPhobos(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.phobosBlockIcon = new IIcon[9];
        this.phobosBlockIcon[0] = par1IconRegister.registerIcon("phobos:phobos_surface_rock");
        this.phobosBlockIcon[1] = par1IconRegister.registerIcon("phobos:phobos_sub_surface_rock");
        this.phobosBlockIcon[2] = par1IconRegister.registerIcon("phobos:phobos_rock");
        this.phobosBlockIcon[3] = par1IconRegister.registerIcon("phobos:phobos_cobblestone");
        this.phobosBlockIcon[4] = par1IconRegister.registerIcon("phobos:phobos_tin_ore");
        this.phobosBlockIcon[5] = par1IconRegister.registerIcon("phobos:phobos_copper_ore");
        this.phobosBlockIcon[6] = par1IconRegister.registerIcon("phobos:phobos_iron_ore");
        this.phobosBlockIcon[7] = par1IconRegister.registerIcon("phobos:phobos_desh_ore");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.phobosBlockIcon[meta];
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
    public float getBlockHardness(World world, int par2, int par3, int par4)
    {
        int meta = world.getBlockMetadata(par2, par3, par4);

        if (meta == 2)
        {
            return 1.75F;
        }
        if (meta == 3)
        {
            return 1.5F;
        }
        if (meta == 0 || meta == 1)
        {
            return 2.5F;
        }
        if (meta >= 4)
        {
            return 3.0F;
        }
        return 1.0F;
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta <= 1)
        {
            return 2.0F;
        }
        if (meta >= 2)
        {
            return 6.0F;
        }
        return this.blockResistance / 5.0F;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 2)
        {
            return 3;
        }
        return meta;
    }

    @Override
    public boolean isValueable(int meta)
    {
        if (meta >= 4 && meta <= 7)
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
        return -1;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return null;
    }
}
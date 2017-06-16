/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.blocks;

import java.util.List;

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
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.moons.io.tileentities.TileEntityIoDungeonSpawner;

public class BlockBasicIo extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] ioBlockIcon;

    public BlockBasicIo(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.ioBlockIcon = new IIcon[9];
        this.ioBlockIcon[0] = par1IconRegister.registerIcon("io:io_surface_rock");
        this.ioBlockIcon[1] = par1IconRegister.registerIcon("io:io_sub_surface_rock");
        this.ioBlockIcon[2] = par1IconRegister.registerIcon("io:io_rock");
        this.ioBlockIcon[3] = par1IconRegister.registerIcon("io:io_cobblestone");
        this.ioBlockIcon[4] = par1IconRegister.registerIcon("io:sulfur_ore");
        this.ioBlockIcon[5] = par1IconRegister.registerIcon("io:ash_stone");
        this.ioBlockIcon[6] = par1IconRegister.registerIcon("io:ash_cobblestone");
        this.ioBlockIcon[7] = par1IconRegister.registerIcon("io:silicate_rock");
        this.ioBlockIcon[8] = par1IconRegister.registerIcon("io:io_dungeon_brick");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.ioBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 9; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0 || meta == 1)
        {
            return 2.0F;
        }
        if (meta == 2 || meta == 5)
        {
            return 3.0F;
        }
        if (meta == 3 || meta == 6)
        {
            return 2.75F;
        }
        if (meta == 4 || meta == 7)
        {
            return 2.5F;
        }
        if (meta == 8)
        {
            return 4.0F;
        }
        return 1.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta <= 1)
        {
            return 3.0F;
        }
        if (meta >= 2 && meta <= 7)
        {
            return 6.0F;
        }
        if (meta == 8)
        {
            return 40.0F;
        }
        return this.blockResistance / 5.0F;
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
    public boolean isValueable(int meta)
    {
        return meta == 4;
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return -1;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityIoDungeonSpawner();
    }
}
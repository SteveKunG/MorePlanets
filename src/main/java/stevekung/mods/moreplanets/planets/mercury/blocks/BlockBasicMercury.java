/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.blocks;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;
import stevekung.mods.moreplanets.planets.mercury.tileentities.TileEntityMercuryDungeonSpawner;

public class BlockBasicMercury extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] mercuryBlockIcon;

    public BlockBasicMercury(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.mercuryBlockIcon = new IIcon[13];
        this.mercuryBlockIcon[0] = par1IconRegister.registerIcon("mercury:mercury_surface_rock");
        this.mercuryBlockIcon[1] = par1IconRegister.registerIcon("mercury:mercury_sub_surface_rock");
        this.mercuryBlockIcon[2] = par1IconRegister.registerIcon("mercury:mercury_rock");
        this.mercuryBlockIcon[3] = par1IconRegister.registerIcon("mercury:mercury_cobblestone");
        this.mercuryBlockIcon[4] = par1IconRegister.registerIcon("mercury:mercury_tin_ore");
        this.mercuryBlockIcon[5] = par1IconRegister.registerIcon("mercury:mercury_copper_ore");
        this.mercuryBlockIcon[6] = par1IconRegister.registerIcon("mercury:mercury_aluminum_ore");
        this.mercuryBlockIcon[7] = par1IconRegister.registerIcon("mercury:mercury_iron_ore");
        this.mercuryBlockIcon[8] = par1IconRegister.registerIcon("mercury:metal_meteoric_iron_ore");
        this.mercuryBlockIcon[9] = par1IconRegister.registerIcon("mercury:mercury_silicate_rock");
        this.mercuryBlockIcon[10] = par1IconRegister.registerIcon("mercury:solid_metal_meteoric_iron");
        this.mercuryBlockIcon[11] = par1IconRegister.registerIcon("mercury:mercury_dungeon_brick");
        this.mercuryBlockIcon[12] = par1IconRegister.registerIcon("galacticraftcore:blank");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.mercuryBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 12; ++i)
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
            return 2.75F;
        }
        if (meta == 3)
        {
            return 2.5F;
        }
        if (meta == 0 || meta == 1 || meta >= 4 && meta <= 9)
        {
            return 3.0F;
        }
        if (meta >= 10 && meta <= 13)
        {
            return 3.25F;
        }
        if (meta == 14)
        {
            return 4.0F;
        }
        if (meta == 15)
        {
            return -1.0F;
        }
        return 1.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 14 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) == 10;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        if (meta == 8)
        {
            return MercuryItems.mercury_item;
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
        if (meta == 8)
        {
            return 1;
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
        return 12;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 12;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityMercuryDungeonSpawner();
    }
}
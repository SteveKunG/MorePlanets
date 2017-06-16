/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.tileentities.TileEntityVenusDungeonSpawner;

public class BlockBasicVenus extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] venusBlockIcon;

    public BlockBasicVenus(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.venusBlockIcon = new IIcon[17];
        this.venusBlockIcon[0] = par1IconRegister.registerIcon("venus:venus_surface_rock");
        this.venusBlockIcon[1] = par1IconRegister.registerIcon("venus:venus_sub_surface_rock");
        this.venusBlockIcon[2] = par1IconRegister.registerIcon("venus:venus_rock");
        this.venusBlockIcon[3] = par1IconRegister.registerIcon("venus:venus_cobblestone");
        this.venusBlockIcon[4] = par1IconRegister.registerIcon("venus:venus_sulfur_ore");
        this.venusBlockIcon[5] = par1IconRegister.registerIcon("venus:venus_lead_ore");
        this.venusBlockIcon[6] = par1IconRegister.registerIcon("venus:venus_tin_ore");
        this.venusBlockIcon[7] = par1IconRegister.registerIcon("venus:venus_copper_ore");
        this.venusBlockIcon[8] = par1IconRegister.registerIcon("venus:venus_coal_ore");
        this.venusBlockIcon[9] = par1IconRegister.registerIcon("venus:venus_iron_ore");
        this.venusBlockIcon[10] = par1IconRegister.registerIcon("venus:venus_gold_ore");
        this.venusBlockIcon[11] = par1IconRegister.registerIcon("venus:lead_block");
        this.venusBlockIcon[12] = par1IconRegister.registerIcon("venus:venus_stone_brick");
        this.venusBlockIcon[13] = par1IconRegister.registerIcon("venus:cracked_venus_stone_brick");
        this.venusBlockIcon[14] = par1IconRegister.registerIcon("venus:venus_dungeon_brick");
        this.venusBlockIcon[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
        this.venusBlockIcon[16] = par1IconRegister.registerIcon("venus:venus_surface_rock_side");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            switch (side)
            {
            case 0:
                return this.venusBlockIcon[1]; //BOTTOM
            case 1:
                return this.venusBlockIcon[0]; //TOP
            case 2:
                return this.venusBlockIcon[16]; //Z-
            case 3:
                return this.venusBlockIcon[16]; //Z+
            case 4:
                return this.venusBlockIcon[16]; //X-
            case 5:
                return this.venusBlockIcon[16]; //X+
            }
        }
        return this.venusBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 15; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) == 11;
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

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
        if (meta == 14)
        {
            return 4.0F;
        }
        return 1.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 14 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int par3)
    {
        if (meta == 4)
        {
            return SiriusBItems.sirius_b_item;
        }
        if (meta == 8)
        {
            return Items.coal;
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
        if (meta == 8)
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
        return super.quantityDropped(meta, fortune, random);
    }

    @Override
    public boolean isValueable(int meta)
    {
        if (meta >= 4 && meta <= 10)
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
        return 15;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 15;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityVenusDungeonSpawner();
    }
}
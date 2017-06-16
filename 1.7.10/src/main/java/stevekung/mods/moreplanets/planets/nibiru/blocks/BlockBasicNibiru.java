/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityNibiruDungeonSpawner;

public class BlockBasicNibiru extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] nibiruBlockIcon;

    public BlockBasicNibiru(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.nibiruBlockIcon = new IIcon[14];
        this.nibiruBlockIcon[0] = par1IconRegister.registerIcon("nibiru:nibiru_surface_rock");
        this.nibiruBlockIcon[1] = par1IconRegister.registerIcon("nibiru:nibiru_sub_surface_rock");
        this.nibiruBlockIcon[2] = par1IconRegister.registerIcon("nibiru:nibiru_rock");
        this.nibiruBlockIcon[3] = par1IconRegister.registerIcon("nibiru:nibiru_cobblestone");
        this.nibiruBlockIcon[4] = par1IconRegister.registerIcon("nibiru:ichorius_ore");
        this.nibiruBlockIcon[5] = par1IconRegister.registerIcon("nibiru:norium_ore");
        this.nibiruBlockIcon[6] = par1IconRegister.registerIcon("nibiru:nibiru_diamond_ore");
        this.nibiruBlockIcon[7] = par1IconRegister.registerIcon("nibiru:nibiru_coal_ore");
        this.nibiruBlockIcon[8] = par1IconRegister.registerIcon("nibiru:red_gem_ore");
        this.nibiruBlockIcon[9] = par1IconRegister.registerIcon("nibiru:ichorius_block");
        this.nibiruBlockIcon[10] = par1IconRegister.registerIcon("nibiru:norium_block");
        this.nibiruBlockIcon[11] = par1IconRegister.registerIcon("nibiru:red_gem_block");
        this.nibiruBlockIcon[12] = par1IconRegister.registerIcon("nibiru:nibiru_dungeon_brick");
        this.nibiruBlockIcon[13] = par1IconRegister.registerIcon("galacticraftcore:blank");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.nibiruBlockIcon[meta];
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) == 10 || world.getBlockMetadata(x, y, z) == 11;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 13; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        final int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0 || meta == 1 || meta == 12)
        {
            return 4.0F;
        }
        if (meta == 2)
        {
            return 4.5F;
        }
        if (meta == 3 || meta >= 9 && meta <= 11)
        {
            return 4.25F;
        }
        if (meta >= 4 && meta <= 8)
        {
            return 5.0F;
        }
        return 4.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 12 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public void onEntityWalking(World world, int par2, int par3, int par4, Entity entity)
    {
        super.onEntityWalking(world, par2, par3, par4, entity);

        if (world.getBlockMetadata(par2, par3, par4) == 12)
        {
            for (int i = 0; i < 20; i++)
            {
                world.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(this)) + "_12", par2 + world.rand.nextFloat(), par3 + 1.0F, par4 + world.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        if (meta == 2)
        {
            return Item.getItemFromBlock(NibiruBlocks.nibiru_block);
        }
        if (meta == 4)
        {
            return NibiruItems.power_crystal;
        }
        if (meta == 6)
        {
            return Items.diamond;
        }
        if (meta == 7)
        {
            return Items.coal;
        }
        if (meta == 8)
        {
            return NibiruItems.nibiru_item;
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
        if (meta == 4 || meta >= 6 && meta <= 8)
        {
            return 0;
        }
        return meta;
    }

    @Override
    public boolean isValueable(int metadata)
    {
        if (metadata >= 4 && metadata <= 8)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTerraformable(World world, int x, int y, int z)
    {
        final int meta = world.getBlockMetadata(x, y, z);

        if ((meta == 0 || meta == 1) && !world.getBlock(x, y + 1, z).isOpaqueCube())
        {
            return true;
        }
        return false;
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return 13;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 13;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityNibiruDungeonSpawner();
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int fortune)
    {
        super.harvestBlock(world, player, x, y, z, fortune);
        MorePlanetEvents.addInfectedGas(player);
    }
}
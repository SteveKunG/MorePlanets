/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.Block;
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
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaDungeonSpawner;

public class BlockBasicDiona extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] dionaBlockIcon;

    public BlockBasicDiona(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.dionaBlockIcon = new IIcon[17];
        this.dionaBlockIcon[0] = par1IconRegister.registerIcon("diona:diona_surface_rock");
        this.dionaBlockIcon[1] = par1IconRegister.registerIcon("diona:diona_sub_surface_rock");
        this.dionaBlockIcon[2] = par1IconRegister.registerIcon("diona:diona_rock");
        this.dionaBlockIcon[3] = par1IconRegister.registerIcon("diona:diona_cobblestone");
        this.dionaBlockIcon[4] = par1IconRegister.registerIcon("diona:quontonium_ore");
        this.dionaBlockIcon[5] = par1IconRegister.registerIcon("diona:fronisium_ore");
        this.dionaBlockIcon[6] = par1IconRegister.registerIcon("diona:diona_tin_ore");
        this.dionaBlockIcon[7] = par1IconRegister.registerIcon("diona:diona_copper_ore");
        this.dionaBlockIcon[8] = par1IconRegister.registerIcon("diona:diona_silicon_ore");
        this.dionaBlockIcon[9] = par1IconRegister.registerIcon("diona:diona_aluminum_ore");
        this.dionaBlockIcon[10] = par1IconRegister.registerIcon("diona:quontonium_block");
        this.dionaBlockIcon[11] = par1IconRegister.registerIcon("diona:smooth_quontonium");
        this.dionaBlockIcon[12] = par1IconRegister.registerIcon("diona:quontonium_brick");
        this.dionaBlockIcon[13] = par1IconRegister.registerIcon("diona:chiseled_quontonium");
        this.dionaBlockIcon[14] = par1IconRegister.registerIcon("diona:diona_dungeon_brick");
        this.dionaBlockIcon[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
        this.dionaBlockIcon[16] = par1IconRegister.registerIcon("diona:diona_surface_rock_side");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            switch (side)
            {
            case 0:
                return this.dionaBlockIcon[1]; //BOTTOM
            case 1:
                return this.dionaBlockIcon[0]; //TOP
            case 2:
                return this.dionaBlockIcon[16]; //Z-
            case 3:
                return this.dionaBlockIcon[16]; //Z+
            case 4:
                return this.dionaBlockIcon[16]; //X-
            case 5:
                return this.dionaBlockIcon[16]; //X+
            default:
                return this.dionaBlockIcon[0];
            }
        }
        return this.dionaBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 15; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
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
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

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
    public void onEntityWalking(World world, int par2, int par3, int par4, Entity entity)
    {
        super.onEntityWalking(world, par2, par3, par4, entity);

        if (world.getBlockMetadata(par2, par3, par4) == 14)
        {
            for (int i = 0; i < 20; i++)
            {
                world.spawnParticle("blockcrack_" + String.valueOf(Block.getIdFromBlock(this)) + "_14", par2 + world.rand.nextFloat(), par3 + 1.0F, par4 + world.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        if (meta == 2)
        {
            return Item.getItemFromBlock(DionaBlocks.diona_block);
        }
        if (meta == 8)
        {
            return GCItems.basicItem;
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
            return 2;
        }
        return meta;
    }

    @Override
    public boolean isValueable(int meta)
    {
        if (meta >= 4 && meta <= 9)
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
        return new TileEntityDionaDungeonSpawner();
    }
}
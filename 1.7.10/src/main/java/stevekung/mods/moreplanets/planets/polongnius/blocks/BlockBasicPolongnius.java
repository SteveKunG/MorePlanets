/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityPolongniusDungeonSpawner;

public class BlockBasicPolongnius extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] polongniusBlockIcon;

    public BlockBasicPolongnius(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        if (par1World.getBlockMetadata(par2, par3, par4) == 13)
        {
            par5Entity.motionX *= 0.45D;
            par5Entity.motionZ *= 0.45D;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 13)
        {
            float f = 0.125F;
            return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
        }
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.polongniusBlockIcon = new IIcon[16];
        this.polongniusBlockIcon[0] = par1IconRegister.registerIcon("polongnius:cheese_gas");
        this.polongniusBlockIcon[1] = par1IconRegister.registerIcon("polongnius:solid_cheese_gas");
        this.polongniusBlockIcon[2] = par1IconRegister.registerIcon("polongnius:polongnius_stone");
        this.polongniusBlockIcon[3] = par1IconRegister.registerIcon("polongnius:polongnius_cobblestone");
        this.polongniusBlockIcon[4] = par1IconRegister.registerIcon("polongnius:polongnius_copper_ore");
        this.polongniusBlockIcon[5] = par1IconRegister.registerIcon("polongnius:polongnius_tin_ore");
        this.polongniusBlockIcon[6] = par1IconRegister.registerIcon("polongnius:polongnius_iron_ore");
        this.polongniusBlockIcon[7] = par1IconRegister.registerIcon("polongnius:palladium_ore");
        this.polongniusBlockIcon[8] = par1IconRegister.registerIcon("polongnius:flonium_ore");
        this.polongniusBlockIcon[9] = par1IconRegister.registerIcon("polongnius:purple_crystal_ore");
        this.polongniusBlockIcon[10] = par1IconRegister.registerIcon("polongnius:solid_polongnius_meteoric_iron");
        this.polongniusBlockIcon[11] = par1IconRegister.registerIcon("polongnius:purple_crystal_block");
        this.polongniusBlockIcon[12] = par1IconRegister.registerIcon("polongnius:palladium_block");
        this.polongniusBlockIcon[13] = par1IconRegister.registerIcon("polongnius:polongnius_dungeon_brick2");
        this.polongniusBlockIcon[14] = par1IconRegister.registerIcon("polongnius:polongnius_dungeon_brick");
        this.polongniusBlockIcon[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) >= 10 && world.getBlockMetadata(x, y, z) <= 12;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.polongniusBlockIcon[meta];
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 15; ++i)
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
            return 0.2F;
        }
        if (meta == 2)
        {
            return 3.25F;
        }
        if (meta == 3)
        {
            return 3.0F;
        }
        if (meta >= 4 && meta <= 9 || meta == 13 || meta == 14)
        {
            return 4.0F;
        }
        if (meta >= 10 && meta <= 12)
        {
            return 3.5F;
        }
        return 2.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 13 || world.getBlockMetadata(x, y, z) == 14 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        if (meta == 0 || meta == 1)
        {
            return true;
        }
        return super.canHarvestBlock(player, meta);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        if (meta == 2)
        {
            return Item.getItemFromBlock(PolongniusBlocks.polongnius_block);
        }
        if (meta == 7 || meta == 8 || meta == 9)
        {
            return PolongniusItems.polongnius_item;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 2 || meta == 7)
        {
            return 3;
        }
        if (meta == 8)
        {
            return 0;
        }
        if (meta == 9)
        {
            return 1;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == 7)
        {
            return 1 + random.nextInt(2);
        }
        if (meta == 8)
        {
            return 1 + random.nextInt(4);
        }
        return super.quantityDropped(meta, fortune, random);
    }

    @Override
    public boolean isValueable(int metadata)
    {
        if (metadata >= 4 && metadata <= 9)
        {
            return true;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.getBlockMetadata(par2, par3, par4) == 13)
        {
            if (!World.doesBlockHaveSolidTopSurface(par1World, par2, par3 + 1, par4))
            {
                if (par1World.rand.nextInt(10) == 0)
                {
                    MorePlanetsCore.proxy.spawnParticle("cheeseSlime", par2 + par1World.rand.nextFloat(), par3 + 1.2F, par4 + par1World.rand.nextFloat());
                }
            }
        }
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
        return new TileEntityPolongniusDungeonSpawner();
    }
}
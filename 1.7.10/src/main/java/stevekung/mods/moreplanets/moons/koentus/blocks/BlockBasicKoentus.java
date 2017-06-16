/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

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
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityKoentusDungeonSpawner;

public class BlockBasicKoentus extends BlockBasicMP implements IDetectableResource, ITerraformableBlock
{
    private IIcon[] koentusBlockIcon;

    public BlockBasicKoentus(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.koentusBlockIcon = new IIcon[16];
        this.koentusBlockIcon[0] = par1IconRegister.registerIcon("koentus:koentus_surface_stone");
        this.koentusBlockIcon[1] = par1IconRegister.registerIcon("koentus:koentus_sub_surface_stone");
        this.koentusBlockIcon[2] = par1IconRegister.registerIcon("koentus:koentus_stone");
        this.koentusBlockIcon[3] = par1IconRegister.registerIcon("koentus:koentus_cobblestone");
        this.koentusBlockIcon[4] = par1IconRegister.registerIcon("koentus:koentus_tin_ore");
        this.koentusBlockIcon[5] = par1IconRegister.registerIcon("koentus:koentus_copper_ore");
        this.koentusBlockIcon[6] = par1IconRegister.registerIcon("koentus:white_crystal_ore");
        this.koentusBlockIcon[7] = par1IconRegister.registerIcon("koentus:emp_crystal_ore");
        this.koentusBlockIcon[8] = par1IconRegister.registerIcon("koentus:becterial_fossil_ore");
        this.koentusBlockIcon[9] = par1IconRegister.registerIcon("koentus:white_crystal_block");
        this.koentusBlockIcon[10] = par1IconRegister.registerIcon("koentus:emp_crystal_block");
        this.koentusBlockIcon[11] = par1IconRegister.registerIcon("koentus:koentus_dungeon_brick");
        this.koentusBlockIcon[12] = par1IconRegister.registerIcon("koentus:koentus_ancient_stone");
        this.koentusBlockIcon[13] = par1IconRegister.registerIcon("koentus:ancient_koentus_brick");
        this.koentusBlockIcon[14] = par1IconRegister.registerIcon("galacticraftcore:blank");
        this.koentusBlockIcon[15] = par1IconRegister.registerIcon("koentus:solid_koentus_meteoric_iron");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) == 9 || world.getBlockMetadata(x, y, z) == 15;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.koentusBlockIcon[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            if (i != 14)
            {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0 || meta == 1 || meta == 3)
        {
            return 3.25F;
        }
        if (meta == 2)
        {
            return 3.5F;
        }
        if (meta >= 4 && meta <= 8)
        {
            return 3.0F;
        }
        if (meta >= 9 && meta <= 11 || meta == 15)
        {
            return 4.0F;
        }
        if (meta == 12 || meta == 13)
        {
            return 2.5F;
        }
        if (meta == 8)
        {
            return 2.75F;
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
        if (meta >= 2 && meta <= 10)
        {
            return 6.0F;
        }
        if (meta == 11)
        {
            return 40.0F;
        }
        if (meta == 12 || meta == 13)
        {
            return 4.0F;
        }
        if (meta == 15)
        {
            return 40.0F;
        }
        return this.blockResistance / 5.0F;
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        if (meta == 2)
        {
            return Item.getItemFromBlock(KoentusBlocks.koentus_block);
        }
        if (meta >= 6 && meta <= 8)
        {
            return KoentusItems.koentus_item;
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
        if (meta == 6)
        {
            return 0;
        }
        if (meta == 7)
        {
            return 1;
        }
        if (meta == 8)
        {
            return 2;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == 7)
        {
            return 1 + random.nextInt(4);
        }
        if (meta == 8)
        {
            return 1 + random.nextInt(2);
        }
        return super.quantityDropped(meta, fortune, random);
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
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata == 11)
        {
            if (rand.nextInt(10) == 0)
            {
                if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
                {
                    MorePlanetsCore.proxy.spawnParticle("koentusBricksDrip", x + rand.nextDouble(), y, z + rand.nextDouble());

                    if (rand.nextInt(100) == 0)
                    {
                        world.playSound(x, y, z, "galacticraftcore:ambience.singledrip", 1, 0.8F + rand.nextFloat() / 5.0F, false);
                    }
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
        return 14;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 14;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityKoentusDungeonSpawner();
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public abstract class BlockBasicMP extends BlockBaseMP implements ITileEntityProvider
{
    public BlockBasicMP(Material material)
    {
        super(material);
        this.blockResistance = 3.0F;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == this.getDungeonSpawnerMetadata())
        {
            return null;
        }
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == this.getDungeonSpawnerMetadata())
        {
            return AxisAlignedBB.getBoundingBox(x + 0.0D, y + 0.0D, z + 0.0D, x + 0.0D, y + 0.0D, z + 0.0D);
        }
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == this.getDungeonSpawnerMetadata())
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        }
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.setBlockBoundsBasedOnState(world, x, y, z);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List list, Entity entity)
    {
        if (world.getBlockMetadata(x, y, z) == this.getDungeonSpawnerMetadata())
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
        }
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
    }

    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == this.getDungeonSpawnerMetadata())
        {
            return false;
        }
        return super.isNormalCube(world, x, y, z);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == this.getDungeonSpawnerMetadata())
        {
            return null;
        }
        return new ItemStack(this, 1, meta);
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        if (meta == this.getDungeonSpawnerMetadata())
        {
            return false;
        }
        return super.canHarvestBlock(player, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        if (metadata == this.getDungeonSpawnerMetadata())
        {
            return this.getDungeonSpawner();
        }
        if (this.getDungeonSpawnerMetadata() == -1)
        {
            return null;
        }
        return null;
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == this.getDungeonSpawnerMetadata())
        {
            return -1.0F;
        }
        return this.blockHardness;
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == this.getDungeonSpawnerMetadata())
        {
            return 10000000000.0F;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == this.getDungeonSpawnerMetadata())
        {
            return 0;
        }
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(meta, random, fortune))
        {
            int j = random.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return this.quantityDropped(random) * (j + 1);
        }
        return this.quantityDropped(random);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this))
        {
            int var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
    }

    public abstract int getDungeonSpawnerMetadata();
    public abstract TileEntity getDungeonSpawner();
}
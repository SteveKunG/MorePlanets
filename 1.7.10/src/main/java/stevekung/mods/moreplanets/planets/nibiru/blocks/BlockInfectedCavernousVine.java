/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedWorm;

public class BlockInfectedCavernousVine extends BlockBaseMP implements IShearable
{
    public BlockInfectedCavernousVine(String name)
    {
        super(Material.vine);
        this.setLightLevel(1.0F);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName(name);
        this.setBlockTextureName("nibiru:infected_cavernous_vine");
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 vec3d, Vec3 vec3d1)
    {
        return super.collisionRayTrace(world, x, y, z, vec3d, vec3d1);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        if (world.setBlockToAir(x, y, z))
        {
            int y2 = y - 1;

            while (world.getBlock(x, y2, z) == this)
            {
                world.setBlockToAir(x, y2, z);
                y2--;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            if (entity instanceof EntityPlayer)
            {
                if (((EntityPlayer)entity).capabilities.isCreativeMode || ((EntityPlayer)entity).capabilities.isFlying)
                {
                    return;
                }
            }

            entity.motionY = 0.07F;

            if (!((EntityLivingBase)entity).getActivePotionEffects().contains(Potion.poison))
            {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 5, 20, false));
            }
        }

        if (entity instanceof EntityPlayer && world.rand.nextInt(1000) == 0)
        {
            if (!world.isRemote)
            {
                EntityInfectedWorm worm = new EntityInfectedWorm(world);
                worm.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
                world.spawnEntityInWorld(worm);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return this.getVineLight(world, x, y, z);
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        return ForgeDirection.getOrientation(side) == ForgeDirection.DOWN && world.getBlock(x, y + 1, z).getMaterial().isSolid();
    }

    public int getVineLength(IBlockAccess world, int x, int y, int z)
    {
        int vineCount = 0;
        int y2 = y;

        while (world.getBlock(x, y2, z) == NibiruBlocks.infected_cavernous_vine)
        {
            vineCount++;
            y2++;
        }
        return vineCount;
    }

    public int getVineLight(IBlockAccess world, int x, int y, int z)
    {
        int vineCount = 0;
        int y2 = y;

        while (world.getBlock(x, y2, z) == NibiruBlocks.infected_cavernous_vine)
        {
            vineCount += 4;
            y2--;
        }
        return Math.max(19 - vineCount, 0);
    }

    @Override
    public int tickRate(World par1World)
    {
        return 50;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            for (int y2 = y - 1; y2 >= y - 2; y2--)
            {
                Block block = world.getBlock(x, y2, z);

                if (block == null || !block.isAir(world, x, y, z))
                {
                    return;
                }
            }
            world.setBlock(x, y - 1, z, this, 0, 2);
            world.func_147451_t(x, y, z);
        }
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(Blocks.air);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y + 1, z);
        return block == this || block.getMaterial().isSolid();
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);

        if (!this.canBlockStay(world, x, y, z))
        {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }

    @Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
    {
        return true;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int fortune)
    {
        super.harvestBlock(world, player, x, y, z, fortune);
        MorePlanetEvents.addInfectedGas(player);
    }
}
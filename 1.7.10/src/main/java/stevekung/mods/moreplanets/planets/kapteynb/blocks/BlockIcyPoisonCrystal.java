/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockContainerMP;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityIcyPoisonCrystal;

public class BlockIcyPoisonCrystal extends BlockContainerMP
{
    public static int[] colors = { -4663096, -6309434, -6368557, -6837559, -7748159, -5851189 };

    public BlockIcyPoisonCrystal(String name)
    {
        super(Material.glass);
        this.setLightLevel(0.8F);
        this.setResistance(1.5F);
        this.setHardness(0.5F);
        this.setStepSound(soundTypeGlass);
        this.setBlockName(name);
        this.setLightOpacity(255);
        this.setBlockTextureName("kapteynb:fallen_ice_crystal_meteor");
        float f = 0.0625F;
        this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F - f, 1.0F - f);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        ItemStack itemStack = player.getCurrentEquippedItem();

        if (itemStack == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe) || !MorePlanetEvents.getTier3ThermalArmor((EntityPlayerMP)player) || !MorePlanetEvents.getIceCrystalArmor((EntityPlayerMP)player))
        {
            player.addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 60, 0, false));
        }
        if (itemStack != null && player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)
        {
            super.harvestBlock(world, player, x, y, z, meta);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            if (entity instanceof EntityPlayerMP)
            {
                if (!MorePlanetEvents.getTier3ThermalArmor((EntityPlayerMP)entity))
                {
                    ((EntityPlayerMP)entity).addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 60, 0, false));
                }
            }
            else
            {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 60, 0, false));
            }
        }
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
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityIcyPoisonCrystal();
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int fortune)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, fortune);

        if (this.getItemDropped(par5, world.rand, fortune) != Item.getItemFromBlock(this))
        {
            int var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(meta, random, fortune))
        {
            int i = random.nextInt(fortune + 1) - 1;

            if (i < 0)
            {
                i = 0;
            }
            return this.quantityDropped(random) * (i + 1);
        }
        return this.quantityDropped(random);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return KapteynBItems.kapteyn_b_item;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 5;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
    {
        super.onNeighborBlockChange(world, i, j, k, block);
        int meta = world.getBlockMetadata(i, j, k);

        if (meta <= 6 && this.checkIfAttachedToBlock(world, i, j, k))
        {
            TileEntityIcyPoisonCrystal crystal = (TileEntityIcyPoisonCrystal)world.getTileEntity(i, j, k);
            int orientation = crystal.orientation;
            boolean flag = false;

            if (!world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5)) && orientation == 5)
            {
                flag = true;
            }
            if (!world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4)) && orientation == 4)
            {
                flag = true;
            }
            if (!world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3)) && orientation == 3)
            {
                flag = true;
            }
            if (!world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2)) && orientation == 2)
            {
                flag = true;
            }
            if (!world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1)) && orientation == 1)
            {
                flag = true;
            }
            if (!world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0)) && orientation == 0)
            {
                flag = true;
            }
            if (flag)
            {
                world.func_147480_a(i, j, k, false);
            }
            return;
        }
    }

    private boolean checkIfAttachedToBlock(World world, int i, int j, int k)
    {
        if (!this.canPlaceBlockAt(world, i, j, k))
        {
            world.func_147480_a(i, j, k, false);
            return false;
        }
        return true;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int i, int j, int k, int meta)
    {
        if (meta == 0 && world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0)))
        {
            return true;
        }
        if (meta == 1 && world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1)))
        {
            return true;
        }
        if (meta == 2 && world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2)))
        {
            return true;
        }
        if (meta == 3 && world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3)))
        {
            return true;
        }
        if (meta == 4 && world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4)))
        {
            return true;
        }
        return meta == 5 && world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5));
    }

    @Override
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        if (world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation(5)))
        {
            return true;
        }
        if (world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation(4)))
        {
            return true;
        }
        if (world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation(3)))
        {
            return true;
        }
        if (world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation(2)))
        {
            return true;
        }
        if (world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation(1)))
        {
            return true;
        }
        return world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation(0));
    }
}
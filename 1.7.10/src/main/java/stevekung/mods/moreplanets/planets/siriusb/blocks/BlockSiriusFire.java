/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;

public class BlockSiriusFire extends BlockFire
{
    private IIcon[] fireIcon;

    public BlockSiriusFire(String name)
    {
        super();
        this.setBlockName(name);
        this.setTickRandomly(true);
        this.setLightLevel(1.0F);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
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
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public int tickRate(World world)
    {
        return 30;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (world.getGameRules().getGameRuleBooleanValue("doFireTick"))
        {
            boolean flag = world.getBlock(x, y - 1, z).isFireSource(world, x, y - 1, z, ForgeDirection.UP);

            if (!this.canPlaceBlockAt(world, x, y, z))
            {
                world.setBlockToAir(x, y, z);
            }
            if (!flag && world.isRaining() && (world.canLightningStrikeAt(x, y, z) || world.canLightningStrikeAt(x - 1, y, z) || world.canLightningStrikeAt(x + 1, y, z) || world.canLightningStrikeAt(x, y, z - 1) || world.canLightningStrikeAt(x, y, z + 1)))
            {
                world.setBlockToAir(x, y, z);
            }
            else
            {
                int l = world.getBlockMetadata(x, y, z);

                if (l < 15)
                {
                    world.setBlockMetadataWithNotify(x, y, z, l + rand.nextInt(3) / 2, 4);
                }

                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + rand.nextInt(10));

                if (!flag && !this.canNeighborBurn(world, x, y, z))
                {
                    if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || l > 3)
                    {
                        world.setBlockToAir(x, y, z);
                    }
                }
                else if (!flag && !this.canCatchFire(world, x, y - 1, z, ForgeDirection.UP) && l == 15 && rand.nextInt(4) == 0)
                {
                    world.setBlockToAir(x, y, z);
                }
                else
                {
                    boolean flag1 = world.isBlockHighHumidity(x, y, z);
                    byte b0 = 0;

                    if (flag1)
                    {
                        b0 = -50;
                    }

                    this.tryCatchFire(world, x + 1, y, z, 300 + b0, rand, l, ForgeDirection.WEST);
                    this.tryCatchFire(world, x - 1, y, z, 300 + b0, rand, l, ForgeDirection.EAST);
                    this.tryCatchFire(world, x, y - 1, z, 250 + b0, rand, l, ForgeDirection.UP);
                    this.tryCatchFire(world, x, y + 1, z, 250 + b0, rand, l, ForgeDirection.DOWN);
                    this.tryCatchFire(world, x, y, z - 1, 300 + b0, rand, l, ForgeDirection.SOUTH);
                    this.tryCatchFire(world, x, y, z + 1, 300 + b0, rand, l, ForgeDirection.NORTH);

                    for (int i1 = x - 1; i1 <= x + 1; i1++)
                    {
                        for (int j1 = z - 1; j1 <= z + 1; j1++)
                        {
                            for (int k1 = y - 1; k1 <= y + 4; k1++)
                            {
                                if (i1 != x || k1 != y || j1 != z)
                                {
                                    int l1 = 100;

                                    if (k1 > y + 1)
                                    {
                                        l1 += (k1 - (y + 1)) * 100;
                                    }

                                    int i2 = this.getChanceOfNeighborsEncouragingFire(world, i1, k1, j1);

                                    if (i2 > 0)
                                    {
                                        int j2 = (i2 + 40 + world.difficultySetting.getDifficultyId() * 7) / (l + 30);

                                        if (flag1)
                                        {
                                            j2 /= 2;
                                        }
                                        if (j2 > 0 && rand.nextInt(l1) <= j2 && (!world.isRaining() || !world.canLightningStrikeAt(i1, k1, j1)) && !world.canLightningStrikeAt(i1 - 1, k1, z) && !world.canLightningStrikeAt(i1 + 1, k1, j1) && !world.canLightningStrikeAt(i1, k1, j1 - 1) && !world.canLightningStrikeAt(i1, k1, j1 + 1))
                                        {
                                            int k2 = l + rand.nextInt(5) / 4;

                                            if (k2 > 15)
                                            {
                                                k2 = 15;
                                            }
                                            world.setBlock(i1, k1, j1, this, k2, 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean func_149698_L()
    {
        return true;
    }

    private void tryCatchFire(World world, int x, int y, int z, int p_149841_5_, Random rand, int p_149841_7_, ForgeDirection face)
    {
        int j1 = world.getBlock(x, y, z).getFlammability(world, x, y, z, face);

        if (rand.nextInt(p_149841_5_) < j1)
        {
            boolean flag = world.getBlock(x, y, z) == Blocks.tnt;
            boolean flag2 = world.getBlock(x, y, z) == DionaBlocks.fronisium_tnt;

            if (rand.nextInt(p_149841_7_ + 10) < 5 && !world.canLightningStrikeAt(x, y, z))
            {
                int k1 = p_149841_7_ + rand.nextInt(5) / 4;

                if (k1 > 15)
                {
                    k1 = 15;
                }
                world.setBlock(x, y, z, this, k1, 3);
            }
            else
            {
                world.setBlockToAir(x, y, z);
            }
            if (flag)
            {
                Blocks.tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
            }
            else if (flag2)
            {
                DionaBlocks.fronisium_tnt.onBlockDestroyedByPlayer(world, x, y, z, 1);
            }
        }
    }

    private boolean canNeighborBurn(World world, int x, int y, int z)
    {
        return this.canCatchFire(world, x + 1, y, z, ForgeDirection.WEST) || this.canCatchFire(world, x - 1, y, z, ForgeDirection.EAST) || this.canCatchFire(world, x, y - 1, z, ForgeDirection.UP) || this.canCatchFire(world, x, y + 1, z, ForgeDirection.DOWN) || this.canCatchFire(world, x, y, z - 1, ForgeDirection.SOUTH) || this.canCatchFire(world, x, y, z + 1, ForgeDirection.NORTH);
    }

    private int getChanceOfNeighborsEncouragingFire(World world, int x, int y, int z)
    {
        byte b0 = 0;

        if (!world.isAirBlock(x, y, z))
        {
            return 0;
        }

        int l = b0;

        l = this.getChanceToEncourageFire(world, x + 1, y, z, l, ForgeDirection.WEST);
        l = this.getChanceToEncourageFire(world, x - 1, y, z, l, ForgeDirection.EAST);
        l = this.getChanceToEncourageFire(world, x, y - 1, z, l, ForgeDirection.UP);
        l = this.getChanceToEncourageFire(world, x, y + 1, z, l, ForgeDirection.DOWN);
        l = this.getChanceToEncourageFire(world, x, y, z - 1, l, ForgeDirection.SOUTH);
        l = this.getChanceToEncourageFire(world, x, y, z + 1, l, ForgeDirection.NORTH);
        return l;
    }

    @Override
    public boolean isCollidable()
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || this.canNeighborBurn(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !this.canNeighborBurn(world, x, y, z))
        {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (rand.nextInt(24) == 0)
        {
            world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "fire.fire", 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }
        if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !SiriusBBlocks.sirius_fire.canCatchFire(world, x, y - 1, z, ForgeDirection.UP))
        {
            if (SiriusBBlocks.sirius_fire.canCatchFire(world, x - 1, y, z, ForgeDirection.EAST))
            {
                for (int l = 0; l < 2; l++)
                {
                    float f = x + rand.nextFloat() * 0.1F;
                    float f1 = y + rand.nextFloat();
                    float f2 = z + rand.nextFloat();
                    world.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
                }
            }
            if (SiriusBBlocks.sirius_fire.canCatchFire(world, x + 1, y, z, ForgeDirection.WEST))
            {
                for (int l = 0; l < 2; l++)
                {
                    float f = x + 1 - rand.nextFloat() * 0.1F;
                    float f1 = y + rand.nextFloat();
                    float f2 = z + rand.nextFloat();
                    world.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
                }
            }
            if (SiriusBBlocks.sirius_fire.canCatchFire(world, x, y, z - 1, ForgeDirection.SOUTH))
            {
                for (int l = 0; l < 2; l++)
                {
                    float f = x + rand.nextFloat();
                    float f1 = y + rand.nextFloat();
                    float f2 = z + rand.nextFloat() * 0.1F;
                    world.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
                }
            }
            if (SiriusBBlocks.sirius_fire.canCatchFire(world, x, y, z + 1, ForgeDirection.NORTH))
            {
                for (int l = 0; l < 2; l++)
                {
                    float f = x + rand.nextFloat();
                    float f1 = y + rand.nextFloat();
                    float f2 = z + 1 - rand.nextFloat() * 0.1F;
                    world.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
                }
            }
            if (SiriusBBlocks.sirius_fire.canCatchFire(world, x, y + 1, z, ForgeDirection.DOWN))
            {
                for (int l = 0; l < 2; l++)
                {
                    float f = x + rand.nextFloat();
                    float f1 = y + 1 - rand.nextFloat() * 0.1F;
                    float f2 = z + rand.nextFloat();
                    world.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        else
        {
            for (int l = 0; l < 3; l++)
            {
                float f = x + rand.nextFloat();
                float f3 = y + rand.nextFloat() * 0.5F + 0.5F;
                float f2 = z + rand.nextFloat();
                world.spawnParticle("largesmoke", f, f3, f2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
        {
            return;
        }
        entity.setFire(8);
    }

    @Override
    public void registerBlockIcons(IIconRegister icon)
    {
        this.fireIcon = new IIcon[] {icon.registerIcon("siriusb:sirius_fire_0"), icon.registerIcon("siriusb:sirius_fire_1") };
    }

    @Override
    public IIcon getFireIcon(int meta)
    {
        return this.fireIcon[0];
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.fireIcon[0];
    }

    @Override
    public MapColor getMapColor(int color)
    {
        return MapColor.diamondColor;
    }
}
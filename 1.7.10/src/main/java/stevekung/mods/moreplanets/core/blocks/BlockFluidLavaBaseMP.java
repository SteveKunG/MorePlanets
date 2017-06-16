/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class BlockFluidLavaBaseMP extends BlockFluidBaseMP
{
    public BlockFluidLavaBaseMP(Fluid fluid)
    {
        super(fluid, Material.lava);
        this.setQuantaPerBlock(4);
        this.setHardness(100.0F);
        this.setResistance(100.0F);
        this.setLightLevel(1.0F);
        this.needsRandomTick = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (this.func_149809_q(world, x, y - 1, z))
        {
            if (this.blockMaterial == Material.lava && world.getBlock(x, y - 1, z).getMaterial() == Material.water)
            {
                world.setBlock(x, y - 1, z, this.getBlockFromWaterTo(), this.getBlockMetaFromWaterTo(), 3);
                this.func_149799_m(world, x, y - 1, z);
                return;
            }
        }

        super.updateTick(world, x, y, z, rand);

        for (int j = 0; j < rand.nextInt(3); j++)
        {
            x += rand.nextInt(3) - 1;
            y++;
            z += rand.nextInt(3) - 1;
            Block block = world.getBlock(x, y, z);

            if (block.getMaterial() == Material.air)
            {
                if (this.isFlammable(world, x - 1, y, z) || this.isFlammable(world, x + 1, y, z) || this.isFlammable(world, x, y, z - 1) || this.isFlammable(world, x, y, z + 1) || this.isFlammable(world, x, y - 1, z) || this.isFlammable(world, x, y + 1, z))
                {
                    world.setBlock(x, y, z, this.getFireBlock());
                }
            }
            else if (block.getMaterial().blocksMovement())
            {
                return;
            }
        }
        if (rand.nextInt(3) == 0)
        {
            int j = x;
            int k = z;

            for (int m = 0; m < 3; m++)
            {
                x = j + rand.nextInt(3) - 1;
                z = k + rand.nextInt(3) - 1;

                if (world.isAirBlock(x, y + 1, z) && this.isFlammable(world, x, y, z))
                {
                    world.setBlock(x, y + 1, z, this.getFireBlock());
                }
            }
        }
    }

    protected void func_149799_m(World world, int x, int y, int z)
    {
        world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

        for (int l = 0; l < 8; l++)
        {
            MorePlanetsCore.proxy.spawnMotionParticle("mcLargeSmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        this.func_149805_n(world, x, y, z);

        if (world.getBlock(x, y, z) == this)
        {
            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
        }
        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.setFire(10);
    }

    @Override
    public boolean func_149698_L()
    {
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        this.func_149805_n(world, x, y, z);

        if (block.getMaterial() != Material.lava)
        {
            world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.15F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        }
        super.onNeighborBlockChange(world, x, y, z, block);
    }

    private void func_149805_n(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) == this)
        {
            boolean flag = false;

            if (flag || world.getBlock(x, y, z - 1).getMaterial() == Material.water)
            {
                flag = true;
            }
            if (flag || world.getBlock(x, y, z + 1).getMaterial() == Material.water)
            {
                flag = true;
            }
            if (flag || world.getBlock(x - 1, y, z).getMaterial() == Material.water)
            {
                flag = true;
            }
            if (flag || world.getBlock(x + 1, y, z).getMaterial() == Material.water)
            {
                flag = true;
            }
            if (flag || world.getBlock(x, y + 1, z).getMaterial() == Material.water)
            {
                flag = true;
            }
            if (flag)
            {
                int l = world.getBlockMetadata(x, y, z);

                if (l == 0)
                {
                    world.setBlock(x, y, z, this.getObsidianBlock());
                }
                else if (l <= 7)
                {
                    world.setBlock(x, y, z, this.getCobblestoneBlock(), this.getCobblestoneBlockMeta(), 3);
                }
                world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                for (int i = 0; i < 8; i++)
                {
                    MorePlanetsCore.proxy.spawnMotionParticle("mcLargeSmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    private boolean func_149809_q(World world, int x, int y, int z)
    {
        Material localMaterial = world.getBlock(x, y, z).getMaterial();

        if (localMaterial == this.blockMaterial)
        {
            return false;
        }
        if (localMaterial == Material.lava)
        {
            return false;
        }
        return !this.func_149807_p(world, x, y, z);
    }

    private boolean func_149807_p(World world, int x, int y, int z)
    {
        Block localBlock = world.getBlock(x, y, z);

        if (localBlock == Blocks.wooden_door || localBlock == Blocks.iron_door || localBlock == Blocks.standing_sign || localBlock == Blocks.ladder || localBlock == Blocks.reeds)
        {
            return true;
        }
        if (localBlock.getMaterial() == Material.portal)
        {
            return true;
        }
        return localBlock.getMaterial().blocksMovement();
    }

    private boolean isFlammable(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).getMaterial().getCanBurn();
    }

    protected abstract Block getBlockFromWaterTo();
    protected abstract int getBlockMetaFromWaterTo();
    protected abstract Block getObsidianBlock();
    protected abstract Block getCobblestoneBlock();
    protected abstract int getCobblestoneBlockMeta();
    protected abstract Block getFireBlock();
}
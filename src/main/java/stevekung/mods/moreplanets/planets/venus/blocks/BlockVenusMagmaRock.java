/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockVenusMagmaRock extends BlockBaseMP
{
    public BlockVenusMagmaRock(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
        this.setHardness(2.5F);
        this.setLightLevel(0.5F);
        this.setBlockTextureName("venus:venus_magma_rock");
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        if (side == ForgeDirection.UP)
        {
            return true;
        }
        return super.isFireSource(world, x, y, z, side);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (rand.nextInt(1) == 0)
        {
            world.spawnParticle("smoke", x + rand.nextFloat(), y + 1.1F, z + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        player.addExhaustion(0.025F);
        this.dropBlockAsItem(world, x, y, z, meta, 0);

        if (world.rand.nextInt(20) == 0)
        {
            world.setBlock(x, y, z, Blocks.flowing_lava);
        }
    }
}
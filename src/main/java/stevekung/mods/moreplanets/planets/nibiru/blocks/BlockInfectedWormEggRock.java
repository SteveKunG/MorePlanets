/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedWorm;

public class BlockInfectedWormEggRock extends BlockBaseMP
{
    public BlockInfectedWormEggRock(String name)
    {
        super(Material.rock);
        this.setResistance(5.0F);
        this.setHardness(4.0F);
        this.setBlockName(name);
        this.setBlockTextureName("nibiru:infected_worm_egg_rock");
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            EntityInfectedWorm infectedWorm = new EntityInfectedWorm(par1World);
            infectedWorm.setLocationAndAngles(par2 + 0.5D, par3, par4 + 0.5D, 0.0F, 0.0F);
            par1World.spawnEntityInWorld(infectedWorm);
            infectedWorm.spawnExplosionParticle();
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int fortune)
    {
        super.harvestBlock(world, player, x, y, z, fortune);
        MorePlanetEvents.addInfectedGas(player);
    }
}
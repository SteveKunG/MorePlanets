/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockEggMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;

public class BlockMinionCreeperEgg extends BlockEggMP
{
    public BlockMinionCreeperEgg(String name)
    {
        super();
        this.setResistance(0.0F);
        this.setHardness(-1.0F);
        this.setBlockName(name);
        this.setBlockTextureName("diona:minion_creeper_egg");
    }

    @Override
    public void onBlockExploded(World world, int par2, int par3, int par4, Explosion explosion)
    {
        if (!world.isRemote)
        {
            EntityDionaMinionCreeper minionCreeper = new EntityDionaMinionCreeper(world);
            minionCreeper.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
            world.spawnEntityInWorld(minionCreeper);
        }
        world.setBlockToAir(par2, par3, par4);
        this.onBlockDestroyedByExplosion(world, par2, par3, par4, explosion);
    }
}
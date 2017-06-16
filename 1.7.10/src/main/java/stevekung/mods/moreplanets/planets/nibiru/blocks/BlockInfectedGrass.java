/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockGrassMP;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;

public class BlockInfectedGrass extends BlockGrassMP
{
    private IIcon[] blockIcon;

    public BlockInfectedGrass(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = new IIcon[8];
        this.blockIcon[0] = par1IconRegister.registerIcon("nibiru:infected_dirt");
        this.blockIcon[1] = par1IconRegister.registerIcon("nibiru:infected_grass_top");
        this.blockIcon[2] = par1IconRegister.registerIcon("nibiru:infected_grass_side");
        this.blockIcon[3] = par1IconRegister.registerIcon("nibiru:infected_grass_side");
        this.blockIcon[4] = par1IconRegister.registerIcon("nibiru:infected_grass_side");
        this.blockIcon[5] = par1IconRegister.registerIcon("nibiru:infected_grass_side");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par1 < 0 || par1 >= this.blockIcon.length)
        {
            par1 = 1;
        }
        return this.blockIcon[par1];
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, NibiruBlocks.infected_dirt);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = x + rand.nextInt(3) - 1;
                    int var8 = y + rand.nextInt(5) - 3;
                    int var9 = z + rand.nextInt(3) - 1;
                    Block block = world.getBlock(var7, var8 + 1, var9);

                    if (world.getBlock(var7, var8, var9) == NibiruBlocks.infected_dirt && world.getBlockMetadata(var7, var8, var9) == 0 && world.getBlockLightValue(var7, var8 + 1, var9) >= 4 && block.getLightOpacity() <= 2)
                    {
                        world.setBlock(var7, var8, var9, NibiruBlocks.infected_grass);
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(NibiruBlocks.infected_dirt);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (rand.nextInt(1) == 0)
        {
            if (!World.doesBlockHaveSolidTopSurface(world, x, y + 1, z))
            {
                MorePlanetsCore.proxy.spawnMotionParticle("infectedSupended", x + rand.nextFloat(), y + 1.1F, z + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public Block getFarmlandBlock()
    {
        return NibiruBlocks.infected_farmland;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int fortune)
    {
        super.harvestBlock(world, player, x, y, z, fortune);
        MorePlanetEvents.addInfectedGas(player);
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockIoSmokeGeyser extends BlockBaseMP
{
    private IIcon[] venusBlockIcon;

    public BlockIoSmokeGeyser(String name)
    {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.venusBlockIcon = new IIcon[14];
        this.venusBlockIcon[0] = par1IconRegister.registerIcon("venus:venus_smoke_geyser");
        this.venusBlockIcon[1] = par1IconRegister.registerIcon("venus:venus_surface_rock_side");
        this.venusBlockIcon[2] = par1IconRegister.registerIcon("venus:venus_sub_surface_rock");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        switch (side)
        {
        case 0:
            return this.venusBlockIcon[2]; //BOTTOM
        case 1:
            return this.venusBlockIcon[0]; //TOP
        case 2:
            return this.venusBlockIcon[1]; //Z-
        case 3:
            return this.venusBlockIcon[1]; //Z+
        case 4:
            return this.venusBlockIcon[1]; //X-
        case 5:
            return this.venusBlockIcon[1]; //X+
        default:
            return this.venusBlockIcon[0];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        for (int i = 0; i < 5; i++)
        {
            if (!World.doesBlockHaveSolidTopSurface(world, x, y + 1, z))
            {
                MorePlanetsCore.proxy.spawnParticle("venusSmoke", x + 0.5F, y + 0.5F + rand.nextFloat(), z + 0.5F);
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.venus.entities.EntityVenusianBlaze;

public class BlockVenusianBlazeEgg extends BlockDragonEgg
{
    public BlockVenusianBlazeEgg(String name)
    {
        super();
        this.setBlockName(name);
        this.setHardness(-1.0F);
        this.setBlockTextureName("venus:venusian_blaze_egg");
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        return false;
    }

    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
    }

    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
        if (!world.isRemote)
        {
            EntityVenusianBlaze blaze = new EntityVenusianBlaze(world);
            blaze.setPosition(x + 0.5, y + 1, z + 0.5);
            world.spawnEntityInWorld(blaze);
        }
        world.setBlockToAir(x, y, z);
        this.onBlockDestroyedByExplosion(world, x, y, z, explosion);
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int metadata)
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack == null)
        {
            return player.canHarvestBlock(this);
        }
        return stack.getItem() == MarsItems.deshPickSlime;
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack != null && stack.getItem() == MarsItems.deshPickSlime)
        {
            return 0.6F;
        }
        return ForgeHooks.blockStrength(this, player, world, x, y, z);
    }
}
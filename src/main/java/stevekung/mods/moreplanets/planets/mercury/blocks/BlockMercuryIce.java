/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.core.blocks.BlockIceMP;
import stevekung.mods.moreplanets.core.util.WorldUtilMP;
import stevekung.mods.moreplanets.planets.mercury.dimension.WorldProviderMercury;

public class BlockMercuryIce extends BlockIceMP
{
    public BlockMercuryIce(String name)
    {
        super(Material.ice);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("mercury:mercury_ice");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, player, x, y, z, meta) && EnchantmentHelper.getSilkTouchModifier(player))
        {
            ArrayList<ItemStack> items = new ArrayList();
            ItemStack itemstack = this.createStackedBlock(meta);

            if (itemstack != null)
            {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, this, x, y, z, meta, 0, 1.0F, true, player);

            for (ItemStack is : items)
            {
                this.dropBlockAsItem(world, x, y, z, is);
            }
        }
        else
        {
            if (world.provider.isHellWorld)
            {
                world.setBlockToAir(x, y, z);
                return;
            }
            else if (WorldUtilMP.isSpaceWorld(world, new WorldProviderMercury()) && world.isDaytime() && world.canBlockSeeTheSky(x, y, z))
            {
                world.setBlockToAir(x, y, z);
                return;
            }

            int i1 = EnchantmentHelper.getFortuneModifier(player);
            this.harvesters.set(player);
            this.dropBlockAsItem(world, x, y, z, meta, i1);
            this.harvesters.set(null);
            Material material = world.getBlock(x, y - 1, z).getMaterial();

            if (material.blocksMovement() || material.isLiquid())
            {
                world.setBlock(x, y, z, MercuryBlocks.dirty_water);
            }
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11 - this.getLightOpacity())
        {
            if (world.provider.isHellWorld)
            {
                world.setBlockToAir(x, y, z);
                return;
            }
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, MercuryBlocks.dirty_water);
        }
        if (WorldUtilMP.isSpaceWorld(world, new WorldProviderMercury()) && world.isDaytime() && world.canBlockSeeTheSky(x, y, z))
        {
            world.setBlockToAir(x, y, z);
            return;
        }
    }
}
/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;

public class BlockOilRock extends Block implements IDetectableResource
{
    private IIcon[] nibiruBlockIcon;

    public BlockOilRock(String name)
    {
        super(Material.rock);
        this.setHardness(3.25F);
        this.setResistance(3.5F);
        this.setBlockName(name);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.nibiruBlockIcon = new IIcon[4];
        this.nibiruBlockIcon[0] = par1IconRegister.registerIcon("nibiru:oil_rock");
        this.nibiruBlockIcon[1] = par1IconRegister.registerIcon("nibiru:oil_ore");
        this.nibiruBlockIcon[2] = par1IconRegister.registerIcon("nibiru:nibiru_surface_rock");
        this.nibiruBlockIcon[3] = par1IconRegister.registerIcon("nibiru:nibiru_sub_surface_rock");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        switch (meta)
        {
        case 0:
            switch (side)
            {
            case 0:
                return this.nibiruBlockIcon[3]; //BOTTOM
            case 1:
                return this.nibiruBlockIcon[0]; //TOP
            case 2:
                return this.nibiruBlockIcon[2]; //Z-
            case 3:
                return this.nibiruBlockIcon[2]; //Z+
            case 4:
                return this.nibiruBlockIcon[2]; //X-
            case 5:
                return this.nibiruBlockIcon[2]; //X+
            default:
                return this.nibiruBlockIcon[0];
            }
        case 1:
            return this.nibiruBlockIcon[1];
        }
        return this.nibiruBlockIcon[0];
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public int getMobilityFlag()
    {
        return 0;
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return super.canHarvestBlock(player, meta);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
    }

    @Override
    public boolean isValueable(int metadata)
    {
        return true;
    }

    @Override
    public void harvestBlock(World par1World, EntityPlayer player, int par3, int par4, int par5, int par6)
    {
        player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(player))
        {
            ItemStack itemstack = this.createStackedBlock(par6);

            if (itemstack != null)
            {
                this.dropBlockAsItem(par1World, par3, par4, par5, itemstack);
            }
        }
        else
        {
            int i1 = EnchantmentHelper.getFortuneModifier(player);
            this.dropBlockAsItem(par1World, par3, par4, par5, par6, i1);
            Material material = par1World.getBlock(par3, par4 - 1, par5).getMaterial();

            if (material.blocksMovement() || material.isLiquid())
            {
                par1World.setBlock(par3, par4, par5, GCBlocks.crudeOil, 0, 3);
            }
        }
        MorePlanetEvents.addInfectedGas(player);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.motionX *= 0.4D;
        par5Entity.motionZ *= 0.4D;
    }
}
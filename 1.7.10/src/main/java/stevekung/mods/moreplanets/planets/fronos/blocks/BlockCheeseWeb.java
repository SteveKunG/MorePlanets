/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockCheeseWeb extends BlockBaseMP
{
    public BlockCheeseWeb(String name)
    {
        super(Material.web);
        this.setBlockName(name);
        this.setLightOpacity(1);
        this.setHardness(4.0F);
        this.setBlockTextureName("fronos:cheese_web");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.setInWeb();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 9;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return FronosItems.fronos_item;
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int metadata)
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack == null)
        {
            return player.canHarvestBlock(this);
        }
        return stack != null && (stack.getItem() instanceof ItemShears || stack.getItem() instanceof ItemSword);
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack != null && (stack.getItem() instanceof ItemShears || stack.getItem() instanceof ItemSword))
        {
            return 0.1F;
        }
        return ForgeHooks.blockStrength(this, player, world, x, y, z);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition mov, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, 0);
    }
}
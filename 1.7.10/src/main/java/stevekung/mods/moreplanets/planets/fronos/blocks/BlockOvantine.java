/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockOvantine extends BlockFalling
{
    public BlockOvantine(String name)
    {
        super();
        this.setStepSound(Block.soundTypeSand);
        this.setHardness(0.55F);
        this.setBlockName(name);
        this.setBlockTextureName("fronos:ovantine_block");
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return FronosItems.fronos_food2;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 4;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float yOffset = 0.125F;
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - yOffset, z + 1);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.motionX *= 0.4D;
        entity.motionZ *= 0.4D;
    }
}
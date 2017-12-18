/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ItemBlockOrangeCreamLayer extends ItemBlockMorePlanet
{
    public ItemBlockOrangeCreamLayer(Block block)
    {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (itemStack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, side, itemStack))
        {
            return false;
        }
        else
        {
            Block block = world.getBlock(x, y, z);

            if (block == FronosBlocks.orange_cream_layer)
            {
                int meta = world.getBlockMetadata(x, y, z) & 7;

                if (meta <= 6 && world.checkNoEntityCollision(block.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlockMetadataWithNotify(x, y, z, meta + 1 | meta & -8, 2))
                {
                    world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --itemStack.stackSize;
                    return true;
                }
            }
            return super.onItemUse(itemStack, player, world, x, y, z, side, par8, par9, par10);
        }
    }
}
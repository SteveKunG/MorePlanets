/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.integration;

import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.siriusb.entities.projectiles.EntitySiriusSmallFireball;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;

public class DispenserRegistryMP
{
    public static void init()
    {
        BlockDispenser.dispenseBehaviorRegistry.putObject(SiriusBItems.sirius_fire_charge, new BehaviorDefaultDispenseItem()
        {
            @Override
            public ItemStack dispenseStack(IBlockSource blockSource, ItemStack itemStack)
            {
                EnumFacing facing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata());
                IPosition iposition = BlockDispenser.func_149939_a(blockSource);
                World world = blockSource.getWorld();
                Random rand = world.rand;
                double d0 = iposition.getX() + facing.getFrontOffsetX() * 0.3F;
                double d1 = iposition.getY() + facing.getFrontOffsetX() * 0.3F;
                double d2 = iposition.getZ() + facing.getFrontOffsetZ() * 0.3F;
                double d3 = rand.nextGaussian() * 0.05D + facing.getFrontOffsetX();
                double d4 = rand.nextGaussian() * 0.05D + facing.getFrontOffsetY();
                double d5 = rand.nextGaussian() * 0.05D + facing.getFrontOffsetZ();
                world.spawnEntityInWorld(new EntitySiriusSmallFireball(world, d0, d1, d2, d3, d4, d5));
                itemStack.splitStack(1);
                return itemStack;
            }

            @Override
            protected void playDispenseSound(IBlockSource blockSource)
            {
                blockSource.getWorld().playAuxSFX(1009, blockSource.getXInt(), blockSource.getYInt(), blockSource.getZInt(), 0);
            }
        });
    }
}
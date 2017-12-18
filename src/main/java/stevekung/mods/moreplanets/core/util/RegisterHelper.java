/*******************************************************************************
 * Copyright 2015 SteveKunG - Steve's Core
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RegisterHelper
{
    static int id = 0;

    public static void registerBlock(Block block)
    {
        if (Loader.isModLoaded("GalacticraftCore"))
        {
            try
            {
                Class<?> clazz = Class.forName("micdoodle8.mods.galacticraft.core.items.ItemBlockGC");
                GameRegistry.registerBlock(block, (Class)clazz, block.getUnlocalizedName().substring(5));
            }
            catch (ClassNotFoundException e)
            {
                GameRegistry.registerBlock(block, ItemBlock.class, block.getUnlocalizedName().substring(5));
            }
        }
        else
        {
            GameRegistry.registerBlock(block, ItemBlock.class, block.getUnlocalizedName().substring(5));
        }
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock)
    {
        GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().substring(5));
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock, Object... objectFromItemBlock)
    {
        GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().substring(5), objectFromItemBlock);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
    }

    public static void registerItem(Item item)
    {
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }

    public static void registerFluidContainer(Fluid fluid, ItemStack filledContainer, ItemStack emptyContainer)
    {
        FluidContainerRegistry.registerFluidContainer(new FluidContainerData(new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME), filledContainer, emptyContainer));
    }

    public static void setFireBurn(Block block, int encouragement, int flammibility)
    {
        Blocks.fire.setFireInfo(block, encouragement, flammibility);
    }

    @Deprecated
    public static void registerEntity(Class<? extends Entity> var0, String var1, int color1, int color2)
    {
        EntityRegistry.registerGlobalEntityID(var0, var1, EntityRegistry.findGlobalUniqueEntityId(), color1, color2);
    }

    public static void registerNonMobEntity(Class<? extends Entity> var0, String var1, Object mod, boolean sendVel)
    {
        EntityRegistry.registerModEntity(var0, var1, id++, mod, 256, 1, sendVel);
    }
}
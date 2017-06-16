/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.nei;

import java.util.List;

import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.api.ItemInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.asteroids.darkasteroids.blocks.DarkAsteroidsBlocks;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;

public class NEIHighlightHandlerMP implements IHighlightHandler
{
    @Override
    public List<String> handleTextData(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop, List<String> currenttip, ItemInfo.Layout layout)
    {
        return currenttip;
    }

    @Override
    public ItemStack identifyHighlight(World world, EntityPlayer player, MovingObjectPosition mop)
    {
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        if (block == MPBlocks.stone_slab_full)
        {
            return new ItemStack(MPBlocks.stone_slab_half, 1, meta);
        }
        if (block == MPBlocks.stone_slab_full2)
        {
            return new ItemStack(MPBlocks.stone_slab_half2, 1, meta);
        }
        if (block == MPBlocks.wooden_slab_full)
        {
            return new ItemStack(MPBlocks.wooden_slab_half, 1, meta);
        }
        if (block == MPBlocks.dungeon_brick_slab_full)
        {
            return new ItemStack(MPBlocks.dungeon_brick_slab_half, 1, meta);
        }
        if (block == DionaBlocks.diona_block && meta == 8)
        {
            return new ItemStack(DionaBlocks.diona_block, 1, 8);//Silicon Ore
        }
        if (block == PolongniusBlocks.polongnius_block)
        {
            return new ItemStack(PolongniusBlocks.polongnius_block, 1, meta);//Polongnius Block
        }
        if (block == NibiruBlocks.nibiru_block)
        {
            return new ItemStack(NibiruBlocks.nibiru_block, 1, meta);//Nibiru Block
        }
        if (block == NibiruBlocks.ancient_dark_leaves)
        {
            return new ItemStack(NibiruBlocks.ancient_dark_leaves, 1, meta);//Ancient Dark Leaves
        }
        if (block == NibiruBlocks.orange_leaves)
        {
            return new ItemStack(NibiruBlocks.orange_leaves, 1, meta);//Orange Leaves
        }
        if (block == KoentusBlocks.koentus_block)
        {
            return new ItemStack(KoentusBlocks.koentus_block, 1, meta);//Koentus Block
        }
        if (block == KoentusBlocks.koentus_ice && meta == 1)
        {
            return new ItemStack(KoentusBlocks.koentus_ice, 1, 1);//Glowing Koentus Ice
        }
        if (block == FronosBlocks.fronos_block)
        {
            return new ItemStack(FronosBlocks.fronos_block, 1, meta);//Fronos Block
        }
        if (block == FronosBlocks.frosted_cake)
        {
            return new ItemStack(FronosBlocks.frosted_cake, 1, meta);//Frosted Cake
        }
        if (block == FronosBlocks.fronos_tall_grass)
        {
            return new ItemStack(FronosBlocks.fronos_tall_grass, 1, meta);//Fronos Tall Grass
        }
        if (block == KapteynBBlocks.kapteyn_b_block)
        {
            return new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, meta);//Kapteyn B Blocks
        }
        if (block == KapteynBBlocks.kapteyn_b_ice && meta == 1)
        {
            return new ItemStack(KapteynBBlocks.kapteyn_b_ice, 1, 1);//Dirty Ice
        }
        if (block == KapteynBBlocks.uranium_waste && meta == 1)
        {
            return new ItemStack(KapteynBBlocks.uranium_waste, 1, 1);//Uranium Waste
        }
        if (block == SiriusBBlocks.sirius_b_block)
        {
            return new ItemStack(SiriusBBlocks.sirius_b_block, 1, meta);//Sirius B Block
        }
        if (block == MercuryBlocks.mercury_block)
        {
            return new ItemStack(MercuryBlocks.mercury_block, 1, meta);
        }
        if (block == VenusBlocks.venus_block)
        {
            return new ItemStack(VenusBlocks.venus_block, 1, meta);
        }
        if (block == VenusBlocks.venus_redstone_ore_active)
        {
            return new ItemStack(VenusBlocks.venus_redstone_ore, 1, 0);
        }
        if (block == PlutoBlocks.pluto_block)
        {
            return new ItemStack(PlutoBlocks.pluto_block, 1, meta);
        }
        if (block == DarkAsteroidsBlocks.dark_asteroid_block)
        {
            return new ItemStack(DarkAsteroidsBlocks.dark_asteroid_block, 1, meta);
        }
        if (block == FronosBlocks.fronos_block_1)
        {
            return new ItemStack(FronosBlocks.fronos_block_1, 1, meta);
        }
        return null;
    }
}
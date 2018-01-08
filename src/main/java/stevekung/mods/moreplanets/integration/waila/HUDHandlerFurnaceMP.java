package stevekung.mods.moreplanets.integration.waila;

import java.util.List;

import mcp.mobius.waila.api.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityFurnaceMP;

public class HUDHandlerFurnaceMP implements IWailaDataProvider
{
    public static void register(IWailaRegistrar register)
    {
        register.addConfig(WailaUtil.WAILA_CONSTANT, "vanilla.furnacedisplay", true);
        WailaUtil.register(TileEntityFurnaceMP.class, new HUDHandlerFurnaceMP(), true, false, true, false, false);
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        NBTTagCompound nbt = accessor.getNBTData();
        TileEntity tile = accessor.getTileEntity();

        if (tile instanceof TileEntityFurnaceMP)
        {
            if (!config.getConfig("vanilla.furnacedisplay") || accessor.getBlock() != NibiruBlocks.NIBIRU_LIT_FURNACE && accessor.getBlock() != NibiruBlocks.TERRASTONE_LIT_FURNACE)
            {
                return tooltip;
            }

            int cookTime = nbt.getShort("CookTime");
            NBTTagList itemTag = nbt.getTagList("Items", 10);
            ItemStack[] inventory = new ItemStack[3];
            String text = "";

            for (int i = 0; i < itemTag.tagCount(); i++)
            {
                NBTTagCompound tagCompound = itemTag.getCompoundTagAt(i);
                byte slot = tagCompound.getByte("Slot");
                ItemStack stack = new ItemStack(tagCompound);
                inventory[slot] = stack;
            }

            if (inventory[0] != null)
            {
                String name = inventory[0].getItem().getRegistryName().toString();
                text += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory[0].getCount()), String.valueOf(inventory[0].getItemDamage()));
            }
            else
            {
                text += SpecialChars.getRenderString("waila.stack", "2");
            }

            if (inventory[1] != null)
            {
                String name = inventory[1].getItem().getRegistryName().toString();
                text += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory[1].getCount()), String.valueOf(inventory[1].getItemDamage()));
            }
            else
            {
                text += SpecialChars.getRenderString("waila.stack", "2");
            }

            text += SpecialChars.getRenderString("waila.progress", String.valueOf(cookTime), String.valueOf(200));

            if (inventory[2] != null)
            {
                String name = inventory[2].getItem().getRegistryName().toString();
                text += SpecialChars.getRenderString("waila.stack", "1", name, String.valueOf(inventory[2].getCount()), String.valueOf(inventory[2].getItemDamage()));
            }
            else
            {
                text += SpecialChars.getRenderString("waila.stack", "2");
            }
            tooltip.add(text);
        }
        return tooltip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, BlockPos pos)
    {
        return tile.writeToNBT(tag);
    }
}
package stevekung.mods.moreplanets.items;

import java.util.List;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemSpaceWarperCore extends ItemBaseMP
{
    public ItemSpaceWarperCore(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.worldObj.isRemote)
        {
            if (player.isSneaking())
            {
                if (GCCoreUtil.getDimensionID(world) == 0 || world.provider instanceof IGalacticraftWorldProvider)
                {
                    if (itemStack.getTagCompound() == null)
                    {
                        itemStack.setTagCompound(new NBTTagCompound());
                    }
                    if (world.getBlockState(new BlockPos(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ))) == MPBlocks.SPACE_WARP_PAD_FULL.getDefaultState())
                    {
                        itemStack.getTagCompound().setInteger("DimensionID", GCCoreUtil.getDimensionID(world));
                        itemStack.getTagCompound().setInteger("X", MathHelper.floor_double(player.posX));
                        itemStack.getTagCompound().setInteger("Y", MathHelper.floor_double(player.posY));
                        itemStack.getTagCompound().setInteger("Z", MathHelper.floor_double(player.posZ));
                        itemStack.getTagCompound().setBoolean("Checked", true);
                        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("gui.warp_core_data_add.message")));
                        return itemStack;
                    }
                    else if (world.getBlockState(new BlockPos(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ))) == MPBlocks.DUMMY_BLOCK.getDefaultState())
                    {
                        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("gui.warp_core_data_add_fail1.message")));
                    }
                    else
                    {
                        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("gui.warp_core_data_add_fail0.message")));
                    }
                }
                else
                {
                    player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("gui.space_dimension_only.message")));
                }
            }
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        if (!itemStack.hasTagCompound())
        {
            return;
        }
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("DimensionID") || itemStack.getTagCompound().hasKey("X") || itemStack.getTagCompound().hasKey("Y") || itemStack.getTagCompound().hasKey("Z"))
        {
            list.add("X: " + itemStack.getTagCompound().getInteger("X"));
            list.add("Y: " + itemStack.getTagCompound().getInteger("Y"));
            list.add("Z: " + itemStack.getTagCompound().getInteger("Z"));
            list.add("Dimension ID: " + itemStack.getTagCompound().getInteger("DimensionID"));
            list.add("Dimension Name: " + WorldUtil.getProviderForDimensionClient(itemStack.getTagCompound().getInteger("DimensionID")).getDimensionName());
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        if (item.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(item.posX), MathHelper.floor_double(item.posY), MathHelper.floor_double(item.posZ))).getBlock().getMaterial() == Material.water)
        {
            if (item.ticksExisted % 20 == 0)
            {
                item.setEntityItemStack(new ItemStack(this));
            }
        }
        return false;
    }

    @Override
    public String getName()
    {
        return "space_warper_core";
    }
}
package stevekung.mods.moreplanets.itemblocks;

import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.blocks.BlockBlackHoleStorage;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;

public class ItemBlockBlackHoleStorage extends ItemBlockDescription
{
    public ItemBlockBlackHoleStorage(Block block)
    {
        super(block);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, IBlockState state)
    {
        for (int y = -24; y <= 24; y++)
        {
            for (int x = -24; x <= 24; x++)
            {
                for (int z = -24; z <= 24; z++)
                {
                    BlockPos posAt = pos.add(x, y, z);
                    Block block = world.getBlockState(posAt).getBlock();

                    for (int y2 = 0; y2 < 3; y2++)
                    {
                        BlockPos posAt1 = pos.add(0, y2, 0);
                        Block block1 = world.getBlockState(posAt1).getBlock();

                        if (world.getBlockState(posAt1).getMaterial() != Material.AIR && !block1.isReplaceable(world, posAt1))
                        {
                            if (world.isRemote)
                            {
                                FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtil().text(I18n.format("gui.warning.noroom")).setStyle(new JsonUtil().red()).getFormattedText(), false);
                            }
                            return false;
                        }
                    }
                    if (block == MPBlocks.BLACK_HOLE_STORAGE)
                    {
                        if (world.isRemote)
                        {
                            FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtil().text(I18n.format("gui.bh_storage.too_close.message")).setStyle(new JsonUtil().red()).getFormattedText(), false);
                        }
                        return false;
                    }
                }
            }
        }
        return super.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean advanced)
    {
        if (this.getBlock() instanceof IBlockDescription)
        {
            if (CommonRegisterHelper.isShiftKeyDown())
            {
                ((IBlockDescription)this.block).getDescription().addDescription(itemStack, list);
            }
            else if (CommonRegisterHelper.isControlKeyDown())
            {
                if (this.getBlock() instanceof BlockBlackHoleStorage)
                {
                    TileEntity tile = ((BlockBlackHoleStorage) this.getBlock()).createTileEntity(null, this.getBlock().getDefaultState());

                    if (tile instanceof TileEntityBlackHoleStorage)
                    {
                        TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;

                        if (itemStack.hasTagCompound())
                        {
                            List<ItemStack> stackList = Arrays.asList(storage.inventory);
                            ItemBlockBlackHoleStorage.loadAllItems(itemStack.getTagCompound(), stackList);
                            int i = 0;
                            int j = 0;

                            for (ItemStack invStack : stackList)
                            {
                                if (invStack != null)
                                {
                                    ++j;

                                    if (i < 8)
                                    {
                                        ++i;
                                        list.add(invStack.getDisplayName() + " x" + invStack.stackSize);
                                    }
                                }
                            }
                            if (j - i > 0)
                            {
                                list.add(TextFormatting.ITALIC + GCCoreUtil.translateWithFormat("desc.bhs_more.name", Integer.valueOf(j - i)));
                            }
                        }
                    }
                }
            }
            else
            {
                if (this.getBlock() instanceof BlockBlackHoleStorage)
                {
                    TileEntity tile = ((BlockBlackHoleStorage) this.getBlock()).createTileEntity(null, this.getBlock().getDefaultState());

                    if (tile instanceof TileEntityBlackHoleStorage)
                    {
                        TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;

                        if (itemStack.hasTagCompound())
                        {
                            NBTTagCompound nbt = itemStack.getTagCompound();

                            if (nbt.hasKey("Disable") && nbt.hasKey("Mode") && nbt.hasKey("XP") && nbt.hasKey("Hopper") && nbt.hasKey("Items"))
                            {
                                String mode = nbt.getString("Mode").equals("item") ? "Item" : nbt.getString("Mode").equals("item_and_xp") ? "Item/EXP" : "EXP";
                                TextFormatting disable = nbt.getBoolean("Disable") ? TextFormatting.GREEN : TextFormatting.RED;
                                TextFormatting hopper = nbt.getBoolean("Hopper") ? TextFormatting.GREEN : TextFormatting.RED;
                                list.add(GCCoreUtil.translate("desc.bhs_disable.name") + ": " + disable + nbt.getBoolean("Disable"));
                                list.add(GCCoreUtil.translate("desc.bhs_hopper.name") + ": " + hopper + nbt.getBoolean("Hopper"));
                                list.add(GCCoreUtil.translate("desc.bhs_collect_mode.name") + ": " + TextFormatting.AQUA + mode);
                                list.add(GCCoreUtil.translate("desc.bhs_xp.name") + ": " + TextFormatting.GREEN + nbt.getInteger("XP") + "/" + storage.getMaxXP());

                                NBTTagList nbtlist = nbt.getTagList("Items", 10);
                                int slot = 0;

                                for (int i = 0; i < nbtlist.tagCount(); ++i)
                                {
                                    nbt = nbtlist.getCompoundTagAt(i);
                                    slot = nbt.getByte("Slot");

                                    if (slot >= 0 && slot < 108)
                                    {
                                        slot = slot + 1;
                                    }
                                }
                                list.add(GCCoreUtil.translate("desc.bhs_slot_used.name") + ": " + TextFormatting.GOLD + slot + "/" + storage.getSizeInventory());
                            }
                        }
                    }
                }
                list.add(GCCoreUtil.translate("desc.shift_info.name"));
                list.add(GCCoreUtil.translate("desc.control_info.name"));
            }
        }
    }

    private static void loadAllItems(NBTTagCompound tag, List<ItemStack> list)
    {
        NBTTagList tagList = tag.getTagList("Items", 10);

        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound compound = tagList.getCompoundTagAt(i);
            int slot = compound.getByte("Slot") & 255;

            if (slot >= 0 && slot < list.size())
            {
                list.set(slot, ItemStack.loadItemStackFromNBT(compound));
            }
        }
    }
}
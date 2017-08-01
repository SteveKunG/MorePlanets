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
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.util.JsonUtil;
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        JsonUtil json = new JsonUtil();
        ItemStack itemStack = player.getHeldItem(hand);

        if (!player.world.isRemote)
        {
            if (player.isSneaking())
            {
                if (GCCoreUtil.getDimensionID(world) == 0 || world.provider instanceof IGalacticraftWorldProvider)
                {
                    if (!itemStack.hasTagCompound())
                    {
                        itemStack.setTagCompound(new NBTTagCompound());
                    }
                    if (world.getBlockState(new BlockPos(MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ))) == MPBlocks.SPACE_WARP_PAD_FULL.getDefaultState())
                    {
                        itemStack.getTagCompound().setInteger("DimensionID", GCCoreUtil.getDimensionID(world));
                        itemStack.getTagCompound().setInteger("X", MathHelper.floor(player.posX));
                        itemStack.getTagCompound().setInteger("Y", MathHelper.floor(player.posY));
                        itemStack.getTagCompound().setInteger("Z", MathHelper.floor(player.posZ));
                        itemStack.getTagCompound().setBoolean("Checked", true);
                        player.sendMessage(json.text(GCCoreUtil.translate("gui.warp_core_data_add.message")));
                        return new ActionResult(EnumActionResult.SUCCESS, itemStack);
                    }
                    else if (world.getBlockState(new BlockPos(MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ))) == MPBlocks.DUMMY_BLOCK.getDefaultState())
                    {
                        player.sendMessage(json.text(GCCoreUtil.translate("gui.warp_core_data_add_fail1.message")).setStyle(json.red()));
                    }
                    else
                    {
                        player.sendMessage(json.text(GCCoreUtil.translate("gui.warp_core_data_add_fail0.message")).setStyle(json.red()));
                    }
                }
                else
                {
                    player.sendMessage(json.text(GCCoreUtil.translate("gui.space_dimension_only.message")).setStyle(json.red()));
                }
            }
        }
        return new ActionResult(EnumActionResult.PASS, itemStack);
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
            list.add("Dimension Name: " + WorldUtil.getProviderForDimensionClient(itemStack.getTagCompound().getInteger("DimensionID")).getDimensionType().getName());
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        if (item.world.getBlockState(new BlockPos(MathHelper.floor(item.posX), MathHelper.floor(item.posY), MathHelper.floor(item.posZ))).getMaterial() == Material.WATER)
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
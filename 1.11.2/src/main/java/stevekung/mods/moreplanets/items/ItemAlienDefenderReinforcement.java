package stevekung.mods.moreplanets.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemAlienDefenderReinforcement extends ItemBaseMP
{
    public ItemAlienDefenderReinforcement(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        int range = 16;
        Vec3d playerEye = player.getPositionEyes(1.0F);
        Vec3d playerLook = player.getLook(1.0F);
        Vec3d lookRange = playerEye.addVector(playerLook.xCoord * range, playerLook.yCoord * range, playerLook.zCoord * range);
        RayTraceResult moving = world.rayTraceBlocks(playerEye, lookRange);
        boolean disable = false;

        if (moving != null && disable)
        {
            BlockPos pos = moving.getBlockPos();

            if (world.isAirBlock(pos.up()))
            {
                if (world.isRemote)
                {
                    FMLClientHandler.instance().getClient().ingameGUI.setOverlayMessage(new JsonUtil().text(I18n.format("gui.alien_defender_beacon.message")).setStyle(new JsonUtil().colorFromConfig("yellow")).getFormattedText(), false);
                    player.swingArm(hand);
                }
                else
                {
                    world.setBlockState(pos.up(), MPBlocks.ALIEN_DEFENDER_BEACON.getDefaultState());
                    TileEntityAlienDefenderBeacon beacon = (TileEntityAlienDefenderBeacon) world.getTileEntity(pos.up());
                    beacon.bossCountdown = 200 + world.rand.nextInt(400);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.shrink(1);
                    }
                }
            }
            else
            {
                if (world.isRemote)
                {
                    FMLClientHandler.instance().getClient().ingameGUI.setOverlayMessage(new JsonUtil().text(I18n.format("gui.not_air_block.message")).setStyle(new JsonUtil().red()).getFormattedText(), false);
                    player.swingArm(hand);
                }
            }
        }
        else
        {
            if (world.isRemote && disable)
            {
                FMLClientHandler.instance().getClient().ingameGUI.setOverlayMessage(new JsonUtil().text(I18n.format("gui.target_too_far.message", range)).setStyle(new JsonUtil().red()).getFormattedText(), false);
                player.swingArm(hand);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, itemStack);
    }

    @Override
    public String getName()
    {
        return "alien_defender_reinforcement";
    }
}
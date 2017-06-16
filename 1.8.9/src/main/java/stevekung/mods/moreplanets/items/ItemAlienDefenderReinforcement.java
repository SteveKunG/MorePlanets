package stevekung.mods.moreplanets.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.util.JsonUtils;
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
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        int range = 16;
        Vec3 playerEye = player.getPositionEyes(1.0F);
        Vec3 playerLook = player.getLook(1.0F);
        Vec3 lookRange = playerEye.addVector(playerLook.xCoord * range, playerLook.yCoord * range, playerLook.zCoord * range);
        MovingObjectPosition moving = world.rayTraceBlocks(playerEye, lookRange);

        if (moving != null)
        {
            BlockPos pos = moving.getBlockPos();

            if (world.isAirBlock(pos.up()))
            {
                if (world.isRemote)
                {
                    FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.alien_defender_beacon.message")).setChatStyle(new JsonUtils().colorFromConfig("yellow")).getFormattedText(), false);
                    player.swingItem();
                }
                else
                {
                    world.setBlockState(pos.up(), MPBlocks.ALIEN_DEFENDER_BEACON.getDefaultState());
                    TileEntityAlienDefenderBeacon beacon = (TileEntityAlienDefenderBeacon) world.getTileEntity(pos.up());
                    beacon.bossCountdown = 200 + world.rand.nextInt(400);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.stackSize--;
                    }
                }
            }
            else
            {
                if (world.isRemote)
                {
                    FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.not_air_block.message")).setChatStyle(new JsonUtils().red()).getFormattedText(), false);
                    player.swingItem();
                }
            }
        }
        else
        {
            if (world.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.setRecordPlaying(new JsonUtils().text(I18n.format("gui.target_too_far.message", range)).setChatStyle(new JsonUtils().red()).getFormattedText(), false);
                player.swingItem();
            }
        }
        return itemStack;
    }

    @Override
    public String getName()
    {
        return "alien_defender_reinforcement";
    }
}
package stevekung.mods.moreplanets.core.todo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import micdoodle8.mods.galacticraft.core.network.IPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.venus.items.ItemJetpack;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class PacketUpdateJetpack implements IPacket
{
    private float power;

    public PacketUpdateJetpack() {}

    public PacketUpdateJetpack(float power)
    {
        this.power = power;
    }

    @Override
    public void encodeInto(ChannelHandlerContext context, ByteBuf buffer)
    {
        buffer.writeFloat(this.power);
    }

    @Override
    public void decodeInto(ChannelHandlerContext context, ByteBuf buffer)
    {
        this.power = buffer.readFloat();
    }

    @Override
    public void handleClientSide(EntityPlayer player)
    {
        if (player != null)
        {
            if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == VenusItems.jetpack)
            {
                float electric = this.power;
                boolean flag = player.capabilities.isCreativeMode;
                ItemJetpack jetpack = (ItemJetpack) player.inventory.armorItemInSlot(2).getItem();
                ItemStack itemStack = player.inventory.armorItemInSlot(2);

                if (!flag && jetpack.getElectricityStored(itemStack) != 0.0F)
                {
                    if (jetpack.getJetpackKeyDown() || jetpack.getJetpackKeySneak())
                    {
                        jetpack.setElectricity(itemStack, jetpack.getElectricityStored(itemStack) - electric);
                    }
                }
            }
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        if (player != null)
        {
            if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == VenusItems.jetpack)
            {
                float electric = this.power;
                boolean flag = player.capabilities.isCreativeMode;
                ItemJetpack jetpack = (ItemJetpack) player.inventory.armorItemInSlot(2).getItem();
                ItemStack itemStack = player.inventory.armorItemInSlot(2);

                if (!flag && jetpack.getElectricityStored(itemStack) != 0.0F)
                {
                    if (jetpack.getJetpackKeyDown() || jetpack.getJetpackKeySneak())
                    {
                        jetpack.setElectricity(itemStack, jetpack.getElectricityStored(itemStack) - electric);
                    }
                }
            }
        }
    }
}
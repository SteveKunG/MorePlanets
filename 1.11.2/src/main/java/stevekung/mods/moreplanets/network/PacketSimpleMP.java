package stevekung.mods.moreplanets.network;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.network.NetworkUtil;
import micdoodle8.mods.galacticraft.core.network.PacketBase;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.core.handler.TeleportHandler;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.helper.WorldDimensionHelper;

public class PacketSimpleMP extends PacketBase
{
    private EnumSimplePacketMP type;
    private List<Object> data;

    public PacketSimpleMP()
    {
        super();
    }

    public PacketSimpleMP(EnumSimplePacketMP packetType, int dimID, Object... data)
    {
        this(packetType, dimID, Arrays.asList(data));
    }

    public PacketSimpleMP(EnumSimplePacketMP packetType, int dimID, List<Object> data)
    {
        super(dimID);

        if (packetType.getDecodeClasses().length != data.size())
        {
            MPLog.warning("More Planets Simple Packet found data length different than packet type: %s", packetType.name());
        }
        this.type = packetType;
        this.data = data;
    }

    @Override
    public void encodeInto(ByteBuf buffer)
    {
        super.encodeInto(buffer);
        buffer.writeInt(this.type.ordinal());

        try
        {
            NetworkUtil.encodeData(buffer, this.data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void decodeInto(ByteBuf buffer)
    {
        super.decodeInto(buffer);
        this.type = EnumSimplePacketMP.valuesCached()[buffer.readInt()];

        if (this.type.getDecodeClasses().length > 0)
        {
            this.data = NetworkUtil.decodeData(this.type.getDecodeClasses(), buffer);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer player)
    {
        switch (this.type)
        {
        default:
            break;
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        World world = player.world;
        TileEntity tile;
        BlockPos pos;

        switch (this.type)
        {
        case S_FIRE_EXTINGUISH:
            pos = (BlockPos) this.data.get(0);
            world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
            world.setBlockToAir(pos);
            break;
        case S_RESPAWN_PLAYER_NETHER:
            if (world instanceof WorldServer && player instanceof EntityPlayerMP)
            {
                WorldServer worldOld = (WorldServer) world;
                EntityPlayerMP playerMP = (EntityPlayerMP) player;
                WorldServer worldNew = WorldDimensionHelper.getStartWorld(worldOld);
                BlockPos spawnPos = worldNew.getTopSolidOrLiquidBlock(worldNew.getSpawnPoint());
                TeleportHandler.setWarpDimension(playerMP, worldNew, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), WorldUtil.getProviderForNameServer(WorldTickEventHandler.startedDimensionData.planetToBack).getDimension(), true);
                player.respawnPlayer();
                player.closeScreen();
            }
            break;
        case S_BLACK_HOLE_STORAGE_OPTION:
            tile = world.getTileEntity((BlockPos) this.data.get(0));
            String type = (String) this.data.get(1);

            if (tile instanceof TileEntityBlackHoleStorage)
            {
                TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;

                switch (type)
                {
                case "disable":
                    storage.disableBlackHole = !storage.disableBlackHole;
                    break;
                case "collect_mode":
                    storage.modeInt++;
                    storage.modeInt %= 3;

                    switch (storage.modeInt)
                    {
                    case 0:
                        storage.collectMode = "item";
                        break;
                    case 1:
                        storage.collectMode = "xp";
                        break;
                    case 2:
                        storage.collectMode = "item_and_xp";
                        break;
                    }
                    break;
                case "use_hopper":
                    storage.useHopper = !storage.useHopper;
                    break;
                }
            }
            break;
        case S_ADD_ENTITY_ID:
            String entityIDAdd = (String) this.data.get(0);
            if (!ClientEventHandler.entityId.contains(entityIDAdd))
            {
                ClientEventHandler.entityId.add(entityIDAdd);
            }
            break;
        case S_REMOVE_ENTITY_ID:
            String entityIDRemove = (String) this.data.get(0);
            ClientEventHandler.entityId.remove(entityIDRemove);
            break;
        default:
            break;
        }
    }

    public static enum EnumSimplePacketMP
    {
        // SERVER
        S_FIRE_EXTINGUISH(Side.SERVER, BlockPos.class),
        S_RESPAWN_PLAYER_NETHER(Side.SERVER),
        S_BLACK_HOLE_STORAGE_OPTION(Side.SERVER, BlockPos.class, String.class),
        S_ADD_ENTITY_ID(Side.SERVER, String.class),
        S_REMOVE_ENTITY_ID(Side.SERVER, String.class);

        // CLIENT
        //C_SWING_HAND(Side.CLIENT);

        private Side targetSide;
        private Class[] decodeAs;
        private static EnumSimplePacketMP[] values = EnumSimplePacketMP.values();

        private EnumSimplePacketMP(Side targetSide, Class... decodeAs)
        {
            this.targetSide = targetSide;
            this.decodeAs = decodeAs;
        }

        public Side getTargetSide()
        {
            return this.targetSide;
        }

        public Class[] getDecodeClasses()
        {
            return this.decodeAs;
        }

        public static EnumSimplePacketMP[] valuesCached()
        {
            return EnumSimplePacketMP.values;
        }
    }
}
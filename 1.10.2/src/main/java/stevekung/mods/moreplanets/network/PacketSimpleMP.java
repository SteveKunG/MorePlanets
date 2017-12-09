package stevekung.mods.moreplanets.network;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.NetworkUtil;
import micdoodle8.mods.galacticraft.core.network.PacketBase;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.gui.GuiShieldGenerator;
import stevekung.mods.moreplanets.client.gui.GuiShieldGeneratorConfig;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.core.handler.TeleportHandler;
import stevekung.mods.moreplanets.inventory.ContainerShieldGeneratorConfig;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
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
        BlockPos pos;

        switch (this.type)
        {
        case C_ADD_ENTITY_ID:
            String entityIDAdd = (String) this.data.get(0);
            if (!ClientEventHandler.entityId.contains(entityIDAdd))
            {
                ClientEventHandler.entityId.add(entityIDAdd);
            }
            break;
        case C_REMOVE_ENTITY_ID:
            String entityIDRemove = (String) this.data.get(0);
            ClientEventHandler.entityId.remove(entityIDRemove);
            break;
        case C_REMOVE_GUIDE_POS:
            pos = (BlockPos) this.data.get(0);
            ClientEventHandler.receiverRenderPos.remove(pos);
            break;
        case C_RELOAD_RENDERER:
            ClientEventHandler.loadRenderers = true;
            break;
        case C_SWITCH_SHIELD_GENERATOR_GUI:
            pos = (BlockPos) this.data.get(0);
            boolean isConfig = (boolean) this.data.get(2);

            if (pos != null)
            {
                TileEntity tile = player.worldObj.getTileEntity(pos);

                if (tile != null && tile instanceof TileEntityShieldGenerator)
                {
                    FMLClientHandler.instance().getClient().displayGuiScreen(isConfig ? new GuiShieldGeneratorConfig(player.inventory, (TileEntityShieldGenerator) tile) : new GuiShieldGenerator(player.inventory, (TileEntityShieldGenerator) tile));
                }
                player.openContainer.windowId = (Integer) this.data.get(1);
            }
            break;
        case C_REMOVE_GENERATOR_GUIDE_POS:
            pos = (BlockPos) this.data.get(0);
            ClientEventHandler.wasteRenderPos.remove(pos);
            break;
        default:
            break;
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        EntityPlayerMP playerMP = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
        World world = player.worldObj;
        TileEntity tile;
        BlockPos pos;
        String type;

        switch (this.type)
        {
        case S_FIRE_EXTINGUISH:
            pos = (BlockPos) this.data.get(0);
            world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
            world.setBlockToAir(pos);
            break;
        case S_RESPAWN_PLAYER_NETHER:
            if (world instanceof WorldServer)
            {
                WorldServer worldOld = (WorldServer) world;
                WorldServer worldNew = WorldDimensionHelper.getStartWorld(worldOld);
                BlockPos spawnPos = worldNew.getTopSolidOrLiquidBlock(worldNew.getSpawnPoint());
                TeleportHandler.setWarpDimension(playerMP, worldNew, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), WorldUtil.getProviderForNameServer(WorldTickEventHandler.startedDimensionData.planetToBack).getDimension(), true);
                player.respawnPlayer();
                player.closeScreen();
            }
            break;
        case S_BLACK_HOLE_STORAGE_OPTION:
            tile = world.getTileEntity((BlockPos) this.data.get(0));
            type = (String) this.data.get(1);

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
        case S_SHIELD_VISIBLE:
            tile = world.getTileEntity((BlockPos) this.data.get(0));

            if (tile instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;
                shield.setBubbleVisible((boolean) this.data.get(1));
            }
            break;
        case S_ENABLE_SHIELD:
            tile = world.getTileEntity((BlockPos) this.data.get(0));

            if (tile instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;
                shield.enableShield = !shield.enableShield;
            }
            break;
        case S_ENABLE_SHIELD_DAMAGE:
            tile = world.getTileEntity((BlockPos) this.data.get(0));

            if (tile instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;
                shield.enableDamage = !shield.enableDamage;
            }
            break;
        case S_SHIELD_GENERATOR_OPTION:
            tile = world.getTileEntity((BlockPos) this.data.get(0));
            int value = (int) this.data.get(1);
            type = (String) this.data.get(2);

            if (tile instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;

                switch (type)
                {
                case "damage":
                    shield.shieldDamage = value;
                    break;
                case "size":
                    shield.maxShieldSize = value;
                    break;
                }
            }
            break;
        case S_SWITCH_SHIELD_GENERATOR_GUI:
            tile = player.worldObj.getTileEntity((BlockPos) this.data.get(0));
            boolean isConfig = (boolean) this.data.get(1);

            if (tile instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;
                PacketSimpleMP.openShieldGeneratorConfig(playerMP, shield, isConfig);
            }
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
        S_SHIELD_VISIBLE(Side.SERVER, BlockPos.class, Boolean.class),
        S_ENABLE_SHIELD(Side.SERVER, BlockPos.class),
        S_ENABLE_SHIELD_DAMAGE(Side.SERVER, BlockPos.class),
        S_SHIELD_GENERATOR_OPTION(Side.SERVER, BlockPos.class, Integer.class, String.class),
        S_SWITCH_SHIELD_GENERATOR_GUI(Side.SERVER, BlockPos.class, Boolean.class),

        // CLIENT
        C_ADD_ENTITY_ID(Side.CLIENT, String.class),
        C_REMOVE_ENTITY_ID(Side.CLIENT, String.class),
        C_REMOVE_GUIDE_POS(Side.CLIENT, BlockPos.class),
        C_RELOAD_RENDERER(Side.CLIENT),
        C_SWITCH_SHIELD_GENERATOR_GUI(Side.CLIENT, BlockPos.class, Integer.class, Boolean.class),
        C_REMOVE_GENERATOR_GUIDE_POS(Side.CLIENT, BlockPos.class);

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

    public static void sendToAllAround(PacketSimpleMP packet, World world, int dimID, BlockPos pos, double radius)
    {
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;
        double r2 = radius * radius;

        for (EntityPlayer player : world.playerEntities)
        {
            if (player instanceof EntityPlayerMP)
            {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;

                if (playerMP.dimension == dimID)
                {
                    double dx = x - playerMP.posX;
                    double dy = y - playerMP.posY;
                    double dz = z - playerMP.posZ;

                    if (dx * dx + dy * dy + dz * dz < r2)
                    {
                        GalacticraftCore.packetPipeline.sendTo(packet, playerMP);
                    }
                }
            }
        }
    }

    private static void openShieldGeneratorConfig(EntityPlayerMP player, TileEntityShieldGenerator tile, boolean isConfig)
    {
        player.getNextWindowId();
        player.closeContainer();
        int windowId = player.currentWindowId;
        GalacticraftCore.packetPipeline.sendTo(new PacketSimpleMP(EnumSimplePacketMP.C_SWITCH_SHIELD_GENERATOR_GUI, GCCoreUtil.getDimensionID(player.worldObj), tile.getPos(), windowId, isConfig), player);
        player.openContainer = new ContainerShieldGeneratorConfig(player.inventory, tile);
        player.openContainer.windowId = windowId;
        player.openContainer.addListener(player);
    }
}
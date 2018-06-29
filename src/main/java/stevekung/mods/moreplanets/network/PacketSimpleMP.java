package stevekung.mods.moreplanets.network;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.network.NetworkUtil;
import micdoodle8.mods.galacticraft.core.network.PacketBase;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemRecord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.gui.GuiCelestialSelection;
import stevekung.mods.moreplanets.client.gui.GuiShieldGenerator;
import stevekung.mods.moreplanets.client.gui.GuiShieldGeneratorConfig;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.inventory.ContainerShieldGeneratorConfig;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.utils.TeleportUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class PacketSimpleMP extends PacketBase
{
    private EnumSimplePacketMP type;
    private List<Object> data;

    public PacketSimpleMP()
    {
        super();
    }

    public PacketSimpleMP(EnumSimplePacketMP packetType, int dimID, Object... dataList)
    {
        super(dimID);
        List<Object> data = Arrays.asList(dataList);

        if (packetType.getDecodeClasses().length != data.size())
        {
            LoggerMP.warning("More Planets Simple Packet found data length different than packet type: {}", packetType.name());
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
        this.type = EnumSimplePacketMP.values[buffer.readInt()];

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
                TileEntity tile = player.world.getTileEntity(pos);

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
        case C_OPEN_SURVIVAL_PLANET_GUI:
            GuiCelestialSelection gui = new GuiCelestialSelection();
            FMLClientHandler.instance().getClient().displayGuiScreen(gui);
            break;
        case C_MESSAGE_SURVIVAL_PLANET:
            String name = (String)this.data.get(0);
            player.sendMessage(JsonUtils.create("You select " + TextFormatting.AQUA + name + TextFormatting.RESET + " as survival planet, Good Luck!"));
            break;
        case C_PLAY_VEIN_FLOATER_MUSIC:
            FMLClientHandler.instance().getClient().getSoundHandler().playSound(new PositionedSoundRecord(MPSounds.A_PLANET_TO_CONQUER.getRegistryName(), SoundCategory.PLAYERS, 1.0F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F));
            FMLClientHandler.instance().getClient().ingameGUI.setRecordPlayingMessage(((ItemRecord)MPItems.VEIN_FLOATER_DISC).getRecordNameLocal());
            break;
        case C_STOP_VEIN_FLOATER_MUSIC:
            FMLClientHandler.instance().getClient().getSoundHandler().stop(MPSounds.A_PLANET_TO_CONQUER.getRegistryName().toString(), SoundCategory.PLAYERS);
            break;
        case C_PLAY_CREATED_BLACK_HOLE_SOUND:
            player.playSound(MPSounds.BLACK_HOLE_CREATED, 5.0F, 0.8F);
            player.playSound(MPSounds.BLACK_HOLE_AMBIENT, 5.0F, 1.0F);
            break;
        default:
            break;
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        EntityPlayerMP playerMP = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
        GCPlayerStats stats = GCPlayerStats.get(playerMP);
        World world = player.world;
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
            tile = player.world.getTileEntity((BlockPos) this.data.get(0));
            boolean isConfig = (boolean) this.data.get(1);

            if (tile instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;
                PacketSimpleMP.openShieldGeneratorConfig(playerMP, shield, isConfig);
            }
            break;
        case S_FAILED_UNLOCK_CHEST:
            if (stats.getChatCooldown() == 0)
            {
                player.sendMessage(new TextComponentString(LangUtils.translate("gui.valid_key.message", this.data.get(0))));
                stats.setChatCooldown(100);
            }
            break;
        case S_START_SURVIVAL_PLANET:
            if (WorldTickEventHandler.survivalPlanetData != null && !WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
            {
                int sourceDimId = (int)this.data.get(0);
                String celestialName = (String)this.data.get(1);
                LoggerMP.info("Start survival planet at: {}, Dimension: {}", celestialName, WorldUtil.getProviderForNameServer(celestialName).getDimension());
                WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData = true;
                WorldTickEventHandler.survivalPlanetData.survivalPlanetName = celestialName;
                WorldTickEventHandler.survivalPlanetData.setDirty(true);
                TeleportUtils.teleportPlayerToPlanet(playerMP, playerMP.getServer(), sourceDimId, WorldUtil.getProviderForNameServer(celestialName).getDimension());
            }
            break;
        case S_UPDATE_NIBIRU_WEATHER:
            int i = (300 + world.rand.nextInt(600)) * 20;
            boolean thunder = (boolean)this.data.get(0);
            WorldInfo worldinfo = world.getWorldInfo();
            worldinfo.setCleanWeatherTime(0);
            worldinfo.setRainTime(i);
            worldinfo.setThunderTime(i);
            worldinfo.setRaining(true);
            worldinfo.setThundering(thunder);
            break;
        default:
            break;
        }
    }

    public static enum EnumSimplePacketMP
    {
        // SERVER
        S_FIRE_EXTINGUISH(Side.SERVER, BlockPos.class),
        S_BLACK_HOLE_STORAGE_OPTION(Side.SERVER, BlockPos.class, String.class),
        S_SHIELD_VISIBLE(Side.SERVER, BlockPos.class, Boolean.class),
        S_ENABLE_SHIELD(Side.SERVER, BlockPos.class),
        S_ENABLE_SHIELD_DAMAGE(Side.SERVER, BlockPos.class),
        S_SHIELD_GENERATOR_OPTION(Side.SERVER, BlockPos.class, Integer.class, String.class),
        S_SWITCH_SHIELD_GENERATOR_GUI(Side.SERVER, BlockPos.class, Boolean.class),
        S_FAILED_UNLOCK_CHEST(Side.SERVER, String.class),
        S_START_SURVIVAL_PLANET(Side.SERVER, Integer.class, String.class),
        S_UPDATE_NIBIRU_WEATHER(Side.SERVER, Boolean.class),

        // CLIENT
        C_ADD_ENTITY_ID(Side.CLIENT, String.class),
        C_REMOVE_ENTITY_ID(Side.CLIENT, String.class),
        C_REMOVE_GUIDE_POS(Side.CLIENT, BlockPos.class),
        C_RELOAD_RENDERER(Side.CLIENT),
        C_SWITCH_SHIELD_GENERATOR_GUI(Side.CLIENT, BlockPos.class, Integer.class, Boolean.class),
        C_REMOVE_GENERATOR_GUIDE_POS(Side.CLIENT, BlockPos.class),
        C_OPEN_SURVIVAL_PLANET_GUI(Side.CLIENT),
        C_MESSAGE_SURVIVAL_PLANET(Side.CLIENT, String.class),
        C_PLAY_VEIN_FLOATER_MUSIC(Side.CLIENT),
        C_STOP_VEIN_FLOATER_MUSIC(Side.CLIENT),
        C_PLAY_CREATED_BLACK_HOLE_SOUND(Side.CLIENT),
        ;

        private Side targetSide;
        private Class[] decodeAs;
        public static final EnumSimplePacketMP[] values = EnumSimplePacketMP.values();

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
    }

    public static void sendToAllAround(PacketSimpleMP packet, World world, int dimID, BlockPos pos, double radius)
    {
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;
        double r2 = radius * radius;

        world.playerEntities.forEach(player ->
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
        });
    }

    private static void openShieldGeneratorConfig(EntityPlayerMP player, TileEntityShieldGenerator tile, boolean isConfig)
    {
        player.getNextWindowId();
        player.closeContainer();
        int windowId = player.currentWindowId;
        GalacticraftCore.packetPipeline.sendTo(new PacketSimpleMP(EnumSimplePacketMP.C_SWITCH_SHIELD_GENERATOR_GUI, GCCoreUtil.getDimensionID(player.world), tile.getPos(), windowId, isConfig), player);
        player.openContainer = new ContainerShieldGeneratorConfig(player.inventory, tile);
        player.openContainer.windowId = windowId;
        player.openContainer.addListener(player);
    }
}
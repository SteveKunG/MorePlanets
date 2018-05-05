package stevekung.mods.moreplanets.core;

import java.util.Arrays;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.client.command.CommandChangeLog;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.event.*;
import stevekung.mods.moreplanets.core.handler.GuiHandlerMP;
import stevekung.mods.moreplanets.init.*;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.proxy.ServerProxyMP;
import stevekung.mods.moreplanets.recipe.CraftingManagerMP;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.CreativeTabsMP;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.stevekunglib.utils.*;

@Mod(modid = MorePlanetsMod.MOD_ID, name = MorePlanetsMod.NAME, version = MorePlanetsMod.VERSION, dependencies = MorePlanetsMod.DEPENDENCIES, certificateFingerprint = MorePlanetsMod.CERTIFICATE)
public class MorePlanetsMod
{
    public static final String NAME = "More Planets";
    public static final String MOD_ID = "moreplanets";
    public static final int MAJOR_VERSION = 3;
    public static final int MINOR_VERSION = 0;
    public static final int BUILD_VERSION = 0;
    public static final String VERSION = MorePlanetsMod.MAJOR_VERSION + "." + MorePlanetsMod.MINOR_VERSION + "." + MorePlanetsMod.BUILD_VERSION;
    public static final String CLIENT_CLASS = "stevekung.mods.moreplanets.proxy.ClientProxyMP";
    public static final String SERVER_CLASS = "stevekung.mods.moreplanets.proxy.ServerProxyMP";
    public static final String FORGE_VERSION = "after:forge@[14.23.3.2673,);";
    public static final String DEPENDENCIES = "required-after:galacticraftcore@[4.0.1.-1,); required-after:galacticraftplanets@[4.0.1.-1,); required-after:micdoodlecore; " + MorePlanetsMod.FORGE_VERSION;
    public static final String CERTIFICATE = "@FINGERPRINT@";
    public static final String URL = "https://minecraft.curseforge.com/projects/galacticraft-add-on-more-planets";
    public static boolean isDevelopment;

    @SidedProxy(clientSide = MorePlanetsMod.CLIENT_CLASS, serverSide = MorePlanetsMod.SERVER_CLASS)
    public static ServerProxyMP PROXY;

    @Instance(MorePlanetsMod.MOD_ID)
    public static MorePlanetsMod INSTANCE;

    public static boolean noConnection;
    public static boolean foundLatest;
    public static boolean showAnnounceMessage;
    public static final VersionChecker checker = new VersionChecker(MOD_ID, VERSION, MAJOR_VERSION, MINOR_VERSION, BUILD_VERSION);
    public static final CreativeTabsMP BLOCK_TAB = new CreativeTabsMP("more_planets_blocks");
    public static final CreativeTabsMP ITEM_TAB = new CreativeTabsMP("more_planets_items");
    public static final ClientRegistryUtils CLIENT_REGISTRY = new ClientRegistryUtils(MOD_ID);
    public static final CommonRegistryUtils COMMON_REGISTRY = new CommonRegistryUtils(MOD_ID);

    static
    {
        FluidRegistry.enableUniversalBucket();

        try
        {
            MorePlanetsMod.isDevelopment = Launch.classLoader.getClassBytes("net.minecraft.world.World") != null;
        }
        catch (Exception e) {}
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CompatibilityManagerMP.init();
        MorePlanetsMod.initModInfo(event.getModMetadata());
        CommonUtils.registerEventHandler(this);

        MPBlocks.init();
        MPItems.init();
        MPEntities.init();
        MPPlanets.init();
        MPPotions.init();
        MPBiomes.init();
        MPOthers.init();
        MorePlanetsMod.PROXY.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MPTileEntities.init();
        MPOreDictionary.init();
        MPPlanets.register();
        GalacticraftCore.packetPipeline.addDiscriminator(ConfigManagerMP.moreplanets_general.idNetworkHandler, PacketSimpleMP.class);
        MorePlanetsMod.BLOCK_TAB.setDisplayItemStack(new ItemStack(DionaBlocks.CRASHED_ALIEN_PROBE));
        MorePlanetsMod.ITEM_TAB.setDisplayItemStack(new ItemStack(MPItems.SPACE_WARPER_CORE));
        MorePlanetsMod.PROXY.init(event);

        if (ClientUtils.isClient())
        {
            BlocksItemsRegistry.postRegisteredSortBlock();
            BlocksItemsRegistry.postRegisteredSortItem();
            CommonUtils.registerEventHandler(new ClientEventHandler());
            ClientCommandHandler.instance.registerCommand(new CommandChangeLog());
        }

        CommonUtils.registerEventHandler(new EntityEventHandler());
        CommonUtils.registerEventHandler(new GeneralEventHandler());
        CommonUtils.registerEventHandler(new WorldTickEventHandler());
        CommonUtils.registerEventHandler(new MissingMappingHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MorePlanetsMod.PROXY.postInit(event);

        if (ConfigManagerMP.moreplanets_general.enableVersionChecker)
        {
            MorePlanetsMod.checker.startCheck();
        }

        CommonUtils.registerGuiHandler(this, new GuiHandlerMP());
        CraftingManagerMP.init();
        MPSchematics.init();
        MPDimensions.init();
    }

    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event)
    {
        WorldTickEventHandler.startedDimensionData = null;
    }

    @EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event)
    {
        if (MorePlanetsMod.isDevelopment)
        {
            LoggerMP.info("Development environment detected! Ignore certificate check.");
        }
        else
        {
            throw new RuntimeException("Invalid fingerprint detected! This version will NOT be supported by the author!");
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MorePlanetsMod.MOD_ID))
        {
            ConfigManager.sync(MorePlanetsMod.MOD_ID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public void onBiomeRegister(RegistryEvent.Register<Biome> event)
    {
        MPBiomes.biomeList.forEach(biome ->
        {
            biome.registerTypes(biome);
        });
    }

    private static void initModInfo(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = MorePlanetsMod.MOD_ID;
        info.name = MorePlanetsMod.NAME;
        info.version = MorePlanetsMod.VERSION;
        info.description = "An add-on exploration with custom planets for Galacticraft!";
        info.url = MorePlanetsMod.URL;
        info.credits = "All credits to Galacticraft Sources/API and some people who helped.";
        info.authorList = Arrays.asList("SteveKunG");
    }
}
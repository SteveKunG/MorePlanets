package stevekung.mods.moreplanets.core;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.advancements.FrameType;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.command.CommandOpenCelestialScreen;
import stevekung.mods.moreplanets.core.capability.CapabilityHandlerMP;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.core.event.EntityEventHandler;
import stevekung.mods.moreplanets.core.event.GeneralEventHandler;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.core.handler.DataFixersMP;
import stevekung.mods.moreplanets.core.handler.GuiHandlerMP;
import stevekung.mods.moreplanets.core.handler.MissingMappingHandler;
import stevekung.mods.moreplanets.init.*;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.proxy.ServerProxyMP;
import stevekung.mods.moreplanets.recipe.CraftingManagerMP;
import stevekung.mods.moreplanets.recipe.SmeltingManagerMP;
import stevekung.mods.moreplanets.utils.*;
import stevekung.mods.stevekunglib.utils.CommonRegistryUtils;
import stevekung.mods.stevekunglib.utils.CommonUtils;
import stevekung.mods.stevekunglib.utils.VersionChecker;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

@Mod(modid = MorePlanetsMod.MOD_ID, name = MorePlanetsMod.NAME, version = MorePlanetsMod.VERSION, dependencies = MorePlanetsMod.MAIN_DEPENDENCIES, updateJSON = MorePlanetsMod.JSON_URL)
public class MorePlanetsMod
{
    public static final String NAME = "More Planets";
    public static final String MOD_ID = "moreplanets";
    private static final int MAJOR_VERSION = 2;
    private static final int MINOR_VERSION = 2;
    private static final int BUILD_VERSION = 2;
    public static final String VERSION = MorePlanetsMod.MAJOR_VERSION + "." + MorePlanetsMod.MINOR_VERSION + "." + MorePlanetsMod.BUILD_VERSION;
    private static final String FORGE_VERSION = "after:forge@[14.23.5.2768,); ";
    private static final String DEPENDENCIES = "after:jei@[4.15.0.268,); ";
    protected static final String MAIN_DEPENDENCIES = "required-after:stevekung's_lib@[1.1.11,); required-after:galacticraftcore@[4.0.1.-1,); required-after:galacticraftplanets@[4.0.1.-1,); required-after:micdoodlecore; required-after:mixinbooter; " + MorePlanetsMod.FORGE_VERSION + MorePlanetsMod.DEPENDENCIES;
    public static final String URL = "https://www.curseforge.com/minecraft/mc-mods/more-planets-gc-addon";
    protected static final String JSON_URL = "https://raw.githubusercontent.com/SteveKunG/VersionCheckLibrary/master/more_planets_version.json";
    public static boolean isDevelopment;

    @SidedProxy(clientSide = "stevekung.mods.moreplanets.proxy.ClientProxyMP", serverSide = "stevekung.mods.moreplanets.proxy.ServerProxyMP")
    public static ServerProxyMP PROXY;

    @Instance(MorePlanetsMod.MOD_ID)
    public static MorePlanetsMod INSTANCE;

    public static VersionChecker CHECKER;
    public static final CreativeTabsMP BLOCK_TAB = new CreativeTabsMP("more_planets_blocks");
    public static final CreativeTabsMP ITEM_TAB = new CreativeTabsMP("more_planets_items");
    public static ClientRegistryUtils CLIENT_REGISTRY;
    public static final CommonRegistryUtils COMMON_REGISTRY = new CommonRegistryUtils(MOD_ID);

    static
    {
        FluidRegistry.enableUniversalBucket();

        try
        {
            MorePlanetsMod.isDevelopment = Launch.classLoader.getClassBytes("net.minecraft.world.World") != null;
        }
        catch (Exception e) {}

        if (ClientUtils.isClient())
        {
            MorePlanetsMod.CLIENT_REGISTRY = new ClientRegistryUtils(MOD_ID);
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CompatibilityManagerMP.init();
        MorePlanetsMod.initModInfo(event.getModMetadata());
        CommonUtils.registerEventHandler(this);
        CapabilityHandlerMP.register();

        MPBlocks.init();
        MPItems.init();
        MPEntities.init();
        MPPlanets.init();
        MPPotions.init();
        MPBiomes.init();
        MPOthers.init();
        MPPlanets.register();
        MorePlanetsMod.PROXY.preInit(event);
        MorePlanetsMod.CHECKER = new VersionChecker(MorePlanetsMod.INSTANCE, MorePlanetsMod.NAME, MorePlanetsMod.URL);

        if (ConfigManagerMP.moreplanets_general.enableVersionChecker)
        {
            MorePlanetsMod.CHECKER.startCheck();
        }

        EnumHelper.addEnum(FrameType.class, "TASK_PURPLE", new Class[] { String.class, Integer.TYPE, TextFormatting.class }, "task_purple", 0, TextFormatting.DARK_PURPLE);
        PaintingMP.RONG = EnumHelper.addArt("RONG", "rong", 32, 32, 0, 128);

        if (ClientUtils.isClient())
        {
            ClientCommandHandler.instance.registerCommand(new CommandOpenCelestialScreen());
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MPTileEntities.init();
        MPOreDictionary.init();
        DataFixersMP.init();
        GalacticraftCore.packetPipeline.addDiscriminator(1999, PacketSimpleMP.class);
        MorePlanetsMod.BLOCK_TAB.setDisplayItemStack(new ItemStack(MPBlocks.CRASHED_ALIEN_PROBE));
        MorePlanetsMod.ITEM_TAB.setDisplayItemStack(new ItemStack(MPItems.SPACE_WARPER_CORE));
        MorePlanetsMod.PROXY.init(event);

        if (ClientUtils.isClient())
        {
            BlocksItemsRegistry.postRegisteredSortBlock();
            BlocksItemsRegistry.postRegisteredSortItem();
            CommonUtils.registerEventHandler(new ClientEventHandler());
        }

        CommonUtils.registerEventHandler(new EntityEventHandler());
        CommonUtils.registerEventHandler(new GeneralEventHandler());
        CommonUtils.registerEventHandler(new WorldTickEventHandler());
        CommonUtils.registerEventHandler(new MissingMappingHandler());
        SchematicsRegistry.registerSchematicDungeonLoot(4, new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC));
        SchematicsRegistry.registerSchematicDungeonLoot(4, new ItemStack(MPItems.ION_CANNON_SCHEMATIC));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MorePlanetsMod.PROXY.postInit(event);
        CommonUtils.registerGuiHandler(this, new GuiHandlerMP());
        CraftingManagerMP.init();
        SmeltingManagerMP.init();
        MPSchematics.init();
        MPDimensions.init();
    }

    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event)
    {
        WorldTickEventHandler.survivalPlanetData = null;
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
        MPBiomes.registerTypes();
    }

    @SubscribeEvent
    public void onSoundRegister(RegistryEvent.Register<SoundEvent> event)
    {
        LoggerMP.info("Initialize sounds from {}", MPSounds.class);
    }

    private static void initModInfo(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = MorePlanetsMod.MOD_ID;
        info.name = MorePlanetsMod.NAME;
        info.version = MorePlanetsMod.VERSION;
        info.description = "An add-on adds new exotic planets into Galacticraft!";
        info.url = MorePlanetsMod.URL;
        info.credits = "All credits goes to Galacticraft Sources/API, Translators and some people who helped.";
        info.authorList = Lists.newArrayList("SteveKunG", "PloyiinGz");
    }
}
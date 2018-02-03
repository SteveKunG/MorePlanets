package stevekung.mods.moreplanets.core;

import java.io.File;
import java.util.Arrays;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import stevekung.mods.moreplanets.client.command.CommandChangeLog;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.event.*;
import stevekung.mods.moreplanets.core.handler.GuiHandlerMP;
import stevekung.mods.moreplanets.init.*;
import stevekung.mods.moreplanets.items.capsule.ItemCapsule;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.proxy.ServerProxyMP;
import stevekung.mods.moreplanets.recipe.CraftingManagerMP;
import stevekung.mods.moreplanets.util.CreativeTabsMP;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.SmeltWithDataFunction;
import stevekung.mods.moreplanets.util.VersionChecker;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

@Mod(modid = MorePlanetsCore.MOD_ID, name = MorePlanetsCore.NAME, version = MorePlanetsCore.VERSION, dependencies = MorePlanetsCore.DEPENDENCIES, guiFactory = MorePlanetsCore.GUI_FACTORY, certificateFingerprint = MorePlanetsCore.CERTIFICATE)
public class MorePlanetsCore
{
    public static final String NAME = "More Planets";
    public static final String MOD_ID = "moreplanets";
    public static final int MAJOR_VERSION = 2;
    public static final int MINOR_VERSION = 0;
    public static final int BUILD_VERSION = 11;
    public static final String VERSION = MorePlanetsCore.MAJOR_VERSION + "." + MorePlanetsCore.MINOR_VERSION + "." + MorePlanetsCore.BUILD_VERSION;
    public static final String GUI_FACTORY = "stevekung.mods.moreplanets.core.config.ConfigGuiFactoryMP";
    public static final String CLIENT_CLASS = "stevekung.mods.moreplanets.proxy.ClientProxyMP";
    public static final String SERVER_CLASS = "stevekung.mods.moreplanets.proxy.ServerProxyMP";
    public static final String FORGE_VERSION = "after:forge@[14.23.1.2555,);";
    public static final String DEPENDENCIES = "required-after:galacticraftcore@[4.0.1.-1,); required-after:galacticraftplanets@[4.0.1.-1,); required-after:micdoodlecore; " + MorePlanetsCore.FORGE_VERSION;
    public static final String MC_VERSION = String.valueOf(FMLInjectionData.data()[4]);
    public static final String CERTIFICATE = "@FINGERPRINT@";
    private static boolean DEOBFUSCATED;

    @SidedProxy(clientSide = MorePlanetsCore.CLIENT_CLASS, serverSide = MorePlanetsCore.SERVER_CLASS)
    public static ServerProxyMP PROXY;

    @Instance(MorePlanetsCore.MOD_ID)
    public static MorePlanetsCore INSTANCE;

    public static boolean[] STATUS_CHECK = { false, false, false };

    public static CreativeTabsMP BLOCK_TAB;
    public static CreativeTabsMP ITEM_TAB;

    static
    {
        FluidRegistry.enableUniversalBucket();

        try
        {
            MorePlanetsCore.DEOBFUSCATED = Launch.classLoader.getClassBytes("net.minecraft.world.World") != null;
        }
        catch (Exception e) {}
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigManagerMP.init(new File(event.getModConfigurationDirectory(), "MorePlanets.cfg"));
        MorePlanetsCore.initModInfo(event.getModMetadata());
        MorePlanetsCore.BLOCK_TAB = new CreativeTabsMP("MorePlanetsBlocks");
        MorePlanetsCore.ITEM_TAB = new CreativeTabsMP("MorePlanetsItems");

        MPBlocks.init();
        MPItems.init();
        MPEntities.init();
        MPPlanets.init();
        MPPotions.init();
        MPBiomes.init();
        MPOthers.init();
        MorePlanetsCore.PROXY.registerPreRendering();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MPTileEntities.init();
        MPOreDictionary.init();
        MPBiomeTypes.init();
        MPPlanets.register();
        GalacticraftCore.packetPipeline.addDiscriminator(ConfigManagerMP.idNetworkHandler, PacketSimpleMP.class);
        MorePlanetsCore.BLOCK_TAB.setDisplayItemStack(new ItemStack(MPBlocks.ROCKET_CRUSHER));
        MorePlanetsCore.ITEM_TAB.setDisplayItemStack(new ItemStack(MPItems.SPACE_WARPER_CORE));
        MorePlanetsCore.PROXY.registerInitRendering();
        LootFunctionManager.registerFunction(new SmeltWithDataFunction.Serializer());

        if (CommonRegisterHelper.isClient())
        {
            CommonRegisterHelper.postRegisteredSortBlock();
            CommonRegisterHelper.postRegisteredSortItem();
            CommonRegisterHelper.registerForgeEvent(new ClientEventHandler());
            ClientCommandHandler.instance.registerCommand(new CommandChangeLog());
        }

        CommonRegisterHelper.registerForgeEvent(new EntityEventHandler());
        CommonRegisterHelper.registerForgeEvent(new GeneralEventHandler());
        CommonRegisterHelper.registerForgeEvent(new WorldTickEventHandler());
        CommonRegisterHelper.registerForgeEvent(new MissingMappingHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        VersionChecker.startCheck();
        MorePlanetsCore.PROXY.registerPostRendering();
        CommonRegisterHelper.registerGUIHandler(this, new GuiHandlerMP());
        CraftingManagerMP.init();
        MPSchematics.init();
        MPDimensions.init();
        ItemCapsule.init = true;
    }

    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event)
    {
        WorldTickEventHandler.startedDimensionData = null;
    }

    @EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event)
    {
        if (MorePlanetsCore.isObfuscatedEnvironment())
        {
            MPLog.info("Development environment detected! Ignore certificate check.");
        }
        else
        {
            throw new RuntimeException("Invalid fingerprint detected! This version will NOT be supported by the author!");
        }
    }

    public static boolean isObfuscatedEnvironment()
    {
        return MorePlanetsCore.DEOBFUSCATED;
    }

    private static void initModInfo(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = MorePlanetsCore.MOD_ID;
        info.name = MorePlanetsCore.NAME;
        info.version = MorePlanetsCore.VERSION;
        info.description = "An add-on exploration with custom planets for Galacticraft!";
        info.url = "https://minecraft.curseforge.com/projects/galacticraft-add-on-more-planets";
        info.credits = "All credits to Galacticraft Sources/API and some people who helped.";
        info.authorList = Arrays.asList("SteveKunG");
    }
}
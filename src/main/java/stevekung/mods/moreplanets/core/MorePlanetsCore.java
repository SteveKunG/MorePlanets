/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.recipe.CircuitFabricatorRecipes;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.RecipeManagerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;
import net.minecraft.block.Block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.achievement.AchievementsMP;
import stevekung.mods.moreplanets.core.command.CommandHomePlanet;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.event.GravityEvents;
import stevekung.mods.moreplanets.core.event.MorePlanetEvents;
import stevekung.mods.moreplanets.core.handler.GuiHandlerMP;
import stevekung.mods.moreplanets.core.handler.MainMenuEventHandlerMP;
import stevekung.mods.moreplanets.core.handler.PlanetFogHandler;
import stevekung.mods.moreplanets.core.handler.SkyProviderHandlerMP;
import stevekung.mods.moreplanets.core.init.*;
import stevekung.mods.moreplanets.core.integration.DispenserRegistryMP;
import stevekung.mods.moreplanets.core.integration.MicroBlockIntegrationMP;
import stevekung.mods.moreplanets.core.integration.TreeCapitatorIntegrationMP;
import stevekung.mods.moreplanets.core.proxy.CommonProxyMP;
import stevekung.mods.moreplanets.core.recipe.CompressorRecipesMP;
import stevekung.mods.moreplanets.core.todo.MorePlanetsChannelHandler;
import stevekung.mods.moreplanets.core.util.CreativeTabsHelper;
import stevekung.mods.moreplanets.core.util.FurnaceFuelMP;
import stevekung.mods.moreplanets.core.util.VersionChecker;
import stevekung.mods.moreplanets.moons.deimos.recipe.CraftingRecipesDeimos;
import stevekung.mods.moreplanets.moons.europa.recipe.CraftingRecipesEuropa;
import stevekung.mods.moreplanets.moons.io.recipe.CraftingRecipesIo;
import stevekung.mods.moreplanets.moons.koentus.recipe.CraftingRecipesKoentus;
import stevekung.mods.moreplanets.moons.phobos.recipe.CraftingRecipesPhobos;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.recipe.CraftingRecipesDiona;
import stevekung.mods.moreplanets.planets.diona.schematic.SchematicTier4Rocket;
import stevekung.mods.moreplanets.planets.diona.schematic.SchematicTier4RocketNoFlag;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.items.armor.FronosArmorItems;
import stevekung.mods.moreplanets.planets.fronos.recipe.CraftingRecipesFronos;
import stevekung.mods.moreplanets.planets.fronos.schematics.SchematicTier7Rocket;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.tools.KapteynBToolsItems;
import stevekung.mods.moreplanets.planets.kapteynb.recipe.CraftingRecipesKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.schematics.SchematicTier8Rocket;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.mercury.recipe.CraftingRecipesMercury;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.nibiru.recipe.CraftingRecipesNibiru;
import stevekung.mods.moreplanets.planets.nibiru.schematics.SchematicTier6Rocket;
import stevekung.mods.moreplanets.planets.nibiru.schematics.SchematicTier6RocketNoFlag;
import stevekung.mods.moreplanets.planets.pluto.recipe.CraftingRecipesPluto;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.polongnius.recipe.CraftingRecipesPolongnius;
import stevekung.mods.moreplanets.planets.polongnius.schematics.SchematicTier5Rocket;
import stevekung.mods.moreplanets.planets.polongnius.schematics.SchematicTier5RocketNoFlag;
import stevekung.mods.moreplanets.planets.siriusb.recipe.CraftingRecipesSiriusB;
import stevekung.mods.moreplanets.planets.venus.recipe.CraftingRecipesVenus;

@Mod(modid = MorePlanetsCore.MOD_ID, name = MorePlanetsCore.NAME, version = MorePlanetsCore.VERSION, dependencies = "required-after:GalacticraftCore; required-after:GalacticraftMars; required-after:Micdoodlecore; after:Forge@[10.13.2.1291,);", guiFactory = "stevekung.mods.moreplanets.core.ConfigGuiFactoryMP")
public class MorePlanetsCore
{
    public static final String NAME = "More Planets";
    public static final String MOD_ID = "MorePlanet";
    public static final String VERSION = MorePlanetsCore.major_version + "." + MorePlanetsCore.minor_version + "." + MorePlanetsCore.build_version;

    public static final int major_version = 1;
    public static final int minor_version = 4;
    public static final int build_version = 4;

    @SidedProxy(clientSide = "stevekung.mods.moreplanets.core.proxy.ClientProxyMP", serverSide = "stevekung.mods.moreplanets.core.proxy.CommonProxyMP")
    public static CommonProxyMP proxy;

    @Instance(MorePlanetsCore.MOD_ID)
    public static MorePlanetsCore instance;

    public static CreativeTabs mpBlocksTab;
    public static CreativeTabs mpItemsTab;
    public static CreativeTabs mpToolsTab;
    public static CreativeTabs mpArmorTab;

    public static MorePlanetsChannelHandler packetPipeline;

    public static boolean[] STATUS_CHECK = { false, false, false };

    public static Planet siriusB;
    public static Planet diona;
    public static Planet polongnius;
    public static Planet nibiru;
    public static Planet fronos;
    public static Planet kapteynB;
    public static Planet darkAsteroids;

    public static Planet mercury;
    public static Planet venus;
    public static Planet pluto;
    public static Planet jupiter;

    public static Moon koentus;
    public static Moon phobos;
    public static Moon deimos;
    public static Moon io;

    public static Star sirius;
    public static Star kapteyn;
    public static Star darkStar;

    public static SolarSystem siriusSolarSystem;
    public static SolarSystem kapteynBSolarSystem;
    public static SolarSystem darkSolarSystem;

    public static Satellite marsSpaceStation;
    public static Satellite jupiterSpaceStation;
    public static Satellite kapteynBSpaceStation;

    public static SoundType soundTypeSlime = new SoundType("slime", 1.0F, 1.0F)
    {
        @Override
        public String getBreakSound()
        {
            return "mob.slime.big";
        }

        @Override
        public String getStepResourcePath()
        {
            return "mob.slime.small";
        }
    };
    public static SoundType soundTypeSmallSlime = new SoundType("slime", 1.0F, 1.0F)
    {
        @Override
        public String getBreakSound()
        {
            return "mob.slime.small";
        }

        @Override
        public String getStepResourcePath()
        {
            return "mob.slime.small";
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigManagerMP.init(new File(event.getModConfigurationDirectory(), "MorePlanets.cfg"));
        MorePlanetsCore.initModInfo(event.getModMetadata());

        MPPotions.init();
        MPBlocks.init();
        MPItems.init();
        MPArmor.init();
        MPTools.init();
        MPBiomes.init();

        FMLCommonHandler.instance().bus().register(new SkyProviderHandlerMP());
        FMLCommonHandler.instance().bus().register(new MorePlanetEvents());

        MinecraftForge.EVENT_BUS.register(new MorePlanetEvents());
        MinecraftForge.EVENT_BUS.register(new GravityEvents());
        MinecraftForge.EVENT_BUS.register(new PlanetFogHandler());

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            MinecraftForge.EVENT_BUS.register(new MainMenuEventHandlerMP());
        }
    }

    private static void initModInfo(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = MorePlanetsCore.MOD_ID;
        info.name = MorePlanetsCore.NAME;
        info.version = MorePlanetsCore.VERSION;
        info.description = "Planets exploration adventure in Minecraft!";
        info.url = "http://minecraftforum.net/forums/thread/2358057";
        info.authorList = Arrays.asList("SteveKunG");
        info.credits = "Thanks to Galacticraft for based sources and some people.";
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MPPlanets.init();
        MPEntities.init();
        TreeCapitatorIntegrationMP.init();
        MicroBlockIntegrationMP.init();
        AchievementsMP.init();
        MorePlanetsCore.packetPipeline = MorePlanetsChannelHandler.init();

        MorePlanetsCore.mpBlocksTab = new CreativeTabsHelper("MorePlanetsBlocks", new ItemStack(MercuryBlocks.mercury_block, 1, 11));
        MorePlanetsCore.mpItemsTab = new CreativeTabsHelper("MorePlanetsItems", new ItemStack(DionaItems.laser_gun));
        MorePlanetsCore.mpToolsTab = new CreativeTabsHelper("MorePlanetsTools", new ItemStack(KapteynBToolsItems.uranium_pickaxe));
        MorePlanetsCore.mpArmorTab = new CreativeTabsHelper("MorePlanetsArmor", new ItemStack(FronosArmorItems.iridium_helmet));

        if (ConfigManagerMP.enableTier4RocketSchematic)
        {
            SchematicRegistry.registerSchematicRecipe(new SchematicTier4Rocket());
            SchematicRegistry.registerSchematicRecipe(new SchematicTier4RocketNoFlag());
        }
        if (ConfigManagerMP.enableTier5RocketSchematic)
        {
            SchematicRegistry.registerSchematicRecipe(new SchematicTier5Rocket());
            SchematicRegistry.registerSchematicRecipe(new SchematicTier5RocketNoFlag());
        }
        if (ConfigManagerMP.enableTier6RocketSchematic)
        {
            SchematicRegistry.registerSchematicRecipe(new SchematicTier6Rocket());
            SchematicRegistry.registerSchematicRecipe(new SchematicTier6RocketNoFlag());
        }
        if (ConfigManagerMP.enableTier7RocketSchematic)
        {
            SchematicRegistry.registerSchematicRecipe(new SchematicTier7Rocket());
        }
        if (ConfigManagerMP.enableTier8RocketSchematic)
        {
            SchematicRegistry.registerSchematicRecipe(new SchematicTier8Rocket());
        }

        if (ConfigManagerMP.enableRocketWithThaiFlag)
        {
            GalacticraftRegistry.addDungeonLoot(1, new ItemStack(DionaItems.tier4_rocket_schematic, 1, 0));
            GalacticraftRegistry.addDungeonLoot(3, new ItemStack(PolongniusItems.tier5_rocket_schematic, 1, 0));
            GalacticraftRegistry.addDungeonLoot(4, new ItemStack(NibiruItems.tier6_rocket_schematic, 1, 0));
        }

        GalacticraftRegistry.addDungeonLoot(1, new ItemStack(DionaItems.tier4_rocket_schematic, 1, 1));
        GalacticraftRegistry.addDungeonLoot(3, new ItemStack(PolongniusItems.tier5_rocket_schematic, 1, 1));
        GalacticraftRegistry.addDungeonLoot(4, new ItemStack(NibiruItems.tier6_rocket_schematic, 1, 1));
        GalacticraftRegistry.addDungeonLoot(5, new ItemStack(FronosItems.tier7_rocket_schematic, 1, 0));
        GalacticraftRegistry.addDungeonLoot(6, new ItemStack(KapteynBItems.tier8_rocket_schematic, 1, 0));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MorePlanetsCore.proxy.postInit(event);
        GameRegistry.registerFuelHandler(new FurnaceFuelMP());
        FurnaceFuelMP.setFuelValues();
        MPTileEntities.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerMP());

        CraftingRecipesDiona.loadRecipes();
        CraftingRecipesPolongnius.loadRecipes();
        CraftingRecipesNibiru.loadRecipes();
        CraftingRecipesKoentus.loadRecipes();
        CraftingRecipesFronos.loadRecipes();
        CraftingRecipesKapteynB.loadRecipes();
        CraftingRecipesSiriusB.loadRecipes();
        CraftingRecipesMercury.loadRecipes();
        CraftingRecipesVenus.loadRecipes();
        CraftingRecipesPluto.loadRecipes();
        CraftingRecipesPhobos.loadRecipes();
        CraftingRecipesDeimos.loadRecipes();
        CraftingRecipesIo.loadRecipes();
        CraftingRecipesEuropa.loadRecipes();

        int siliconCount = OreDictionary.getOres(ConfigManagerCore.otherModsSilicon).size();

        for (int i = 0; i <= siliconCount; i++)
        {
            ItemStack silicon;

            if (i == 0)
            {
                silicon = new ItemStack(GCItems.basicItem, 1, 2);
            }
            else
            {
                silicon = OreDictionary.getOres("itemSilicon").get(i - 1);
            }
            CircuitFabricatorRecipes.addRecipe(new ItemStack(PolongniusItems.purple_crystal_solar_module, 1, 0), new ItemStack[] { new ItemStack(PolongniusItems.polongnius_item, 1, 1), silicon, silicon, new ItemStack(Items.redstone), new ItemStack(Items.repeater) });
            CircuitFabricatorRecipes.addRecipe(new ItemStack(PolongniusItems.purple_crystal_solar_module, 9, 1), new ItemStack[] { new ItemStack(Items.diamond), silicon, silicon, new ItemStack(Items.redstone), new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        }

        CompressorRecipesMP.registerCompressorRecipes();
        DispenserRegistryMP.init();

        if (ConfigManagerMP.enableJupiterSpaceStation)
        {
            HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
            inputMap.put("ingotTin", 80);
            inputMap.put(RecipeManagerGC.aluminumIngots, 48);
            inputMap.put("waferAdvanced", 10);
            inputMap.put("ingotIron", 32);
            inputMap.put("ingotDesh", 16);
            GalacticraftRegistry.registerSpaceStation(new SpaceStationType(ConfigManagerMP.idDimensionJupiterSpaceStation, ConfigManagerMP.idDimensionJupiter, new SpaceStationRecipe(inputMap)));
        }
        if (ConfigManagerMP.enableMarsSpaceStation)
        {
            HashMap<Object, Integer> inputMap2 = new HashMap<Object, Integer>();
            inputMap2.put("ingotTin", 64);
            inputMap2.put(RecipeManagerGC.aluminumIngots, 24);
            inputMap2.put("waferAdvanced", 3);
            inputMap2.put("ingotIron", 48);
            GalacticraftRegistry.registerSpaceStation(new SpaceStationType(ConfigManagerMP.idDimensionMarsSpaceStation, ConfigManagerMars.dimensionIDMars, new SpaceStationRecipe(inputMap2)));
        }
        VersionChecker.startCheck();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandHomePlanet());
    }

    public static boolean isObfuscatedEnvironment()
    {
        try
        {
            Blocks.class.getField("air");
            return true;
        }
        catch (Throwable e) {}
        return false;
    }
}
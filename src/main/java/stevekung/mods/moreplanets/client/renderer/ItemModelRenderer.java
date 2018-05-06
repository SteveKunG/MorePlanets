package stevekung.mods.moreplanets.client.renderer;

import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.client.renderer.ccl.*;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityAlienBerryChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityInfectedChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNibiruAncientChest;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNibiruTreasureChest;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienChest;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class ItemModelRenderer
{
    public static void init()
    {
        ItemModelRenderer.registerBlockRenderer();
        ItemModelRenderer.registerItemRenderer();
    }

    private static void registerBlockRenderer()
    {
        BlocksItemsRegistry.SINGLE_BLOCK_RENDER_LIST.entrySet().forEach(map ->
        {
            Block block = map.getKey();
            String name = map.getValue();
            MorePlanetsMod.CLIENT_REGISTRY.registerModelRender(block, name);
        });
    }

    private static void registerItemRenderer()
    {
        BlocksItemsRegistry.SINGLE_ITEM_RENDER_LIST.entrySet().forEach(map ->
        {
            Item item = map.getKey();
            String name = map.getValue();
            MorePlanetsMod.CLIENT_REGISTRY.registerModelRender(item, name);
        });
    }

    public static void registerCCLRenderer()
    {
        try
        {
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.BLACK_HOLE_STORAGE, new RenderBlackHoleStorage());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_RECEIVER, new RenderDarkEnergyReceiver());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.SHIELD_GENERATOR, new RenderShieldGenerator());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_CORE, new RenderDarkEnergyCore());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_GENERATOR, new RenderDarkEnergyGenerator());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.NUCLEAR_WASTE_TANK, new RenderNuclearWasteTank());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.CHEESE_SPORE_CHEST, new RenderChest(new TileEntityCheeseSporeChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DIONA_ANCIENT_CHEST, new RenderChest(new TileEntityDionaAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DIONA_TREASURE_CHEST, new RenderChest(new TileEntityDionaTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.CHALOS_ANCIENT_CHEST, new RenderChest(new TileEntityChalosAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.CHALOS_TREASURE_CHEST, new RenderChest(new TileEntityChalosTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.NIBIRU_ANCIENT_CHEST, new RenderChest(new TileEntityNibiruAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.NIBIRU_TREASURE_CHEST, new RenderChest(new TileEntityNibiruTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.INFECTED_CHEST, new RenderChest(new TileEntityInfectedChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_BERRY_CHEST, new RenderChest(new TileEntityAlienBerryChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_DEFENDER_BEACON, new RenderAlienDefenderBeacon());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ZELIUS_EGG, new RenderZeliusEgg());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.LARGE_INFECTED_CRYSTALLIZED, new RenderLargeInfectedCrystallized());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.MULTALIC_CRYSTAL, new RenderMultalicCrystal());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.JUICER_EGG, new RenderJuicerEgg());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.VEIN_FRAME, new RenderVeinFrame());
            ItemModelRenderer.registerCCLItemRenderer(DionaItems.INFECTED_CRYSTALLIZED_BOMB, new RenderInfectedCrystallizedBombCCL());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_CHEST, new RenderChest(new TileEntityAlienChest()));

            LoggerMP.info("Successfully registered CodeChickenCore item rendering for More Planets tile entities");
        }
        catch (Exception e)
        {
            LoggerMP.info("CodeChickenCore not loaded, using vanilla TileEntityItemStackRenderer");
        }
    }

    private static void registerCCLItemRenderer(Block block, Object obj) throws Exception
    {
        ItemModelRenderer.registerCCLItemRenderer(Item.getItemFromBlock(block), obj);
    }

    private static void registerCCLItemRenderer(Item item, Object obj) throws Exception
    {
        Class<?> clazz = Class.forName("codechicken.lib.model.ModelRegistryHelper");
        Class<?> itemRenderClass = Class.forName("codechicken.lib.render.item.IItemRenderer");
        Method method = clazz.getDeclaredMethod("registerItemRenderer", Item.class, itemRenderClass);
        method.invoke(clazz, item, obj);
    }
}
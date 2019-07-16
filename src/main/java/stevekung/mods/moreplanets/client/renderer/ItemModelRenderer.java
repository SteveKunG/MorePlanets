package stevekung.mods.moreplanets.client.renderer;

import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.client.renderer.ccl.*;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
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
        BlocksItemsRegistry.SINGLE_BLOCK_RENDER_LIST.forEach((block, name) -> MorePlanetsMod.CLIENT_REGISTRY.registerModelRender(block, name));
    }

    private static void registerItemRenderer()
    {
        BlocksItemsRegistry.SINGLE_ITEM_RENDER_LIST.forEach((item, name) -> MorePlanetsMod.CLIENT_REGISTRY.registerModelRender(item, name));
    }

    public static void registerCCLRenderer()
    {
        try
        {
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.BLACK_HOLE_STORAGE, new CCLBlackHoleStorage());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_RECEIVER, new CCLDarkEnergyReceiver());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.SHIELD_GENERATOR, new CCLShieldGenerator());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_CORE, new CCLDarkEnergyCore());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_GENERATOR, new CCLDarkEnergyGenerator());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.NUCLEAR_WASTE_TANK, new CCLNuclearWasteTank());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.CHEESE_SPORE_CHEST, new CCLChest(new TileEntityCheeseSporeChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DIONA_ANCIENT_CHEST, new CCLChest(new TileEntityDionaAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DIONA_TREASURE_CHEST, new CCLChest(new TileEntityDionaTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.CHALOS_ANCIENT_CHEST, new CCLChest(new TileEntityChalosAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.CHALOS_TREASURE_CHEST, new CCLChest(new TileEntityChalosTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.NIBIRU_ANCIENT_CHEST, new CCLChest(new TileEntityNibiruAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.NIBIRU_TREASURE_CHEST, new CCLChest(new TileEntityNibiruTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.INFECTED_CHEST, new CCLChest(new TileEntityInfectedChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_BERRY_CHEST, new CCLChest(new TileEntityAlienBerryChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_DEFENDER_BEACON, new CCLAlienDefenderBeacon());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ZELIUS_EGG, new CCLZeliusEgg());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.INFECTED_CRYSTALLIZED_CRYSTAL, new CCLInfectedCrystallizedCrystal());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.MULTALIC_CRYSTAL, new CCLMultalicCrystal());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.JUICER_EGG, new CCLJuicerEgg());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.VEIN_FRAME, new CCLVeinFrame());
            ItemModelRenderer.registerCCLItemRenderer(MPItems.INFECTED_CRYSTALLIZED_BOMB, new CCLInfectedCrystallizedBomb());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_CHEST, new CCLChest(new TileEntityAlienChest()));
            LoggerMP.info("Successfully registered CodeChickenCore item renderer for More Planets blocks/items");
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
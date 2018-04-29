package stevekung.mods.moreplanets.client.renderer;

import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.client.renderer.ccl.*;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.BlockKoentus;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.*;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.*;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityAlienBerryChest;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityInfectedChest;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNibiruAncientChest;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNibiruTreasureChest;
import stevekung.mods.moreplanets.utils.MPLog;
import stevekung.mods.moreplanets.utils.helper.ClientRegisterHelper;
import stevekung.mods.moreplanets.utils.helper.CommonRegisterHelper;

public class ItemModelRenderer
{
    public static void init()
    {
        ItemModelRenderer.registerBlockRenderer();
        ItemModelRenderer.registerBlockVariantsRenderer();
        ItemModelRenderer.registerItemRenderer();
    }

    private static void registerBlockRenderer()
    {
        CommonRegisterHelper.SINGLE_BLOCK_RENDER_LIST.entrySet().forEach(map ->
        {
            Block block = map.getKey();
            String name = map.getValue();
            ClientRegisterHelper.registerModelRender(block, name);
        });
    }

    private static void registerBlockVariantsRenderer()
    {
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_SPONGE, 0, "infected_sponge");
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_SPONGE, 1, "infected_wet_sponge");

        ClientRegisterHelper.registerModelRender(KoentusBlocks.KOENTUS_BLOCK, BlockKoentus.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_LOG, BlockNibiruLog.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_PLANKS, BlockNibiruPlanks.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_ORE, BlockNibiruOre.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_LEAVES, BlockNibiruLeaves.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_SAPLING, BlockNibiruSapling.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_FLOWER, BlockNibiruFlower.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_FENCE, BlockNibiruFence.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.INFECTED_PRISMARINE, BlockInfectedPrismarine.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_CRAFTING_TABLE, BlockNibiruCraftingTable.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_SEAWEED, BlockNibiruSeaweed.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, BlockHalfInfectedPrismarineSlab.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, BlockHalfInfectedStoneBricksSlab.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, BlockHalfNibiruSandstoneSlab.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_BOOKSHELF, BlockNibiruBookshelf.BlockType.class);
        ClientRegisterHelper.registerModelRender(NibiruBlocks.NIBIRU_GRASS_PATH, BlockNibiruGrassPath.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.FRONOS_DIRT, BlockFronosDirt.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.FRONOS_BLOCK, BlockFronos.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.FRONOS_ORE, BlockFronosOre.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.CANDY_CANE_1, BlockCandyCane1.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.CANDY_CANE_2, BlockCandyCane2.BlockType.class);
        ClientRegisterHelper.registerModelRender(FronosBlocks.JELLY_BLOCK, BlockJelly.BlockType.class);
    }

    private static void registerItemRenderer()
    {
        CommonRegisterHelper.SINGLE_ITEM_RENDER_LIST.entrySet().forEach(map ->
        {
            Item item = map.getKey();
            String name = map.getValue();
            ClientRegisterHelper.registerModelRender(item, name);
        });
        ClientRegisterHelper.registerToolsModelRender(DionaItems.ILLENIUM_SWORD, DionaItems.ILLENIUM_SHOVEL, DionaItems.ILLENIUM_PICKAXE, DionaItems.ILLENIUM_AXE, DionaItems.ILLENIUM_HOE, "illenium");
        ClientRegisterHelper.registerToolsModelRender(ChalosItems.DIREMSIUM_SWORD, ChalosItems.DIREMSIUM_SHOVEL, ChalosItems.DIREMSIUM_PICKAXE, ChalosItems.DIREMSIUM_AXE, ChalosItems.DIREMSIUM_HOE, "diremsium");
        ClientRegisterHelper.registerToolsModelRender(ChalosItems.CHEESE_SPORE_WOOD_SWORD, ChalosItems.CHEESE_SPORE_WOOD_SHOVEL, ChalosItems.CHEESE_SPORE_WOOD_PICKAXE, ChalosItems.CHEESE_SPORE_WOOD_AXE, ChalosItems.CHEESE_SPORE_WOOD_HOE, "cheese_spore_wood");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.INFECTED_WOOD_SWORD, NibiruItems.INFECTED_WOOD_SHOVEL, NibiruItems.INFECTED_WOOD_PICKAXE, NibiruItems.INFECTED_WOOD_AXE, NibiruItems.INFECTED_WOOD_HOE, "infected_wood");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.ALIEN_BERRY_WOOD_SWORD, NibiruItems.ALIEN_BERRY_WOOD_SHOVEL, NibiruItems.ALIEN_BERRY_WOOD_PICKAXE, NibiruItems.ALIEN_BERRY_WOOD_AXE, NibiruItems.ALIEN_BERRY_WOOD_HOE, "alien_berry_wood");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.NIBIRU_STONE_SWORD, NibiruItems.NIBIRU_STONE_SHOVEL, NibiruItems.NIBIRU_STONE_PICKAXE, NibiruItems.NIBIRU_STONE_AXE, NibiruItems.NIBIRU_STONE_HOE, "nibiru_stone");
        ClientRegisterHelper.registerToolsModelRender(NibiruItems.MULTALIC_CRYSTAL_SWORD, NibiruItems.MULTALIC_CRYSTAL_SHOVEL, NibiruItems.MULTALIC_CRYSTAL_PICKAXE, NibiruItems.MULTALIC_CRYSTAL_AXE, NibiruItems.MULTALIC_CRYSTAL_HOE, "multalic_crystal");
        ClientRegisterHelper.registerArmorModelRender(DionaItems.ILLENIUM_HELMET, DionaItems.ILLENIUM_CHESTPLATE, DionaItems.ILLENIUM_LEGGINGS, DionaItems.ILLENIUM_BOOTS, "illenium");
        ClientRegisterHelper.registerArmorModelRender(ChalosItems.DIREMSIUM_HELMET, ChalosItems.DIREMSIUM_CHESTPLATE, ChalosItems.DIREMSIUM_LEGGINGS, ChalosItems.DIREMSIUM_BOOTS, "diremsium");
        ClientRegisterHelper.registerArmorModelRender(NibiruItems.MULTALIC_CRYSTAL_HELMET, NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, NibiruItems.MULTALIC_CRYSTAL_BOOTS, "multalic_crystal");
    }

    public static void registerCCLRenderer()
    {
        try
        {
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.BLACK_HOLE_STORAGE, new RenderBlackHoleStorage());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.DARK_ENERGY_RECEIVER, new RenderDarkEnergyReceiver());
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.SHIELD_GENERATOR, new RenderShieldGenerator());
            ItemModelRenderer.registerCCLItemRenderer(DionaBlocks.DARK_ENERGY_CORE, new RenderDarkEnergyCore());
            ItemModelRenderer.registerCCLItemRenderer(DionaBlocks.DARK_ENERGY_GENERATOR, new RenderDarkEnergyGenerator());
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.NUCLEAR_WASTE_TANK, new RenderNuclearWasteTank());
            ItemModelRenderer.registerCCLItemRenderer(ChalosBlocks.CHEESE_SPORE_CHEST, new RenderChest(new TileEntityCheeseSporeChest()));
            ItemModelRenderer.registerCCLItemRenderer(DionaBlocks.DIONA_ANCIENT_CHEST, new RenderChest(new TileEntityDionaAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(DionaBlocks.DIONA_TREASURE_CHEST, new RenderChest(new TileEntityDionaTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(ChalosBlocks.CHALOS_ANCIENT_CHEST, new RenderChest(new TileEntityChalosAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(ChalosBlocks.CHALOS_TREASURE_CHEST, new RenderChest(new TileEntityChalosTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.NIBIRU_ANCIENT_CHEST, new RenderChest(new TileEntityNibiruAncientChest()));
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.NIBIRU_TREASURE_CHEST, new RenderChest(new TileEntityNibiruTreasureChest()));
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.INFECTED_CHEST, new RenderChest(new TileEntityInfectedChest()));
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.ALIEN_BERRY_CHEST, new RenderChest(new TileEntityAlienBerryChest()));
            ItemModelRenderer.registerCCLItemRenderer(MPBlocks.ALIEN_DEFENDER_BEACON, new RenderAlienDefenderBeacon());
            ItemModelRenderer.registerCCLItemRenderer(DionaBlocks.ZELIUS_EGG, new RenderZeliusEgg());
            ItemModelRenderer.registerCCLItemRenderer(DionaBlocks.LARGE_INFECTED_CRYSTALLIZED, new RenderLargeInfectedCrystallized());
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.MULTALIC_CRYSTAL, new RenderMultalicCrystal());
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.JUICER_EGG, new RenderJuicerEgg());
            ItemModelRenderer.registerCCLItemRenderer(NibiruBlocks.VEIN_FRAME, new RenderVeinFrame());
            ItemModelRenderer.registerCCLItemRenderer(DionaItems.INFECTED_CRYSTALLIZED_BOMB, new RenderInfectedCrystallizedBombCCL());

            MPLog.info("Successfully registered CodeChickenCore item rendering for More Planets tile entities");
        }
        catch (Exception e)
        {
            MPLog.info("CodeChickenCore not loaded, using vanilla TileEntityItemStackRenderer");
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
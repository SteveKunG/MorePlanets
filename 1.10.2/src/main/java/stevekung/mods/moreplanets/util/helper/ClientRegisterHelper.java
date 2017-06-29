package stevekung.mods.moreplanets.util.helper;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap.Builder;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.EnumStateMapper;
import stevekung.mods.moreplanets.util.client.model.ModelBipedTranslucent;

@SideOnly(Side.CLIENT)
public class ClientRegisterHelper
{
    public static void registerEntityRendering(Class<? extends Entity> entity, final Class<? extends Render> render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entity, new IRenderFactory()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                try
                {
                    return render.getConstructor(RenderManager.class).newInstance(manager);
                }
                catch (Exception e)
                {
                    return null;
                }
            }
        });
    }

    public static void registerEntityRendering(Class<? extends Entity> entity, IRenderFactory render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entity, render);
    }

    public static void registerTileEntityRendering(Class<? extends TileEntity> tile, TileEntitySpecialRenderer render)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, render);
    }

    public static void registerTileEntityItemStackRendering(TileEntity tile)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(tile, 0.0D);
    }

    public static void registerTileEntityItemStackRendering(TileEntity tile, double yOffset)
    {
        TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0.0D, yOffset, 0.0D, 0.0F);
    }

    public static void registerVariantsName(Item item, String... variant)
    {
        for (String name : variant)
        {
            ModelBakery.registerItemVariants(item, new ModelResourceLocation("moreplanets:" + name, "inventory"));
        }
    }

    public static void registerVariantsName(Item item, Class<? extends Enum> enumClass)
    {
        String[] enumNameList = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);

        for (String name : enumNameList)
        {
            ClientRegisterHelper.registerVariantsName(item, name.toLowerCase());
        }
    }

    public static void registerVariantsName(Block block, Class<? extends Enum> enumClass)
    {
        String[] enumNameList = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);

        for (String name : enumNameList)
        {
            ClientRegisterHelper.registerVariantsName(block, name.toLowerCase());
        }
    }

    // For internal redstone ore only
    public static void registerVariantsName(Block block, Class<? extends Enum> enumClass, List<String> old, List<String> replace)
    {
        String[] enumNameList = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);

        for (String name : enumNameList)
        {
            for (String oldS : old)
            {
                for (String replaceS : replace)
                {
                    name = name.toLowerCase().replace(oldS, replaceS);
                }
            }
            ClientRegisterHelper.registerVariantsName(block, name.toLowerCase());
        }
    }

    public static void registerVariantsName(Block block, String... variant)
    {
        ClientRegisterHelper.registerVariantsName(Item.getItemFromBlock(block), variant);
    }

    public static void registerVariantNameWithDyeColor(Block block)
    {
        String name = block.getUnlocalizedName().substring(5);
        ClientRegisterHelper.registerVariantsName(block, "white_" + name);
        ClientRegisterHelper.registerVariantsName(block, "orange_" + name);
        ClientRegisterHelper.registerVariantsName(block, "magenta_" + name);
        ClientRegisterHelper.registerVariantsName(block, "light_blue_" + name);
        ClientRegisterHelper.registerVariantsName(block, "yellow_" + name);
        ClientRegisterHelper.registerVariantsName(block, "lime_" + name);
        ClientRegisterHelper.registerVariantsName(block, "pink_" + name);
        ClientRegisterHelper.registerVariantsName(block, "gray_" + name);
        ClientRegisterHelper.registerVariantsName(block, "silver_" + name);
        ClientRegisterHelper.registerVariantsName(block, "cyan_" + name);
        ClientRegisterHelper.registerVariantsName(block, "purple_" + name);
        ClientRegisterHelper.registerVariantsName(block, "blue_" + name);
        ClientRegisterHelper.registerVariantsName(block, "brown_" + name);
        ClientRegisterHelper.registerVariantsName(block, "green_" + name);
        ClientRegisterHelper.registerVariantsName(block, "red_" + name);
        ClientRegisterHelper.registerVariantsName(block, "black_" + name);
    }

    public static void registerVariantNameWithDyeColor(Item item)
    {
        String name = item.getUnlocalizedName().substring(5);
        ClientRegisterHelper.registerVariantsName(item, "white_" + name);
        ClientRegisterHelper.registerVariantsName(item, "orange_" + name);
        ClientRegisterHelper.registerVariantsName(item, "magenta_" + name);
        ClientRegisterHelper.registerVariantsName(item, "light_blue_" + name);
        ClientRegisterHelper.registerVariantsName(item, "yellow_" + name);
        ClientRegisterHelper.registerVariantsName(item, "lime_" + name);
        ClientRegisterHelper.registerVariantsName(item, "pink_" + name);
        ClientRegisterHelper.registerVariantsName(item, "gray_" + name);
        ClientRegisterHelper.registerVariantsName(item, "silver_" + name);
        ClientRegisterHelper.registerVariantsName(item, "cyan_" + name);
        ClientRegisterHelper.registerVariantsName(item, "purple_" + name);
        ClientRegisterHelper.registerVariantsName(item, "blue_" + name);
        ClientRegisterHelper.registerVariantsName(item, "brown_" + name);
        ClientRegisterHelper.registerVariantsName(item, "green_" + name);
        ClientRegisterHelper.registerVariantsName(item, "red_" + name);
        ClientRegisterHelper.registerVariantsName(item, "black_" + name);
    }

    public static void registerModelRender(Block block, int meta, String variantName)
    {
        ClientRegisterHelper.registerModelRender(Item.getItemFromBlock(block), meta, variantName);
    }

    public static void registerModelRender(Block block, Class<? extends Enum> enumClass)
    {
        ClientRegisterHelper.registerModelRender(Item.getItemFromBlock(block), enumClass);
    }

    public static void registerModelRender(Block block, String variantName)
    {
        ClientRegisterHelper.registerModelRender(Item.getItemFromBlock(block), 0, variantName);
    }

    public static void registerModelRender(Item item, int meta, String variantName)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("moreplanets:" + variantName, "inventory"));
    }

    public static void registerModelRender(Item item, Class<? extends Enum> enumClass)
    {
        for (Enum enumObj : enumClass.getEnumConstants())
        {
            ClientRegisterHelper.registerModelRender(item, enumObj.ordinal(), enumObj.name().toLowerCase());
        }
    }

    public static void registerRocketModelRender(Item item, int tier)
    {
        for (int i = 0; i < 5; ++i)
        {
            ClientRegisterHelper.registerModelRender(item, i, "tier_" + tier + "_rocket");
        }
    }

    public static void registerModelRender(Item item, ItemMeshDefinition itemMesh)
    {
        ModelLoader.setCustomMeshDefinition(item, itemMesh);
    }

    public static void registerModelRender(Block block, ItemMeshDefinition itemMesh)
    {
        ClientRegisterHelper.registerModelRender(Item.getItemFromBlock(block), itemMesh);
    }

    public static void registerModelRender(Item item, String variantName)
    {
        ClientRegisterHelper.registerModelRender(item, 0, variantName);
    }

    public static void registerToolsModelRender(Item sword, Item shovel, Item pickaxe, Item axe, Item hoe, String toolName)
    {
        ClientRegisterHelper.registerModelRender(sword, 0, toolName + "_sword");
        ClientRegisterHelper.registerModelRender(shovel, 0, toolName + "_shovel");
        ClientRegisterHelper.registerModelRender(pickaxe, 0, toolName + "_pickaxe");
        ClientRegisterHelper.registerModelRender(axe, 0, toolName + "_axe");
        ClientRegisterHelper.registerModelRender(hoe, 0, toolName + "_hoe");
    }

    public static void registerArmorModelRender(Item helmet, Item chestplate, Item leggings, Item boots, String toolName)
    {
        ClientRegisterHelper.registerModelRender(helmet, 0, toolName + "_helmet");
        ClientRegisterHelper.registerModelRender(chestplate, 0, toolName + "_chestplate");
        ClientRegisterHelper.registerModelRender(leggings, 0, toolName + "_leggings");
        ClientRegisterHelper.registerModelRender(boots, 0, toolName + "_boots");
    }

    public static void registerModelRenderWithDyeColor(Block block)
    {
        String name = block.getUnlocalizedName().substring(5);
        ClientRegisterHelper.registerModelRender(block, 0, "white_" + name);
        ClientRegisterHelper.registerModelRender(block, 1, "orange_" + name);
        ClientRegisterHelper.registerModelRender(block, 2, "magenta_" + name);
        ClientRegisterHelper.registerModelRender(block, 3, "light_blue_" + name);
        ClientRegisterHelper.registerModelRender(block, 4, "yellow_" + name);
        ClientRegisterHelper.registerModelRender(block, 5, "lime_" + name);
        ClientRegisterHelper.registerModelRender(block, 6, "pink_" + name);
        ClientRegisterHelper.registerModelRender(block, 7, "gray_" + name);
        ClientRegisterHelper.registerModelRender(block, 8, "silver_" + name);
        ClientRegisterHelper.registerModelRender(block, 9, "cyan_" + name);
        ClientRegisterHelper.registerModelRender(block, 10, "purple_" + name);
        ClientRegisterHelper.registerModelRender(block, 11, "blue_" + name);
        ClientRegisterHelper.registerModelRender(block, 12, "brown_" + name);
        ClientRegisterHelper.registerModelRender(block, 13, "green_" + name);
        ClientRegisterHelper.registerModelRender(block, 14, "red_" + name);
        ClientRegisterHelper.registerModelRender(block, 15, "black_" + name);
    }

    public static void registerModelRenderWithDyeColor(Item item)
    {
        String name = item.getUnlocalizedName().substring(5);
        ClientRegisterHelper.registerModelRender(item, 0, "white_" + name);
        ClientRegisterHelper.registerModelRender(item, 1, "orange_" + name);
        ClientRegisterHelper.registerModelRender(item, 2, "magenta_" + name);
        ClientRegisterHelper.registerModelRender(item, 3, "light_blue_" + name);
        ClientRegisterHelper.registerModelRender(item, 4, "yellow_" + name);
        ClientRegisterHelper.registerModelRender(item, 5, "lime_" + name);
        ClientRegisterHelper.registerModelRender(item, 6, "pink_" + name);
        ClientRegisterHelper.registerModelRender(item, 7, "gray_" + name);
        ClientRegisterHelper.registerModelRender(item, 8, "silver_" + name);
        ClientRegisterHelper.registerModelRender(item, 9, "cyan_" + name);
        ClientRegisterHelper.registerModelRender(item, 10, "purple_" + name);
        ClientRegisterHelper.registerModelRender(item, 11, "blue_" + name);
        ClientRegisterHelper.registerModelRender(item, 12, "brown_" + name);
        ClientRegisterHelper.registerModelRender(item, 13, "green_" + name);
        ClientRegisterHelper.registerModelRender(item, 14, "red_" + name);
        ClientRegisterHelper.registerModelRender(item, 15, "black_" + name);
    }

    public static void registerStateMapper(Block block, EnumStateMapper mapper)
    {
        ClientRegisterHelper.registerStateMapper(block, new Builder().ignore(mapper.getProperty()).build());
    }

    public static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    public static void registerStateMapperSplitVariants(Block block, IProperty property)
    {
        ClientRegisterHelper.registerStateMapper(block, new Builder().withName(property).build());
    }

    public static void registerStateMapper(Block block, IProperty... property)
    {
        ClientRegisterHelper.registerStateMapper(block, new Builder().ignore(property).build());
    }

    public static void registerSpriteTexture(TextureStitchEvent.Pre event, String texture)
    {
        event.getMap().registerSprite(new ResourceLocation("moreplanets:" + texture));
    }

    public static ModelBiped getTranclucentArmorModel(EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        if (armorSlot == EntityEquipmentSlot.LEGS)
        {
            ModelBipedTranslucent model = new ModelBipedTranslucent(0.5F);
            model.isChild = false;
            model.setModelAttributes(defaultModel);
            return model;
        }
        if (armorSlot == EntityEquipmentSlot.HEAD || armorSlot == EntityEquipmentSlot.CHEST || armorSlot == EntityEquipmentSlot.FEET)
        {
            ModelBipedTranslucent model = new ModelBipedTranslucent(1.0F);
            model.isChild = false;
            model.setModelAttributes(defaultModel);
            return model;
        }
        return defaultModel;
    }

    public static void registerOBJModel(ModelBakeEvent event, String name, String file, List<String> visibleGroups, Class<? extends ModelTransformWrapper> clazz, IModelState parentState)
    {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation("moreplanets:" + name, "inventory");
        IBakedModel object = event.getModelRegistry().getObject(modelResourceLocation);

        if (object != null)
        {
            IBakedModel newModel;

            try
            {
                OBJModel model = (OBJModel) ModelLoaderRegistry.getModel(new ResourceLocation("moreplanets:obj/" + file + ".obj"));
                model = (OBJModel) model.process(ImmutableMap.of("flip-v", "true"));
                Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
                newModel = model.bake(new OBJModel.OBJState(visibleGroups, false, parentState), DefaultVertexFormats.ITEM, spriteFunction);

                if (clazz != null)
                {
                    newModel = clazz.getConstructor(IBakedModel.class).newInstance(newModel);
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            event.getModelRegistry().putObject(modelResourceLocation, newModel);
        }
    }

    public static void registerBlockColor(IBlockColor blockColor, Block block)
    {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(blockColor, block);
    }

    public static void registerItemColor(IItemColor blockColor, Block block)
    {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(blockColor, block);
    }

    public static void registerItemColor(IItemColor blockColor, Item item)
    {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(blockColor, item);
    }

    private static void registerVariantsName(Block block, String variant)
    {
        ClientRegisterHelper.registerVariantsName(Item.getItemFromBlock(block), variant);
    }

    private static void registerVariantsName(Item item, String variant)
    {
        ModelBakery.registerItemVariants(item, new ModelResourceLocation("moreplanets:" + variant, "inventory"));
    }
}
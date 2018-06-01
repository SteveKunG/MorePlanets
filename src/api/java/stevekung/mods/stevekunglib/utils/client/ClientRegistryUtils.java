package stevekung.mods.stevekunglib.utils.client;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import stevekung.mods.stevekunglib.utils.enums.EnumStateMapper;

public class ClientRegistryUtils
{
    private final String resourcePath;

    public ClientRegistryUtils(@Nonnull String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    public void registerVariantsName(Item item, String... variant)
    {
        for (String name : variant)
        {
            ModelBakery.registerItemVariants(item, new ModelResourceLocation(this.resourcePath + ":" + name, "inventory"));
        }
    }

    public void registerVariantsName(Block block, String... variant)
    {
        this.registerVariantsName(Item.getItemFromBlock(block), variant);
    }

    public void registerModelRender(Block block, String variantName)
    {
        this.registerModelRender(Item.getItemFromBlock(block), variantName);
    }

    public void registerModelRender(Item item, String variantName)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(this.resourcePath + ":" + variantName, "inventory"));
    }

    public void registerSpriteTexture(TextureStitchEvent.Pre event, String texture)
    {
        event.getMap().registerSprite(new ResourceLocation(this.resourcePath + ":" + texture));
    }

    public static <E extends Entity> void registerEntityRendering(Class<E> entity, IRenderFactory<E> render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entity, render);
    }

    public static <T extends TileEntity> void registerTileEntityRendering(Class<T> tile, TileEntitySpecialRenderer<? super T> render)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, render);
    }

    public static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    public static void registerStateMapper(Block block, EnumStateMapper mapper)
    {
        ClientRegistryUtils.registerStateMapper(block, new StateMap.Builder().ignore(mapper.getProperty()).build());
    }

    public static void registerStateMapperSplitVariants(Block block, IProperty property)
    {
        ClientRegistryUtils.registerStateMapper(block, new StateMap.Builder().withName(property).build());
    }

    public static void registerStateMapper(Block block, IProperty... property)
    {
        ClientRegistryUtils.registerStateMapper(block, new StateMap.Builder().ignore(property).build());
    }

    public static void renderTESR(TileEntity tile)
    {
        ClientRegistryUtils.renderTESR(tile, 0.0D);
    }

    public static void renderTESR(TileEntity tile, double yOffset)
    {
        TileEntityRendererDispatcher.instance.render(tile, 0.0D, yOffset, 0.0D, 0.0F);
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
}
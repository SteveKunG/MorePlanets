package stevekung.mods.stevekunglib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientRegistryUtils
{
    public static <E extends Entity> void registerEntityRendering(Class<E> entity, IRenderFactory<E> render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entity, render);
    }

    public static <T extends TileEntity> void registerTileEntityRendering(Class<T> tile, TileEntitySpecialRenderer<? super T> render)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, render);
    }

    public static void registerStateMapper(Block block, EnumStateMapper mapper)
    {
        ClientRegistryUtils.registerStateMapper(block, new StateMap.Builder().ignore(mapper.getProperty()).build());
    }

    public static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
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
}
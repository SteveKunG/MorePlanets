package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityVeinFrame;
import stevekung.mods.moreplanets.utils.helper.ClientRegisterHelper;

public class RenderVeinFrame extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityVeinFrame());
        GlStateManager.enableBlend();
    }
}
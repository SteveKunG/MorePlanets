package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityVeinFrame;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

public class RenderVeinFrame extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegistryUtils.renderTESR(new TileEntityVeinFrame());
        GlStateManager.enableBlend();
    }
}
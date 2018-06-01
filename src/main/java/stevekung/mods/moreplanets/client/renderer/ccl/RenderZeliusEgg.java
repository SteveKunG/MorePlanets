package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

public class RenderZeliusEgg extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegistryUtils.renderTESR(new TileEntityZeliusEgg());
        GlStateManager.enableBlend();
    }
}
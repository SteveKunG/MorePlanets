package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderAlienDefenderBeacon extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityAlienDefenderBeacon());
        GlStateManager.enableBlend();
        GlStateManager.enableCull();
    }
}
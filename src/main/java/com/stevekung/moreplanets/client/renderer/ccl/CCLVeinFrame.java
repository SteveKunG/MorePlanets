package com.stevekung.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.planets.nibiru.tileentity.TileEntityVeinFrame;
import com.stevekung.lib.utils.client.ClientRegistryUtils;

public class CCLVeinFrame extends CCLRenderBase
{
    private final TileEntityVeinFrame frame = new TileEntityVeinFrame();

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegistryUtils.renderTESR(this.frame);
        GlStateManager.enableBlend();
    }
}
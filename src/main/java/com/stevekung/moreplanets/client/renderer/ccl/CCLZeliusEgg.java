package com.stevekung.moreplanets.client.renderer.ccl;

import com.stevekung.lib.utils.client.ClientRegistryUtils;
import com.stevekung.moreplanets.planets.diona.tileentity.TileEntityZeliusEgg;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class CCLZeliusEgg extends CCLRenderBase
{
    private final TileEntityZeliusEgg egg = new TileEntityZeliusEgg();

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegistryUtils.renderTESR(this.egg);
        GlStateManager.enableBlend();
    }
}
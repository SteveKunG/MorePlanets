package com.stevekung.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

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
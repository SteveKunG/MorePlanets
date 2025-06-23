package com.stevekung.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

public class CCLAlienDefenderBeacon extends CCLRenderBase
{
    private final TileEntityAlienDefenderBeacon alienBeacon = new TileEntityAlienDefenderBeacon();

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegistryUtils.renderTESR(this.alienBeacon);
        GlStateManager.enableBlend();
        GlStateManager.enableCull();
    }
}
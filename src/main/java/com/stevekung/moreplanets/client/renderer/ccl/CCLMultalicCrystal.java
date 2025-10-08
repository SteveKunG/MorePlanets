package com.stevekung.moreplanets.client.renderer.ccl;

import com.stevekung.moreplanets.planets.nibiru.client.renderer.tileentity.TileEntityMultalicCrystalRenderer;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class CCLMultalicCrystal extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityMultalicCrystalRenderer.INSTANCE.render();
    }
}
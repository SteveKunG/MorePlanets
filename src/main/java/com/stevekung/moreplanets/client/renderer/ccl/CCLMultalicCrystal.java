package com.stevekung.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.planets.nibiru.client.renderer.tileentity.TileEntityMultalicCrystalRenderer;

public class CCLMultalicCrystal extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityMultalicCrystalRenderer.INSTANCE.render();
    }
}
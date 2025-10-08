package com.stevekung.moreplanets.client.renderer.ccl;

import com.stevekung.moreplanets.planets.diona.client.renderer.tileentity.TileEntityInfectedPurloniteCrystalRenderer;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

public class CCLInfectedPurloniteCrystal extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityInfectedPurloniteCrystalRenderer.INSTANCE.render();
    }
}
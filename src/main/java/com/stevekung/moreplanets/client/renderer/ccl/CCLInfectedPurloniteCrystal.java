package com.stevekung.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.planets.diona.client.renderer.tileentity.TileEntityInfectedPurloniteCrystalRenderer;

public class CCLInfectedPurloniteCrystal extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityInfectedPurloniteCrystalRenderer.INSTANCE.render();
    }
}
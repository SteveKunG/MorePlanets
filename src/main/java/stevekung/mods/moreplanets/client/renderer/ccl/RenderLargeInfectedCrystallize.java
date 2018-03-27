package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.TileEntityLargeInfectedCrystallizeRenderer;

public class RenderLargeInfectedCrystallize extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityLargeInfectedCrystallizeRenderer.INSTANCE.render();
    }
}
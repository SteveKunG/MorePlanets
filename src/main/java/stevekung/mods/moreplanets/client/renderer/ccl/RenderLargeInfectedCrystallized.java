package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity.TileEntityLargeInfectedCrystallizedRenderer;

public class RenderLargeInfectedCrystallized extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityLargeInfectedCrystallizedRenderer.INSTANCE.render();
    }
}
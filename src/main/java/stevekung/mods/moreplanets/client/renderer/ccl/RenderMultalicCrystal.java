package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity.TileEntityMultalicCrystalRenderer;

public class RenderMultalicCrystal extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityMultalicCrystalRenderer.INSTANCE.render();
    }
}
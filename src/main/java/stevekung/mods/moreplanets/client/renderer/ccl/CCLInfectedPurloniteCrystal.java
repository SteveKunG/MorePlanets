package stevekung.mods.moreplanets.client.renderer.ccl;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity.TileEntityInfectedPurloniteCrystalRenderer;

public class CCLInfectedPurloniteCrystal extends CCLRenderBase
{
    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        TileEntityInfectedPurloniteCrystalRenderer.INSTANCE.render();
    }
}
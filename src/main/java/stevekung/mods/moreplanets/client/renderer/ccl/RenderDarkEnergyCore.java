package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderDarkEnergyCore extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/dark_energy_core");
    }

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        float scale = 0.9625F;
        GlStateManager.translate(0.0625F, -0.11625F, 0.0F);
        GlStateManager.scale(scale, scale, scale);
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityDarkEnergyCore());
    }
}
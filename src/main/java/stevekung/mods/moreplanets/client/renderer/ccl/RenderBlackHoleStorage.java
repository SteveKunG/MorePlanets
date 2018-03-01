package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderBlackHoleStorage extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/machine_side");
    }

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        float scale = 1.0625F;
        GlStateManager.translate(-0.0625F, 0.0F, 0.0F);
        GlStateManager.scale(scale, scale, scale);
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityBlackHoleStorage());
        GlStateManager.enableBlend();
    }
}
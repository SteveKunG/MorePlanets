package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntityShieldGeneratorRenderer;

public class RenderShieldGenerator extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/machine_side");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        TileEntityShieldGeneratorRenderer.INSTANCE.render(0.70625F, 0.88625F);
    }
}
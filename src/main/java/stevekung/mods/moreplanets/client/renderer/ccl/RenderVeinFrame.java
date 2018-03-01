package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityVeinFrame;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderVeinFrame extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/vein_frame");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityVeinFrame());
        GlStateManager.enableBlend();
    }
}
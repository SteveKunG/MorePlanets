package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderZeliusEgg extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/zelius_egg");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityZeliusEgg());
        GlStateManager.enableBlend();
    }
}
package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderAlienDefenderBeacon extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/alien_defender");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityAlienDefenderBeacon());
        GlStateManager.enableBlend();
        GlStateManager.enableCull();
    }
}
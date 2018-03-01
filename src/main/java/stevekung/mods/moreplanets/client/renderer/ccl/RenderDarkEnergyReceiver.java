package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderDarkEnergyReceiver extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/dark_energy_receiver");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityDarkEnergyReceiver());
    }
}
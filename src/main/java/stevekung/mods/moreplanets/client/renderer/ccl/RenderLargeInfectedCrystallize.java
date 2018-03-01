package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.TileEntityLargeInfectedCrystallizeRenderer;

public class RenderLargeInfectedCrystallize extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/crystal");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        TileEntityLargeInfectedCrystallizeRenderer.INSTANCE.render();
    }
}
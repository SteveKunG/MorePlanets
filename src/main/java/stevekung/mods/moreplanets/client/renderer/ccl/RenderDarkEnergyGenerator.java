package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.TileEntityDarkEnergyGeneratorRenderer;

public class RenderDarkEnergyGenerator extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/machine_side");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        TileEntityDarkEnergyGeneratorRenderer.INSTANCE.render(1.0F, 1.4F);
    }
}
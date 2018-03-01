package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.RenderInfectedCrystallizeBomb;

public class RenderInfectedCrystallizedBomb extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/dark_energy_core");
    }

    @Override
    public void renderItem(ItemStack itemStack)
    {
        RenderInfectedCrystallizeBomb.INSTANCE.render(2.75F, 3.5F);
    }
}
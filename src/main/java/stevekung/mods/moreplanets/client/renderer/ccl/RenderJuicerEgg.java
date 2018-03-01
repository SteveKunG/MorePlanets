package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityJuicerEgg;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderJuicerEgg extends CCLRenderBase
{
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("moreplanets:blocks/juicer_egg");
    }

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityJuicerEgg());
    }
}
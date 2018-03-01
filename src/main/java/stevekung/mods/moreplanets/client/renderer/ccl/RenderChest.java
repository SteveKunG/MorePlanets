package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

public class RenderChest extends CCLRenderBase
{
    private final TileEntity tile;

    public RenderChest(TileEntity tile)
    {
        this.tile = tile;
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return TextureUtils.getTexture("blocks/planks");
    }

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType type)
    {
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-1.0F, 0.0F, -1.0F);
        ClientRegisterHelper.registerTileEntityItemStackRendering(this.tile);
    }
}
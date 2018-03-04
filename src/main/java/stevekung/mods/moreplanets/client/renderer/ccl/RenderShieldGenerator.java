package stevekung.mods.moreplanets.client.renderer.ccl;

import com.google.common.collect.ImmutableMap;

import codechicken.lib.render.CCModelState;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.TRSRTransformation;
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
        TileEntityShieldGeneratorRenderer.INSTANCE.render();
    }

    @Override
    protected CCModelState getCustomTransforms()
    {
        ImmutableMap.Builder<TransformType, TRSRTransformation> builder = ImmutableMap.builder();
        TRSRTransformation thirdPerson = TransformUtils.get(0, 2.5F, 0, 75, 45, 0, 0.375F);
        builder.put(TransformType.GUI, TransformUtils.get(0, -2.65F, 0, 30, 135, 0, 0.455F));
        builder.put(TransformType.GROUND, TransformUtils.get(0, 3, 0, 0, 0, 0, 0.25F));
        builder.put(TransformType.FIXED, TransformUtils.get(0, 0, 0, 0, 180, 0, 0.5F));
        builder.put(TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
        builder.put(TransformType.THIRD_PERSON_LEFT_HAND, thirdPerson);
        builder.put(TransformType.FIRST_PERSON_RIGHT_HAND, TransformUtils.get(0, 0, 0, 0, 315, 0, 0.4F));
        builder.put(TransformType.FIRST_PERSON_LEFT_HAND, TransformUtils.get(0, 0, 0, 0, 315, 0, 0.4F));
        return new CCModelState(builder.build());
    }
}
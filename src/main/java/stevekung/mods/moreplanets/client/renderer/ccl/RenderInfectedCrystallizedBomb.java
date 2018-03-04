package stevekung.mods.moreplanets.client.renderer.ccl;

import com.google.common.collect.ImmutableMap;

import codechicken.lib.render.CCModelState;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.TRSRTransformation;
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
        RenderInfectedCrystallizeBomb.INSTANCE.render();
    }

    @Override
    protected CCModelState getCustomTransforms()
    {
        ImmutableMap.Builder<TransformType, TRSRTransformation> builder = ImmutableMap.builder();
        TRSRTransformation thirdPerson = TransformUtils.get(0, 1.0F, 0, 75, 45, 0, 0.6F);
        builder.put(TransformType.GUI, TransformUtils.get(0, -5, 0, 30, 225, 0, 1.75F));
        builder.put(TransformType.GROUND, TransformUtils.get(0, -0.5F, 0, 0, 0, 0, 0.65F));
        builder.put(TransformType.FIXED, TransformUtils.get(0, -2.5F, 0, 0, 180, 0, 0.85F));
        builder.put(TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
        builder.put(TransformType.THIRD_PERSON_LEFT_HAND, thirdPerson);
        builder.put(TransformType.FIRST_PERSON_RIGHT_HAND, TransformUtils.get(0, 0, 0, 0, 45, 0, 0.6F));
        builder.put(TransformType.FIRST_PERSON_LEFT_HAND, TransformUtils.get(0, 0, 0, 0, 225, 0, 0.6F));
        return new CCModelState(builder.build());
    }
}
package stevekung.mods.moreplanets.client.renderer.ccl;

import com.google.common.collect.ImmutableMap;

import codechicken.lib.render.CCModelState;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public abstract class CCLRenderBase implements IItemRenderer
{
    @Override
    public boolean isAmbientOcclusion()
    {
        return false;
    }

    @Override
    public boolean isGui3d()
    {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return true;
    }

    @Override
    public IModelState getTransforms()
    {
        return this.getCustomTransforms();
    }

    protected CCModelState getCustomTransforms()
    {
        ImmutableMap.Builder<TransformType, TRSRTransformation> builder = ImmutableMap.builder();
        TRSRTransformation thirdPerson = TransformUtils.get(0, 2.5F, 0, 75, 45, 0, 0.375F);
        builder.put(TransformType.GUI, TransformUtils.get(0, 0, 0, 30, 225, 0, 0.625F));
        builder.put(TransformType.GROUND, TransformUtils.get(0, 3, 0, 0, 0, 0, 0.25F));
        builder.put(TransformType.FIXED, TransformUtils.get(0, 0, 0, 0, 0, 0, 0.5F));
        builder.put(TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
        builder.put(TransformType.THIRD_PERSON_LEFT_HAND, thirdPerson);
        builder.put(TransformType.FIRST_PERSON_RIGHT_HAND, TransformUtils.get(0, 0, 0, 0, 45, 0, 0.4F));
        builder.put(TransformType.FIRST_PERSON_LEFT_HAND, TransformUtils.get(0, 0, 0, 0, 225, 0, 0.4F));
        return new CCModelState(builder.build());
    }
}
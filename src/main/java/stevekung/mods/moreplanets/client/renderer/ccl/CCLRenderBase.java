package stevekung.mods.moreplanets.client.renderer.ccl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableMap;

import codechicken.lib.render.CCModelState;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;

public abstract class CCLRenderBase implements IItemRenderer, IPerspectiveAwareModel
{
    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing facing, long rand)
    {
        return new ArrayList<>();
    }

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
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return ItemOverrideList.NONE;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType type)
    {
        return MapWrapper.handlePerspective(this, this.getCustomTransforms().getTransforms(), type);
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
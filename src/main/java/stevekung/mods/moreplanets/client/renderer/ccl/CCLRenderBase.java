package stevekung.mods.moreplanets.client.renderer.ccl;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import net.minecraftforge.common.model.IModelState;

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
        return false;
    }

    @Override
    public IModelState getTransforms()
    {
        return TransformUtils.DEFAULT_BLOCK;
    }
}
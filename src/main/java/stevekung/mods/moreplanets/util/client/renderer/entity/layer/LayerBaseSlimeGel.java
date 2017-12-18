package stevekung.mods.moreplanets.util.client.renderer.entity.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.entity.EntitySlimeBaseMP;

@SideOnly(Side.CLIENT)
public class LayerBaseSlimeGel implements LayerRenderer<EntitySlimeBaseMP>
{
    private RenderLiving render;
    private ModelBase model = new ModelSlime(0);

    public LayerBaseSlimeGel(RenderLiving render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntitySlimeBaseMP entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entity.isInvisible())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            this.model.setModelAttributes(this.render.getMainModel());
            this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
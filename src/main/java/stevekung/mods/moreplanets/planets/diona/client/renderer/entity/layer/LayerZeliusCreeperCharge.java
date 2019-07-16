package stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.RenderZeliusCreeper;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusCreeper;

@SideOnly(Side.CLIENT)
public class LayerZeliusCreeperCharge implements LayerRenderer<EntityZeliusCreeper>
{
    private final RenderZeliusCreeper render;
    private final ModelCreeper model = new ModelCreeper(2.0F);
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");

    public LayerZeliusCreeperCharge(RenderZeliusCreeper render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityZeliusCreeper entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.getPowered())
        {
            boolean flag = entity.isInvisible();
            GlStateManager.depthMask(!flag);
            this.render.bindTexture(LayerZeliusCreeperCharge.TEXTURE);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = entity.ticksExisted + partialTicks;
            GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float f1 = 0.5F;
            GlStateManager.color(f1, f1, f1, 1.0F);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            this.model.setModelAttributes(this.render.getMainModel());
            this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(flag);
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}
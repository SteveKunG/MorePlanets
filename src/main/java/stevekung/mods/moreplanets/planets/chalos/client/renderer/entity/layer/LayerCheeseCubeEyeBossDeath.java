package stevekung.mods.moreplanets.planets.chalos.client.renderer.entity.layer;

import java.util.Random;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseCubeEyeBoss;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

@SideOnly(Side.CLIENT)
public class LayerCheeseCubeEyeBossDeath implements LayerRenderer<EntityCheeseCubeEyeBoss>
{
    @Override
    public void doRenderLayer(EntityCheeseCubeEyeBoss entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.deathTicks > 0)
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder worldrenderer = tessellator.getBuffer();
            float f = (entity.deathTicks + partialTicks) / 200.0F;
            float f1 = 0.0F;

            if (f > 0.8F)
            {
                f1 = (f - 0.8F) / 0.2F;
            }

            Random random = new Random(432L);
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 1.0F, 0.0F);

            for (int i = 0; i < (f + f * f) / 2.0F * 60.0F; ++i)
            {
                GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
                float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
                float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
                worldrenderer.begin(GLConstants.TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
                worldrenderer.pos(0.0D, 0.0D, 0.0D).color(255, 255, 100, (int)(255.0F * (1.0F - f1))).endVertex();
                worldrenderer.pos(-0.866D * f3, f2, -0.5F * f3).color(255, 255, 100, 0).endVertex();
                worldrenderer.pos(0.866D * f3, f2, -0.5F * f3).color(255, 255, 100, 0).endVertex();
                worldrenderer.pos(0.0D, f2, 1.0F * f3).color(255, 255, 100, 0).endVertex();
                worldrenderer.pos(-0.866D * f3, f2, -0.5F * f3).color(255, 255, 100, 0).endVertex();
                tessellator.draw();
            }
            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.shadeModel(7424);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}
package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelAlienMiner;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer.LayerGlowingAlienMiner;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.stevekunglib.utils.client.GLConstants;

@SideOnly(Side.CLIENT)
public class RenderAlienMiner extends RenderLiving<EntityAlienMiner>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/alien_miner.png");
    private static final ResourceLocation BEAM = new ResourceLocation("moreplanets:textures/entity/alien_miner_beam.png");

    public RenderAlienMiner(RenderManager manager)
    {
        super(manager, new ModelAlienMiner(), 0.4F);
        this.addLayer(new LayerGlowingAlienMiner(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlienMiner entity)
    {
        return RenderAlienMiner.TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityAlienMiner entity, float partialTicks)
    {
        GlStateManager.translate(0.0F, 0.5F - entity.getHoverTick(partialTicks), 0.0F);
    }

    @Override
    public void doRender(EntityAlienMiner entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        EntityLivingBase entitylivingbase = entity.getTargetedEntity();

        if (entitylivingbase != null && entity.getChargedTime(partialTicks) > 0.925F)
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder worldrenderer = tessellator.getBuffer();
            this.bindTexture(RenderAlienMiner.BEAM);
            GlStateManager.glTexParameterf(3553, 10242, 10497.0F);
            GlStateManager.glTexParameterf(3553, 10243, 10497.0F);
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.enableAlpha();
            float f1 = 240.0F;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f1, f1);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            float f2 = entity.world.getTotalWorldTime() + partialTicks;
            float f3 = f2 * 0.5F % 1.0F;
            float f4 = entity.getEyeHeight() + 0.25F - 0.5F + entity.getHoverTick(partialTicks);
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y + f4, (float)z);
            Vec3d vec3 = this.getPosition(entitylivingbase, entitylivingbase.height * 0.5D, partialTicks);
            Vec3d vec31 = this.getPosition(entity, f4, partialTicks);
            Vec3d vec32 = vec3.subtract(vec31);
            double d0 = vec32.lengthVector();
            vec32 = vec32.normalize();
            float f5 = (float)Math.acos(vec32.y);
            float f6 = (float)Math.atan2(vec32.z, vec32.x);
            GlStateManager.rotate(((float)Math.PI / 2F + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            int i = 1;
            double d1 = f2 * 0.05D * (1.0D - (i & 1) * 2.5D);
            worldrenderer.begin(GLConstants.QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            int j = 255;
            int k = 255;
            int l = 255;
            double d2 = i * 0.2D;
            double d3 = d2 * 1.41D;
            double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * d3;
            double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * d3;
            double d6 = 0.0D + Math.cos(d1 + Math.PI / 4D) * d3;
            double d7 = 0.0D + Math.sin(d1 + Math.PI / 4D) * d3;
            double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * d3;
            double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * d3;
            double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * d3;
            double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * d3;
            double d12 = 0.0D + Math.cos(d1 + Math.PI) * d2;
            double d13 = 0.0D + Math.sin(d1 + Math.PI) * d2;
            double d14 = 0.0D + Math.cos(d1 + 0.0D) * d2;
            double d15 = 0.0D + Math.sin(d1 + 0.0D) * d2;
            double d16 = 0.0D + Math.cos(d1 + Math.PI / 2D) * d2;
            double d17 = 0.0D + Math.sin(d1 + Math.PI / 2D) * d2;
            double d18 = 0.0D + Math.cos(d1 + Math.PI * 3D / 2D) * d2;
            double d19 = 0.0D + Math.sin(d1 + Math.PI * 3D / 2D) * d2;
            double d22 = -1.0F + f3;
            double d23 = d0 * (0.5D / d2) + d22;
            worldrenderer.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d16, d0, d17).tex(0.4999D, d23).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d18, 0.0D, d19).tex(0.0D, d22).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d18, d0, d19).tex(0.0D, d23).color(j, k, l, 100).endVertex();
            double d24 = 0.0D;

            if (entity.ticksExisted % 2 == 0)
            {
                d24 = 0.5D;
            }

            worldrenderer.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d10, d0, d11).tex(1.0D, d24).color(j, k, l, 100).endVertex();
            worldrenderer.pos(d8, d0, d9).tex(0.5D, d24).color(j, k, l, 100).endVertex();
            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.enableBlend();
            GlStateManager.popMatrix();
        }
    }

    private Vec3d getPosition(EntityLivingBase entity, double height, float partialTicks)
    {
        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks;
        double d1 = height + entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks;
        return new Vec3d(d0, d1, d2);
    }
}
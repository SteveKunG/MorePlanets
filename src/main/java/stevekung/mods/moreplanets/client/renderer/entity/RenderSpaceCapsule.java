package stevekung.mods.moreplanets.client.renderer.entity;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.planets.mars.client.model.ModelBalloonParachute;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.client.renderer.OBJLoaderMP;
import stevekung.mods.moreplanets.entity.EntitySpaceCapsule;

public class RenderSpaceCapsule extends Render<EntitySpaceCapsule>
{
    private IBakedModel modelEntryPod;
    private IBakedModel modelFlame;
    protected ModelBalloonParachute parachuteModel = new ModelBalloonParachute();

    public RenderSpaceCapsule(RenderManager manager)
    {
        super(manager);
    }

    private void updateModels()
    {
        if (this.modelFlame == null)
        {
            try
            {
                this.modelFlame = ClientUtil.modelFromOBJ(new ResourceLocation("galacticraftplanets:pod_flame.obj"), ImmutableList.of("Flame_Sphere"));
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        if (this.modelEntryPod == null)
        {
            try
            {
                this.modelEntryPod = OBJLoaderMP.getModelFromOBJ(new ResourceLocation("moreplanets:space_capsule.obj"), ImmutableList.of("Cone", "Tube0", "Tube1", "Tube2", "Tube3"));
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void doRender(EntitySpaceCapsule entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.disableRescaleNormal();
        GlStateManager.pushMatrix();
        float rotationPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        float rotationYaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;

        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F - rotationPitch, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(-rotationYaw, 0.0F, 1.0F, 0.0F);

        this.updateModels();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.5F, 2.5F, 2.5F);
        GlStateManager.translate(0.0F, -1.0F, 0.0F);
        ClientUtil.drawBakedModel(this.modelEntryPod);
        GlStateManager.popMatrix();

        if (entity.posY > 382.0F)
        {
            RenderHelper.disableStandardItemLighting();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 1);
            GlStateManager.cullFace(GlStateManager.CullFace.FRONT);

            int color = ColorUtil.to32BitColor(entity.posY >= 790.0F ? 255 : (int) Math.max(Math.min(255, -(entity.motionY + 0.6F) * 100.0F), 0), 255, 255, 255);

            GlStateManager.pushMatrix();
            float flameScale = (float) (Math.sin(entity.ticksExisted) / 20.0F + 0.5F);
            GlStateManager.scale(1.0F, 1.0F + flameScale, 1.0F);
            GlStateManager.rotate(entity.ticksExisted * 20.0F, 0.0F, 1.0F, 0.0F);
            ClientUtil.drawBakedModelColored(this.modelFlame, color);
            GlStateManager.popMatrix();

            GlStateManager.scale(1.0F, 1.0F + flameScale / 6.0F, 1.0F);
            GlStateManager.rotate(entity.ticksExisted * 5.0F, 0.0F, 1.0F, 0.0F);
            ClientUtil.drawBakedModelColored(this.modelFlame, color);

            GlStateManager.cullFace(GlStateManager.CullFace.BACK);
            GlStateManager.enableCull();
            GlStateManager.blendFunc(770, 771);
            RenderHelper.enableStandardItemLighting();
        }

        if (entity.getGroundPosY() != null && entity.posY - entity.getGroundPosY() > 5.0F && entity.posY <= 382.0F)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(-1.4F, 1.5F, -0.3F);
            GlStateManager.scale(2.5F, 3.0F, 2.5F);
            this.parachuteModel.renderAll();
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySpaceCapsule entity)
    {
        return null;
    }
}
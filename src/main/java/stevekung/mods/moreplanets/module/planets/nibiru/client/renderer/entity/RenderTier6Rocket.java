package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityTier6Rocket;
import stevekung.mods.moreplanets.util.client.renderer.item.ItemRendererTieredRocket;

@SideOnly(Side.CLIENT)
public class RenderTier6Rocket extends Render<EntityTier6Rocket>
{
    private ItemRendererTieredRocket rocketModel;

    public RenderTier6Rocket(RenderManager manager)
    {
        super(manager);
        this.shadowSize = 2F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTier6Rocket entity)
    {
        return null;
    }

    @Override
    public void doRender(EntityTier6Rocket entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        float rotationPitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 180;
        float rollAmplitude = entity.rollAmplitude / 3 - partialTicks;
        GlStateManager.disableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + entity.getRenderOffsetY(), (float)z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-rotationPitch, 0.0F, 0.0F, 1.0F);

        if (rollAmplitude > 0.0F)
        {
            float i = entity.getLaunched() ? (5 - MathHelper.floor(entity.timeUntilLaunch / 85)) / 10F : 0.3F;
            GlStateManager.rotate(MathHelper.sin(rollAmplitude) * rollAmplitude * i * partialTicks, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(rollAmplitude) * rollAmplitude * i * partialTicks, 1.0F, 0.0F, 1.0F);
        }

        this.updateModel();
        RenderHelper.disableStandardItemLighting();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (Minecraft.isAmbientOcclusionEnabled())
        {
            GlStateManager.shadeModel(7425);
        }
        else
        {
            GlStateManager.shadeModel(7424);
        }

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
        ClientUtil.drawBakedModel(this.rocketModel);
        Vector3 teamColor = ClientUtil.updateTeamColor(Minecraft.getMinecraft().player.getName(), true);

        if (teamColor != null)
        {
            GlStateManager.color(teamColor.floatX(), teamColor.floatY(), teamColor.floatZ());
        }
        if (Minecraft.getMinecraft().player.ticksExisted / 10 % 2 < 1)
        {
            GlStateManager.color(1, 0, 0);
        }
        else
        {
            GlStateManager.color(0, 1, 0);
        }
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.color(1, 1, 1);
        GlStateManager.popMatrix();
        RenderHelper.enableStandardItemLighting();
    }

    @Override
    public boolean shouldRender(EntityTier6Rocket rocket, ICamera camera, double camX, double camY, double camZ)
    {
        AxisAlignedBB axisalignedbb = rocket.getEntityBoundingBox().grow(0.5D, 0, 0.5D);
        return rocket.isInRangeToRender3d(camX, camY, camZ) && camera.isBoundingBoxInFrustum(axisalignedbb);
    }

    private void updateModel()
    {
        if (this.rocketModel == null)
        {
            ModelResourceLocation modelResourceLocation = new ModelResourceLocation("moreplanets:tier_6_rocket", "inventory");
            this.rocketModel = (ItemRendererTieredRocket) Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(modelResourceLocation);
        }
    }
}
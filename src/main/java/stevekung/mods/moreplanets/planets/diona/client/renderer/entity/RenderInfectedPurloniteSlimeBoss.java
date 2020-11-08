package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer.LayerInfectedCrystallizeSlimeBossDeath;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.layer.LayerInfectedPurloniteSlimeBossBarrier;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.utils.ClientRendererUtils;
import stevekung.mods.moreplanets.utils.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderInfectedPurloniteSlimeBoss extends RenderLiving<EntityInfectedPurloniteSlimeBoss>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_purlonite_slime_boss.png");

    public RenderInfectedPurloniteSlimeBoss(RenderManager manager)
    {
        super(manager, new ModelInfectedPurloniteSlimeBoss(), 1.0F);
        this.addLayer(new LayerGlowingTexture<>(this, "infected_purlonite_slime_boss_glow", true));
        this.addLayer(new LayerInfectedCrystallizeSlimeBossDeath());
        this.addLayer(new LayerInfectedPurloniteSlimeBossBarrier(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedPurloniteSlimeBoss entity)
    {
        return RenderInfectedPurloniteSlimeBoss.TEXTURE;
    }

    @Override
    public void doRender(EntityInfectedPurloniteSlimeBoss entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F * entity.getSlimeSize();

        if (entity.tentacle != null)
        {
            this.bindTexture(RenderDragon.ENDERCRYSTAL_BEAM_TEXTURES);
            ClientRendererUtils.renderBeam(x, y + 1.0D, z, partialTicks, entity.posX + (entity.prevPosX - entity.posX) * (1.0F - partialTicks), entity.posY + (entity.prevPosY - entity.posY) * (1.0F - partialTicks), entity.posZ + (entity.prevPosZ - entity.posZ) * (1.0F - partialTicks), entity.ticksExisted, entity.tentacle.posX, entity.tentacle.posY, entity.tentacle.posZ);
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityInfectedPurloniteSlimeBoss entity, float partialTicks)
    {
        float f = entity.getSlimeSize();
        float f1 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTicks) / (f * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GlStateManager.scale(f2 * f, 1.0F / f2 * f, f2 * f);
    }
}
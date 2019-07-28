package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedCrystallizedTentacle;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedCrystallizedTentacle;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizedTentacle extends Render<EntityInfectedCrystallizedTentacle>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_tentacle.png");
    private static final ResourceLocation EYES = new ResourceLocation("moreplanets:textures/entity/infected_crystallized_tentacle_eyes.png");
    private final ModelInfectedCrystallizedTentacle model = new ModelInfectedCrystallizedTentacle();

    public RenderInfectedCrystallizedTentacle(RenderManager manager)
    {
        super(manager);
        this.shadowSize = 0.5F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizedTentacle entity)
    {
        return RenderInfectedCrystallizedTentacle.TEXTURE;
    }

    @Override
    public void doRender(EntityInfectedCrystallizedTentacle entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        float f2 = entity.innerRotation + partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 1.5F, (float)z);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
        f3 += f3 * f3;
        this.bindEntityTexture(entity);
        this.model.render(entity, 0.0F, 0.0F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float time = entity.ticksExisted + entity.getEntityId() + partialTicks;
        float sin = (MathHelper.sin(time / 4) + 1F) / 2F + 0.15F;
        GlStateManager.color(sin, sin, sin, sin);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(RenderInfectedCrystallizedTentacle.EYES);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.model.render(entity, 0.0F, 0.0F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
    }
}
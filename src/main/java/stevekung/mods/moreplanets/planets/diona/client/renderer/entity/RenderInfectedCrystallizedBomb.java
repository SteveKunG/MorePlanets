package stevekung.mods.moreplanets.planets.diona.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.client.model.ModelInfectedCrystallizedBomb;
import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedCrystallizedBomb;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizedBomb extends Render<EntityInfectedCrystallizedBomb>
{
    public static final RenderInfectedCrystallizedBomb INSTANCE = new RenderInfectedCrystallizedBomb(Minecraft.getMinecraft().getRenderManager());
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/infected_crystallized_bomb.png");
    private static final ResourceLocation GLOW = new ResourceLocation("moreplanets:textures/model/infected_crystallized_bomb_glow.png");
    private ModelInfectedCrystallizedBomb model = new ModelInfectedCrystallizedBomb();

    public RenderInfectedCrystallizedBomb(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public void doRender(EntityInfectedCrystallizedBomb entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 1.5F, (float)z);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindEntityTexture(entity);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(RenderInfectedCrystallizedBomb.GLOW);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizedBomb entity)
    {
        return RenderInfectedCrystallizedBomb.TEXTURE;
    }

    public void render()
    {
        EntityInfectedCrystallizedBomb entity = new EntityInfectedCrystallizedBomb(Minecraft.getMinecraft().world);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 1.5F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(RenderInfectedCrystallizedBomb.TEXTURE);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(RenderInfectedCrystallizedBomb.GLOW);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
    }
}
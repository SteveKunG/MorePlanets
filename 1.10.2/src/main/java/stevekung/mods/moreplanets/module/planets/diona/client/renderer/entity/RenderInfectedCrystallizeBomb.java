package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.client.model.ModelInfectedCrystallizeBomb;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeBomb;

@SideOnly(Side.CLIENT)
public class RenderInfectedCrystallizeBomb extends Render<EntityInfectedCrystallizeBomb>
{
    public static RenderInfectedCrystallizeBomb INSTANCE;
    private ModelInfectedCrystallizeBomb model = new ModelInfectedCrystallizeBomb();

    public RenderInfectedCrystallizeBomb(RenderManager manager)
    {
        super(manager);
        RenderInfectedCrystallizeBomb.INSTANCE = this;
    }

    @Override
    public void doRender(EntityInfectedCrystallizeBomb entity, double x, double y, double z, float entityYaw, float partialTicks)
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
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/infected_crystallize_bomb_glow.png"));
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCrystallizeBomb entity)
    {
        return new ResourceLocation("moreplanets:textures/model/infected_crystallize_bomb.png");
    }

    public void renderItem()
    {
        EntityInfectedCrystallizeBomb entity = new EntityInfectedCrystallizeBomb(Minecraft.getMinecraft().theWorld);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 1.5F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindEntityTexture(entity);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindTexture(new ResourceLocation("moreplanets:textures/model/infected_crystallize_bomb_glow.png"));
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
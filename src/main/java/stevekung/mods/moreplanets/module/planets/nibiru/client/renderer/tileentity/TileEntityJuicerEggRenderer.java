package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity;

import java.util.Random;

import micdoodle8.mods.galacticraft.planets.venus.entities.EntityJuicer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelJuicerEgg;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityJuicerEgg;
import stevekung.mods.stevekunglib.utils.RenderUtils;

public class TileEntityJuicerEggRenderer extends TileEntitySpecialRenderer<TileEntityJuicerEgg>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/juicer_egg.png");
    private final ModelJuicerEgg model = new ModelJuicerEgg();

    @Override
    public void render(TileEntityJuicerEgg tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        Random rand = new Random(tile.getPos().getX() + tile.getPos().getY() * tile.getPos().getZ());
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 1.475F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        if (tile.renderTicks > 0)
        {
            GlStateManager.rotate(rand.nextInt(180), 0.0F, 1.0F, 0.0F);
            this.renderJuicer(partialTicks);
        }

        this.bindTexture(TileEntityJuicerEggRenderer.TEXTURE);
        this.model.renderAll();
        GlStateManager.popMatrix();
    }

    public static void renderItem(ItemCameraTransforms.TransformType type, boolean render)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5F, 1.35F, 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);

        RenderManager manager = Minecraft.getMinecraft().getRenderManager();
        EntityJuicer juicer = new EntityJuicer(Minecraft.getMinecraft().world);
        manager.renderEntity(juicer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);

        if (type != null && type == ItemCameraTransforms.TransformType.GUI || render)
        {
            GlStateManager.enableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.disableLighting();
            RenderUtils.enableLighting();
        }

        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.popMatrix();
    }

    private void renderJuicer(float partialTicks)
    {
        EntityJuicer juicer = new EntityJuicer(Minecraft.getMinecraft().world);
        Minecraft.getMinecraft().getRenderManager().renderEntity(juicer, 0.0F, 0.1F, 0.0F, 0.0F, partialTicks, false);
    }
}
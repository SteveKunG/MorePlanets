package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity;

import java.util.Random;

import micdoodle8.mods.galacticraft.planets.venus.entities.EntityJuicer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelJuicerEgg;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityJuicerEgg;

public class TileEntityJuicerEggRenderer extends TileEntitySpecialRenderer<TileEntityJuicerEgg>
{
    private static ResourceLocation texture = new ResourceLocation("moreplanets:textures/model/juicer_egg.png");
    private ModelJuicerEgg model = new ModelJuicerEgg();

    @Override
    public void renderTileEntityAt(TileEntityJuicerEgg tile, double x, double y, double z, float partialTicks, int destroyStage)
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

        this.bindTexture(TileEntityJuicerEggRenderer.texture);
        this.model.renderAll();
        GlStateManager.popMatrix();
    }

    private void renderJuicer(float partialTicks)
    {
        EntityJuicer juicer = new EntityJuicer(Minecraft.getMinecraft().theWorld);
        Minecraft.getMinecraft().getRenderManager().doRenderEntity(juicer, 0.0F, 0.1F, 0.0F, 0.0F, partialTicks, false);
    }
}
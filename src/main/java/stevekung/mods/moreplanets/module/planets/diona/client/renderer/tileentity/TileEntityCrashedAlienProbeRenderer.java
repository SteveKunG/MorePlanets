package stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityCrashedAlienProbe;

public class TileEntityCrashedAlienProbeRenderer extends TileEntitySpecialRenderer<TileEntityCrashedAlienProbe>
{
    @Override
    public void renderTileEntityAt(TileEntityCrashedAlienProbe tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        Random rand = new Random(tile.getPos().getX() + tile.getPos().getY() * tile.getPos().getZ());
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        if (tile.renderTicks > 0)
        {
            GlStateManager.rotate(180.0F + rand.nextInt(10), 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-90.0F + rand.nextInt(10), 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(rand.nextInt(45), 0.0F, 0.0F, 1.0F);
            EntityAlienMiner miner = new EntityAlienMiner(Minecraft.getMinecraft().theWorld);
            Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(miner, 0.0F, -0.75F, 0.6F, 0.0F, partialTicks);
        }
        GlStateManager.popMatrix();
    }
}
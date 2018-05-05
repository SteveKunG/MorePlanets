package stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienMiner;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityCrashedAlienProbe;

@SideOnly(Side.CLIENT)
public class TileEntityCrashedAlienProbeRenderer extends TileEntitySpecialRenderer<TileEntityCrashedAlienProbe>
{
    @Override
    public void render(TileEntityCrashedAlienProbe tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
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
            EntityAlienMiner miner = new EntityAlienMiner(Minecraft.getMinecraft().world);
            miner.setHovered(false);
            Minecraft.getMinecraft().getRenderManager().renderEntity(miner, 0.0F, -0.2F, 0.65F, 0.0F, miner.world.getTotalWorldTime() + partialTicks, false);
        }
        GlStateManager.popMatrix();
    }
}
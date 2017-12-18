package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienBeam;

@SideOnly(Side.CLIENT)
public class RenderAlienBeam extends Render<EntityAlienBeam>
{
    public RenderAlienBeam(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public void doRender(EntityAlienBeam entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.disableFog();
        GlStateManager.alphaFunc(516, 0.1F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        this.bindEntityTexture(entity);
        TileEntityBeaconRenderer.renderBeamSegment(x - 0.5D, y - 0.5D, z - 0.5D, partialTicks, 0, entity.world.getTotalWorldTime(), 0, 512, EntitySheep.getDyeRgb(EnumDyeColor.LIGHT_BLUE), 0.25D, 0.275D);
        GlStateManager.enableFog();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAlienBeam entity)
    {
        return new ResourceLocation("textures/entity/beacon_beam.png");
    }
}
package stevekung.mods.moreplanets.moons.koentus.client.renderer.entity;

import micdoodle8.mods.galacticraft.core.client.model.ModelMeteor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.moons.koentus.entity.EntityKoentusMeteor;

@SideOnly(Side.CLIENT)
public class RenderKoentusMeteor extends Render<EntityKoentusMeteor>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/model/koentus_meteor.png");
    private final ModelMeteor modelMeteor = new ModelMeteor();

    public RenderKoentusMeteor(RenderManager manager)
    {
        super(manager);
        this.shadowSize = 1.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKoentusMeteor entity)
    {
        return RenderKoentusMeteor.TEXTURE;
    }

    @Override
    public void doRender(EntityKoentusMeteor entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entityYaw, 1.0F, 0.0F, 0.0F);
        float size = entity.getSize();
        GlStateManager.scale(size / 2.0F, size / 2.0F, size / 2.0F);
        this.bindEntityTexture(entity);
        this.modelMeteor.render(entity, 0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.1F);
        GlStateManager.popMatrix();
    }
}

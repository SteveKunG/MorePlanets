package stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.util.client.renderer.entity.layer.LayerGlowingTexture;

@SideOnly(Side.CLIENT)
public class RenderZeliusZombie extends RenderBiped<EntityZeliusZombie>
{
    private List<LayerRenderer<EntityZeliusZombie>> layerRendererLists;
    private ModelBiped mainZombieModel;

    public RenderZeliusZombie(RenderManager manager)
    {
        super(manager, new ModelZombie(), 0.5F, 1.0F);
        this.mainZombieModel = this.modelBipedMain;
        this.addLayer(new LayerGlowingTexture(this, "zelius_zombie_glow", true));
        this.addLayer(new LayerHeldItem(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            @Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.layerRendererLists = new ArrayList<>(this.layerRenderers);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZeliusZombie entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/zelius_zombie.png");
    }

    @Override
    public void doRender(EntityZeliusZombie entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.mainModel = this.mainZombieModel;
        this.layerRenderers = this.layerRendererLists;
        this.modelBipedMain = (ModelBiped)this.mainModel;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void rotateCorpse(EntityZeliusZombie entity, float rotation, float interpolateRot, float partialTicks)
    {
        if (entity.isConverting())
        {
            interpolateRot += (float)(Math.cos(entity.ticksExisted * 3.25D) * Math.PI * 0.25D);
        }
        super.rotateCorpse(entity, rotation, interpolateRot, partialTicks);
    }
}
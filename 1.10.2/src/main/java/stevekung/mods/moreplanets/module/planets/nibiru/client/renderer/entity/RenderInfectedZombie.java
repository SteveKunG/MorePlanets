package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.client.model.ModelInfectedZombie;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedZombie;

@SideOnly(Side.CLIENT)
public class RenderInfectedZombie extends RenderBiped<EntityInfectedZombie>
{
    private List<LayerRenderer<EntityInfectedZombie>> layerRendererLists;
    private ModelBiped mainZombieModel;

    public RenderInfectedZombie(RenderManager manager)
    {
        super(manager, new ModelInfectedZombie(), 0.5F, 1.0F);
        this.mainZombieModel = this.modelBipedMain;
        this.addLayer(new LayerHeldItem(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            @Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelInfectedZombie(0.5F, true);
                this.modelArmor = new ModelInfectedZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.layerRendererLists = Lists.newArrayList(this.layerRenderers);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedZombie entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_zombie.png");
    }

    @Override
    public void doRender(EntityInfectedZombie entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.mainModel = this.mainZombieModel;
        this.layerRenderers = this.layerRendererLists;
        this.modelBipedMain = (ModelBiped)this.mainModel;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void rotateCorpse(EntityInfectedZombie entity, float rotation, float interpolateRot, float partialTicks)
    {
        if (entity.isConverting())
        {
            interpolateRot += (float)(Math.cos(entity.ticksExisted * 3.25D) * Math.PI * 0.25D);
        }
        super.rotateCorpse(entity, rotation, interpolateRot, partialTicks);
    }
}
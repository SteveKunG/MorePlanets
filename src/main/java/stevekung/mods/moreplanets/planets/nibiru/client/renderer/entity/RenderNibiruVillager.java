package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

@SideOnly(Side.CLIENT)
public class RenderNibiruVillager extends RenderLiving<EntityNibiruVillager>
{
    private static final ResourceLocation FARMER = new ResourceLocation("moreplanets:textures/entity/villager/farmer.png");
    private static final ResourceLocation LIBRARIAN = new ResourceLocation("moreplanets:textures/entity/villager/librarian.png");
    private static final ResourceLocation MEDIC = new ResourceLocation("moreplanets:textures/entity/villager/medic.png");
    private static final ResourceLocation GREEN_VEIN_FARMER = new ResourceLocation("moreplanets:textures/entity/villager/green_vein_farmer.png");
    private static final ResourceLocation GREEN_VEIN_LIBRARIAN = new ResourceLocation("moreplanets:textures/entity/villager/green_vein_librarian.png");
    private static final ResourceLocation GREEN_VEIN_MEDIC = new ResourceLocation("moreplanets:textures/entity/villager/green_vein_medic.png");

    public RenderNibiruVillager(RenderManager manager)
    {
        super(manager, new ModelVillager(0.0F), 0.5F);
        this.addLayer(new LayerCustomHead(this.getMainModel().villagerHead));
    }

    @Override
    public ModelVillager getMainModel()
    {
        return (ModelVillager)super.getMainModel();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNibiruVillager entity)
    {
        switch (entity.getProfession())
        {
        case 0:
            return RenderNibiruVillager.FARMER;
        case 1:
            return RenderNibiruVillager.LIBRARIAN;
        case 2:
            return RenderNibiruVillager.MEDIC;
        case 3:
            return RenderNibiruVillager.GREEN_VEIN_FARMER;
        case 4:
            return RenderNibiruVillager.GREEN_VEIN_LIBRARIAN;
        default:
            return RenderNibiruVillager.GREEN_VEIN_MEDIC;
        }
    }

    @Override
    protected void preRenderCallback(EntityNibiruVillager entity, float partialTicks)
    {
        float f = 0.9375F;

        if (entity.getGrowingAge() < 0)
        {
            f = (float)(f * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }
        GlStateManager.scale(f, f, f);
    }
}
package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityNibiruVillager;

@SideOnly(Side.CLIENT)
public class RenderNibiruVillager extends RenderLiving<EntityNibiruVillager>
{
    private ResourceLocation farmerTexture = new ResourceLocation("moreplanets:textures/entity/villager/farmer.png");
    private ResourceLocation librarianTexture = new ResourceLocation("moreplanets:textures/entity/villager/librarian.png");
    private ResourceLocation medicTexture = new ResourceLocation("moreplanets:textures/entity/villager/medic.png");
    private ResourceLocation greenVeinFarmerTexture = new ResourceLocation("moreplanets:textures/entity/villager/green_vein_farmer.png");
    private ResourceLocation greenVeinLibrarianTexture = new ResourceLocation("moreplanets:textures/entity/villager/green_vein_librarian.png");
    private ResourceLocation greenVeinMedicTexture = new ResourceLocation("moreplanets:textures/entity/villager/green_vein_medic.png");

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
            return this.farmerTexture;
        case 1:
            return this.librarianTexture;
        case 2:
            return this.medicTexture;
        case 3:
            return this.greenVeinFarmerTexture;
        case 4:
            return this.greenVeinLibrarianTexture;
        default:
            return this.greenVeinMedicTexture;
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
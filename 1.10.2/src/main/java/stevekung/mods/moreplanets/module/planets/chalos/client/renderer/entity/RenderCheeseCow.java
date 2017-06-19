package stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseCow;

@SideOnly(Side.CLIENT)
public class RenderCheeseCow extends RenderLiving<EntityCheeseCow>
{
    public RenderCheeseCow(RenderManager manager)
    {
        super(manager, new ModelCow(), 0.6F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCheeseCow entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/cheese_cow.png");
    }
}
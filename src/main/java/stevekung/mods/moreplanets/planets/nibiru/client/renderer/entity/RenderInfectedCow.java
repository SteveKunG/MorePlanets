package stevekung.mods.moreplanets.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedCow;

@SideOnly(Side.CLIENT)
public class RenderInfectedCow extends RenderLiving<EntityInfectedCow>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("moreplanets:textures/entity/infected_cow.png");

    public RenderInfectedCow(RenderManager manager)
    {
        super(manager, new ModelCow(), 0.7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedCow entity)
    {
        return RenderInfectedCow.TEXTURE;
    }
}
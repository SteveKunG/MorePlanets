package stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.entity;

import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedSnowman;

@SideOnly(Side.CLIENT)
public class RenderInfectedSnowman extends RenderLiving<EntityInfectedSnowman>
{
    public RenderInfectedSnowman(RenderManager manager)
    {
        super(manager, new ModelSnowMan(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfectedSnowman entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/infected_snowman.png");
    }

    @Override
    public ModelSnowMan getMainModel()
    {
        return (ModelSnowMan)super.getMainModel();
    }
}
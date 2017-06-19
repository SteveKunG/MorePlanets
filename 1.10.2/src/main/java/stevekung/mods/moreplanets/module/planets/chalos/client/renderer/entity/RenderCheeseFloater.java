package stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.client.model.ModelCheeseFloater;
import stevekung.mods.moreplanets.module.planets.chalos.client.renderer.entity.layer.LayerCheeseFloaterEye;
import stevekung.mods.moreplanets.module.planets.chalos.entity.EntityCheeseFloater;

@SideOnly(Side.CLIENT)
public class RenderCheeseFloater extends RenderLiving<EntityCheeseFloater>
{
    public RenderCheeseFloater(RenderManager manager)
    {
        super(manager, new ModelCheeseFloater(), 0.5F);
        this.addLayer(new LayerCheeseFloaterEye(this));
    }

    @Override
    public void doRender(EntityCheeseFloater entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        if (entity.getHealth() > 0.0F)
        {
            float time = entity.ticksExisted + partialTicks;
            float f1 = MathHelper.sin(time / 12) / 50.0F + 0.5F;
            f1 = f1 * f1 + f1;
            EntityItem item = new EntityItem(entity.worldObj, 0D, 0D, 0D, new ItemStack(ChalosBlocks.CHEESE_SLIME_BLOCK));
            GlStateManager.pushMatrix();
            item.hoverStart = 0.0F;
            GlStateManager.translate((float)x, (float)y + 1.655F - f1, (float)z);
            GlStateManager.rotate(0, 0, 1, 1);
            this.renderManager.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            GlStateManager.popMatrix();
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityCheeseFloater entity, float partialTicks)
    {
        float time = entity.ticksExisted + partialTicks;
        float f1 = MathHelper.sin(time / 12) / 30.0F + 0.5F;
        f1 = f1 * f1 + f1;
        GlStateManager.translate(0.0F, 0.5F - f1, 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCheeseFloater entity)
    {
        return new ResourceLocation("moreplanets:textures/entity/cheese_floater.png");
    }
}
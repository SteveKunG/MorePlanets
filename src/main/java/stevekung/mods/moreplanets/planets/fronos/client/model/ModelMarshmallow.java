package stevekung.mods.moreplanets.planets.fronos.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelMarshmallow extends ModelBase
{
    private ModelRenderer body;

    public ModelMarshmallow()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0F, 0F, 0F, 6, 6, 8, 0F);
        this.body.setRotationPoint(-3F, 18F, -4F);
        this.body.mirror = true;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.body.render(scale);
        }
    }
}
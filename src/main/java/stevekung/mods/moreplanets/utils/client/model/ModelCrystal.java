package stevekung.mods.moreplanets.utils.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCrystal extends ModelBase
{
    private ModelRenderer crystal;

    public ModelCrystal()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.crystal = new ModelRenderer(this, 0, 0);
        this.crystal.addBox(-16.0F, -16.0F, 0.0F, 16, 16, 16);
        this.crystal.setRotationPoint(0.0F, 32.0F, 0.0F);
        this.setRotation(this.crystal, 0.7071F, 0.0F, 0.7071F);
    }

    public void render()
    {
        this.crystal.render(0.0625F);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
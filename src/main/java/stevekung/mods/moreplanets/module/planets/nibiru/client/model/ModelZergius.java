package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZergius extends ModelBase
{
    private ModelRenderer body;
    private ModelRenderer body1;
    private ModelRenderer bodys1;
    private ModelRenderer bodys2;
    private ModelRenderer top;
    private ModelRenderer wing1;
    private ModelRenderer wing2;
    private ModelRenderer rod1;
    private ModelRenderer rod2;
    private ModelRenderer eye1;
    private ModelRenderer eye2;
    private ModelRenderer mouth1;
    private ModelRenderer mouth2;

    public ModelZergius()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body = new ModelRenderer(this, 32, 0);
        this.body.addBox(-3F, -2.5F, 0F, 6, 5, 10);
        this.body.setRotationPoint(0F, 10F, -5F);
        this.body1 = new ModelRenderer(this, 52, 16);
        this.body1.addBox(-2.5F, -2F, 0F, 5, 4, 1);
        this.body1.setRotationPoint(0F, 10F, 5F);
        this.bodys1 = new ModelRenderer(this, 48, 21);
        this.bodys1.addBox(0F, -2F, 0F, 1, 4, 7);
        this.bodys1.setRotationPoint(2.5F, 10F, -3.5F);
        this.bodys2 = new ModelRenderer(this, 48, 21);
        this.bodys2.addBox(0F, -2F, 0F, 1, 4, 7);
        this.bodys2.setRotationPoint(-3.5F, 10F, -3.5F);
        this.top = new ModelRenderer(this, 31, 16);
        this.top.addBox(-1F, 0F, 0F, 2, 1, 6);
        this.top.setRotationPoint(0F, 7F, -3F);
        this.wing1 = new ModelRenderer(this, 21, 24);
        this.wing1.addBox(-2.5F, 0F, 0F, 5, 0, 8);
        this.wing1.setRotationPoint(1F, 7F, 0F);
        this.setRotation(this.wing1, 0F, 1.570796F, 0F);
        this.wing2 = new ModelRenderer(this, 10, 24);
        this.wing2.addBox(-2.5F, 0F, 0F, 5, 0, 8);
        this.wing2.setRotationPoint(-1F, 7F, 0F);
        this.setRotation(this.wing2, 0F, -1.570796F, 0F);
        this.rod1 = new ModelRenderer(this, 27, 0);
        this.rod1.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
        this.rod1.setRotationPoint(1.5F, 8F, -4F);
        this.setRotation(this.rod1, -0.3490659F, 0F, 0F);
        this.rod2 = new ModelRenderer(this, 27, 0);
        this.rod2.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
        this.rod2.setRotationPoint(-1.5F, 8F, -4F);
        this.setRotation(this.rod2, -0.3490659F, 0F, 0F);
        this.eye1 = new ModelRenderer(this, 25, 6);
        this.eye1.addBox(-1F, -1F, 0F, 2, 2, 1);
        this.eye1.setRotationPoint(1.5F, 9F, -6F);
        this.eye2 = new ModelRenderer(this, 25, 6);
        this.eye2.addBox(-1F, -1F, 0F, 2, 2, 1);
        this.eye2.setRotationPoint(-1.5F, 9F, -6F);
        this.mouth1 = new ModelRenderer(this, 19, 14);
        this.mouth1.addBox(-1F, 0F, -4F, 2, 0, 4);
        this.mouth1.setRotationPoint(1F, 11.5F, -4.5F);
        this.mouth2 = new ModelRenderer(this, 19, 10);
        this.mouth2.addBox(-1F, 0F, -4F, 2, 0, 4);
        this.mouth2.setRotationPoint(-1F, 11.5F, -4.5F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.body.render(scale);
        this.body1.render(scale);
        this.bodys1.render(scale);
        this.bodys2.render(scale);
        this.top.render(scale);
        this.wing1.render(scale);
        this.wing2.render(scale);
        this.rod1.render(scale);
        this.rod2.render(scale);
        this.eye1.render(scale);
        this.eye2.render(scale);
        this.mouth1.render(scale);
        this.mouth2.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.wing1.rotateAngleZ = ageInTicks;
        this.wing2.rotateAngleZ = -ageInTicks;
    }
}
package stevekung.mods.moreplanets.module.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelVeinFloater extends ModelBase
{
    public ModelRenderer base;
    public ModelRenderer base1;
    public ModelRenderer base2;
    public ModelRenderer base3;
    public ModelRenderer base4;
    public ModelRenderer tailbase;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;
    public ModelRenderer tail6;
    public ModelRenderer tail7;
    public ModelRenderer tailtop1;
    public ModelRenderer tailtop2;
    public ModelRenderer tailtop3;
    public ModelRenderer tailtop4;
    public ModelRenderer tailhead1;
    public ModelRenderer tailhead2;
    public ModelRenderer tailhead3;
    public ModelRenderer tailhead4;
    public ModelRenderer tail11;
    public ModelRenderer tail22;
    public ModelRenderer tail33;
    public ModelRenderer tail44;
    public ModelRenderer tail55;
    public ModelRenderer tail66;
    public ModelRenderer tail77;
    public ModelRenderer tailtop11;
    public ModelRenderer tailtop22;
    public ModelRenderer tailtop33;
    public ModelRenderer tailtop44;

    public ModelVeinFloater()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.base4 = new ModelRenderer(this, 100, 22);
        this.base4.setRotationPoint(0.0F, -1.0F, -3.0F);
        this.base4.addBox(-4.0F, 0.0F, -6.0F, 8, 1, 6, 0.0F);
        this.setRotateAngle(this.base4, 0.13962634015954636F, 0.0F, 0.0F);
        this.tailhead3 = new ModelRenderer(this, 82, 10);
        this.tailhead3.setRotationPoint(-2.0F, -2.4F, -1.5F);
        this.tailhead3.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tailhead3, 0.4886921905584123F, 2.0488420089161434F, 0.0F);
        this.tailtop2 = new ModelRenderer(this, 74, 7);
        this.tailtop2.setRotationPoint(4.4F, -2.8F, 0.4F);
        this.tailtop2.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.setRotateAngle(this.tailtop2, 0.0F, 0.0F, 0.3141592653589793F);
        this.tailtop44 = new ModelRenderer(this, 60, 70);
        this.tailtop44.setRotationPoint(-4.9F, 0.0F, 0.0F);
        this.tailtop44.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.tail5 = new ModelRenderer(this, 110, 30);
        this.tail5.setRotationPoint(-2.2F, 0.0F, 3.9F);
        this.tail5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.tailtop3 = new ModelRenderer(this, 74, 0);
        this.tailtop3.setRotationPoint(0.0F, -2.7F, -4.2F);
        this.tailtop3.addBox(0.0F, 0.0F, -5.0F, 1, 1, 5, 0.0F);
        this.setRotateAngle(this.tailtop3, 0.3141592653589793F, 0.0F, 0.0F);
        this.tailhead4 = new ModelRenderer(this, 82, 10);
        this.tailhead4.setRotationPoint(-2.3F, -2.9F, 2.5F);
        this.tailhead4.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tailhead4, -0.4886921905584123F, -0.6373942428283291F, 0.0F);
        this.tail66 = new ModelRenderer(this, 100, 30);
        this.tail66.setRotationPoint(0.0F, 7.7F, 0.0F);
        this.tail66.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.tailtop33 = new ModelRenderer(this, 60, 0);
        this.tailtop33.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.tailtop33.addBox(0.0F, 0.0F, -5.0F, 1, 1, 5, 0.0F);
        this.tailtop22 = new ModelRenderer(this, 60, 7);
        this.tailtop22.setRotationPoint(5.0F, 0.0F, 0.0F);
        this.tailtop22.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.tail22 = new ModelRenderer(this, 100, 30);
        this.tail22.setRotationPoint(0.0F, 7.7F, 0.0F);
        this.tail22.addBox(0.0F, 0.0F, 0.1F, 1, 6, 1, 0.0F);
        this.tail33 = new ModelRenderer(this, 100, 30);
        this.tail33.setRotationPoint(0.0F, 7.9F, 0.0F);
        this.tail33.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.tail77 = new ModelRenderer(this, 105, 30);
        this.tail77.setRotationPoint(0.0F, 7.8F, 0.0F);
        this.tail77.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.tail55 = new ModelRenderer(this, 105, 30);
        this.tail55.setRotationPoint(0.0F, 7.8F, 0.0F);
        this.tail55.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.tail7 = new ModelRenderer(this, 110, 30);
        this.tail7.setRotationPoint(-1.2F, 0.0F, -5.1F);
        this.tail7.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.tailtop4 = new ModelRenderer(this, 74, 7);
        this.tailtop4.setRotationPoint(-4.6F, -2.8F, 0.0F);
        this.tailtop4.addBox(-5.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.setRotateAngle(this.tailtop4, 0.0F, 0.0F, -0.3141592653589793F);
        this.tailhead2 = new ModelRenderer(this, 82, 10);
        this.tailhead2.setRotationPoint(1.5F, -2.4F, -2.0F);
        this.tailhead2.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tailhead2, 0.3490658503988659F, 0.4886921905584123F, 0.0F);
        this.tail1 = new ModelRenderer(this, 115, 30);
        this.tail1.setRotationPoint(1.9F, 0.0F, 3.3F);
        this.tail1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.tailbase = new ModelRenderer(this, 120, 30);
        this.tailbase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailbase.addBox(-1.0F, 0.0F, -1.0F, 2, 16, 2, 0.0F);
        this.tail3 = new ModelRenderer(this, 110, 30);
        this.tail3.setRotationPoint(-3.6F, 0.0F, 0.2F);
        this.tail3.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.tail4 = new ModelRenderer(this, 115, 30);
        this.tail4.setRotationPoint(-2.1F, 0.0F, -2.9F);
        this.tail4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.base = new ModelRenderer(this, 88, 0);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-5.0F, -3.0F, -5.0F, 10, 3, 10, 0.0F);
        this.tailhead1 = new ModelRenderer(this, 82, 10);
        this.tailhead1.setRotationPoint(2.0F, -2.4F, 1.9F);
        this.tailhead1.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tailhead1, -0.3490658503988659F, 0.4886921905584123F, 0.0F);
        this.tail11 = new ModelRenderer(this, 105, 30);
        this.tail11.setRotationPoint(0.0F, 6.7F, 0.0F);
        this.tail11.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.tail44 = new ModelRenderer(this, 100, 30);
        this.tail44.setRotationPoint(0.0F, 6.8F, 0.0F);
        this.tail44.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.base1 = new ModelRenderer(this, 100, 13);
        this.base1.setRotationPoint(3.0F, -1.0F, 0.0F);
        this.base1.addBox(0.0F, 0.0F, -4.0F, 6, 1, 8, 0.0F);
        this.setRotateAngle(this.base1, 0.0F, 0.0F, 0.13962634015954636F);
        this.base2 = new ModelRenderer(this, 100, 13);
        this.base2.setRotationPoint(-4.0F, -1.0F, 0.0F);
        this.base2.addBox(-5.0F, 0.0F, -4.0F, 6, 1, 8, 0.0F);
        this.setRotateAngle(this.base2, 0.0F, 0.0F, -0.13962634015954636F);
        this.tail2 = new ModelRenderer(this, 110, 30);
        this.tail2.setRotationPoint(1.8F, 0.0F, -3.4F);
        this.tail2.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.tailtop11 = new ModelRenderer(this, 60, 0);
        this.tailtop11.setRotationPoint(0.0F, 0.0F, 4.9F);
        this.tailtop11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.base3 = new ModelRenderer(this, 100, 22);
        this.base3.setRotationPoint(0.0F, -1.0F, 3.0F);
        this.base3.addBox(-4.0F, 0.0F, 0.0F, 8, 1, 6, 0.0F);
        this.setRotateAngle(this.base3, -0.13962634015954636F, 0.0F, 0.0F);
        this.tailtop1 = new ModelRenderer(this, 74, 0);
        this.tailtop1.setRotationPoint(0.0F, -2.7F, 3.7F);
        this.tailtop1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.setRotateAngle(this.tailtop1, -0.3141592653589793F, 0.0F, 0.0F);
        this.tail6 = new ModelRenderer(this, 110, 30);
        this.tail6.setRotationPoint(2.8F, 0.0F, 0.8F);
        this.tail6.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.tailtop4.addChild(this.tailtop44);
        this.tail6.addChild(this.tail66);
        this.tailtop3.addChild(this.tailtop33);
        this.tailtop2.addChild(this.tailtop22);
        this.tail2.addChild(this.tail22);
        this.tail3.addChild(this.tail33);
        this.tail7.addChild(this.tail77);
        this.tail5.addChild(this.tail55);
        this.tail1.addChild(this.tail11);
        this.tail4.addChild(this.tail44);
        this.tailtop1.addChild(this.tailtop11);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.base4.render(scale);
        this.tailhead3.render(scale);
        this.tailtop2.render(scale);
        this.tail5.render(scale);
        this.tailtop3.render(scale);
        this.tailhead4.render(scale);
        this.tail7.render(scale);
        this.tailtop4.render(scale);
        this.tailhead2.render(scale);
        this.tail1.render(scale);
        this.tailbase.render(scale);
        this.tail3.render(scale);
        this.tail4.render(scale);
        this.base.render(scale);
        this.tailhead1.render(scale);
        this.base1.render(scale);
        this.base2.render(scale);
        this.tail2.render(scale);
        this.base3.render(scale);
        this.tailtop1.render(scale);
        this.tail6.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        float rotation1 = MathHelper.cos(ageInTicks * 0.1F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        float rotation2 = MathHelper.cos(ageInTicks * 0.15F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        float rotation3 = MathHelper.cos(ageInTicks * 0.125F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        float rotation4 = MathHelper.cos(ageInTicks * 0.13F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        float rotation5 = MathHelper.cos(ageInTicks * 0.14F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        float rotation6 = MathHelper.cos(ageInTicks * 0.12F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));
        float rotation7 = MathHelper.cos(ageInTicks * 0.135F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(2 - 2));

        this.tail1.rotateAngleX = rotation1;
        this.tail2.rotateAngleX = rotation2;
        this.tail3.rotateAngleX = rotation3;
        this.tail4.rotateAngleX = rotation4;
        this.tail5.rotateAngleX = rotation5;
        this.tail6.rotateAngleX = rotation6;
        this.tail7.rotateAngleX = rotation7;

        this.tail11.rotateAngleX = rotation7;
        this.tail22.rotateAngleX = rotation6;
        this.tail33.rotateAngleX = rotation5;
        this.tail44.rotateAngleX = rotation4;
        this.tail55.rotateAngleX = rotation3;
        this.tail66.rotateAngleX = rotation2;
        this.tail77.rotateAngleX = rotation1;

        this.tailtop1.rotateAngleX = rotation1;
        this.tailtop2.rotateAngleX = rotation2;
        this.tailtop3.rotateAngleX = rotation3;
        this.tailtop4.rotateAngleX = rotation4;

        this.tailtop1.rotateAngleZ = rotation1;
        this.tailtop2.rotateAngleZ = rotation2;
        this.tailtop3.rotateAngleZ = rotation3;
        this.tailtop4.rotateAngleZ = rotation4;

        this.tailtop11.rotateAngleX = rotation1;
        this.tailtop22.rotateAngleX = rotation2;
        this.tailtop33.rotateAngleX = rotation3;
        this.tailtop44.rotateAngleX = rotation4;

        this.tailtop11.rotateAngleZ = rotation1;
        this.tailtop22.rotateAngleZ = rotation2;
        this.tailtop33.rotateAngleZ = rotation3;
        this.tailtop44.rotateAngleZ = rotation4;

        this.base1.rotateAngleZ = rotation1;
        this.base2.rotateAngleZ = rotation2;
        this.base3.rotateAngleX = rotation1;
        this.base4.rotateAngleX = rotation2;

        this.tailhead1.rotateAngleY = rotation1;
        this.tailhead2.rotateAngleY = rotation2;
        this.tailhead3.rotateAngleY = rotation1;
        this.tailhead4.rotateAngleY = rotation2;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
package stevekung.mods.moreplanets.planets.diona.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlienMiner extends ModelBase
{
    private ModelRenderer head1;
    private ModelRenderer head2;
    private ModelRenderer head3;
    private ModelRenderer head4;
    private ModelRenderer head5;
    private ModelRenderer head6;
    private ModelRenderer top1;
    private ModelRenderer top2;
    private ModelRenderer top3;
    private ModelRenderer armp1;
    private ModelRenderer armp2;
    private ModelRenderer armp3;
    private ModelRenderer armp4;
    private ModelRenderer armp5;
    private ModelRenderer armp6;
    private ModelRenderer armpten1;
    private ModelRenderer armpten2;
    private ModelRenderer armpten3;
    private ModelRenderer armpten4;
    private ModelRenderer armpten5;
    private ModelRenderer armpten6;
    private ModelRenderer bottom1;
    private ModelRenderer bottom2;
    private ModelRenderer bottom3;
    private ModelRenderer tentacle1;
    private ModelRenderer tentacle2;
    private ModelRenderer tentacle3;

    public ModelAlienMiner()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.armpten1 = new ModelRenderer(this, 0, 9);
        this.armpten1.setRotationPoint(2.799999952316284F, 8.5F, 1.5F);
        this.armpten1.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armpten1, -0.5424940975264884F, -1.3050149876405968F, 0.5584280667383914F);
        this.bottom1 = new ModelRenderer(this, 12, 0);
        this.bottom1.setRotationPoint(0.0F, 14.100000381469727F, 1.0F);
        this.bottom1.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(this.bottom1, -0.06981316953897475F, -0.0F, 0.0F);
        this.top1 = new ModelRenderer(this, 0, 26);
        this.top1.setRotationPoint(-0.5F, 3.5F, 1.0F);
        this.top1.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(this.top1, -1.7453304994104737F, 1.5358897420253812F, -1.5707975785253419F);
        this.head1 = new ModelRenderer(this, 32, 0);
        this.head1.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.head1.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotateAngle(this.head1, -0.2094395160675048F, -0.0F, 0.0F);
        this.armpten2 = new ModelRenderer(this, 0, 9);
        this.armpten2.setRotationPoint(2.799999952316284F, 10.0F, 1.5F);
        this.armpten2.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armpten2, -0.5424940975264884F, -1.3050149876405968F, 0.5584280667383914F);
        this.armp5 = new ModelRenderer(this, 0, 12);
        this.armp5.setRotationPoint(-1.5F, 10.0F, 0.699999988079071F);
        this.armp5.addBox(-1.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armp5, 0.0F, 0.43633231520652765F, 0.0F);
        this.tentacle1 = new ModelRenderer(this, 9, 11);
        this.tentacle1.setRotationPoint(1.2999999523162842F, 16.0F, 1.5F);
        this.tentacle1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tentacle1, -0.2865991743282312F, -0.4121251114290374F, 0.1906692256492553F);
        this.tentacle2 = new ModelRenderer(this, 9, 11);
        this.tentacle2.setRotationPoint(-1.2999999523162842F, 16.0F, 1.5F);
        this.tentacle2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tentacle2, -0.2865991743282312F, 0.4121251114290374F, -0.1906692256492553F);
        this.head4 = new ModelRenderer(this, 32, 0);
        this.head4.setRotationPoint(0.0F, 8.0F, 2.0999999046325684F);
        this.head4.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotateAngle(this.head4, 0.2094395160675048F, -0.0F, 0.0F);
        this.bottom2 = new ModelRenderer(this, 12, 5);
        this.bottom2.setRotationPoint(1.399999976158142F, 14.300000190734863F, 1.399999976158142F);
        this.bottom2.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(this.bottom2, -0.11010154654075492F, -0.477108480261695F, 0.23624002651768705F);
        this.armp3 = new ModelRenderer(this, 0, 12);
        this.armp3.setRotationPoint(1.5F, 11.5F, 0.699999988079071F);
        this.armp3.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armp3, 0.0F, -0.43633231520652765F, 0.0F);
        this.bottom3 = new ModelRenderer(this, 12, 5);
        this.bottom3.setRotationPoint(-1.399999976158142F, 14.300000190734863F, 1.399999976158142F);
        this.bottom3.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(this.bottom3, -0.11010154654075492F, 0.477108480261695F, -0.23624002651768705F);
        this.tentacle3 = new ModelRenderer(this, 9, 15);
        this.tentacle3.setRotationPoint(0.0F, 15.5F, 0.5F);
        this.tentacle3.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.tentacle3, 0.2094395160675048F, -0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 32, 6);
        this.head2.setRotationPoint(-1.0F, 8.0F, 0.5F);
        this.head2.addBox(-1.0F, 0.0F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotateAngle(this.head2, -0.17453292012214658F, 0.5585053563117981F, 0.0F);
        this.armp1 = new ModelRenderer(this, 0, 12);
        this.armp1.setRotationPoint(1.5F, 8.5F, 0.699999988079071F);
        this.armp1.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armp1, 0.0F, -0.43633231520652765F, 0.0F);
        this.top3 = new ModelRenderer(this, 0, 18);
        this.top3.setRotationPoint(0.0F, 4.0F, 1.0F);
        this.top3.addBox(-0.30000001192092896F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
        this.setRotateAngle(this.top3, -1.5707966408741982F, 1.43116998771694F, -1.5707966378176001F);
        this.armpten3 = new ModelRenderer(this, 0, 9);
        this.armpten3.setRotationPoint(2.799999952316284F, 11.5F, 1.5F);
        this.armpten3.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armpten3, -0.5424940975264884F, -1.3050149876405968F, 0.5584280667383914F);
        this.head6 = new ModelRenderer(this, 32, 13);
        this.head6.setRotationPoint(1.0F, 8.0F, 1.5F);
        this.head6.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotateAngle(this.head6, 0.17453292012214658F, 0.942477822303772F, 0.0F);
        this.head3 = new ModelRenderer(this, 32, 6);
        this.head3.setRotationPoint(1.0F, 8.0F, 0.5F);
        this.head3.addBox(-1.0F, 0.0F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotateAngle(this.head3, -0.17453292012214658F, -0.5585053563117981F, 0.0F);
        this.armp4 = new ModelRenderer(this, 0, 12);
        this.armp4.setRotationPoint(-1.5F, 8.5F, 0.699999988079071F);
        this.armp4.addBox(-1.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armp4, 0.0F, 0.43633231520652765F, 0.0F);
        this.armp2 = new ModelRenderer(this, 0, 12);
        this.armp2.setRotationPoint(1.5F, 10.0F, 0.699999988079071F);
        this.armp2.addBox(-0.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armp2, 0.0F, -0.43633231520652765F, 0.0F);
        this.armpten5 = new ModelRenderer(this, 0, 6);
        this.armpten5.setRotationPoint(-2.799999952316284F, 10.0F, 1.5F);
        this.armpten5.addBox(-1.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armpten5, -0.5424940975264884F, 1.3050149876405968F, -0.5584280667383914F);
        this.armp6 = new ModelRenderer(this, 0, 12);
        this.armp6.setRotationPoint(-1.5F, 11.5F, 0.699999988079071F);
        this.armp6.addBox(-1.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armp6, 0.0F, 0.43633231520652765F, 0.0F);
        this.armpten4 = new ModelRenderer(this, 0, 6);
        this.armpten4.setRotationPoint(-2.799999952316284F, 8.5F, 1.5F);
        this.armpten4.addBox(-1.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armpten4, -0.5424940975264884F, 1.3050149876405968F, -0.5584280667383914F);
        this.head5 = new ModelRenderer(this, 32, 13);
        this.head5.setRotationPoint(-1.0F, 8.0F, 1.5F);
        this.head5.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        this.setRotateAngle(this.head5, 0.17453292012214658F, -0.942477822303772F, 0.0F);
        this.top2 = new ModelRenderer(this, 0, 26);
        this.top2.setRotationPoint(0.5F, 3.5F, 1.0F);
        this.top2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(this.top2, -1.3962646591661803F, 1.5358897420253812F, -1.5707975785253419F);
        this.armpten6 = new ModelRenderer(this, 0, 6);
        this.armpten6.setRotationPoint(-2.799999952316284F, 11.5F, 1.5F);
        this.armpten6.addBox(-1.5F, 0.0F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(this.armpten6, -0.5424940975264884F, 1.3050149876405968F, -0.5584280667383914F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.armpten1.render(scale);
        this.bottom1.render(scale);
        this.top1.render(scale);
        this.head1.render(scale);
        this.armpten2.render(scale);
        this.armp5.render(scale);
        this.tentacle1.render(scale);
        this.tentacle2.render(scale);
        this.head4.render(scale);
        this.bottom2.render(scale);
        this.armp3.render(scale);
        this.bottom3.render(scale);
        this.tentacle3.render(scale);
        this.head2.render(scale);
        this.armp1.render(scale);
        this.top3.render(scale);
        this.armpten3.render(scale);
        this.head6.render(scale);
        this.head3.render(scale);
        this.armp4.render(scale);
        this.armp2.render(scale);
        this.armpten5.render(scale);
        this.armp6.render(scale);
        this.armpten4.render(scale);
        this.head5.render(scale);
        this.top2.render(scale);
        this.armpten6.render(scale);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
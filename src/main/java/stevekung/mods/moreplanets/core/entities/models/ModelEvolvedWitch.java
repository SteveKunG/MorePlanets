package stevekung.mods.moreplanets.core.entities.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelEvolvedWitch extends ModelWitch
{
    ModelRenderer tank1;
    ModelRenderer tank2;
    ModelRenderer oxygenMask;
    ModelRenderer tube1;
    ModelRenderer tube2;
    ModelRenderer tube3;
    ModelRenderer tube4;
    ModelRenderer tube5;
    ModelRenderer tube6;
    ModelRenderer tube7;
    ModelRenderer tube8;
    ModelRenderer tube9;
    ModelRenderer tube10;
    ModelRenderer tube11;
    ModelRenderer tube12;
    ModelRenderer tube13;
    ModelRenderer tube14;
    ModelRenderer tube15;
    ModelRenderer tube16;
    ModelRenderer tube17;
    ModelRenderer tube18;

    public ModelEvolvedWitch()
    {
        super(0.0F);
        this.textureWidth = 64;
        this.textureHeight = 128;

        this.tank1 = new ModelRenderer(this, 52, 66);
        this.tank1.addBox(0F, 0F, 0F, 3, 7, 3);
        this.tank1.setRotationPoint(0F, 4F, 3F);

        this.tank2 = new ModelRenderer(this, 52, 66);
        this.tank2.addBox(0F, 0F, 0F, 3, 7, 3);
        this.tank2.setRotationPoint(-3F, 4F, 3F);

        this.oxygenMask = new ModelRenderer(this, 24, 90);
        this.oxygenMask.addBox(-5F, -9F, -5F, 10, 10, 10);
        this.oxygenMask.setRotationPoint(0F, 1F, 0F);

        this.tube1 = new ModelRenderer(this, 60, 76);
        this.tube1.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube1.setRotationPoint(1F, 4.5F, 6F);

        this.tube2 = new ModelRenderer(this, 60, 76);
        this.tube2.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube2.setRotationPoint(1F, 3.5F, 7F);

        this.tube3 = new ModelRenderer(this, 60, 76);
        this.tube3.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube3.setRotationPoint(1F, 2.5F, 7.5F);

        this.tube4 = new ModelRenderer(this, 60, 76);
        this.tube4.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube4.setRotationPoint(1F, 1.5F, 7.5F);

        this.tube5 = new ModelRenderer(this, 60, 76);
        this.tube5.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube5.setRotationPoint(1F, 0.5F, 7.5F);

        this.tube6 = new ModelRenderer(this, 60, 76);
        this.tube6.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube6.setRotationPoint(1F, -0.5F, 7F);

        this.tube7 = new ModelRenderer(this, 60, 76);
        this.tube7.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube7.setRotationPoint(1F, -1.5F, 6F);

        this.tube8 = new ModelRenderer(this, 60, 76);
        this.tube8.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube8.setRotationPoint(1F, -2.5F, 5F);

        this.tube9 = new ModelRenderer(this, 60, 76);
        this.tube9.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube9.setRotationPoint(1F, -3F, 4F);

        this.tube10 = new ModelRenderer(this, 60, 76);
        this.tube10.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube10.setRotationPoint(-2F, 4.5F, 6F);

        this.tube11 = new ModelRenderer(this, 60, 76);
        this.tube11.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube11.setRotationPoint(-2F, 3.5F, 7F);

        this.tube12 = new ModelRenderer(this, 60, 76);
        this.tube12.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube12.setRotationPoint(-2F, 2.5F, 7.5F);

        this.tube13 = new ModelRenderer(this, 60, 76);
        this.tube13.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube13.setRotationPoint(-2F, 1.5F, 7.5F);

        this.tube14 = new ModelRenderer(this, 60, 76);
        this.tube14.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube14.setRotationPoint(-2F, 0.5F, 7.5F);

        this.tube15 = new ModelRenderer(this, 60, 76);
        this.tube15.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube15.setRotationPoint(-2F, -0.5F, 7F);

        this.tube16 = new ModelRenderer(this, 60, 76);
        this.tube16.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube16.setRotationPoint(-2F, -1.5F, 6F);

        this.tube17 = new ModelRenderer(this, 60, 76);
        this.tube17.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube17.setRotationPoint(-2F, -2.5F, 5F);

        this.tube18 = new ModelRenderer(this, 60, 76);
        this.tube18.addBox(0F, 0F, 0F, 1, 1, 1);
        this.tube18.setRotationPoint(-2F, -3F, 4F);
    }

    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(entity, par2, par3, par4, par5, par6, par7);
        this.tank1.render(par7);
        this.tank2.render(par7);
        this.oxygenMask.render(par7);
        this.tube1.render(par7);
        this.tube2.render(par7);
        this.tube3.render(par7);
        this.tube4.render(par7);
        this.tube5.render(par7);
        this.tube6.render(par7);
        this.tube7.render(par7);
        this.tube8.render(par7);
        this.tube9.render(par7);
        this.tube10.render(par7);
        this.tube11.render(par7);
        this.tube12.render(par7);
        this.tube13.render(par7);
        this.tube14.render(par7);
        this.tube15.render(par7);
        this.tube16.render(par7);
        this.tube17.render(par7);
        this.tube18.render(par7);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.oxygenMask.rotateAngleY = this.villagerHead.rotateAngleY;
        this.oxygenMask.rotateAngleX = this.villagerHead.rotateAngleX;
    }
}
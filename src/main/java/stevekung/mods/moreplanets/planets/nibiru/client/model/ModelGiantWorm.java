package stevekung.mods.moreplanets.planets.nibiru.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGiantWorm extends ModelBase
{
    private final ModelRenderer[] body = new ModelRenderer[9];
    private final ModelRenderer head;
    private final ModelRenderer[][] pincers = new ModelRenderer[4][4];

    public ModelGiantWorm()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;

        this.body[6] = new ModelRenderer(this, 152, 28);
        this.body[6].addBox(-5F, -5F, -122F, 10, 10, 16);
        this.body[6].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[6], 3.141593F, 0F, -0.6981317F);
        this.body[5] = new ModelRenderer(this, 152, 0);
        this.body[5].addBox(-6F, -6F, -106F, 12, 12, 16);
        this.body[5].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[5], 3.141593F, 0F, -0.3490659F);
        this.body[4] = new ModelRenderer(this, 88, 96);
        this.body[4].addBox(-8F, -8F, -90F, 16, 16, 16);
        this.body[4].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[4], 3.141593F, 0F, 0F);
        this.body[2] = new ModelRenderer(this, 88, 32);
        this.body[2].addBox(-8F, -8F, -58F, 16, 16, 16);
        this.body[2].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[2], 3.141593F, 0F, 0.6981317F);
        this.body[1] = new ModelRenderer(this, 88, 0);
        this.body[1].addBox(-8F, -8F, -42F, 16, 16, 16);
        this.body[1].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[1], 3.141593F, 0F, 1.047198F);
        this.body[0] = new ModelRenderer(this, 0, 0);
        this.body[0].addBox(-8F, -8F, -26F, 16, 16, 16);
        this.body[0].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[0], 3.141593F, 0F, 1.396263F);
        this.head = new ModelRenderer(this, 0, 32);
        this.head.addBox(-8F, -8F, -10F, 16, 16, 16);
        this.head.setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.head, 3.141593F, 0F, 0F);
        this.pincers[0][0] = new ModelRenderer(this, 64, 24);
        this.pincers[0][0].addBox(-0.5F, -15F, 9F, 1, 1, 3);
        this.pincers[0][0].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[0][0], 3.141593F, 0F, 0F);
        this.pincers[3][3] = new ModelRenderer(this, 64, 0);
        this.pincers[3][3].addBox(-1F, -11F, -4F, 2, 3, 10);
        this.pincers[3][3].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[3][3], 3.141593F, 0F, 1.570796F);
        this.pincers[2][3] = new ModelRenderer(this, 64, 13);
        this.pincers[2][3].addBox(-0.5F, -14F, 4F, 1, 3, 3);
        this.pincers[2][3].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[2][3], 3.141593F, 0F, 1.570796F);
        this.pincers[1][3] = new ModelRenderer(this, 64, 19);
        this.pincers[1][3].addBox(-0.5F, -15F, 6F, 1, 2, 3);
        this.pincers[1][3].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[1][3], 3.141593F, 0F, 1.570796F);
        this.pincers[3][1] = new ModelRenderer(this, 64, 0);
        this.pincers[3][1].addBox(-1F, -11F, -4F, 2, 3, 10);
        this.pincers[3][1].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[3][1], 3.141593F, 0F, 4.712389F);
        this.pincers[2][1] = new ModelRenderer(this, 64, 13);
        this.pincers[2][1].addBox(-0.5F, -14F, 4F, 1, 3, 3);
        this.pincers[2][1].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[2][1], 3.141593F, 0F, 4.712389F);
        this.pincers[1][1] = new ModelRenderer(this, 64, 19);
        this.pincers[1][1].addBox(-0.5F, -15F, 6F, 1, 2, 3);
        this.pincers[1][1].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[1][1], 3.141593F, 0F, 4.712389F);
        this.pincers[0][1] = new ModelRenderer(this, 64, 24);
        this.pincers[0][1].addBox(-0.5F, -15F, 9F, 1, 1, 3);
        this.pincers[0][1].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[0][1], 3.141593F, 0F, 4.712389F);
        this.pincers[3][2] = new ModelRenderer(this, 64, 0);
        this.pincers[3][2].addBox(-1F, -11F, -4F, 2, 3, 10);
        this.pincers[3][2].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[3][2], 3.141593F, 0F, 3.141593F);
        this.pincers[2][2] = new ModelRenderer(this, 64, 13);
        this.pincers[2][2].addBox(-0.5F, -14F, 4F, 1, 3, 3);
        this.pincers[2][2].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[2][2], 3.141593F, 0F, 3.141593F);
        this.pincers[1][2] = new ModelRenderer(this, 64, 19);
        this.pincers[1][2].addBox(-0.5F, -15F, 6F, 1, 2, 3);
        this.pincers[1][2].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[1][2], 3.141593F, 0F, 3.141593F);
        this.pincers[0][2] = new ModelRenderer(this, 64, 24);
        this.pincers[0][2].addBox(-0.5F, -15F, 9F, 1, 1, 3);
        this.pincers[0][2].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[0][2], 3.141593F, 0F, 3.141593F);
        this.pincers[3][0] = new ModelRenderer(this, 64, 0);
        this.pincers[3][0].addBox(-1F, -11F, -4F, 2, 3, 10);
        this.pincers[3][0].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[3][0], 3.141593F, 0F, 0F);
        this.pincers[2][0] = new ModelRenderer(this, 64, 13);
        this.pincers[2][0].addBox(-0.5F, -14F, 4F, 1, 3, 3);
        this.pincers[2][0].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[2][0], 3.141593F, 0F, 0F);
        this.pincers[1][0] = new ModelRenderer(this, 64, 19);
        this.pincers[1][0].addBox(-0.5F, -15F, 6F, 1, 2, 3);
        this.pincers[1][0].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[1][0], 3.141593F, 0F, 0F);
        this.pincers[0][3] = new ModelRenderer(this, 64, 24);
        this.pincers[0][3].addBox(-0.5F, -15F, 9F, 1, 1, 3);
        this.pincers[0][3].setRotationPoint(0F, 16F, -65F);
        this.setRotation(this.pincers[0][3], 3.141593F, 0F, 1.570796F);
        this.body[3] = new ModelRenderer(this, 88, 64);
        this.body[3].addBox(-8F, -8F, -74F, 16, 16, 16);
        this.body[3].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[3], 3.141593F, 0F, 0.3490659F);
        this.body[7] = new ModelRenderer(this, 152, 54);
        this.body[7].addBox(-4F, -4F, -154F, 6, 6, 16);
        this.body[7].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[7], 3.141593F, 0F, -1.396263F);
        this.body[8] = new ModelRenderer(this, 152, 78);
        this.body[8].addBox(-4F, -4F, -138F, 8, 8, 16);
        this.body[8].setRotationPoint(0F, 16F, -66F);
        this.setRotation(this.body[8], 3.141593F, 0F, -1.047198F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.updateBodyRotation(entity, ageInTicks);

        for (ModelRenderer element : this.body)
        {
            element.render(scale);
        }
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                this.pincers[i][j].render(scale);
            }
        }
        this.head.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    private void updateBodyRotation(Entity entity, float ageInTicks)
    {
        float rotationSwing = 0.03F * (entity.getEntityId() % 10) + 0.05F;

        for (int i = 0; i < this.body.length; i++)
        {
            if (i != this.body.length - 1)
            {
                this.body[i].rotationPointX = MathHelper.sin(ageInTicks * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 1F * Math.abs(i - 2);
            }
            else
            {
                this.body[i].rotationPointX = MathHelper.sin(ageInTicks * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.7F * Math.abs(i - 2);
            }
            this.body[i].rotateAngleZ = entity.ticksExisted * rotationSwing - (float) (Math.PI / 15) * i;
        }

        this.head.rotateAngleZ = entity.ticksExisted * rotationSwing + (float) (Math.PI / 8) * 1;

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                this.pincers[i][j].rotateAngleZ = entity.ticksExisted * rotationSwing + (float) (Math.PI / 2) * j;
            }
        }
        for (int i = 0; i < 4; i++)
        {
            this.pincers[i][0].rotateAngleX = MathHelper.sin(entity.ticksExisted * rotationSwing) * 4.5F * (float) Math.PI / 180.0F - (float) Math.PI;
            this.pincers[i][1].rotateAngleX = MathHelper.sin(entity.ticksExisted * rotationSwing) * 4.5F * (float) Math.PI / 180.0F - (float) Math.PI;
            this.pincers[i][2].rotateAngleX = MathHelper.sin(entity.ticksExisted * rotationSwing) * 4.5F * (float) Math.PI / 180.0F - (float) Math.PI;
            this.pincers[i][3].rotateAngleX = MathHelper.sin(entity.ticksExisted * rotationSwing) * 4.5F * (float) Math.PI / 180.0F - (float) Math.PI;
        }
    }
}
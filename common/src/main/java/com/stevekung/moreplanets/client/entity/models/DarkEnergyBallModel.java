package com.stevekung.moreplanets.client.entity.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class DarkEnergyBallModel extends Model
{
    private final ModelPart ball = new ModelPart(32, 32, 0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F);

    public DarkEnergyBallModel()
    {
        super(RenderType::entitySolid);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        this.ball.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
package com.stevekung.moreplanets.client.entity.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;

public class DarkEnergyBallModel extends Model
{
    private final ModelPart ball;

    public DarkEnergyBallModel(ModelPart part)
    {
        super(RenderType::entitySolid);
        this.ball = part.getChild("ball");
    }

    public static LayerDefinition createBodyLayer()
    {
        var meshDefinition = new MeshDefinition();
        var partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("ball", CubeListBuilder.create().texOffs(32, 32).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        this.ball.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
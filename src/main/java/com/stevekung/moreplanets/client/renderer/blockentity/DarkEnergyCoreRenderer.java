package com.stevekung.moreplanets.client.renderer.blockentity;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.stevekung.moreplanets.client.entity.models.DarkEnergyOrbModel;
import com.stevekung.moreplanets.core.MorePlanets;
import com.stevekung.moreplanets.world.level.block.DarkEnergyCoreBlock;
import com.stevekung.moreplanets.world.level.block.entity.DarkEnergyCoreBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;

public class DarkEnergyCoreRenderer implements BlockEntityRenderer<DarkEnergyCoreBlockEntity>
{
    public static final Material ORB_MATERIAL = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation(MorePlanets.MOD_ID, "entity/dark_energy_orb"));
    public static final Material EGG_MATERIAL = new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation(MorePlanets.MOD_ID, "entity/dark_energy_egg"));
    private final DarkEnergyOrbModel orbModel;

    public DarkEnergyCoreRenderer(BlockEntityRendererProvider.Context context)
    {
        this.orbModel = new DarkEnergyOrbModel(context.bakeLayer(DarkEnergyOrbModel.LAYER));
    }

    @Override
    public void render(DarkEnergyCoreBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay)
    {
        var isFull = blockEntity.getBlockState().getValue(DarkEnergyCoreBlock.STATE) == DarkEnergyCoreBlock.State.FULL;
        var ticks = blockEntity.ticks + partialTicks;
        var material = isFull ? ORB_MATERIAL : EGG_MATERIAL;
        poseStack.pushPose();
        poseStack.translate(0.5D, 0.75D, 0.5D);
        poseStack.translate(0.0D, 0.3F + Mth.sin(ticks * 0.1F) * 0.05F, 0.0D);
        var vertexConsumer = material.buffer(bufferSource, RenderType::entitySolid);
        this.orbModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        if (isFull)
        {
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            this.renderDarkBeam(poseStack, bufferSource, ticks / 200.0F);
        }
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(DarkEnergyCoreBlockEntity blockEntity)
    {
        return true;
    }

    private void renderDarkBeam(PoseStack poseStack, MultiBufferSource multiBufferSource, float ticks)
    {
        var alpha = 0.35F;
        var random = new Random(432L);
        var vertexConsumer5 = multiBufferSource.getBuffer(RenderType.lightning());
        poseStack.pushPose();
        poseStack.translate(0, -0.2D, 0);

        for (var n = 0; n < 12; ++n)
        {
            poseStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F + ticks * 90.0F));
            var height = random.nextFloat() * -1.0F;
            var width = random.nextFloat() * 2.0F + alpha;
            var matrix4f = poseStack.last().pose();
            var alphaVal = (int)(255.0F * (1.0F - alpha));
            vertex01(vertexConsumer5, matrix4f, alphaVal);
            vertex2(vertexConsumer5, matrix4f, height, width);
            vertex3(vertexConsumer5, matrix4f, height, width);
            vertex01(vertexConsumer5, matrix4f, alphaVal);
            vertex3(vertexConsumer5, matrix4f, height, width);
            vertex4(vertexConsumer5, matrix4f, height, width);
            vertex01(vertexConsumer5, matrix4f, alphaVal);
            vertex4(vertexConsumer5, matrix4f, height, width);
            vertex2(vertexConsumer5, matrix4f, height, width);
        }
        poseStack.popPose();
    }

    private static void vertex01(VertexConsumer vertexConsumer, Matrix4f matrix4f, int alpha)
    {
        vertexConsumer.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(0, 0, 0, alpha).endVertex();
    }

    private static void vertex2(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float g)
    {
        vertexConsumer.vertex(matrix4f, (float)(-Math.sqrt(0.1D) * g), f, -0.5F * g).color(10, 10, 10, 0).endVertex();
    }

    private static void vertex3(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float g)
    {
        vertexConsumer.vertex(matrix4f, (float)(Math.sqrt(0.1D) * g), f, -0.5F * g).color(10, 10, 10, 0).endVertex();
    }

    private static void vertex4(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float g)
    {
        vertexConsumer.vertex(matrix4f, 0.0F, f, g).color(10, 10, 10, 0).endVertex();
    }
}
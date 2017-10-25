package stevekung.mods.moreplanets.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.entity.EntityAntiGravFallingBlock;

@SideOnly(Side.CLIENT)
public class RenderAntiGravFallingBlock extends Render<EntityAntiGravFallingBlock>
{
    public RenderAntiGravFallingBlock(RenderManager manager)
    {
        super(manager);
        this.shadowSize = 0.5F;
    }

    @Override
    public void doRender(EntityAntiGravFallingBlock entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        if (entity.getBlockState() != null)
        {
            IBlockState state = entity.getBlockState();

            if (state.getRenderType() == EnumBlockRenderType.MODEL)
            {
                World world = entity.world;

                if (state != world.getBlockState(new BlockPos(entity)) && state.getRenderType() != EnumBlockRenderType.INVISIBLE)
                {
                    this.bindEntityTexture(entity);
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    Tessellator tessellator = Tessellator.getInstance();
                    VertexBuffer vertexbuffer = tessellator.getBuffer();

                    if (this.renderOutlines)
                    {
                        GlStateManager.enableColorMaterial();
                        GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                    }

                    vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
                    BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                    GlStateManager.translate((float)(x - blockpos.getX() - 0.5D), (float)(y - blockpos.getY()), (float)(z - blockpos.getZ() - 0.5D));
                    BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                    blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(state), state, blockpos, vertexbuffer, false, MathHelper.getPositionRandom(entity.getOrigin()));
                    tessellator.draw();

                    if (this.renderOutlines)
                    {
                        GlStateManager.disableOutlineMode();
                        GlStateManager.disableColorMaterial();
                    }
                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                    super.doRender(entity, x, y, z, entityYaw, partialTicks);
                }
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAntiGravFallingBlock entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
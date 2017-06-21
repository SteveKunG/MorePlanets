package stevekung.mods.moreplanets.util.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelPlane extends ModelBox {

    private TexturedQuad quad;

    public ModelPlane(ModelRenderer renderer, int textureX, int textureY, float offX, float offY, float offZ, int width, int height, int depth, boolean flip)
    {
        super(renderer, textureX, textureY, offX, offY, offZ, width, height, depth, 0, false);

        PositionTextureVertex vertex1 = null;
        PositionTextureVertex vertex2 = null;
        PositionTextureVertex vertex3 = null;
        PositionTextureVertex vertex4 = null;
        PositionTextureVertex[] posVerts = null;

        float f = offX + width;
        float f1 = offY + height;
        float f2 = offZ + depth;

        for (int i=0; i<2; i++) {
            //1 = bottom right, 2 = bottom left, 3 = top left, 4 = top right
            if (width == 0) {//y-z plane
                vertex1 = new PositionTextureVertex(offX, f1, f2, 0.0F, 8.0F);//6
                vertex2 = new PositionTextureVertex(offX, offY, f2, 0.0F, 0.0F);//3
                vertex3 = new PositionTextureVertex(offX, offY, offZ, 0.0F, 0.0F);//7
                vertex4 = new PositionTextureVertex(offX, f1, offZ, 0.0F, 8.0F);//2
                if (flip)
                {
                    posVerts = new PositionTextureVertex[] {vertex3, vertex2, vertex1, vertex4};
                }
                else
                {
                    posVerts = new PositionTextureVertex[] {vertex2, vertex3, vertex4, vertex1};
                }
                this.quad = new TexturedQuad(posVerts, textureX + depth, textureY + depth, textureX, textureY + depth + height, renderer.textureWidth, renderer.textureHeight);//???
            }
            else if (height == 0) {
                vertex1 = new PositionTextureVertex(f, offY, f2, 0.0F, 8.0F);//4
                vertex2 = new PositionTextureVertex(offX, offY, f2, 0.0F, 0.0F);//3
                vertex3 = new PositionTextureVertex(offX, offY, offZ, 0.0F, 0.0F);//7                                                             //width7 height8 depth9
                vertex4 = new PositionTextureVertex(f, offY, offZ, 0.0F, 8.0F);//0
                if (flip)
                {
                    posVerts = new PositionTextureVertex[] {vertex3, vertex2, vertex1, vertex4};
                }
                else
                {
                    posVerts = new PositionTextureVertex[] {vertex2, vertex3, vertex4, vertex1};
                }
                this.quad = new TexturedQuad(posVerts, textureX + depth, textureY, textureX + depth + width, textureY + depth, renderer.textureWidth, renderer.textureHeight);//2
            }
            else if (depth == 0) {//x-z plane
                vertex1 = new PositionTextureVertex(f, f1, offZ, 0.0F, 8.0F);//1
                vertex2 = new PositionTextureVertex(offX, f1, offZ, 0.0F, 0.0F);//2
                vertex3 = new PositionTextureVertex(offX, offY, offZ, 0.0F, 0.0F);//7
                vertex4 = new PositionTextureVertex(f, offY, offZ, 0.0F, 8.0F);//0
                if (flip)
                {
                    posVerts = new PositionTextureVertex[] {vertex3, vertex4, vertex1, vertex2};
                }
                else
                {
                    posVerts = new PositionTextureVertex[] {vertex4, vertex3, vertex2, vertex1};
                }
                this.quad = new TexturedQuad(posVerts, textureX + depth, textureY + depth, textureX + depth + width, textureY + depth + height, renderer.textureWidth, renderer.textureHeight);//4
            }//1 = bottom right, 2 = bottom left, 3 = top left, 4 = top right
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(VertexBuffer renderer, float scale)
    {
        this.quad.draw(renderer, scale);
    }
}
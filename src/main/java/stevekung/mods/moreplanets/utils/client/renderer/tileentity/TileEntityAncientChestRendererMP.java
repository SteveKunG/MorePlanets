package stevekung.mods.moreplanets.utils.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityAncientChestMP;
import stevekung.mods.stevekunglib.utils.CalendarUtils;

@SideOnly(Side.CLIENT)
public class TileEntityAncientChestRendererMP extends TileEntitySpecialRenderer<TileEntityAncientChestMP>
{
    private static final ResourceLocation CHRISTMAS_DOUBLE = new ResourceLocation("textures/entity/chest/christmas_double.png");
    private static final ResourceLocation CHRISTMAS = new ResourceLocation("textures/entity/chest/christmas.png");
    private ResourceLocation textureNormalDouble;
    private ResourceLocation textureNormal;
    private ResourceLocation morePlanetsChestNormal;
    private ResourceLocation morePlanetsLargeChestNormal;
    private final ModelChest simpleChest = new ModelChest();
    private final ModelChest largeChest = new ModelLargeChest();

    public TileEntityAncientChestRendererMP(String texture)
    {
        this.textureNormalDouble = new ResourceLocation("moreplanets:textures/model/" + texture + "_ancient_chest_double.png");
        this.textureNormal = new ResourceLocation("moreplanets:textures/model/" + texture + "_ancient_chest.png");
        this.morePlanetsChestNormal = new ResourceLocation("moreplanets:textures/model/stevekung_chest.png");
        this.morePlanetsLargeChestNormal = new ResourceLocation("moreplanets:textures/model/stevekung_chest_double.png");
    }

    @Override
    public void render(TileEntityAncientChestMP tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        int meta;

        if (!tile.hasWorld())
        {
            meta = 0;
        }
        else
        {
            Block block = tile.getBlockType();
            meta = tile.getBlockMetadata();

            if (block instanceof BlockAncientChestMP && meta == 0)
            {
                ((BlockAncientChestMP)block).checkForSurroundingChests(tile.getWorld(), tile.getPos(), tile.getWorld().getBlockState(tile.getPos()));
                meta = tile.getBlockMetadata();
            }
            tile.checkForAdjacentChests();
        }

        if (tile.adjacentChestZNeg == null && tile.adjacentChestXNeg == null)
        {
            ModelChest modelchest;

            if (tile.adjacentChestXPos == null && tile.adjacentChestZPos == null)
            {
                modelchest = this.simpleChest;

                if (destroyStage >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
                else if (CalendarUtils.isChristmasDay())
                {
                    this.bindTexture(TileEntityAncientChestRendererMP.CHRISTMAS);
                }
                else if (CalendarUtils.isMorePlanetsBirthDay())
                {
                    this.bindTexture(this.morePlanetsChestNormal);
                }
                else
                {
                    this.bindTexture(this.textureNormal);
                }
            }
            else
            {
                modelchest = this.largeChest;

                if (destroyStage >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[destroyStage]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
                else if (CalendarUtils.isChristmasDay())
                {
                    this.bindTexture(TileEntityAncientChestRendererMP.CHRISTMAS_DOUBLE);
                }
                else if (CalendarUtils.isMorePlanetsBirthDay())
                {
                    this.bindTexture(this.morePlanetsLargeChestNormal);
                }
                else
                {
                    this.bindTexture(this.textureNormalDouble);
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (destroyStage < 0)
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (meta == 2)
            {
                short1 = 180;
            }
            if (meta == 3)
            {
                short1 = 0;
            }
            if (meta == 4)
            {
                short1 = 90;
            }
            if (meta == 5)
            {
                short1 = -90;
            }

            if (meta == 2 && tile.adjacentChestXPos != null)
            {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }
            if (meta == 5 && tile.adjacentChestZPos != null)
            {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTicks;
            float f2;

            if (tile.adjacentChestZNeg != null)
            {
                f2 = tile.adjacentChestZNeg.prevLidAngle + (tile.adjacentChestZNeg.lidAngle - tile.adjacentChestZNeg.prevLidAngle) * partialTicks;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (tile.adjacentChestXNeg != null)
            {
                f2 = tile.adjacentChestXNeg.prevLidAngle + (tile.adjacentChestXNeg.lidAngle - tile.adjacentChestXNeg.prevLidAngle) * partialTicks;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (destroyStage >= 0)
            {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }
}
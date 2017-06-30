package stevekung.mods.moreplanets.util.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.EnumChestTexture;
import stevekung.mods.moreplanets.util.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.util.helper.CalendarHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAncientChestMP;

@SideOnly(Side.CLIENT)
public class TileEntityAncientChestRendererMP extends TileEntitySpecialRenderer<TileEntityAncientChestMP>
{
    private ResourceLocation textureChristmasDouble;
    private ResourceLocation textureChristmas;
    private ResourceLocation textureNormalDouble;
    private ResourceLocation textureNormal;
    private ResourceLocation morePlanetsChestNormal;
    private ResourceLocation morePlanetsLargeChestNormal;
    private ModelChest simpleChest = new ModelChest();
    private ModelChest largeChest = new ModelLargeChest();

    public TileEntityAncientChestRendererMP(EnumChestTexture texture)
    {
        this.textureChristmasDouble = new ResourceLocation("textures/entity/chest/christmas_double.png");
        this.textureChristmas = new ResourceLocation("textures/entity/chest/christmas.png");
        this.textureNormalDouble = new ResourceLocation("moreplanets:textures/model/" + texture.toString() + "_ancient_chest_double.png");
        this.textureNormal = new ResourceLocation("moreplanets:textures/model/" + texture.toString() + "_ancient_chest.png");
        this.morePlanetsChestNormal = new ResourceLocation("moreplanets:textures/model/stevekung_chest.png");
        this.morePlanetsLargeChestNormal = new ResourceLocation("moreplanets:textures/model/stevekung_chest_double.png");
    }

    @Override
    public void renderTileEntityAt(TileEntityAncientChestMP tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        int meta;

        if (!tile.hasWorldObj())
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
                else if (CalendarHelper.isChristmasDay())
                {
                    this.bindTexture(this.textureChristmas);
                }
                else if (CalendarHelper.isMorePlanetsBirthDay())
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
                else if (CalendarHelper.isChristmasDay())
                {
                    this.bindTexture(this.textureChristmasDouble);
                }
                else if (CalendarHelper.isMorePlanetsBirthDay())
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
package stevekung.mods.moreplanets.utils.client.renderer.tileentity;

import micdoodle8.mods.galacticraft.core.client.model.block.ModelTreasureChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;
import stevekung.mods.stevekunglib.utils.CalendarUtils;

@SideOnly(Side.CLIENT)
public class TileEntityTreasureChestRendererMP extends TileEntitySpecialRenderer<TileEntityTreasureChestMP>
{
    private static final ResourceLocation CHRISTMAS = new ResourceLocation("textures/entity/chest/christmas.png");
    private ResourceLocation textureNormal;
    private final ModelTreasureChest model = new ModelTreasureChest();

    public TileEntityTreasureChestRendererMP(String texture)
    {
        this.textureNormal = new ResourceLocation("moreplanets:textures/model/" + texture + "_treasure_chest.png");
    }

    @Override
    public void render(TileEntityTreasureChestMP tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        int meta;

        if (!tile.hasWorld())
        {
            meta = 0;
        }
        else
        {
            tile.getBlockType();
            meta = tile.getBlockMetadata();
        }

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            if (CalendarUtils.isChristmasDay())
            {
                this.bindTexture(TileEntityTreasureChestRendererMP.CHRISTMAS);
            }
            else
            {
                this.bindTexture(this.textureNormal);
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

        GlStateManager.rotate(short1, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTicks;
        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        this.model.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);

        if (tile.locked)
        {
            this.model.renderAll(false);
        }
        else
        {
            this.model.renderAll(true);
        }

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
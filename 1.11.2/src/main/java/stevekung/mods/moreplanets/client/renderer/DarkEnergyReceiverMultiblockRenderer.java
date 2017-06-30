package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityInfectedCrystallizeEnderCore;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.util.ClientRendererUtils;

public class DarkEnergyReceiverMultiblockRenderer
{
    public static void render(double x, double y, double z)
    {
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityZeliusEgg(), x - 1, y - 1, z, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityZeliusEgg(), x + 1, y - 1, z, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityZeliusEgg(), x, y - 1, z + 1, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityZeliusEgg(), x, y - 1, z - 1, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y - 2, z);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y - 2, z + 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 1, y - 2, z + 1);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 1, y - 2, z + 1);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 1, y - 2, z);
        ClientRendererUtils.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 1, y - 2, z + 2);
        ClientRendererUtils.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 1, y - 2, z);
        ClientRendererUtils.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 1, y - 2, z + 2);
        ClientRendererUtils.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y - 1, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1));
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 1, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 2, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2));
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityInfectedCrystallizeEnderCore(), x + 3, y + 2, z + 3, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y - 1, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1));
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 1, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 2, z + 4);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2));
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityInfectedCrystallizeEnderCore(), x - 3, y + 2, z + 3, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y - 1, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1));
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 1, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 2, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2));
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityInfectedCrystallizeEnderCore(), x - 3, y + 2, z - 3, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y - 1, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1));
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 1, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 2, z - 2);
        ClientRendererUtils.renderModel(DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2));
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityInfectedCrystallizeEnderCore(), x + 3, y + 2, z - 3, 0.0F);
    }
}
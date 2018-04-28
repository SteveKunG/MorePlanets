package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityInfectedCrystallizedEnderCore;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.util.ClientRendererUtil;

public class DarkEnergyReceiverMultiblockRenderer
{
    public static void render(double x, double y, double z)
    {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.render(new TileEntityZeliusEgg(), x - 1, y - 1, z, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.render(new TileEntityZeliusEgg(), x + 1, y - 1, z, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.render(new TileEntityZeliusEgg(), x, y - 1, z + 1, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        TileEntityRendererDispatcher.instance.render(new TileEntityZeliusEgg(), x, y - 1, z - 1, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y - 2, z);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y - 2, z + 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 1, y - 2, z + 1);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 1, y - 2, z + 1);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 1, y - 2, z);
        ClientRendererUtil.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 1, y - 2, z + 2);
        ClientRendererUtil.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 1, y - 2, z);
        ClientRendererUtil.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 1, y - 2, z + 2);
        ClientRendererUtil.renderModel(MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y - 1, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 1, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 2, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.render(new TileEntityInfectedCrystallizedEnderCore(), x + 3, y + 2, z + 3, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y - 1, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 1, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 2, z + 4);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.render(new TileEntityInfectedCrystallizedEnderCore(), x - 3, y + 2, z + 3, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y - 1, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 1, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 3, y + 2, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.render(new TileEntityInfectedCrystallizedEnderCore(), x - 3, y + 2, z - 3, 0.0F);

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.4F);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y - 1, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 1, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 3, y + 2, z - 2);
        ClientRendererUtil.renderModel(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());
        GlStateManager.popMatrix();

        TileEntityRendererDispatcher.instance.render(new TileEntityInfectedCrystallizedEnderCore(), x + 3, y + 2, z - 3, 0.0F);
        GlStateManager.enableDepth();
    }
}
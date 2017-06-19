package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.RenderInfectedCrystallizeBomb;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.TileEntityDarkEnergyGeneratorRenderer;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.*;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity.TileEntityNuclearWasteTankRenderer;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.*;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

@SideOnly(Side.CLIENT)
public class TileEntityItemStackRendererMP extends TileEntityItemStackRenderer
{
    @Override
    public void renderByItem(ItemStack itemStack)
    {
        Block block = Block.getBlockFromItem(itemStack.getItem());

        if (block == DionaBlocks.DIONA_ANCIENT_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityDionaAncientChest.class);
        }
        else if (block == DionaBlocks.DIONA_TREASURE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityDionaTreasureChest.class);
        }
        else if (block == ChalosBlocks.CHALOS_ANCIENT_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityChalosAncientChest.class);
        }
        else if (block == ChalosBlocks.CHALOS_TREASURE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityChalosTreasureChest.class);
        }
        else if (block == DionaBlocks.ZELIUS_EGG)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityZeliusEgg.class);
            GlStateManager.enableBlend();
        }
        else if (block == DionaBlocks.LARGE_INFECTED_CRYSTALLIZE)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityLargeInfectedCrystallize.class);
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.DARK_ENERGY_RECEIVER)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityDarkEnergyReceiver.class);
        }
        else if (itemStack.getItem() == DionaItems.INFECTED_CRYSTALLIZE_BOMB)
        {
            RenderInfectedCrystallizeBomb.INSTANCE.renderItem();
        }
        else if (block == NibiruBlocks.NIBIRU_ANCIENT_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityNibiruAncientChest.class);
        }
        else if (block == NibiruBlocks.NIBIRU_TREASURE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityNibiruTreasureChest.class);
        }
        else if (block == ChalosBlocks.CHEESE_SPORE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityCheeseSporeChest.class);
        }
        else if (block == NibiruBlocks.INFECTED_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityInfectedChest.class);
        }
        else if (block == NibiruBlocks.MULTALIC_CRYSTAL)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityMultalicCrystal.class);
            GlStateManager.enableBlend();
        }
        else if (block == DionaBlocks.DARK_ENERGY_CORE)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityDarkEnergyCore.class);
            GlStateManager.enableBlend();
        }
        else if (block == NibiruBlocks.ALIEN_BERRY_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityAlienBerryChest.class);
        }
        else if (block == NibiruBlocks.JUICER_EGG)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityJuicerEgg.class);
        }
        else if (block == NibiruBlocks.NUCLEAR_WASTE_TANK)
        {
            TileEntityNuclearWasteTankRenderer.INSTANCE.renderItem();
        }
        else if (block == NibiruBlocks.VEIN_FRAME)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityVeinFrame.class);
            GlStateManager.enableBlend();
        }
        else if (block == DionaBlocks.DARK_ENERGY_GENERATOR)
        {
            TileEntityDarkEnergyGeneratorRenderer.INSTANCE.renderItem();
        }
        else if (block == MPBlocks.BLACK_HOLE_STORAGE)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityBlackHoleStorage.class);
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.ALIEN_DEFENDER_BEACON)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(TileEntityAlienDefenderBeacon.class);
            GlStateManager.enableBlend();
            GlStateManager.enableCull();
        }
        else
        {
            super.renderByItem(itemStack);
        }
    }
}
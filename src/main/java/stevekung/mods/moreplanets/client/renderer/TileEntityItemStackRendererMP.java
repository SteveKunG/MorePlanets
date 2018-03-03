package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntityShieldGeneratorRenderer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.module.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.entity.RenderInfectedCrystallizeBomb;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.TileEntityDarkEnergyGeneratorRenderer;
import stevekung.mods.moreplanets.module.planets.diona.client.renderer.tileentity.TileEntityLargeInfectedCrystallizeRenderer;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity.TileEntityMultalicCrystalRenderer;
import stevekung.mods.moreplanets.module.planets.nibiru.client.renderer.tileentity.TileEntityNuclearWasteTankRenderer;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.*;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;

@SideOnly(Side.CLIENT)
public class TileEntityItemStackRendererMP extends TileEntityItemStackRenderer
{
    private TileEntityItemStackRenderer old;

    public TileEntityItemStackRendererMP(TileEntityItemStackRenderer old)
    {
        this.old = old;
    }

    @Override
    public void renderByItem(ItemStack itemStack)
    {
        Block block = Block.getBlockFromItem(itemStack.getItem());

        if (block == DionaBlocks.DIONA_ANCIENT_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityDionaAncientChest());
        }
        else if (block == DionaBlocks.DIONA_TREASURE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityDionaTreasureChest());
        }
        else if (block == ChalosBlocks.CHALOS_ANCIENT_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityChalosAncientChest());
        }
        else if (block == ChalosBlocks.CHALOS_TREASURE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityChalosTreasureChest());
        }
        else if (block == DionaBlocks.ZELIUS_EGG)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityZeliusEgg());
            GlStateManager.enableBlend();
        }
        else if (block == DionaBlocks.LARGE_INFECTED_CRYSTALLIZE)
        {
            TileEntityLargeInfectedCrystallizeRenderer.INSTANCE.render();
        }
        else if (block == MPBlocks.DARK_ENERGY_RECEIVER)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityDarkEnergyReceiver());
        }
        else if (itemStack.getItem() == DionaItems.INFECTED_CRYSTALLIZE_BOMB)
        {
            RenderInfectedCrystallizeBomb.INSTANCE.render(1.0F, 1.5F);
        }
        else if (block == NibiruBlocks.NIBIRU_ANCIENT_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityNibiruAncientChest());
        }
        else if (block == NibiruBlocks.NIBIRU_TREASURE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityNibiruTreasureChest());
        }
        else if (block == ChalosBlocks.CHEESE_SPORE_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityCheeseSporeChest());
        }
        else if (block == NibiruBlocks.INFECTED_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityInfectedChest());
        }
        else if (block == NibiruBlocks.MULTALIC_CRYSTAL)
        {
            TileEntityMultalicCrystalRenderer.INSTANCE.render();
        }
        else if (block == DionaBlocks.DARK_ENERGY_CORE)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityDarkEnergyCore());
            GlStateManager.enableBlend();
        }
        else if (block == NibiruBlocks.ALIEN_BERRY_CHEST)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityAlienBerryChest());
        }
        else if (block == NibiruBlocks.JUICER_EGG)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityJuicerEgg());
        }
        else if (block == NibiruBlocks.NUCLEAR_WASTE_TANK)
        {
            TileEntityNuclearWasteTankRenderer.INSTANCE.render(1.0F, 1.5F);
        }
        else if (block == NibiruBlocks.VEIN_FRAME)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityVeinFrame());
            GlStateManager.enableBlend();
        }
        else if (block == DionaBlocks.DARK_ENERGY_GENERATOR)
        {
            TileEntityDarkEnergyGeneratorRenderer.INSTANCE.render(1.0F, 1.5F);
        }
        else if (block == MPBlocks.BLACK_HOLE_STORAGE)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityBlackHoleStorage());
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.ALIEN_DEFENDER_BEACON)
        {
            ClientRegisterHelper.registerTileEntityItemStackRendering(new TileEntityAlienDefenderBeacon());
            GlStateManager.enableBlend();
            GlStateManager.enableCull();
        }
        else if (block == MPBlocks.SHIELD_GENERATOR)
        {
            TileEntityShieldGeneratorRenderer.INSTANCE.render(1.0F, 1.5F);
        }
        else
        {
            this.old.renderByItem(itemStack);
        }
    }
}
package stevekung.mods.moreplanets.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.tileentity.TileEntityShieldGeneratorRenderer;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosAncientChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityChalosTreasureChest;
import stevekung.mods.moreplanets.planets.chalos.tileentity.TileEntityCheeseSporeChest;
import stevekung.mods.moreplanets.planets.diona.client.renderer.entity.RenderInfectedCrystallizedBomb;
import stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity.TileEntityDarkEnergyGeneratorRenderer;
import stevekung.mods.moreplanets.planets.diona.client.renderer.tileentity.TileEntityInfectedCrystallizedCrystalRenderer;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDionaAncientChest;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDionaTreasureChest;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.planets.nibiru.client.renderer.tileentity.TileEntityJuicerEggRenderer;
import stevekung.mods.moreplanets.planets.nibiru.client.renderer.tileentity.TileEntityMultalicCrystalRenderer;
import stevekung.mods.moreplanets.planets.nibiru.client.renderer.tileentity.TileEntityNuclearWasteTankRenderer;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.*;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienChest;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

@SideOnly(Side.CLIENT)
public class TileEntityItemStackRendererMP extends TileEntityItemStackRenderer
{
    public static final TileEntityItemStackRendererMP INSTANCE = new TileEntityItemStackRendererMP();
    private final TileEntityDionaAncientChest dionaAncientChest = new TileEntityDionaAncientChest();
    private final TileEntityDionaTreasureChest dionaTreasureChest = new TileEntityDionaTreasureChest();
    private final TileEntityChalosAncientChest chalosAncientChest = new TileEntityChalosAncientChest();
    private final TileEntityChalosTreasureChest chalosTreasureChest = new TileEntityChalosTreasureChest();
    private final TileEntityNibiruAncientChest nibiruAncientChest = new TileEntityNibiruAncientChest();
    private final TileEntityNibiruTreasureChest nibiruTreasureChest = new TileEntityNibiruTreasureChest();
    private final TileEntityZeliusEgg zeliusEgg = new TileEntityZeliusEgg();
    private final TileEntityDarkEnergyReceiver receiver = new TileEntityDarkEnergyReceiver();
    private final TileEntityCheeseSporeChest sporeChest = new TileEntityCheeseSporeChest();
    private final TileEntityInfectedChest infectedChest = new TileEntityInfectedChest();
    private final TileEntityDarkEnergyCore darkEnergyCore = new TileEntityDarkEnergyCore();
    private final TileEntityAlienBerryChest alienBerryChest = new TileEntityAlienBerryChest();
    private final TileEntityJuicerEgg juicerEgg = new TileEntityJuicerEgg();
    private final TileEntityVeinFrame veinFrame = new TileEntityVeinFrame();
    private final TileEntityBlackHoleStorage blackHoleStorage = new TileEntityBlackHoleStorage();
    private final TileEntityAlienDefenderBeacon alienBeacon = new TileEntityAlienDefenderBeacon();
    private final TileEntityAlienChest alienChest = new TileEntityAlienChest();

    @Override
    public void renderByItem(ItemStack itemStack)
    {
        Block block = Block.getBlockFromItem(itemStack.getItem());

        if (block == MPBlocks.DIONA_ANCIENT_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.dionaAncientChest);
        }
        else if (block == MPBlocks.DIONA_TREASURE_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.dionaTreasureChest);
        }
        else if (block == MPBlocks.CHALOS_ANCIENT_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.chalosAncientChest);
        }
        else if (block == MPBlocks.CHALOS_TREASURE_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.chalosTreasureChest);
        }
        else if (block == MPBlocks.ZELIUS_EGG)
        {
            ClientRegistryUtils.renderTESR(this.zeliusEgg);
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.INFECTED_CRYSTALLIZED_CRYSTAL)
        {
            TileEntityInfectedCrystallizedCrystalRenderer.INSTANCE.render();
        }
        else if (block == MPBlocks.DARK_ENERGY_RECEIVER)
        {
            ClientRegistryUtils.renderTESR(this.receiver);
        }
        else if (itemStack.getItem() == MPItems.INFECTED_CRYSTALLIZED_BOMB)
        {
            RenderInfectedCrystallizedBomb.INSTANCE.render();
        }
        else if (block == MPBlocks.NIBIRU_ANCIENT_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.nibiruAncientChest);
        }
        else if (block == MPBlocks.NIBIRU_TREASURE_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.nibiruTreasureChest);
        }
        else if (block == MPBlocks.CHEESE_SPORE_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.sporeChest);
        }
        else if (block == MPBlocks.INFECTED_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.infectedChest);
        }
        else if (block == MPBlocks.MULTALIC_CRYSTAL)
        {
            TileEntityMultalicCrystalRenderer.INSTANCE.render();
        }
        else if (block == MPBlocks.DARK_ENERGY_CORE)
        {
            ClientRegistryUtils.renderTESR(this.darkEnergyCore);
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.ALIEN_BERRY_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.alienBerryChest);
        }
        else if (block == MPBlocks.JUICER_EGG)
        {
            ClientRegistryUtils.renderTESR(this.juicerEgg);
            TileEntityJuicerEggRenderer.renderItem(null, true);
        }
        else if (block == MPBlocks.NUCLEAR_WASTE_TANK)
        {
            boolean hasRod = true;
            boolean createRod = false;
            int amount = 0;

            if (itemStack.hasTagCompound())
            {
                hasRod = itemStack.getTagCompound().getBoolean("HasRod");
                createRod = itemStack.getTagCompound().getBoolean("CreateRod");
                amount = itemStack.getTagCompound().hasKey("FluidTank") ? itemStack.getTagCompound().getCompoundTag("FluidTank").getInteger("Amount") : 0;
            }
            TileEntityNuclearWasteTankRenderer.INSTANCE.render(hasRod, createRod, amount);
        }
        else if (block == MPBlocks.VEIN_FRAME)
        {
            ClientRegistryUtils.renderTESR(this.veinFrame);
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.DARK_ENERGY_GENERATOR)
        {
            TileEntityDarkEnergyGeneratorRenderer.INSTANCE.render();
        }
        else if (block == MPBlocks.BLACK_HOLE_STORAGE)
        {
            ClientRegistryUtils.renderTESR(this.blackHoleStorage);
            GlStateManager.enableBlend();
        }
        else if (block == MPBlocks.ALIEN_DEFENDER_BEACON)
        {
            ClientRegistryUtils.renderTESR(this.alienBeacon);
            GlStateManager.enableBlend();
            GlStateManager.enableCull();
        }
        else if (block == MPBlocks.SHIELD_GENERATOR)
        {
            TileEntityShieldGeneratorRenderer.INSTANCE.render();
        }
        else if (block == MPBlocks.ALIEN_CHEST)
        {
            ClientRegistryUtils.renderTESR(this.alienChest);
        }
    }
}
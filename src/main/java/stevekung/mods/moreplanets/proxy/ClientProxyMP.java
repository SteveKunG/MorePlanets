package stevekung.mods.moreplanets.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.*;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPSchematics;
import stevekung.mods.moreplanets.items.ItemCapsule;
import stevekung.mods.moreplanets.moons.koentus.client.particle.ParticleGravityHarvester;
import stevekung.mods.moreplanets.moons.koentus.client.particle.ParticleKoentusMeteor;
import stevekung.mods.moreplanets.planets.diona.client.particle.ParticleAlienMinerSpark;
import stevekung.mods.moreplanets.planets.diona.client.particle.ParticleCrystallizedFlame;
import stevekung.mods.moreplanets.planets.diona.client.particle.ParticleDarkPortal;
import stevekung.mods.moreplanets.planets.nibiru.client.particle.ParticleAlienBerryLeavesSpark;
import stevekung.mods.moreplanets.planets.nibiru.client.particle.ParticleInfectedGuardianAppearance;
import stevekung.mods.moreplanets.planets.nibiru.client.particle.ParticleInfectedSpore;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;
import stevekung.mods.moreplanets.utils.client.particle.ParticleBreakingMC;
import stevekung.mods.moreplanets.utils.client.particle.ParticleFallingDustMP;
import stevekung.mods.moreplanets.utils.client.particle.ParticleLavaMC;
import stevekung.mods.moreplanets.utils.client.particle.ParticleLiquidDrip;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.CommonUtils;
import stevekung.mods.stevekunglib.utils.client.ClientRegistryUtils;

public class ClientProxyMP extends ServerProxyMP
{
    @Override
    public void registerVariant()
    {
        VariantsRenderer.init();
        BlockStateMapper.init();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        ModelLoaderRegistry.registerLoader(OBJLoaderMP.INSTANCE);
        EntityRendererMP.init();
        CommonUtils.registerEventHandler(this);

        if (CompatibilityManagerMP.isCCLLoaded)
        {
            ItemModelRenderer.registerCCLRenderer();
        }
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        BlockColors color = Minecraft.getMinecraft().getBlockColors();

        ItemModelRenderer.init();
        TileEntityRenderer.init();

        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D), MPBlocks.FRONOS_GRASS_BLOCK);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(120, 85, 190), MPBlocks.LARGE_INFECTED_CRYSTALLIZED);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(50, 101, 236), MPBlocks.MULTALIC_CRYSTAL);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(50, 101, 236), MPBlocks.MULTALIC_CRYSTAL_BLOCK);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(143, 55, 33), MPBlocks.INFECTED_MELON_STEM);

        ClientRegistryUtils.registerItemColor((itemStack, tintIndex) -> color.colorMultiplier(((ItemBlock)itemStack.getItem()).getBlock().getDefaultState(), null, null, tintIndex), MPBlocks.FRONOS_GRASS_BLOCK);
        ClientRegistryUtils.registerItemColor((itemStack, tintIndex) -> ColorUtils.rgbToDecimal(50, 101, 236), MPBlocks.MULTALIC_CRYSTAL_BLOCK);
        ClientRegistryUtils.registerItemColor((itemStack, tintIndex) -> tintIndex == 1 ? ItemCapsule.CapsuleType.INFECTED_SPORE.getColor() : -1, MPItems.INFECTED_SPORE_PROTECTION_CAPSULE);
        ClientRegistryUtils.registerItemColor((itemStack, tintIndex) -> tintIndex == 1 ? ItemCapsule.CapsuleType.DARK_ENERGY.getColor() : -1, MPItems.DARK_ENERGY_PROTECTION_CAPSULE);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        MPSchematics.registerSchematicTexture();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTexturesStitch(TextureStitchEvent.Pre event)
    {
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "blocks/infected_crystallized");
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "blocks/shield");
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "blocks/xpjuice_still");
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "blocks/xpjuice_flowing");
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "particle/crystallized_lava");
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "particle/alien_berry_leaves_spark");
        MorePlanetsMod.CLIENT_REGISTRY.registerSpriteTexture(event, "particle/crystallized_flame");
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event)
    {
        for (Item item : BlocksItemsRegistry.TESR_ITEM_RENDER)
        {
            item.setTileEntityItemStackRenderer(TileEntityItemStackRendererMP.INSTANCE);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event)
    {
        this.registerVariant();
    }

    @Override
    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z)
    {
        this.spawnParticle(type, x, y, z, 0.0D, 0.0D, 0.0D, null);
    }

    @Override
    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, Object[] objects)
    {
        this.spawnParticle(type, x, y, z, 0.0D, 0.0D, 0.0D, objects);
    }

    @Override
    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        this.spawnParticle(type, x, y, z, motionX, motionY, motionZ, null);
    }

    @Override
    public void spawnParticle(EnumParticleTypesMP type, double x, double y, double z, double motionX, double motionY, double motionZ, Object[] data)
    {
        Particle particle = null;
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.getRenderViewEntity() != null && mc.effectRenderer != null && mc.world != null)
        {
            int i = mc.gameSettings.particleSetting;
            double d6 = mc.getRenderViewEntity().posX - x;
            double d7 = mc.getRenderViewEntity().posY - y;
            double d8 = mc.getRenderViewEntity().posZ - z;
            double d9 = 16.0D;

            if (i == 1 && mc.world.rand.nextInt(3) == 0)
            {
                i = 2;
            }
            if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
            {
                return;
            }
            else if (i > 1)
            {
                return;
            }

            if (type == EnumParticleTypesMP.CRYSTALLIZED_FLAME)
            {
                particle = new ParticleCrystallizedFlame(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.CHEESE_MILK_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, ColorUtils.intToRGB(255, 236, 182, 210), false);
            }
            else if (type == EnumParticleTypesMP.INFECTED_SPORE)
            {
                particle = new ParticleInfectedSpore(mc.world, x, y, z, motionX, motionY, motionZ);
            }
            else if (type == EnumParticleTypesMP.ALIEN_MINER_SPARK)
            {
                particle = new ParticleAlienMinerSpark(mc.world, x, y, z, (float) data[0]);
            }
            else if (type == EnumParticleTypesMP.INFECTED_GUARDIAN_APPEARANCE)
            {
                particle = new ParticleInfectedGuardianAppearance(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.DARK_PORTAL)
            {
                particle = new ParticleDarkPortal(mc.world, x, y, z, motionX, motionY, motionZ);
            }
            else if (type == EnumParticleTypesMP.ALIEN_BERRY_LEAVES_SPARK)
            {
                particle = new ParticleAlienBerryLeavesSpark(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING)
            {
                particle = new ParticleBreakingMC(mc.world, x, y, z, (Item) data[0]);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING_MOTION)
            {
                particle = new ParticleBreakingMC(mc.world, x, y, z, motionX, motionY, motionZ, (Item) data[0]);
            }
            else if (type == EnumParticleTypesMP.INFECTED_WATER_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, ColorUtils.intToRGB(133, 51, 31, 204), false);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZED_WATER_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, ColorUtils.intToRGB(133, 102, 194, 150), false);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZED_LAVA_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, ColorUtils.intToRGB(153, 127, 204, 255), true);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZED_LAVA)
            {
                particle = new ParticleLavaMC(mc.world, x, y, z, "crystallized_lava");
            }
            else if (type == EnumParticleTypesMP.NUCLEAR_WASTE_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, ColorUtils.intToRGB(145, 242, 88, 255), true);
            }
            else if (type == EnumParticleTypesMP.PURIFY_WATER_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, ColorUtils.intToRGB(147, 209, 255, 130), false);
            }
            else if (type == EnumParticleTypesMP.KOENTUS_METEOR_SMOKE)
            {
                particle = new ParticleKoentusMeteor(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_FALLING_DUST)
            {
                particle = new ParticleFallingDustMP(mc.world, x, y, z, (int) data[0]);
            }
            else if (type == EnumParticleTypesMP.GRAVITY_HARVESTER)
            {
                particle = new ParticleGravityHarvester(mc.world, x, y, z, (boolean) data[0]);
            }

            if (particle != null)
            {
                mc.effectRenderer.addEffect(particle);
            }
        }
    }

    @Override
    public void addBoss(IMorePlanetsBoss boss)
    {
        ClientEventHandler.bossList.add(boss);
    }

    @Override
    public void removeBoss(IMorePlanetsBoss boss)
    {
        ClientEventHandler.bossList.remove(boss);
    }
}
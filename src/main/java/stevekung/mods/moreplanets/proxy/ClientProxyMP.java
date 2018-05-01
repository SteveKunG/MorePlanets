package stevekung.mods.moreplanets.proxy;

import com.google.common.base.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.internal.FMLMessage.EntitySpawnMessage;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.*;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.entity.projectile.EntitySpaceFishHook;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPSchematics;
import stevekung.mods.moreplanets.items.ItemCapsule;
import stevekung.mods.moreplanets.module.moons.koentus.client.particle.ParticleKoentusMeteor;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.client.particle.ParticleAlienMinerSpark;
import stevekung.mods.moreplanets.module.planets.diona.client.particle.ParticleCrystallizedFlame;
import stevekung.mods.moreplanets.module.planets.diona.client.particle.ParticleDarkPortal;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.client.particle.ParticleAlienBerry;
import stevekung.mods.moreplanets.module.planets.nibiru.client.particle.ParticleInfectedGuardianAppearance;
import stevekung.mods.moreplanets.module.planets.nibiru.client.particle.ParticleInfectedSpore;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IMorePlanetsBoss;
import stevekung.mods.moreplanets.utils.client.particle.ParticleBreakingMC;
import stevekung.mods.moreplanets.utils.client.particle.ParticleFallingDustMP;
import stevekung.mods.moreplanets.utils.client.particle.ParticleLavaMC;
import stevekung.mods.moreplanets.utils.client.particle.ParticleLiquidDrip;
import stevekung.mods.stevekunglib.utils.ClientRegistryUtils;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.CommonUtils;

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
        ClientProxyMP.handleSpaceFishHookSpawning();
        CommonUtils.registerEventHandler(this);

        if (!CompatibilityManagerMP.isCCLLoaded)
        {
            TileEntityItemStackRenderer.instance = new TileEntityItemStackRendererMP(TileEntityItemStackRenderer.instance);
        }
        else
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

        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D), FronosBlocks.FRONOS_GRASS);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(120, 85, 190), DionaBlocks.LARGE_INFECTED_CRYSTALLIZED);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(50, 101, 236), NibiruBlocks.MULTALIC_CRYSTAL);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(50, 101, 236), NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        ClientRegistryUtils.registerBlockColor((state, world, pos, tint) -> ColorUtils.rgbToDecimal(143, 55, 33), NibiruBlocks.INFECTED_MELON_STEM);

        ClientRegistryUtils.registerItemColor((itemStack, tintIndex) -> color.colorMultiplier(((ItemBlock)itemStack.getItem()).getBlock().getStateFromMeta(itemStack.getMetadata()), null, null, tintIndex), FronosBlocks.FRONOS_GRASS);
        ClientRegistryUtils.registerItemColor((itemStack, tintIndex) -> ColorUtils.rgbToDecimal(50, 101, 236), NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
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
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event)
    {

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
                particle = new ParticleLiquidDrip(mc.world, x, y, z, 1.0F, 0.85F, 0.5F, 0.4F, false);
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
            else if (type == EnumParticleTypesMP.ALIEN_BERRY_LEAVES)
            {
                particle = new ParticleAlienBerry(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING)
            {
                particle = new ParticleBreakingMC(mc.world, x, y, z, (Item) data[0]);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING_META)
            {
                particle = new ParticleBreakingMC(mc.world, x, y, z, (Item) data[0], (int) data[1]);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING_MOTION)
            {
                particle = new ParticleBreakingMC(mc.world, x, y, z, motionX, motionY, motionZ, (Item) data[0]);
            }
            else if (type == EnumParticleTypesMP.INFECTED_WATER_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, 0.95F, 0.4F, 0.3F, 0.6F, false);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZED_WATER_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, 0.6F, 0.2F, 0.8F, 0.6F, false);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZED_LAVA_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, 0.6F, 0.2F, 0.8F, 1.0F, true);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZED_LAVA)
            {
                particle = new ParticleLavaMC(mc.world, x, y, z, "crystallized_lava");
            }
            else if (type == EnumParticleTypesMP.MC_SMOKE_LARGE)
            {
                mc.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, 0.0D, 0.0D, 0.0D);
            }
            else if (type == EnumParticleTypesMP.NUCLEAR_WASTE_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, 0.4F, 0.8F, 0.1F, 1.0F, true);
            }
            else if (type == EnumParticleTypesMP.PURIFY_WATER_DRIP)
            {
                particle = new ParticleLiquidDrip(mc.world, x, y, z, 0.45F, 0.8F, 1.0F, 0.6F, false);
            }
            else if (type == EnumParticleTypesMP.KOENTUS_METEOR_SMOKE)
            {
                particle = new ParticleKoentusMeteor(mc.world, x, y, z, motionX, motionY, motionZ);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_FALLING_DUST)
            {
                particle = new ParticleFallingDustMP(mc.world, x, y, z, (int) data[0]);
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

    private static void handleSpaceFishHookSpawning()
    {
        EntityRegistration entityRegistration = EntityRegistry.instance().lookupModSpawn(EntitySpaceFishHook.class, false);

        Function<EntitySpawnMessage, Entity> handler = input ->
        {
            int entityID = 0;
            double posX = 0;
            double posY = 0;
            double posZ = 0;
            WorldClient world = FMLClientHandler.instance().getWorldClient();

            try
            {
                entityID = ReflectionHelper.findField(EntitySpawnMessage.class, "throwerId").getInt(input);
                posX = ReflectionHelper.findField(EntitySpawnMessage.class, "rawX").getDouble(input);
                posY = ReflectionHelper.findField(EntitySpawnMessage.class, "rawY").getDouble(input);
                posZ = ReflectionHelper.findField(EntitySpawnMessage.class, "rawZ").getDouble(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            Entity angler = world.getEntityByID(entityID);

            if (angler instanceof EntityPlayer)
            {
                Entity entity = new EntitySpaceFishHook(world, (EntityPlayer) angler, posX, posY, posZ);
                return entity;
            }
            return null;
        };
        entityRegistration.setCustomSpawning(handler, false);
    }
}
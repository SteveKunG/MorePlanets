package stevekung.mods.moreplanets.proxy;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

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
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.internal.FMLMessage.EntitySpawnMessage;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.*;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.entity.projectile.EntitySpaceFishHook;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPSchematics;
import stevekung.mods.moreplanets.module.moons.koentus.client.particle.ParticleKoentusMeteor;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.client.particle.ParticleAlienMinerSpark;
import stevekung.mods.moreplanets.module.planets.diona.client.particle.ParticleCrystallizeFlame;
import stevekung.mods.moreplanets.module.planets.diona.client.particle.ParticleDarkPortal;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.client.particle.ParticleAlienBerry;
import stevekung.mods.moreplanets.module.planets.nibiru.client.particle.ParticleInfectedGuardianAppearance;
import stevekung.mods.moreplanets.module.planets.nibiru.client.particle.ParticleInfectedSpore;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.IMorePlanetsBoss;
import stevekung.mods.moreplanets.util.client.particle.ParticleBreakingMC;
import stevekung.mods.moreplanets.util.client.particle.ParticleFallingDustMP;
import stevekung.mods.moreplanets.util.client.particle.ParticleLavaMC;
import stevekung.mods.moreplanets.util.client.particle.ParticleLiquidDrip;
import stevekung.mods.moreplanets.util.client.renderer.item.ItemRendererTieredRocket;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;
import stevekung.mods.moreplanets.util.helper.ColorHelper;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class ClientProxyMP extends ServerProxyMP
{
    @Override
    public void registerVariant()
    {
        VariantsRenderer.init();
        BlockStateMapper.init();
    }

    @Override
    public void registerPreRendering()
    {
        OBJLoader.INSTANCE.addDomain(MorePlanetsCore.MOD_ID);
        EntityRendererMP.init();
        TileEntityItemStackRenderer.instance = new TileEntityItemStackRendererMP();
        ClientProxyMP.handleSpaceFishHookSpawning();
        CommonRegisterHelper.registerForgeEvent(this);
    }

    @Override
    public void registerInitRendering()
    {
        BlockColors color = Minecraft.getMinecraft().getBlockColors();

        ItemModelRenderer.init();
        TileEntityRenderer.init();

        ClientRegisterHelper.registerBlockColor((state, world, pos, tint) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D), FronosBlocks.FRONOS_GRASS);
        ClientRegisterHelper.registerBlockColor((state, world, pos, tint) -> ColorHelper.rgbToDecimal(120, 85, 190), DionaBlocks.LARGE_INFECTED_CRYSTALLIZE);
        ClientRegisterHelper.registerBlockColor((state, world, pos, tint) -> ColorHelper.rgbToDecimal(50, 101, 236), NibiruBlocks.MULTALIC_CRYSTAL);
        ClientRegisterHelper.registerBlockColor((state, world, pos, tint) -> ColorHelper.rgbToDecimal(50, 101, 236), NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        ClientRegisterHelper.registerBlockColor((state, world, pos, tint) -> ColorHelper.rgbToDecimal(143, 55, 33), NibiruBlocks.INFECTED_MELON_STEM);

        ClientRegisterHelper.registerItemColor((itemStack, tintIndex) -> color.colorMultiplier(((ItemBlock)itemStack.getItem()).getBlock().getStateFromMeta(itemStack.getMetadata()), null, null, tintIndex), FronosBlocks.FRONOS_GRASS);
        ClientRegisterHelper.registerItemColor((itemStack, tintIndex) -> ColorHelper.rgbToDecimal(50, 101, 236), NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        ClientRegisterHelper.registerItemColor((itemStack, tintIndex) -> itemStack.hasTagCompound() && tintIndex == 1 ? itemStack.getTagCompound().getInteger("Color") : -1, MPItems.CAPSULE);
    }

    @Override
    public void registerPostRendering()
    {
        MPSchematics.registerSchematicTexture();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTexturesStitch(TextureStitchEvent.Pre event)
    {
        ClientRegisterHelper.registerSpriteTexture(event, "blocks/infected_crystallize");
        ClientRegisterHelper.registerSpriteTexture(event, "blocks/shield");
        ClientRegisterHelper.registerSpriteTexture(event, "entity/space_capsule");
        ClientRegisterHelper.registerSpriteTexture(event, "entity/tier_4_rocket");
        ClientRegisterHelper.registerSpriteTexture(event, "entity/tier_5_rocket");
        ClientRegisterHelper.registerSpriteTexture(event, "entity/tier_6_rocket");
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event)
    {
        ClientRegisterHelper.registerOBJModel(event, "tier_4_rocket", "tier_4_rocket", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemRendererTieredRocket.class, TRSRTransformation.identity());
        ClientRegisterHelper.registerOBJModel(event, "tier_5_rocket", "tier_5_rocket", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemRendererTieredRocket.class, TRSRTransformation.identity());
        ClientRegisterHelper.registerOBJModel(event, "tier_6_rocket", "tier_6_rocket", ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket"), ItemRendererTieredRocket.class, TRSRTransformation.identity());
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
        Particle entityfx = null;
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

            if (type == EnumParticleTypesMP.CRYSTALLIZE_FLAME)
            {
                entityfx = new ParticleCrystallizeFlame(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.CHEESE_OF_MILK_DRIP)
            {
                entityfx = new ParticleLiquidDrip(mc.world, x, y, z, 1.0F, 0.85F, 0.5F, 0.4F, false);
            }
            else if (type == EnumParticleTypesMP.INFECTED_SPORE)
            {
                entityfx = new ParticleInfectedSpore(mc.world, x, y, z, motionX, motionY, motionZ);
            }
            else if (type == EnumParticleTypesMP.ALIEN_MINER_SPARK)
            {
                entityfx = new ParticleAlienMinerSpark(mc.world, x, y, z, (float) data[0]);
            }
            else if (type == EnumParticleTypesMP.INFECTED_GUARDIAN_APPEARANCE)
            {
                entityfx = new ParticleInfectedGuardianAppearance(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.DARK_PORTAL)
            {
                entityfx = new ParticleDarkPortal(mc.world, x, y, z, motionX, motionY, motionZ);
            }
            else if (type == EnumParticleTypesMP.ALIEN_BERRY_LEAVES)
            {
                entityfx = new ParticleAlienBerry(mc.world, x, y, z);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING)
            {
                entityfx = new ParticleBreakingMC(mc.world, x, y, z, (Item) data[0]);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING_META)
            {
                entityfx = new ParticleBreakingMC(mc.world, x, y, z, (Item) data[0], (int) data[1]);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_BREAKING_MOTION)
            {
                entityfx = new ParticleBreakingMC(mc.world, x, y, z, motionX, motionY, motionZ, (Item) data[0]);
            }
            else if (type == EnumParticleTypesMP.INFECTED_WATER_DRIP)
            {
                entityfx = new ParticleLiquidDrip(mc.world, x, y, z, 0.95F, 0.4F, 0.3F, 0.6F, false);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZE_WATER_DRIP)
            {
                entityfx = new ParticleLiquidDrip(mc.world, x, y, z, 0.6F, 0.2F, 0.8F, 0.6F, false);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZE_LAVA_DRIP)
            {
                entityfx = new ParticleLiquidDrip(mc.world, x, y, z, 0.6F, 0.2F, 0.8F, 1.0F, true);
            }
            else if (type == EnumParticleTypesMP.CRYSTALLIZE_LAVA)
            {
                entityfx = new ParticleLavaMC(mc.world, x, y, z, "crystallize_lava");
            }
            else if (type == EnumParticleTypesMP.MC_SMOKE_LARGE)
            {
                mc.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, 0.0D, 0.0D, 0.0D);
            }
            else if (type == EnumParticleTypesMP.NUCLEAR_WASTE_DRIP)
            {
                entityfx = new ParticleLiquidDrip(mc.world, x, y, z, 0.4F, 0.8F, 0.1F, 1.0F, true);
            }
            else if (type == EnumParticleTypesMP.PURIFY_WATER_DRIP)
            {
                entityfx = new ParticleLiquidDrip(mc.world, x, y, z, 0.45F, 0.8F, 1.0F, 0.6F, false);
            }
            else if (type == EnumParticleTypesMP.KOENTUS_METEOR_SMOKE)
            {
                entityfx = new ParticleKoentusMeteor(mc.world, x, y, z, motionX, motionY, motionZ);
            }
            else if (type == EnumParticleTypesMP.CUSTOM_FALLING_DUST)
            {
                entityfx = new ParticleFallingDustMP(mc.world, x, y, z, (int) data[0]);
            }

            if (entityfx != null)
            {
                mc.effectRenderer.addEffect(entityfx);
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
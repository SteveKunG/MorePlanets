/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.event;

import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.FMLInjectionData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent.CelestialRingRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.inventory.AccessInventoryGC;
import micdoodle8.mods.galacticraft.api.inventory.IInventoryGC;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerHandler.ThermalArmorEvent;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import stevekung.mods.moreplanets.asteroids.darkasteroids.blocks.DarkAsteroidsBlocks;
import stevekung.mods.moreplanets.asteroids.darkasteroids.dimension.WorldProviderDarkAsteroids;
import stevekung.mods.moreplanets.asteroids.darkasteroids.entities.EntityDarkAsteroid;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.achievement.AchievementsMP;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.entities.EntityEvolvedWitch;
import stevekung.mods.moreplanets.core.entities.IEntityLivingPlanet;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.core.player.MPPlayerStats;
import stevekung.mods.moreplanets.core.todo.PacketUpdateJetpack;
import stevekung.mods.moreplanets.core.util.JsonUtils;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.core.util.VersionChecker;
import stevekung.mods.moreplanets.core.world.ILightningStorm;
import stevekung.mods.moreplanets.core.world.IMeteorType;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;
import stevekung.mods.moreplanets.moons.io.items.IoItems;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteor;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.items.tools.DionaToolsItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.items.ItemCandyBow;
import stevekung.mods.moreplanets.planets.fronos.items.tools.FronosToolsItems;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.entities.EntityIceCrystalMeteor;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.armor.KapteynBArmorItems;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityPolongniusMeteor;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.dimension.WorldProviderSiriusB;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.items.ItemJetpack;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class MorePlanetEvents
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerTick(PlayerTickEvent event)
    {
        String URL = "https://minecraft.curseforge.com/projects/galacticraft-add-on-more-planets";
        JsonUtils json = new JsonUtils();
        EntityPlayer player = event.player;
        String versionMC = (String) FMLInjectionData.data()[4];

        if (player != null)
        {
            // Credit to Jarbelar
            // 0 = OutOfDate, 1 = ShowDesc, 2 = NoConnection
            if (player.worldObj.isRemote)
            {
                if (ConfigManagerMP.enableVersionCheck)
                {
                    if (!MorePlanetsCore.STATUS_CHECK[2] && VersionChecker.INSTANCE.noConnection())
                    {
                        player.addChatMessage(json.text("Unable to check latest version, Please check your internet connection").setChatStyle(json.red()));
                        player.addChatMessage(json.text(VersionChecker.INSTANCE.getExceptionMessage()).setChatStyle(json.red()));
                        MorePlanetsCore.STATUS_CHECK[2] = true;
                        return;
                    }
                    if (!MorePlanetsCore.STATUS_CHECK[0] && !MorePlanetsCore.STATUS_CHECK[2] && VersionChecker.INSTANCE.isLatestVersion())
                    {
                        player.addChatMessage(json.text("New version of ").appendSibling(json.text("More Planets").setChatStyle(json.style().setColor(EnumChatFormatting.AQUA)).appendSibling(json.text(" is available ").setChatStyle(json.white()).appendSibling(json.text("v" + VersionChecker.INSTANCE.getLatestVersion().replace("[" + versionMC + "]=", "")).setChatStyle(json.style().setColor(EnumChatFormatting.GREEN)).appendSibling(json.text(" for ").setChatStyle(json.white()).appendSibling(json.text("MC-" + versionMC).setChatStyle(json.style().setColor(EnumChatFormatting.GOLD))))))));
                        player.addChatMessage(json.text("Download Link ").setChatStyle(json.style().setColor(EnumChatFormatting.YELLOW)).appendSibling(json.text("[CLICK HERE]").setChatStyle(json.style().setColor(EnumChatFormatting.BLUE).setChatHoverEvent(json.hover(HoverEvent.Action.SHOW_TEXT, json.text("Click Here!").setChatStyle(json.style().setColor(EnumChatFormatting.DARK_GREEN)))).setChatClickEvent(json.click(ClickEvent.Action.OPEN_URL, URL)))));
                        MorePlanetsCore.STATUS_CHECK[0] = true;
                    }
                }
                if (ConfigManagerMP.enableChangeLogInGame)
                {
                    if (!MorePlanetsCore.STATUS_CHECK[1] && !MorePlanetsCore.STATUS_CHECK[2])
                    {
                        for (String log : VersionChecker.INSTANCE.getChangeLog())
                        {
                            player.addChatMessage(json.text(log).setChatStyle(json.colorFromConfig("gray")));
                        }
                        player.addChatMessage(json.text("To read More Planets full change log. Use /mpchangelog command!").setChatStyle(json.colorFromConfig("gray").setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/mpchangelog"))));
                    }
                    MorePlanetsCore.STATUS_CHECK[1] = true;
                }
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event)
    {
        if (event.modID.equals(MorePlanetsCore.MOD_ID))
        {
            ConfigManagerMP.syncConfig(false);
        }
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayerMP && MPPlayerStats.get((EntityPlayerMP) event.entity) == null)
        {
            ((EntityPlayerMP)event.entity).registerExtendedProperties(MPPlayerStats.MP_PLAYER_PROPERTY, new MPPlayerStats());
        }
    }

    @SubscribeEvent
    public void onZombieSummonAid(SummonAidEvent event)
    {
        if (event.entity instanceof EntityInfectedZombie)
        {
            event.customSummonedAid = new EntityInfectedZombie(event.world);

            if (((EntityLivingBase) event.entity).getRNG().nextFloat() < ((EntityInfectedZombie) event.entity).getEntityAttribute(((EntityInfectedZombie) event.entity).getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void onThermalArmorEvent(ThermalArmorEvent event)
    {
        if (event.armorStack == null)
        {
            event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.REMOVE);
            return;
        }
        if (event.armorStack.getItem() == AsteroidsItems.thermalPadding && event.armorStack.getItemDamage() == event.armorIndex)
        {
            event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
            return;
        }
        if (event.armorStack.getItem() == MPItems.tier_2_thermal_padding && event.armorStack.getItemDamage() == event.armorIndex)
        {
            event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
            return;
        }
        if (event.armorStack.getItem() == MPItems.tier_3_thermal_padding && event.armorStack.getItemDamage() == event.armorIndex)
        {
            event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.ADD);
            return;
        }
        event.setArmorAddResult(ThermalArmorEvent.ArmorAddResult.NOTHING);
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
    {
        if (event.entityLiving.isPotionActive(MPPotions.icy_poison))
        {
            if (event.entityLiving.worldObj.isAirBlock((int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ))
            {
                if (KapteynBBlocks.icy_poison_crystal.canPlaceBlockAt(event.entityLiving.worldObj, (int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ))
                {
                    if (event.entityLiving.worldObj.rand.nextInt(50) == 0)
                    {
                        event.entityLiving.worldObj.setBlock((int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ, KapteynBBlocks.icy_poison_crystal);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingJumpEvent event)
    {
        if (event.entityLiving.isPotionActive(MPPotions.electro_magnetic_pulse))
        {
            event.entityLiving.motionY -= 1.0D;
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onBodyRender(CelestialBodyRenderEvent.Pre renderEvent)
    {
        if (renderEvent.celestialBody == MorePlanetsCore.darkAsteroids)
        {
            GL11.glRotatef(Sys.getTime() / 10.0F % 360, 0, 0, 1);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRingRender(CelestialRingRenderEvent.Pre event)
    {
        if (event.celestialBody == MorePlanetsCore.darkAsteroids)
        {
            this.renderRing(event, event.celestialBody, 0.1F, 0.1F, 0.1F, 1.0F, 0.5F);
        }
        else if (event.celestialBody == MorePlanetsCore.siriusB)
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(ClientTickEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.theWorld;
        EntityClientPlayerMP player = mc.thePlayer;

        if (event.phase == Phase.START)
        {
            if (player != null && world != null)
            {
                if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == VenusItems.jetpack)
                {
                    ItemJetpack jetpack = (ItemJetpack) player.inventory.armorItemInSlot(2).getItem();
                    ItemStack itemStack = player.inventory.armorItemInSlot(2);
                    boolean flag = player.capabilities.isCreativeMode;

                    if (!flag && jetpack.getElectricityStored(itemStack) != 0.0F)
                    {
                        boolean pressed = false;
                        float power = 0.0F;

                        if (mc.gameSettings.keyBindJump.getIsKeyPressed())
                        {
                            jetpack.setJetpackKeyDown(true);
                            player.motionY = 0.5D;
                            world.spawnParticle("largesmoke", player.posX, player.posY - 1, player.posZ, 0, -1.0D, 0);
                            power = 1000.0F;
                            pressed = true;
                        }
                        else if (!mc.gameSettings.keyBindJump.getIsKeyPressed())
                        {
                            jetpack.setJetpackKeyDown(false);
                        }

                        if (!player.onGround)
                        {
                            if (mc.gameSettings.keyBindSneak.getIsKeyPressed())
                            {
                                player.motionY *= 0.85D;
                                world.spawnParticle("largesmoke", player.posX, player.posY - 1, player.posZ, 0, -2.0D, 0);
                                jetpack.setJetpackKeySneak(true);
                                pressed = true;
                                power = 500.0F;
                            }
                            else if (!mc.gameSettings.keyBindSneak.getIsKeyPressed())
                            {
                                jetpack.setJetpackKeySneak(false);
                            }
                        }
                        else
                        {
                            jetpack.setJetpackKeySneak(false);
                        }
                        if (pressed)
                        {
                            MorePlanetsCore.packetPipeline.sendToServer(new PacketUpdateJetpack(power));
                        }
                    }
                    if (flag)
                    {
                        if (mc.gameSettings.keyBindJump.getIsKeyPressed())
                        {
                            player.motionY = 0.5D;
                            world.spawnParticle("largesmoke", player.posX, player.posY - 1, player.posZ, 0, -1.0D, 0);
                        }
                        if (!player.onGround)
                        {
                            if (mc.gameSettings.keyBindSneak.getIsKeyPressed())
                            {
                                player.motionY *= 0.85D;
                                world.spawnParticle("largesmoke", player.posX, player.posY - 1, player.posZ, 0, -2.0D, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event)
    {
        World world = event.entityLiving.worldObj;

        if (event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entityLiving;

            if (player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() == VenusItems.jetpack)
            {
                event.distance = 0.0F;
                event.setCanceled(true);
                return;
            }
            if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == PlutoItems.gravity_boots)
            {
                if (world.provider instanceof WorldProviderKoentus || world.provider instanceof WorldProviderPluto)
                {
                    event.distance *= 0.1F;
                }
                else if (world.provider instanceof WorldProviderSiriusB)
                {
                    event.distance *= 0.45F;
                }
                event.distance *= 0.4F;
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onFOVUpdate(FOVUpdateEvent event)
    {
        if (event.entity.isUsingItem() && event.entity.getItemInUse().getItem() instanceof ItemCandyBow)
        {
            int i = event.entity.getItemInUseDuration();
            float f = i / 20.0F;

            if (f > 1.0F)
            {
                f = 1.0F;
            }
            else
            {
                f *= f;
            }
            event.newfov *= 1.0F - f * 0.15F;
        }
    }

    @SubscribeEvent
    public void onRenderPlanet(CelestialBodyRenderEvent.Post event)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.currentScreen instanceof GuiCelestialSelection)
        {
            if (event.celestialBody == MorePlanetsCore.polongnius)
            {
                mc.renderEngine.bindTexture(new ResourceLocation("polongnius:textures/gui/celestialbodies/polongnius_ring.png"));
                float size = GuiCelestialSelection.getWidthForCelestialBodyStatic(event.celestialBody) / 6.0F;
                ((GuiCelestialSelection)mc.currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 15.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 30, 7);
            }
        }
    }

    @SubscribeEvent
    public void onPickupItem(ItemPickupEvent event)
    {
        ItemStack itemStack = event.pickedUp.getEntityItem();
        Item item = itemStack.getItem();
        Block block = Block.getBlockFromItem(item);
        int meta = itemStack.getItemDamage();

        if (block == KoentusBlocks.crystal_log || block == NibiruBlocks.nibiru_log || block == FronosBlocks.fronos_log || block == EuropaBlocks.europa_log || block == DarkAsteroidsBlocks.alien_log)
        {
            event.player.triggerAchievement(AchievementList.mineWood);
        }
        if (item == Item.getItemFromBlock(DionaBlocks.diona_block) && (meta == 4 || meta == 5))
        {
            event.player.triggerAchievement(AchievementsMP.mineDionaOre);
        }
    }

    @SubscribeEvent
    public void onChangeDimension(PlayerChangedDimensionEvent event)
    {
        if (event.toDim == ConfigManagerMP.idDimensionDiona)
        {
            event.player.triggerAchievement(AchievementsMP.reachDiona);
        }
        else if (event.toDim == ConfigManagerMP.idDimensionJupiter)
        {
            event.player.addChatMessage(new ChatComponentTranslation("Used IO Block for temporarily dimension -.-"));
        }
    }

    @SubscribeEvent
    public void onCraftItem(ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();

        if (item == DionaToolsItems.quontonium_pickaxe || item == DionaToolsItems.fronisium_pickaxe)
        {
            event.player.triggerAchievement(AchievementsMP.getSpacePick);
        }
        if (item == DionaItems.laser_gun)
        {
            event.player.triggerAchievement(AchievementsMP.laserGun);
        }
        if (item == FronosToolsItems.fronos_rock_pickaxe)
        {
            event.player.triggerAchievement(AchievementList.buildBetterPickaxe);
        }
    }

    @SubscribeEvent
    public void onUseBonemeal(BonemealEvent event)
    {
        World world = event.world;
        Random rand = world.rand;
        int x = event.x;
        int z = event.z;

        this.glowSapling(event, world, NibiruBlocks.nibiru_sapling);
        this.glowSapling(event, world, KoentusBlocks.crystal_sapling);
        this.glowSapling(event, world, FronosBlocks.fronos_sapling);
        this.glowSapling(event, world, DarkAsteroidsBlocks.alien_sapling);

        this.glowTallGrass(event, rand, x, z, FronosBlocks.fronos_grass, FronosBlocks.fronos_tall_grass, 3, 0, true);
        this.glowTallGrass(event, rand, x, z, FronosBlocks.pink_grass, FronosBlocks.fronos_tall_grass, 3, 3, true);
        this.glowTallGrass(event, rand, x, z, FronosBlocks.purple_grass, FronosBlocks.fronos_tall_grass, 3, 6, true);
        this.glowTallGrass(event, rand, x, z, FronosBlocks.plains_grass, FronosBlocks.fronos_tall_grass, 3, 9, true);
        this.glowTallGrass(event, rand, x, z, FronosBlocks.golden_grass, FronosBlocks.fronos_tall_grass, 3, 12, true);
        this.glowTallGrass(event, rand, x, z, FronosBlocks.frosted_cake, FronosBlocks.candy_flower, 8, 0, true);

        if (event.block == FronosBlocks.glass_gem_corn1)
        {
            int y = event.y;

            if (world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && world.getBlock(x, y + 2, z).isAir(world, x, y + 2, z))
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (rand.nextInt(3) == 0)
                    {
                        event.world.setBlock(x, y, z, FronosBlocks.glass_gem_corn1, 1, 2);
                        event.world.setBlock(x, y + 1, z, FronosBlocks.glass_gem_corn2, rand.nextInt(3) + 1, 2);
                        event.world.setBlock(x, y + 2, z, FronosBlocks.glass_gem_corn3, 0, 2);
                    }
                }
            }
        }
        else if (event.block == FronosBlocks.glass_gem_corn2)
        {
            int y = event.y;

            if (event.world.getBlock(x, y + 1, z).isAir(event.world, x, y + 1, z))
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (event.block == FronosBlocks.glass_gem_corn2 && event.world.getBlockMetadata(x, y, z) == 0)
                    {
                        if (rand.nextInt(2) == 0)
                        {
                            event.world.setBlock(x, y, z, FronosBlocks.glass_gem_corn2, 1, 2);
                            event.world.setBlock(x, y + 1, z, FronosBlocks.glass_gem_corn3, 0, 2);
                        }
                    }
                }
            }
            if (event.block == FronosBlocks.glass_gem_corn2 && event.world.getBlockMetadata(x, y, z) == 1)
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        event.world.setBlock(x, y, z, FronosBlocks.glass_gem_corn2, rand.nextInt(2) + 1, 2);
                    }
                }
            }
            if (event.block == FronosBlocks.glass_gem_corn2 && event.world.getBlockMetadata(x, y, z) == 2)
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        event.world.setBlock(x, y, z, FronosBlocks.glass_gem_corn2, 3, 2);
                    }
                }
            }
        }
        else if (event.block == FronosBlocks.dandelion)
        {
            int y = event.y;

            if (event.block == FronosBlocks.dandelion && event.world.getBlockMetadata(x, y, z) == 0)
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        event.world.setBlock(x, y, z, FronosBlocks.dandelion, 3, 2);
                    }
                }
            }
            if (event.block == FronosBlocks.dandelion && event.world.getBlockMetadata(x, y, z) == 1)
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        event.world.setBlock(x, y, z, FronosBlocks.dandelion, 4, 2);
                    }
                }
            }
            if (event.block == FronosBlocks.dandelion && event.world.getBlockMetadata(x, y, z) == 2)
            {
                event.setResult(Result.ALLOW);

                if (!event.world.isRemote)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        event.world.setBlock(x, y, z, FronosBlocks.dandelion, 5, 2);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        EntityLivingBase living = event.entityLiving;
        World world = living.worldObj;

        if (living instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)living;

            if (ConfigManagerMP.enableHumanFecesSpawningOnMars)
            {
                this.spawnFeces(world, player);
            }
            this.spawnDarkAsteroids(world, player);
            this.removeIcyPoison(player);

            if (world.provider instanceof IMeteorType)
            {
                this.spawnMeteor(world, player, (IMeteorType)world.provider);
            }
            else if (world.provider instanceof ILightningStorm)
            {
                this.spawnLightningBolt(world, player);
            }
            if ((player.isPotionActive(MPPotions.infected_gas.id) || player.isPotionActive(MPPotions.icy_poison.id)) && MorePlanetEvents.getTier3ThermalArmor(player))
            {
                player.removePotionEffect(MPPotions.infected_gas.id);
                player.removePotionEffect(MPPotions.icy_poison.id);
            }
        }
        this.doSiriusFire(world, living);
        this.doInfectedGasForEntity(world, living);
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        World world = event.world;
        MovingObjectPosition pos = event.target;
        Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

        MorePlanetEvents.registerBucket(event, world, pos, KapteynBBlocks.frozen_water, 0, new ItemStack(KapteynBItems.frozen_water_bucket), false);
        MorePlanetEvents.registerBucket(event, world, pos, PolongniusBlocks.cheese_of_milk, 0, new ItemStack(PolongniusItems.cheese_of_milk_bucket), false);
        MorePlanetEvents.registerBucket(event, world, pos, FronosBlocks.coconut_milk, 0, new ItemStack(FronosItems.fronos_bucket, 1, 0), false);
        MorePlanetEvents.registerBucket(event, world, pos, FronosBlocks.mineral_water, 0, new ItemStack(FronosItems.fronos_bucket, 1, 1), false);
        MorePlanetEvents.registerBucket(event, world, pos, FronosBlocks.ovantine, 0, new ItemStack(FronosItems.fronos_bucket, 1, 2), false);
        MorePlanetEvents.registerBucket(event, world, pos, FronosBlocks.tea, 0, new ItemStack(FronosItems.fronos_bucket, 1, 3), false);
        MorePlanetEvents.registerBucket(event, world, pos, FronosBlocks.caramel, 3, new ItemStack(FronosItems.fronos_bucket, 1, 4), false);
        MorePlanetEvents.registerBucket(event, world, pos, SiriusBBlocks.sirius_lava, 0, new ItemStack(SiriusBItems.sirius_lava_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, IoBlocks.io_lava, 0, new ItemStack(IoItems.io_lava_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, IoBlocks.liquid_red_sulfur, 0, new ItemStack(IoItems.liquid_red_sulfur_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, IoBlocks.liquid_yellow_sulfur, 0, new ItemStack(IoItems.liquid_yellow_sulfur_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, IoBlocks.liquid_orange_sulfur, 0, new ItemStack(IoItems.liquid_orange_sulfur_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, IoBlocks.liquid_brown_sulfur, 0, new ItemStack(IoItems.liquid_brown_sulfur_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, IoBlocks.io_black_lava, 0, new ItemStack(IoItems.io_black_lava_bucket), true);
        MorePlanetEvents.registerBucket(event, world, pos, MercuryBlocks.dirty_water, 0, new ItemStack(MercuryItems.dirty_water_bucket), false);
        MorePlanetEvents.registerBucket(event, world, pos, EuropaBlocks.europa_water, 0, new ItemStack(EuropaItems.europa_water_bucket), false);

        if (block == PlutoBlocks.liquid_methane)
        {
            if (event.current.getItem() == Items.bucket)
            {
                event.setCanceled(true);
            }
            else
            {
                //world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
                //event.result = new ItemStack(IoItems.liquid_brown_sulfur_bucket);
                //event.setResult(Result.ALLOW);
            }
        }
        else if (block == PlutoBlocks.liquid_nitrogen)
        {
            if (event.current.getItem() == Items.bucket)
            {
                event.setCanceled(true);
            }
            else
            {
                //world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
                //event.result = new ItemStack(IoItems.liquid_brown_sulfur_bucket);
                //event.setResult(Result.ALLOW);
            }
        }
    }

    @SubscribeEvent
    public void interactEntityWithDye(EntityInteractEvent event)
    {
        ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
        Entity entity = event.target;

        if (itemstack != null)
        {
            int meta = itemstack.getItemDamage() & 15;

            if (itemstack.getItem() == Items.dye && meta >= 0)
            {
                int color = BlockColored.func_150032_b(meta);

                if (entity instanceof EntityGrappy)
                {
                    EntityGrappy grappy = (EntityGrappy)entity;

                    if (!grappy.getSheared() && grappy.getFleeceColor() != color)
                    {
                        grappy.setFleeceColor(color);

                        if (!event.entityPlayer.capabilities.isCreativeMode)
                        {
                            --itemstack.stackSize;
                        }
                    }
                    event.setResult(Result.ALLOW);
                }
            }
        }
    }

    private void spawnFeces(World world, EntityPlayerMP player)
    {
        if (world.provider instanceof WorldProviderMars)
        {
            if (world.getWorldTime() == 7000L)
            {
                EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(MPItems.feces));
                item.delayBeforeCanPickup = 10;
                world.playSoundAtEntity(player, "mob.chicken.plop", 0.75F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 0.6F);
                world.spawnEntityInWorld(item);
            }
            if (world.getWorldTime() == 12000L)
            {
                EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(MPItems.feces));
                item.delayBeforeCanPickup = 10;
                world.playSoundAtEntity(player, "mob.chicken.plop", 0.75F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 0.6F);
                world.spawnEntityInWorld(item);
            }
        }
    }

    private void spawnMeteor(World world, EntityPlayerMP player, IMeteorType type)
    {
        Entity meteor = null;

        if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
        {
            if (((IMeteorType)world.provider).getMeteorSpawnFrequency() > 0.0D)
            {
                int f = (int) (((IMeteorType)world.provider).getMeteorSpawnFrequency() * 1000D);

                if (world.rand.nextInt(f) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        int x, y, z;
                        double motX, motZ;
                        x = world.rand.nextInt(20) - 10;
                        y = world.rand.nextInt(20) + 200;
                        z = world.rand.nextInt(20) - 10;
                        motX = world.rand.nextDouble() * 5;
                        motZ = world.rand.nextDouble() * 5;

                        switch (type.getMeteorEventType())
                        {
                        case 0:
                            meteor = new EntityPolongniusMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);
                            break;
                        case 1:
                            meteor = new EntityKoentusMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);
                            break;
                        case 2:
                            meteor = new EntityIceCrystalMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);
                            break;
                        }

                        if (!world.isRemote)
                        {
                            world.spawnEntityInWorld(meteor);
                            MPLog.debug("Spawn %s at %s %s %s", meteor.getClass().getSimpleName(), (int)meteor.posX, (int)meteor.posY, (int)meteor.posZ);
                        }
                    }
                }
                if (world.rand.nextInt(f * 3) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        int x, y, z;
                        double motX, motZ;
                        x = world.rand.nextInt(20) - 10;
                        y = world.rand.nextInt(20) + 200;
                        z = world.rand.nextInt(20) - 10;
                        motX = world.rand.nextDouble() * 5;
                        motZ = world.rand.nextDouble() * 5;

                        switch (type.getMeteorEventType())
                        {
                        case 0:
                            meteor = new EntityPolongniusMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);
                            break;
                        case 1:
                            meteor = new EntityKoentusMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);
                            break;
                        case 2:
                            meteor = new EntityIceCrystalMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);
                            break;
                        }

                        if (!world.isRemote)
                        {
                            world.spawnEntityInWorld(meteor);
                            MPLog.debug("Spawn %s at %s %s %s", meteor.getClass().getSimpleName(), (int)meteor.posX, (int)meteor.posY, (int)meteor.posZ);
                        }
                    }
                }
            }
        }
    }

    private void spawnLightningBolt(World world, EntityPlayerMP player)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
        {
            if (((ILightningStorm)world.provider).getLightningStormFrequency() > 0.0D)
            {
                int f = (int)(((ILightningStorm)world.provider).getLightningStormFrequency() * 1000D);

                if (world.rand.nextInt(f) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        double x = player.posX + world.rand.nextInt(25) - 5;
                        double y = player.posY + world.rand.nextInt(10);
                        double z = player.posZ + world.rand.nextInt(25) - 5;
                        EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);

                        if (!world.isRemote)
                        {
                            world.spawnEntityInWorld(lightning);
                            MPLog.debug("Spawn Bolt at %s %s %s", (int)lightning.posX, (int)lightning.posY, (int)lightning.posZ);
                        }
                    }
                }
                if (world.rand.nextInt(f * 3) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        double x = player.posX + world.rand.nextInt(25) - 5;
                        double y = player.posY + world.rand.nextInt(10);
                        double z = player.posZ + world.rand.nextInt(25) - 5;
                        EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);

                        if (!world.isRemote)
                        {
                            for (int i = 0; i < 3; i++)
                            {
                                world.spawnEntityInWorld(lightning);
                                MPLog.debug("Spawn Bolt at %s %s %s", (int)lightning.posX, (int)lightning.posY, (int)lightning.posZ);
                            }
                        }
                    }
                }
            }
        }
    }

    private void spawnDarkAsteroids(World world, EntityPlayerMP player)
    {
        if (!world.isRemote && world.provider instanceof WorldProviderDarkAsteroids)
        {
            int f = 50;

            if (world.rand.nextInt(f) == 0 && player.posY < 260D)
            {
                EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                {
                    double x, y, z;
                    double motX, motY, motZ;
                    double r = world.rand.nextInt(60)+30D;
                    double theta = Math.PI * 2.0 * world.rand.nextDouble();
                    x = player.posX + Math.cos(theta) * r;
                    y = player.posY + world.rand.nextInt(5);
                    z = player.posZ + Math.sin(theta) * r;
                    motX = (player.posX - x + (world.rand.nextDouble() - 0.5) * 40) / 400.0F;
                    motY = (world.rand.nextDouble() - 0.5) * 0.4;
                    motZ = (player.posZ - z + (world.rand.nextDouble() - 0.5) * 40) / 400.0F;

                    EntityDarkAsteroid smallAsteroid = new EntityDarkAsteroid(world);
                    smallAsteroid.setPosition(x, y, z);
                    smallAsteroid.motionX = motX;
                    smallAsteroid.motionY = motY;
                    smallAsteroid.motionZ = motZ;
                    smallAsteroid.spinYaw = world.rand.nextFloat() * 4;
                    smallAsteroid.spinPitch = world.rand.nextFloat() * 2;
                    world.spawnEntityInWorld(smallAsteroid);
                    MPLog.debug("Spawn Dark Asteroid at %s %s %s", (int)smallAsteroid.posX, (int)smallAsteroid.posY, (int)smallAsteroid.posZ);
                }
            }
        }
    }

    private void doInfectedGasForEntity(World world, EntityLivingBase living)
    {
        if (world.provider instanceof WorldProviderNibiru && living.posY <= 1000)
        {
            if (!(living instanceof EntityPlayer))
            {
                if (living.ticksExisted % 100 == 0 && (!(living instanceof IEntityLivingPlanet) || !(world.provider.dimensionId == ((IEntityLivingPlanet)living).canLivingInDimension().dimID)) && !(living.getClass() == EntityEvolvedZombie.class || living.getClass() == EntityEvolvedSpider.class || living.getClass() == EntityEvolvedSkeleton.class || living.getClass() == EntityEvolvedCreeper.class || living.getClass() == EntityEvolvedEnderman.class || living.getClass() == EntityEvolvedWitch.class))
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 80));
                }
            }
            else if (living instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP)living;

                if (player.isPotionActive(MPPotions.infected_gas.id))
                {
                    if (player.ticksExisted % 2000 == 0 && !player.capabilities.isCreativeMode)
                    {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("message.warning.infected.gas")));
                    }
                }
                if (ConfigManagerMP.disableInfectedGas)
                {
                    if (player.isPotionActive(MPPotions.infected_gas.id) && MorePlanetEvents.getTier2ThermalArmor(player))
                    {
                        player.removePotionEffect(MPPotions.infected_gas.id);
                    }
                }
                else
                {
                    if (player.ticksExisted % 200 == 0 && !player.capabilities.isCreativeMode && !(MorePlanetEvents.getTier2ThermalArmor(player) || OxygenUtil.inOxygenBubble(world, player.posX, player.posY, player.posZ) || OxygenUtil.isAABBInBreathableAirBlock(player)))
                    {
                        player.addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 80));
                    }
                    else if (MorePlanetEvents.getTier2ThermalArmor(player) || OxygenUtil.inOxygenBubble(world, player.posX, player.posY, player.posZ) || OxygenUtil.isAABBInBreathableAirBlock(player))
                    {
                        player.removePotionEffect(MPPotions.infected_gas.id);
                    }
                }
            }
        }
    }

    private void doSiriusFire(World world, EntityLivingBase living)
    {
        if (world.provider instanceof WorldProviderSiriusB)
        {
            if (!(living instanceof EntityPlayer))
            {
                if (living.ticksExisted % 100 == 0 && (!(living instanceof IEntityLivingPlanet) || !(world.provider.dimensionId == ((IEntityLivingPlanet)living).canLivingInDimension().dimID)))
                {
                    living.setFire(8);
                }
            }
        }
    }

    private void removeIcyPoison(EntityPlayerMP player)
    {
        InventoryPlayer inventory = player.inventory;
        boolean armor1 = inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == KapteynBArmorItems.ice_crystal_boots;
        boolean armor2 = inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == KapteynBArmorItems.ice_crystal_leggings;
        boolean armor3 = inventory.armorInventory[2] != null && inventory.armorInventory[2].getItem() == KapteynBArmorItems.ice_crystal_chestplate;
        boolean armor4 = inventory.armorInventory[3] != null && inventory.armorInventory[3].getItem() == KapteynBArmorItems.ice_crystal_helmet || inventory.armorInventory[3] != null && inventory.armorInventory[3].getItem() == KapteynBArmorItems.breathable_ice_crystal_helmet;
        boolean fullArmor = armor1 && armor2 && armor3 && armor4;

        if (fullArmor && player.isPotionActive(MPPotions.icy_poison.id))
        {
            player.removePotionEffect(MPPotions.icy_poison.id);
        }
    }

    private void glowSapling(BonemealEvent event, World world, Block block)
    {
        if (event.block == block)
        {
            event.setResult(Result.ALLOW);

            if (!world.isRemote)
            {
                if (world.rand.nextFloat() < 0.45D)
                {
                    ((BlockSapling)block).func_149878_d(world, event.x, event.y, event.z, world.rand);
                }
            }
        }
    }

    private void glowTallGrass(BonemealEvent event, Random rand, int x, int z, Block grass, Block tallGrass, int meta, int randMeta, boolean useRand)
    {
        if (event.block == grass)
        {
            int y = event.y + 1;

            for (int i1 = 0; i1 < 128; ++i1)
            {
                for (int i2 = 0; i2 < i1 / 16; ++i2)
                {
                    x += event.world.rand.nextInt(3) - 1;
                    y += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
                    z += event.world.rand.nextInt(3) - 1;
                }
                if (event.world.getBlock(x, y, z).isAir(event.world, x, y, z))
                {
                    if (!useRand)
                    {
                        if (tallGrass.canReplace(event.world, x, y, z, 0, new ItemStack(tallGrass, 1, meta)))
                        {
                            event.setResult(Result.ALLOW);

                            if (!event.world.isRemote)
                            {
                                event.world.setBlock(x, y, z, tallGrass, meta, 2);
                            }
                        }
                    }
                    else
                    {
                        if (tallGrass.canReplace(event.world, x, y, z, 0, new ItemStack(tallGrass, 1, rand.nextInt(meta) + randMeta)))
                        {
                            event.setResult(Result.ALLOW);

                            if (!event.world.isRemote)
                            {
                                event.world.setBlock(x, y, z, tallGrass, rand.nextInt(meta) + randMeta, 2);
                            }
                        }
                    }
                }
            }
        }
    }

    //Credit to AmunRa mod :) (Why Galacticraft can do this without map pos? -.-)
    private void renderRing(CelestialRingRenderEvent.Pre event, CelestialBody celestial, float r, float g, float b, float outerAlpha, float innerAlpha)
    {
        Vector3f mapPos = event.parentOffset;
        float xOffset = mapPos.x;
        float yOffset = mapPos.y;

        if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection)
        {
            GL11.glColor4f(r, g, b, outerAlpha);
        }

        event.setCanceled(true);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        float theta = (float) (2 * Math.PI / 90);
        float cos = (float) Math.cos(theta);
        float sin = (float) Math.sin(theta);
        float min = 0.0F;
        float max = 0.0F;

        if (celestial instanceof Planet)
        {
            min = 72.0F;
            max = 78.0F;
        }
        else if (celestial instanceof Moon)
        {
            max = 1 / 1.5F;
            min = 1 / 1.9F;
        }

        float x = max * celestial.getRelativeDistanceFromCenter().unScaledDistance;
        float y = 0;
        float temp;

        for (int i = 0; i < 90; i++)
        {
            GL11.glVertex2f(x + xOffset, y + yOffset);
            temp = x;
            x = cos * x - sin * y;
            y = sin * temp + cos * y;
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        x = min * celestial.getRelativeDistanceFromCenter().unScaledDistance;
        y = 0;

        for (int i = 0; i < 90; i++)
        {
            GL11.glVertex2f(x + xOffset, y + yOffset);
            temp = x;
            x = cos * x - sin * y;
            y = sin * temp + cos * y;
        }

        GL11.glEnd();
        GL11.glColor4f(r, g, b, innerAlpha);// Inner ring
        GL11.glBegin(GL11.GL_QUADS);
        x = min * celestial.getRelativeDistanceFromCenter().unScaledDistance;
        y = 0;
        float x2 = max * celestial.getRelativeDistanceFromCenter().unScaledDistance;
        float y2 = 0;

        for (int i = 0; i < 90; i++)
        {
            GL11.glVertex2f(x2 + xOffset, y2 + yOffset);
            GL11.glVertex2f(x + xOffset, y + yOffset);
            temp = x;
            x = cos * x - sin * y;
            y = sin * temp + cos * y;
            temp = x2;
            x2 = cos * x2 - sin * y2;
            y2 = sin * temp + cos * y2;
            GL11.glVertex2f(x + xOffset, y + yOffset);
            GL11.glVertex2f(x2 + xOffset, y2 + yOffset);
        }
        GL11.glEnd();
    }

    public static void addInfectedGas(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;

            if (ConfigManagerMP.disableInfectedGas && !MorePlanetEvents.getTier2ThermalArmor(playerMP))
            {
                playerMP.addPotionEffect(new PotionEffect(MPPotions.infected_gas.id, 80));
            }
        }
    }

    public static boolean getTier2ThermalArmor(EntityPlayerMP player)
    {
        IInventoryGC inv = AccessInventoryGC.getGCInventoryForPlayer(player);
        boolean armor1 = inv.getStackInSlot(6) != null && inv.getStackInSlot(6).getItem() == MPItems.tier_2_thermal_padding && inv.getStackInSlot(6).getItemDamage() == 0;
        boolean armor2 = inv.getStackInSlot(7) != null && inv.getStackInSlot(7).getItem() == MPItems.tier_2_thermal_padding && inv.getStackInSlot(7).getItemDamage() == 1;
        boolean armor3 = inv.getStackInSlot(8) != null && inv.getStackInSlot(8).getItem() == MPItems.tier_2_thermal_padding && inv.getStackInSlot(8).getItemDamage() == 2;
        boolean armor4 = inv.getStackInSlot(9) != null && inv.getStackInSlot(9).getItem() == MPItems.tier_2_thermal_padding && inv.getStackInSlot(9).getItemDamage() == 3;
        return armor1 && armor2 && armor3 && armor4;
    }

    public static boolean getTier3ThermalArmor(EntityPlayerMP player)
    {
        IInventoryGC inv = AccessInventoryGC.getGCInventoryForPlayer(player);
        boolean armor1 = inv.getStackInSlot(6) != null && inv.getStackInSlot(6).getItem() == MPItems.tier_3_thermal_padding && inv.getStackInSlot(6).getItemDamage() == 0;
        boolean armor2 = inv.getStackInSlot(7) != null && inv.getStackInSlot(7).getItem() == MPItems.tier_3_thermal_padding && inv.getStackInSlot(7).getItemDamage() == 1;
        boolean armor3 = inv.getStackInSlot(8) != null && inv.getStackInSlot(8).getItem() == MPItems.tier_3_thermal_padding && inv.getStackInSlot(8).getItemDamage() == 2;
        boolean armor4 = inv.getStackInSlot(9) != null && inv.getStackInSlot(9).getItem() == MPItems.tier_3_thermal_padding && inv.getStackInSlot(9).getItemDamage() == 3;
        return armor1 && armor2 && armor3 && armor4;
    }

    public static boolean getIceCrystalArmor(EntityPlayerMP player)
    {
        InventoryPlayer inventory = player.inventory;
        boolean armor1 = inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == KapteynBArmorItems.ice_crystal_boots;
        boolean armor2 = inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == KapteynBArmorItems.ice_crystal_leggings;
        boolean armor3 = inventory.armorInventory[2] != null && inventory.armorInventory[2].getItem() == KapteynBArmorItems.ice_crystal_chestplate;
        boolean armor4 = inventory.armorInventory[3] != null && inventory.armorInventory[3].getItem() == KapteynBArmorItems.ice_crystal_helmet || inventory.armorInventory[3] != null && inventory.armorInventory[3].getItem() == KapteynBArmorItems.breathable_ice_crystal_helmet;
        return armor1 && armor2 && armor3 && armor4;
    }

    private static void registerBucket(FillBucketEvent event, World world, MovingObjectPosition pos, Block block, int meta, ItemStack itemStack, boolean cancelBucket)
    {
        if (world.getBlock(pos.blockX, pos.blockY, pos.blockZ) == block && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == meta)
        {
            if (cancelBucket)
            {
                if (event.current.getItem() == Items.bucket)
                {
                    event.setCanceled(true);
                }
            }
            else
            {
                world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
                event.result = itemStack;
                event.setResult(Result.ALLOW);
            }
        }
    }
}
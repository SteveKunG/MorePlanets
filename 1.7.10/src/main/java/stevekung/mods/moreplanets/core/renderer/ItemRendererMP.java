/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.renderer;

import micdoodle8.mods.galacticraft.core.client.render.item.ItemRendererKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import stevekung.mods.moreplanets.core.entities.models.ModelRocketNoFlagMP;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.core.renderer.items.ItemRendererTier2ThermalArmor;
import stevekung.mods.moreplanets.core.renderer.items.ItemRendererTier3ThermalArmor;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.moons.koentus.render.items.ItemRendererKoentusMeteorChunk;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.render.items.ItemRendererFlagMP;
import stevekung.mods.moreplanets.planets.diona.render.items.ItemRendererTier4Rocket;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.models.items.ModelEmptyCup;
import stevekung.mods.moreplanets.planets.fronos.models.items.ModelFilledCup;
import stevekung.mods.moreplanets.planets.fronos.render.items.ItemRendererCandyBow;
import stevekung.mods.moreplanets.planets.fronos.render.items.ItemRendererCup;
import stevekung.mods.moreplanets.planets.fronos.render.items.ItemRendererTier7Rocket;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.render.items.ItemRendererIceCrystalMeteorChunk;
import stevekung.mods.moreplanets.planets.kapteynb.render.items.ItemRendererTier8Rocket;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.nibiru.render.items.ItemRendererTier6Rocket;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.polongnius.render.items.ItemRendererPolongniusMeteorChunk;
import stevekung.mods.moreplanets.planets.polongnius.render.items.ItemRendererTier5Rocket;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class ItemRendererMP
{
    private static IModelCustom rocketModel = AdvancedModelLoader.loadModel(new ResourceLocation("galacticraftasteroids:models/tier3rocket.obj"));

    public static void registerItemRenderers()
    {
        MinecraftForgeClient.registerItemRenderer(MPItems.flag, new ItemRendererFlagMP());
        MinecraftForgeClient.registerItemRenderer(DionaItems.diona_dungeon_key, new ItemRendererKey(new ResourceLocation("diona:textures/model/diona_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(PolongniusItems.polongnius_dungeon_key, new ItemRendererKey(new ResourceLocation("polongnius:textures/model/polongnius_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(NibiruItems.nibiru_dungeon_key, new ItemRendererKey(new ResourceLocation("nibiru:textures/model/nibiru_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(DionaItems.tier4_rocket, new ItemRendererTier4Rocket(new ModelRocketNoFlagMP()));
        MinecraftForgeClient.registerItemRenderer(FronosItems.fronos_dungeon_key, new ItemRendererKey(new ResourceLocation("fronos:textures/model/fronos_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(PolongniusItems.tier5_rocket, new ItemRendererTier5Rocket(new ModelRocketNoFlagMP()));
        MinecraftForgeClient.registerItemRenderer(KapteynBItems.kapteyn_b_dungeon_key, new ItemRendererKey(new ResourceLocation("kapteynb:textures/model/kapteyn_b_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(NibiruItems.tier6_rocket, new ItemRendererTier6Rocket(new ModelRocketNoFlagMP()));
        MinecraftForgeClient.registerItemRenderer(FronosItems.cup, new ItemRendererCup(new ModelEmptyCup(), new ModelFilledCup()));
        MinecraftForgeClient.registerItemRenderer(KoentusItems.koentus_meteor_chunk, new ItemRendererKoentusMeteorChunk());
        MinecraftForgeClient.registerItemRenderer(PolongniusItems.polongnius_meteor_chunk, new ItemRendererPolongniusMeteorChunk());
        MinecraftForgeClient.registerItemRenderer(SiriusBItems.sirius_b_dungeon_key, new ItemRendererKey(new ResourceLocation("siriusb:textures/model/sirius_b_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(FronosItems.tier7_rocket, new ItemRendererTier7Rocket(ItemRendererMP.rocketModel));
        MinecraftForgeClient.registerItemRenderer(KapteynBItems.tier8_rocket, new ItemRendererTier8Rocket(ItemRendererMP.rocketModel));
        MinecraftForgeClient.registerItemRenderer(FronosItems.candy_bow, new ItemRendererCandyBow());
        MinecraftForgeClient.registerItemRenderer(VenusItems.venus_dungeon_key, new ItemRendererKey(new ResourceLocation("venus:textures/model/venus_treasure_chest.png")));
        MinecraftForgeClient.registerItemRenderer(MPItems.tier_2_thermal_padding, new ItemRendererTier2ThermalArmor());
        MinecraftForgeClient.registerItemRenderer(MPItems.tier_3_thermal_padding, new ItemRendererTier3ThermalArmor());
        MinecraftForgeClient.registerItemRenderer(KapteynBItems.ice_crystal_meteor_chunk, new ItemRendererIceCrystalMeteorChunk());
    }
}
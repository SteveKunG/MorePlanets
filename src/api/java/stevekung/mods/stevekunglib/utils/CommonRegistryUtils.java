package stevekung.mods.stevekunglib.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import stevekung.mods.stevekunglib.utils.enums.EnumEntityTrackerType;

public class CommonRegistryUtils
{
    private static int ID;
    private final String resourcePath;

    public CommonRegistryUtils(@Nonnull String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    public void registerBlock(Block block)
    {
        this.registerBlock(block, ItemBlock::new);
    }

    public void registerBlock(Block block, @Nullable Function<Block, ItemBlock> itemBlock)
    {
        String name = block.getUnlocalizedName().substring(5);
        ForgeRegistries.BLOCKS.register(block.setRegistryName(name));

        if (itemBlock != null)
        {
            ForgeRegistries.ITEMS.register(itemBlock.apply(block).setRegistryName(block.getRegistryName()));
        }
    }

    public void registerItem(Item item)
    {
        String name = item.getUnlocalizedName().substring(5);
        ForgeRegistries.ITEMS.register(item.setRegistryName(name));
    }

    public void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
    }

    public void registerForgeBucket(Fluid fluid)
    {
        FluidRegistry.addBucketForFluid(fluid);
    }

    public void registerTileEntity(Class<? extends TileEntity> tile, String name)
    {
        GameRegistry.registerTileEntity(tile, new ResourceLocation(this.resourcePath + ":" + name));
    }

    public void registerPotion(Potion potion, String name)
    {
        ForgeRegistries.POTIONS.register(potion.setRegistryName(this.resourcePath + ":" + name));
    }

    public void registerBiome(Biome biome, String name)
    {
        ForgeRegistries.BIOMES.register(biome.setRegistryName(this.resourcePath + ":" + name));
    }

    public void registerBiomeType(Biome biome, @Nonnull BiomeDictionary.Type... biomeType)
    {
        BiomeDictionary.addTypes(biome, biomeType);

        if (biome.isMutation()) // should put to mutation after registered biomes
        {
            Biome.MUTATION_TO_BASE_ID_MAP.put(biome, Biome.getIdForBiome(ForgeRegistries.BIOMES.getValue(new ResourceLocation(this.resourcePath + ":" + biome.baseBiomeRegName))));
        }
    }

    public void registerEntity(Class<? extends Entity> entity, String name, int backgroundColor, int foregroundColor)
    {
        this.registerEntity(entity, name, backgroundColor, foregroundColor, EnumEntityTrackerType.NORMAL);
    }

    public void registerEntity(Class<? extends Entity> entity, String name, int backgroundColor, int foregroundColor, EnumEntityTrackerType type)
    {
        this.registerEntity(entity, name, backgroundColor, foregroundColor, type.getTrackingRange(), type.getUpdateFrequency());
    }

    public void registerEntity(Class<? extends Entity> entity, String name, int backgroundColor, int foregroundColor, int trackingRange, int updateFrequency)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(this.resourcePath + ":" + name), entity, this.resourcePath + "." + name, ID++, this.resourcePath, trackingRange, updateFrequency, true, backgroundColor, foregroundColor);
    }

    public void registerNonMobEntity(Class<? extends Entity> entity, String name)
    {
        this.registerNonMobEntity(entity, name, EnumEntityTrackerType.NORMAL);
    }

    public void registerNonMobEntity(Class<? extends Entity> entity, String name, EnumEntityTrackerType type)
    {
        this.registerNonMobEntity(entity, name, type.getTrackingRange(), type.getUpdateFrequency(), type.sendsVelocityUpdates());
    }

    public void registerNonMobEntity(Class<? extends Entity> entity, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(this.resourcePath + ":" + name), entity, this.resourcePath + "." + name, ID++, this.resourcePath, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public void registerEntityPlacement(Class<? extends Entity> entity, SpawnPlacementType type)
    {
        EntitySpawnPlacementRegistry.setPlacementType(entity, type);
    }

    public void registerCarriable(Block block)
    {
        EntityEnderman.setCarriable(block, true);
    }

    public void registerProjectileDispense(Item item, IBehaviorDispenseItem projectile)
    {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item, projectile);
    }

    public SoundEvent registerSound(String name)
    {
        ResourceLocation resource = new ResourceLocation(this.resourcePath + ":" + name);
        SoundEvent event = new SoundEvent(resource).setRegistryName(resource);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    public SoundEvent registerRecord(String name)
    {
        return this.registerSound("record." + name);
    }

    public ResourceLocation registerEntityLoot(String name)
    {
        return LootTableList.register(new ResourceLocation(this.resourcePath + ":entities/" + name));
    }

    public ResourceLocation registerEntitySubLoot(String folder, String name)
    {
        return LootTableList.register(new ResourceLocation(this.resourcePath + ":entities/" + folder + "/" + name));
    }

    public ResourceLocation registerChestLoot(String name)
    {
        return LootTableList.register(new ResourceLocation(this.resourcePath + ":chests/" + name));
    }

    public ResourceLocation registerGameplayLoot(String name)
    {
        return LootTableList.register(new ResourceLocation(this.resourcePath + ":gameplay/" + name));
    }

    public ResourceLocation registerFishingLoot(String name)
    {
        return LootTableList.register(new ResourceLocation(this.resourcePath + ":gameplay/fishing/" + name));
    }
}
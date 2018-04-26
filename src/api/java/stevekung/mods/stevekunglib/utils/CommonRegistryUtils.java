package stevekung.mods.stevekunglib.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonRegistryUtils
{
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

    public void registerPotion(Potion potion, String name)
    {
        ForgeRegistries.POTIONS.register(potion.setRegistryName(name));
    }

    public void registerBiome(Biome biome, String name)
    {
        ForgeRegistries.BIOMES.register(biome.setRegistryName(this.resourcePath + ":" + name));
    }

    public void registerBiomeType(Biome biome, @Nonnull BiomeDictionary.Type... biomeType)
    {
        BiomeDictionary.addTypes(biome, biomeType);
    }

    public SoundEvent registerSound(String name)
    {
        ResourceLocation resource = new ResourceLocation(this.resourcePath + ":" + name);
        ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(resource).setRegistryName(resource));
        return new SoundEvent(resource);
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
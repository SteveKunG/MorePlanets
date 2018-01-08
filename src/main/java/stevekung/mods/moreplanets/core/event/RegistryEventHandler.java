package stevekung.mods.moreplanets.core.event;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.client.renderer.BlockStateMapper;
import stevekung.mods.moreplanets.client.renderer.VariantsRenderer;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class RegistryEventHandler
{
    public static final List<Block> BLOCK_REGISTRY = new ArrayList<>();
    public static final List<Item> ITEM_REGISTRY = new ArrayList<>();
    public static final List<Biome> BIOME_REGISTRY = new ArrayList<>();
    public static final List<Potion> POTION_REGISTRY = new ArrayList<>();
    public static final List<SoundEvent> SOUND_EVENT_REGISTRY = new ArrayList<>();

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        for (Block block : BLOCK_REGISTRY)
        {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        for (Item item : ITEM_REGISTRY)
        {
            event.getRegistry().register(item);
        }

        if (CommonRegisterHelper.isEffectiveClient())
        {
            for (Item item : ITEM_REGISTRY)
            {
                CommonRegisterHelper.registerSorted(item);
            }
            for (Block block : BLOCK_REGISTRY)
            {
                CommonRegisterHelper.registerSorted(block);
            }
        }
    }

    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        for (Biome biome : BIOME_REGISTRY)
        {
            event.getRegistry().register(biome);
        }
    }

    @SubscribeEvent
    public void registerPotions(RegistryEvent.Register<Potion> event)
    {
        for (Potion potion : POTION_REGISTRY)
        {
            event.getRegistry().register(potion);
        }
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        if (CommonRegisterHelper.isEffectiveClient())
        {
            for (SoundEvent sound : SOUND_EVENT_REGISTRY)
            {
                event.getRegistry().register(sound);
            }
        }
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event)
    {
        VariantsRenderer.init();
        BlockStateMapper.init();
    }
}
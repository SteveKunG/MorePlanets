package stevekung.mods.moreplanets.core.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.CreateFluidSourceEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockCheeseDirt;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.BlockFronosDirt;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockInfectedDirt;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.util.CachedEnumUtil;
import stevekung.mods.moreplanets.util.blocks.BlockFluidLavaBaseMP;
import stevekung.mods.moreplanets.util.blocks.IFireBlock;

public class GeneralEventHandler
{
    private static List<BreakBlockData> INFECTED_BLOCK_LIST = new ArrayList<>();
    private static List<BreakBlockData> NON_INFECTED_BLOCK_LIST = new ArrayList<>();

    static
    {
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_WOODEN_SLAB_1, 1));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_WOODEN_SLAB_1, 9));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_WOODEN_SLAB_1, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_WOODEN_SLAB_1, 10));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.DOUBLE_WOODEN_SLAB_1, 1));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.DOUBLE_WOODEN_SLAB_1, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_COBBLESTONE_SLAB_1, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_COBBLESTONE_SLAB_1, 10));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.DOUBLE_COBBLESTONE_SLAB_1, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, 10));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.DOUBLE_DUNGEON_BRICK_SLAB_1, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.COBBLESTONE_WALL, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.DUNGEON_BRICK_WALL, 2));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.JUICER_EGG, -1));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.OIL_ORE, -1));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.SPORELILY, -1));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_GRASS_PATH, 0));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.INFECTED_CRYSTALLIZE_FENCE, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.LARGE_INFECTED_CRYSTALLIZE, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.INFECTED_CRYSTALLIZE_TORCH, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.INFECTED_CRYSTALLIZE_WEB, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.INFECTED_CRYSTALLIZE_PART, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK, -1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_BOOKSHELF, 1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_TALL_GRASS, 2));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_CRAFTING_TABLE, 1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_LEAVES, 3));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_LEAVES, 7));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_LEAVES, 11));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_FENCE, 2));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_LOG, 3));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_LOG, 7));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_LOG, 11));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_PLANKS, 2));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_SAPLING, 3));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_DOUBLE_PLANT, 3));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_DOUBLE_PLANT, 8));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_FLOWER, 1));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_FLOWER, 7));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_BLOCK, 7));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 3));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 11));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, 3));
        GeneralEventHandler.NON_INFECTED_BLOCK_LIST.add(new BreakBlockData(NibiruBlocks.NIBIRU_GRASS_PATH, 1));
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event)
    {
        if (event.getModID().equals(MorePlanetsCore.MOD_ID))
        {
            ConfigManagerMP.syncConfig(false);
        }
    }

    @SubscribeEvent
    public void onCreateFluidSource(CreateFluidSourceEvent event)
    {
        Block block = event.getState().getBlock();

        if (block == DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK || block == ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK || block == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK)
        {
            event.setResult(Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        BlockPos firePos = event.getPos().offset(event.getFace());

        if (event.getWorld().getBlockState(firePos).getBlock() instanceof IFireBlock)
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_FIRE_EXTINGUISH, GCCoreUtil.getDimensionID(event.getWorld()), firePos));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack heldItem = event.getItemStack();

        if (!heldItem.isEmpty() && (heldItem.getItem() instanceof ItemSpade || heldItem.getItem().getToolClasses(heldItem) == Collections.singleton("shovel")))
        {
            if (event.getFace() != EnumFacing.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR)
            {
                if (world.getBlockState(pos).getBlock() == NibiruBlocks.INFECTED_GRASS)
                {
                    if (!world.isRemote)
                    {
                        world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.setBlockState(pos, NibiruBlocks.NIBIRU_GRASS_PATH.getDefaultState(), 11);
                        heldItem.damageItem(1, player);
                    }
                    player.swingArm(event.getHand());
                }
                else if (world.getBlockState(pos).getBlock() == NibiruBlocks.GREEN_VEIN_GRASS)
                {
                    if (!world.isRemote)
                    {
                        world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.setBlockState(pos, NibiruBlocks.NIBIRU_GRASS_PATH.getStateFromMeta(1), 11);
                        heldItem.damageItem(1, player);
                    }
                    player.swingArm(event.getHand());
                }
            }
        }
    }

    @SubscribeEvent
    public void onBreakSpeed(BreakSpeed event)
    {
        Block block = event.getState().getBlock();
        EntityPlayer player = event.getEntityPlayer();

        if (this.isShears(player))
        {
            if (block == FronosBlocks.CANDY_CANE_1 || block == FronosBlocks.CANDY_CANE_2)
            {
                event.setNewSpeed(7.5F);
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BreakEvent event)
    {
        IBlockState sourceState = event.getState();
        Block source = sourceState.getBlock();
        EntityPlayer player = event.getPlayer();

        if (source == NibiruBlocks.INFECTED_FARMLAND && event.getWorld().getBiomeForCoordsBody(event.getPos()) == MPBiomes.GREEN_VEIN)
        {
            return;
        }
        for (BreakBlockData data : GeneralEventHandler.NON_INFECTED_BLOCK_LIST)
        {
            Block block = data.getBlock();
            int meta = data.getMeta();

            if (meta != -1)
            {
                if (source == block && source.getMetaFromState(sourceState) == meta)
                {
                    return;
                }
            }
            else
            {
                if (source == block)
                {
                    return;
                }
            }
        }

        for (BreakBlockData data : GeneralEventHandler.NON_INFECTED_BLOCK_LIST)
        {
            Block block = data.getBlock();
            int meta = data.getMeta();
            boolean flag = false;

            if (meta != -1)
            {
                if (source == block && source.getMetaFromState(sourceState) == meta)
                {
                    flag = true;
                }
            }
            else
            {
                if (source == block)
                {
                    flag = true;
                }
            }
            if (flag && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && !player.capabilities.isCreativeMode)
            {
                player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 60));
            }
        }
        if (source.getRegistryName().toString().contains("moreplanets"))
        {
            if (source.getUnlocalizedName().contains("infected") || source.getUnlocalizedName().contains("nibiru") || source.getLocalizedName().contains("infected"))
            {
                if (!player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && !player.capabilities.isCreativeMode)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 60));
                }
            }
        }
        if (this.isShears(player))
        {
            if (source == FronosBlocks.CANDY_CANE_1 || source == FronosBlocks.CANDY_CANE_2)
            {
                player.getActiveItemStack().damageItem(1, player);
            }
        }
    }

    @SubscribeEvent
    public void onPickupItem(ItemPickupEvent event)
    {
        ItemStack itemStack = event.pickedUp.getEntityItem();
        Item item = itemStack.getItem();
        Block block = Block.getBlockFromItem(item);

        if (block == ChalosBlocks.CHEESE_SPORE_STEM || block == NibiruBlocks.NIBIRU_LOG)
        {
            event.player.addStat(AchievementList.MINE_WOOD);
        }
    }

    @SubscribeEvent
    public void onCraftItem(ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();
        Block block = Block.getBlockFromItem(item);

        if (block == ChalosBlocks.CHALOS_CRAFTING_TABLE || block == NibiruBlocks.NIBIRU_CRAFTING_TABLE)
        {
            event.player.addStat(AchievementList.BUILD_WORK_BENCH);
        }
        if (item == NibiruItems.NIBIRU_STONE_PICKAXE)
        {
            event.player.addStat(AchievementList.BUILD_BETTER_PICKAXE);
        }
        if (block == NibiruBlocks.NIBIRU_FURNACE || block == NibiruBlocks.TERRASTONE_FURNACE)
        {
            event.player.addStat(AchievementList.BUILD_FURNACE);
        }
    }

    @SubscribeEvent
    public void onUseHoe(UseHoeEvent event)
    {
        if (event.getResult() != Result.DEFAULT || event.isCanceled())
        {
            return;
        }

        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (world.isAirBlock(pos.up()))
        {
            if (block == ChalosBlocks.CHEESE_DIRT)
            {
                this.setFarmland(event, world, pos, state, BlockCheeseDirt.VARIANT, BlockCheeseDirt.BlockType.CHEESE_COARSE_DIRT, ChalosBlocks.CHEESE_DIRT, ChalosBlocks.CHEESE_FARMLAND);
            }
            else if (block == ChalosBlocks.CHEESE_GRASS)
            {
                this.setFarmland(event, world, pos, ChalosBlocks.CHEESE_FARMLAND);
            }
            else if (block == NibiruBlocks.INFECTED_DIRT)
            {
                this.setFarmland(event, world, pos, state, BlockInfectedDirt.VARIANT, BlockInfectedDirt.BlockType.INFECTED_COARSE_DIRT, NibiruBlocks.INFECTED_DIRT, NibiruBlocks.INFECTED_FARMLAND);
            }
            else if (block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.GREEN_VEIN_GRASS)
            {
                this.setFarmland(event, world, pos, NibiruBlocks.INFECTED_FARMLAND);
            }
            else if (block == FronosBlocks.FRONOS_GRASS)
            {
                this.setFarmland(event, world, pos, FronosBlocks.FRONOS_FARMLAND);
            }
            else if (block == FronosBlocks.FRONOS_DIRT)
            {
                this.setFarmland(event, world, pos, state, BlockFronosDirt.VARIANT, BlockFronosDirt.BlockType.FRONOS_COARSE_DIRT, FronosBlocks.FRONOS_DIRT, FronosBlocks.FRONOS_FARMLAND);
            }
        }
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        World world = event.getWorld();

        if (event.getTarget() != null)
        {
            if (!FluidRegistry.isUniversalBucketEnabled())
            {
                BlockPos pos = event.getTarget().getBlockPos();
                this.registerBucket(event, world, pos, ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK, new ItemStack(ChalosItems.CHEESE_OF_MILK_FLUID_BUCKET), false);
                this.registerBucket(event, world, pos, ChalosBlocks.CHEESE_OF_MILK_GAS_BLOCK, new ItemStack(ChalosItems.CHEESE_OF_MILK_GAS_BUCKET), false);
                this.registerBucket(event, world, pos, NibiruBlocks.HELIUM_GAS_BLOCK, new ItemStack(NibiruItems.HELIUM_GAS_BUCKET), false);
                this.registerBucket(event, world, pos, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK, new ItemStack(NibiruItems.INFECTED_WATER_FLUID_BUCKET), false);
                this.registerBucket(event, world, pos, DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK, new ItemStack(DionaItems.CRYSTALLIZE_WATER_FLUID_BUCKET), false);
                this.registerBucket(event, world, pos, DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK, new ItemStack(DionaItems.CRYSTALLIZE_LAVA_FLUID_BUCKET), false);
                this.registerBucket(event, world, pos, NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK, new ItemStack(NibiruItems.NUCLEAR_WASTE_BUCKET), false);
                this.registerBucket(event, world, pos, NibiruBlocks.PURIFY_WATER_FLUID_BLOCK, new ItemStack(NibiruItems.PURIFY_WATER_BUCKET), false);
            }
        }
    }

    private void registerBucket(FillBucketEvent event, World world, BlockPos pos, Block block, ItemStack itemStack, boolean cancelBucket)
    {
        if (world.getBlockState(pos) == block.getDefaultState())
        {
            if (cancelBucket)
            {
                if (event.getEmptyBucket().getItem() == Items.BUCKET)
                {
                    event.setCanceled(true);
                }
            }
            else
            {
                event.getEntityPlayer().playSound(block instanceof BlockFluidLavaBaseMP ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                world.setBlockToAir(pos);
                event.setFilledBucket(itemStack);
                event.setResult(Result.ALLOW);
            }
        }
    }

    private void setFarmland(UseHoeEvent event, World world, BlockPos pos, IBlockState state, IProperty<?> property, Object value, Block dirt, Block farmland)
    {
        if (state.getValue(property) == value)
        {
            world.setBlockState(pos, dirt.getDefaultState());
        }
        else
        {
            world.setBlockState(pos, farmland.getDefaultState());
        }
        event.setResult(Result.ALLOW);
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.GROUND.getStepSound(), SoundCategory.BLOCKS, (SoundType.GROUND.getVolume() + 1.0F) / 2.0F, SoundType.GROUND.getPitch() * 0.8F);

        for (EnumHand hand : CachedEnumUtil.valuesHandCached())
        {
            event.getEntityPlayer().swingArm(hand);
        }
    }

    private void setFarmland(UseHoeEvent event, World world, BlockPos pos, Block farmland)
    {
        world.setBlockState(pos, farmland.getDefaultState());
        event.setResult(Result.ALLOW);
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.GROUND.getStepSound(), SoundCategory.BLOCKS, (SoundType.GROUND.getVolume() + 1.0F) / 2.0F, SoundType.GROUND.getPitch() * 0.8F);

        for (EnumHand hand : CachedEnumUtil.valuesHandCached())
        {
            event.getEntityPlayer().swingArm(hand);
        }
    }

    private boolean isShears(EntityPlayer player)
    {
        return player.getActiveItemStack() != null && player.getActiveItemStack().getItem() instanceof ItemShears;
    }

    private static class BreakBlockData
    {
        private Block block;
        private int meta;

        public BreakBlockData(Block block, int meta)
        {
            this.block = block;
            this.meta = meta;
        }

        public Block getBlock()
        {
            return this.block;
        }

        public int getMeta()
        {
            return this.meta;
        }
    }
}
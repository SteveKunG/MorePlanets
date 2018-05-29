package stevekung.mods.moreplanets.core.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.CreateFluidSourceEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.utils.blocks.IFire;
import stevekung.mods.moreplanets.utils.items.IDungeonKey;
import stevekung.mods.moreplanets.utils.items.IDungeonKeyable;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityTreasureChestMP;
import stevekung.mods.stevekunglib.utils.CachedEnum;

public class GeneralEventHandler
{
    private static final List<BreakBlockData> INFECTED_BLOCK_LIST = new ArrayList<>();

    static
    {
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.JUICER_EGG));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.OIL_ORE));
        GeneralEventHandler.INFECTED_BLOCK_LIST.add(new BreakBlockData(MPBlocks.SPORELILY));
    }

    @SubscribeEvent
    public void onFuelBurnTime(FurnaceFuelBurnTimeEvent event)
    {
        Item item = event.getItemStack().getItem();
        Block block = Block.getBlockFromItem(item);

        if (block == MPBlocks.CHEESE_SPORE_FLOWER || block == MPBlocks.INFECTED_OAK_SAPLING || block == MPBlocks.INFECTED_JUNGLE_SAPLING || block == MPBlocks.INFECTED_SPRUCE_SAPLING || block == MPBlocks.ALIEN_BERRY_OAK_SAPLING)
        {
            event.setBurnTime(100);
        }
        if (item == MPItems.INFECTED_CRYSTALLIZED_SHARD)
        {
            event.setBurnTime(400);
        }
        if (item == MPItems.INFECTED_COAL || item == MPItems.INFECTED_CHARCOAL)
        {
            event.setBurnTime(1600);
        }
        if (ItemStack.areItemStackTagsEqual(FluidUtil.getFilledBucket(new FluidStack(MPBlocks.CRYSTALLIZED_LAVA_FLUID, 1000)), event.getItemStack()))
        {
            event.setBurnTime(25000);
        }
        if (ItemStack.areItemStackTagsEqual(FluidUtil.getFilledBucket(new FluidStack(MPBlocks.NUCLEAR_WASTE_FLUID, 1000)), event.getItemStack()))
        {
            event.setBurnTime(60000);
        }
    }

    @SubscribeEvent
    public void onCreateFluidSource(CreateFluidSourceEvent event)
    {
        Block block = event.getState().getBlock();

        if (block == MPBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK || block == MPBlocks.CHEESE_MILK_FLUID_BLOCK || block == MPBlocks.INFECTED_WATER_FLUID_BLOCK)
        {
            event.setResult(Result.ALLOW);
        }
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        BlockPos firePos = event.getPos().offset(event.getFace());

        if (event.getWorld().getBlockState(firePos).getBlock() instanceof IFire)
        {
            event.setCanceled(true);
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleMP(EnumSimplePacketMP.S_FIRE_EXTINGUISH, GCCoreUtil.getDimensionID(event.getWorld()), firePos));
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack heldItem = event.getItemStack();

        //Skip events triggered from Thaumcraft Golems and other non-players
        if (player == null || pos == null || world == null)
        {
            return;
        }

        if (!heldItem.isEmpty() && (heldItem.getItem() instanceof ItemSpade || heldItem.getItem().getToolClasses(heldItem) == Collections.singleton("shovel")))
        {
            if (event.getFace() != EnumFacing.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR)
            {
                if (world.getBlockState(pos).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
                {
                    if (!world.isRemote)
                    {
                        world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.setBlockState(pos, MPBlocks.INFECTED_GRASS_PATH.getDefaultState(), 11);
                        heldItem.damageItem(1, player);
                    }
                    player.swingArm(event.getHand());
                }
                else if (world.getBlockState(pos).getBlock() == MPBlocks.GREEN_VEIN_GRASS_BLOCK)
                {
                    if (!world.isRemote)
                    {
                        world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.setBlockState(pos, MPBlocks.GREEN_VEIN_GRASS_PATH.getDefaultState(), 11);
                        heldItem.damageItem(1, player);
                    }
                    player.swingArm(event.getHand());
                }
            }
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile != null && tile instanceof TileEntityTreasureChestMP && tile instanceof IDungeonKeyable)
        {
            TileEntityTreasureChestMP chest = (TileEntityTreasureChestMP) tile;
            IDungeonKeyable keyable = (IDungeonKeyable) tile;

            if (chest.locked)
            {
                if (!heldItem.isEmpty())
                {
                    if (heldItem.getItem() instanceof IDungeonKey)
                    {
                        event.setCanceled(keyable.onActivated(player, keyable.getDungeonKey(), true));
                    }
                    else if (!player.isSneaking())
                    {
                        event.setCanceled(keyable.onActivated(player, keyable.getDungeonKey(), false));
                    }
                }
                else
                {
                    event.setCanceled(keyable.onActivated(player, keyable.getDungeonKey(), false));
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
            if (block == MPBlocks.RED_CANDY_CANE || block == MPBlocks.GREEN_CANDY_CANE || block == MPBlocks.BLUE_CANDY_CANE || block == MPBlocks.ORANGE_CANDY_CANE || block == MPBlocks.PINK_CANDY_CANE || block == MPBlocks.YELLOW_CANDY_CANE || block == MPBlocks.PURPLE_CANDY_CANE || block == MPBlocks.RAINBOW_CANDY_CANE)
            {
                event.setNewSpeed(7.5F);
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BreakEvent event)
    {
        IBlockState sourceState = event.getState();
        Block sourceBlock = sourceState.getBlock();
        EntityPlayer player = event.getPlayer();

        if (sourceBlock == MPBlocks.INFECTED_FARMLAND && event.getWorld().getBiomeForCoordsBody(event.getPos()) == MPBiomes.GREEN_VEIN_FIELDS)
        {
            return;
        }

        GeneralEventHandler.INFECTED_BLOCK_LIST.forEach(data ->
        {
            Block block = data.getBlock();

            if (sourceBlock == block && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && !player.capabilities.isCreativeMode)
            {
                player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 60));
            }
        });

        if (sourceBlock.getRegistryName().toString().startsWith("moreplanets"))
        {
            String sourceName = sourceBlock.getUnlocalizedName().substring(5);

            if (sourceName.contains("infected_crystallized"))
            {
                return;
            }
            else
            {
                if (sourceName.contains("infected") || sourceName.contains("nibiru"))
                {
                    if (!player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && !player.capabilities.isCreativeMode)
                    {
                        player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 60));
                    }
                }
            }
        }
        if (this.isShears(player))
        {
            if (sourceBlock == MPBlocks.RED_CANDY_CANE || sourceBlock == MPBlocks.GREEN_CANDY_CANE || sourceBlock == MPBlocks.BLUE_CANDY_CANE || sourceBlock == MPBlocks.ORANGE_CANDY_CANE || sourceBlock == MPBlocks.PINK_CANDY_CANE || sourceBlock == MPBlocks.YELLOW_CANDY_CANE || sourceBlock == MPBlocks.PURPLE_CANDY_CANE || sourceBlock == MPBlocks.RAINBOW_CANDY_CANE)
            {
                player.getActiveItemStack().damageItem(1, player);
            }
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
            if (block == MPBlocks.CHEESE_DIRT || block == MPBlocks.CHEESE_COARSE_DIRT || block == MPBlocks.CHEESE_GRASS_BLOCK)
            {
                this.setFarmland(event, world, pos, state, MPBlocks.CHEESE_COARSE_DIRT, MPBlocks.CHEESE_DIRT, MPBlocks.CHEESE_FARMLAND);
            }
            else if (block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT)
            {
                this.setFarmland(event, world, pos, state, MPBlocks.INFECTED_COARSE_DIRT, MPBlocks.INFECTED_DIRT, MPBlocks.INFECTED_FARMLAND);
            }
            else if (block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.GREEN_VEIN_GRASS_BLOCK)
            {
                this.setFarmland(event, world, pos, MPBlocks.INFECTED_FARMLAND);
            }
            else if (block == MPBlocks.FRONOS_GRASS_BLOCK || block == MPBlocks.FRONOS_DIRT || block == MPBlocks.FRONOS_COARSE_DIRT)
            {
                this.setFarmland(event, world, pos, state, MPBlocks.FRONOS_COARSE_DIRT, MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_FARMLAND);
            }
        }
    }

    private void setFarmland(UseHoeEvent event, World world, BlockPos pos, IBlockState state, Block coarse, Block dirt, Block farmland)
    {
        if (state.getBlock() == coarse)
        {
            world.setBlockState(pos, dirt.getDefaultState());
        }
        else
        {
            world.setBlockState(pos, farmland.getDefaultState());
        }

        event.setResult(Result.ALLOW);
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.GROUND.getStepSound(), SoundCategory.BLOCKS, (SoundType.GROUND.getVolume() + 1.0F) / 2.0F, SoundType.GROUND.getPitch() * 0.8F);

        for (EnumHand hand : CachedEnum.handValues)
        {
            event.getEntityPlayer().swingArm(hand);
        }
    }

    private void setFarmland(UseHoeEvent event, World world, BlockPos pos, Block farmland)
    {
        world.setBlockState(pos, farmland.getDefaultState());
        event.setResult(Result.ALLOW);
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.GROUND.getStepSound(), SoundCategory.BLOCKS, (SoundType.GROUND.getVolume() + 1.0F) / 2.0F, SoundType.GROUND.getPitch() * 0.8F);

        for (EnumHand hand : CachedEnum.handValues)
        {
            event.getEntityPlayer().swingArm(hand);
        }
    }

    private boolean isShears(EntityPlayer player)
    {
        return !player.getActiveItemStack().isEmpty() && player.getActiveItemStack().getItem() instanceof ItemShears;
    }

    static class BreakBlockData
    {
        private Block block;

        public BreakBlockData(Block block)
        {
            this.block = block;
        }

        public Block getBlock()
        {
            return this.block;
        }
    }
}
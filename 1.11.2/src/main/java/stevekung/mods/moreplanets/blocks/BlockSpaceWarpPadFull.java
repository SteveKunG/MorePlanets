package stevekung.mods.moreplanets.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.handler.TeleportHandler;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.MPLog;

public class BlockSpaceWarpPadFull extends BlockAdvancedTile implements IPartialSealableBlock
{
    public BlockSpaceWarpPadFull(String name)
    {
        super(Material.ROCK);
        this.setHardness(3.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(name);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 9;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof IMultiBlock)
        {
            ((IMultiBlock) tile).onDestroy(tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MPBlocks.SPACE_WARP_PAD);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4D, 1.0D);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        for (int x2 = -1; x2 < 2; ++x2)
        {
            for (int z2 = -1; z2 < 2; ++z2)
            {
                if (!super.canPlaceBlockAt(world, new BlockPos(pos.getX() + x2, pos.getY(), pos.getZ() + z2)))
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        world.notifyBlockUpdate(pos, state, state, 3);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing facing)
    {
        return facing == EnumFacing.UP;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(MPBlocks.SPACE_WARP_PAD, 1, 0);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntitySpaceWarpPadFull();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileEntitySpaceWarpPadFull)
            {
                ItemStack itemStack = player.getHeldItem(hand);
                TileEntitySpaceWarpPadFull warpPad = (TileEntitySpaceWarpPadFull) tile;

                if (!itemStack.isEmpty() && itemStack.getItem() == MPItems.SPACE_WARPER_CORE)
                {
                    NBTTagCompound warpCoreData = itemStack.getTagCompound();

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.shrink(1);
                    }
                    if (warpCoreData != null && warpCoreData.hasKey("DimensionID"))
                    {
                        warpPad.setTeleportData(new BlockPos(warpCoreData.getInteger("X"), warpCoreData.getInteger("Y"), warpCoreData.getInteger("Z")), warpCoreData.getInteger("DimensionID"), true);
                        player.sendMessage(new JsonUtil().text(GCCoreUtil.translate("gui.warp_pad_data_add.message")).setStyle(new JsonUtil().colorFromConfig("green")));
                        return true;
                    }
                    else
                    {
                        player.sendMessage(new JsonUtil().text(GCCoreUtil.translate("gui.warp_pad_data_add_fail.message")).setStyle(new JsonUtil().red()));

                        if (warpCoreData == null)
                        {
                            warpCoreData = new NBTTagCompound();
                            itemStack.setTagCompound(warpCoreData);
                        }
                        return false;
                    }
                }
                if (warpPad.getHasReceiveData())
                {
                    if (player.isSneaking())
                    {
                        if (warpPad.getCheckInvalid())
                        {
                            player.sendMessage(new JsonUtil().text(GCCoreUtil.translate("gui.cannot_detect_pad.message")).setStyle(new JsonUtil().red()));
                            return false;
                        }
                        if (!warpPad.disabled)
                        {
                            if (warpPad.getEnergyStoredGC() >= 5000.0F)
                            {
                                warpPad.storage.setEnergyStored(warpPad.storage.getEnergyStoredGC() - 5000.0F);
                                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                                WorldServer worldserver = server.worldServerForDimension(GCCoreUtil.getDimensionID(server.worlds[0]));

                                if (player instanceof EntityPlayerMP)
                                {
                                    TeleportHandler.setWarpDimension((EntityPlayerMP) player, worldserver, warpPad.getBlockPos().getX(), warpPad.getBlockPos().getY(), warpPad.getBlockPos().getZ(), warpPad.getDimensionID(), false);
                                }

                                world.playSound(player, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                                MPLog.debug("Teleport player to {}, {}, {}, {}, {}", warpPad.getBlockPos().getX(), warpPad.getBlockPos().getY(), warpPad.getBlockPos().getZ(), warpPad.getDimensionID(), WorldUtil.getProviderForDimensionClient(warpPad.getDimensionID()).getDimensionType().getName());
                                return true;
                            }
                            else
                            {
                                player.sendMessage(new JsonUtil().text(GCCoreUtil.translate("gui.status.missingpower.name")).setStyle(new JsonUtil().red()));
                                return true;
                            }
                        }
                        else
                        {
                            player.sendMessage(new JsonUtil().text(GCCoreUtil.translate("gui.dark_energy_disabled.message")).setStyle(new JsonUtil().red()));
                        }
                    }
                    else
                    {
                        player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
                        return true;
                    }
                }
                else
                {
                    if (player.isSneaking())
                    {
                        player.sendMessage(new JsonUtil().text(GCCoreUtil.translate("gui.no_warp_data.message")).setStyle(new JsonUtil().red()));
                        return true;
                    }
                    else
                    {
                        player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        super.harvestBlock(world, player, pos, state, tile, heldStack);

        if (tile instanceof TileEntitySpaceWarpPadFull)
        {
            ItemStack machine = new ItemStack(MPItems.SPACE_WARPER_CORE);
            TileEntitySpaceWarpPadFull pad = (TileEntitySpaceWarpPadFull) tile;

            if (pad.getHasReceiveData())
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setInteger("DimensionID", pad.getDimensionID());
                machine.getTagCompound().setInteger("X", pad.getBlockPos().getX());
                machine.getTagCompound().setInteger("Y", pad.getBlockPos().getY());
                machine.getTagCompound().setInteger("Z", pad.getBlockPos().getZ());
                machine.getTagCompound().setBoolean("Checked", true);
                Block.spawnAsEntity(world, pos, machine);
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<>();
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        TileEntity tile = world.getTileEntity(pos);
        int count = this.quantityDropped(state, fortune, rand);

        for (int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);

            if (item != null)
            {
                ret.add(new ItemStack(item, 1, this.damageDropped(state)));
            }
        }
        if (tile instanceof TileEntitySpaceWarpPadFull)
        {
            ItemStack machine = new ItemStack(MPItems.SPACE_WARPER_CORE);
            TileEntitySpaceWarpPadFull pad = (TileEntitySpaceWarpPadFull) tile;

            if (pad.getHasReceiveData())
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setInteger("DimensionID", pad.getDimensionID());
                machine.getTagCompound().setInteger("X", pad.getBlockPos().getX());
                machine.getTagCompound().setInteger("Y", pad.getBlockPos().getY());
                machine.getTagCompound().setInteger("Z", pad.getBlockPos().getZ());
                machine.getTagCompound().setBoolean("Checked", true);
                ret.add(machine);
            }
        }
        return ret;
    }
}
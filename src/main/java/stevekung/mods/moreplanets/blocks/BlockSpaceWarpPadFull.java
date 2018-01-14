package stevekung.mods.moreplanets.blocks;

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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import stevekung.mods.moreplanets.util.JsonUtil;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.TeleportUtil;

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
            if (!player.isSneaking())
            {
                TileEntity tile = world.getTileEntity(pos);

                if (tile instanceof TileEntitySpaceWarpPadFull)
                {
                    TileEntitySpaceWarpPadFull warpPad = (TileEntitySpaceWarpPadFull) tile;
                    JsonUtil json = new JsonUtil();

                    if (!warpPad.disabled)
                    {
                        if (warpPad.hasWarpCore())
                        {
                            if (warpPad.getDestinationPos() == null)
                            {
                                player.sendMessage(json.text(GCCoreUtil.translate("gui.no_warp_destination.message")).setStyle(json.red()));
                                return true;
                            }
                            else
                            {
                                if (warpPad.getEnergyStoredGC() >= 5000.0F)
                                {
                                    warpPad.storage.setEnergyStored(warpPad.storage.getEnergyStoredGC() - 5000.0F);
                                    TeleportUtil.teleportEntity(player, warpPad.getDimensionId(), warpPad.getDestinationPos().getX(), warpPad.getDestinationPos().getY(), warpPad.getDestinationPos().getZ(), warpPad.getRotationPitch(), warpPad.getRotationYaw());
                                    world.playSound(null, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 0.75F, 1.0F);
                                    MPLog.debug("Teleport player to %s, %s, %s, %s, %s", warpPad.getDestinationPos().getX(), warpPad.getDestinationPos().getY(), warpPad.getDestinationPos().getZ(), warpPad.getDimensionId(), WorldUtil.getProviderForDimensionClient(warpPad.getDimensionId()).getDimensionType().getName());
                                    return true;
                                }
                                else
                                {
                                    player.sendMessage(json.text(GCCoreUtil.translate("gui.status.missingpower.name")).setStyle(json.red()));
                                    return true;
                                }
                            }
                        }
                        else
                        {
                            player.sendMessage(json.text(GCCoreUtil.translate("gui.status.warp_core_required.name")).setStyle(json.red()));
                            return true;
                        }
                    }
                    else
                    {
                        player.sendMessage(json.text(GCCoreUtil.translate("gui.dark_energy_disabled.message")).setStyle(json.red()));
                        return true;
                    }
                }
            }
            else
            {
                player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }
        return false;
    }
}
package stevekung.mods.moreplanets.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.entity.EntityBlackHoleStorage;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.moreplanets.utils.ItemDescription;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockBlackHoleStorage extends BlockBaseMP implements ITileEntityProvider, IDescription
{
    public BlockBlackHoleStorage(String name)
    {
        super(Material.IRON);
        this.setSoundType(SoundType.METAL);
        this.setHardness(2.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityBlackHoleStorage)
        {
            TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;

            if (!storage.disableBlackHole)
            {
                for (int i = 0; i < 16; i++)
                {
                    double x = pos.getX() + rand.nextFloat() - 0.5D;
                    double y = pos.getY() + 0.5D;
                    double z = pos.getZ() + rand.nextFloat();
                    double motionX = rand.nextDouble() - 0.5D;
                    double motionZ = rand.nextDouble() - 0.5D;
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, x + 0.5D, y, z, motionX, 1.0D, 0.0D);
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, x + 0.5D, y, z + 0.0D, 0.0D, 1.0D, motionZ);
                }
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        TileEntity tile = world.getTileEntity(pos);
        world.setBlockState(pos, this.getDefaultState());

        if (!world.isRemote)
        {
            if (placer instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) placer;
                EntityBlackHoleStorage blackHole = new EntityBlackHoleStorage(world);
                blackHole.setTileEntityPos(pos);
                blackHole.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() + 2, pos.getZ() + 0.5D, 0.0F, 0.0F);
                world.spawnEntity(blackHole);
                world.playSound(null, pos.add(0, 2, 0), MPSounds.BLACK_HOLE_CREATED, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (tile instanceof TileEntityBlackHoleStorage)
                {
                    TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;
                    storage.ownerUUID = player.getGameProfile().getId().toString();

                    if (itemStack.hasTagCompound())
                    {
                        storage.inventory = NonNullList.withSize(storage.getSizeInventory(), ItemStack.EMPTY);
                        storage.disableBlackHole = itemStack.getTagCompound().getBoolean("Disable");
                        storage.useHopper = itemStack.getTagCompound().getBoolean("Hopper");
                        storage.collectMode = itemStack.getTagCompound().getString("Mode");
                        ItemStackHelper.loadAllItems(itemStack.getTagCompound(), storage.inventory);
                        storage.fluidTank.readFromNBT(itemStack.getTagCompound().getCompoundTag("XpFluid"));
                    }
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tile = world.getTileEntity(pos);
            int slot = player.inventory.currentItem;

            if (tile instanceof TileEntityBlackHoleStorage)
            {
                TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;
                FluidActionResult result = FluidUtil.interactWithFluidHandler(player.inventory.getCurrentItem(), storage.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null), player);

                if (result.isSuccess())
                {
                    player.inventory.setInventorySlotContents(slot, result.result);

                    if (player.inventoryContainer != null)
                    {
                        player.inventoryContainer.detectAndSendChanges();
                    }
                    return true;
                }
                else
                {
                    if (player.getGameProfile().getId().toString().equals(storage.ownerUUID))
                    {
                        if (player.isSneaking() && storage.fluidTank.getFluidAmount() > 0)
                        {
                            Random rand = world.rand;
                            storage.drainExp(player);
                            world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, 0.5F * ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.8F));
                        }
                        else
                        {
                            player.openGui(MorePlanetsMod.MOD_ID, -1, world, pos.getX(), pos.getY(), pos.getZ());
                        }
                    }
                    else
                    {
                        player.sendMessage(JsonUtils.create(LangUtils.translate("gui.bh_storage_not_owner.message")).setStyle(JsonUtils.red()));
                    }
                }
            }
            return true;
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        world.updateComparatorOutputLevel(pos, this);
        world.playSound(null, pos.add(0, 2, 0), MPSounds.BLACK_HOLE_DESTROYED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        super.breakBlock(world, pos, state);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityBlackHoleStorage)
        {
            ItemStack itemStack = new ItemStack(this);
            NBTTagCompound nbt = new NBTTagCompound();
            TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;
            ItemStackHelper.saveAllItems(nbt, storage.inventory);
            nbt.setBoolean("Disable", storage.disableBlackHole);
            nbt.setBoolean("Hopper", storage.useHopper);
            nbt.setString("Mode", storage.collectMode);

            if (storage.fluidTank.getFluid() != null)
            {
                NBTTagCompound fluidNbt = new NBTTagCompound();
                fluidNbt.setString("FluidName", "xpjuice");
                fluidNbt.setInteger("Amount", storage.fluidTank.getFluidAmount());
                nbt.setTag("XpFluid", fluidNbt);
            }

            itemStack.setTagCompound(nbt);
            Block.spawnAsEntity(world, pos, itemStack);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityBlackHoleStorage)
        {
            TileEntityBlackHoleStorage storage = (TileEntityBlackHoleStorage) tile;

            if (!player.getGameProfile().getId().toString().equals(storage.ownerUUID))
            {
                return -1.0F;
            }
        }
        return super.getPlayerRelativeBlockHardness(state, player, world, pos);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
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
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        return Container.calcRedstone(world.getTileEntity(pos));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityBlackHoleStorage();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.MACHINE_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> list.addAll(BlocksItemsRegistry.getDescription(this.getUnlocalizedName() + ".description"));
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToRGB(IItemRarity.SPECIAL);
    }
}
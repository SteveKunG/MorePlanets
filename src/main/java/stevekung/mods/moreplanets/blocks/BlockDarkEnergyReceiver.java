package stevekung.mods.moreplanets.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.ItemDescription;
import stevekung.mods.moreplanets.utils.blocks.BlockTileMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.blocks.IBlockDescription;
import stevekung.mods.moreplanets.utils.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.utils.helper.ItemDescriptionHelper;
import stevekung.mods.stevekunglib.utils.CommonUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockDarkEnergyReceiver extends BlockTileMP implements IBlockDescription, ISingleBlockRender
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.225D, 0.0D, 0.225D, 0.775D, 0.7D, 0.775D);

    public BlockDarkEnergyReceiver(String name)
    {
        super(Material.IRON);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack heldStack)
    {
        int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();
        int direction = 0;

        if (change == 0)
        {
            direction = 180;
        }
        if (change == 1)
        {
            direction = -90;
        }
        if (change == 2)
        {
            direction = 0;
        }
        if (change == 3)
        {
            direction = 90;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver energy = (TileEntityDarkEnergyReceiver) world.getTileEntity(pos);
            energy.onCreate(world, pos);
            energy.setFacing(direction);

            if (heldStack.hasTagCompound() && heldStack.getTagCompound().hasKey("EnergyStored"))
            {
                energy.storage.setEnergyStored(heldStack.getTagCompound().getFloat("EnergyStored"));
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack heldStack)
    {
        ItemStack machine = new ItemStack(this);
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver electric = (TileEntityDarkEnergyReceiver) tile;

            if (electric.getEnergyStoredGC() > 0)
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setFloat("EnergyStored", electric.getEnergyStoredGC());
            }
            if (!electric.successful && !electric.failed)
            {
                Block.spawnAsEntity(world, pos, machine);
            }
        }
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
            if (world.getTileEntity(pos) instanceof TileEntityDarkEnergyReceiver)
            {
                TileEntityDarkEnergyReceiver tile = (TileEntityDarkEnergyReceiver) world.getTileEntity(pos);

                if (player.isSneaking())
                {
                    if (TileEntityDarkEnergyReceiver.checkValidMultiblock(pos, world))
                    {
                        TileEntityDarkEnergyReceiver.multiBlockLists.entrySet().forEach(list ->
                        {
                            BlockPos blockpos = list.getKey();
                            IBlockState blockstate = list.getValue();
                            BlockPos newPos = pos.add(blockpos);

                            if (world.getBlockState(newPos) != blockstate)
                            {
                                Item item = Item.getItemFromBlock(blockstate.getBlock());
                                String name = item.getItemStackDisplayName(new ItemStack(item));
                                player.sendMessage(JsonUtils.create(LangUtils.translate("gui.missing_block.message", name, newPos.getX(), newPos.getY(), newPos.getZ())).setStyle(JsonUtils.red()));
                            }
                        });
                    }
                    else
                    {
                        if (!tile.disabled)
                        {
                            if (tile.getEnergyStoredGC() > 20000.0F)
                            {
                                if (!tile.isActivated())
                                {
                                    tile.setActivated(true);
                                    tile.getWorld().playSound(player, tile.getPos(), MPSounds.MACHINE_START, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                    player.sendMessage(JsonUtils.create(LangUtils.translate("gui.dark_energy_success.message")).setStyle(JsonUtils.green()));
                                    return true;
                                }
                                else
                                {
                                    player.sendMessage(JsonUtils.create(LangUtils.translate("gui.dark_energy_already_active.message")).setStyle(JsonUtils.red()));
                                    return false;
                                }
                            }
                            else
                            {
                                if (!tile.isActivated())
                                {
                                    player.sendMessage(JsonUtils.create(LangUtils.translate("gui.dark_energy_no_power.message")).setStyle(JsonUtils.red()));
                                    return false;
                                }
                            }
                        }
                        else
                        {
                            player.sendMessage(JsonUtils.create(LangUtils.translate("gui.dark_energy_disabled.message")).setStyle(JsonUtils.red()));
                            return false;
                        }
                    }
                }
                else
                {
                    if (tile.failedTick > 0 && !tile.successful)
                    {
                        int failedTick = 600 - tile.failedTick;
                        String s = "s";

                        if (failedTick < 40)
                        {
                            s = "";
                        }
                        player.sendMessage(JsonUtils.create(LangUtils.translate("gui.dark_energy_malfunction.message", CommonUtils.ticksToElapsedTime(failedTick)) + " second" + s + "!").setStyle(JsonUtils.red()));
                        return false;
                    }
                    else
                    {
                        if (!tile.failed)
                        {
                            player.openGui(MorePlanetsMod.MOD_ID, -1, world, pos.getX(), pos.getY(), pos.getZ());
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BlockDarkEnergyReceiver.AABB;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver receiver = (TileEntityDarkEnergyReceiver) tile;
            receiver.onDestroy(tile);
            GalacticraftCore.packetPipeline.sendToDimension(new PacketSimpleMP(EnumSimplePacketMP.C_REMOVE_GUIDE_POS, world.provider.getDimension(), receiver.getPos()), world.provider.getDimension());
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<>();
        TileEntity tile = world.getTileEntity(pos);
        boolean flag = true;

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tile;

            if (dark.activated || dark.failed || dark.successful)
            {
                flag = false;
            }
        }
        if (flag)
        {
            ret.add(new ItemStack(this, 1, 0));
        }
        return ret;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tile;

            if (dark.activated && !dark.successful)
            {
                for (int i = 0; i < 32; ++i)
                {
                    double d0 = pos.getX() + rand.nextFloat();
                    double d1 = pos.getY() + rand.nextFloat();
                    double d2 = pos.getZ() + rand.nextFloat();
                    double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
                    double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
                    double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
                    int j = rand.nextInt(2) * 2 - 1;
                    d2 = pos.getZ() + 0.5D + 0.25D * j;
                    d5 = rand.nextFloat() * 2.0F * j;
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, d0, d1, d2, d3, d4, d5);
                }
                for (int i = 0; i < 32; ++i)
                {
                    double d0 = pos.getX() + rand.nextFloat();
                    double d1 = pos.getY() + rand.nextFloat();
                    double d2 = pos.getZ() + rand.nextFloat();
                    double d3 = (rand.nextFloat() - 0.5D) * 0.5D;
                    double d4 = (rand.nextFloat() - 0.5D) * 0.5D;
                    double d5 = (rand.nextFloat() - 0.5D) * 0.5D;
                    int j = rand.nextInt(2) * 2 - 1;
                    d0 = pos.getX() + 0.5D + 0.25D * j;
                    d3 = rand.nextFloat() * 2.0F * j;
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, d0, d1, d2, d3, d4, d5);
                }
            }
        }
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tile;

            if ((dark.activated || dark.failed) && !dark.successful)
            {
                return false;
            }
        }
        return super.canHarvestBlock(world, pos, player);
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tile;

            if ((dark.activated || dark.failed) && !dark.successful)
            {
                return -1.0F;
            }
        }
        return super.getPlayerRelativeBlockHardness(state, player, world, pos);
    }

    @Override
    public float getBlockHardness(IBlockState state, World world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tile;

            if ((dark.activated || dark.failed) && !dark.successful)
            {
                return -1.0F;
            }
        }
        return 2.0F;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityDarkEnergyReceiver();
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
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.MACHINE_NON_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> list.addAll(ItemDescriptionHelper.getDescription(BlockDarkEnergyReceiver.this.getUnlocalizedName() + ".description"));
    }
}
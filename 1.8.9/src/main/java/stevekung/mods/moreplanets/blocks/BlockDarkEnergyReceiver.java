package stevekung.mods.moreplanets.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.JsonUtils;
import stevekung.mods.moreplanets.util.blocks.BlockTileMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockDarkEnergyReceiver extends BlockTileMP implements IBlockDescription, ISingleBlockRender
{
    public BlockDarkEnergyReceiver(String name)
    {
        super(Material.iron);
        this.setStepSound(soundTypeMetal);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        int angle = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
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

            if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("EnergyStored"))
            {
                energy.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
    {
        player.addExhaustion(0.025F);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            ItemStack machine = new ItemStack(this);
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            if (player.isSneaking())
            {
                if (world.getTileEntity(pos) instanceof TileEntityDarkEnergyReceiver)
                {
                    TileEntityDarkEnergyReceiver tile = (TileEntityDarkEnergyReceiver) world.getTileEntity(pos);
                    boolean flag = tile.successful || tile.failed;

                    if (this.checkBlock(world, pos.down().north(), player, DionaBlocks.ZELIUS_EGG.getDefaultState(), flag) && this.checkBlock(world, pos.down().south(), player, DionaBlocks.ZELIUS_EGG.getDefaultState(), flag)
                            && this.checkBlock(world, pos.down().east(), player, DionaBlocks.ZELIUS_EGG.getDefaultState(), flag) && this.checkBlock(world, pos.down().west(), player, DionaBlocks.ZELIUS_EGG.getDefaultState(), flag) && this.checkBlock(world, pos.down().down().north(), player, DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState(), flag)
                            && this.checkBlock(world, pos.down().down().south(), player, DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState(), flag) && this.checkBlock(world, pos.down().down().east(), player, DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState(), flag) && this.checkBlock(world, pos.down().down().west(), player, DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState(), flag)
                            && this.checkBlock(world, pos.down().down().north().east(), player, MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), flag) && this.checkBlock(world, pos.down().down().south().east(), player, MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), flag) && this.checkBlock(world, pos.down().down().north().west(), player, MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), flag)
                            && this.checkBlock(world, pos.down().down().south().west(), player, MPBlocks.DUNGEON_GLOWSTONE.getDefaultState(), flag) && this.checkBlock(world, pos.add(3, -1, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag) && this.checkBlock(world, pos.add(3, -1, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag)
                            && this.checkBlock(world, pos.add(-3, -1, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag) && this.checkBlock(world, pos.add(-3, -1, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag) && this.checkBlock(world, pos.add(3, 0, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1), flag)
                            && this.checkBlock(world, pos.add(3, 0, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1), flag) && this.checkBlock(world, pos.add(-3, 0, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1), flag) && this.checkBlock(world, pos.add(-3, 0, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1), flag)
                            && this.checkBlock(world, pos.add(3, 1, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag) && this.checkBlock(world, pos.add(3, 1, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag) && this.checkBlock(world, pos.add(-3, 1, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag)
                            && this.checkBlock(world, pos.add(-3, 1, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState(), flag) && this.checkBlock(world, pos.add(3, 2, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2), flag) && this.checkBlock(world, pos.add(3, 2, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2), flag)
                            && this.checkBlock(world, pos.add(-3, 2, 3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2), flag) && this.checkBlock(world, pos.add(-3, 2, -3), player, DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2), flag))
                    {
                        if (!tile.disabled)
                        {
                            if (tile.getEnergyStoredGC() > 20000.0F)
                            {
                                if (!tile.isActivated())
                                {
                                    tile.setActivated(true);
                                    tile.getWorld().playSoundEffect(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), "moreplanets:ambient.machine.start", 1.0F, 1.0F);
                                    player.addChatMessage(new JsonUtils().text(StatCollector.translateToLocal("gui.dark_energy_success.message")).setChatStyle(new JsonUtils().colorFromConfig("green")));
                                    return true;
                                }
                                else
                                {
                                    player.addChatMessage(new JsonUtils().text(StatCollector.translateToLocal("gui.dark_energy_already_active.message")).setChatStyle(new JsonUtils().red()));
                                    return false;
                                }
                            }
                            else
                            {
                                if (!tile.isActivated())
                                {
                                    player.addChatMessage(new JsonUtils().text(StatCollector.translateToLocal("gui.dark_energy_no_power.message")).setChatStyle(new JsonUtils().red()));
                                    return false;
                                }
                            }
                        }
                        else
                        {
                            player.addChatMessage(new JsonUtils().text(StatCollector.translateToLocal("gui.dark_energy_disabled.message")).setChatStyle(new JsonUtils().red()));
                            return false;
                        }
                    }
                }
            }
            else
            {
                if (world.getTileEntity(pos) instanceof TileEntityDarkEnergyReceiver)
                {
                    TileEntityDarkEnergyReceiver tile = (TileEntityDarkEnergyReceiver) world.getTileEntity(pos);

                    if (tile.failedTick > 0 && !tile.successful)
                    {
                        int failedTick = 600 - tile.failedTick;
                        String s = "s";

                        if (failedTick < 40)
                        {
                            s = "";
                        }
                        player.addChatMessage(new JsonUtils().text(StatCollector.translateToLocalFormatted("gui.dark_energy_malfunction.message", this.ticksToElapsedTime(failedTick)) + " second" + s + "!").setChatStyle(new JsonUtils().red()));
                        return false;
                    }
                    else
                    {
                        if (!tile.failed)
                        {
                            player.openGui(MorePlanetsCore.MOD_ID, -1, world, pos.getX(), pos.getY(), pos.getZ());
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String ticksToElapsedTime(int ticks)
    {
        int i = ticks / 20;
        int j = i / 60;
        i = i % 60;
        return i < 10 ? j + ":0" + i : j + ":" + i;
    }

    private boolean checkBlock(World world, BlockPos pos, EntityPlayer player, IBlockState state, boolean flag)
    {
        if (world.getBlockState(pos) == state)
        {
            return true;
        }
        else
        {
            if (!flag)
            {
                player.addChatMessage(new JsonUtils().text("Missing block " + state.getBlock().getLocalizedName() + " at " + pos.getX() + " " + pos.getY() + " " + pos.getZ()).setChatStyle(new JsonUtils().red()));
            }
            return false;
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        this.setBlockBounds(0.225F, 0.0F, 0.225F, 0.775F, 0.7F, 0.775F);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            ((TileEntityDarkEnergyReceiver) tile).onDestroy(tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = Lists.newArrayList();
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
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
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
                    MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, d0, d1, d2, d3, d4, d5);
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
                    MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, d0, d1, d2, d3, d4, d5);
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
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, BlockPos pos)
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
        return super.getPlayerRelativeBlockHardness(player, world, pos);
    }

    @Override
    public float getBlockHardness(World world, BlockPos pos)
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
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityDarkEnergyReceiver();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 2;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_NON_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> list.addAll(ItemDescriptionHelper.getDescription(BlockDarkEnergyReceiver.this.getUnlocalizedName() + ".description"));
    }

    @Override
    public String getName()
    {
        return "dark_energy_receiver";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}
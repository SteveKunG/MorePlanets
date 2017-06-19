package stevekung.mods.moreplanets.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;
import stevekung.mods.moreplanets.util.blocks.BlockContainerMP;

public class BlockDummy extends BlockContainerMP implements IPartialSealableBlock
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockDummy(String name)
    {
        super(Material.iron);
        this.setHardness(1.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setUnlocalizedName(name);
        this.setResistance(1000000000000000.0F);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.WARP_PAD));
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 0)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F / 16.0F, 1.0F);
        }
        else if (meta == 1)
        {
            this.setBlockBounds(-0.25F, 0.3F, 0.3F, 0.5F, 0.6F, 0.7F);
        }
        else if (meta == 2)
        {
            this.setBlockBounds(0.5F, 0.3F, 0.3F, 1.25F, 0.6F, 0.7F);
        }
        else if (meta == 3)
        {
            this.setBlockBounds(0.3F, 0.3F, -0.25F, 0.7F, 0.6F, 0.5F);
        }
        else if (meta == 4)
        {
            this.setBlockBounds(0.3F, 0.3F, 0.5F, 0.7F, 0.6F, 1.25F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB axisalignedbb, List list, Entity entity)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        super.addCollisionBoxesToList(world, pos, state, axisalignedbb, list, entity);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        return super.getCollisionBoundingBox(world, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
    {
        this.setBlockBoundsBasedOnState(world, pos);
        return super.getSelectedBoundingBox(world, pos);
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }

    public void makeFakeBlock(World world, BlockPos pos, BlockPos mainBlock, BlockDummy.BlockType type)
    {
        world.setBlockState(pos, MPBlocks.DUMMY_BLOCK.getStateFromMeta(type.ordinal()), 3);
        world.getTileEntity(pos).setWorldObj(world);
        ((TileEntityDummy) world.getTileEntity(pos)).setMainBlock(mainBlock);
    }

    @Override
    public float getBlockHardness(World world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityDummy)
        {
            BlockPos mainBlockPosition = ((TileEntityDummy) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null && !mainBlockPosition.equals(new BlockPos(pos.getX(), pos.getY(), pos.getZ())))
            {
                TileEntity tileOther = world.getTileEntity(mainBlockPosition);

                if (tileOther instanceof TileEntityDarkEnergyReceiver)
                {
                    TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tileOther;

                    if ((dark.activated || dark.failed) && !dark.successful)
                    {
                        return -1.0F;
                    }
                }
                return world.getBlockState(mainBlockPosition).getBlock().getBlockHardness(world, mainBlockPosition);
            }
        }
        return super.getBlockHardness(world, pos);
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing facing)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 0)
        {
            return facing == EnumFacing.DOWN;
        }
        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityDummy)
        {
            ((TileEntityDummy) tileEntity).onBlockRemoval();
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileEntityDummy tileEntity = (TileEntityDummy) world.getTileEntity(pos);
        ItemStack itemStack = player.inventory.getCurrentItem();
        TileEntity tile = world.getTileEntity(pos.down());
        TileEntity tile1 = world.getTileEntity(pos.down(2));

        if (tileEntity == null)
        {
            return false;
        }
        if (state == state.withProperty(VARIANT, BlockType.NUCLEAR_WASTE_TANK_MIDDLE) && world.getBlockState(pos.down()) == NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, false))
        {
            if (itemStack != null)
            {
                if (itemStack.getItem() == NibiruItems.WASTE_ROD_PICKER)
                {
                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.damageItem(1, player);
                    }
                    if (tile instanceof TileEntityNuclearWasteTank)
                    {
                        TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;
                        tank.hasRod = false;
                        Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NUCLEAR_WASTE_ROD));
                        world.setBlockState(pos.down(), NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, true));
                    }
                    return true;
                }
            }
        }
        if (state == state.withProperty(VARIANT, BlockType.NUCLEAR_WASTE_TANK_TOP) && world.getBlockState(pos.down(2)) == NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, false))
        {
            if (itemStack != null)
            {
                if (itemStack.getItem() == NibiruItems.WASTE_ROD_PICKER)
                {
                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.damageItem(1, player);
                    }
                    if (tile1 instanceof TileEntityNuclearWasteTank)
                    {
                        TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile1;
                        tank.hasRod = false;
                        Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NUCLEAR_WASTE_ROD));
                        world.setBlockState(pos.down(2), NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.DEPLETE, true));
                    }
                    return true;
                }
            }
        }
        return tileEntity.onBlockActivated(world, pos, player);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityDummy();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityDummy)
        {
            BlockPos mainBlockPosition = ((TileEntityDummy) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null && !mainBlockPosition.equals(new BlockPos(pos.getX(), pos.getY(), pos.getZ())))
            {
                Block mainBlock = world.getBlockState(mainBlockPosition).getBlock();

                if (Blocks.air != mainBlock)
                {
                    return mainBlock.getPickBlock(target, world, mainBlockPosition, player);
                }
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        TileEntity tileEntity = world.getTileEntity(target.getBlockPos());

        if (tileEntity instanceof TileEntityDummy)
        {
            BlockPos mainBlockPosition = ((TileEntityDummy) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null && !mainBlockPosition.equals(new BlockPos(target.getBlockPos().getX(), target.getBlockPos().getY(), target.getBlockPos().getZ())))
            {
                effectRenderer.addBlockHitEffects(mainBlockPosition, target);
            }
        }
        return super.addHitEffects(world, target, effectRenderer);
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        WARP_PAD,
        DARK_ENERGY_SOLAR1,
        DARK_ENERGY_SOLAR2,
        DARK_ENERGY_SOLAR3,
        DARK_ENERGY_SOLAR4,
        NUCLEAR_WASTE_TANK_MIDDLE,
        NUCLEAR_WASTE_TANK_TOP;

        private static BlockType[] values = BlockType.values();

        public static BlockType[] valuesCached()
        {
            return BlockType.values;
        }

        @Override
        public String toString()
        {
            return this.getName().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}
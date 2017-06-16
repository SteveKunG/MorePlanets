package stevekung.mods.moreplanets.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.tileentity.TileEntityRocketCrusher;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.blocks.BlockTileMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockRocketCrusher extends BlockTileMP implements IBlockDescription, ISingleBlockRender
{
    public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockRocketCrusher(String name)
    {
        super(Material.iron);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityRocketCrusher)
        {
            TileEntityRocketCrusher tileEntity = (TileEntityRocketCrusher)tile;
            ItemStack itemStack = new ItemStack(AsteroidsItems.basicItem, 1, 0);

            if (tileEntity.processTicks > 20)
            {
                for (int i = 0; i < 2; i++)
                {
                    world.spawnParticle(EnumParticleTypes.ITEM_CRACK, pos.getX() + (double)rand.nextFloat(), pos.getY() + 1.0D, pos.getZ() + (double)rand.nextFloat(), 0.1D * (rand.nextFloat() - 0.5D), 0.2D, (rand.nextFloat() - 0.5D) * 0.1D, new int[] {Item.getIdFromItem(itemStack.getItem()), itemStack.getMetadata()});
                }
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        int metadata = this.getMetaFromState(state);
        int angle = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();
        world.setBlockState(pos, this.getStateFromMeta((metadata & 12) + change), 3);

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("EnergyStored"))
        {
            if (world.getTileEntity(pos) instanceof TileEntityRocketCrusher)
            {
                TileEntityRocketCrusher electric = (TileEntityRocketCrusher) world.getTileEntity(pos);
                electric.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
            }
        }
    }

    @Override
    public boolean onUseWrench(World world, BlockPos pos, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState state = world.getBlockState(pos);
        TileBaseUniversalElectrical.onUseWrenchBlock(state, world, pos, state.getValue(FACING));
        return true;
    }

    @Override
    public boolean onMachineActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityRocketCrusher();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }

    @Override
    public ItemDescription getDescription()
    {
        return new ItemDescription()
        {
            @Override
            public void addDescription(ItemStack itemStack, List list)
            {
                list.addAll(ItemDescriptionHelper.getDescription(BlockRocketCrusher.this.getUnlocalizedName() + ".description"));
            }
        };
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_BLOCK;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta % 4);
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
    {
        if (tile instanceof TileEntityRocketCrusher)
        {
            ItemStack machine = new ItemStack(this);
            TileEntityRocketCrusher electric = (TileEntityRocketCrusher) tile;

            if (electric.getEnergyStoredGC() > 0)
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setFloat("EnergyStored", electric.getEnergyStoredGC());
            }
            Block.spawnAsEntity(world, pos, machine);
        }
    }

    @Override
    public String getName()
    {
        return "rocket_crusher";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}
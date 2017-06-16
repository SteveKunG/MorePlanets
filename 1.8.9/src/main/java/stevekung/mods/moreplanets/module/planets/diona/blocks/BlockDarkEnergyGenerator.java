package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.blocks.BlockTileMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockDarkEnergyGenerator extends BlockTileMP implements IBlockDescription, ISingleBlockRender
{
    public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockDarkEnergyGenerator(String name)
    {
        super(Material.iron);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.75F, 0.9F);
        this.setUnlocalizedName(name);
    }

    @Override
    public int getRenderType()
    {
        return 2;
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
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        float particlePosX = pos.getX() + 0.5F;
        float particlePosY = pos.getY() + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
        float particlePosZ = pos.getZ() + 0.5F;
        float particleSize0 = 0.52F;
        float particleSize1 = rand.nextFloat() * 0.4F - 0.25F;
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            TileEntityDarkEnergyGenerator tileEntity = (TileEntityDarkEnergyGenerator) tile;

            if (!tileEntity.disabled && tileEntity.darkEnergyFuel > 0)
            {
                for (int i = 0; i < 16; i++)
                {
                    if (tileEntity.getFacing() == 90 || tileEntity.getFacing() == -90)
                    {
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particlePosX + particleSize0, particlePosY, particlePosZ + particleSize1, 0.0D, 0.0D, 0.0D);
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particlePosX - particleSize0, particlePosY, particlePosZ + particleSize1, 0.0D, 0.0D, 0.0D);
                    }
                    else
                    {
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particlePosX + particleSize1, particlePosY, particlePosZ - particleSize0, 0.0D, 0.0D, 0.0D);
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particlePosX + particleSize1, particlePosY, particlePosZ + particleSize0, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        int angle = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();
        int direction = 0;

        if (change == 0)
        {
            direction = 90;
        }
        if (change == 1)
        {
            direction = 0;
        }
        if (change == 2)
        {
            direction = -90;
        }
        if (change == 3)
        {
            direction = -180;
        }

        world.setBlockState(pos, this.getStateFromMeta((this.getMetaFromState(world.getBlockState(pos)) & 12) + change), 3);

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            TileEntityDarkEnergyGenerator energy = (TileEntityDarkEnergyGenerator) world.getTileEntity(pos);
            energy.setFacing(direction);

            if (itemStack.hasTagCompound())
            {
                if (itemStack.getTagCompound().hasKey("EnergyStored"))
                {
                    energy.storage.setEnergyStored(itemStack.getTagCompound().getFloat("EnergyStored"));
                }
                if (itemStack.getTagCompound().hasKey("DarkEnergyFuel"))
                {
                    energy.darkEnergyFuel = itemStack.getTagCompound().getInteger("DarkEnergyFuel");
                }
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile)
    {
        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            ItemStack machine = new ItemStack(this);
            TileEntityDarkEnergyGenerator electric = (TileEntityDarkEnergyGenerator) tile;

            if (electric.getEnergyStoredGC() > 0)
            {
                machine.setTagCompound(new NBTTagCompound());
                machine.getTagCompound().setFloat("EnergyStored", electric.getEnergyStoredGC());

                if (electric.darkEnergyFuel > 0)
                {
                    machine.getTagCompound().setInteger("DarkEnergyFuel", electric.darkEnergyFuel);
                }
            }
            else
            {
                if (electric.darkEnergyFuel > 0)
                {
                    machine.setTagCompound(new NBTTagCompound());
                    machine.getTagCompound().setInteger("DarkEnergyFuel", electric.darkEnergyFuel);
                }
            }
            Block.spawnAsEntity(world, pos, machine);
        }
    }

    @Override
    public boolean onUseWrench(World world, BlockPos pos, EntityPlayer entityPlayer, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        int change = world.getBlockState(pos).getValue(FACING).rotateY().getHorizontalIndex();
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            int direction = 0;

            if (change == 0)
            {
                direction = 90;
            }
            if (change == 1)
            {
                direction = 0;
            }
            if (change == 2)
            {
                direction = -90;
            }
            if (change == 3)
            {
                direction = -180;
            }
            TileEntityDarkEnergyGenerator energy = (TileEntityDarkEnergyGenerator) tile;
            energy.setFacing(direction);
        }
        IBlockState state = world.getBlockState(pos);
        TileBaseUniversalElectrical.onUseWrenchBlock(state, world, pos, state.getValue(FACING));
        return true;
    }

    @Override
    public boolean onMachineActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getCurrentEquippedItem();

        if (itemStack != null && itemStack.getItem() == DionaItems.DARK_ENERGY_PEARL)
        {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileEntityDarkEnergyGenerator)
            {
                TileEntityDarkEnergyGenerator energy = (TileEntityDarkEnergyGenerator) tile;

                if (energy.darkEnergyFuel == 0)
                {
                    energy.darkEnergyFuel = 1000;

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemStack.stackSize--;
                    }
                    return true;
                }
                else
                {
                    player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        else
        {
            player.openGui(MorePlanetsCore.INSTANCE, -1, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityDarkEnergyGenerator();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state) & 12;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
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
        return state.getValue(FACING).getHorizontalIndex() * 4;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_NON_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return new ItemDescription()
        {
            @Override
            public void addDescription(ItemStack itemStack, List list)
            {
                list.addAll(ItemDescriptionHelper.getDescription(BlockDarkEnergyGenerator.this.getUnlocalizedName() + ".description"));
            }
        };
    }

    @Override
    public String getName()
    {
        return "dark_energy_generator";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}
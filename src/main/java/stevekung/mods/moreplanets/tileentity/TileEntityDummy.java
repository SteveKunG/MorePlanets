package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityDummy extends TileBaseElectricBlock
{
    public BlockPos mainBlockPosition;

    public TileEntityDummy()
    {
        super("");
    }

    public TileEntityDummy(String tileName)
    {
        super(tileName);
    }

    public TileEntityDummy(BlockPos mainBlockPosition)
    {
        super("");
        this.mainBlockPosition = mainBlockPosition;
    }

    @Override
    protected boolean handleInventory()
    {
        return this.inventory != null;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[0];
    }

    @Override
    public void slowDischarge() {}

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagCompound tag = nbt.getCompoundTag("mainBlockPosition");
        this.mainBlockPosition = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (this.mainBlockPosition != null)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("x", this.mainBlockPosition.getX());
            tag.setInteger("y", this.mainBlockPosition.getY());
            tag.setInteger("z", this.mainBlockPosition.getZ());
            nbt.setTag("mainBlockPosition", tag);
        }
        return nbt;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    @Override
    public void getNetworkedData(ArrayList<Object> sendData)
    {
        if (this.mainBlockPosition == null)
        {
            return;
        }
        super.getNetworkedData(sendData);
    }

    @Override
    public boolean shouldUseEnergy()
    {
        return false;
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        return null;
    }

    @Override
    public ItemStack getBatteryInSlot()
    {
        return ItemStack.EMPTY;
    }

    @Override
    public EnumFacing getFront()
    {
        return null;
    }

    public void onBlockRemoval()
    {
        TileEntity tileEntity = this.world.getTileEntity(Optional.ofNullable(this.mainBlockPosition).orElse(null));

        if (tileEntity instanceof IMultiBlock)
        {
            IMultiBlock mainBlock = (IMultiBlock) tileEntity;
            mainBlock.onDestroy(this);
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, EntityPlayer player)
    {
        TileEntity tileEntity = this.world.getTileEntity(Optional.ofNullable(this.mainBlockPosition).orElse(null));

        if (tileEntity instanceof IMultiBlock)
        {
            return ((IMultiBlock) tileEntity).onActivated(player);
        }
        return false;
    }

    public TileEntity getMainBlockTile()
    {
        return this.world.getTileEntity(Optional.ofNullable(this.mainBlockPosition).orElse(null));
    }

    public static boolean initialiseMultiTiles(BlockPos pos, World world, IMultiBlock thisTile)
    {
        // Client can create its own fake blocks and tiles - no need for networking in 1.8+
        if (world.isRemote)
        {
            thisTile.onCreate(world, pos);
        }

        List<BlockPos> positions = new ArrayList<>();
        thisTile.getPositions(pos, positions);
        boolean result = true;

        for (BlockPos vecToAdd : positions)
        {
            TileEntity tile = world.getTileEntity(vecToAdd);

            if (tile instanceof TileEntityDummy)
            {
                ((TileEntityDummy) tile).mainBlockPosition = pos;
            }
            else
            {
                result = false;
            }
        }
        return result;
    }
}
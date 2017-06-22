package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;

public class TileEntityNuclearWasteTank extends TileEntityDummy implements IMultiBlock
{
    public boolean hasRod;
    public int renderTicks;

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void onCreate(World world, BlockPos pos)
    {
        this.mainBlockPosition = pos;
    }

    @Override
    public void onDestroy(TileEntity tile)
    {
        BlockPos thisBlock = this.getPos();

        if (this.worldObj.isRemote && this.worldObj.rand.nextDouble() < 0.1D)
        {
            Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(thisBlock.up(), MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState());
            Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(thisBlock.up(2), MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState());
        }
        this.worldObj.destroyBlock(this.getPos(), true);
        this.worldObj.destroyBlock(thisBlock.up(), false);
        this.worldObj.destroyBlock(thisBlock.up(2), false);
    }

    @Override
    public void getPositions(BlockPos placedPosition, List<BlockPos> positions) {}

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return 65536.0D;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.hasRod = nbt.getBoolean("HasRod");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("HasRod", this.hasRod);
        return super.writeToNBT(nbt);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("HasRod", this.hasRod);
        return new SPacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        if (pkt.getTileEntityType() == -1)
        {
            NBTTagCompound nbt = pkt.getNbtCompound();
            this.hasRod = nbt.getBoolean("HasRod");
        }
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
    }
}
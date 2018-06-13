package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityVeinFloater;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityRenderTickable;

public class TileEntityVeinPortal extends TileEntityRenderTickable
{
    public long age;
    public boolean isMiddle;
    public boolean spawnedBoss;
    public int delayToTeleport;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.age = nbt.getLong("Age");
        this.isMiddle = nbt.getBoolean("Middle");
        this.spawnedBoss = nbt.getBoolean("SpawnedBoss");
        this.delayToTeleport = nbt.getInteger("DelayToTeleport");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setLong("Age", this.age);
        nbt.setBoolean("Middle", this.isMiddle);
        nbt.setBoolean("SpawnedBoss", this.spawnedBoss);
        nbt.setInteger("DelayToTeleport", this.delayToTeleport);
        return nbt;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("Age", this.age);
        nbt.setBoolean("Middle", this.isMiddle);
        nbt.setBoolean("SpawnedBoss", this.spawnedBoss);
        nbt.setInteger("DelayToTeleport", this.delayToTeleport);
        return new SPacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        if (pkt.getTileEntityType() == -1)
        {
            NBTTagCompound nbt = pkt.getNbtCompound();
            this.age = nbt.getLong("Age");
            this.isMiddle = nbt.getBoolean("Middle");
            this.spawnedBoss = nbt.getBoolean("SpawnedBoss");
            this.delayToTeleport = nbt.getInteger("DelayToTeleport");
        }
    }

    @Override
    public void update()
    {
        super.update();

        if (this.age < 5L)
        {
            this.age++;
        }

        if (!this.world.isRemote)
        {
            if (this.age < 5L)
            {
                ArrayList<TileEntity> attachedList = new ArrayList<>();

                for (int x = this.getPos().getX() - 1; x < this.getPos().getX() + 2; x++)
                {
                    for (int z = this.getPos().getZ() - 1; z < this.getPos().getZ() + 2; z++)
                    {
                        TileEntity tile = this.world.getTileEntity(new BlockPos(x, this.getPos().getY(), z));

                        if (tile instanceof TileEntityVeinPortal)
                        {
                            attachedList.add(tile);
                        }
                    }
                }

                if (attachedList.size() == 9)
                {
                    this.world.setBlockState(this.getPos(), MPBlocks.VEIN_PORTAL.getDefaultState(), 3);
                    TileEntityVeinPortal portal = this;
                    this.isMiddle = true;
                    this.world.setTileEntity(this.getPos(), portal);

                    for (int x = -1; x < 2; x++)
                    {
                        for (int z = -1; z < 2; z++)
                        {
                            BlockPos vecToAdd = new BlockPos(this.getPos().getX() + x, this.getPos().getY(), this.getPos().getZ() + z);

                            if (!vecToAdd.equals(this.getPos()))
                            {
                                this.world.setBlockState(vecToAdd, MPBlocks.VEIN_PORTAL.getDefaultState(), 3);
                                this.delayToTeleport = 120;
                            }
                        }
                    }
                }
                if (this.isMiddle)
                {
                    if (!this.spawnedBoss)
                    {
                        EntityVeinFloater vein = new EntityVeinFloater(this.world);
                        vein.setLocationAndAngles(this.getPos().getX() + 0.5D, this.world.getTopSolidOrLiquidBlock(this.getPos()).getY(), this.getPos().getZ() + 0.5D, 0.0F, 0.0F);
                        this.world.spawnEntity(vein);
                        this.spawnedBoss = true;
                    }
                    this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), MPBlocks.VEIN_PORTAL.getDefaultState(), 3);
                }
            }
        }

        if (this.isMiddle)
        {
            EntityPlayer player = this.world.getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 64, false);
            List<EntityVeinFloater> vein = this.world.getEntitiesWithinAABB(EntityVeinFloater.class, new AxisAlignedBB(this.getPos().getX() - 256, this.getPos().getY() - 256, this.getPos().getZ() - 256, this.getPos().getX() + 256, this.getPos().getY() + 256, this.getPos().getZ() + 256));

            if (this.delayToTeleport > 0)
            {
                this.delayToTeleport--;
            }
            if (this.delayToTeleport == 1)
            {
                if (player instanceof EntityPlayerMP)
                {
                    EntityPlayerMP playerMP = (EntityPlayerMP) player;
                    playerMP.dismountRidingEntity();
                    playerMP.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, 10));
                    playerMP.connection.setPlayerLocation(playerMP.posX + playerMP.world.rand.nextInt(64), playerMP.world.getTopSolidOrLiquidBlock(playerMP.getPosition()).getY(), playerMP.posZ + playerMP.world.rand.nextInt(64), playerMP.rotationYaw, playerMP.rotationPitch);
                }
            }
            if (this.renderTicks % 50 == 0 && vein.isEmpty())
            {
                this.isMiddle = false;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        if (this.isMiddle)
        {
            return TileEntity.INFINITE_EXTENT_AABB;
        }
        else
        {
            return new AxisAlignedBB(this.getPos(), this.getPos().add(1, 1, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        if (this.isMiddle)
        {
            return 65536.0D;
        }
        else
        {
            return super.getMaxRenderDistanceSquared();
        }
    }
}
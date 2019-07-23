package stevekung.mods.moreplanets.tileentity;

import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAdvancedMP;

public class TileEntityAlienDefenderBeacon extends TileEntityAdvancedMP
{
    @NetworkedField(targetSide = Side.CLIENT)
    public int bossCountdown = 1;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean prepareBossSpawn = false;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean creativeSpawn = false;
    public int age = 0;

    public TileEntityAlienDefenderBeacon()
    {
        super("");
    }

    @Override
    public void update()
    {
        super.update();
        ++this.age;

        if (this.bossCountdown > 1)
        {
            this.bossCountdown--;
        }
        if (this.bossCountdown == 1 && !this.creativeSpawn)
        {
            this.prepareBossSpawn = true;
        }
        if (this.prepareBossSpawn && !this.creativeSpawn)
        {
            if (!this.world.isRemote)
            {
                EntityCreeper creeper = new EntityCreeper(this.world);
                creeper.setLocationAndAngles(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D, 0.0F, 0.0F);
                this.world.spawnEntity(creeper);
                this.world.setBlockToAir(this.getPos());
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.bossCountdown = nbt.getInteger("BossCountdown");
        this.prepareBossSpawn = nbt.getBoolean("PrepareBossSpawn");
        this.creativeSpawn = nbt.getBoolean("CreativeSpawn");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("BossCountdown", this.bossCountdown);
        nbt.setBoolean("PrepareBossSpawn", this.prepareBossSpawn);
        nbt.setBoolean("CreativeSpawn", this.creativeSpawn);
        return nbt;
    }

    @Override
    public double getPacketRange()
    {
        return 32;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[0];
    }

    @Override
    protected boolean handleInventory()
    {
        return false;
    }
}
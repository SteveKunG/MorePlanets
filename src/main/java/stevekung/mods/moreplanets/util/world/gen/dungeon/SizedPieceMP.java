package stevekung.mods.moreplanets.util.world.gen.dungeon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class SizedPieceMP extends DirectionalPieceMP
{
    protected int sizeX;
    protected int sizeY;
    protected int sizeZ;

    public SizedPieceMP() {}

    public SizedPieceMP(DungeonConfigurationMP configuration, int sizeX, int sizeY, int sizeZ, EnumFacing direction)
    {
        super(configuration, direction);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setInteger("SizeX", this.sizeX);
        tagCompound.setInteger("SizeY", this.sizeY);
        tagCompound.setInteger("SizeZ", this.sizeZ);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
        super.readStructureFromNBT(tagCompound);
        this.sizeX = tagCompound.getInteger("SizeX");
        this.sizeY = tagCompound.getInteger("SizeY");
        this.sizeZ = tagCompound.getInteger("SizeZ");
    }

    public int getSizeX()
    {
        return this.sizeX;
    }

    public int getSizeY()
    {
        return this.sizeY;
    }

    public int getSizeZ()
    {
        return this.sizeZ;
    }
}
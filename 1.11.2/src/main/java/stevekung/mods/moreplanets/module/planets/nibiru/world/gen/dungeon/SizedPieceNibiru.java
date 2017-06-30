package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;

public abstract class SizedPieceNibiru extends DirectionalPieceNibiru
{
    protected int sizeX;
    protected int sizeY;
    protected int sizeZ;

    public SizedPieceNibiru() {}

    public SizedPieceNibiru(DungeonConfigurationMP configuration, int sizeX, int sizeY, int sizeZ, EnumFacing direction)
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
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager)
    {
        super.readStructureFromNBT(tagCompound, manager);
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
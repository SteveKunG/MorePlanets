package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import stevekung.mods.moreplanets.util.world.gen.dungeon.DungeonConfigurationMP;

public abstract class DirectionalPieceNibiru extends PieceNibiru
{
    private EnumFacing direction;

    public DirectionalPieceNibiru() {}

    public DirectionalPieceNibiru(DungeonConfigurationMP configuration, EnumFacing direction)
    {
        super(configuration);
        this.direction = direction;
    }

    public EnumFacing getDirection()
    {
        return this.direction;
    }

    public void setDirection(EnumFacing direction)
    {
        this.direction = direction;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setInteger("Direction", this.direction.ordinal());
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
        super.readStructureFromNBT(tagCompound);

        if (tagCompound.hasKey("Direction"))
        {
            this.direction = EnumFacing.getFront(tagCompound.getInteger("Direction"));
        }
        else
        {
            this.direction = EnumFacing.NORTH;
        }
    }

    public PieceNibiru getCorridor(Random rand, DungeonStartNibiru startPiece, int maxAttempts, boolean small)
    {
        EnumFacing randomDir;
        int blockX;
        int blockZ;
        int sizeX;
        int sizeZ;
        boolean valid;
        int attempts = maxAttempts;

        do
        {
            int randDir = rand.nextInt(4);
            randomDir = EnumFacing.getHorizontal((randDir == this.getDirection().getOpposite().getHorizontalIndex() ? randDir + 1 : randDir) % 4);
            StructureBoundingBox extension = this.getExtension(randomDir, this.configuration.getHallwayLengthMin() + rand.nextInt(this.configuration.getHallwayLengthMax() - this.configuration.getHallwayLengthMin()), 5);
            blockX = extension.minX;
            blockZ = extension.minZ;
            sizeX = extension.maxX - extension.minX;
            sizeZ = extension.maxZ - extension.minZ;
            valid = !startPiece.checkIntersection(extension);
            attempts--;
        }
        while (!valid && attempts > 0);

        if (!valid)
        {
            return null;
        }
        return new CorridorNibiru(this.configuration, rand, blockX, blockZ, sizeX, small ? 3 : this.configuration.getHallwayHeight(), sizeZ, randomDir);
    }
}
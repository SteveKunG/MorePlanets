package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.dungeon;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;

public abstract class PieceNibiru extends StructureComponent
{
    protected DungeonConfigurationMP configuration;

    public PieceNibiru() {}

    public PieceNibiru(DungeonConfigurationMP configuration)
    {
        this.configuration = configuration;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        this.configuration.writeToNBT(tagCompound);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager)
    {
        if (this.configuration == null)
        {
            this.configuration = new DungeonConfigurationMP();
            this.configuration.readFromNBT(tagCompound);
        }
    }

    protected StructureBoundingBox getExtension(EnumFacing direction, int length, int width)
    {
        int blockX, blockZ, sizeX, sizeZ;

        switch (direction)
        {
        case NORTH:
            sizeX = width;
            sizeZ = length;
            blockX = this.boundingBox.minX + (this.boundingBox.maxX - this.boundingBox.minX) / 2 - sizeX / 2;
            blockZ = this.boundingBox.minZ - sizeZ;
            break;
        case EAST:
            sizeX = length;
            sizeZ = width;
            blockX = this.boundingBox.maxX;
            blockZ = this.boundingBox.minZ + (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 - sizeZ / 2;
            break;
        case SOUTH:
            sizeX = width;
            sizeZ = length;
            blockX = this.boundingBox.minX + (this.boundingBox.maxX - this.boundingBox.minX) / 2 - sizeX / 2;
            blockZ = this.boundingBox.maxZ;
            break;
        case WEST:
        default:
            sizeX = length;
            sizeZ = width;
            blockX = this.boundingBox.minX - sizeX;
            blockZ = this.boundingBox.minZ + (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 - sizeZ / 2;
            break;
        }
        return new StructureBoundingBox(blockX, blockZ, blockX + sizeX, blockZ + sizeZ);
    }

    public PieceNibiru getNextPiece(DungeonStartNibiru startPiece, Random rand)
    {
        return null;
    }
}
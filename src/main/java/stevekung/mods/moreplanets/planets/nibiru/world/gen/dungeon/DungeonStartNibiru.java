package stevekung.mods.moreplanets.planets.nibiru.world.gen.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;

public class DungeonStartNibiru extends EntranceCraterNibiru
{
    public final List<StructureComponent> attachedComponents = new ArrayList<>();
    public final List<StructureBoundingBox> componentBounds = new ArrayList<>();

    public DungeonStartNibiru() {}

    public DungeonStartNibiru(World world, DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ)
    {
        super(world, configuration, rand, blockPosX, blockPosZ);
    }

    @Override
    public void buildComponent(StructureComponent component, List<StructureComponent> listIn, Random rand)
    {
        boolean validAttempt = false;
        final int maxAttempts = 10;
        int attempts = 0;

        while (!validAttempt && attempts < maxAttempts)
        {
            this.attachedComponents.clear();
            this.componentBounds.clear();
            this.componentBounds.add(this.boundingBox);
            listIn.clear();
            listIn.add(this);
            PieceNibiru next = this.getNextPiece(this, rand);

            while (next != null)
            {
                listIn.add(next);
                this.attachedComponents.add(next);
                this.componentBounds.add(next.getBoundingBox());
                next = next.getNextPiece(this, rand);
            }
            if (this.attachedComponents.size() >= 3 && this.attachedComponents.get(this.attachedComponents.size() - 1) instanceof RoomTreasureNibiru && this.attachedComponents.get(this.attachedComponents.size() - 3) instanceof RoomBossNibiru)
            {
                validAttempt = true;
            }
            attempts++;
        }

        LoggerMP.debug("Dungeon generation took {} attempt(s)", attempts);

        if (!validAttempt)
        {
            int xPos = this.boundingBox.minX + (this.boundingBox.maxX - this.boundingBox.minX) / 2;
            int zPos = this.boundingBox.minZ + (this.boundingBox.maxZ - this.boundingBox.minZ) / 2;
            LoggerMP.error("Could not find valid dungeon layout! This is a bug, please report it, including your world seed (/seed) and dungeon location {} {}", xPos, zPos);
        }
        super.buildComponent(component, listIn, rand);
    }

    public boolean checkIntersection(int blockX, int blockZ, int sizeX, int sizeZ)
    {
        return this.checkIntersection(new StructureBoundingBox(blockX, blockZ, blockX + sizeX, blockZ + sizeZ));
    }

    public boolean checkIntersection(StructureBoundingBox bounds)
    {
        for (int i = 0; i < this.componentBounds.size() - 1; ++i)
        {
            StructureBoundingBox boundingBox = this.componentBounds.get(i);

            if (boundingBox.intersectsWith(bounds))
            {
                return true;
            }
        }
        return false;
    }
}
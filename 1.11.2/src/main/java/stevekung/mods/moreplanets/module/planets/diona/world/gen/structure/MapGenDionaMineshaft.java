package stevekung.mods.moreplanets.module.planets.diona.world.gen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenDionaMineshaft extends MapGenStructure
{
    private double chance = 0.004D;

    public MapGenDionaMineshaft() {}

    static
    {
        MapGenStructureIO.registerStructure(StructureMineshaftStart.class, "DionaMineshaft");
        StructureDionaMineshaftPieces.registerStructurePieces();
    }

    @Override
    public String getStructureName()
    {
        return "DionaMineshaft";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        return this.rand.nextDouble() < this.chance && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ));
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new StructureMineshaftStart(this.worldObj, this.rand, chunkX, chunkZ);
    }

    public static class StructureMineshaftStart extends StructureStart
    {
        public StructureMineshaftStart() {}

        public StructureMineshaftStart(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            StructureDionaMineshaftPieces.Room piece = new StructureDionaMineshaftPieces.Room(0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.components.add(piece);
            piece.buildComponent(piece, this.components, rand);
            this.updateBoundingBox();
            this.markAvailableHeight(world, rand, 10);
        }
    }
}
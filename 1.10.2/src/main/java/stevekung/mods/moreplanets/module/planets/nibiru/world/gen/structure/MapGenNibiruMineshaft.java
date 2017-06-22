package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenNibiruMineshaft extends MapGenStructure
{
    private double chance = 0.004D;

    public MapGenNibiruMineshaft() {}

    @Override
    public String getStructureName()
    {
        return "NibiruMineshaft";
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

    class StructureMineshaftStart extends StructureStart
    {
        public StructureMineshaftStart() {}

        public StructureMineshaftStart(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            StructureNibiruMineshaftPieces.Room piece = new StructureNibiruMineshaftPieces.Room(0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.components.add(piece);
            piece.buildComponent(piece, this.components, rand);
            this.updateBoundingBox();
            this.markAvailableHeight(world, rand, 10);
        }
    }
}
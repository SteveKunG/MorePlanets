package stevekung.mods.moreplanets.module.planets.diona.world.gen.structure;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.util.math.MathHelper;
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
        MapGenStructureIO.registerStructure(Start.class, "DionaMineshaft");
        StructureDionaMineshaftPieces.registerStructurePieces();
    }

    @Override
    public String getStructureName()
    {
        return "DionaMineshaft";
    }

    public MapGenDionaMineshaft(Map map)
    {
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("chance"))
            {
                this.chance = MathHelper.parseDoubleWithDefault((String)entry.getValue(), this.chance);
            }
        }
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        return this.rand.nextDouble() < this.chance && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ));
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new Start(this.worldObj, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            StructureDionaMineshaftPieces.Room room = new StructureDionaMineshaftPieces.Room(0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.components.add(room);
            room.buildComponent(room, this.components, rand);
            this.updateBoundingBox();
            this.markAvailableHeight(world, rand, 10);
        }
    }
}
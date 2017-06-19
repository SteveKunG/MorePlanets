package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenNibiruMineshaft extends MapGenStructure
{
    private double chance = 0.004D;

    public MapGenNibiruMineshaft() {}

    static
    {
        MapGenStructureIO.registerStructure(Start.class, "NibiruMineshaft");
        StructureNibiruMineshaftPieces.registerStructurePieces();
    }

    @Override
    public String getStructureName()
    {
        return "NibiruMineshaft";
    }

    public MapGenNibiruMineshaft(Map<String, String> p_i2034_1_)
    {
        for (Entry<String, String> entry : p_i2034_1_.entrySet())
        {
            if (entry.getKey().equals("chance"))
            {
                this.chance = MathHelper.parseDoubleWithDefault(entry.getValue(), this.chance);
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
            StructureNibiruMineshaftPieces.Room structuremineshaftpieces$room = new StructureNibiruMineshaftPieces.Room(0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.components.add(structuremineshaftpieces$room);
            structuremineshaftpieces$room.buildComponent(structuremineshaftpieces$room, this.components, rand);
            this.updateBoundingBox();
            this.markAvailableHeight(world, rand, 10);
        }
    }
}
//package stevekung.mods.moreplanets.core.blocks.base;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.util.ChunkCoordinates;
//import net.minecraft.util.LongHashMap;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.ChunkCoordIntPair;
//import net.minecraft.world.Teleporter;
//import net.minecraft.world.WorldServer;
//import stevekung.mods.moreplanets.core.init.MPBlocks;
//import stevekung.mods.moreplanets.core.util.MPLog;
//
//public class TeleporterMP extends Teleporter
//{
//    private WorldServer worldServerInstance;
//    private LongHashMap destinationCoordinateCache = new LongHashMap();
//
//    public TeleporterMP(WorldServer world)
//    {
//        super(world);
//        this.worldServerInstance = world;
//    }
//
//    @Override
//    public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw) {}
//
//    @Override
//    public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw)
//    {
//        int l = MathHelper.floor_double(entity.posX);
//        int i1 = MathHelper.floor_double(entity.posZ);
//        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
//
//        if (this.destinationCoordinateCache.containsItem(j1))
//        {
//
//        }
//        else
//        {
//            ChunkCoordinates chunkcoordinates = entity.worldObj.getSpawnPoint();
//            chunkcoordinates.posY = entity.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
//            entity.setLocationAndAngles(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, entity.rotationYaw, entity.rotationPitch);
//            this.worldServerInstance.setBlock((int)entity.posX, (int)entity.posY - 1, (int)entity.posZ, MPBlocks.nether_teleporter);
//            MPLog.info("Test placeInExistingPortal %s %s %s", chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean makePortal(Entity entity)
//    {
//        this.worldServerInstance.setBlock((int)entity.posX, (int)entity.posY - 1, (int)entity.posZ, MPBlocks.nether_teleporter);
//        return true;
//    }
//
//    @Override
//    public void removeStalePortalLocations(long par1) {}
//}
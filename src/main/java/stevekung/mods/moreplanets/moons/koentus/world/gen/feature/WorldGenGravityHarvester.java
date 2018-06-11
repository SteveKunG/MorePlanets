package stevekung.mods.moreplanets.moons.koentus.world.gen.feature;

import java.util.Map;
import java.util.Random;

import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.moons.koentus.tileentity.TileEntityGravityExtractor;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class WorldGenGravityHarvester extends WorldGenerator
{
    private static final ResourceLocation GRAVITY_HARVESTER_0 = new ResourceLocation("moreplanets:gravity_harvester/gravity_harvester_0");
    private static final ResourceLocation GRAVITY_HARVESTER_1 = new ResourceLocation("moreplanets:gravity_harvester/gravity_harvester_1");
    private static final ResourceLocation GRAVITY_HARVESTER_2 = new ResourceLocation("moreplanets:gravity_harvester/gravity_harvester_2");
    private static final ResourceLocation[] GRAVITY_HARVESTERS = new ResourceLocation[] { GRAVITY_HARVESTER_0, GRAVITY_HARVESTER_1, GRAVITY_HARVESTER_2 };

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        Random random = world.getChunkFromBlockCoords(pos).getRandomWithSeed(987234911L);
        MinecraftServer server = world.getMinecraftServer();
        Rotation[] arotation = CachedEnum.rotationValues;
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        int i = random.nextInt(GRAVITY_HARVESTERS.length);
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        Template template = manager.getTemplate(server, GRAVITY_HARVESTERS[i]);
        ChunkPos chunkpos = new ChunkPos(pos);
        StructureBoundingBox box = new StructureBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
        PlacementSettings settings = new PlacementSettings().setRotation(rotation).setBoundingBox(box).setRandom(random);
        BlockPos blockpos = template.transformedSize(rotation);
        int x = random.nextInt(16 - blockpos.getX());
        int z = random.nextInt(16 - blockpos.getZ());
        BlockPos blockpos1 = template.getZeroPositionWithTransform(pos.add(x, 0, z), Mirror.NONE, rotation);
        Map<BlockPos, String> dataList = template.getDataBlocks(blockpos1, settings);

        for (Map.Entry<BlockPos, String> entry : dataList.entrySet())
        {
            if ("extractor".equals(entry.getValue()))
            {
                BlockPos pos2 = entry.getKey();
                world.setBlockState(pos2, MPBlocks.GRAVITY_CREEP_EXTRACTOR.getDefaultState(), 3);
                TileEntity tile = world.getTileEntity(pos2);

                if (tile instanceof TileEntityGravityExtractor)
                {
                    ((TileEntityGravityExtractor)tile).setLifeTime(6000 + rand.nextInt(6000));
                }
            }
            else if ("extractor_with_vines".equals(entry.getValue()))
            {
                BlockPos pos2 = entry.getKey();
                world.setBlockState(pos2, MPBlocks.GRAVITY_CREEP_EXTRACTOR.getDefaultState(), 3);
                world.setBlockState(pos2.down(), MPBlocks.GRAVITY_CREEP_VINES.getDefaultState(), 3);
                TileEntity tile = world.getTileEntity(pos2);

                if (tile instanceof TileEntityGravityExtractor)
                {
                    ((TileEntityGravityExtractor)tile).setLifeTime(6000 + rand.nextInt(6000));
                }
            }
        }
        template.addBlocksToWorld(world, blockpos1, settings, 20);
        return true;
    }
}
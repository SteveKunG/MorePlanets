package stevekung.mods.moreplanets.planets.diona.world.gen.structure;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityCrashedAlienProbe;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienChest;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.stevekunglib.utils.CachedEnum;

public abstract class ComponentCrashedAlienShipPieces extends StructureComponent
{
    protected int width;
    protected int height;
    protected int depth;
    protected int horizontalPos = -1;

    public ComponentCrashedAlienShipPieces() {}

    protected ComponentCrashedAlienShipPieces(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
    {
        super(0);
        this.width = sizeX;
        this.height = sizeY;
        this.depth = sizeZ;
        this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

        if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
        {
            this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
        }
        else
        {
            this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
        }
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("Width", this.width);
        nbt.setInteger("Height", this.height);
        nbt.setInteger("Depth", this.depth);
        nbt.setInteger("HPos", this.horizontalPos);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound nbt, TemplateManager manager)
    {
        this.width = nbt.getInteger("Width");
        this.height = nbt.getInteger("Height");
        this.depth = nbt.getInteger("Depth");
        this.horizontalPos = nbt.getInteger("HPos");
    }

    protected boolean offsetToAverageGroundLevel(World world, StructureBoundingBox bb, int yOffset)
    {
        if (this.horizontalPos >= 0)
        {
            return true;
        }
        else
        {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
            {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                {
                    pos.setPos(l, 64, k);

                    if (bb.isVecInside(pos))
                    {
                        i += Math.max(world.getTopSolidOrLiquidBlock(pos).getY(), world.provider.getAverageGroundLevel());
                        ++j;
                    }
                }
            }

            if (j == 0)
            {
                return false;
            }
            else
            {
                this.horizontalPos = i / j;
                this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
                return true;
            }
        }
    }

    public static class CrashedAlienShip extends ComponentCrashedAlienShipPieces
    {
        private static final ResourceLocation ALIEN_SHIP_0 = new ResourceLocation("moreplanets:crashed_alien_ship/crashed_alien_ship_0");

        public CrashedAlienShip() {}

        public CrashedAlienShip(Random rand, int x, int z)
        {
            super(rand, x, 64, z, 17, 9, 11);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (!this.offsetToAverageGroundLevel(world, box, -1))
            {
                return false;
            }
            else
            {
                StructureBoundingBox box1 = this.getBoundingBox();
                BlockPos pos = new BlockPos(box1.minX, box1.minY, box1.minZ).add(0, -4, 0);
                Rotation[] arotation = CachedEnum.rotationValues;
                MinecraftServer server = world.getMinecraftServer();
                TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
                PlacementSettings settings = new PlacementSettings().setRotation(arotation[rand.nextInt(arotation.length)]).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(box1);
                settings.setIntegrity(0.65F);
                Template template = manager.getTemplate(server, ALIEN_SHIP_0);
                template.addBlocksToWorldChunk(world, pos, settings);
                LoggerMP.debug("Generate Crashed Alien Ship at {} {} {}", pos.getX(), pos.getY(), pos.getZ());
                Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

                for (Entry<BlockPos, String> entry : map.entrySet())
                {
                    if ("crashed_alien_probe".equals(entry.getValue()))
                    {
                        BlockPos pos2 = entry.getKey();
                        world.setBlockState(pos2, MPBlocks.ALIEN_DEFENDER_BEACON.getDefaultState(), 3);
                        TileEntity tile = world.getTileEntity(pos2);
                        TileEntity tileDown = world.getTileEntity(pos2.down());

                        if (tile instanceof TileEntityAlienDefenderBeacon)
                        {
                            ((TileEntityAlienDefenderBeacon)tile).creativeSpawn = true;
                        }
                        if (tileDown instanceof TileEntityCrashedAlienProbe)
                        {
                            ((TileEntityCrashedAlienProbe)tileDown).setLootTable(MPLootTables.CRASHED_ALIEN_PROBE, rand.nextLong());
                        }
                    }
                    if ("alien_chest".equals(entry.getValue()))
                    {
                        BlockPos pos2 = entry.getKey();
                        world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 3);
                        TileEntity tileDown = world.getTileEntity(pos2.down());

                        if (tileDown instanceof TileEntityAlienChest)
                        {
                            ((TileEntityAlienChest)tileDown).setLootTable(MPLootTables.CRASHED_ALIEN_SHIP, rand.nextLong());
                        }
                    }
                }
                return true;
            }
        }
    }
}
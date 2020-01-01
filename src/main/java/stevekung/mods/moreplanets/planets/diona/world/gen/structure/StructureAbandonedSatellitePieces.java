package stevekung.mods.moreplanets.planets.diona.world.gen.structure;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockEnclosed;
import micdoodle8.mods.galacticraft.core.blocks.BlockMachineTiered;
import micdoodle8.mods.galacticraft.core.tile.TileEntityEnergyStorageModule;
import micdoodle8.mods.galacticraft.planets.venus.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import micdoodle8.mods.galacticraft.planets.venus.blocks.BlockLaserTurret;
import micdoodle8.mods.galacticraft.planets.venus.tile.TileEntityLaserTurret;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.ElectricFireBlockProcessor;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class StructureAbandonedSatellitePieces extends StructureComponent
{
    private static final ResourceLocation SATELLITE_BASE_ID = new ResourceLocation("moreplanets:abandoned_satellite/satellite_base");
    private static final ResourceLocation SATELLITE_DISH_BASE_ID = new ResourceLocation("moreplanets:abandoned_satellite/dish_base");
    private static final ResourceLocation SATELLITE_DISH_ID = new ResourceLocation("moreplanets:abandoned_satellite/dish");
    protected int width;
    protected int height;
    protected int depth;
    protected int horizontalPos = -1;

    public StructureAbandonedSatellitePieces() {}

    public StructureAbandonedSatellitePieces(Random rand, int x, int z)
    {
        this(rand, x, 64, z, 4, 11, 4);
    }

    protected StructureAbandonedSatellitePieces(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
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

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
    {
        if (!this.offsetToAverageGroundLevel(world, structureBoundingBox, -1))
        {
            return false;
        }
        else
        {
            StructureBoundingBox box = this.getBoundingBox();
            BlockPos pos = new BlockPos(box.minX, box.minY, box.minZ);
            Rotation[] arotation = CachedEnum.rotationValues;
            Rotation rotation = arotation[rand.nextInt(arotation.length)];
            MinecraftServer server = world.getMinecraftServer();
            TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
            PlacementSettings settings = new PlacementSettings().setRotation(rotation).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(box);
            settings.setIntegrity(0.85F);
            Template template = manager.getTemplate(server, SATELLITE_BASE_ID);
            Template template1 = manager.getTemplate(server, SATELLITE_DISH_BASE_ID);
            Template template2 = manager.getTemplate(server, SATELLITE_DISH_ID);
            this.addBlocksToWorldChunk(template, world, pos, settings);

            BlockPos blockpos1 = template.calculateConnectedPos(settings, new BlockPos(0, 4, 0), settings, new BlockPos(-1, 0, -1));
            this.addBlocksToWorldChunk(template1, world, pos.add(blockpos1), settings);

            BlockPos blockpos4 = pos.add(template.calculateConnectedPos(settings, new BlockPos(0, 8, 0), settings, new BlockPos(0, 0, 4)));
            this.addBlocksToWorldChunk(template2, world, blockpos4, settings);

            Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

            for (Map.Entry<BlockPos, String> entry : map.entrySet())
            {
                if ("laser_turret".equals(entry.getValue()))
                {
                    BlockPos blockpos2 = entry.getKey();
                    world.setBlockState(blockpos2, VenusBlocks.laserTurret.getDefaultState().withProperty(BlockLaserTurret.FACING, rotation.rotate(EnumFacing.NORTH)), 3);
                    TileEntity tile = world.getTileEntity(blockpos2);

                    if (tile instanceof TileEntityLaserTurret)
                    {
                        TileEntityLaserTurret turret = (TileEntityLaserTurret)tile;
                        turret.setOwnerUUID(UUID.randomUUID());
                        turret.addPlayer("");
                        turret.targetMeteors = false;
                        turret.getPositions(blockpos2, new ArrayList<>());
                        turret.onCreate(world, blockpos2);
                    }
                }
                else if ("chest_and_wire".equals(entry.getValue()))
                {
                    BlockPos blockpos2 = entry.getKey();
                    world.setBlockState(blockpos2, GCBlocks.sealableBlock.getDefaultState().withProperty(BlockEnclosed.TYPE, BlockEnclosed.EnumEnclosedBlockType.ALUMINUM_WIRE_HEAVY), 3);
                    TileEntity tile = world.getTileEntity(blockpos2.down());

                    if (tile instanceof TileEntityChestMP)
                    {
                        ((TileEntityChestMP)tile).setLootTable(MPLootTables.CRASHED_ALIEN_PROBE, rand.nextLong());
                    }
                }
                else if ("energy_storage".equals(entry.getValue()))
                {
                    BlockPos blockpos2 = entry.getKey();
                    world.setBlockState(blockpos2, GCBlocks.machineTiered.getDefaultState().withProperty(BlockMachineTiered.TYPE, BlockMachineTiered.EnumTieredMachineType.STORAGE_MODULE).withProperty(BlockMachineTiered.FACING, rotation.rotate(EnumFacing.SOUTH)), 3);
                    TileEntity tile = world.getTileEntity(blockpos2);

                    if (tile instanceof TileEntityEnergyStorageModule)
                    {
                        TileEntityEnergyStorageModule module = (TileEntityEnergyStorageModule)tile;
                        module.storage.setEnergyStored(500000.0F);
                        module.setInventorySlotContents(1, new ItemStack(VenusItems.atomicBattery));
                    }
                }
            }
            return true;
        }
    }

    private void addBlocksToWorldChunk(Template template, World world, BlockPos pos, PlacementSettings placement)
    {
        placement.setBoundingBoxFromChunk();
        template.addBlocksToWorld(world, pos, new ElectricFireBlockProcessor(pos, placement), placement, 2);
    }

    private boolean offsetToAverageGroundLevel(World world, StructureBoundingBox box, int yOffset)
    {
        if (this.horizontalPos >= 0)
        {
            return true;
        }
        else
        {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
            {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                {
                    mutablePos.setPos(l, 64, k);

                    if (box.isVecInside(mutablePos))
                    {
                        i += Math.max(world.getTopSolidOrLiquidBlock(mutablePos).getY(), 56);
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
}
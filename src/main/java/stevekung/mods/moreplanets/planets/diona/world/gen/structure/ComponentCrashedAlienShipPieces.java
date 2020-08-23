package stevekung.mods.moreplanets.planets.diona.world.gen.structure;

import java.util.Map;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockBrightLamp;
import micdoodle8.mods.galacticraft.core.blocks.BlockMachineBase;
import micdoodle8.mods.galacticraft.core.blocks.BlockMachineTiered;
import micdoodle8.mods.galacticraft.core.blocks.BlockScreen;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAirLockController;
import micdoodle8.mods.galacticraft.core.tile.TileEntityEnergyStorageModule;
import micdoodle8.mods.galacticraft.core.tile.TileEntityFallenMeteor;
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
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.moreplanets.utils.world.gen.feature.ElectricFireBlockProcessor;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class ComponentCrashedAlienShipPieces extends StructureComponent
{
    private static final ResourceLocation ALIEN_SHIP_BRIGDE = new ResourceLocation("moreplanets:crashed_alien_ship/bridge");
    private static final ResourceLocation ALIEN_SHIP_STORAGE = new ResourceLocation("moreplanets:crashed_alien_ship/storage");
    private static final ResourceLocation ALIEN_SHIP_BOOSTER = new ResourceLocation("moreplanets:crashed_alien_ship/booster");
    protected int width;
    protected int height;
    protected int depth;
    protected int horizontalPos = -1;

    public ComponentCrashedAlienShipPieces() {}

    public ComponentCrashedAlienShipPieces(Random rand, int x, int z)
    {
        this(rand, x, 64, z, 8, 7, 8);
    }

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
            BlockPos pos = new BlockPos(box.minX, box.minY - 3, box.minZ);
            Rotation[] arotation = CachedEnum.rotationValues;
            Rotation rotation = arotation[rand.nextInt(arotation.length)];
            MinecraftServer server = world.getMinecraftServer();
            TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
            PlacementSettings settings = new PlacementSettings().setRotation(rotation).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(box);
            settings.setIntegrity(0.8F);
            Template template = manager.getTemplate(server, ALIEN_SHIP_BRIGDE);
            Template template1 = manager.getTemplate(server, ALIEN_SHIP_STORAGE);
            Template template2 = manager.getTemplate(server, ALIEN_SHIP_BOOSTER);
            this.addBlocksToWorldChunk(template, world, pos, settings);

            BlockPos blockpos1 = template1.calculateConnectedPos(settings, new BlockPos(11, 0, 0), settings, new BlockPos(0, 0, 2));
            this.addBlocksToWorldChunk(template1, world, pos.add(blockpos1), settings);

            BlockPos blockpos2 = pos.add(template2.calculateConnectedPos(settings, new BlockPos(27, 0, 0), settings, new BlockPos(0, 0, 2)));
            this.addBlocksToWorldChunk(template2, world, blockpos2, settings);

            ComponentCrashedAlienShipPieces.replaceDataBlocks1(world, rand, rotation, template, pos, settings);
            ComponentCrashedAlienShipPieces.replaceDataBlocks2(world, rand, rotation, template1, pos.add(blockpos1), settings);
            ComponentCrashedAlienShipPieces.replaceDataBlocks3(world, rand, rotation, template2, blockpos2, settings);
            return true;
        }
    }

    private static void replaceDataBlocks1(World world, Random rand, Rotation rotation, Template template, BlockPos pos, PlacementSettings settings)
    {
        Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

        for (Map.Entry<BlockPos, String> entry : map.entrySet())
        {
            String dataName = entry.getValue();
            BlockPos dataPos = entry.getKey();

            if ("crashed_alien_probe".equals(dataName))
            {
                world.setBlockState(dataPos, MPBlocks.ALIEN_DEFENDER_BEACON.getDefaultState(), 3);
                TileEntity tile = world.getTileEntity(dataPos);
                TileEntity tileDown = world.getTileEntity(dataPos.down());

                if (tile instanceof TileEntityAlienDefenderBeacon)
                {
                    ((TileEntityAlienDefenderBeacon)tile).creativeSpawn = true;
                }
                if (tileDown instanceof TileEntityCrashedAlienProbe)
                {
                    ((TileEntityCrashedAlienProbe)tileDown).setLootTable(MPLootTables.CRASHED_ALIEN_PROBE, rand.nextLong());
                }
            }
            else if ("alien_chest".equals(dataName))
            {
                world.setBlockState(dataPos, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileDown = world.getTileEntity(dataPos.down());

                if (tileDown instanceof TileEntityAlienChest)
                {
                    ((TileEntityAlienChest)tileDown).setLootTable(MPLootTables.CRASHED_ALIEN_SHIP, rand.nextLong());
                }
            }
        }
    }

    private static void replaceDataBlocks2(World world, Random rand, Rotation rotation, Template template, BlockPos pos, PlacementSettings settings)
    {
        Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

        for (Map.Entry<BlockPos, String> entry : map.entrySet())
        {
            String dataName = entry.getValue();
            BlockPos dataPos = entry.getKey();

            if ("airlock".equals(dataName))
            {
                world.setBlockState(dataPos, GCBlocks.airLockFrame.getStateFromMeta(1), 3);
                TileEntity tile = world.getTileEntity(dataPos);

                if (tile instanceof TileEntityAirLockController)
                {
                    ((TileEntityAirLockController)tile).playerDistanceSelection = 1;
                }
            }
            else if ("chest_or_crystals".equals(dataName))
            {
                if (rand.nextFloat() > 0.95F)
                {
                    world.setBlockState(dataPos, MPBlocks.ALIEN_CHEST.getDefaultState().withRotation(rotation), 3);
                    TileEntity tile = world.getTileEntity(dataPos);

                    if (tile instanceof TileEntityChestMP)
                    {
                        ((TileEntityChestMP)tile).setLootTable(MPLootTables.CRASHED_ALIEN_SHIP, rand.nextLong());
                    }
                }
                else if (rand.nextFloat() > 0.75F)
                {
                    world.setBlockState(dataPos, GCBlocks.fallenMeteor.getDefaultState(), 3);
                    TileEntity tile = world.getTileEntity(dataPos);

                    if (tile instanceof TileEntityFallenMeteor)
                    {
                        ((TileEntityFallenMeteor)tile).heatLevel = 0;
                    }
                }
                else
                {
                    world.setBlockState(dataPos, MPBlocks.INFECTED_PURLONITE_CRYSTAL.getDefaultState(), 3);
                }
            }
            else if ("arclamp_west".equals(dataName))
            {
                world.setBlockState(dataPos, GCBlocks.brightLamp.getDefaultState().withProperty(BlockBrightLamp.FACING, rotation.rotate(EnumFacing.WEST)), 3);
            }
            else if ("arclamp_east".equals(dataName))
            {
                world.setBlockState(dataPos, GCBlocks.brightLamp.getDefaultState().withProperty(BlockBrightLamp.FACING, rotation.rotate(EnumFacing.EAST)), 3);
            }
        }
    }

    private static void replaceDataBlocks3(World world, Random rand, Rotation rotation, Template template, BlockPos pos, PlacementSettings settings)
    {
        Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

        for (Map.Entry<BlockPos, String> entry : map.entrySet())
        {
            String dataName = entry.getValue();
            BlockPos dataPos = entry.getKey();

            if ("screen_west".equals(dataName))
            {
                world.setBlockState(dataPos, GCBlocks.screen.getDefaultState().withProperty(BlockScreen.FACING, rotation.rotate(EnumFacing.WEST)), 3);
            }
            else if ("energy_storage_west".equals(dataName))
            {
                world.setBlockState(dataPos, GCBlocks.machineTiered.getDefaultState().withProperty(BlockMachineTiered.TYPE, BlockMachineTiered.EnumTieredMachineType.STORAGE_CLUSTER).withProperty(BlockMachineBase.FACING, rotation.rotate(EnumFacing.WEST)), 3);
                TileEntity tile = world.getTileEntity(dataPos);

                if (tile instanceof TileEntityEnergyStorageModule)
                {
                    ((TileEntityEnergyStorageModule)tile).storage.setEnergyStored(100000 + rand.nextInt(500000));
                }
            }
            else if ("arclamp_west".equals(dataName))
            {
                world.setBlockState(dataPos, GCBlocks.brightLamp.getDefaultState().withProperty(BlockBrightLamp.FACING, rotation.rotate(EnumFacing.WEST)), 3);
            }
        }
    }

    private boolean offsetToAverageGroundLevel(World world, StructureBoundingBox bb, int yOffset)
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

    private void addBlocksToWorldChunk(Template template, World world, BlockPos pos, PlacementSettings placement)
    {
        placement.setBoundingBoxFromChunk();
        template.addBlocksToWorld(world, pos, new ElectricFireBlockProcessor(pos, placement), placement, 2);
    }
}
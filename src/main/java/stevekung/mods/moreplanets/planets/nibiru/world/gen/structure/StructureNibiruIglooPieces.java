package stevekung.mods.moreplanets.planets.nibiru.world.gen.structure;

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
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class StructureNibiruIglooPieces extends StructureComponent
{
    private static final ResourceLocation IGLOO_TOP_ID = new ResourceLocation("moreplanets:nibiru_igloo/igloo_top");
    private static final ResourceLocation IGLOO_MIDDLE_ID = new ResourceLocation("moreplanets:nibiru_igloo/igloo_middle");
    private static final ResourceLocation IGLOO_BOTTOM_ID = new ResourceLocation("moreplanets:nibiru_igloo/igloo_bottom");
    protected int width;
    protected int height;
    protected int depth;
    protected int horizontalPos = -1;

    public StructureNibiruIglooPieces() {}

    public StructureNibiruIglooPieces(Random rand, int x, int z)
    {
        this(rand, x, 64, z, 7, 5, 8);
    }

    protected StructureNibiruIglooPieces(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
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
            MinecraftServer server = world.getMinecraftServer();
            TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
            PlacementSettings settings = new PlacementSettings().setRotation(arotation[rand.nextInt(arotation.length)]).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(box);
            Template template = manager.getTemplate(server, IGLOO_TOP_ID);
            template.addBlocksToWorldChunk(world, pos, settings);

            if (rand.nextDouble() < 0.5D)
            {
                Template template1 = manager.getTemplate(server, IGLOO_MIDDLE_ID);
                Template template2 = manager.getTemplate(server, IGLOO_BOTTOM_ID);
                int i = rand.nextInt(8) + 4;

                for (int j = 0; j < i; ++j)
                {
                    BlockPos blockpos1 = template.calculateConnectedPos(settings, new BlockPos(3, -1 - j * 3, 5), settings, new BlockPos(1, 2, 1));
                    template1.addBlocksToWorldChunk(world, pos.add(blockpos1), settings);
                }

                BlockPos blockpos4 = pos.add(template.calculateConnectedPos(settings, new BlockPos(3, -1 - i * 3, 5), settings, new BlockPos(3, 5, 7)));
                template2.addBlocksToWorldChunk(world, blockpos4, settings);
                Map<BlockPos, String> map = template2.getDataBlocks(blockpos4, settings);

                for (Entry<BlockPos, String> entry : map.entrySet())
                {
                    if ("chest".equals(entry.getValue()))
                    {
                        BlockPos blockpos2 = entry.getKey();
                        world.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 3);
                        TileEntity tile = world.getTileEntity(blockpos2.down());

                        if (tile instanceof TileEntityChestMP)
                        {
                            ((TileEntityChestMP)tile).setLootTable(MPLootTables.COMMON_SPACE_DUNGEON, rand.nextLong());
                        }
                    }
                }
            }
            else
            {
                BlockPos blockpos3 = Template.transformedBlockPos(settings, new BlockPos(3, 0, 5));
                world.setBlockState(pos.add(blockpos3), MPBlocks.INFECTED_SNOW.getDefaultState(), 3);
            }
            return true;
        }
    }

    protected boolean offsetToAverageGroundLevel(World world, StructureBoundingBox structurebb, int yOffset)
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

                    if (structurebb.isVecInside(mutablePos))
                    {
                        i += Math.max(world.getTopSolidOrLiquidBlock(mutablePos).getY(), world.provider.getAverageGroundLevel());
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
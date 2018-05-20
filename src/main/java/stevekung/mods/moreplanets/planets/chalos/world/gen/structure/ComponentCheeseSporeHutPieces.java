package stevekung.mods.moreplanets.planets.chalos.world.gen.structure;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityShulkerBox;
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
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.CachedEnum;

public abstract class ComponentCheeseSporeHutPieces extends StructureComponent
{
    protected int width;
    protected int height;
    protected int depth;
    protected int horizontalPos = -1;

    public ComponentCheeseSporeHutPieces() {}

    protected ComponentCheeseSporeHutPieces(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
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

    public static class CheeseSporeHut extends ComponentCheeseSporeHutPieces
    {
        private static final ResourceLocation CHEESE_SPORE_HUT = new ResourceLocation("moreplanets:cheese_spore_hut");

        public CheeseSporeHut() {}

        public CheeseSporeHut(Random rand, int x, int z)
        {
            super(rand, x, 64, z, 18, 17, 18);
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
                BlockPos pos = new BlockPos(box1.minX, box1.minY, box1.minZ);
                Rotation[] arotation = CachedEnum.rotationValues;
                MinecraftServer server = world.getMinecraftServer();
                TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
                PlacementSettings settings = new PlacementSettings().setRotation(arotation[rand.nextInt(arotation.length)]).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(box1);
                Template template = manager.getTemplate(server, CheeseSporeHut.CHEESE_SPORE_HUT);
                template.addBlocksToWorldChunk(world, pos, settings);
                LoggerMP.debug("Generate Cheese Spore Hut at {} {} {}", pos.getX(), pos.getY(), pos.getZ());
                Map<BlockPos, String> map = template.getDataBlocks(pos, settings);

                for (Entry<BlockPos, String> entry : map.entrySet())
                {
                    if ("hut_shulker".equals(entry.getValue()) || "hut_chest".equals(entry.getValue()))
                    {
                        BlockPos pos2 = entry.getKey();
                        world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 3);
                        TileEntity tileDown = world.getTileEntity(pos2.down());

                        if (tileDown instanceof TileEntityChestMP)//TODO
                        {
                            ((TileEntityChestMP)tileDown).setLootTable(MPLootTables.COMMON_SPACE_DUNGEON, rand.nextLong());
                        }
                        if (tileDown instanceof TileEntityShulkerBox)//TODO
                        {
                            ((TileEntityShulkerBox)tileDown).setLootTable(MPLootTables.COMMON_SPACE_DUNGEON, rand.nextLong());
                        }
                    }
                    if ("hut_brewing".equals(entry.getValue()))
                    {
                        BlockPos pos2 = entry.getKey();
                        world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 3);
                        TileEntity tileDown = world.getTileEntity(pos2.down());

                        if (tileDown instanceof TileEntityBrewingStand)
                        {
                            ((TileEntityBrewingStand)tileDown).setField(1, rand.nextInt(20));
                        }
                    }
                    if ("cheese_cake".equals(entry.getValue()))
                    {
                        BlockPos pos2 = entry.getKey();
                        world.setBlockState(pos2, MPBlocks.CHEESE_MILK_CAKE.getDefaultState().withProperty(BlockStateProperty.BITES, 1 + rand.nextInt(4)), 3);
                    }
                }
                return true;
            }
        }
    }
}
package stevekung.mods.moreplanets.planets.fronos.world.gen;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
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
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.utils.PaintingMP;

public class RongHouse extends StructureComponent
{
    private static final List<Block> FOLIAGE = Lists.newArrayList(MPBlocks.NEMOPHILA, MPBlocks.PINK_BLECHNUM, MPBlocks.PURPLE_BUSH, MPBlocks.FRONOS_FERN);
    private static final ResourceLocation RONG_HOUSE = new ResourceLocation("moreplanets:rong_house");
    private int width;
    private int height;
    private int depth;
    private int horizontalPos = -1;

    public RongHouse() {}

    public RongHouse(Random rand, int x, int z)
    {
        super(0);
        int y = 64;
        int sizeX = 8;
        int sizeY = 8;
        int sizeZ = 8;
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
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setInteger("Width", this.width);
        tagCompound.setInteger("Height", this.height);
        tagCompound.setInteger("Depth", this.depth);
        tagCompound.setInteger("HPos", this.horizontalPos);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager)
    {
        this.width = tagCompound.getInteger("Width");
        this.height = tagCompound.getInteger("Height");
        this.depth = tagCompound.getInteger("Depth");
        this.horizontalPos = tagCompound.getInteger("HPos");
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox)
    {
        if (!this.offsetToAverageGroundLevel(world, structureBoundingBox, -2))
        {
            return false;
        }
        else
        {
            StructureBoundingBox structureboundingbox = this.getBoundingBox();
            BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
            Rotation[] arotation = Rotation.values();
            MinecraftServer minecraftserver = world.getMinecraftServer();
            TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
            Rotation rotation = arotation[rand.nextInt(arotation.length)];
            PlacementSettings placementsettings = new PlacementSettings().setRotation(rotation).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
            Template template = templatemanager.getTemplate(minecraftserver, RONG_HOUSE);
            template.addBlocksToWorldChunk(world, blockpos, placementsettings);
            Map<BlockPos, String> map = template.getDataBlocks(blockpos, placementsettings);

            for (Map.Entry<BlockPos, String> entry : map.entrySet())
            {
                String dataName = entry.getValue();
                BlockPos blockpos2 = entry.getKey();

                if ("fronos_foliage".equals(dataName))
                {
                    world.setBlockState(blockpos2, FOLIAGE.get(rand.nextInt(FOLIAGE.size())).getDefaultState(), 3);
                }
                else if ("rong_with_white_carpet".equals(dataName))
                {
                    EntityWolf rong = new EntityWolf(world);
                    rong.setTamed(true);
                    rong.setHealth(20.0F);
                    rong.setCustomNameTag("Rong");
                    rong.setSitting(true);
                    rong.setCollarColor(EnumDyeColor.LIGHT_BLUE);
                    rong.setLocationAndAngles(blockpos2.getX() + 0.5D, blockpos2.getY() + 0.25D, blockpos2.getZ() + 0.5D, 0.0F, 0.0F);
                    world.spawnEntity(rong);
                    world.setBlockState(blockpos2, Blocks.CARPET.getDefaultState(), 3);
                }
                else if ("rong_painting".equals(dataName))
                {
                    EntityPainting painting = new EntityPainting(world, blockpos2, this.getPaintingFacing(rotation, blockpos2));
                    painting.art = PaintingMP.RONG;
                    world.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 3);
                    world.spawnEntity(painting);
                }
            }
            return true;
        }
    }

    private EnumFacing getPaintingFacing(Rotation rotation, BlockPos pos)
    {
        EnumFacing facing = this.getCoordBaseMode();

        if (rotation == Rotation.NONE)
        {
            if (facing.getAxis() == EnumFacing.Axis.X)
            {
                switch (facing)
                {
                case EAST:
                case WEST:
                    return EnumFacing.EAST;
                case NORTH:
                default:
                    this.printDebug(rotation, facing, pos);
                    break;
                case SOUTH:
                    this.printDebug(rotation, facing, pos);
                    break;
                }
            }
            else
            {
                switch (facing)
                {
                case EAST:
                    this.printDebug(rotation, facing, pos);
                    break;
                case NORTH:
                case SOUTH:
                default:
                    return EnumFacing.EAST;
                case WEST:
                    this.printDebug(rotation, facing, pos);
                    break;
                }
            }
        }
        else if (rotation == Rotation.CLOCKWISE_90)
        {
            if (facing.getAxis() == EnumFacing.Axis.X)
            {
                switch (facing)
                {
                case EAST:
                case WEST:
                    return EnumFacing.SOUTH;
                case NORTH:
                default:
                    this.printDebug(rotation, facing, pos);
                    break;
                case SOUTH:
                    this.printDebug(rotation, facing, pos);
                    break;
                }
            }
            else
            {
                switch (facing)
                {
                case EAST:
                    this.printDebug(rotation, facing, pos);
                    break;
                case NORTH:
                case SOUTH:
                default:
                    return EnumFacing.SOUTH;
                case WEST:
                    this.printDebug(rotation, facing, pos);
                    break;
                }
            }
        }
        else if (rotation == Rotation.CLOCKWISE_180)
        {
            if (facing.getAxis() == EnumFacing.Axis.X)
            {
                switch (facing)
                {
                case NORTH:
                default:
                    this.printDebug(rotation, facing, pos);
                    break;
                case SOUTH:
                    this.printDebug(rotation, facing, pos);
                    break;
                case WEST:
                case EAST:
                    return EnumFacing.WEST;
                }
            }
            else
            {
                switch (facing)
                {
                case EAST:
                    this.printDebug(rotation, facing, pos);
                    break;
                case NORTH:
                case SOUTH:
                case WEST:
                default:
                    return EnumFacing.WEST;
                }
            }
        }
        else if (rotation == Rotation.COUNTERCLOCKWISE_90)
        {
            if (facing.getAxis() == EnumFacing.Axis.X)
            {
                switch (facing)
                {
                case EAST:
                case WEST:
                    return EnumFacing.NORTH;
                case NORTH:
                default:
                    this.printDebug(rotation, facing, pos);
                    break;
                case SOUTH:
                    this.printDebug(rotation, facing, pos);
                    break;
                }
            }
            else
            {
                switch (facing)
                {
                case EAST:
                    this.printDebug(rotation, facing, pos);
                    break;
                case NORTH:
                case SOUTH:
                default:
                    return EnumFacing.NORTH;
                case WEST:
                    this.printDebug(rotation, facing, pos);
                    break;
                }
            }
        }
        return facing;
    }

    private void printDebug(Rotation rotation, EnumFacing currentStructureFacing, BlockPos pos)
    {
        LoggerMP.debug("rot: {}, facing: {}, axis: {}, pos: /tp {} {} {}", rotation, currentStructureFacing, currentStructureFacing.getAxis(), pos.getX(), pos.getY(), pos.getZ());
    }

    private boolean offsetToAverageGroundLevel(World world, StructureBoundingBox structurebb, int yOffset)
    {
        if (this.horizontalPos >= 0)
        {
            return true;
        }
        else
        {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
            {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                {
                    blockpos$mutableblockpos.setPos(l, 64, k);

                    if (structurebb.isVecInside(blockpos$mutableblockpos))
                    {
                        i += Math.max(world.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), world.provider.getAverageGroundLevel());
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
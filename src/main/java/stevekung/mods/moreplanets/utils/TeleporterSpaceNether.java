package stevekung.mods.moreplanets.utils;

import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntitySpacePortal;

public class TeleporterSpaceNether extends Teleporter
{
    public TeleporterSpaceNether(WorldServer world)
    {
        super(world);
    }

    @Override
    public void placeInPortal(Entity entity, float yaw)
    {
        if (!this.placeInExistingPortal(entity, yaw))
        {
            this.makePortal(entity);
            this.placeInExistingPortal(entity, yaw);
        }
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float yaw)
    {
        BlockPos pos = entity.getPosition();
        BlockPos teleporterPos = this.findNearestTeleporter(new BlockPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D));

        if (teleporterPos != null)
        {
            entity.setLocationAndAngles(teleporterPos.getX() + 0.5D, teleporterPos.getY() + 1, teleporterPos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
            this.world.playSound(null, entity.getPosition(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS, 0.25F, this.random.nextFloat() * 0.4F + 0.8F);
            return true;
        }
        return false;
    }

    @Override
    public boolean makePortal(Entity entity)
    {
        BlockPos pos = entity.getPosition();
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;

        for (int dx = -2; dx < 3; dx++)
        {
            for (int dy = -1; dy < 3; dy++)
            {
                for (int dz = -2; dz < 3; dz++)
                {
                    BlockPos pos2 = new BlockPos(x + dx, y + dy, z + dz);

                    if (dy > -1)
                    {
                        this.world.setBlockToAir(pos2);
                    }
                    else if (this.world.isAirBlock(pos2))
                    {
                        this.world.setBlockState(pos2, Blocks.OBSIDIAN.getDefaultState());
                    }
                }
            }
        }
        this.world.setBlockState(entity.getPosition(), MPBlocks.SPACE_PORTAL.getDefaultState());
        entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, entity.rotationYaw, 0.0F);
        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        return true;
    }

    private BlockPos findNearestTeleporter(BlockPos pos)
    {
        BlockPos closestPos = null;
        double minDist = 0;

        for (int x = pos.getX() - 8 >> 4; x <= pos.getX() + 8 >> 4; x++)
        {
            for (int z = pos.getZ() - 8 >> 4; z <= pos.getZ() + 8 >> 4; z++)
            {
                Chunk chunk = this.world.getChunk(x, z);

                for (Map.Entry<BlockPos, TileEntity> entry : chunk.getTileEntityMap().entrySet())
                {
                    TileEntity tileEntity = entry.getValue();

                    if (tileEntity instanceof TileEntitySpacePortal)
                    {
                        BlockPos key = entry.getKey();

                        if (key == null)
                        {
                            continue;
                        }

                        double dist = pos.distanceSq(key);

                        if (closestPos == null || dist < minDist)
                        {
                            closestPos = key;
                            minDist = dist;
                        }
                    }
                }
            }
        }
        return closestPos;
    }
}
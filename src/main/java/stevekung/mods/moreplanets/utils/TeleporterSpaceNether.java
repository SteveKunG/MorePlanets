package stevekung.mods.moreplanets.utils;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntitySpacePortal;

public class TeleporterSpaceNether extends Teleporter
{
    private WorldServer world;
    private BlockPos pos;
    private int prevDim;

    public TeleporterSpaceNether(WorldServer world, BlockPos pos, WorldProvider provider)
    {
        super(world);
        this.world = world;
        this.prevDim = provider.getDimension();
        this.pos = pos;
    }

    @Override
    public void placeInPortal(Entity entity, float rotationYaw)
    {
        if (this.world.getBlockState(this.pos).getBlock() != MPBlocks.SPACE_PORTAL)
        {
            for (int x = -2; x < 3; x++)
            {
                for (int y = -1; y < 3; y++)
                {
                    for (int z = -2; z < 3; z++)
                    {
                        if (y > -1)
                        {
                            this.world.setBlockToAir(this.pos.add(x, y, z));
                        }
                        else if (this.world.isAirBlock(this.pos.add(x, y, z)))
                        {
                            this.world.setBlockState(this.pos.add(x, y, z), Blocks.OBSIDIAN.getDefaultState());
                        }
                    }
                }
            }
            this.world.setBlockState(this.pos, MPBlocks.SPACE_PORTAL.getDefaultState());
        }

        if (this.prevDim != ConfigManagerMP.moreplanets_dimension.idDimensionSpaceNether)
        {
            TileEntity tile = this.world.getTileEntity(this.pos);

            if (tile != null && tile instanceof TileEntitySpacePortal)
            {
                ((TileEntitySpacePortal)tile).setDimension(this.prevDim);
            }
        }
        entity.setPosition(this.pos.getX() + 0.5D, this.pos.getY() + 1.0D, this.pos.getZ() + 0.5D);
        entity.motionX = entity.motionY = entity.motionZ = 0;
        this.world.playSound(null, entity.getPosition(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.MASTER, 0.25F, this.random.nextFloat() * 0.4F + 0.8F);
    }
}
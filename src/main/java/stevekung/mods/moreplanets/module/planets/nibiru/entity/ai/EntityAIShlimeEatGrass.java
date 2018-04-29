package stevekung.mods.moreplanets.module.planets.nibiru.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class EntityAIShlimeEatGrass extends EntityAIBase
{
    private EntityLiving grassEaterEntity;
    private World entityWorld;
    int eatingGrassTimer;

    public EntityAIShlimeEatGrass(EntityLiving grassEaterEntity)
    {
        this.grassEaterEntity = grassEaterEntity;
        this.entityWorld = grassEaterEntity.world;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
            Block blockDown = this.entityWorld.getBlockState(blockpos.down()).getBlock();
            return blockDown == NibiruBlocks.INFECTED_GRASS_BLOCK || blockDown == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK || this.entityWorld.getBlockState(blockpos) == NibiruBlocks.INFECTED_GRASS.getDefaultState() || this.entityWorld.getBlockState(blockpos) == NibiruBlocks.GREEN_VEIN_GRASS.getDefaultState();
        }
    }

    @Override
    public void startExecuting()
    {
        this.eatingGrassTimer = 40;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
        this.grassEaterEntity.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.eatingGrassTimer = 0;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.eatingGrassTimer > 0;
    }

    public int getEatingGrassTimer()
    {
        return this.eatingGrassTimer;
    }

    @Override
    public void updateTask()
    {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4)
        {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (this.entityWorld.getBlockState(blockpos) == NibiruBlocks.INFECTED_GRASS.getDefaultState() || this.entityWorld.getBlockState(blockpos) == NibiruBlocks.GREEN_VEIN_GRASS.getDefaultState())
            {
                if (this.entityWorld.getGameRules().getBoolean("mobGriefing"))
                {
                    this.entityWorld.destroyBlock(blockpos, false);
                }
                this.grassEaterEntity.eatGrassBonus();
            }
            else
            {
                BlockPos blockpos1 = blockpos.down();

                if (this.entityWorld.getBlockState(blockpos1).getBlock() == NibiruBlocks.INFECTED_GRASS_BLOCK)
                {
                    if (this.entityWorld.getGameRules().getBoolean("mobGriefing"))
                    {
                        this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(NibiruBlocks.INFECTED_GRASS_BLOCK));
                        this.entityWorld.setBlockState(blockpos1, NibiruBlocks.INFECTED_DIRT.getDefaultState(), 2);
                    }
                    this.grassEaterEntity.eatGrassBonus();
                }
                else if (this.entityWorld.getBlockState(blockpos1).getBlock() == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK)
                {
                    if (this.entityWorld.getGameRules().getBoolean("mobGriefing"))
                    {
                        this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(NibiruBlocks.GREEN_VEIN_GRASS_BLOCK));
                        this.entityWorld.setBlockState(blockpos1, NibiruBlocks.INFECTED_DIRT.getDefaultState(), 2);
                    }
                    this.grassEaterEntity.eatGrassBonus();
                }
            }
        }
    }
}
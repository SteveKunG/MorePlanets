package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;

public class EntityAIShlimeEatGrass extends EntityAIBase
{
    private EntityLiving entity;
    private World world;
    int eatingGrassTimer;

    public EntityAIShlimeEatGrass(EntityLiving entity)
    {
        this.entity = entity;
        this.world = entity.world;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getRNG().nextInt(this.entity.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            BlockPos pos = new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);
            Block block = this.world.getBlockState(pos).getBlock();
            Block blockDown = this.world.getBlockState(pos.down()).getBlock();
            return blockDown == MPBlocks.INFECTED_GRASS_BLOCK || blockDown == MPBlocks.GREEN_VEIN_GRASS_BLOCK || block == MPBlocks.INFECTED_GRASS || block == MPBlocks.GREEN_VEIN_GRASS;
        }
    }

    @Override
    public void startExecuting()
    {
        this.eatingGrassTimer = 40;
        this.world.setEntityState(this.entity, (byte)10);
        this.entity.getNavigator().clearPath();
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

    @Override
    public void updateTask()
    {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4)
        {
            BlockPos pos = new BlockPos(this.entity.posX, this.entity.posY, this.entity.posZ);

            if (this.world.getBlockState(pos) == MPBlocks.INFECTED_GRASS.getDefaultState() || this.world.getBlockState(pos) == MPBlocks.GREEN_VEIN_GRASS.getDefaultState())
            {
                if (this.world.getGameRules().getBoolean("mobGriefing"))
                {
                    this.world.destroyBlock(pos, false);
                }
                this.entity.eatGrassBonus();
            }
            else
            {
                BlockPos pos1 = pos.down();

                if (this.world.getBlockState(pos1).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
                {
                    if (this.world.getGameRules().getBoolean("mobGriefing"))
                    {
                        this.world.playEvent(2001, pos1, Block.getIdFromBlock(MPBlocks.INFECTED_GRASS_BLOCK));
                        this.world.setBlockState(pos1, MPBlocks.INFECTED_DIRT.getDefaultState(), 2);
                    }
                    this.entity.eatGrassBonus();
                }
                else if (this.world.getBlockState(pos1).getBlock() == MPBlocks.GREEN_VEIN_GRASS_BLOCK)
                {
                    if (this.world.getGameRules().getBoolean("mobGriefing"))
                    {
                        this.world.playEvent(2001, pos1, Block.getIdFromBlock(MPBlocks.GREEN_VEIN_GRASS_BLOCK));
                        this.world.setBlockState(pos1, MPBlocks.INFECTED_DIRT.getDefaultState(), 2);
                    }
                    this.entity.eatGrassBonus();
                }
            }
        }
    }

    public int getEatingGrassTimer()
    {
        return this.eatingGrassTimer;
    }
}
package stevekung.mods.moreplanets.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class EntityAntiGravFallingBlock extends Entity
{
    private boolean hasActivated = false;
    private static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(EntityAntiGravFallingBlock.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<String> BLOCK_NAME = new DataParameter<>(20, DataSerializers.STRING);

    public EntityAntiGravFallingBlock(World world)
    {
        super(world);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.setSize(1.0F, 1.0F);
    }

    public EntityAntiGravFallingBlock(World world, double x, double y, double z, IBlockState state)
    {
        this(world);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.setBlockState(state);
        this.setPosition(x, y, z);
        this.setOrigin(new BlockPos(this));
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(ORIGIN, BlockPos.ORIGIN);
        this.dataManager.register(BLOCK_NAME, Block.REGISTRY.getNameForObject(Blocks.AIR).toString());
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    public void onUpdate()
    {
        IBlockState state = this.getBlockState();
        BlockPos pos = this.getPosition();

        if (!this.world.isRemote && !this.hasActivated)
        {
            if (this.world.getBlockState(pos).getBlock() == state.getBlock())
            {
                this.world.setBlockToAir(pos);
            }
            else
            {
                this.setDead();
            }
            this.hasActivated = true;
        }

        if (this.ticksExisted > 200)
        {
            if (!this.world.isRemote)
            {
                this.entityDropItem(new ItemStack(state.getBlock()), 0.0F);
                SoundType type = state.getBlock().getSoundType(state, this.world, pos, null);
                this.playSound(type.getBreakSound(), (type.getVolume() + 1.0F) / 2.0F, type.getPitch() * 0.8F);
            }
            else
            {
                FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(pos, state);
            }
            this.setDead();
        }
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            if (!this.hasNoGravity())
            {
                this.motionY += 0.005D;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

            if (!this.world.isAirBlock(pos.up()))
            {
                if (!this.world.isRemote)
                {
                    this.world.setBlockState(pos, state);
                    this.setDead();
                }
            }

            if (this.rand.nextInt(8) == 0)
            {
                if (BlockFalling.canFallThrough(this.world.getBlockState(pos.up())))
                {
                    double x = (float)this.posX - 0.5D + this.rand.nextFloat();
                    double y = this.posY - 0.05D;
                    double z = (float)this.posZ - 0.5D + this.rand.nextFloat();
                    int color = this.rand.nextInt(4) == 0 ? ColorUtils.rgbToDecimal(47, 66, 87) : this.rand.nextInt(2) == 0 ? ColorUtils.rgbToDecimal(38, 53, 70) : ColorUtils.rgbToDecimal(47, 62, 79);

                    if (state.getBlock() == KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK)
                    {
                        color = this.rand.nextInt(3) == 0 ? ColorUtils.rgbToDecimal(88, 88, 88) : this.rand.nextInt(2) == 0 ? ColorUtils.rgbToDecimal(206, 105, 10) : ColorUtils.rgbToDecimal(16, 136, 207);
                    }
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_FALLING_DUST, x, y, z, new Object[] { color });
                }
            }
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setString("Block", Block.REGISTRY.getNameForObject(this.getBlockState().getBlock()).toString());
        nbt.setInteger("TicksExisted", this.ticksExisted);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.setBlockState(Block.getBlockFromName(nbt.getString("Block")).getDefaultState());
        this.ticksExisted = nbt.getInteger("TicksExisted");
        this.hasActivated = this.ticksExisted > 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderOnFire()
    {
        return false;
    }

    public IBlockState getBlockState()
    {
        return Block.getBlockFromName(this.dataManager.get(BLOCK_NAME)).getDefaultState();
    }

    private void setBlockState(IBlockState state)
    {
        this.dataManager.set(BLOCK_NAME, Block.REGISTRY.getNameForObject(state.getBlock()).toString());
    }

    private void setOrigin(BlockPos pos)
    {
        this.dataManager.set(ORIGIN, pos);
    }

    @SideOnly(Side.CLIENT)
    public BlockPos getOrigin()
    {
        return this.dataManager.get(ORIGIN);
    }
}
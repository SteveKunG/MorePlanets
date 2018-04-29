package stevekung.mods.moreplanets.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.BlockKoentus;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class EntityAntiGravFallingBlock extends Entity
{
    private boolean hasActivated = false;
    private static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(EntityAntiGravFallingBlock.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Integer> BLOCK_NAME = new DataParameter<>(20, DataSerializers.VARINT);
    private static final DataParameter<Integer> BLOCK_METADATA = new DataParameter<>(21, DataSerializers.VARINT);

    public EntityAntiGravFallingBlock(World world)
    {
        super(world);
    }

    public EntityAntiGravFallingBlock(World world, double x, double y, double z, IBlockState state)
    {
        super(world);
        this.setBlockState(state);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.setPosition(x, y + (1.0F - this.height) / 2.0F, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
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
        this.dataManager.register(BLOCK_NAME, 2);
        this.dataManager.register(BLOCK_METADATA, 4);
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    public void onUpdate()
    {
        if (!this.world.isRemote && !this.hasActivated)
        {
            BlockPos pos = new BlockPos(this);

            if (this.world.getBlockState(pos).getBlock() == this.getBlockState().getBlock())
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
                this.entityDropItem(new ItemStack(this.getBlockState().getBlock(), 1, this.getBlockState().getBlock().damageDropped(this.getBlockState())), 0.0F);
            }
            this.setDead();
        }
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.motionY += 0.01D;
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.98D;
            this.motionY *= 0.98D;
            this.motionZ *= 0.98D;

            BlockPos pos = new BlockPos(this);

            if (!this.world.isAirBlock(pos.up()))
            {
                if (!this.world.isRemote)
                {
                    this.world.setBlockState(pos, this.getBlockState());
                    this.setDead();
                }
                this.posX = pos.getX() + 0.5D;
                this.posY = pos.getY();
                this.posZ = pos.getZ() + 0.5D;
            }

            if (this.rand.nextInt(8) == 0)
            {
                BlockPos blockpos = this.getPosition().up();

                if (BlockFalling.canFallThrough(this.world.getBlockState(blockpos)))
                {
                    double d0 = (float)this.posX - 0.5D + this.rand.nextFloat();
                    double d1 = this.posY - 0.05D;
                    double d2 = (float)this.posZ - 0.5D + this.rand.nextFloat();
                    int color = this.rand.nextInt(4) == 0 ? ColorUtils.rgbToDecimal(47, 66, 87) : this.rand.nextInt(2) == 0 ? ColorUtils.rgbToDecimal(38, 53, 70) : ColorUtils.rgbToDecimal(47, 62, 79);

                    if (this.getBlockState().getValue(BlockKoentus.VARIANT) == BlockKoentus.BlockType.ANTI_GRAVITY_FRAGMENT_BLOCK)
                    {
                        color = this.rand.nextInt(3) == 0 ? ColorUtils.rgbToDecimal(88, 88, 88) : this.rand.nextInt(2) == 0 ? ColorUtils.rgbToDecimal(206, 105, 10) : ColorUtils.rgbToDecimal(16, 136, 207);
                    }
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CUSTOM_FALLING_DUST, d0, d1, d2, new Object[] { color });
                }
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        IBlockState state = this.getBlockState();
        Block block = state.getBlock();
        nbt.setString("Block", Block.REGISTRY.getNameForObject(block).toString());
        nbt.setByte("BlockState", (byte) block.getMetaFromState(state));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        Block block = Block.getBlockFromName(nbt.getString("Block"));
        this.setBlockState(block.getStateFromMeta(nbt.getByte("BlockState")));
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
        Block block = Block.getBlockById(this.dataManager.get(BLOCK_NAME));
        int meta = this.dataManager.get(BLOCK_METADATA);
        return block.getStateFromMeta(meta);
    }

    private void setBlockState(IBlockState state)
    {
        Block block = state.getBlock();
        this.dataManager.set(BLOCK_NAME, Block.REGISTRY.getIDForObject(block));
        this.dataManager.set(BLOCK_METADATA, block.getMetaFromState(state));
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
package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.entity.EntityBlackHole;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityDarkLightningBolt;
import stevekung.mods.moreplanets.util.JsonUtils;

public class TileEntityDarkEnergyReceiver extends TileEntityDummy implements IMultiBlock, IInventory, ISidedInventory
{
    private int facing;
    private ItemStack[] containingItems = new ItemStack[1];
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean activated;
    @NetworkedField(targetSide = Side.CLIENT)
    public int activatedTick;
    @NetworkedField(targetSide = Side.CLIENT)
    public int failedTick;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean activatedMessage;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean successful;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean spawnedBlackHole;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean failed;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean rendered;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean renderBlock;
    public float solarRotate0;
    public float solarRotate1;
    public float rodRotate;
    private ArrayList<BlockPos> posList = Lists.newArrayList();
    private ArrayList<BlockPos> obsidianPosList = Lists.newArrayList();

    public TileEntityDarkEnergyReceiver()
    {
        this.storage.setMaxExtract(1000);
        this.storage.setCapacity(250000.0F);
    }

    @Override
    public void setDisabled(int index, boolean disabled)
    {
        if (this.disableCooldown == 0)
        {
            this.disabled = disabled;
            this.disableCooldown = 0;
        }
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
    }

    @Override
    public void update()
    {
        super.update();

        if (!this.worldObj.isRemote)
        {
            if (this.activated && !this.disabled)
            {
                if (!this.successful)
                {
                    int radius = 32;
                    List<Entity> entity = this.worldObj.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.pos.getX() - radius, this.pos.getY() - radius, this.pos.getZ() - radius, this.pos.getX() + radius, this.pos.getY() + radius, this.pos.getZ() + radius));

                    for (Entity around : entity)
                    {
                        if (around instanceof EntityLivingBase)
                        {
                            EntityLivingBase living = (EntityLivingBase) around;

                            if (!(living instanceof EntityPlayer))
                            {
                                living.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY.id, 200, 0));
                            }
                            if (around instanceof EntityPlayer)
                            {
                                EntityPlayer player = (EntityPlayer) around;

                                if (!player.capabilities.isCreativeMode && !player.isPotionActive(MPPotions.DARK_ENERGY_PROTECTION))
                                {
                                    player.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY.id, 200, 0));
                                }
                            }
                        }
                    }
                }

                if (this.activatedTick < this.getSuccessfulTick())
                {
                    if (this.activatedTick % 20 == 0)
                    {
                        this.worldObj.playSoundEffect(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "moreplanets:ambient.machine.ambient", 1.0F, 1.025F);
                    }
                    if (this.activatedTick >= this.getSuccessfulTick() - 5 && this.activatedTick <= this.getSuccessfulTick())
                    {
                        this.worldObj.playSoundEffect(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "moreplanets:ambient.machine.stop", 1.0F, 1.0F);
                    }

                    if (!this.failed)
                    {
                        if (this.worldObj.rand.nextInt(50) == 0)
                        {
                            EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                            bolt.setLocationAndAngles(this.pos.getX() + 3, this.pos.getY() + 2.5D, this.pos.getZ() + 3, 0.0F, 0.0F);
                            this.worldObj.spawnEntityInWorld(bolt);
                        }
                        if (this.worldObj.rand.nextInt(50) == 0)
                        {
                            EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                            bolt.setLocationAndAngles(this.pos.getX() - 3, this.pos.getY() + 2.5D, this.pos.getZ() + 3, 0.0F, 0.0F);
                            this.worldObj.spawnEntityInWorld(bolt);
                        }
                        if (this.worldObj.rand.nextInt(50) == 0)
                        {
                            EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                            bolt.setLocationAndAngles(this.pos.getX() + 3, this.pos.getY() + 2.5D, this.pos.getZ() - 3, 0.0F, 0.0F);
                            this.worldObj.spawnEntityInWorld(bolt);
                        }
                        if (this.worldObj.rand.nextInt(50) == 0)
                        {
                            EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                            bolt.setLocationAndAngles(this.pos.getX() - 3, this.pos.getY() + 2.5D, this.pos.getZ() - 3, 0.0F, 0.0F);
                            this.worldObj.spawnEntityInWorld(bolt);
                        }
                        this.activatedTick++;
                    }
                }

                if (this.failed)
                {
                    this.failedTick++;

                    if (this.worldObj.rand.nextInt(12) == 0)
                    {
                        EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                        bolt.setLocationAndAngles(this.pos.getX() + 3, this.pos.getY() + 2.5D, this.pos.getZ() + 3, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(bolt);
                    }
                    if (this.worldObj.rand.nextInt(12) == 0)
                    {
                        EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                        bolt.setLocationAndAngles(this.pos.getX() - 3, this.pos.getY() + 2.5D, this.pos.getZ() + 3, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(bolt);
                    }
                    if (this.worldObj.rand.nextInt(12) == 0)
                    {
                        EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                        bolt.setLocationAndAngles(this.pos.getX() + 3, this.pos.getY() + 2.5D, this.pos.getZ() - 3, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(bolt);
                    }
                    if (this.worldObj.rand.nextInt(12) == 0)
                    {
                        EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                        bolt.setLocationAndAngles(this.pos.getX() - 3, this.pos.getY() + 2.5D, this.pos.getZ() - 3, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(bolt);
                    }
                    if (this.failedTick % 20 == 0)
                    {
                        this.worldObj.playSoundEffect(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "moreplanets:ambient.machine.ambient", 1.0F, 1.025F);
                        this.worldObj.playSoundEffect(this.pos.getX(), this.pos.getY(), this.pos.getZ(), "moreplanets:ambient.machine.danger", 5.0F, 1.0F);
                    }
                }

                if (!(this.checkBlock(this.worldObj, this.pos.down().north(), DionaBlocks.ZELIUS_EGG.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().south(), DionaBlocks.ZELIUS_EGG.getDefaultState())
                        && this.checkBlock(this.worldObj, this.pos.down().east(), DionaBlocks.ZELIUS_EGG.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().west(), DionaBlocks.ZELIUS_EGG.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().down().north(), DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState())
                        && this.checkBlock(this.worldObj, this.pos.down().down().south(), DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().down().east(), DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().down().west(), DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState())
                        && this.checkBlock(this.worldObj, this.pos.down().down().north().east(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().down().south().east(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.down().down().north().west(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState())
                        && this.checkBlock(this.worldObj, this.pos.down().down().south().west(), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(3, -1, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(3, -1, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState())
                        && this.checkBlock(this.worldObj, this.pos.add(-3, -1, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(-3, -1, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(3, 0, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1))
                        && this.checkBlock(this.worldObj, this.pos.add(3, 0, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1)) && this.checkBlock(this.worldObj, this.pos.add(-3, 0, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1)) && this.checkBlock(this.worldObj, this.pos.add(-3, 0, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(1))
                        && this.checkBlock(this.worldObj, this.pos.add(3, 1, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(3, 1, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(-3, 1, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState())
                        && this.checkBlock(this.worldObj, this.pos.add(-3, 1, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getDefaultState()) && this.checkBlock(this.worldObj, this.pos.add(3, 2, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2)) && this.checkBlock(this.worldObj, this.pos.add(3, 2, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2))
                        && this.checkBlock(this.worldObj, this.pos.add(-3, 2, 3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2)) && this.checkBlock(this.worldObj, this.pos.add(-3, 2, -3), DionaBlocks.INFECTED_CRYSTALLIZE_PART.getStateFromMeta(2))))
                {
                    this.failed = true;
                }
                if (this.getEnergyStoredGC() < 20000.0F)
                {
                    this.failed = true;
                }

                for (int yRender = this.pos.getY(); yRender < 256; yRender++)
                {
                    Block block = this.worldObj.getBlockState(new BlockPos(this.pos.getX(), yRender, this.pos.getZ())).getBlock();

                    if (block.isOpaqueCube() && block != DionaBlocks.DARK_ENERGY_CORE)
                    {
                        this.failed = true;
                    }

                    if (this.facing == 0 || this.facing == 180)
                    {
                        block = this.worldObj.getBlockState(new BlockPos(this.pos.getX() + 1, yRender + 1, this.pos.getZ())).getBlock();

                        if (block.isOpaqueCube() && block != DionaBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }

                        block = this.worldObj.getBlockState(new BlockPos(this.pos.getX() - 1, yRender + 1, this.pos.getZ())).getBlock();

                        if (block.isOpaqueCube() && block != DionaBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }
                    }
                    if (this.facing == -90 || this.facing == 90)
                    {
                        block = this.worldObj.getBlockState(new BlockPos(this.pos.getX(), yRender + 1, this.pos.getZ() + 1)).getBlock();

                        if (block.isOpaqueCube() && block != DionaBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }

                        block = this.worldObj.getBlockState(new BlockPos(this.pos.getX(), yRender + 1, this.pos.getZ() - 1)).getBlock();

                        if (block.isOpaqueCube() && block != DionaBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }
                    }
                }

                if (this.activatedTick == this.getSuccessfulTick())
                {
                    if (!this.successful)
                    {
                        if (this.worldObj.getBlockState(this.getPos().up()).getBlock() != DionaBlocks.DARK_ENERGY_CORE)
                        {
                            EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.worldObj);
                            bolt.setLocationAndAngles(this.pos.getX(), this.pos.getY() + 2.5D, this.pos.getZ(), 0.0F, 0.0F);
                            this.worldObj.playSoundEffect(this.pos.getX(), this.pos.getY() + 2.5D, this.pos.getZ(), "random.explode", 0.5F, 0.5F + this.worldObj.rand.nextFloat() * 0.2F);
                            this.worldObj.spawnEntityInWorld(bolt);
                            this.worldObj.setBlockState(this.getPos().up(), DionaBlocks.DARK_ENERGY_CORE.getDefaultState());
                        }

                        if (!this.posList.isEmpty())
                        {
                            for (BlockPos pos : this.posList)
                            {
                                this.worldObj.setBlockToAir(pos);
                            }
                        }
                        if (!this.obsidianPosList.isEmpty())
                        {
                            for (BlockPos pos : this.obsidianPosList)
                            {
                                this.worldObj.setBlockState(pos, Blocks.obsidian.getDefaultState());
                            }
                        }
                        this.setDisabled(0, true);
                        this.activatedMessage = true;
                        this.successful = true;
                        FMLClientHandler.instance().getClient().thePlayer.addChatMessage(new JsonUtils().text(StatCollector.translateToLocal("gui.status.dark_energy_core_created.name")).setChatStyle(new JsonUtils().colorFromConfig("green")));
                    }
                }

                if (this.failedTick > 600)
                {
                    if (!this.spawnedBlackHole)
                    {
                        EntityBlackHole blackHole = new EntityBlackHole(this.worldObj);
                        blackHole.setLocationAndAngles(this.pos.getX() + 0.5D, this.pos.getY() + 2.0D, this.pos.getZ() + 0.5D, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(blackHole);
                        this.worldObj.createExplosion(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 5.0F, true);
                        this.worldObj.destroyBlock(this.pos, false);
                        this.spawnedBlackHole = true;
                    }
                }
            }
        }
        else
        {
            if (this.activated && !this.successful)
            {
                this.solarRotate0++;
                this.solarRotate0 %= 180;

                if (this.getEnergyStoredGC() > 0.0F && !this.failed)
                {
                    if (this.rodRotate < 57)
                    {
                        this.rodRotate += 2.0F;
                    }
                }
                else
                {
                    if (this.rodRotate > 0)
                    {
                        this.rodRotate -= 0.25F;
                    }
                }
            }
            if (this.successful && this.solarRotate0 < 180)
            {
                this.solarRotate0++;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.facing = nbt.getInteger("Facing");
        this.activated = nbt.getBoolean("Activated");
        this.activatedMessage = nbt.getBoolean("ActivatedMessage");
        this.successful = nbt.getBoolean("Successful");
        this.spawnedBlackHole = nbt.getBoolean("SpawnedBlackHole");
        this.failed = nbt.getBoolean("Failed");
        this.rendered = nbt.getBoolean("Rendered");
        this.activatedTick = nbt.getInteger("ActivatedTick");
        this.failedTick = nbt.getInteger("FailedTick");
        NBTTagList list = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound compound = list.getCompoundTagAt(i);
            int slot = compound.getByte("Slot") & 255;

            if (slot < this.containingItems.length)
            {
                this.containingItems[slot] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("Facing", this.facing);
        nbt.setBoolean("Activated", this.activated);
        nbt.setBoolean("ActivatedMessage", this.activatedMessage);
        nbt.setBoolean("Successful", this.successful);
        nbt.setBoolean("SpawnedBlackHole", this.spawnedBlackHole);
        nbt.setBoolean("Failed", this.failed);
        nbt.setBoolean("Rendered", this.rendered);
        nbt.setInteger("ActivatedTick", this.activatedTick);
        nbt.setInteger("FailedTick", this.failedTick);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.containingItems.length; ++i)
        {
            if (this.containingItems[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.containingItems[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        nbt.setTag("Items", list);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Facing", this.facing);
        nbt.setBoolean("Activated", this.activated);
        nbt.setBoolean("ActivatedMessage", this.activatedMessage);
        nbt.setBoolean("Successful", this.successful);
        nbt.setBoolean("SpawnedBlackHole", this.spawnedBlackHole);
        nbt.setBoolean("Failed", this.failed);
        nbt.setBoolean("Rendered", this.rendered);
        nbt.setInteger("ActivatedTick", this.activatedTick);
        nbt.setInteger("FailedTick", this.failedTick);
        return new S35PacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        if (pkt.getTileEntityType() == -1)
        {
            NBTTagCompound nbt = pkt.getNbtCompound();
            this.facing = nbt.getInteger("Facing");
            this.activated = nbt.getBoolean("Activated");
            this.activatedMessage = nbt.getBoolean("ActivatedMessage");
            this.successful = nbt.getBoolean("Successful");
            this.spawnedBlackHole = nbt.getBoolean("SpawnedBlackHole");
            this.failed = nbt.getBoolean("Failed");
            this.rendered = nbt.getBoolean("Rendered");
            this.activatedTick = nbt.getInteger("ActivatedTick");
            this.failedTick = nbt.getInteger("FailedTick");
        }
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return MPBlocks.DARK_ENERGY_RECEIVER.onBlockActivated(this.worldObj, this.mainBlockPosition, MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState(), player, player.getHorizontalFacing(), 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void onCreate(World world, BlockPos pos)
    {
        this.mainBlockPosition = pos;
    }

    @Override
    public void getPositions(BlockPos placedPosition, List<BlockPos> positions) {}

    @Override
    public void onDestroy(TileEntity tile)
    {
        BlockPos thisBlock = this.getPos();
        this.destroyBlock(thisBlock, true);

        if (this.getFacing() == 0 || this.getFacing() == 180)
        {
            for (int i = -1; i < 2; i++)
            {
                BlockPos pos = new BlockPos(thisBlock.getX() + i, thisBlock.getY(), thisBlock.getZ());

                if (this.worldObj.isRemote && this.worldObj.rand.nextDouble() < 0.1D)
                {
                    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(pos, MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState());
                }
                if (this.worldObj.getBlockState(pos) == MPBlocks.DUMMY_BLOCK.getStateFromMeta(1) || this.worldObj.getBlockState(pos) == MPBlocks.DUMMY_BLOCK.getStateFromMeta(2))
                {
                    this.worldObj.destroyBlock(pos, false);
                }
                this.worldObj.destroyBlock(this.getPos(), true);
            }
        }
        if (this.getFacing() == 90 || this.getFacing() == -90)
        {
            for (int i = -1; i < 2; i++)
            {
                BlockPos pos = new BlockPos(thisBlock.getX(), thisBlock.getY(), thisBlock.getZ() + i);

                if (this.worldObj.isRemote && this.worldObj.rand.nextDouble() < 0.1D)
                {
                    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(pos, MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState());
                }
                if (this.worldObj.getBlockState(pos) == MPBlocks.DUMMY_BLOCK.getStateFromMeta(3) || this.worldObj.getBlockState(pos) == MPBlocks.DUMMY_BLOCK.getStateFromMeta(4))
                {
                    this.worldObj.destroyBlock(pos, false);
                }
                this.worldObj.destroyBlock(this.getPos(), true);
            }
        }
    }

    @Override
    public boolean canRenderBreaking()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return 65536.0D;
    }

    @Override
    public boolean shouldUseEnergy()
    {
        return this.activated && !this.successful && !this.disabled;
    }

    @Override
    public EnumFacing getElectricInputDirection()
    {
        return EnumFacing.DOWN;
    }

    @Override
    public ItemStack getBatteryInSlot()
    {
        return this.getStackInSlot(0);
    }

    @Override
    public EnumFacing getFront()
    {
        return EnumFacing.DOWN;
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.containingItems[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.containingItems[slot] != null)
        {
            ItemStack itemStack;

            if (this.containingItems[slot].stackSize <= amount)
            {
                itemStack = this.containingItems[slot];
                this.containingItems[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = this.containingItems[slot].splitStack(amount);

                if (this.containingItems[slot].stackSize == 0)
                {
                    this.containingItems[slot] = null;
                }
                return itemStack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int slot)
    {
        if (this.containingItems[slot] != null)
        {
            ItemStack itemStack = this.containingItems[slot];
            this.containingItems[slot] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.containingItems[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getName()
    {
        return StatCollector.translateToLocal("container.dark_energy_receiver.name");
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public IChatComponent getDisplayName()
    {
        return new ChatComponentTranslation(this.getName());
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return this.isItemValidForSlot(slotID, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return slotID == 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return slotID == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear() {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    public boolean isActivated()
    {
        return this.activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
        this.rendered = true;
    }

    public void setFacing(int facing)
    {
        this.facing = facing;
    }

    public int getFacing()
    {
        return this.facing;
    }

    public int getSuccessfulTick()
    {
        return 12000;
    }

    private boolean checkBlock(World world, BlockPos pos, IBlockState state)
    {
        if (state != MPBlocks.DUNGEON_GLOWSTONE.getDefaultState() && state != DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK.getDefaultState())
        {
            this.posList.add(pos);
        }
        else
        {
            this.obsidianPosList.add(pos);
        }
        if (world.getBlockState(pos) != state)
        {
            return false;
        }
        return true;
    }

    private boolean destroyBlock(BlockPos pos, boolean dropBlock)
    {
        IBlockState iblockstate = this.worldObj.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block.getMaterial() == Material.air)
        {
            return false;
        }
        else
        {
            this.worldObj.playAuxSFX(2001, pos, Block.getStateId(iblockstate));

            if (dropBlock)
            {
                ItemStack machine = new ItemStack(MPBlocks.DARK_ENERGY_RECEIVER);
                TileEntityDarkEnergyReceiver electric = this;

                if (electric.getEnergyStoredGC() > 0)
                {
                    machine.setTagCompound(new NBTTagCompound());
                    machine.getTagCompound().setFloat("EnergyStored", electric.getEnergyStoredGC());
                }
                if (!electric.successful && !electric.failed)
                {
                    Block.spawnAsEntity(this.worldObj, pos, machine);
                }
            }
            return this.worldObj.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        }
    }

    public String getGUIStatus()
    {
        if (this.successful)
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.off.name");
        }
        if (this.getEnergyStoredGC() == 0)
        {
            return EnumColor.DARK_RED + GCCoreUtil.translate("gui.status.missingpower.name");
        }
        if (this.getDisabled(0))
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.ready.name");
        }
        if (this.getEnergyStoredGC() < this.storage.getMaxExtract())
        {
            return EnumColor.ORANGE + GCCoreUtil.translate("gui.status.missingpower.name");
        }
        return EnumColor.DARK_GREEN + GCCoreUtil.translate("gui.status.active.name");
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
    }
}
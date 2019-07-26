package stevekung.mods.moreplanets.tileentity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.core.event.ClientEventHandler;
import stevekung.mods.moreplanets.entity.EntityBlackHole;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.planets.diona.entity.EntityDarkLightningBolt;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityInfectedCrystallizedEnderCore;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityZeliusEgg;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class TileEntityDarkEnergyReceiver extends TileEntityDummy implements IMultiBlock
{
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean activated;
    @NetworkedField(targetSide = Side.CLIENT)
    public int activatedTick;
    public int failedTick;
    public boolean activatedMessage;
    @NetworkedField(targetSide = Side.CLIENT)
    public boolean successful;
    public boolean spawnedBlackHole;
    public boolean failed;
    public boolean rendered;
    public float solarRotate;
    public float rodUp;
    public static final Map<BlockPos, IBlockState> multiBlockLists = new HashMap<>();
    public final Map<BlockPos, TileEntity> multiTileClientLists = new HashMap<>();
    public final Map<BlockPos, IBlockState> multiBlockClientLists = new HashMap<>();
    public boolean initMultiBlock;
    private final TileEntityZeliusEgg egg = new TileEntityZeliusEgg();
    private final TileEntityInfectedCrystallizedEnderCore core = new TileEntityInfectedCrystallizedEnderCore();

    static
    {
        multiBlockLists.put(new BlockPos(1, -1, 0), MPBlocks.ZELIUS_EGG.getDefaultState());
        multiBlockLists.put(new BlockPos(0, -1, 1), MPBlocks.ZELIUS_EGG.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -1, 0), MPBlocks.ZELIUS_EGG.getDefaultState());
        multiBlockLists.put(new BlockPos(0, -1, -1), MPBlocks.ZELIUS_EGG.getDefaultState());

        multiBlockLists.put(new BlockPos(1, -2, 1), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -2, 1), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        multiBlockLists.put(new BlockPos(1, -2, -1), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -2, -1), MPBlocks.DUNGEON_GLOWSTONE.getDefaultState());

        multiBlockLists.put(new BlockPos(1, -2, 0), MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(0, -2, 1), MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(-1, -2, 0), MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());
        multiBlockLists.put(new BlockPos(0, -2, -1), MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState());

        multiBlockLists.put(new BlockPos(3, -1, 3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(3, 0, 3), MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        multiBlockLists.put(new BlockPos(3, 1, 3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(3, 2, 3), MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());

        multiBlockLists.put(new BlockPos(-3, -1, 3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(-3, 0, 3), MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        multiBlockLists.put(new BlockPos(-3, 1, 3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(-3, 2, 3), MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());

        multiBlockLists.put(new BlockPos(3, -1, -3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(3, 0, -3), MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        multiBlockLists.put(new BlockPos(3, 1, -3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(3, 2, -3), MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());

        multiBlockLists.put(new BlockPos(-3, -1, -3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(-3, 0, -3), MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
        multiBlockLists.put(new BlockPos(-3, 1, -3), MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT.getDefaultState());
        multiBlockLists.put(new BlockPos(-3, 2, -3), MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());
    }

    public TileEntityDarkEnergyReceiver()
    {
        super("container.dark_energy_receiver.name");
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
        this.initMultiBlock = true;
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
        this.markDirty();

        if (this.initMultiBlock)
        {
            this.multiTileClientLists.put(new BlockPos(1, -1, 0), this.egg);
            this.multiTileClientLists.put(new BlockPos(0, -1, 1), this.egg);
            this.multiTileClientLists.put(new BlockPos(-1, -1, 0), this.egg);
            this.multiTileClientLists.put(new BlockPos(0, -1, -1), this.egg);
            this.multiTileClientLists.put(new BlockPos(3, 2, 3), this.core);
            this.multiTileClientLists.put(new BlockPos(-3, 2, 3), this.core);
            this.multiTileClientLists.put(new BlockPos(3, 2, -3), this.core);
            this.multiTileClientLists.put(new BlockPos(-3, 2, -3), this.core);
            this.multiBlockClientLists.putAll(multiBlockLists);
            this.initMultiBlock = false;
        }

        if (this.world.isRemote)
        {
            ClientEventHandler.receiverRenderPos.forEach(renderPos ->
            {
                this.multiBlockClientLists.entrySet().removeIf(entry -> this.pos.equals(renderPos) && this.world.getBlockState(this.pos.add(entry.getKey())) == entry.getValue());
                this.multiTileClientLists.entrySet().removeIf(entry -> this.pos.equals(renderPos) && this.world.isRemote && this.world.getTileEntity(this.pos.add(entry.getKey())) != null && this.world.getTileEntity(this.pos.add(entry.getKey())).getClass().equals(entry.getValue().getClass()));
            });
        }

        if (!this.world.isRemote)
        {
            if (this.activated && !this.disabled)
            {
                if (!this.successful)
                {
                    int radius = 32;

                    this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.pos.getX() - radius, this.pos.getY() - radius, this.pos.getZ() - radius, this.pos.getX() + radius, this.pos.getY() + radius, this.pos.getZ() + radius)).forEach(entity ->
                    {
                        if (entity instanceof EntityLivingBase)
                        {
                            EntityLivingBase living = (EntityLivingBase) entity;

                            if (!(living instanceof EntityPlayer))
                            {
                                living.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY, 200, 0));
                            }
                            if (entity instanceof EntityPlayer)
                            {
                                EntityPlayer player = (EntityPlayer) entity;

                                if (!player.capabilities.isCreativeMode && !player.isPotionActive(MPPotions.DARK_ENERGY_PROTECTION))
                                {
                                    player.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY, 200, 0));
                                }
                            }
                        }
                    });
                }

                if (this.activatedTick < this.getSuccessfulTick())
                {
                    if (this.activatedTick % 20 == 0)
                    {
                        this.world.playSound(null, this.getPos(), MPSounds.MACHINE_ACTIVATE_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.025F);
                    }
                    if (this.activatedTick >= this.getSuccessfulTick() - 5 && this.activatedTick <= this.getSuccessfulTick())
                    {
                        this.world.playSound(null, this.getPos(), MPSounds.MACHINE_STOP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }

                    if (!this.failed)
                    {
                        if (this.world.rand.nextInt(30) == 0)
                        {
                            EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.world, this.world.rand.nextBoolean() ? this.pos.getX() + 3 : this.pos.getX() - 3, this.pos.getY() + 2.5D, this.world.rand.nextBoolean() ? this.pos.getZ() + 3 : this.pos.getZ() - 3);
                            this.world.spawnEntity(bolt);
                        }
                        this.activatedTick++;
                    }
                }

                if (this.failed)
                {
                    this.failedTick++;

                    if (this.world.rand.nextInt(10) == 0)
                    {
                        EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.world, this.world.rand.nextBoolean() ? this.pos.getX() + 3 : this.pos.getX() - 3, this.pos.getY() + 2.5D, this.world.rand.nextBoolean() ? this.pos.getZ() + 3 : this.pos.getZ() - 3);
                        this.world.spawnEntity(bolt);
                    }
                    if (this.failedTick % 20 == 0)
                    {
                        this.world.playSound(null, this.getPos(), MPSounds.MACHINE_ACTIVATE_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.025F);
                        this.world.playSound(null, this.getPos(), MPSounds.MACHINE_DANGER, SoundCategory.BLOCKS, 5.0F, 1.0F);
                    }
                }

                if (TileEntityDarkEnergyReceiver.checkValidMultiblock(this.pos, this.world) || this.getEnergyStoredGC() < 20000.0F)
                {
                    this.failed = true;
                }

                for (int yRender = this.pos.getY(); yRender < 256; yRender++)
                {
                    IBlockState state = this.world.getBlockState(new BlockPos(this.pos.getX(), yRender, this.pos.getZ()));

                    if (state.isOpaqueCube() && state.getBlock() != MPBlocks.DARK_ENERGY_CORE)
                    {
                        this.failed = true;
                    }

                    if (this.getBlockMetadata() == 0 || this.getBlockMetadata() == 2)
                    {
                        state = this.world.getBlockState(new BlockPos(this.pos.getX() + 1, yRender + 1, this.pos.getZ()));

                        if (state.isOpaqueCube() && state.getBlock() != MPBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }

                        state = this.world.getBlockState(new BlockPos(this.pos.getX() - 1, yRender + 1, this.pos.getZ()));

                        if (state.isOpaqueCube() && state.getBlock() != MPBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }
                    }
                    else
                    {
                        state = this.world.getBlockState(new BlockPos(this.pos.getX(), yRender + 1, this.pos.getZ() + 1));

                        if (state.isOpaqueCube() && state.getBlock() != MPBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }

                        state = this.world.getBlockState(new BlockPos(this.pos.getX(), yRender + 1, this.pos.getZ() - 1));

                        if (state.isOpaqueCube() && state.getBlock() != MPBlocks.DARK_ENERGY_CORE)
                        {
                            this.failed = true;
                        }
                    }
                }

                if (!this.successful && this.activatedTick == this.getSuccessfulTick())
                {
                    if (this.world.getBlockState(this.getPos().up()).getBlock() != MPBlocks.DARK_ENERGY_CORE)
                    {
                        EntityDarkLightningBolt bolt = new EntityDarkLightningBolt(this.world, this.pos.getX(), this.pos.getY() + 2.5D, this.pos.getZ());
                        this.world.playSound(null, this.getPos().add(0, 2, 0), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
                        this.world.spawnEntity(bolt);
                        this.world.setBlockState(this.getPos().up(), MPBlocks.DARK_ENERGY_CORE.getDefaultState());
                    }
                    TileEntityDarkEnergyReceiver.multiBlockLists.forEach((mPos, state) ->
                    {
                        BlockPos pos = this.pos.add(mPos);

                        if (state != MPBlocks.DUNGEON_GLOWSTONE.getDefaultState() && state != MPBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK.getDefaultState())
                        {
                            for (int i = 0; i < 120; i++)
                            {
                                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.DARK_PORTAL, pos.getX() + this.world.rand.nextDouble() * 1.0D, pos.getY() + this.world.rand.nextDouble() * 1.0D, pos.getZ() + this.world.rand.nextDouble() * 1.0D, 0.0D, -this.world.rand.nextDouble(), 0.0D);
                            }
                            this.world.setBlockToAir(pos);
                        }
                        else
                        {
                            this.world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
                        }
                    });
                    this.setDisabled(0, true);
                    this.activatedMessage = true;
                    this.successful = true;
                }

                if (this.failedTick > 600)
                {
                    if (!this.spawnedBlackHole)
                    {
                        EntityBlackHole blackHole = new EntityBlackHole(this.world);
                        blackHole.setLocationAndAngles(this.pos.getX() + 0.5D, this.pos.getY() + 2.0D, this.pos.getZ() + 0.5D, 0.0F, 0.0F);
                        blackHole.setRange(64);
                        blackHole.setMaxLife(6000);
                        this.world.spawnEntity(blackHole);
                        this.world.createExplosion(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 5.0F, true);
                        this.world.destroyBlock(this.pos, false);
                        this.spawnedBlackHole = true;
                    }
                }
            }
        }
        else
        {
            if (this.activated && !this.successful)
            {
                this.solarRotate++;
                this.solarRotate %= 180;

                if (this.getEnergyStoredGC() > 0.0F && !this.failed)
                {
                    if (this.rodUp < 58)
                    {
                        this.rodUp++;
                    }
                }
                else
                {
                    if (this.rodUp > 0)
                    {
                        this.rodUp -= 0.25F;
                    }
                }
            }
            if (this.successful && this.solarRotate < 180)
            {
                this.solarRotate++;
            }
            if (this.activatedMessage)
            {
                ClientUtils.printClientMessage(JsonUtils.create(LangUtils.translate("gui.status.dark_energy_core_created.name")).setStyle(JsonUtils.green()));
                this.activatedMessage = false;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.activated = nbt.getBoolean("Activated");
        this.activatedMessage = nbt.getBoolean("ActivatedMessage");
        this.successful = nbt.getBoolean("Successful");
        this.spawnedBlackHole = nbt.getBoolean("SpawnedBlackHole");
        this.failed = nbt.getBoolean("Failed");
        this.rendered = nbt.getBoolean("Rendered");
        this.activatedTick = nbt.getInteger("ActivatedTick");
        this.failedTick = nbt.getInteger("FailedTick");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("Activated", this.activated);
        nbt.setBoolean("ActivatedMessage", this.activatedMessage);
        nbt.setBoolean("Successful", this.successful);
        nbt.setBoolean("SpawnedBlackHole", this.spawnedBlackHole);
        nbt.setBoolean("Failed", this.failed);
        nbt.setBoolean("Rendered", this.rendered);
        nbt.setInteger("ActivatedTick", this.activatedTick);
        nbt.setInteger("FailedTick", this.failedTick);
        return nbt;
    }

    @Override
    public boolean onActivated(EntityPlayer player)
    {
        return MPBlocks.DARK_ENERGY_RECEIVER.onBlockActivated(this.world, this.mainBlockPosition, MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState(), player, player.getActiveHand(), player.getHorizontalFacing(), 0.0F, 0.0F, 0.0F);
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

        if (this.getBlockMetadata() == 0 || this.getBlockMetadata() == 2)
        {
            for (int i = -1; i < 2; i++)
            {
                BlockPos pos = new BlockPos(thisBlock.getX() + i, thisBlock.getY(), thisBlock.getZ());

                if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
                {
                    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(pos, MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState());
                }
                if (this.world.getBlockState(pos).getBlock() == MPBlocks.DER_SOLAR1_DUMMY || this.world.getBlockState(pos).getBlock() == MPBlocks.DER_SOLAR2_DUMMY)
                {
                    this.world.destroyBlock(pos, false);
                }
                this.world.destroyBlock(this.getPos(), true);
            }
        }
        else
        {
            for (int i = -1; i < 2; i++)
            {
                BlockPos pos = new BlockPos(thisBlock.getX(), thisBlock.getY(), thisBlock.getZ() + i);

                if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
                {
                    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(pos, MPBlocks.DARK_ENERGY_RECEIVER.getDefaultState());
                }
                if (this.world.getBlockState(pos).getBlock() == MPBlocks.DER_SOLAR3_DUMMY || this.world.getBlockState(pos).getBlock() == MPBlocks.DER_SOLAR4_DUMMY)
                {
                    this.world.destroyBlock(pos, false);
                }
                this.world.destroyBlock(this.getPos(), true);
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
    public int[] getSlotsForFace(EnumFacing side)
    {
        return new int[] { 0 };
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack itemStack, EnumFacing side)
    {
        return slotID == 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return slotID == 0 && ItemElectricBase.isElectricItem(itemStack.getItem());
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public EnumBlockMultiType getMultiType()
    {
        return null;
    }

    public boolean isActivated()
    {
        return this.activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
        this.rendered = true;
        GalacticraftCore.packetPipeline.sendToDimension(new PacketSimpleMP(EnumSimplePacketMP.C_REMOVE_GUIDE_POS, this.world.provider.getDimension(), this.getPos()), this.world.provider.getDimension());
    }

    public int getSuccessfulTick()
    {
        return 12000;
    }

    public static boolean checkValidMultiblock(BlockPos pos, World world)
    {
        for (Map.Entry<BlockPos, IBlockState> list : TileEntityDarkEnergyReceiver.multiBlockLists.entrySet())
        {
            BlockPos blockpos = list.getKey();
            IBlockState state = list.getValue();
            BlockPos newPos = pos.add(blockpos);

            if (world.getBlockState(newPos) != state)
            {
                return true;
            }
        }
        return false;
    }

    public String getGUIStatus()
    {
        if (this.successful)
        {
            return TextFormatting.DARK_RED + LangUtils.translate("gui.status.off.name");
        }
        if (this.getEnergyStoredGC() == 0)
        {
            return TextFormatting.DARK_RED + LangUtils.translate("gui.status.missingpower.name");
        }
        if (this.getDisabled(0))
        {
            return TextFormatting.GOLD + LangUtils.translate("gui.status.ready.name");
        }
        if (this.getEnergyStoredGC() < this.storage.getMaxExtract())
        {
            return TextFormatting.GOLD + LangUtils.translate("gui.status.missingpower.name");
        }
        return TextFormatting.DARK_GREEN + LangUtils.translate("gui.status.active.name");
    }

    public String getGuiStatusWaila()
    {
        if (this.successful)
        {
            return TextFormatting.GREEN + LangUtils.translate("gui.status.dark_energy_core_created.name");
        }
        if (this.getEnergyStoredGC() == 0)
        {
            return TextFormatting.DARK_RED + LangUtils.translate("gui.status.dark_energy_offline.name");
        }
        if (this.getDisabled(0))
        {
            return TextFormatting.GOLD + LangUtils.translate("gui.status.ready.name");
        }
        return TextFormatting.DARK_GREEN + LangUtils.translate("gui.status.active.name");
    }

    private boolean destroyBlock(BlockPos pos, boolean dropBlock)
    {
        IBlockState iblockstate = this.world.getBlockState(pos);

        if (iblockstate.getMaterial() == Material.AIR)
        {
            return false;
        }
        else
        {
            this.world.playEvent(2001, pos, Block.getStateId(iblockstate));

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
                    Block.spawnAsEntity(this.world, pos, machine);
                }
            }
            return this.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
}
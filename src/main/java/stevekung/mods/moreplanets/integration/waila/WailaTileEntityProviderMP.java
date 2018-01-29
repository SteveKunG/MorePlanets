package stevekung.mods.moreplanets.integration.waila;

import java.util.List;
import java.util.UUID;

import mcp.mobius.waila.api.*;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import stevekung.mods.moreplanets.blocks.BlockDummy;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.tileentity.*;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

@WailaPlugin
public class WailaTileEntityProviderMP implements IWailaDataProvider, IWailaPlugin
{
    public WailaTileEntityProviderMP()
    {
        WailaUtil.dataProvider = this;
    }

    @Override
    public void register(IWailaRegistrar register)
    {
        MPLog.info("Waila Integration initialized");
        WailaUtil.register = register;

        HUDHandlerFurnaceMP.register(register);
        WailaUtil.register(TileEntityDarkEnergyReceiver.class, true, true, false, false, false);
        WailaUtil.register(TileEntityDarkEnergyCore.class, true, true, false, false, false);
        WailaUtil.register(TileEntitySpaceWarpPadFull.class, true, true, false, false, false);
        WailaUtil.register(TileEntityRocketCrusher.class, true, true, false, false, false);
        WailaUtil.register(TileEntityDarkEnergyGenerator.class, true, true, false, false, false);
        WailaUtil.register(TileEntityDarkEnergyStorageCluster.class, true, true, false, false, false);
        WailaUtil.register(TileEntityNuclearWasteStorageCluster.class, true, true, false, false, false);
        WailaUtil.register(TileEntityBlackHoleStorage.class, true, true, false, false, false);
        WailaUtil.register(TileEntityShieldGenerator.class, true, true, false, false, false);
        WailaUtil.register(TileEntityNuclearWasteGenerator.class, true, true, false, false, false);
        WailaUtil.register(IBlockDescription.class, false, false, true, false, false);
        WailaUtil.register(MPBlocks.DUMMY_BLOCK.getClass(), true, true, true, false, false);
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor,	IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        TileEntity tile = accessor.getTileEntity();
        Block block = accessor.getBlock();
        IBlockState state = accessor.getBlockState();
        NBTTagCompound nbt = accessor.getNBTData();

        if (nbt.hasKey("EnergyF") && !(block == MPBlocks.DUMMY_BLOCK && (state.getValue(BlockDummy.VARIANT) == BlockDummy.BlockType.NUCLEAR_WASTE_TANK_MIDDLE || state.getValue(BlockDummy.VARIANT) == BlockDummy.BlockType.NUCLEAR_WASTE_TANK_TOP)))
        {
            tooltip.add(TextFormatting.GREEN + GCCoreUtil.translate("gui.message.energy") + ": " + EnergyDisplayHelper.getEnergyDisplayS(nbt.getFloat("EnergyF")));
        }

        // block
        if (block == MPBlocks.DUMMY_BLOCK)
        {
            BlockDummy.BlockType type = state.getValue(BlockDummy.VARIANT);

            if (type == BlockDummy.BlockType.DARK_ENERGY_SOLAR1 || type == BlockDummy.BlockType.DARK_ENERGY_SOLAR2 || type == BlockDummy.BlockType.DARK_ENERGY_SOLAR3 || type == BlockDummy.BlockType.DARK_ENERGY_SOLAR4)
            {
                int process = nbt.getInteger("ActivatedTick") * 100 / 12000;
                int destruct = nbt.getInteger("FailedTick");

                if (destruct > 0)
                {
                    destruct = 600 - destruct;
                    tooltip.add(TextFormatting.DARK_RED + GCCoreUtil.translate("gui.status.destruct.name") + ": " + CommonRegisterHelper.ticksToElapsedTime(destruct));
                }
                else
                {
                    tooltip.add(GCCoreUtil.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
                }

                if (process > 0 && process < 100)
                {
                    tooltip.add("Process: " + process + "%");
                }
            }
            if (type == BlockDummy.BlockType.WARP_PAD)
            {
                if (tile instanceof TileEntityDummy)
                {
                    TileEntityDummy dummy = (TileEntityDummy) tile;
                    TileEntitySpaceWarpPadFull warp = (TileEntitySpaceWarpPadFull) accessor.getWorld().getTileEntity(dummy.mainBlockPosition);
                    String dimension = GCCoreUtil.translate("gui.status.unknown.name");
                    String name = GCCoreUtil.translate("gui.status.unknown.name");
                    String dest = GCCoreUtil.translate("gui.status.unknown.name");

                    if (warp.hasWarpCore() && warp.containingItems.get(1).hasTagCompound())
                    {
                        NBTTagCompound compound = warp.containingItems.get(1).getTagCompound();
                        dimension = String.valueOf(compound.getInteger("DimensionID"));
                        name = WorldUtil.getProviderForDimensionClient(compound.getInteger("DimensionID")).getDimensionType().getName();
                        dest = compound.getInteger("X") + " " + compound.getInteger("Y") + " " + compound.getInteger("Z");
                    }
                    tooltip.add(GCCoreUtil.translate("gui.status.dimension.name") + ": " + dimension + " ");
                    tooltip.add(GCCoreUtil.translate("gui.status.name.name") + ": " + name);
                    tooltip.add(GCCoreUtil.translate("gui.status.destination.name") + ": " + dest);
                }
            }
            if (type == BlockDummy.BlockType.SHIELD_GENERATOR_TOP)
            {
                int chargeCooldown = nbt.getInteger("ShieldChargeCooldown");
                tooltip.add(GCCoreUtil.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
                tooltip.add(GCCoreUtil.translate("gui.status.shield_damage.name") + ": " + nbt.getInteger("ShieldDamage"));
                tooltip.add(GCCoreUtil.translate("gui.status.shield_size.name") + ": " + nbt.getInteger("MaxShieldSize"));
                tooltip.add(GCCoreUtil.translate("gui.status.shield_capacity.name") + ": " + nbt.getInteger("ShieldCapacity") + "/" + nbt.getInteger("MaxShieldCapacity"));

                if (chargeCooldown > 0)
                {
                    tooltip.add(GCCoreUtil.translate("gui.status.shield_charge_cooldown.name") + ": " + chargeCooldown / 20);
                }
            }
        }

        // tile
        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            int process = nbt.getInteger("ActivatedTick") * 100 / 12000;
            int destruct = nbt.getInteger("FailedTick");

            if (destruct > 0)
            {
                destruct = 600 - destruct;
                tooltip.add(TextFormatting.DARK_RED + GCCoreUtil.translate("gui.status.destruct.name") + ": " + CommonRegisterHelper.ticksToElapsedTime(destruct));
            }
            else
            {
                tooltip.add(GCCoreUtil.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            }

            if (process > 0 && process < 100)
            {
                tooltip.add(GCCoreUtil.translate("gui.status.process.name") + ": " + process + "%");
            }
        }
        if (tile instanceof TileEntityDarkEnergyCore)
        {
            int transform = nbt.getInteger("ProduceTime");

            if (transform > 0)
            {
                tooltip.add(GCCoreUtil.translate("gui.status.transform_time.name") + ": " + CommonRegisterHelper.ticksToElapsedTime(transform));
            }
        }
        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            int generateWatts = nbt.getInteger("GenerateWatts");
            int fuel = nbt.getInteger("DarkEnergyFuel");
            tooltip.add(GCCoreUtil.translate("gui.message.generating.name") + ": " + (generateWatts > 0 ? EnergyDisplayHelper.getEnergyDisplayS(generateWatts) + "/t" : GCCoreUtil.translate("gui.status.not_generating.name")));
            tooltip.add(GCCoreUtil.translate("gui.status.dark_energy_fuel.name") + ": " + (fuel > 0 ? String.valueOf(fuel * 100 / 1000) + "%" : TextFormatting.GOLD + GCCoreUtil.translate("gui.status.empty.name")));
        }
        if (tile instanceof TileEntitySpaceWarpPadFull)
        {
            TileEntitySpaceWarpPadFull warp = (TileEntitySpaceWarpPadFull) tile;
            String dimension = GCCoreUtil.translate("gui.status.unknown.name");
            String name = GCCoreUtil.translate("gui.status.unknown.name");
            String dest = GCCoreUtil.translate("gui.status.unknown.name");

            if (warp.hasWarpCore() && warp.containingItems.get(1).hasTagCompound())
            {
                NBTTagCompound compound = warp.containingItems.get(1).getTagCompound();
                dimension = String.valueOf(compound.getInteger("DimensionID"));
                name = WorldUtil.getProviderForDimensionClient(compound.getInteger("DimensionID")).getDimensionType().getName();
                dest = compound.getInteger("X") + " " + compound.getInteger("Y") + " " + compound.getInteger("Z");
            }
            tooltip.add(GCCoreUtil.translate("gui.status.dimension.name") + ": " + dimension + " ");
            tooltip.add(GCCoreUtil.translate("gui.status.name.name") + ": " + name);
            tooltip.add(GCCoreUtil.translate("gui.status.destination.name") + ": " + dest);
        }
        if (tile instanceof TileEntityDarkEnergyStorageCluster || tile instanceof TileEntityNuclearWasteStorageCluster || tile instanceof TileEntityNuclearWasteGenerator)
        {
            tooltip.add(TextFormatting.GREEN + GCCoreUtil.translate("gui.message.max_energy") + ": " + EnergyDisplayHelper.getEnergyDisplayS(nbt.getFloat("MaxEnergy")));
            tooltip.add(GCCoreUtil.translate("gui.max_output.desc") + ": " + EnergyDisplayHelper.getEnergyDisplayS(nbt.getFloat("MaxOutput")) + "/t");

            if (nbt.hasKey("Status"))
            {
                tooltip.add(GCCoreUtil.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            }
        }
        if (tile instanceof TileEntityRocketCrusher)
        {
            int scale = (int) ((double) nbt.getInteger("ProcessTicks") / (double) nbt.getInteger("ProcessTime") * 100);

            if (scale > 0)
            {
                tooltip.add(GCCoreUtil.translate("gui.electric_compressor.desc.0") + ": " + scale + "%");
            }
        }
        if (tile instanceof TileEntityBlackHoleStorage)
        {
            String owner = GCCoreUtil.translate("gui.status.unknown.name");
            String collectMode = nbt.getString("CollectMode").equals("item") ? GCCoreUtil.translate("gui.status.collect_item.name") : nbt.getString("CollectMode").equals("item_and_xp") ? GCCoreUtil.translate("gui.status.collect_item_and_xp.name") : GCCoreUtil.translate("gui.status.collect_xp.name");

            try
            {
                owner = accessor.getWorld().getPlayerEntityByUUID(UUID.fromString(nbt.getString("OwnerUUID"))).getName();
            }
            catch (Exception e)
            {
                owner = "";
            }
            int xp = nbt.hasKey("XpFluid", Constants.NBT.TAG_COMPOUND) ? nbt.getCompoundTag("XpFluid").getInteger("Amount") : 0;
            tooltip.add(GCCoreUtil.translate("gui.status.owner.name") + ": " + owner);
            tooltip.add(GCCoreUtil.translate("gui.status.mode.name") + ": " + collectMode);
            tooltip.add(GCCoreUtil.translate("desc.bhs_xp.name") + ": " + xp + "/" + 1000000);
        }
        if (tile instanceof TileEntityShieldGenerator)
        {
            int chargeCooldown = nbt.getInteger("ShieldChargeCooldown");
            tooltip.add(GCCoreUtil.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            tooltip.add(GCCoreUtil.translate("gui.status.shield_damage.name") + ": " + nbt.getInteger("ShieldDamage"));
            tooltip.add(GCCoreUtil.translate("gui.status.shield_size.name") + ": " + nbt.getInteger("MaxShieldSize"));
            tooltip.add(GCCoreUtil.translate("gui.status.shield_capacity.name") + ": " + nbt.getInteger("ShieldCapacity") + "/" + nbt.getInteger("MaxShieldCapacity"));

            if (chargeCooldown > 0)
            {
                tooltip.add(GCCoreUtil.translate("gui.status.shield_charge_cooldown.name") + ": " + chargeCooldown / 20);
            }
        }
        return tooltip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        TileEntity tile = accessor.getTileEntity();
        Block block = accessor.getBlock();

        if (ConfigManagerMP.enableDescriptionInWaila)
        {
            if (block instanceof IBlockDescription)
            {
                IBlockDescription desc = (IBlockDescription) block;

                if (CommonRegisterHelper.isShiftKeyDown())
                {
                    desc.getDescription().addDescription(itemStack, tooltip);
                }
                else
                {
                    tooltip.add(GCCoreUtil.translate("desc.shift_info.name"));
                }
            }
            // block
            if (block == MPBlocks.DUMMY_BLOCK)
            {
                TileEntityDummy dummy = (TileEntityDummy) tile;

                if (accessor.getWorld().getBlockState(dummy.mainBlockPosition).getBlock() instanceof IBlockDescription)
                {
                    IBlockDescription desc = (IBlockDescription) accessor.getWorld().getBlockState(dummy.mainBlockPosition).getBlock();

                    if (CommonRegisterHelper.isShiftKeyDown())
                    {
                        desc.getDescription().addDescription(itemStack, tooltip);
                    }
                    else
                    {
                        tooltip.add(GCCoreUtil.translate("desc.shift_info.name"));
                    }
                }
            }
        }
        return tooltip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return tooltip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound nbt, World world, BlockPos pos)
    {
        if (tile instanceof TileEntityDummy)
        {
            TileEntityDummy dummy = (TileEntityDummy) tile;

            if (world.getTileEntity(dummy.mainBlockPosition) instanceof TileEntityDarkEnergyReceiver)
            {
                TileEntityDarkEnergyReceiver receiver = (TileEntityDarkEnergyReceiver) world.getTileEntity(dummy.mainBlockPosition);
                nbt.setString("Status", receiver.getGuiStatusWaila());
                return receiver.writeToNBT(nbt);
            }
            if (world.getTileEntity(dummy.mainBlockPosition) instanceof TileEntityShieldGenerator)
            {
                TileEntityShieldGenerator shield = (TileEntityShieldGenerator) world.getTileEntity(dummy.mainBlockPosition);
                nbt.setString("Status", shield.getStatus());
                return shield.writeToNBT(nbt);
            }
        }
        if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            TileEntityDarkEnergyReceiver receiver = (TileEntityDarkEnergyReceiver) tile;
            nbt.setString("Status", receiver.getGuiStatusWaila());
            return receiver.writeToNBT(nbt);
        }
        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            TileEntityDarkEnergyGenerator generator = (TileEntityDarkEnergyGenerator) tile;
            nbt.setInteger("GenerateWatts", generator.generateWatts);
            return generator.writeToNBT(nbt);
        }
        if (tile instanceof TileEntityDarkEnergyStorageCluster)
        {
            TileEntityDarkEnergyStorageCluster energy = (TileEntityDarkEnergyStorageCluster) tile;
            nbt.setFloat("MaxEnergy", energy.getMaxEnergyStoredGC());
            nbt.setFloat("MaxOutput", energy.storage.getMaxExtract());
            return energy.writeToNBT(nbt);
        }
        if (tile instanceof TileEntityNuclearWasteStorageCluster)
        {
            TileEntityNuclearWasteStorageCluster energy = (TileEntityNuclearWasteStorageCluster) tile;
            nbt.setFloat("MaxEnergy", energy.getMaxEnergyStoredGC());
            nbt.setFloat("MaxOutput", energy.storage.getMaxExtract());
            return energy.writeToNBT(nbt);
        }
        if (tile instanceof TileEntityRocketCrusher)
        {
            TileEntityRocketCrusher crusher = (TileEntityRocketCrusher) tile;
            nbt.setInteger("ProcessTime", crusher.processTimeRequired);
            return crusher.writeToNBT(nbt);
        }
        if (tile instanceof TileEntityShieldGenerator)
        {
            TileEntityShieldGenerator shield = (TileEntityShieldGenerator) tile;
            nbt.setString("Status", shield.getStatus());
            return shield.writeToNBT(nbt);
        }
        if (tile instanceof TileEntityNuclearWasteGenerator)
        {
            TileEntityNuclearWasteGenerator generator = (TileEntityNuclearWasteGenerator) tile;
            nbt.setString("Status", generator.getStatus());
            nbt.setFloat("MaxEnergy", generator.getMaxEnergyStoredGC());
            nbt.setFloat("MaxOutput", generator.storage.getMaxExtract());
            return generator.writeToNBT(nbt);
        }
        return tile.writeToNBT(nbt);
    }
}
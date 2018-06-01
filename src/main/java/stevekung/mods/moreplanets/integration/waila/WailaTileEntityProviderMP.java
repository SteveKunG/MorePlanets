package stevekung.mods.moreplanets.integration.waila;

import java.util.List;
import java.util.UUID;

import mcp.mobius.waila.api.*;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
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
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteTank;
import stevekung.mods.moreplanets.tileentity.*;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;
import stevekung.mods.stevekunglib.utils.CommonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

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
        LoggerMP.info("Waila Integration initialized");
        WailaUtil.register = register;

        HUDHandlerFurnaceMP.register(register);
        WailaUtil.register(TileEntityDarkEnergyReceiver.class, true, true, false, false, false);
        WailaUtil.register(TileEntityDarkEnergyCore.class, true, true, false, false, false);
        WailaUtil.register(TileEntitySpaceWarpPadFull.class, true, true, false, false, false);
        WailaUtil.register(TileEntityDarkEnergyGenerator.class, true, true, false, false, false);
        WailaUtil.register(TileEntityEnergyStorageClusterMP.class, true, true, false, false, false);
        WailaUtil.register(TileEntityBlackHoleStorage.class, true, true, false, false, false);
        WailaUtil.register(TileEntityShieldGenerator.class, true, true, false, false, false);
        WailaUtil.register(TileEntityNuclearWasteGenerator.class, true, true, false, false, false);
        WailaUtil.register(TileEntityNuclearWasteTank.class, true, true, false, false, false);
        WailaUtil.register(IDescription.class, false, false, true, false, false);
        WailaUtil.register(BlockDummy.class, true, true, true, false, false);
        WailaUtil.register(MPBlocks.DER_SOLAR1_DUMMY.getClass(), true, true, true, false, false);
        WailaUtil.register(MPBlocks.DER_SOLAR2_DUMMY.getClass(), true, true, true, false, false);
        WailaUtil.register(MPBlocks.DER_SOLAR3_DUMMY.getClass(), true, true, true, false, false);
        WailaUtil.register(MPBlocks.DER_SOLAR4_DUMMY.getClass(), true, true, true, false, false);
        WailaUtil.register(MPBlocks.WARP_PAD_DUMMY.getClass(), true, true, true, false, false);
        WailaUtil.register(MPBlocks.SHIELD_GENERATOR_DUMMY.getClass(), true, true, true, false, false);
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor,	IWailaConfigHandler config)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        TileEntity tile = accessor.getTileEntity();
        Block block = accessor.getBlock();
        NBTTagCompound nbt = accessor.getNBTData();

        if (nbt.hasKey("EnergyF") && !(block == MPBlocks.NWT_MIDDLE_DUMMY || block == MPBlocks.NWT_TOP_DUMMY) && !(tile instanceof TileEntityNuclearWasteTank))
        {
            tooltip.add(TextFormatting.GREEN + LangUtils.translate("gui.message.energy") + ": " + EnergyDisplayHelper.getEnergyDisplayS(nbt.getFloat("EnergyF")));
        }

        // block
        if (block == MPBlocks.DER_SOLAR1_DUMMY || block == MPBlocks.DER_SOLAR2_DUMMY || block == MPBlocks.DER_SOLAR3_DUMMY || block == MPBlocks.DER_SOLAR4_DUMMY)
        {
            int process = nbt.getInteger("ActivatedTick") * 100 / 12000;
            int destruct = nbt.getInteger("FailedTick");

            if (destruct > 0)
            {
                destruct = 600 - destruct;
                tooltip.add(TextFormatting.DARK_RED + LangUtils.translate("gui.status.destruct.name") + ": " + CommonUtils.ticksToElapsedTime(destruct));
            }
            else
            {
                tooltip.add(LangUtils.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            }

            if (process > 0 && process < 100)
            {
                tooltip.add("Process: " + process + "%");
            }
        }
        if (block == MPBlocks.WARP_PAD_DUMMY)
        {
            if (tile instanceof TileEntityDummy)
            {
                TileEntityDummy dummy = (TileEntityDummy) tile;
                TileEntitySpaceWarpPadFull warp = (TileEntitySpaceWarpPadFull) accessor.getWorld().getTileEntity(dummy.mainBlockPosition);
                String dimension = LangUtils.translate("gui.status.unknown.name");
                String name = LangUtils.translate("gui.status.unknown.name");
                String dest = LangUtils.translate("gui.status.unknown.name");

                if (warp.hasWarpCore() && warp.containingItems.get(1).hasTagCompound())
                {
                    NBTTagCompound compound = warp.containingItems.get(1).getTagCompound();
                    dimension = String.valueOf(compound.getInteger("DimensionID"));
                    name = WorldUtil.getProviderForDimensionClient(compound.getInteger("DimensionID")).getDimensionType().getName();
                    dest = compound.getInteger("X") + " " + compound.getInteger("Y") + " " + compound.getInteger("Z");
                }
                tooltip.add(LangUtils.translate("gui.status.dimension.name") + ": " + dimension + " ");
                tooltip.add(LangUtils.translate("gui.status.name.name") + ": " + name);
                tooltip.add(LangUtils.translate("gui.status.destination.name") + ": " + dest);
            }
        }
        if (block == MPBlocks.SHIELD_GENERATOR_DUMMY)
        {
            int chargeCooldown = nbt.getInteger("ShieldChargeCooldown");
            tooltip.add(LangUtils.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            tooltip.add(LangUtils.translate("gui.status.shield_damage.name") + ": " + nbt.getInteger("ShieldDamage"));
            tooltip.add(LangUtils.translate("gui.status.shield_size.name") + ": " + nbt.getInteger("MaxShieldSize"));
            tooltip.add(LangUtils.translate("gui.status.shield_capacity.name") + ": " + nbt.getInteger("ShieldCapacity") + "/" + nbt.getInteger("MaxShieldCapacity"));

            if (chargeCooldown > 0)
            {
                tooltip.add(LangUtils.translate("gui.status.shield_charge_cooldown.name") + ": " + chargeCooldown / 20);
            }
        }
        if (block == MPBlocks.NWT_MIDDLE_DUMMY || block == MPBlocks.NWT_TOP_DUMMY)
        {
            if (!nbt.getBoolean("HasRod") && !nbt.getBoolean("CreateRod"))
            {
                tooltip.add(LangUtils.translate("gui.status.no_waste_rod"));
            }
            if (nbt.getCompoundTag("FluidTank").getInteger("Amount") > 0 && nbt.getCompoundTag("FluidTank").getInteger("Amount") < 3000)
            {
                int amount = nbt.getCompoundTag("FluidTank").getInteger("Amount") * 100 / 3000;
                tooltip.add(LangUtils.translate("gui.status.has_waste"));
                tooltip.add(LangUtils.translate("gui.status.waste_fluid_amount") + ": " + amount + "%");
            }
            if (nbt.getInteger("Time") > 0 && nbt.getCompoundTag("FluidTank").getInteger("Amount") == 3000)
            {
                int cooldown = nbt.getInteger("RodCreateTime") * 100 / nbt.getInteger("Time");
                tooltip.add(LangUtils.translate("gui.status.rod_processing"));
                tooltip.add(LangUtils.translate("gui.status.create_rod_progress.name") + ": " + cooldown + "%");
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
                tooltip.add(TextFormatting.DARK_RED + LangUtils.translate("gui.status.destruct.name") + ": " + CommonUtils.ticksToElapsedTime(destruct));
            }
            else
            {
                tooltip.add(LangUtils.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            }

            if (process > 0 && process < 100)
            {
                tooltip.add(LangUtils.translate("gui.status.process.name") + ": " + process + "%");
            }
        }
        if (tile instanceof TileEntityDarkEnergyCore)
        {
            int transform = nbt.getInteger("ProduceTime");

            if (transform > 0)
            {
                tooltip.add(LangUtils.translate("gui.status.transform_time.name") + ": " + CommonUtils.ticksToElapsedTime(transform));
            }
        }
        if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            int generateWatts = nbt.getInteger("GenerateWatts");
            int fuel = nbt.getInteger("DarkEnergyFuel");
            tooltip.add(LangUtils.translate("gui.message.generating.name") + ": " + (generateWatts > 0 ? EnergyDisplayHelper.getEnergyDisplayS(generateWatts) + "/t" : LangUtils.translate("gui.status.not_generating.name")));
            tooltip.add(LangUtils.translate("gui.status.dark_energy_fuel.name") + ": " + (fuel > 0 ? String.valueOf(fuel * 100 / 1000) + "%" : TextFormatting.GOLD + LangUtils.translate("gui.status.empty.name")));
        }
        if (tile instanceof TileEntitySpaceWarpPadFull)
        {
            TileEntitySpaceWarpPadFull warp = (TileEntitySpaceWarpPadFull) tile;
            String dimension = LangUtils.translate("gui.status.unknown.name");
            String name = LangUtils.translate("gui.status.unknown.name");
            String dest = LangUtils.translate("gui.status.unknown.name");

            if (warp.hasWarpCore() && warp.containingItems.get(1).hasTagCompound())
            {
                NBTTagCompound compound = warp.containingItems.get(1).getTagCompound();
                dimension = String.valueOf(compound.getInteger("DimensionID"));
                name = WorldUtil.getProviderForDimensionClient(compound.getInteger("DimensionID")).getDimensionType().getName();
                dest = compound.getInteger("X") + " " + compound.getInteger("Y") + " " + compound.getInteger("Z");
            }
            tooltip.add(LangUtils.translate("gui.status.dimension.name") + ": " + dimension + " ");
            tooltip.add(LangUtils.translate("gui.status.name.name") + ": " + name);
            tooltip.add(LangUtils.translate("gui.status.destination.name") + ": " + dest);
        }
        if (tile instanceof TileEntityEnergyStorageClusterMP || tile instanceof TileEntityNuclearWasteGenerator)
        {
            tooltip.add(TextFormatting.GREEN + LangUtils.translate("gui.message.max_energy") + ": " + EnergyDisplayHelper.getEnergyDisplayS(nbt.getFloat("MaxEnergy")));
            tooltip.add(LangUtils.translate("gui.max_output.desc") + ": " + EnergyDisplayHelper.getEnergyDisplayS(nbt.getFloat("MaxOutput")) + "/t");

            if (nbt.hasKey("Status"))
            {
                tooltip.add(LangUtils.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            }
        }
        if (tile instanceof TileEntityBlackHoleStorage)
        {
            String owner = LangUtils.translate("gui.status.unknown.name");
            String collectMode = nbt.getString("CollectMode").equals("item") ? LangUtils.translate("gui.status.collect_item.name") : nbt.getString("CollectMode").equals("item_and_xp") ? LangUtils.translate("gui.status.collect_item_and_xp.name") : LangUtils.translate("gui.status.collect_xp.name");

            try
            {
                owner = accessor.getWorld().getPlayerEntityByUUID(UUID.fromString(nbt.getString("OwnerUUID"))).getName();
            }
            catch (Exception e)
            {
                owner = "";
            }
            int xp = nbt.hasKey("XpFluid", Constants.NBT.TAG_COMPOUND) ? nbt.getCompoundTag("XpFluid").getInteger("Amount") : 0;
            tooltip.add(LangUtils.translate("gui.status.owner.name") + ": " + owner);
            tooltip.add(LangUtils.translate("gui.status.mode.name") + ": " + collectMode);
            tooltip.add(LangUtils.translate("desc.bhs_xp.name") + ": " + xp + "/" + 1000000);
        }
        if (tile instanceof TileEntityShieldGenerator)
        {
            int chargeCooldown = nbt.getInteger("ShieldChargeCooldown");
            tooltip.add(LangUtils.translate("gui.message.status.name") + ": " + nbt.getString("Status"));
            tooltip.add(LangUtils.translate("gui.status.shield_damage.name") + ": " + nbt.getInteger("ShieldDamage"));
            tooltip.add(LangUtils.translate("gui.status.shield_size.name") + ": " + nbt.getInteger("MaxShieldSize"));
            tooltip.add(LangUtils.translate("gui.status.shield_capacity.name") + ": " + nbt.getInteger("ShieldCapacity") + "/" + nbt.getInteger("MaxShieldCapacity"));

            if (chargeCooldown > 0)
            {
                tooltip.add(LangUtils.translate("gui.status.shield_charge_cooldown.name") + ": " + chargeCooldown / 20);
            }
        }
        if (tile instanceof TileEntityNuclearWasteTank)
        {
            if (!nbt.getBoolean("HasRod") && !nbt.getBoolean("CreateRod"))
            {
                tooltip.add(LangUtils.translate("gui.status.no_waste_rod"));
            }
            if (nbt.getCompoundTag("FluidTank").getInteger("Amount") > 0 && nbt.getCompoundTag("FluidTank").getInteger("Amount") < 3000)
            {
                int amount = nbt.getCompoundTag("FluidTank").getInteger("Amount") * 100 / 3000;
                tooltip.add(LangUtils.translate("gui.status.has_waste"));
                tooltip.add(LangUtils.translate("gui.status.waste_fluid_amount") + ": " + amount + "%");
            }
            if (nbt.getInteger("Time") > 0 && nbt.getCompoundTag("FluidTank").getInteger("Amount") == 3000)
            {
                int cooldown = nbt.getInteger("RodCreateTime") * 100 / nbt.getInteger("Time");
                tooltip.add(LangUtils.translate("gui.status.rod_processing"));
                tooltip.add(LangUtils.translate("gui.status.create_rod_progress.name") + ": " + cooldown + "%");
            }
        }
        return tooltip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        TileEntity tile = accessor.getTileEntity();
        Block block = accessor.getBlock();

        if (ConfigManagerMP.moreplanets_other.enableDescriptionInWaila)
        {
            if (block instanceof IDescription)
            {
                IDescription desc = (IDescription) block;

                if (ClientUtils.isShiftKeyDown())
                {
                    desc.getDescription().addDescription(itemStack, tooltip);
                }
                else
                {
                    tooltip.add(LangUtils.translate("desc.shift_info.name"));
                }
            }
            // block
            if (block.getClass().equals(BlockDummy.class))
            {
                TileEntityDummy dummy = (TileEntityDummy) tile;

                if (accessor.getWorld().getBlockState(dummy.mainBlockPosition).getBlock() instanceof IDescription)
                {
                    IDescription desc = (IDescription) accessor.getWorld().getBlockState(dummy.mainBlockPosition).getBlock();

                    if (ClientUtils.isShiftKeyDown())
                    {
                        desc.getDescription().addDescription(itemStack, tooltip);
                    }
                    else
                    {
                        tooltip.add(LangUtils.translate("desc.shift_info.name"));
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

            if (dummy.mainBlockPosition != null)
            {
                BlockPos dummyPos = dummy.mainBlockPosition;

                if (world.getTileEntity(dummyPos) instanceof TileEntityDarkEnergyReceiver)
                {
                    TileEntityDarkEnergyReceiver receiver = (TileEntityDarkEnergyReceiver) world.getTileEntity(dummy.mainBlockPosition);
                    nbt.setString("Status", receiver.getGuiStatusWaila());
                    return receiver.writeToNBT(nbt);
                }
                if (world.getTileEntity(dummyPos) instanceof TileEntityShieldGenerator)
                {
                    TileEntityShieldGenerator shield = (TileEntityShieldGenerator) world.getTileEntity(dummy.mainBlockPosition);
                    nbt.setString("Status", shield.getStatus());
                    return shield.writeToNBT(nbt);
                }
                if (world.getTileEntity(dummyPos) instanceof TileEntityNuclearWasteTank)
                {
                    TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) world.getTileEntity(dummy.mainBlockPosition);
                    return tank.writeToNBT(nbt);
                }
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
        if (tile instanceof TileEntityEnergyStorageClusterMP)
        {
            TileEntityEnergyStorageClusterMP energy = (TileEntityEnergyStorageClusterMP) tile;
            nbt.setFloat("MaxEnergy", energy.getMaxEnergyStoredGC());
            nbt.setFloat("MaxOutput", energy.storage.getMaxExtract());
            return energy.writeToNBT(nbt);
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
        if (tile instanceof TileEntityNuclearWasteTank)
        {
            TileEntityNuclearWasteTank tank = (TileEntityNuclearWasteTank) tile;
            return tank.writeToNBT(nbt);
        }
        return tile.writeToNBT(nbt);
    }
}
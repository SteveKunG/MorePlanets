package stevekung.mods.moreplanets.module.planets.diona.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.stevekunglib.utils.ClientUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemBlockDarkEnergyGenerator extends ItemBlock
{
    public ItemBlockDarkEnergyGenerator(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        if (this.getBlock() instanceof IDescription)
        {
            if (ClientUtils.isShiftKeyDown())
            {
                ((IDescription)this.block).getDescription().addDescription(itemStack, list);
            }
            else
            {
                if (this.getBlock() instanceof BlockTileGC)
                {
                    TileEntity tile = ((BlockTileGC) this.getBlock()).createTileEntity(null, this.getBlock().getDefaultState());

                    if (tile instanceof TileBaseUniversalElectrical)
                    {
                        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("EnergyStored"))
                        {
                            int power = (int)itemStack.getTagCompound().getFloat("EnergyStored");
                            list.add(TextFormatting.GREEN + LangUtils.translate("desc.energy_stored.name", EnergyDisplayHelper.getEnergyDisplayS(power)));
                        }
                    }
                    if (tile instanceof TileEntityDarkEnergyGenerator)
                    {
                        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("DarkEnergyFuel"))
                        {
                            int power = itemStack.getTagCompound().getInteger("DarkEnergyFuel");
                            power = power * 100 / 1000;//TODO More dark energy fuel
                            list.add(TextFormatting.GREEN + LangUtils.translate("desc.dark_energy_fuel.name", power) + "%");
                        }
                    }
                }
                list.add(LangUtils.translate("desc.shift_info.name"));
            }
        }
    }
}
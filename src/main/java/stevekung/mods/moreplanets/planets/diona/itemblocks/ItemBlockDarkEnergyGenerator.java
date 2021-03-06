package stevekung.mods.moreplanets.planets.diona.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.itemblocks.ItemBlockTESRMP;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.moreplanets.utils.blocks.BlockTileMP;
import stevekung.mods.moreplanets.utils.items.IDarkEnergyFuel;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class ItemBlockDarkEnergyGenerator extends ItemBlockTESRMP
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
                if (this.getBlock() instanceof BlockTileMP)
                {
                    TileEntity tile = ((BlockTileMP) this.getBlock()).createNewTileEntity(null, 0);

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
                        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("DarkEnergyFuel") && itemStack.getTagCompound().hasKey("Items"))
                        {
                            int fuelValue = 0;
                            NBTTagList listTag = itemStack.getTagCompound().getTagList("Items", NBT.TAG_COMPOUND);

                            for (int i = 0; i < listTag.tagCount(); ++i)
                            {
                                NBTTagCompound compound = listTag.getCompoundTagAt(i);
                                int slot = compound.getByte("Slot") & 255;
                                ItemStack darkEnergyFuel = new ItemStack(compound);

                                if (slot == 2 && darkEnergyFuel.getItem() instanceof IDarkEnergyFuel)
                                {
                                    IDarkEnergyFuel fuel = (IDarkEnergyFuel)darkEnergyFuel.getItem();
                                    fuelValue = fuel.getDarkEnergyFuel();
                                }
                            }
                            int power = itemStack.getTagCompound().getInteger("DarkEnergyFuel");
                            power = power * 100 / fuelValue;
                            list.add(TextFormatting.GREEN + LangUtils.translate("desc.dark_energy_fuel.name", power) + "%");
                        }
                    }
                }
                list.add(LangUtils.translate("desc.shift_info.name"));
            }
        }
    }
}
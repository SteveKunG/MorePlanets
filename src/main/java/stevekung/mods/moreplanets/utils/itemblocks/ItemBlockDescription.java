package stevekung.mods.moreplanets.utils.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
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
import stevekung.mods.moreplanets.utils.blocks.IBlockDescription;
import stevekung.mods.stevekunglib.utils.ClientUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemBlockDescription extends ItemBlock
{
    public ItemBlockDescription(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        if (this.getBlock() instanceof IBlockDescription)
        {
            if (ClientUtils.isShiftKeyDown())
            {
                ((IBlockDescription)this.block).getDescription().addDescription(itemStack, list);
            }
            else
            {
                if (this.getBlock() instanceof BlockTileGC)
                {
                    TileEntity te = ((BlockTileGC) this.getBlock()).createTileEntity(null, this.getBlock().getStateFromMeta(itemStack.getItemDamage() & 12));

                    if (te instanceof TileBaseElectricBlock)
                    {
                        float powerDrawn = ((TileBaseElectricBlock) te).storage.getMaxExtract();

                        if (powerDrawn > 0)
                        {
                            list.add(TextFormatting.GREEN + LangUtils.translate("item_desc.powerdraw.name", EnergyDisplayHelper.getEnergyDisplayS(powerDrawn * 20)));
                        }
                    }
                    if (te instanceof TileBaseUniversalElectrical)
                    {
                        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("EnergyStored"))
                        {
                            int power = (int)itemStack.getTagCompound().getFloat("EnergyStored");
                            list.add(TextFormatting.GREEN + LangUtils.translate("desc.energy_stored.name", EnergyDisplayHelper.getEnergyDisplayS(power)));
                        }
                    }
                }
                else if (this.getBlock() instanceof BlockAdvancedTile)
                {
                    TileEntity te = ((BlockAdvancedTile) this.getBlock()).createTileEntity(world, this.getBlock().getStateFromMeta(itemStack.getItemDamage() & 12));

                    if (te instanceof TileBaseElectricBlock)
                    {
                        float powerDrawn = ((TileBaseElectricBlock) te).storage.getMaxExtract();

                        if (powerDrawn > 0)
                        {
                            list.add(TextFormatting.GREEN + LangUtils.translate("item_desc.powerdraw.name", EnergyDisplayHelper.getEnergyDisplayS(powerDrawn * 20)));
                        }
                    }
                }
                list.add(LangUtils.translate("desc.shift_info.name"));
            }
        }
    }
}
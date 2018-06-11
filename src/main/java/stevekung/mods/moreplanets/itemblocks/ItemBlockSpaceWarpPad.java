package stevekung.mods.moreplanets.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.IDescription;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockMP;
import stevekung.mods.stevekunglib.utils.LangUtils;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class ItemBlockSpaceWarpPad extends ItemBlockMP
{
    public ItemBlockSpaceWarpPad(Block block)
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
                list.add(TextFormatting.GREEN + LangUtils.translate("item_desc.powerdraw.name", EnergyDisplayHelper.getEnergyDisplayS(75 * 20)));
                list.add(LangUtils.translate("desc.shift_info.name"));
            }
        }
    }
}
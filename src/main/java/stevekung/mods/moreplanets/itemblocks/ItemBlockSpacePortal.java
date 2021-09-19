package stevekung.mods.moreplanets.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemBlockSpacePortal extends ItemBlockMP
{
    public ItemBlockSpacePortal(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        list.add(LangUtils.translate("description.space_portal"));
    }
}
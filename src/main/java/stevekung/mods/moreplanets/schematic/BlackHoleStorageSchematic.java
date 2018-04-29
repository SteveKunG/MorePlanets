package stevekung.mods.moreplanets.schematic;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.gui.GuiBlackHoleStorageSchematic;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.inventory.ContainerBlackHoleStorageSchematic;
import stevekung.mods.moreplanets.utils.schematic.SchematicMP;

public class BlackHoleStorageSchematic extends SchematicMP
{
    @Override
    public int getPageID()
    {
        return ConfigManagerMP.moreplanets_other.idBaseSchematic;
    }

    @Override
    public int getGuiID()
    {
        return ConfigManagerMP.moreplanets_other.idBaseSchematicGui;
    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(MPItems.BLACK_HOLE_STORAGE_SCHEMATIC);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiBlackHoleStorageSchematic(player.inventory, pos);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerBlackHoleStorageSchematic(player.inventory, pos);
    }
}
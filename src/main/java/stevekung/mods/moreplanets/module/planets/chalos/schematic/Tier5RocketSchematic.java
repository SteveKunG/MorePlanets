package stevekung.mods.moreplanets.module.planets.chalos.schematic;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.inventory.ContainerTier5RocketSchematic;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.client.gui.GuiRocketSchematicMP;
import stevekung.mods.moreplanets.util.schematic.SchematicMP;

public class Tier5RocketSchematic extends SchematicMP
{
    @Override
    public int getPageID()
    {
        return ConfigManagerMP.moreplanets_other.idBaseRocketSchematic + 1;
    }

    @Override
    public int getGuiID()
    {
        return ConfigManagerMP.moreplanets_other.idBaseRocketSchematicGui + 1;
    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(DionaItems.TIER_5_ROCKET_SCHEMATIC, 1, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiRocketSchematicMP(new ContainerTier5RocketSchematic(player.inventory, pos), pos, 5);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerTier5RocketSchematic(player.inventory, pos);
    }
}
package stevekung.mods.moreplanets.module.planets.nibiru.schematic;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.nibiru.inventory.ContainerTier6RocketSchematic;
import stevekung.mods.moreplanets.util.client.gui.GuiRocketSchematicMP;
import stevekung.mods.moreplanets.util.schematic.SchematicMP;

public class Tier6RocketSchematic extends SchematicMP
{
    @Override
    public int getPageID()
    {
        return ConfigManagerMP.idBaseRocketSchematic + 2;
    }

    @Override
    public int getGuiID()
    {
        return ConfigManagerMP.idBaseRocketSchematicGui + 2;
    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(ChalosItems.TIER_6_ROCKET_SCHEMATIC, 1, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
    {
        return new GuiRocketSchematicMP(new ContainerTier6RocketSchematic(player.inventory, pos), pos, 6);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos)
    {
        return new ContainerTier6RocketSchematic(player.inventory, pos);
    }
}
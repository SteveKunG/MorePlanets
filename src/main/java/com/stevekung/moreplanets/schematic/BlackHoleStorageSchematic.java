package com.stevekung.moreplanets.schematic;

import com.stevekung.moreplanets.client.gui.GuiBlackHoleStorageSchematic;
import com.stevekung.moreplanets.core.config.ConfigManagerMP;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.inventory.ContainerBlackHoleStorageSchematic;
import com.stevekung.moreplanets.utils.schematic.SchematicMP;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
package com.stevekung.moreplanets.planets.diona.blocks;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.utils.CompatibilityManagerMP;
import com.stevekung.moreplanets.utils.blocks.BlockBaseMP;
import com.stevekung.moreplanets.utils.blocks.MPBlockCategory;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;

public class BlockGlowingIronBlock extends BlockBaseMP implements IDetectableResource
{
    public BlockGlowingIronBlock(String name)
    {
        super(name, Material.IRON);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.INGOT_BLOCK;
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return ColorUtils.stringToRGB(ItemRarity.ALIEN);
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }
}
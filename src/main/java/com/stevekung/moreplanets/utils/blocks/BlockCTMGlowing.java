package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.moreplanets.utils.CompatibilityManagerMP;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCTMGlowing extends BlockBaseMP
{
    public BlockCTMGlowing(String name, Material material)
    {
        super(material);
        this.setTranslationKey(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }
}
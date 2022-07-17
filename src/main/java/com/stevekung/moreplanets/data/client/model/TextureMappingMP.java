package com.stevekung.moreplanets.data.client.model;

import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;

public class TextureMappingMP
{
    public static TextureMapping displayJar(Block displayJar)
    {
        return TextureMapping.singleSlot(TextureSlotMP.TERRARIUM, TextureMapping.getBlockTexture(displayJar));
    }

    public static TextureMapping compactedCrystal(Block crystal)
    {
        return TextureMapping.singleSlot(TextureSlotMP.CRYSTAL, TextureMapping.getBlockTexture(crystal));
    }
}